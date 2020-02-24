package com.meep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ResourceDTO {

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
	//Field that will be telling that the resource is no longer available
	private Boolean available;

}