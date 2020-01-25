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
public class RenewInsuranceController{

    @Autowired
    private final RenewInsuranceRepository renewinsuranceRepository;
   
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterInsuranceRepository registerInsuranceRepository;
    

    RenewInsuranceController(RenewInsuranceRepository renewInsuranceRepository) {
        this.renewinsuranceRepository = renewInsuranceRepository;
    }

    @GetMapping("/renewinsurances")
    public Collection<RenewInsurance> RenewInsurances(){
        return renewinsuranceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/renewinsurances/{use_id}/{reg_id}")
    public RenewInsurance newRenewInsurance(RenewInsurance newrenewinsurance,

    @PathVariable long use_id,
    @PathVariable long reg_id

  
    ){
        User member = userRepository.findById(use_id);
        RegisterInsurance reg = registerInsuranceRepository.findById(reg_id);
        
        newrenewinsurance.setUseID(member);
        newrenewinsurance.setRegID(reg);

        return renewinsuranceRepository.save(newrenewinsurance);
    }
}