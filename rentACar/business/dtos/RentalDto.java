package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
	private Integer id;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private Integer rentedKilometer;
	private Integer returnedKilometer;
	private Integer customerId;
	private Integer carId;
	private Integer pickUpCityId;
	private Integer returnCityId;
	
	private String promoCodeId;
}
