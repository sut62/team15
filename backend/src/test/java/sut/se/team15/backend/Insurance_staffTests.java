package sut.se.team15.backend;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class Insurance_staffTests {
    
    private Validator validator;

    @Autowired
    private PayInsuranceRepository payInsuranceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;
    @Autowired
    private Insurance_staffRepository insurance_staffRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6001025_testInsurance_staffOK() { // ใส่ข้อมูลปกติ

        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);

        Optional<Insurance_staff> found = insurance_staffRepository.findById(staff_name.getStaff_id());
        assertEquals("สมรัก", found.get().getStaff_name());
    }
   
    @Test
    void B6001025_testInsurance_stafftMustNotBeNull() { // ใส่ข้อมูลปกติ

        Insurance_staff staff = new Insurance_staff();

        staff.setStaff_name(null);

        Set<ConstraintViolation<Insurance_staff>> result = validator.validate(staff);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Insurance_staff> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("staff_name", v.getPropertyPath().toString());
    }

}