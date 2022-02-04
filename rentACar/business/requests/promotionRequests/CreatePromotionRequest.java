package com.btkAkademi.rentACar.business.requests.promotionRequests;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionRequest {
   
	private int id;
	private String promoCode;
	private double discountRate;
	private LocalDate promotionStart;
	private LocalDate PromotionEnd;
}
