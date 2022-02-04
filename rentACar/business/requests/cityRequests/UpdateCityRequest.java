package com.btkAkademi.rentACar.business.requests.cityRequests;

import javax.validation.constraints.NotBlank;

import com.btkAkademi.rentACar.business.requests.brandsRequests.CreateBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
	private int id;
	@NotBlank
	private String cityName;
}
