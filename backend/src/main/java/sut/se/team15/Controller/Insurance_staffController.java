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
public class Insurance_staffController {

    @Autowired
    private final Insurance_staffRepository insurance_staffRepository;

    public Insurance_staffController(Insurance_staffRepository insurance_staffRepository) {
        this.insurance_staffRepository = insurance_staffRepository;
    }

    @GetMapping("/staff")
    public Collection<Insurance_staff> insurance_staff() {
        return insurance_staffRepository.findAll().stream().collect(Collectors.toList());
    }
}