package com.btkAkademi.rentACar.business.requests.corporateCustomerRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	@NotBlank
	private String email;
	private String password;
    private String companyName;
	private String taxNumber;
}
	
