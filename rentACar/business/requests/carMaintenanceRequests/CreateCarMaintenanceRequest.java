package com.btkAkademi.rentACar.business.requests.carMaintenanceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	private int carId ;
    private String carName;
    private LocalDate maintenanceStart;
    private LocalDate maintenanceEnd;
}
