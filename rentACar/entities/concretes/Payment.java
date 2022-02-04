package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name="payments")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name="payment_time")
	private LocalDateTime paymentTime;
	
	@Column(name="total_payment_amount")
	private double totalPaymentAmount;	
	
	@Column(name="payment_type")
	private String paymentType;
	
	  @ManyToOne
	  @JoinColumn(name="rental_id")
	  private Rental rental;

}