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
public class HospitalController {

    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private final HospitalRepository hospitalRepository;

    HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/Hospital")
    public Collection<Hospital> hospitals() {
        return hospitalRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/AddHospital/{Type}/{Region}/{Province}/{Name}/{Location}/{Telephone}")
    public Hospital newHospital(Hospital newHospital, @PathVariable long Type,
            @PathVariable long Region, @PathVariable long Province,  @PathVariable String Name, @PathVariable String Location, @PathVariable String Telephone) {

        HospitalType TypeID = hospitalTypeRepository.findById(Type);
        Region RegionID = regionRepository.findById(Region);
        Province ProvinceID = provinceRepository.findById(Province);

        newHospital.setHospitalTypeId(TypeID);
        newHospital.setRegionId(RegionID);
        newHospital.setProvinceId(ProvinceID);
        newHospital.setHospitalName(Name);
        newHospital.setLocationDetails(Location);
        newHospital.setTelephoneNumber(Telephone);

        return hospitalRepository.save(newHospital);
    }
}