package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandsRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandsRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface BrandService {

	   DataResult<List<BrandListDto>> getAll();
	    DataResult<BrandListDto> findById(int id);
	    
		Result add(CreateBrandRequest createBrandRequest);
		Result update(UpdateBrandRequest updateBrandRequest);
		Result delete(int id);
		
}
