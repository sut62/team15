package sut.se.team15.Dataloader;

import java.util.stream.Stream;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PayInsuranceDataloader implements ApplicationRunner {

	@Autowired
	private Insurance_staffRepository insurance_staffRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Stream.of("สมรัก", "สมพร", "สมศักดิ์", "สมบูรณ์", "ประเสร็ฐ", "สมศรี","เพ็ญศรี", "วันเพ็ญ", "จันทร์เพ็ญ", "เอกรินทร์", "พรทิพย์", "ศศิธร").forEach(staff_name -> {
			Insurance_staff insurance_staff = new Insurance_staff();
			insurance_staff.setStaff_name(staff_name);
			insurance_staffRepository.save(insurance_staff);
		});

		
		insurance_staffRepository.findAll().forEach(System.out::println);
		

	};
}
