package sut.se.team15.Dataloader;

import java.util.stream.Stream;

import sut.se.team15.Entity.PurposeRequest;
import sut.se.team15.Repository.PurposeRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeInsuranceDataloader implements ApplicationRunner {

	@Autowired
	private PurposeRequestRepository purposeRequestRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Stream.of("ค่ารักษาอุบัติเหตุ", "ค่ารักษาโรคร้ายแรง", "ค่ารักษาผู้ป่วยนอก","ค่ารักษาพยาบาลทั่วไป").forEach(purposeRequests -> {
			PurposeRequest purposeRequest = new PurposeRequest();
			purposeRequest.setPurposeRequest(purposeRequests);
			purposeRequestRepository.save(purposeRequest);
		});

		purposeRequestRepository.findAll().forEach(System.out::println);
	};
}