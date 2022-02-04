package com.btkAkademi.rentACar.business.requests.carRequests;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	private int brandId;
	private int colorId;
	private int cityId;
	@NotNull
    private double dailyPrice;
	private int modelYear;
	@Size(min=3,max=20,message=Messages.invalidCarName)
	private String description;
	private int findexScore;
	private int kilometer;
	private String imageUrl;
    private int minCustomerAge;
	private int segmentId;
}



