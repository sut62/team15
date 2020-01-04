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
	private InsuranceTypeRepository insuranceTypeRepository;
	@Autowired
	private PaymentPeriodRepository paymentPeriodRepository;

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

		
		

        insuranceTypeRepository.findAll().forEach(System.out::println);
		paymentPeriodRepository.findAll().forEach(System.out::println);

	};
}