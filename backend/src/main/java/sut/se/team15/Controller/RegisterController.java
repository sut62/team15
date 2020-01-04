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

import sut.se.team15.Repository.*;
import sut.se.team15.Entity.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegisterController {

    @Autowired
    private final RegisterRepository registerRepository;

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TitleRepository titleRepository;

    RegisterController(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping("/Register")
    public Collection<Register> register() {
        return registerRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Register/{User}/{Password}/{Title_ID}/{FristName}/{LastName}/{BirthDay}/{Age}/{Status_ID}/{Career}/{Disease}/{PhoneNumber}/{Address}/{Province_ID}/{PostalCode}")
    public Register newRegister(Register newRegister,
            @PathVariable String User,
            @PathVariable String Password, 
            @PathVariable long Title_ID, 
            @PathVariable String FristName,
            @PathVariable String LastName, 
            @PathVariable String BirthDay, 
            @PathVariable long Age,
            @PathVariable long Status_ID, 
            @PathVariable String Career, 
            @PathVariable String Disease,
            @PathVariable String PhoneNumber, 
            @PathVariable String Address,
            @PathVariable long Province_ID,
            @PathVariable long PostalCode) {

            
        

        Title title = titleRepository.findById(Title_ID);
        Status status = statusRepository.findById(Status_ID);   
        Province province = provinceRepository.findById(Province_ID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDays = LocalDate.parse(BirthDay, formatter);
        
        
        newRegister.setUser_id(User);
        newRegister.setPassword(Password);
        newRegister.setTitle(title);
        newRegister.setFristName(FristName);
        newRegister.setLastName(LastName);
        newRegister.setBirthDay(BirthDays);
        newRegister.setAge(Age);
        newRegister.setStatus(status);
        newRegister.setCareer(Career);
        newRegister.setDisease(Disease);
        newRegister.setPhoneNumber(PhoneNumber);
        newRegister.setAddress(Address);
        newRegister.setCareer(Career);
        newRegister.setProvince(province);
        newRegister.setPostalCode(PostalCode);


        return registerRepository.save(newRegister);
    }
}