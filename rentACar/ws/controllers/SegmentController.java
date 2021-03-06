package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btkAkademi.rentACar.business.requests.segmentRequests.UpdateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/segments")
@CrossOrigin
public class SegmentController {
	private SegmentService segmentService;
	@Autowired
	public SegmentController(SegmentService segmentService) {
		super();
		this.segmentService = segmentService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateSegmentRequest createSegmentRequest) {
		return this.segmentService.add(createSegmentRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateSegmentRequest createSegmentRequest) {
		return this.segmentService.update(createSegmentRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.segmentService.delete(id);
	}
}
