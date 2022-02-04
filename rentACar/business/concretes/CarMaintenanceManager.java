package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService{
	
	private CarMaintenanceDao carMaintenanceDao; 
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
    private CarService carService;;
	
	@Autowired
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao,
			ModelMapperService modelMapperService,@Lazy RentalService rentalService,CarService carService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService=rentalService;
		this.carService=carService;
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		List<CarMaintenance> carMaintananceList = this.carMaintenanceDao.findAll();
		List<CarMaintenanceListDto> response = carMaintananceList.stream()
				.map(carMaintanance->modelMapperService.forDto()
				.map(carMaintanance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);

	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> findAllByCarId(int id) {
		List<CarMaintenance> carMaintananceList = this.carMaintenanceDao.findAllByCarId(id);
		List<CarMaintenanceListDto> response = carMaintananceList.stream()
				.map(carMaintanance->modelMapperService.forDto()
				.map(carMaintanance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);


	}

	@Override
	public DataResult<CarMaintenanceListDto> findById(int id) {
		if(carMaintenanceDao.existsById(id)) {
			CarMaintenance carMaintenance = carMaintenanceDao.findById(id).get();
			CarMaintenanceListDto response = modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class);
			return new SuccessDataResult<CarMaintenanceListDto>(response);
		}
		return new ErrorDataResult<CarMaintenanceListDto>();


	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintananceRequest) {
		Result result = BusinessRules.run(				
				checkIfCarIsExists(createCarMaintananceRequest.getCarId()),
				checkIfCarIsRented(createCarMaintananceRequest.getCarId()),
				checkIfCarIsAlreadyInMaintanance(createCarMaintananceRequest.getCarId())
				)	;		
		if(result!=null) {			
			return result;
		}
		
		CarMaintenance carMaintanance = this.modelMapperService.forRequest()
				.map(createCarMaintananceRequest,CarMaintenance.class);
		//to avoid mapping error
		carMaintanance.setId(0);
		
		this.carMaintenanceDao.save(carMaintanance);		
		return new SuccessResult("araba bakımı eklendi");

	}

	@Override
	public Result update(UpdateCarMaintananceRequest updateCarMaintananceRequest) {
		Result result=BusinessRules.run(checkIfCarIsExists(updateCarMaintananceRequest.getCarId()),
				checkIfCarIsRented(updateCarMaintananceRequest.getCarId()));
				
           if(result!=null) {			
	             return result;
   }
            CarMaintenance carMaintanance = this.modelMapperService.forRequest().map(updateCarMaintananceRequest,CarMaintenance.class);

            this.carMaintenanceDao.save(carMaintanance);		
            return new SuccessResult("araba bakımı güncellendi");

	}

	@Override
	public Result delete(int id) {
		if(carMaintenanceDao.existsById(id)) {
			carMaintenanceDao.deleteById(id);
			return new SuccessResult("araba bakımı silindi");
		}
		else return new ErrorResult();

	}

	@Override
	public boolean isCarInMaintenance(int carId) {
		if(carMaintenanceDao.findByCarIdAndMaintenanceEndIsNull(carId)!=null) {
			return true;
		}
		else return false;

	}

	@Override
	public DataResult<CarMaintenance> findByCarIdAndMaintenanceEndIsNull(int carId) {
		return new SuccessDataResult<CarMaintenance>(this.carMaintenanceDao.findByCarIdAndMaintenanceEndIsNull(carId));
	}

	private Result checkIfCarIsExists(int carId) {
		if(!carService.findByCarId(carId).isSuccess()) {
			return new ErrorResult("card id bulunamadı");
		}
		else return new SuccessResult();
	}
	
	private Result checkIfCarIsRented(int carId) {
		if(rentalService.isCarRented(carId)) {
			return new ErrorResult("araba kiralandı");
		}
		else return new SuccessResult();
	}
	
	private Result checkIfCarIsAlreadyInMaintanance(int carId) {
		if(isCarInMaintenance(carId)) {
			return new ErrorResult("araba bakımda");
		}
		else return new SuccessResult();
	}
}

	