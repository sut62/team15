package sut.se.team15.Repository;

import  sut.se.team15.Entity.RenewInsurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RenewInsuranceRepository extends JpaRepository<RenewInsurance, Long> {
	RenewInsurance findById(long id);
}