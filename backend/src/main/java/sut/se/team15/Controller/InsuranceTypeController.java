package sut.se.team15.Controller;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class InsuranceTypeController {

    @Autowired
    private final InsuranceTypeRepository insuranceTypeRepository;

    public InsuranceTypeController(InsuranceTypeRepository insuranceTypeRepository) {
        this.insuranceTypeRepository = insuranceTypeRepository;
    }

    @GetMapping("/InsuranceType")
    public Collection<InsuranceType> insuranceTypes() {
        return insuranceTypeRepository.findAll().stream().collect(Collectors.toList());
    }
}