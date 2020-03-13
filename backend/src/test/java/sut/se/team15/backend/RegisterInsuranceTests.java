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
public class RegisterInsuranceTests {

    private Validator validator;

    @Autowired
    private RegisterInsuranceRepository registerInsuranceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TitleRepository titleRepository;
    

    

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6005900_testRegisterInsuranceOK() { // RegisterInsurance ใส่ข้อมูลปกติ
        RegisterInsurance registerInsurance= new RegisterInsurance();
    
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("Name");
        registerInsurance.setSurname("Surname");
     
        registerInsurance = registerInsuranceRepository.saveAndFlush(registerInsurance);

        Optional<RegisterInsurance> found = registerInsuranceRepository.findById(registerInsurance.getRegisterInsuranceId());
        assertEquals("Name", found.get().getName());
        assertEquals("Surname", found.get().getSurname());

    }

    @Test
    void B6005900_testRegisterInsuranceNameMustNotBeNull() { // Name มีค่า null
        RegisterInsurance registerInsurance= new RegisterInsurance();
        
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName(null);
        registerInsurance.setSurname("Surname");

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
    @Test
    void B6005900_testRegisterInsuranceNameNotDigital() { // Name ไม่มีตัวเลข
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("123name");
        registerInsurance.setSurname("Surname");

          Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must match \"\\D+\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

     @Test
    void B6005900_testRegisterInsuranceNameMoreThan200() { // Nameมากกว่า200
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);


        String Name = "" ;
        int i = 0;
        while(i<201){
            Name += "a";
            i++;
        }

        registerInsurance.setName(Name);
        registerInsurance.setSurname("Surname");

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testRegisterInsuranceNameLessThan2() { // Name น้อยกว่า2
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("n");
        registerInsurance.setSurname("Surname");

          Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testRegisterInsuranceSurnameMustNotBeNull() { // Surname มีค่า null
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(2);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("Name");
        registerInsurance.setSurname(null);

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("surname", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testRegisterInsuranceSurnameNotDigital() { // SurName ไม่มีตัวเลข
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("์Name");
        registerInsurance.setSurname("Surname123");

          Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must match \"\\D+\"", v.getMessage());
        assertEquals("surname", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testRegisterInsuranceSurnameMoreThan200() { // Surnameมากกว่า200
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);


        String Surname = "" ;
        int i = 0;
        while(i<201){
            Surname += "a";
            i++;
        }

        registerInsurance.setName("Name");
        registerInsurance.setSurname(Surname);

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("surname", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testRegisterInsuranceSurnameLessThan5() { // Surname น้อยกว่า5
        RegisterInsurance registerInsurance= new RegisterInsurance();
        User user = userRepository.findById(1);
        CreateInsurance createInsurance = createInsuranceRepository.findById(1);
        Person person = personRepository.findById(1);
        Title title = titleRepository.findById(1);

        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("Name");
        registerInsurance.setSurname("Sur");

          Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("surname", v.getPropertyPath().toString());
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

    @Test
    void B6005900_testTitleOk() { //Title ปกติ
        
        Title title = new Title();

        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        Optional<Title> found = titleRepository.findById(title.getId());
        assertEquals("นางสาว", found.get().getTitle());
    }

    @Test
    void B6005900_testTitleMustNotBeNull() { //Title notnull

        Title title = new Title();

        title.setTitle(null);

        Set<ConstraintViolation<Title>> result = validator.validate(title);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Title> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Title", v.getPropertyPath().toString());
    }
}
