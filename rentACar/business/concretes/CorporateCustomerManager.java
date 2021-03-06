package com.btkAkademi.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;

	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	
	@Override
	public DataResult<List<CorporateCustomerListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(pageable).getContent();
		List<CorporateCustomerListDto> response = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomer,
						CorporateCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
	}
	
	
	@Override
	public DataResult<CorporateCustomerListDto> findById(int id) {
		if(corporateCustomerDao.existsById(id)) {
			CorporateCustomer corporateCustomer = corporateCustomerDao.findById(id).get();
			CorporateCustomerListDto response = modelMapperService.forDto().map(corporateCustomer, CorporateCustomerListDto.class);
			return new SuccessDataResult<>(response); 
		}
		return null;
	}

	
	
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()),
				checkIfEmailExists(createCorporateCustomerRequest.getEmail()));

		if (result != null) {
			return result;
		}

		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult("kurumsal m????teri eklendi");
	}
	
	
	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult("kurumsal m????teri g??ncellendi");
	}
	
	
	@Override
	public Result delete(int id) {
		if(corporateCustomerDao.existsById(id)) {
			corporateCustomerDao.deleteById(id);
			return new SuccessResult("kurumsal m????teri silindi");
		}
		return new ErrorResult();
	}



	private Result checkIfCompanyNameExists(String companyName) {
		if (corporateCustomerDao.findByCompanyName(companyName) != null) {
			return new ErrorResult("kurum ismi bulunamad??");
		}
		return new SuccessResult();

	}
	private Result checkIfEmailExists(String email) {
		if (corporateCustomerDao.findByEmail(email) != null) {
			return new ErrorResult("email bulunamad??");
		}
		return new SuccessResult();
	}


	

}
