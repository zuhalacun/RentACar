package com.btkAkademi.rentACar.business.requests.carRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	
	@NotNull
	private int id;
	
	private int brandId;
	private int colorId;
	
	
	@NotNull
	private double dailyPrice;
	
	@NotNull
	private int modelYear;
	
	@NotBlank
	private String imageUrl;
	
	@NotBlank
	@Size(min=3, max=25)
	private String description;
	
	@NotNull
	private int findexScore;
	@NotNull
	private int kilometer;
	
	private int minCustomerAge;
	private int segmentId;
}

