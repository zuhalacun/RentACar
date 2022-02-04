package com.btkAkademi.rentACar.business.concretes;


import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalserviceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CustomerCardDetailService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PromotionService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.AdditionalserviceListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.PromotionDto;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromotionDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.btkAkademi.rentACar.entities.concretes.Rental;
import com.btkAkademi.rentACar.core.utilities.adapters.abstracts.*;

@Service
public class PaymentManager implements PaymentService{

	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;	
	private RentalService rentalService;
	private CarService carService;
	private AdditionalserviceService additionalserviceService;
	private CustomerCardDetailService customerCardDetailService;
	private BankAdapterService bankAdapterService;
	private PromotionService promotionService;
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService,
			CarService carService, AdditionalserviceService additionalserviceService,
			 CustomerCardDetailService customerCardDetailService,BankAdapterService bankAdapterService,
			 PromotionService promotionService) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.carService = carService;
		this.additionalserviceService = additionalserviceService;
		this.customerCardDetailService = customerCardDetailService;
		this.bankAdapterService= bankAdapterService;
		this.promotionService=promotionService;
	}

	@Override
	public DataResult<List<PaymentListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Payment> paymentList = this.paymentDao.findAll(pageable).getContent();
		List<PaymentListDto> response = paymentList.stream().map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<List<PaymentListDto>> findAllByRentalId(int id) {
		List<Payment> paymentList = this.paymentDao.getAllByRentalId(id); //id yoksa error veriyor
		List<PaymentListDto> response = paymentList.stream().map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response);
	}

	@Override
	public DataResult<PaymentListDto> getById(int id) {
		if(paymentDao.existsById(id)) {
			Payment payment= paymentDao.findById(id).get();
			PaymentListDto response = modelMapperService.forDto().map(payment,PaymentListDto.class);
			return new SuccessDataResult<PaymentListDto>(response);
		}
		else return new ErrorDataResult<>();
	}

	

	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);	
		
		this.paymentDao.save(payment);
		
		return new SuccessResult("Messages.paymentUpdated");
	}

	@Override
	public Result delete(int id) {
		if(paymentDao.existsById(id)) {
			paymentDao.deleteById(id);
			return new SuccessResult("Messages.paymentDeleted");
		}
		return new ErrorResult();
	}

	
	

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		
		Result result = BusinessRules.run(
				  bankAdapterService.checkIfLimitIsEnough(
						createPaymentRequest.getCardNo(),
						createPaymentRequest.getDay(),
						createPaymentRequest.getMonth(),
						createPaymentRequest.getCvv(),
						createPaymentRequest.getTotalPaymentAmount()));
		if (result != null) {
			return result;
		}

		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		
		int rentalId= createPaymentRequest.getRentalId();
		
		
		RentalDto rental = rentalService.findById(rentalId).getData();
	
		
		double totalPrice = totalPriceCalculator(rental);
		
		payment.setTotalPaymentAmount(totalPrice);
		
		this.paymentDao.save(payment);
		
		return new SuccessResult("Messages.paymentAdded");
	}


private double totalPriceCalculator(RentalDto rental) {
		
		double totalPrice = 0.0;

		
		long days = ChronoUnit.DAYS.between( rental.getRentDate() , rental.getReturnDate()) ;
	 
		//aynı gün içinde alınıp bırakılacaksa günü 1 say.
		if(days==0) {
			days=1;
		}
		
		totalPrice+=days* carService.findByCarId(rental.getCarId()).getData().getDailyPrice();
		
		//ek hizmet aldıysa fiyata ekle
		List<AdditionalserviceListDto> services = additionalserviceService.getAllByRentalId(rental.getId()).getData();
		 
		for(AdditionalserviceListDto additionalService : services) {
			
			totalPrice+=additionalService.getPrice();
			
		}
	
		//promosyon indirim kodu varsa uygulanır.
		
		if(rental.getPromoCodeId()!=null) {
			PromotionDto promotionDto=this.promotionService.findByPromoCode(rental.getPromoCodeId()).getData();
			if(!promotionDto.getPromotionEnd().isAfter(rental.getReturnDate())) {
				totalPrice= totalPrice-totalPrice*promotionDto.getDiscountRate(); 
		}
		}
		return totalPrice;
	}

}
		
		



