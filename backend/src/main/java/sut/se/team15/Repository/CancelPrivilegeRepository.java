package sut.se.team15.Repository;

import sut.se.team15.Entity.CancelPrivilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CancelPrivilegeRepository extends JpaRepository<CancelPrivilege, Long> {
    CancelPrivilege findById(long id);
}