package com.btkAkademi.rentACar.business.requests.rentalsRequests;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    

	private int customerId;
	private int carId;
	private int findexScore;
	private LocalDate rentDate;
    private LocalDate returnDate;
	private int rentedKilometer;
	private int returnedKilometer;
	private int pickupCityId;
	private int returnCityId;
	
	
}

