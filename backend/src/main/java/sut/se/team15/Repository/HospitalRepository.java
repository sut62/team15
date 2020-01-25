package sut.se.team15.Repository;

import java.util.List;
import  sut.se.team15.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	Hospital findById(long id);
	List<Hospital> findByhospitalName(String hospitalName);
}