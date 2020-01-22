package sut.se.team15.backend;

import sut.se.team15.Repository.CreateInsuranceRepository;
import sut.se.team15.Entity.CreateInsurance;
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
public class CreateInsuranceTest {

    private Validator validator;

    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010256_testCreateInsuranceNameOK() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Optional<CreateInsurance> found = createInsuranceRepository.findById(createInsurance.getCreateInsurance_id());
        assertEquals("CreateInsurance_name", found.get().getCreateInsurance_name());
    }

    @Test
    void B6010256_testCreateInsuranceNameMustNotBeNull() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name(null);
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_name", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceNameMoreThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();

        String Insurance_name = "" ;
        int i = 0;
        while(i<101){
            Insurance_name += "a";
            i++;
        }

        createInsurance.setCreateInsurance_name(Insurance_name);
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_name", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceNameLessThan5() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("aaaa");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_name", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsurancePremiumMustNotBeNull() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(null);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_insurancePremium", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsurancePremiumMoreThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(1000000.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999", v.getMessage());
        assertEquals("CreateInsurance_insurancePremium", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsurancePremiumLessThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(99.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("CreateInsurance_insurancePremium", v.getPropertyPath().toString());
    }


    @Test
    void B6010256_testCreateInsuranceAccidentCoverageMustNotBeNull() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage(null);
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_accidentCoverage", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceAccidentCoverageMoreThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();

        String CreateInsurance_accidentCoverage = "" ;
        int i = 0;
        while(i<101){
            CreateInsurance_accidentCoverage += "a";
            i++;
        }

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage(CreateInsurance_accidentCoverage);
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_accidentCoverage", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceAccidentCoverageLessThan5() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("aaaa");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_accidentCoverage", v.getPropertyPath().toString());
    }

    
    @Test
    void B6010256_testCreateInsuranceDiseaseCoverageMustNotBeNull() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage(null);
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_diseaseCoverage", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceDiseaseCoverageMoreThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();

        String CreateInsurance_diseaseCoverage = "" ;
        int i = 0;
        while(i<101){
            CreateInsurance_diseaseCoverage += "a";
            i++;
        }

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage(CreateInsurance_diseaseCoverage);
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_diseaseCoverage", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceDiseaseCoverageLessThan5() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("aaaa");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_diseaseCoverage", v.getPropertyPath().toString());
    }

     
    @Test
    void B6010256_testCreateInsuranceProtectionRightsMustNotBeNull() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights(null);
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_protectionRights", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceProtectionRightsMoreThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();

        String CreateInsurance_protectionRights = "" ;
        int i = 0;
        while(i<101){
            CreateInsurance_protectionRights += "a";
            i++;
        }

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights(CreateInsurance_protectionRights);
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_protectionRights", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceProtectionRightsLessThan5() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("aaaa");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_protectionRights", v.getPropertyPath().toString());
    }


    
     
    @Test
    void B6010256_testCreateInsuranceTermOfProtectionMustNotBeNull() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection(null);
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_termOfProtection", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceTermOfProtectionMoreThan100() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();

        String CreateInsurance_termOfProtection = "" ;
        int i = 0;
        while(i<101){
            CreateInsurance_termOfProtection += "a";
            i++;
        }

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection(CreateInsurance_termOfProtection);
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_termOfProtection", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceTermOfProtectionThan5() { // ใส่ข้อมูลปกติ
        CreateInsurance createInsurance = new CreateInsurance();
        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("aaaa");
        createInsurance.setCreateInsurance_insurancePremium(500.00);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_termOfProtection", v.getPropertyPath().toString());
    }





}