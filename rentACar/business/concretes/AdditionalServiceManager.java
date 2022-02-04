package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalserviceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.AdditionalserviceListDto;
import com.btkAkademi.rentACar.business.requests.additionalserviceRequests.CreateAdditionalserviceRequest;
import com.btkAkademi.rentACar.business.requests.additionalserviceRequests.UpdateAdditionalserviceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalserviceService{
	private AdditionalServiceDao additionalServiceDao;
	private RentalService rentalService;
	private ModelMapperService modelMapperService;
	

    @Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, RentalService rentalService,
			ModelMapperService modelMapperService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.rentalService = rentalService;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public DataResult<List<AdditionalserviceListDto>> getAllByRentalId(int rentalId) {
		List<AdditionalService> additionalServiceList = this.additionalServiceDao.findAllByRentalId(rentalId);
		List<AdditionalserviceListDto> response = additionalServiceList.stream()
				.map(additionalService -> modelMapperService.forDto()
						.map(additionalService, AdditionalserviceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalserviceListDto>>(response);
	}


	@Override
	public Result add(CreateAdditionalserviceRequest createAdditionalServiceRequest) {
		Result result = BusinessRules.run(
				checkIfRentalExists(createAdditionalServiceRequest.getRentalId()));		
		if(result!=null) {			
			return result;
		}
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult("ek hizmet eklendi");
	}


	@Override
	public Result update(UpdateAdditionalserviceRequest updateAdditionalServiceRequest) {
		Result result = BusinessRules.run(
				checkIfRentalExists(updateAdditionalServiceRequest.getRentalId())
				);
		
		if(result!=null) {			
			return result;
		}
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
		additionalServiceDao.save(additionalService);
		return new SuccessResult("ek hizmet güncellendi");
	}


	@Override
	public Result delete(int id) {
		if(additionalServiceDao.existsById(id)) {
			additionalServiceDao.deleteById(id);
			return new SuccessResult();
		}
		else return new ErrorResult();
	}
	
	private Result checkIfRentalExists(int rentalId) {
		if (!rentalService.findById(rentalId).isSuccess()) {
			return new ErrorResult("kiralama bulunamadı");
		} else
			return new SuccessResult();
	}
}


	