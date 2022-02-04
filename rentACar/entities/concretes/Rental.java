package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rentals")
public class Rental {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column(name="rentDate")
    private LocalDate rentDate;
	
	@Column(name="returnDate")
    private LocalDate returnDate;
	
	@Column(name="rentedKilometer")
	private int rentedKilometer;
    
	@Column(name="returnedKilometer")
	private int returnedKilometer;
	
	@ManyToOne()
	@JoinColumn(name="pickup_city_id")
	private City pickUpCity;
	
	
	@ManyToOne
	@JoinColumn(name="return_city_id")
	private City returnCity;
	
	
	@ManyToOne  //çok rental bir customer
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> addtionalServices;
	
	@OneToMany(mappedBy="rental")
	private List<Payment> payments;
	
	@OneToMany(mappedBy="rental")
	private List<Invoice> invoices;
	
	@ManyToOne
	@JoinColumn(name="promo_id")
	private Promotion promotion;
	
} 
