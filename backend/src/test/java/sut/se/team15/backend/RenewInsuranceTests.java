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
public class RenewInsuranceTests {
    
    private Validator validator;

    @Autowired
    private RenewInsuranceRepository renewInsuranceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterInsuranceRepository registerInsuranceRepository;
    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TitleRepository titleRepository;
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

    @Test
    void B6001025_testRenewInsuranceOK() { // ใส่ข้อมูลปกติ
        /**set ข้อมูลของ user**/
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
        /**************set ข้อมูลของ register insurance********************* */
        

        CreateInsurance createInsurance = new CreateInsurance();

        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
        insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
        ContractDueDate contractDueDate = new ContractDueDate();
        contractDueDate.setContractDueDate_name("1 ปี");
        contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
         PaymentPeriod paymentPeriod = new PaymentPeriod();
         paymentPeriod.setPaymentPeriod_name("3 เดือน");
         paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
        createInsurance.setCreateInsurance_name("CreateInsurance_name");
        createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
        createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
        createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
        createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
        createInsurance.setInsuranceTypeId(insuranceType);
        createInsurance.setContractDueDateId(contractDueDate);
        createInsurance.setPaymentPeriodId(paymentPeriod);
        createInsurance.setCreateInsurance_insurancePremium(500.00);
        createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

        Person person = new Person();
        person.setPersontype("มารดา");
        person = personRepository.saveAndFlush(person);

        Title title = new Title();
        title.setTitle("นางสาว");
        title = titleRepository.saveAndFlush(title);

        RegisterInsurance registerInsurance = new RegisterInsurance();
        registerInsurance.setUser(user);
        registerInsurance.setCreateInsurance(createInsurance);
        registerInsurance.setPerson(person);
        registerInsurance.setTitle(title);
        registerInsurance.setName("Name");
        registerInsurance.setSurname("Surname");
     
        registerInsurance = registerInsuranceRepository.saveAndFlush(registerInsurance);
        /**************************************************/
        RenewInsurance renewInsurance = new RenewInsurance();
        renewInsurance.setUseID(user);
        renewInsurance.setRegID(registerInsurance);
        renewInsurance = renewInsuranceRepository.saveAndFlush(renewInsurance);
 
        Optional<RenewInsurance> found = renewInsuranceRepository.findById(renewInsurance.getReins_id());
        assertEquals(user, found.get().getUseID());
        assertEquals(registerInsurance, found.get().getRegID());
    }

    @Test
    void B6001025_testUserMustNotBeNull() {
       /**set ข้อมูลของ user**/
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
       /**************set ข้อมูลของ register insurance********************* */
       

       CreateInsurance createInsurance = new CreateInsurance();

       InsuranceType insuranceType = new InsuranceType();
       insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
       insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
       ContractDueDate contractDueDate = new ContractDueDate();
       contractDueDate.setContractDueDate_name("1 ปี");
       contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
        PaymentPeriod paymentPeriod = new PaymentPeriod();
        paymentPeriod.setPaymentPeriod_name("3 เดือน");
        paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
       createInsurance.setCreateInsurance_name("CreateInsurance_name");
       createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
       createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
       createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
       createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
       createInsurance.setInsuranceTypeId(insuranceType);
       createInsurance.setContractDueDateId(contractDueDate);
       createInsurance.setPaymentPeriodId(paymentPeriod);
       createInsurance.setCreateInsurance_insurancePremium(500.00);
       createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

       Person person = new Person();
       person.setPersontype("มารดา");
       person = personRepository.saveAndFlush(person);

       Title title = new Title();
       title.setTitle("นางสาว");
       title = titleRepository.saveAndFlush(title);

       RegisterInsurance registerInsurance = new RegisterInsurance();
       registerInsurance.setUser(user);
       registerInsurance.setCreateInsurance(createInsurance);
       registerInsurance.setPerson(person);
       registerInsurance.setTitle(title);
       registerInsurance.setName("Name");
       registerInsurance.setSurname("Surname");
    
       registerInsurance = registerInsuranceRepository.saveAndFlush(registerInsurance);
        /**************************************************/
        RenewInsurance renewInsurance = new RenewInsurance();
        renewInsurance.setUseID(null);
        renewInsurance.setRegID(registerInsurance);
        

        Set<ConstraintViolation<RenewInsurance>> result = validator.validate(renewInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RenewInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("useID", v.getPropertyPath().toString());
    }

    @Test
    void B6001025_testRegisterInsurenceMustNotBeNull() {
      /**set ข้อมูลของ user**/
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
      /**************set ข้อมูลของ register insurance********************* */
      

      CreateInsurance createInsurance = new CreateInsurance();

      InsuranceType insuranceType = new InsuranceType();
      insuranceType.setInsuranceType_name("ประเภทอุบัติเหตุ");
      insuranceType = insuranceTypeRepository.saveAndFlush(insuranceType);
      ContractDueDate contractDueDate = new ContractDueDate();
      contractDueDate.setContractDueDate_name("1 ปี");
      contractDueDate = contractDueDateRepository.saveAndFlush(contractDueDate);
       PaymentPeriod paymentPeriod = new PaymentPeriod();
       paymentPeriod.setPaymentPeriod_name("3 เดือน");
       paymentPeriod = paymentPeriodRepository.saveAndFlush(paymentPeriod);
      createInsurance.setCreateInsurance_name("CreateInsurance_name");
      createInsurance.setCreateInsurance_accidentCoverage("CreateInsurance_accidentCoverage");
      createInsurance.setCreateInsurance_diseaseCoverage("CreateInsurance_diseaseCoverage");
      createInsurance.setCreateInsurance_protectionRights("CreateInsurance_protectionRights");
      createInsurance.setCreateInsurance_termOfProtection("CreateInsurance_termOfProtection");
      createInsurance.setInsuranceTypeId(insuranceType);
      createInsurance.setContractDueDateId(contractDueDate);
      createInsurance.setPaymentPeriodId(paymentPeriod);
      createInsurance.setCreateInsurance_insurancePremium(500.00);
      createInsurance = createInsuranceRepository.saveAndFlush(createInsurance);

      Person person = new Person();
      person.setPersontype("มารดา");
      person = personRepository.saveAndFlush(person);

      Title title = new Title();
      title.setTitle("นางสาว");
      title = titleRepository.saveAndFlush(title);

      RegisterInsurance registerInsurance = new RegisterInsurance();
      registerInsurance.setUser(user);
      registerInsurance.setCreateInsurance(createInsurance);
      registerInsurance.setPerson(person);
      registerInsurance.setTitle(title);
      registerInsurance.setName("Name");
      registerInsurance.setSurname("Surname");
   
      registerInsurance = registerInsuranceRepository.saveAndFlush(registerInsurance);
        /**************************************************/
        RenewInsurance renewInsurance = new RenewInsurance();
        renewInsurance.setUseID(user);
        renewInsurance.setRegID(null);
        

        Set<ConstraintViolation<RenewInsurance>> result = validator.validate(renewInsurance);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RenewInsurance> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("regID", v.getPropertyPath().toString());
    }

  
   
}