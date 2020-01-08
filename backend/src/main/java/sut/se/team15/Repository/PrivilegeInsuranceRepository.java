package sut.se.team15.Repository;

import sut.se.team15.Entity.PrivilegeInsurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PrivilegeInsuranceRepository extends JpaRepository<PrivilegeInsurance, Long> {
    PrivilegeInsurance findById(long id);
}