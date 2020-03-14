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
public class ContractDueDateTest {

    private Validator validator;

    @Autowired
    private ContractDueDateRepository contractDueDateRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010256_testContractDueDateComplete(){
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");

        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);

        Optional<ContractDueDate> found = contractDueDateRepository.findById(contractDueDate.getContractDueDate_id());
        assertEquals("1 ปี", found.get().getContractDueDate_name());
    }

    @Test
    void B6010256_testContractDueDateMustNotBeNull(){
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name(null);

        Set<ConstraintViolation<ContractDueDate>> result = validator.validate(contractDueDate);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ContractDueDate> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ContractDueDate_name", v.getPropertyPath().toString());
    }
}