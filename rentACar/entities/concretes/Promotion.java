package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name="promotions")
public class Promotion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //otomatik artıyor.
	@Column(name="id")
	private int id;
	
	@Column(name="promo_code")
	private String promoCode;
	
	@Column(name="discount_rate") //indirim kodu 
	private double discountRate;
	
	@Column(name="promotion_start")
	private LocalDate promotionStart;
	
	@Column(name="promotion_end")
	private LocalDate PromotionEnd;
	
	@OneToMany(mappedBy = "promotion")	
	private List<Rental> rentals;
}
