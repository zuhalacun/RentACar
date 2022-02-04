package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.dtos.PromotionDto;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PromotionService {
   
	Result add(CreatePromotionRequest createPromotionRequest);
	boolean  isPromoCode (int id) ;
	DataResult<PromotionDto> findByPromoCode(String promoCode);
}
