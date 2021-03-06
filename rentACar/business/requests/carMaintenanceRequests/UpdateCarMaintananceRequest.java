package com.btkAkademi.rentACar.business.requests.carMaintenanceRequests;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.brandsRequests.CreateBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintananceRequest {
	private int id;
	private int carId ;	
	private LocalDate maintenanceStart;
	private LocalDate maintenanceEnd;
}
