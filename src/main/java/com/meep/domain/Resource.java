package com.meep.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Resource {
	private String id;
	private String name;
	private Double x;
	private Double y;
	private String licencePlate;
	private Integer range;
	private Integer batteryLevel;
	private Integer seats;
	private String model;
	private String resourceImageId;
	private Double pricePerMinuteParking;
	private Double pricePerMinuteDriving;
	private Boolean realTimeData;
	private String engineType;
	private String resourceType;
	private Integer companyZoneId;
	private Integer helmets;

}
