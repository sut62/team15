package sut.se.team15.Repository;

import  sut.se.team15.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CreateInsuranceRepository extends JpaRepository<CreateInsurance, Long> {
	CreateInsurance findById(long CreateInsurance_id);
}