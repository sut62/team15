package sut.se.team15.backend;

import sut.se.team15.Entity.RegisterInsurance;
import sut.se.team15.Repository.RegisterInsuranceRepository;
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

    

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6005900_testRegisterInsuranceNameOK() { // ใส่ข้อมูลปกติ
        RegisterInsurance registerInsurance= new RegisterInsurance();
        registerInsurance.setName("Name");
        registerInsurance.setSurname("Surname");
       

        registerInsurance = registerInsuranceRepository.saveAndFlush(registerInsurance);

        Optional<RegisterInsurance> found = registerInsuranceRepository.findById(registerInsurance.getRegisterInsuranceId());
        assertEquals("Name", found.get().getName());
        assertEquals("Surname", found.get().getSurname());
    }

    @Test
    void b6005900_testRegisterInsuranceNameMustNotBeNull() { // Name มีค่า null
        RegisterInsurance registerInsurance = new RegisterInsurance();
        
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
    void b6005900_testRegisterInsuranceNameMoreThan200() { // Nameมากกว่า200
        RegisterInsurance registerInsurance = new RegisterInsurance();

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
    void b6005900_testRegisterInsuranceNameLessThan2() { // Name น้อยกว่า2
         RegisterInsurance registerInsurance= new RegisterInsurance();
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
    void b6005900_testRegisterInsuranceSurnameMustNotBeNull() { // Surname มีค่า null
        RegisterInsurance registerInsurance = new RegisterInsurance();
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
    void b6005900_testRegisterInsuranceSurnameMoreThan200() { // Surnameมากกว่า200
        RegisterInsurance registerInsurance = new RegisterInsurance();

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
    void B6010256_testCreateInsuranceSurnameLessThan5() { // Surname น้อยกว่า2
         RegisterInsurance registerInsurance= new RegisterInsurance();
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
}
