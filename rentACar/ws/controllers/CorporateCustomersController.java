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

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporatecustomers")
@CrossOrigin
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}
	@GetMapping("getall")
	public DataResult<List<CorporateCustomerListDto>> getAll(@RequestParam int pageNo,@RequestParam(defaultValue = "10") int pageSize) {

		return this.corporateCustomerService.getAll(pageNo,pageSize);
	}
	@GetMapping("find-by-id/{id}")
	public DataResult<CorporateCustomerListDto> findById(@PathVariable int id) {

		return this.corporateCustomerService.findById(id);
	}
	@PostMapping("add")
	public Result add(@RequestBody  @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {

		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.corporateCustomerService.delete(id);
	}
}

