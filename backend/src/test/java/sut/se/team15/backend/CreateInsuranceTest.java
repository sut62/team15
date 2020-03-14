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
public class CreateInsuranceTest {

    private Validator validator;

    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;
    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;
    @Autowired
    private ContractDueDateRepository contractDueDateRepository;
    @Autowired
    private PaymentPeriodRepository paymentPeriodRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010256_testCreateInsuranceComplete() { // ใส่ข้อมูลปกติ
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Optional<CreateInsurance> found = createInsuranceRepository.findById(createInsurance.getCreateInsurance_id());
        assertEquals("CreateInsurance_name", found.get().getCreateInsurance_name());
      
     
    }

    @Test
    void B6010256_testCreateInsuranceNameMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name(null);
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_name", v.getPropertyPath().toString());
    }


  @Test
    void B6010256_testCreateInsuranceAccidentCoverageMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();


        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage(null);
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);


        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_accidentCoverage", v.getPropertyPath().toString());
    }

   
    @Test
    void B6010256_testCreateInsuranceDiseaseCoverageMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage(null);
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_diseaseCoverage", v.getPropertyPath().toString());
    }

    
    
     @Test
    void B6010256_testCreateInsuranceProtectionRightsMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights(null);
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_protectionRights", v.getPropertyPath().toString());
    }


     @Test
    void B6010256_testCreateInsuranceTermOfProtectionMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection(null);
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);


        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_termOfProtection", v.getPropertyPath().toString());
    }

     @Test
    void B6010256_testCreateInsurancePremiumMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(null);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CreateInsurance_insurancePremium", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceNameMoreThan100() { // มีความยาวไม่เกิน 100 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
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
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_name", v.getPropertyPath().toString());
    }


    @Test
    void B6010256_testCreateInsuranceAccidentCoverageMoreThan100() { // มีความยาวไม่เกิน 100 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
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
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_accidentCoverage", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceDiseaseCoverageMoreThan100() { // มีความยาวไม่เกิน 100 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
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
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_diseaseCoverage", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceProtectionRightsMoreThan100() { // มีความยาวไม่เกิน 100 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
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
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_protectionRights", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsuranceTermOfProtectionMoreThan100() { // มีความยาวไม่เกิน 100 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
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
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_termOfProtection", v.getPropertyPath().toString());
    }



    @Test
    void B6010256_testCreateInsuranceNameLessThan5() { // ใส่ข้อมูลน้อยกว่า 5 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();


        createInsurance.setCreateInsurance_name("aaaa");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_name", v.getPropertyPath().toString());
    }

   
    

    @Test
    void B6010256_testCreateInsuranceAccidentCoverageLessThan5() { // ใส่ข้อมูลน้อยกว่า 5 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("aaaa");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_accidentCoverage", v.getPropertyPath().toString());
    }

    
   
   

    @Test
    void B6010256_testCreateInsuranceDiseaseCoverageLessThan5() { // ใส่ข้อมูลน้อยกว่า 5 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("aaaa");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_diseaseCoverage", v.getPropertyPath().toString());
    }

     
   

   
    @Test
    void B6010256_testCreateInsuranceProtectionRightsLessThan5() { // ใส่ข้อมูลน้อยกว่า 5 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("aaaa");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_protectionRights", v.getPropertyPath().toString());
    }


    

    @Test
    void B6010256_testCreateInsuranceTermOfProtectionThan5() { // ใส่ข้อมูลน้อยกว่า 5 ตัวอักษร
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("Insurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("aaaa");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("CreateInsurance_termOfProtection", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsurancePremiumMoreThan999999() { // ใส่ข้อมูลปกติ
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(1000000.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);


        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999", v.getMessage());
        assertEquals("CreateInsurance_insurancePremium", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testCreateInsurancePremiumLessThan100() { // ใส่ค่าน้อยกว่า 100
           InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(99.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("CreateInsurance_insurancePremium", v.getPropertyPath().toString());
    }


    @Test
    void B6010256_testinsuranceTypeMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();


        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(null);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("InsuranceTypeId", v.getPropertyPath().toString());
    }

    


    @Test
    void B6010256_testContractDueDateMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(null);
        createInsurance.setPaymentPeriodId(paymentPeriod);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ContractDueDateId", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testPaymentPeriodMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        /***********************************************************/
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         /***********************************************************/
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        /***********************************************************/
         CreateInsurance createInsurance = new CreateInsurance();

        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(null);

        Set<ConstraintViolation<CreateInsurance>> result = validator.validate(createInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CreateInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("PaymentPeriodId", v.getPropertyPath().toString());
    }

}