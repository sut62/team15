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
public class PromotionController {

     @Autowired
    private final PromotionRepository promotionRepository;
    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;


    PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @GetMapping("/Promotion")
    public Collection<Promotion> promotions() {
        return promotionRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Promotion/{Name}/{Insurance_id}/{Discounts}/{StartDates}/{EndDates}")
    public Promotion newPromotion(Promotion newPromotion,
            @PathVariable String Name,
            @PathVariable long Insurance_id,
            @PathVariable Double Discounts, 
            @PathVariable String StartDates,
            @PathVariable String EndDates) {

        CreateInsurance Insuranceid = createInsuranceRepository.findById(Insurance_id);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate StartDate = LocalDate.parse(StartDates, formatter);
        
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate EndDate = LocalDate.parse(EndDates, formatters);
        
        newPromotion.setPromotion_name(Name);
        newPromotion.setCreateInsurance(Insuranceid);
        newPromotion.setPromotion_Discount(Discounts);
        newPromotion.setPromotion_StartDate(StartDate);
        newPromotion.setPromotion_EndDate(EndDate);
        
       
        return promotionRepository.save(newPromotion);
    }
}