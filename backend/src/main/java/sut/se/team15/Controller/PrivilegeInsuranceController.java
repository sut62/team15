package sut.se.team15.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PrivilegeInsuranceController {

    @Autowired
    private final PrivilegeInsuranceRepository privilegeInsuranceRepository;

    @Autowired
    private RegisterInsuranceRepository registerInsuranceRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private PurposeRequestRepository purposeRequestRepository;

    PrivilegeInsuranceController(PrivilegeInsuranceRepository privilegeInsuranceRepository) {
        this.privilegeInsuranceRepository = privilegeInsuranceRepository;
    }

    @GetMapping("/PrivilegeInsurance")
    public Collection<PrivilegeInsurance> PrivilegeInsurances() {
        return privilegeInsuranceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/PrivilegeInsurance/{registerInsurance_id}/{hospital_id}/{privilegeDates}/{purposeRequest_id}/{signedPlace}")
    public PrivilegeInsurance newPrivilegeInsurance(PrivilegeInsurance newPrivilegeInsurance,
            @PathVariable long registerInsurance_id, @PathVariable long hospital_id,
            @PathVariable String privilegeDates, @PathVariable long purposeRequest_id,
            @PathVariable String signedPlace) {

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(registerInsurance_id);
        Hospital hospital = hospitalRepository.findById(hospital_id);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(purposeRequest_id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate privilegeDate = LocalDate.parse(privilegeDates, formatter);

        newPrivilegeInsurance.setRegisterInsurance(registerInsurance);
        newPrivilegeInsurance.setHospital(hospital);
        newPrivilegeInsurance.setPrivilegeDate(privilegeDate);
        newPrivilegeInsurance.setPurposeRequest(purposeRequest);
        newPrivilegeInsurance.setPrivileSignedPlace(signedPlace);

        return privilegeInsuranceRepository.save(newPrivilegeInsurance);
    }
}