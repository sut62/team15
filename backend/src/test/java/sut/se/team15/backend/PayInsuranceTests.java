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

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6001025_testAmountOK() { // ใส่ข้อมูลปกติ
        PayInsurance amount = new PayInsurance();
        amount.setAmount(1000.00);
    

        amount = payInsuranceRepository.saveAndFlush(amount);

        Optional<PayInsurance> found = payInsuranceRepository.findById(amount.getIns_id());
        assertEquals(1000.00, found.get().getAmount());
    }

    @Test
    void B6001025_testAmountMustNotBeNull() { // ใส่ข้อมูลปกติ
        PayInsurance amounts = new PayInsurance();
       
        amounts.setAmount(null);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(amounts);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B6001025_testAmountMoreThan100() { // ใส่ข้อมูลปกติ
        PayInsurance amount = new PayInsurance();
       
        amount.setAmount(1000000.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(amount);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
    
    @Test
    void B6001025_testAmountLessThan100() { // ใส่ข้อมูลปกติ
        PayInsurance amount = new PayInsurance();
        
        amount.setAmount(99.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(amount);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }


}