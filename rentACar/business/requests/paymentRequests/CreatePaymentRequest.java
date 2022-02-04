package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	private int rentalId;
	private LocalDate paymentTime;
	private double totalPaymentAmount;	
	private String cardNo; 
	private String day; 
	private String month; 
	private String cvv;
}


