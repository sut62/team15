package sut.se.team15.backend;

import sut.se.team15.Repository.*;
import sut.se.team15.Entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class PaymentPeriodTest {

    private Validator validator;

    @Autowired
    private PaymentPeriodRepository paymentPeriodRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010256_testPaymentPeriodComplete(){
        PaymentPeriod paymentPeriod = new PaymentPeriod();
        paymentPeriod.setPaymentPeriod_name("3 เดือน");

        paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);

        Optional<PaymentPeriod> found = paymentPeriodRepository.findById(paymentPeriod.getPaymentPeriod_id());
        assertEquals("3 เดือน", found.get().getPaymentPeriod_name());
    }

    @Test
    void B6010256_testPaymentPeriodMustNotBeNull(){
        PaymentPeriod paymentPeriod = new PaymentPeriod();
        paymentPeriod.setPaymentPeriod_name(null);

        Set<ConstraintViolation<PaymentPeriod>> result = validator.validate(paymentPeriod);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PaymentPeriod> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("PaymentPeriod_name", v.getPropertyPath().toString());
    }
}