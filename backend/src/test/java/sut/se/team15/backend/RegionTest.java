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
public class RegionTest {

    private Validator validator;

    @Autowired
    private RegionRepository regionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // OK
    @Test
    void B5917440_testRegionOK() { // ใส่ข้อมูลปกติ
        Region region = new Region();
        region.setRegionName("ภาคตะวันออกเฉียงเหนือ");
        
        region = regionRepository.saveAndFlush(region);

        Optional<Region> found = regionRepository.findById(region.getId());
        assertEquals(1, found.get().getId());
    }

    // BeNull
    @Test
    void B5917440_testRegionNameBeNull() { // ใส่ข้อมูลทที่เป็นnull
        Region region = new Region();
        region.setRegionName(null);

        Set<ConstraintViolation<Region>> result = validator.validate(region);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Region> error = result.iterator().next();
        assertEquals("must not be null", error.getMessage());
        assertEquals("regionName", error.getPropertyPath().toString());
    }

    // NotBeSize
    @Test
    void B5917440_testRegionNameNotBeMinSize() { // ใส่ข้อมูลที่ขนาดน้อยกว่าที่ size กำหนด

        Region region = new Region();
        region.setRegionName("ภา");

        Set<ConstraintViolation<Region>> result = validator.validate(region);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Region> error = result.iterator().next();
        assertEquals("size must be between 3 and 25", error.getMessage());
        assertEquals("regionName", error.getPropertyPath().toString());
    }

    @Test
    void B5917440_testRegionNameNotBeMaxSize() { // ใส่ข้อมูลที่ขนาดมากกว่าที่ size กำหนด

        Region region = new Region();
        region.setRegionName("ภาคตะวันออกเฉียงเหนือภาคตะวันออกเฉียงเหนือ");

        Set<ConstraintViolation<Region>> result = validator.validate(region);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Region> error = result.iterator().next();
        assertEquals("size must be between 3 and 25", error.getMessage());
        assertEquals("regionName", error.getPropertyPath().toString());
    }
}