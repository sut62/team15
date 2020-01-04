package sut.se.team15.Controller;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CreateInsuranceController {

    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;
    @Autowired
    private PaymentPeriodRepository paymentPeriodRepository;

    @Autowired
    private final CreateInsuranceRepository createInsuranceRepository;

    CreateInsuranceController(final CreateInsuranceRepository createInsuranceRepository) {
        this.createInsuranceRepository = createInsuranceRepository;
    }

    @GetMapping("/CreateInsurance")
    public Collection<CreateInsurance> createInsurances() {
        return createInsuranceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/CreateInsurance/{Name}/{Details}/{InsuranceType}/{Price}/{PaymentPeriod}")
    public CreateInsurance newCreateInsurance(final CreateInsurance newCreateInsurance, @PathVariable final String Name,
            @PathVariable final String Details, @PathVariable final String InsuranceType,  @PathVariable final Double Price, @PathVariable final String PaymentPeriod) {

        

        newCreateInsurance.setCreateInsurance_name(Name);
        newCreateInsurance.setCreateInsurance_details(Details);
        newCreateInsurance.setCreateInsurance_price(Price);

        return createInsuranceRepository.save(newCreateInsurance);
    }
}