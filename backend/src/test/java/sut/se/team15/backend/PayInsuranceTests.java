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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PayInsuranceTests {
    
    private Validator validator;

    @Autowired
    private PayInsuranceRepository payInsuranceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;
    @Autowired
    private Insurance_staffRepository insurance_staffRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6001025_testAmountOK() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(staff_name);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(1000.00);
        payInsurance = payInsuranceRepository.saveAndFlush(payInsurance);

        Optional<PayInsurance> found = payInsuranceRepository.findById(payInsurance.getIns_id());
        assertEquals(1000.00, found.get().getAmount());
    }

    @Test
    void B6001025_testAmountMustNotBeNull() {
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(staff_name);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(null);
        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

  
    @Test
    void B6001025_testAmountMoreThan100() { 
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(staff_name);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(1000000.00);


        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
    
    @Test
    void B6001025_testAmountLessThan100() { 
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(staff_name);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(99.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }



   
    @Test
    void B6001025_testInsurance_stafftMustNotBeNull() { // ใส่ข้อมูลปกติ

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(null);
        payInsurance.setRegID(user);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(1000.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("staffID", v.getPropertyPath().toString());
    }

   
    @Test
    void B6001025_testCreateInsuranceMustNotBeNull() {

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(staff_name);
        payInsurance.setRegID(user);
        payInsurance.setInsID(null);
        payInsurance.setAmount(1000.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("insID", v.getPropertyPath().toString());
    }


    @Test
    void B6001025_testUserMustNotBeNull() {

        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);
        /***************************************************************/
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
        /*****************************************************************/
        Insurance_staff staff_name= new Insurance_staff();
        
        staff_name.setStaff_name("สมรัก");
        staff_name = insurance_staffRepository.saveAndFlush(staff_name);
        /****************************************************************/
        PayInsurance payInsurance = new PayInsurance();
       
       
        payInsurance.setStaffID(staff_name);
        payInsurance.setRegID(null);
        payInsurance.setInsID(createInsurance);
        payInsurance.setAmount(1000.00);

        Set<ConstraintViolation<PayInsurance>> result = validator.validate(payInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PayInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("regID", v.getPropertyPath().toString());
    }
}