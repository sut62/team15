package sut.se.team15.Repository;

import  sut.se.team15.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaymentPeriodRepository extends JpaRepository<PaymentPeriod, Long> {
	PaymentPeriod findById(long PaymentPeriod_id);
}