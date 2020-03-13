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
public class StatusTests {

    private Validator validator;

    @Autowired
    private StatusRepository statusRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
     // OK
     @Test
     void B6005924_testStatusOK() { // ใส่ข้อมูลปกติ
 
         Status status = new Status();
         status.setStatusName("โสด");
 
         status = statusRepository.saveAndFlush(status);
 
         Optional<Status> found = statusRepository.findById(status.getId());
         assertEquals(1, found.get().getId());
     }
 
     // BeNull
     @Test
     void B6005924_testStatusMustNotBeNull() { // ใส่ข้อมูลที่เป็น null
 
         Status status = new Status();
         status.setStatusName(null);
    
         Set<ConstraintViolation<Status>> result = validator.validate(status);
 
         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());
 
         // error message ตรงชนิด และถูก field
         ConstraintViolation<Status> error = result.iterator().next();
         assertEquals("must not be null", error.getMessage());
         assertEquals("StatusName", error.getPropertyPath().toString());
     }  

    }