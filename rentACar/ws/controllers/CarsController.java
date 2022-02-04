package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarsController {
	
	private CarService carService;
	
	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}
    
	@GetMapping("getall")
	public DataResult<List<CarListDto>> getall(@RequestParam int pageNo,@RequestParam(defaultValue =" 10") int pageSize) {
		return this.carService.getAll(pageNo,pageSize);
	}
	@GetMapping("find-all-by-brand-id")
	public DataResult<List<CarListDto>> findAllByBrandId(
			@RequestParam int brandId,
			@RequestParam int pageNo,
			@RequestParam(defaultValue = " 10") int pageSize) {
		return this.carService.findAllByBrandId(brandId,pageNo, pageSize);
	}
	
	@GetMapping("find-all-by-color-id")
	public DataResult<List<CarListDto>> findAllByColorId(
			@RequestParam int colorId,
			@RequestParam int pageNo,
			@RequestParam(defaultValue = " 10") int pageSize) {
		return this.carService.findAllByColorId(colorId,pageNo, pageSize);
	}
	
	@GetMapping("find-by-id/{id}")
	public DataResult<CarListDto> findById(@PathVariable int id) {
		return this.carService.findByCarId(id);
	}
	
	

	
	@PostMapping("add")
	public Result add(@RequestBody  @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	
	@PostMapping("update")
	public Result add(@RequestBody  @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(@Valid @PathVariable int id) {
		return this.carService.delete(id);
	}
}
