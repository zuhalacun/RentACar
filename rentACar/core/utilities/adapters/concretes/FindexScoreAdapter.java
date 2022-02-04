package com.btkAkademi.rentACar.core.utilities.adapters.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.utilities.adapters.abstracts.FindexScoreService;
import com.btkAkademi.rentACar.core.utilities.adapters.fakeServices.FakeFindexScoreService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;

@Service
public class FindexScoreAdapter implements FindexScoreService{

	@Override
	public DataResult<Integer> getScoreOfIndividualCustomer(String nationalityId) {
		FakeFindexScoreService fakeFindexScoreService = new FakeFindexScoreService();
		return new SuccessDataResult<Integer>(fakeFindexScoreService.getScoreOfIndividualCustomer(nationalityId));
	}

	@Override
	public DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber) {
		FakeFindexScoreService fakeFindexScoreService = new FakeFindexScoreService();
		return new SuccessDataResult<Integer>(fakeFindexScoreService.getScoreOfCorporateCustomer(taxNumber));
	}

	

}
