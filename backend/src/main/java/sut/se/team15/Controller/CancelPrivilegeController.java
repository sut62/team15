package sut.se.team15.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CancelPrivilegeController {

    @Autowired
    private final CancelPrivilegeRepository cancelPrivilegeRepository;

    @Autowired
    private PrivilegeInsuranceRepository privilegeInsuranceRepository;

    CancelPrivilegeController(CancelPrivilegeRepository cancelPrivilegeRepository) {
        this.cancelPrivilegeRepository = cancelPrivilegeRepository;
    }

    @GetMapping("/CancelPrivilege")
    public Collection<CancelPrivilege> CancelPrivileges() {
        return cancelPrivilegeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/CancelPrivilege/{privilegeInsurance_id}")
    public CancelPrivilege newCancelPrivilege(CancelPrivilege newCancelPrivilege,
            @PathVariable long privilegeInsurance_id) {

        PrivilegeInsurance privilegeInsurance = privilegeInsuranceRepository.findById(privilegeInsurance_id);

        newCancelPrivilege.setPrivilegeInsurance(privilegeInsurance);
        newCancelPrivilege.setStatusInsurance("ยกเลิกแล้ว");
        newCancelPrivilege.setCancelDate(new Date());
        
        return cancelPrivilegeRepository.save(newCancelPrivilege);
    }
}