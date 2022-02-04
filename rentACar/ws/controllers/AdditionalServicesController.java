package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalserviceService;
import com.btkAkademi.rentACar.business.requests.additionalserviceRequests.CreateAdditionalserviceRequest;
import com.btkAkademi.rentACar.business.requests.additionalserviceRequests.UpdateAdditionalserviceRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalservices")
@CrossOrigin
public class AdditionalServicesController {
	private AdditionalserviceService additionalserviceService;

	@Autowired
	public AdditionalServicesController(AdditionalserviceService additionalserviceService) {
		super();
		this.additionalserviceService = additionalserviceService;
	}
	@GetMapping("find-all-by-rental-id/{id}")
	public Result findAllByRentalId(@PathVariable int id) {
		return additionalserviceService.getAllByRentalId(id);
		
	}
	@PostMapping("add")
	public Result add(@RequestBody  @Valid CreateAdditionalserviceRequest createAdditionalserviceRequest) {
		return this.additionalserviceService.add(createAdditionalserviceRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateAdditionalserviceRequest updateAdditionalServiceRequest) {
		return this.additionalserviceService.update(updateAdditionalServiceRequest);
	}
	@PostMapping("delete/{id}")
	public Result update(@PathVariable int id) {
		return this.additionalserviceService.delete(id);
	}

	
}
