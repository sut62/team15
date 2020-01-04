package sut.se.team15.Repository;

import  sut.se.team15.Entity.Insurance_staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Insurance_staffRepository extends JpaRepository<Insurance_staff, Long> {
	Insurance_staff findById(long id);
}