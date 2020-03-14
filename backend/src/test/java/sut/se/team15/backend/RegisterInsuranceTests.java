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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("123Name");
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("N");
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("Name");
        registerInsurance.setSurname("surname123");

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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
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
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        user.setPostalCode((long)30000);
        user = userRepository.saveAndFlush(user);

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
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
    void B6005900_testUserMustNotBeNull() { //คอมโบ User notnull

      User user = new User();
      user.setUserid("1234567890123");
      user.setPassword("12345678");
      user.setFirstName("FirstName");
      user.setLastName("LastName");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
      user.setBirthDay(BirthDay);
      user.setAge((long)20);
      user.setCareer("Career");
      user.setDisease("Disease");
      user.setPhoneNumber("0123456789");
      user.setAddress("Address");
      user.setPostalCode((long)30000);
      user = userRepository.saveAndFlush(user);

      CreateInsurance createInsurance = new CreateInsurance();
      createInsurance.setCreateInsurance_name("CreateInsurance_name");
      createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
      createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
      createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
      createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
      createInsurance.setCreateInsurance_insurancePremium(500.00);
      createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

      Person person = new Person();
      person.setPersontype("มารดา");
      person = personRepository.saveAndFlush(person);

      Title title = new Title();
      title.setTitle("นางสาว");
      title = titleRepository.saveAndFlush(title);

     RegisterInsurance registerInsurance = new RegisterInsurance();
     registerInsurance.setUser(null);
     registerInsurance.setCreateInsurance(createInsurance);
     registerInsurance.setPerson(person);
     registerInsurance.setTitle(title);
     registerInsurance.setName("name");
     registerInsurance.setSurname("surname");

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("user", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testCreateInsuranceMustNotBeNull() { //คอมโบ CreateInsurance notnull

      User user = new User();
      user.setUserid("1234567890123");
      user.setPassword("12345678");
      user.setFirstName("FirstName");
      user.setLastName("LastName");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
      user.setBirthDay(BirthDay);
      user.setAge((long)20);
      user.setCareer("Career");
      user.setDisease("Disease");
      user.setPhoneNumber("0123456789");
      user.setAddress("Address");
      user.setPostalCode((long)30000);
      user = userRepository.saveAndFlush(user);

      CreateInsurance createInsurance = new CreateInsurance();
      createInsurance.setCreateInsurance_name("CreateInsurance_name");
      createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
      createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
      createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
      createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
      createInsurance.setCreateInsurance_insurancePremium(500.00);
      createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

      Person person = new Person();
      person.setPersontype("มารดา");
      person = personRepository.saveAndFlush(person);

      Title title = new Title();
      title.setTitle("นางสาว");
      title = titleRepository.saveAndFlush(title);

     RegisterInsurance registerInsurance = new RegisterInsurance();
     registerInsurance.setUser(user);
     registerInsurance.setCreateInsurance(null);
     registerInsurance.setPerson(person);
     registerInsurance.setTitle(title);
     registerInsurance.setName("name");
     registerInsurance.setSurname("surname");

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testPersonMustNotBeNull() { //คอมโบ Person notnull

      User user = new User();
      user.setUserid("1234567890123");
      user.setPassword("12345678");
      user.setFirstName("FirstName");
      user.setLastName("LastName");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
      user.setBirthDay(BirthDay);
      user.setAge((long)20);
      user.setCareer("Career");
      user.setDisease("Disease");
      user.setPhoneNumber("0123456789");
      user.setAddress("Address");
      user.setPostalCode((long)30000);
      user = userRepository.saveAndFlush(user);

      CreateInsurance createInsurance = new CreateInsurance();
      createInsurance.setCreateInsurance_name("CreateInsurance_name");
      createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
      createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
      createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
      createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
      createInsurance.setCreateInsurance_insurancePremium(500.00);
      createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

      Person person = new Person();
      person.setPersontype("มารดา");
      person = personRepository.saveAndFlush(person);

      Title title = new Title();
      title.setTitle("นางสาว");
      title = titleRepository.saveAndFlush(title);

     RegisterInsurance registerInsurance = new RegisterInsurance();
     registerInsurance.setUser(user);
     registerInsurance.setCreateInsurance(createInsurance);
     registerInsurance.setPerson(null);
     registerInsurance.setTitle(title);
     registerInsurance.setName("name");
     registerInsurance.setSurname("surname");

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("person", v.getPropertyPath().toString());
    }

    @Test
    void B6005900_testTitleMustNotBeNull() { //คอมโบ Title notnull

      User user = new User();
      user.setUserid("1234567890123");
      user.setPassword("12345678");
      user.setFirstName("FirstName");
      user.setLastName("LastName");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
      user.setBirthDay(BirthDay);
      user.setAge((long)20);
      user.setCareer("Career");
      user.setDisease("Disease");
      user.setPhoneNumber("0123456789");
      user.setAddress("Address");
      user.setPostalCode((long)30000);
      user = userRepository.saveAndFlush(user);

      CreateInsurance createInsurance = new CreateInsurance();
      createInsurance.setCreateInsurance_name("CreateInsurance_name");
      createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
      createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
      createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
      createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
      createInsurance.setCreateInsurance_insurancePremium(500.00);
      createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

      Person person = new Person();
      person.setPersontype("มารดา");
      person = personRepository.saveAndFlush(person);

      Title title = new Title();
      title.setTitle("นางสาว");
      title = titleRepository.saveAndFlush(title);

     RegisterInsurance registerInsurance = new RegisterInsurance();
     registerInsurance.setUser(user);
     registerInsurance.setCreateInsurance(createInsurance);
     registerInsurance.setPerson(person);
     registerInsurance.setTitle(null);
     registerInsurance.setName("name");
     registerInsurance.setSurname("surname");

        Set<ConstraintViolation<RegisterInsurance>> result = validator.validate(registerInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("title", v.getPropertyPath().toString());
    }
}
