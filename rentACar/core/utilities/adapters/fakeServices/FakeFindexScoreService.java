package com.btkAkademi.rentACar.core.utilities.adapters.fakeServices;

public class FakeFindexScoreService {
  public int getScoreOfIndividualCustomer(String idendityId) {
	  if(idendityId != null) {
		  return 1000;
	  }
	  return 0;
  }
  public int getScoreOfCorporateCustomer(String taxNumber) {
	  if(taxNumber!=null) {
		  return 1250;
	  }
	  return 0;
  }
}
