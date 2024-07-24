package com.event.eventsmanagement.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.event.eventsmanagement.entity.Events;

public interface EventsRepository extends JpaRepository<Events, Long>, 
PagingAndSortingRepository<Events, Long> {
	
	@Query("SELECT e FROM Events e WHERE e.date "
			+ "BETWEEN :startDate AND :endDate ORDER BY e.date ASC")
	public abstract Page<Events> findByEventsWithinDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, Pageable pageable);
}