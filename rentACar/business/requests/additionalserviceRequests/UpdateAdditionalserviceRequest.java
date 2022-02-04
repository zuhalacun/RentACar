package com.btkAkademi.rentACar.business.requests.additionalserviceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalserviceRequest {
	
	private int id;
	private String name;
	private int rentalId;
	private double price;
}
