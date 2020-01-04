package sut.se.team15.Dataloader;

import java.util.stream.Stream;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RegisterInsuranceDataloader implements ApplicationRunner {

	@Autowired
	private PersonRepository personRepository;


	@Override
	public void run(ApplicationArguments args) throws Exception {

		Stream.of("ตนเอง","บุตร","สามี","ภรรยา","บิดา","มารดา","ลุง","ป้า","น้า","อา")
				.forEach(Persontype -> {
					Person person = new Person();
					person.setPersontype(Persontype);
					personRepository.save(person);
				});

       

		personRepository.findAll().forEach(System.out::println);
        

	};
}