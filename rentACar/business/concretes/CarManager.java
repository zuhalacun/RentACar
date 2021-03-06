package com.btkAkademi.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.City;
import com.btkAkademi.rentACar.entities.concretes.Rental;



@Service
public class CarManager implements CarService{
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private BrandService brandService;
	private ColorService colorService;
	private CityService cityService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService,
			ColorService colorService,CityService cityService) {
		super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
	    this.cityService =cityService;
	}

	@Override
	public DataResult<List<CarListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> carList = this.carDao.findAll(pageable).getContent();
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);

	}

	@Override
	public DataResult<List<CarListDto>> findAllByBrandId(int brandId, int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> carList = this.carDao.findAllByBrandId(brandId, pageable);
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);

	}

	@Override
	public DataResult<List<CarListDto>> findAllByColorId(int colorId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Car> carList = this.carDao.findAllByColorId(colorId,pageable);
		List<CarListDto> response = carList.stream().map(car -> modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);

	}

	@Override
	public DataResult<CarListDto> findByCarId(int id) {
            if(carDao.existsById(id)) {
			
			CarListDto response = modelMapperService.forDto().map(carDao.findById(id).get(), CarListDto.class);
					
			return new SuccessDataResult<CarListDto>(response);
		}
		else return new ErrorDataResult<>();

	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Result result = BusinessRules.run(
				checkIfColorExist(createCarRequest.getColorId()),
				checkIfBrandExists(createCarRequest.getBrandId()));
		if (result != null) {
			return result;
		}
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);

		return new SuccessResult(Messages.carAdded);
	}



	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Result result = BusinessRules.run(checkIfCarIdExists(updateCarRequest.getId()));

		if (result != null) {

			return result;
		}

		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);

		this.carDao.save(car);
		return new SuccessResult(Messages.carUpdated);

	}

	@Override
	public Result delete(int id) {
		if(carDao.existsById(id)){
			carDao.deleteById(id);
			return new SuccessResult("araba silindi");		}
		
		return new ErrorResult();

	}

	@Override
	public DataResult<CarListDto> findById(int carId) {
		if (carDao.existsById(carId)) {

			CarListDto response = modelMapperService.forDto().map(carDao.findById(carId), CarListDto.class);

			return new SuccessDataResult<CarListDto>(response);
		} else
			return new ErrorDataResult<>("Messages.notFound");
	}

	private Result checkIfCarIdExists(int id) {
		if (!this.carDao.existsById(id)) {

			return new ErrorResult("araba id bulunamad??");
		}
		return new SuccessResult();
	}
	
	
	private Result checkIfColorExist(int colorId) {
		if(!colorService.findById(colorId).isSuccess()) {
			return new ErrorResult("renk bulunamad??");					
		}else return new SuccessResult();			
	}

	private Result checkIfBrandExists(int brandId) {
		if (!brandService.findById(brandId).isSuccess()) {
			return new ErrorResult("marka bulunamad??");
		} else
			return new SuccessResult();
	}

	@Override
	public DataResult<List<CarListDto>> findAllBySegmentId(int segmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<Integer>> findAvailableCarsBySegmentId(int segmentId, int cityId) {
		if (carDao.findAvailableCarBySegment(segmentId, cityId).size() < 1) {
			return new ErrorDataResult<List<Integer>>();
		} else
			return new SuccessDataResult<List<Integer>>(carDao.findAvailableCarBySegment(segmentId, cityId));
	}
	

	@Override
	public Result updateCarKilometer(int carId, int kilometer) {
		Car car = carDao.findById(carId).get();
		car.setKilometer(kilometer);
		carDao.save(car);
		return new SuccessResult();
	}

	@Override
	public Result updateCarCity(int carId, int cityId) {
		Car car = carDao.findById(carId).get();
		City city = modelMapperService.forRequest().map(cityService.findById(cityId).getData(), City.class);
		car.setCity(city);   //neden yap??l??yor?
		carDao.save(car);
		return new SuccessResult();
	}


	
	
}
