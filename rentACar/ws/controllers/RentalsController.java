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

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.requests.rentalsRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalsRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin
public class RentalsController {
	
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	@GetMapping("getall")
	public DataResult<List<RentalDto>> getAll(@RequestParam int pageNo, @RequestParam(defaultValue = "10") int pageSize){
		return rentalService.getAll(pageNo, pageSize);
	}	
	@GetMapping("find-all-by-customer-id/{id}")
	public DataResult<List<RentalDto>> findAllByCustomerId(@PathVariable int id){
		return rentalService.findAllByCustomerId(id);
	}	
	@GetMapping("find-by-id/{id}")
	public DataResult<RentalDto> findById(@PathVariable int id){
		return rentalService.findById(id);
	}	
	@PostMapping("add-for-individual-customer")
	public Result addForIndividualCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return this.rentalService.addForIndividualCustomer(createRentalRequest);
	}

	@PostMapping("add-for-corporate-customer")
	public Result addForCorporateCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return this.rentalService.addForCorporateCustomer(createRentalRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.rentalService.delete(id);
	}
	

}
