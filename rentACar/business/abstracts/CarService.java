package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface CarService {
	
	DataResult<List<CarListDto>> getAll(int pageNo, int pageSize);
	DataResult<List<CarListDto>> findAllByBrandId(int brandId,int pageNo, int pageSize);
	DataResult<List<CarListDto>> findAllByColorId(int colorId,int pageNo,int pageSize);
	DataResult<CarListDto> findByCarId(int id);
	DataResult<List<CarListDto>> findAllBySegmentId(int segmentId);
	

	DataResult<List<Integer>> findAvailableCarsBySegmentId(int segmentId, int cityId);
	Result updateCarKilometer(int carId, int kilometer);

	Result updateCarCity(int carId, int cityId);
	
	Result add(CreateCarRequest createCarRequest);	
	Result update(UpdateCarRequest updateCarRequest);
	Result delete( int id);

	DataResult<CarListDto> findById(int carId);
}



