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

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {
  
	private PaymentService paymentService;

	@Autowired
public PaymentController(PaymentService paymentService) {
	super();
	this.paymentService = paymentService;
}
@GetMapping("getall")
public DataResult<List<PaymentListDto>> getAll(@RequestParam int pageNo,
		@RequestParam(defaultValue = " 10") int pageSize) {
	return this.paymentService.getAll(pageNo, pageSize);
}
@GetMapping("find-all-by-rental-id/{id}")
public DataResult<List<PaymentListDto>> getAllByRentalId(@PathVariable int rentalId) {
	return this.paymentService.findAllByRentalId(rentalId);
}
@GetMapping("find-by-id/{id}")
public DataResult<PaymentListDto> findById(@PathVariable int id) {
	return this.paymentService.getById(id);
}
@PostMapping("add")
public Result add(@RequestBody  @Valid CreatePaymentRequest createPaymentRequest) {
	return this.paymentService.add(createPaymentRequest);
}
@PostMapping("update")
public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {

	return this.paymentService.update(updatePaymentRequest);
}
@PostMapping("delete/{id}")
public Result delete(@PathVariable int id) {
	return this.paymentService.delete(id);
}
}
