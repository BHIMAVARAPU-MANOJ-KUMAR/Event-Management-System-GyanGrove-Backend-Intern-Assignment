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
}