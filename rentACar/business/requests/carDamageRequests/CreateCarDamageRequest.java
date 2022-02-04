package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	@NotBlank
    private String description;
	private int carId ;
}
