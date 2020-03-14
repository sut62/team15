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
public class PersonTests {

    private Validator validator;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void B6005900_testPersonOk() { //Person มีค่าเป็น null
        
        Person person = new Person();

        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Optional<Person> found = personRepository.findById(person.getPersonId());
        assertEquals("มารดา", found.get().getPersontype());
    }

    @Test
    void B6005900_testPersonMustNotBeNull() { // Personใส่ข้อมูลปกติ

        Person person = new Person();

        person.setPersontype(null);

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Persontype", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testPersonMustNotBeSizeThen201() { // Person มากกว่า 200

        Person person = new Person();

        String persontype = "";
        int i = 0;
        while (i < 201) {
            persontype += "I";
            i++;
        }

        person.setPersontype(persontype);

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("Persontype", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testPersonMustNotBeSizeThen2() {         // Person น้อยกว่า 2

        Person person = new Person();

        person.setPersontype("p");

        Set<ConstraintViolation<Person>> result = validator.validate(person);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Person> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        
        assertEquals("Persontype", v.getPropertyPath().toString());
    }
}