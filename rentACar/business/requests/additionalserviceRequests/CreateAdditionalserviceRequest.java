package com.btkAkademi.rentACar.business.requests.additionalserviceRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalserviceRequest {
   private int rentalId;
   private String name;
   private double price;
}
