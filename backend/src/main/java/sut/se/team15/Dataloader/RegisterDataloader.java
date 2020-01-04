package sut.se.team15.Dataloader;

import java.util.stream.Stream;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RegisterDataloader implements ApplicationRunner {

	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private TitleRepository titleRepository;

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

		statusRepository.findAll().forEach(System.out::println);
		titleRepository.findAll().forEach(System.out::println);

	};
}
