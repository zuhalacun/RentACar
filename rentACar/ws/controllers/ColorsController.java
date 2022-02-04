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
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorsRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorsRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


	@RestController
	@RequestMapping("/api/colors")
	@CrossOrigin
	public class ColorsController {
	    
		private ColorService colorService;

		@Autowired
		public ColorsController(ColorService colorService) {
			super();
			this.colorService = colorService;
		}
	    
		@GetMapping("getall")
	    public DataResult<List<ColorListDto>> getAll(){
	    	return this.colorService.getAll();
	    }
		@GetMapping("find-by-id/{id}")
		public DataResult<ColorListDto> findById(@PathVariable int id) {
			return this.colorService.findById(id);
		}
		@PostMapping("add")
		public Result add(@RequestBody  @Valid CreateColorRequest createColorRequests) {
			return this.colorService.add(createColorRequests);
		}
		@PostMapping("update")
		public Result update(@RequestBody  @Valid UpdateColorRequest updateColorRequests) {
			return this.colorService.update(updateColorRequests);
		}
		@PostMapping("delete/{id}")
		public Result delete(@Valid @PathVariable int id) {

			return this.colorService.delete(id);
		}
	    }

