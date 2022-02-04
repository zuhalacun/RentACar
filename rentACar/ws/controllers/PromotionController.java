package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.requests.promotionRequests.CreatePromotionRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/promotions")
@CrossOrigin
public class PromotionController {
     private PromotionService promotionService;

     @Autowired
	 public PromotionController(PromotionService promotionService) {
		super();
		this.promotionService = promotionService;
	}
     @PostMapping("add")
 	public Result add(@RequestBody  @Valid CreatePromotionRequest createPromotionRequest) {
 		return this.promotionService.add(createPromotionRequest);
 	}
}
