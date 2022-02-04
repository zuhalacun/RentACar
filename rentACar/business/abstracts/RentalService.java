package com.btkAkademi.rentACar.business.abstracts;


import java.util.List;

import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.requests.rentalsRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalsRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface RentalService {
	
	Result addForIndividualCustomer(CreateRentalRequest createRentalRequest);
	Result addForCorporateCustomer(CreateRentalRequest createRentalRequest);
	DataResult<List<RentalDto>>  getAll(int pageNo,int pageSize);
	DataResult<List<RentalDto>>  findAllByCustomerId(int id);
	DataResult<RentalDto> findById(int id);
		
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(int id);
	
	boolean isCarRented(int carId);

	
}
