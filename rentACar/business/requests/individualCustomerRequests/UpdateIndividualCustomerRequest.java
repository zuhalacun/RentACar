package com.btkAkademi.rentACar.business.requests.individualCustomerRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.brandsRequests.CreateBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	
	private int id;
	private String email;
	private String firstName;
	private String lastName;	
	private LocalDate birthDate;
}
