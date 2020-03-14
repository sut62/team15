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
public class InsuranceTypeTest {

    private Validator validator;

    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010256_testInsuranceTypeComplete(){
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");

        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);

        Optional<InsuranceType> found = insuranceTypeRepository.findById(insuranceType.getInsuranceType_id());
        assertEquals("ประเภทอุบัติเหตุ", found.get().getInsuranceType_name());
    }

    @Test
    void B6010256_testInsuranceTypeMustNotBeNull(){
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name(null);

        Set<ConstraintViolation<InsuranceType>> result = validator.validate(insuranceType);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<InsuranceType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("InsuranceType_name", v.getPropertyPath().toString());
    }
}