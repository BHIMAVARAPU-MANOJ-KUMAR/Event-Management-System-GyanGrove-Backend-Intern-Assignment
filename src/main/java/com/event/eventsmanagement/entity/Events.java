package com.event.eventsmanagement.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Events")
@Table(name = "events",
uniqueConstraints = @UniqueConstraint(
		columnNames = "event_id",
		name = "event_id_unique"
		)
)
public final class Events {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;
	
	@Column(name = "event_name",
			nullable = false,
			updatable = false,
			columnDefinition = "VARCHAR(100)")
	@NotNull(message = "Event Name must not be Null")
	@NotEmpty(message = "Event Name must not be Empty")
	@NotBlank(message = "Event Name is mandatory")
	private String eventName;
	
	@Column(name = "city_name",
			nullable = false,
			updatable = false,
			columnDefinition = "VARCHAR(40)")
	@NotNull(message = "Event City Name must not be Null")
	@NotEmpty(message = "Event City Name must not be Empty")
	@NotBlank(message = "Event City Name is mandatory")
	private String cityName;
	
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "date",
			nullable = false,
			updatable = false,
			columnDefinition = "DATE")
	@NotNull(message = "Event Date is mandatory")
	private LocalDate date;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "time",
			nullable = false,
			updatable = false,
			columnDefinition = "TIME")
	@NotNull(message = "Event Time is mandatory")
	private LocalTime time;
	
	@Column(name = "latitude",
			nullable = false,
			updatable = false,
			columnDefinition = "DOUBLE PRECISION")
	@NotNull(message = "Latitude Coordinates are mandatory")
	private Double latitude;
	
	@Column(name = "longitude",
			nullable = false,
			updatable = false,
			columnDefinition = "DOUBLE PRECISION")
	@NotNull(message = "Longitude Coordinates are mandatory")
	private Double longitude;
	
	
	public Events(String eventName, String cityName, LocalDate date, LocalTime time, double latitude,
			double longitude) {
		this.eventName = eventName;
		this.cityName = cityName;
		this.date = date;
		this.time = time;
		this.latitude = latitude;
		this.longitude=longitude;
	}
}