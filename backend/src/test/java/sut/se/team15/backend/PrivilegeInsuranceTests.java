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
public class PrivilegeInsuranceTests {

    private Validator validator;

    @Autowired
    private PrivilegeInsuranceRepository privilegeInsuranceRepository;
    @Autowired
    private PurposeRequestRepository purposeRequestRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private RegisterInsuranceRepository registerInsuranceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5917099_testPrivilegeDateOKPresent() { // ใส่ข้อมูลปกติ

        PrivilegeInsurance privilegeinsurance = new PrivilegeInsurance();

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(1);
        Hospital hospital = hospitalRepository.findById(2);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(3);
        LocalDate privilegeDate = LocalDate.now();

        privilegeinsurance.setRegisterInsurance(registerInsurance);
        privilegeinsurance.setHospital(hospital);
        privilegeinsurance.setPrivilegeDate(privilegeDate);
        privilegeinsurance.setPurposeRequest(purposeRequest);
        privilegeinsurance.setPrivileSignedPlace("suranaree university of technology");
        privilegeinsurance = privilegeInsuranceRepository.saveAndFlush(privilegeinsurance);

        Optional<PrivilegeInsurance> found = privilegeInsuranceRepository.findById(privilegeinsurance.getId());
        assertEquals(privilegeDate, found.get().getPrivilegeDate());
    }

    @Test
    void B5917099_testPrivilegeDateMustNotBeNull() {

        PrivilegeInsurance privilegeinsurance = new PrivilegeInsurance();

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(1);
        Hospital hospital = hospitalRepository.findById(2);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(3);

        privilegeinsurance.setRegisterInsurance(registerInsurance);
        privilegeinsurance.setHospital(hospital);
        privilegeinsurance.setPrivilegeDate(null);
        privilegeinsurance.setPurposeRequest(purposeRequest);
        privilegeinsurance.setPrivileSignedPlace("suranaree university of technology");

        Set<ConstraintViolation<PrivilegeInsurance>> result = validator.validate(privilegeinsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PrivilegeInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("privilegeDate", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_testPrivilegeDateWrongFormatPast() {

        PrivilegeInsurance privilegeinsurance = new PrivilegeInsurance();

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(1);
        Hospital hospital = hospitalRepository.findById(2);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate privilegeDate = LocalDate.parse("2020-01-01", formatter);

        privilegeinsurance.setRegisterInsurance(registerInsurance);
        privilegeinsurance.setHospital(hospital);
        privilegeinsurance.setPrivilegeDate(privilegeDate);
        privilegeinsurance.setPurposeRequest(purposeRequest);
        privilegeinsurance.setPrivileSignedPlace("suranaree university of technology");

        Set<ConstraintViolation<PrivilegeInsurance>> result = validator.validate(privilegeinsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PrivilegeInsurance> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("privilegeDate", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_testPrivileSignedPlaceMustNotBeNull() {

        PrivilegeInsurance privilegeinsurance = new PrivilegeInsurance();

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(1);
        Hospital hospital = hospitalRepository.findById(2);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(3);
        LocalDate privilegeDate = LocalDate.now();

        privilegeinsurance.setRegisterInsurance(registerInsurance);
        privilegeinsurance.setHospital(hospital);
        privilegeinsurance.setPrivilegeDate(privilegeDate);
        privilegeinsurance.setPurposeRequest(purposeRequest);
        privilegeinsurance.setPrivileSignedPlace(null);

        Set<ConstraintViolation<PrivilegeInsurance>> result = validator.validate(privilegeinsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PrivilegeInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("privileSignedPlace", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_testPrivileSignedPlaceMustNotBeSizeThen4() {

        PrivilegeInsurance privilegeinsurance = new PrivilegeInsurance();

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(1);
        Hospital hospital = hospitalRepository.findById(2);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(3);
        LocalDate privilegeDate = LocalDate.now();

        privilegeinsurance.setRegisterInsurance(registerInsurance);
        privilegeinsurance.setHospital(hospital);
        privilegeinsurance.setPrivilegeDate(privilegeDate);
        privilegeinsurance.setPurposeRequest(purposeRequest);
        privilegeinsurance.setPrivileSignedPlace("SUTT");

        Set<ConstraintViolation<PrivilegeInsurance>> result = validator.validate(privilegeinsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PrivilegeInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("privileSignedPlace", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_testPrivileSignedPlaceMustNotBeSizeThen201() {

        PrivilegeInsurance privilegeinsurance = new PrivilegeInsurance();

        String privileSignedPlace = "";
        int i = 0;
        while (i < 201) {
            privileSignedPlace += "s";
            i++;
        }

        RegisterInsurance registerInsurance = registerInsuranceRepository.findById(1);
        Hospital hospital = hospitalRepository.findById(2);
        PurposeRequest purposeRequest = purposeRequestRepository.findById(3);
        LocalDate privilegeDate = LocalDate.now();

        privilegeinsurance.setRegisterInsurance(registerInsurance);
        privilegeinsurance.setHospital(hospital);
        privilegeinsurance.setPrivilegeDate(privilegeDate);
        privilegeinsurance.setPurposeRequest(purposeRequest);
        privilegeinsurance.setPrivileSignedPlace(privileSignedPlace);

        Set<ConstraintViolation<PrivilegeInsurance>> result = validator.validate(privilegeinsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PrivilegeInsurance> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("privileSignedPlace", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_testPurposeRequestOK() { // ใส่ข้อมูลปกติ

        PurposeRequest purposeRequest = new PurposeRequest();
        
        purposeRequest.setPurposeRequest("ค่ารักษาอุบัติเหตุ");
        purposeRequest = purposeRequestRepository.saveAndFlush(purposeRequest);

        Optional<PurposeRequest> found = purposeRequestRepository.findById(purposeRequest.getId());
        assertEquals("ค่ารักษาอุบัติเหตุ", found.get().getPurposeRequest());
    }

    @Test
    void B5917099_testPurposeRequestMustNotBeNull() { // ใส่ข้อมูลปกติ

        PurposeRequest purposeRequests = new PurposeRequest();

        purposeRequests.setPurposeRequest(null);

        Set<ConstraintViolation<PurposeRequest>> result = validator.validate(purposeRequests);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PurposeRequest> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("purposeRequest", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_estPurposeRequestMustNotBeSizeThen201() {

        PurposeRequest purposeRequests = new PurposeRequest();

        String purposeRequest = "";
        int i = 0;
        while (i < 201) {
            purposeRequest += "I";
            i++;
        }

        purposeRequests.setPurposeRequest(purposeRequest);

        Set<ConstraintViolation<PurposeRequest>> result = validator.validate(purposeRequests);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PurposeRequest> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("purposeRequest", v.getPropertyPath().toString());
    }

    @Test
    void B5917099_estPurposeRequestMustNotBeSizeThen4() {

        PurposeRequest purposeRequests = new PurposeRequest();

        purposeRequests.setPurposeRequest("iiii");

        Set<ConstraintViolation<PurposeRequest>> result = validator.validate(purposeRequests);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PurposeRequest> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("purposeRequest", v.getPropertyPath().toString());
    }
}