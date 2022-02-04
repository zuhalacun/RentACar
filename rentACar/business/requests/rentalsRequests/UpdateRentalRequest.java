package com.btkAkademi.rentACar.business.requests.rentalsRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.brandsRequests.CreateBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	private int id;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private Integer rentedKilometer;
	private Integer returnedKilometer;
	private int pickUpCityId;
	private int returnCityId;
	private int customerId;	
	private int carId;
}
