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

    @PostMapping("/registerInsurance/{id}/{PersonId}/{id}/{Name}/{Surname}")
    public RegisterInsurance newRegisterInsurance(RegisterInsurance newRegisterInsurance, @PathVariable long id, @PathVariable long id,
            @PathVariable long PersonId, @PathVariable String Name, @PathVariable String Surname) {

        CreateInsurance createInsurance = createInsuranceRepository.findById(id);
        Title title = titleRepository.findById(id);
        Person person  = personRepository.findById(PersonId);

      
        newRegisterInsurance.setCreateInsurance(createInsurance);
        newRegisterInsurance.setPerson(person);
        newRegisterInsurance.setTitle(title);
        newRegisterInsurance.setName(Name);
        newRegisterInsurance.setSurname(Surname);
       

        return registerInsuranceRepository.save(newRegisterInsurance);
    }
}