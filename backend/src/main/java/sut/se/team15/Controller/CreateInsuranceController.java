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
    private ContractDueDateRepository contractDueDateRepository;

    @Autowired
    private final CreateInsuranceRepository createInsuranceRepository;

    CreateInsuranceController(CreateInsuranceRepository createInsuranceRepository) {
        this.createInsuranceRepository = createInsuranceRepository;
    }

    @GetMapping("/CreateInsurance")
    public Collection<CreateInsurance> createInsurances() {
        return createInsuranceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/CreateInsurance/{Name}/{AccidentCoverage}/{DiseaseCoverage}/{ProtectionRights}/{TermOfProtection}/{InsurancePremium}/{InsuranceType_id}/{PaymentPeriod_id}/{ContractDueDate_id}")
    public CreateInsurance newCreateInsurance(CreateInsurance newCreateInsurance, @PathVariable String Name,
            @PathVariable String AccidentCoverage, @PathVariable String DiseaseCoverage,
            @PathVariable String ProtectionRights, @PathVariable String TermOfProtection,
            @PathVariable Double InsurancePremium, @PathVariable long InsuranceType_id, @PathVariable long PaymentPeriod_id,
            @PathVariable long ContractDueDate_id) {

        InsuranceType insuranceTypeID = insuranceTypeRepository.findById(InsuranceType_id);
        PaymentPeriod paymentPeriodID = paymentPeriodRepository.findById(PaymentPeriod_id);
        ContractDueDate contractDueDateID = contractDueDateRepository.findById(ContractDueDate_id);

        newCreateInsurance.setInsuranceTypeId(insuranceTypeID);
        newCreateInsurance.setPaymentPeriodId(paymentPeriodID);
        newCreateInsurance.setContractDueDateId(contractDueDateID);

        newCreateInsurance.setCreateInsurance_name(Name);
        newCreateInsurance.setCreateInsurance_accidentCoverage(AccidentCoverage);
        newCreateInsurance.setCreateInsurance_diseaseCoverage(DiseaseCoverage);
        newCreateInsurance.setCreateInsurance_protectionRights(ProtectionRights);
        newCreateInsurance.setCreateInsurance_termOfProtection(TermOfProtection);
        newCreateInsurance.setCreateInsurance_insurancePremium(InsurancePremium);

        return createInsuranceRepository.save(newCreateInsurance);
    }
}