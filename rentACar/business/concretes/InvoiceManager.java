package com.btkAkademi.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalserviceService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.AdditionalserviceListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalDto;
import com.btkAkademi.rentACar.business.requests.InvoceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.InvoceRequests.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private RentalService rentalService;
	private PaymentService paymentService;
	private AdditionalserviceService additionalserviceService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			RentalService rentalService, PaymentService paymentService,
			AdditionalserviceService additionalServiceService) {

		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.rentalService = rentalService;
		this.paymentService = paymentService;
		this.additionalserviceService = additionalServiceService;
		}

	@Override
	public DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(int rentalId) {
		return null;

	}

	@Override
	public DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(int rentalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Result result = BusinessRules.run(checkIfInvoiceAlreadyExists(createInvoiceRequest.getRentalId()));
		if (result != null) {
			return result;
		}
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult("Messages.invoiceAdded");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult("Messages.invoiceUpdated");
	}

	@Override
	public Result delete(int id) {
		if (invoiceDao.existsById(id)) {
			invoiceDao.deleteById(id);
			return new SuccessResult("Messages.invoiceDeleted");
		} else
			return new ErrorResult();
	}
	
	private Result checkIfInvoiceAlreadyExists(int rentalId) {
		if (invoiceDao.findById(rentalId) != null) {
			return new ErrorResult("Messages.invoiceAlreadyExists");
		}
		return new SuccessResult();
		
	}
	private Result checkIfInvoiceExistsByRentalId(int rentalId) {
		if (invoiceDao.findById(rentalId) == null) {
			return new ErrorResult("Messages.invoiceNotCreated");
		}
		return new SuccessResult();
	}

	
	private Result checkIfRentalIsFinished(int rentalId) {
		if (rentalService.findById(rentalId).getData() != null) {
			if (rentalService.findById(rentalId).getData().getReturnDate() == null) {
				return new ErrorResult("Messages.rentalIsNotFinished");
			} else
				return new SuccessResult();
		} else
			return new ErrorResult("Messages.notFound");
	}

	
	private Result checkIfPaymentIsMade(int rentalId) {
		if (rentalService.findById(rentalId).getData() != null) {
			return new SuccessResult();
		} else
			return new ErrorResult("Messages.paymentNotFound");
	}


}
