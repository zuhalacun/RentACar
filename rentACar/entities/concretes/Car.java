package com.btkAkademi.rentACar.entities.concretes;

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
@AllArgsConstructor  //parametreli constructor
@NoArgsConstructor   //parametresiz constructor
@Entity
@Table(name="cars")
public class Car {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //otomatik artıyor.
	@Column(name="id")
	private int id;
	
	@Column(name="min_customer_age")
	private int minCustomerAge;
	
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="model_year")
    private int modelYear;
	
	@Column(name="image_url")
    private String imageUrl;
	
	@Column(name="description")
    private String description;
	
	@Column(name="findex_score")
    private int findexScore;  //kredilendirme puanı
	
	@Column(name="kilometer")
    private int kilometer;

    
    //sınıflar arasında ilişkilendirme:
    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand; 
    
    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;
    
    @ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
    
    @OneToMany(mappedBy="car")
    private List<CarMaintenance> carMaintenances;
    
    @OneToMany(mappedBy="car")
    private List<Rental> rentals;
    
    @OneToMany(mappedBy="car")
    private List<CarDamage> cardamages;
    
    @ManyToOne()
    @JoinColumn(name="segment_id")
    private Segment segment;
    
    
    
   
}
