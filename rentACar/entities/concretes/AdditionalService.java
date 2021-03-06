package com.btkAkademi.rentACar.entities.concretes;


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
@Table(name="additionalservices")
public class AdditionalService {  //ek hizmetler
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //otomatik artıyor.
	@Column(name="id")
    private int id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="price")
	private double price;
	
	@ManyToOne()
	@JoinColumn(name="rental_id")
	private Rental rental;
}
