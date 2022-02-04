package com.btkAkademi.rentACar.business.requests.brandsRequests;

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
public class UpdateBrandRequest {
	@NotNull
	private int id;
	
	@NotBlank
	@Size(min=3,max=20,message=Messages.invalidBrandName)
    private String name;
}
