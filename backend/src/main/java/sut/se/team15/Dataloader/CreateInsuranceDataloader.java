package sut.se.team15.Dataloader;

import java.util.stream.Stream;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateInsuranceDataloader implements ApplicationRunner {

	@Autowired
	private CreateInsuranceRepository createInsuranceRepository;
	
	@Autowired
	private InsuranceTypeRepository insuranceTypeRepository;
	@Autowired
	private PaymentPeriodRepository paymentPeriodRepository;
	@Autowired
    private ContractDueDateRepository contractDueDateRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Stream.of("ประเภทอุบัติเหตุ", "ประเภทเสียชีวิต","ประเภทโรคร้ายแรง").forEach(insuranceTypes -> {
			InsuranceType insuranceTypedb = new InsuranceType();
			insuranceTypedb.setInsuranceType_name(insuranceTypes);
			insuranceTypeRepository.save(insuranceTypedb);
		});

		Stream.of("3 เดือน", "6 เดือน","9 เดือน", "1 ปี").forEach(paymentPeriods -> {
			PaymentPeriod paymentPerioddb = new PaymentPeriod();
			paymentPerioddb.setPaymentPeriod_name(paymentPeriods);
			paymentPeriodRepository.save(paymentPerioddb);
		});

		Stream.of("1 ปี", "2 ปี","4 ปี", "6 ปี", "10 ปี").forEach(contractDueDates -> {
			ContractDueDate contractDueDatedb = new ContractDueDate();
			contractDueDatedb.setContractDueDate_name(contractDueDates);
			contractDueDateRepository.save(contractDueDatedb);
		});

		CreateInsurance createInsurance = new CreateInsurance();
		createInsurance.setCreateInsurance_name("ประกันอุบัติเหตุการเดินทาง");
		createInsurance.setCreateInsurance_accidentCoverage("คุ้มครองอุบัติเหตุ");
		createInsurance.setCreateInsurance_diseaseCoverage("คุ้มครองโรคประจำตัว");
		createInsurance.setCreateInsurance_protectionRights("ค่ารักษาพยาบาล");
		createInsurance.setCreateInsurance_termOfProtection("รักษาเฉพาะโรงพยาบาลในเครือเท่านั่น");
		createInsurance.setCreateInsurance_insurancePremium((double)30000);

		InsuranceType InsuranceTypeId = insuranceTypeRepository.findById(1);
		ContractDueDate ContractDueDateId = contractDueDateRepository.findById(1);
		PaymentPeriod PaymentPeriodId = paymentPeriodRepository.findById(1);

		createInsurance.setInsuranceTypeId(InsuranceTypeId);
		createInsurance.setContractDueDateId(ContractDueDateId);
		createInsurance.setPaymentPeriodId(PaymentPeriodId);
		createInsuranceRepository.save(createInsurance);

        insuranceTypeRepository.findAll().forEach(System.out::println);
		paymentPeriodRepository.findAll().forEach(System.out::println);
		contractDueDateRepository.findAll().forEach(System.out::println);

	};
}