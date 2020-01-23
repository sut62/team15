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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PayInsuranceTests {

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
    void B6001025_testAmountOK() { // ใส่ข้อมูลปกติ
        PayInsurance payInsurance = new PayInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Insurance_staff staff = insurance_staffRepository.findById(2);
        payInsurance.setAmount(1000.00);
        
        payInsurance.setStaffID(staff);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance = payInsuranceRepository.saveAndFlush(payInsurance);

        Optional<PayInsurance> found = payInsuranceRepository.findById(payInsurance.getIns_id());
        assertEquals(1000.00, found.get().getAmount());
    }

    @Test
    void B6001025_testAmountMustNotBeNull() {
        PayInsurance payInsurance = new PayInsurance();
       
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Insurance_staff staff = insurance_staffRepository.findById(2);
       
        payInsurance.setStaffID(staff);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(null);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B6001025_testAmountMoreThan100() { 
        PayInsurance payInsurance = new PayInsurance();

        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Insurance_staff staff = insurance_staffRepository.findById(2);


        payInsurance.setStaffID(staff);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(1000000.00);


        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
    
    @Test
    void B6001025_testAmountLessThan100() { 
        PayInsurance payInsurance = new PayInsurance();

        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Insurance_staff staff = insurance_staffRepository.findById(2);
        
        payInsurance.setStaffID(staff);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(99.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

   
}