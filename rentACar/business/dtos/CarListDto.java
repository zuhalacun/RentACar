package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
	private int id;
	private double dailyPrice;	
	private int modelYear;
	private int minCustomerAge;
	private int minfindexScore;
	private String description;
	private int findexScore;	
	private String imageUrl;
	private int kilometer;	
	private String brandName;	
	private int brandId;
	private String colorName;
	private int segmentId;
	private int cityId;
	   
}
