package sut.se.team15.backend;

import sut.se.team15.Repository.*;
import sut.se.team15.Entity.*;
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
public class PromotionTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate StartDate = LocalDate.parse("2025-01-10", formatter);
    LocalDate EndDate = LocalDate.parse("2025-02-20", formatter);

    private Validator validator;

    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;
    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;
    @Autowired
    private ContractDueDateRepository contractDueDateRepository;
    @Autowired
    private PaymentPeriodRepository paymentPeriodRepository;
    @Autowired
    private PromotionRepository promotionRepository;
 

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010256_testPromotionComplete() { // ใส่ข้อมูลปกติ
       
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

        Promotion promotion = new Promotion();
        promotion.setPromotion_name("Names");
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(500.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);

       
        promotion = promotionRepository.saveAndFlush(promotion);

        Optional<Promotion> found = promotionRepository.findById(promotion.getPromotion_id());
        assertEquals("Names", found.get().getPromotion_name());
      
     
    }

    @Test
    void B6010256_testPromotionNameMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
        
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
        
        Promotion promotion = new Promotion();

        promotion.setPromotion_name(null);
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(500.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);

        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Promotion_name", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testPromotionNameMoreThan100() { // มีความยาวไม่เกิน 100 ตัวอักษร
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

      Promotion promotion = new Promotion();

        String Promotion_name = "" ;
        int i = 0;
        while(i<101){
            Promotion_name += "a";
            i++;
        }

        promotion.setPromotion_name(Promotion_name);
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(500.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);

        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());


        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("Promotion_name", v.getPropertyPath().toString());
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
         Promotion promotion = new Promotion();

        promotion.setPromotion_name("aaaa");
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(500.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);

        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());


        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("size must be between 5 and 100", v.getMessage());
        assertEquals("Promotion_name", v.getPropertyPath().toString());
    }


    @Test
    void B6010256_testPromotionDiscountMoreThan999999() { // ใส่ข้อมูลปกติ
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
       
        Promotion promotion = new Promotion();
       
        promotion.setPromotion_name("Names");
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(1000000.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);


        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());


        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("must be less than or equal to 999999", v.getMessage());
        assertEquals("promotion_Discount", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testPromotionDiscountLessThan100() { // ใส่ค่าน้อยกว่า 100
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
       
        
        Promotion promotion = new Promotion();

        promotion.setPromotion_name("Names");
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(99.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);


        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());


        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("must be greater than or equal to 100", v.getMessage());
        assertEquals("promotion_Discount", v.getPropertyPath().toString());
    }

    @Test
    void B6010256_testEndDateWrongFormatPast() { // ใส่ข้อมูลปกติ
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

        Promotion promotion = new Promotion();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate EndDate = LocalDate.parse("2019-01-01", formatter);
      
        promotion.setPromotion_name("Names");
        promotion.setCreateInsurance(createInsurance);
        promotion.setPromotion_Discount(500.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);

        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        assertEquals(1, result.size());


        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("Promotion_EndDate", v.getPropertyPath().toString());
        
    }

   

    
    @Test
    void B6010256_testCreateInsuranceMustNotBeNull() { // ต้องไม่ใส่ค่าว่าง
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
    
    
        Promotion promotion = new Promotion();

        promotion.setPromotion_name("Names");
        promotion.setCreateInsurance(null);
        promotion.setPromotion_Discount(500.00);
        promotion.setPromotion_StartDate(StartDate);
        promotion.setPromotion_EndDate(EndDate);

        Set<ConstraintViolation<Promotion>> result = validator.validate(promotion);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Promotion> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("createInsurance", v.getPropertyPath().toString());
    }
}