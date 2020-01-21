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
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TitleRepository titleRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/User")
    public Collection<User> register() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/UserLogin/{userid}/{password}")
    public User UserLogin(@PathVariable String userid, @PathVariable String password) {
        return userRepository.findAll().stream()
                .filter(s -> s.getUserid().equals(userid) && s.getPassword().equals(password))
                .collect(Collectors.toList()).get(0);
    }

    @PostMapping("/User/{UserId}/{Password}/{Title_ID}/{FirstName}/{LastName}/{BirthDays}/{Age}/{Status_ID}/{Career}/{Disease}/{PhoneNumber}/{Address}/{Province_ID}/{PostalCode}")
    public User newUser(User newUser,
            @PathVariable String UserId,
            @PathVariable String Password, 
            @PathVariable long Title_ID, 
            @PathVariable String FirstName,
            @PathVariable String LastName, 
            @PathVariable String BirthDays, 
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
        LocalDate BirthDay = LocalDate.parse(BirthDays, formatter);
        
        
        newUser.setUserid(UserId);
        newUser.setPassword(Password);
        newUser.setTitle(title);
        newUser.setFirstName(FirstName);
        newUser.setLastName(LastName);
        newUser.setBirthDay(BirthDay);
        newUser.setAge(Age);
        newUser.setStatus(status);
        newUser.setCareer(Career);
        newUser.setDisease(Disease);
        newUser.setPhoneNumber(PhoneNumber);
        newUser.setAddress(Address);
        newUser.setProvince(province);
        newUser.setPostalCode(PostalCode);


        return userRepository.save(newUser);
    }
}