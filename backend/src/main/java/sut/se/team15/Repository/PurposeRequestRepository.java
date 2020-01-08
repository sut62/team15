package sut.se.team15.Repository;

import sut.se.team15.Entity.PurposeRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurposeRequestRepository extends JpaRepository<PurposeRequest, Long> {
    PurposeRequest findById(long id);
}