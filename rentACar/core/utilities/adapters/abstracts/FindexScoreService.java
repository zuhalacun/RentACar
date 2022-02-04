package com.btkAkademi.rentACar.core.utilities.adapters.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface FindexScoreService {
     // int calculateIndividualCustomerFindexScore(String idendityId);
     // int calculateCorporateCustomerFindexScore(String taxNumber);
	DataResult<Integer> getScoreOfIndividualCustomer(String nationalityId);

	DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber);
}
