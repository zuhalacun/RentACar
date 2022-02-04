package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    
	private int id;

	private String promoCode;
	
	private double discountRate;
	
	private LocalDate promotionStart;
	
	private LocalDate PromotionEnd;
}
