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
public class ProvinceTest {

    private Validator validator;

    
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
    void B5917440_testProvinceOK() { // ใส่ข้อมูลปกติ
        Province province = new Province();
        Region region = regionRepository.findById(1);
        
        province.setProvinceName("กรุงเทพ");
        province.setRegionId(region);
        

        province = provinceRepository.saveAndFlush(province);

        Optional<Province> found = provinceRepository.findById(province.getId());
        assertEquals(1, found.get().getId());
    }

    // BeNull
    @Test
    void B5917440_testProvinceNameBeNull() { // ใส่ข้อมูลทที่เป็นnull
        Province province = new Province();
        Region region = regionRepository.findById(1);
        
        province.setProvinceName(null);
        province.setRegionId(region);

        Set<ConstraintViolation<Province>> result = validator.validate(province);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Province> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("provinceName", error.getPropertyPath().toString());
    }

    // NotBeSize
    @Test
    void B5917440_testProvinceNameNotBeMinSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด

        Province province = new Province();
        Region region = regionRepository.findById(1);
        
        province.setProvinceName("เล");
        province.setRegionId(region);

        Set<ConstraintViolation<Province>> result = validator.validate(province);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Province> error = result.iterator().next();
        assertEquals("size must be between 3 and 20", error.getMessage());
        assertEquals("provinceName", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testProvinceNameNotBeMaxSize() { // ใส่ข้อมูลที่ขนาดมากกว่าที่ size กำหนด

        Province province = new Province();
        Region region = regionRepository.findById(1);
        
        province.setProvinceName("จังหวัดพระนครศรีอยุธยา");
        province.setRegionId(region);

        Set<ConstraintViolation<Province>> result = validator.validate(province);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Province> error = result.iterator().next();
        assertEquals("size must be between 3 and 20", error.getMessage());
        assertEquals("provinceName", error.getPropertyPath().toString());
    }

}