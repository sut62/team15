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
public class TitleTests {

    private Validator validator;

    @Autowired
    private TitleRepository titleRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
        @Test
    void B6005924_testTitleOK() { // ใส่ข้อมูลปกติ
        Title title = new Title();
        title.setTitle("นางสาว");
        
        title = titleRepository.saveAndFlush(title);

        Optional<Title> found = titleRepository.findById(title.getId());
        assertEquals(1, found.get().getId());
    }

    // BeNull
    @Test
    void B6005924_testTitleMustNotBeNull() { // ใส่ข้อมูลที่เป็น null

        Title title = new Title();
        title.setTitle(null);
   

        Set<ConstraintViolation<Title>> result = validator.validate(title);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Title> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("Title", error.getPropertyPath().toString());
    }

    }