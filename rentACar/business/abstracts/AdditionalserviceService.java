package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.AdditionalserviceListDto;
import com.btkAkademi.rentACar.business.requests.additionalserviceRequests.CreateAdditionalserviceRequest;
import com.btkAkademi.rentACar.business.requests.additionalserviceRequests.UpdateAdditionalserviceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


public interface AdditionalserviceService {
	DataResult<List<AdditionalserviceListDto>> getAllByRentalId(int rentalId);
	Result add(CreateAdditionalserviceRequest createAdditionalServiceRequest);
	Result update(UpdateAdditionalserviceRequest updateAdditionalServiceRequest);
	Result delete(int id);

}
