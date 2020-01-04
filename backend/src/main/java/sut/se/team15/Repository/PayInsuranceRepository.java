package sut.se.team15.Repository;

import  sut.se.team15.Entity.PayInsurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PayInsuranceRepository extends JpaRepository<PayInsurance, Long> {
	PayInsurance findById(long id);
}