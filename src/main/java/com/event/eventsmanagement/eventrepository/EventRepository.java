package com.event.eventsmanagement.eventrepository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.event.eventsmanagement.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>, 
PagingAndSortingRepository<Event, Long> {
	
	@Query("SELECT e FROM Events e WHERE e.date "
			+ "BETWEEN :startDate AND :endDate ORDER BY e.date ASC")
	public abstract Page<Event> findByEventsWithinDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, Pageable pageable);
	
//	SELECT * FROM "function_find_events_within_date_range"('2024-03-14', '2024-03-28');
//	SELECT "function_find_events_within_date_range"('2024-03-14', '2024-03-28');
//	SELECT COUNT(*) FROM "function_find_events_within_date_range"('2024-03-14', '2024-03-28');
	@Query(value = "SELECT * FROM function_find_events_within_date_range(:startDate, :endDate)",
			nativeQuery = true)
	public abstract Page<Event> findByEventsWithinDateRange1(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, Pageable pageable);
}