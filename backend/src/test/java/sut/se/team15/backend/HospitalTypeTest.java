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
public class HospitalTypeTest {

    private Validator validator;

    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // OK
    @Test
    void B5917440_testHospitalTypeOK() { // ใส่ข้อมูลปกติ
        HospitalType hospitalType = new HospitalType();
        hospitalType.setTypeName("โรงพยาบาลเอกชน");
        
        hospitalType = hospitalTypeRepository.saveAndFlush(hospitalType);

        Optional<HospitalType> found = hospitalTypeRepository.findById(hospitalType.getId());
        assertEquals(1, found.get().getId());
    }

    // BeNull
    @Test
    void B5917440_testTypeNameBeNull() { // ใส่ข้อมูลทที่เป็นnull
        HospitalType hospitalType = new HospitalType();
        hospitalType.setTypeName(null);

        Set<ConstraintViolation<HospitalType>> result = validator.validate(hospitalType);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<HospitalType> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("typeName", error.getPropertyPath().toString());
    }

    // NotBeSize
    @Test
    void B5917440_testTypeNameNotBeMinSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด
        HospitalType hospitalType = new HospitalType();
        hospitalType.setTypeName("โรงพยาบาล");

        Set<ConstraintViolation<HospitalType>> result = validator.validate(hospitalType);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<HospitalType> error = result.iterator().next();
        assertEquals("size must be between 10 and 20", error.getMessage());
        assertEquals("typeName", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testTypeNameNotBeMaxSize() { // ใส่ข้อมูลที่ขนาดมากกว่าที่ size กำหนด
        HospitalType hospitalType = new HospitalType();
        hospitalType.setTypeName("โรงพยาบาลเอกชนโรงพยาบาลเอกชน");

        Set<ConstraintViolation<HospitalType>> result = validator.validate(hospitalType);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<HospitalType> error = result.iterator().next();
        assertEquals("size must be between 10 and 20", error.getMessage());
        assertEquals("typeName", error.getPropertyPath().toString());
    }
}