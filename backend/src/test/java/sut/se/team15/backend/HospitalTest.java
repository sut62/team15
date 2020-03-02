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
public class HospitalTest {

    private Validator validator;

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // OK
    @Test
    void B5917440_testHospitalOK() { // ใส่ข้อมูลปกติ
        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("โรงพยาบาลมหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        hospital = hospitalRepository.saveAndFlush(hospital);

        Optional<Hospital> found = hospitalRepository.findById(hospital.getId());
        assertEquals(1, found.get().getId());
    }

    // BeNull
    @Test
    void B5917440_testHospitalNameBeNull() { // ใส่ข้อมูลทที่เป็นnull
        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName(null);
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("hospitalName", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testLocationDetailsBeNull() { // ใส่ข้อมูลทที่เป็นnull
        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("โรงพยาบาลมหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails(null);
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("locationDetails", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testTelephoneNumberBeNull() { // ใส่ข้อมูลทที่เป็นnull
        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("โรงพยาบาลมหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber(null);
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("telephoneNumber", error.getPropertyPath().toString());
    }

    // NotBeSize
    @Test
    void B5917440_testhospitalNameNotBeMinSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด

        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("โรงพยาบาล");
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("size must be between 10 and 300", error.getMessage());
        assertEquals("hospitalName", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testHospitalNameNotBeMaxSize() { // ใส่ข้อมูลที่ขนาดมากกว่าที่ size กำหนด

        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName(
                "1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล");
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("size must be between 10 and 300", error.getMessage());
        assertEquals("hospitalName", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testLocationDetailsNotBeMinSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด

        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails("โรงพยาบาล");
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("size must be between 10 and 300", error.getMessage());
        assertEquals("locationDetails", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testLocationDetailsNotBeMaxSize() { // ใส่ข้อมูลที่ขนาดมากกว่าที่ size กำหนด

        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails(
                "1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล1โรงพยาบาล");
        hospital.setTelephoneNumber("1234567890");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("size must be between 10 and 300", error.getMessage());
        assertEquals("locationDetails", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testTelephoneNumberNotBeMinSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด

        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber("12345678");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("size must be between 9 and 10", error.getMessage());
        assertEquals("telephoneNumber", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testTelephoneNumberNotBeMaxSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด

        Hospital hospital = new Hospital();
        HospitalType hospitalType = hospitalTypeRepository.findById(1);
        Region region = regionRepository.findById(1);
        Province province = provinceRepository.findById(2);

        hospital.setHospitalName("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setLocationDetails("มหาวิทยาลัยเทคโนโลยีสุรนารี");
        hospital.setTelephoneNumber("12345678901");
        hospital.setHospitalTypeId(hospitalType);
        hospital.setRegionId(region);
        hospital.setProvinceId(province);

        Set<ConstraintViolation<Hospital>> result = validator.validate(hospital);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Hospital> error = result.iterator().next();
        assertEquals("size must be between 9 and 10", error.getMessage());
        assertEquals("telephoneNumber", error.getPropertyPath().toString());
    }

}