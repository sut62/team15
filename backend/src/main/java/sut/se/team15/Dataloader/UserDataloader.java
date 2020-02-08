package sut.se.team15.Dataloader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataloader implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private TitleRepository titleRepository;
	@Autowired
	ProvinceRepository provinceRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Stream.of("โสด", "สมรส", "หย่าร้าง").forEach(statusName -> {
			Status status = new Status();
			status.setStatusName(statusName);
			statusRepository.save(status);
		});

		Stream.of("เด็กหญิง", "เด็กชาย", "นาย", "นาง", "นางสาว").forEach(titles -> {
			Title title = new Title();
			title.setTitle(titles);
			titleRepository.save(title);
		});

		User user = new User();
		user.setUserid("1234567890123");
		user.setPassword("123456789");
		user.setFirstName("โจรูโน่");
		user.setLastName("โจบาน่า");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDay = LocalDate.parse("1999-01-01", format);

		user.setBirthDay(birthDay);
		user.setAge((long) 21);
		user.setCareer("นักศึกษา");
		user.setDisease("โคโรนา");
		user.setPhoneNumber("0812345678");
		user.setAddress("111, ถนน มหาวิทยาลัย ตำบล สุรนารี อำเภอเมืองนครราชสีมา นครราชสีมา");
		user.setPostalCode((long) 30000);

		Status status = statusRepository.findById(1);
		Province province = provinceRepository.findById(21);
		Title title = titleRepository.findById(3);

		user.setStatus(status);
		user.setProvince(province);
		user.setTitle(title);
		userRepository.save(user);

		statusRepository.findAll().forEach(System.out::println);
		titleRepository.findAll().forEach(System.out::println);
	};
}
