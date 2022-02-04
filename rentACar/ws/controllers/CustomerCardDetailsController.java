package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CustomerCardDetailService;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.CreateCustomerCardDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.UpdateCustomerCardDetailsRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/customer-card-details")
@CrossOrigin
public class CustomerCardDetailsController {
	private CustomerCardDetailService customerCardDetailService;
	
	@Autowired
	public CustomerCardDetailsController(CustomerCardDetailService customerCardDetailService) {
		super();
		this.customerCardDetailService = customerCardDetailService;
	}
	@GetMapping ("find-by-customer-id/{id}")
	public Result findByCustomerId(@PathVariable int id) {

		return this.customerCardDetailService.findCustomerPaymentDetailsByCustomerId(id);
	}
	@GetMapping ("find-by-id/{id}")
	public Result findById(@PathVariable int id) {

		return this.customerCardDetailService.findById(id);
	}
	
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCustomerCardDetailRequest createCustomerPaymentDetailRequest) {

		return this.customerCardDetailService.add(createCustomerPaymentDetailRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCustomerCardDetailsRequest updateCustomerPamentDetailsRequest) {

		return this.customerCardDetailService.update(updateCustomerPamentDetailsRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(@PathVariable int id) {

		return this.customerCardDetailService.delete(id);
	}
}
