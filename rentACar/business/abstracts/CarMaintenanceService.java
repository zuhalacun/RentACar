package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;


import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

public interface CarMaintenanceService {
	
	
	DataResult<List<CarMaintenanceListDto>> getAll();
	DataResult<List<CarMaintenanceListDto>> findAllByCarId(int id);
	DataResult<CarMaintenanceListDto> findById(int id);
	
	Result add(CreateCarMaintenanceRequest createCarMaintananceRequest);
	Result update(UpdateCarMaintananceRequest updateCarMaintananceRequest);
	Result delete(int id);
	
	boolean isCarInMaintenance (int carId) ;
	
	DataResult<CarMaintenance> findByCarIdAndMaintenanceEndIsNull(int carId);
}

