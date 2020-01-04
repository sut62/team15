package sut.se.team15.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegisterInsuranceController {

    @Autowired
    private final RegisterInsuranceRepository registerInsuranceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;
    @Autowired
    private TitleRepository titleRepository;

    RegisterInsuranceController(RegisterInsuranceRepository registerInsuranceRepository) {
        this.registerInsuranceRepository = registerInsuranceRepository;
    }

    @GetMapping("/registerInsurance")
    public Collection<RegisterInsurance> registerInsurances() {
        return registerInsuranceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/registerInsurance/{Insurance_id}/{Person_id}/{Title_id}/{FirtName}/{Surname}")
    public RegisterInsurance newRegisterInsurance(RegisterInsurance newRegisterInsurance, 
    @PathVariable long Insurance_id, 
     @PathVariable long Person_id,
    @PathVariable long Title_id,
    @PathVariable String Firtname, 
    @PathVariable String Surname) {

        CreateInsurance createInsurance = createInsuranceRepository.findById(Insurance_id);
        Title title = titleRepository.findById(Title_id);
        Person person  = personRepository.findById(Person_id);

      
        newRegisterInsurance.setCreateInsurance(createInsurance);
        newRegisterInsurance.setPerson(person);
        newRegisterInsurance.setTitle(title);
        newRegisterInsurance.setName(Firtname);
        newRegisterInsurance.setSurname(Surname);
       

        return registerInsuranceRepository.save(newRegisterInsurance);
    }
}