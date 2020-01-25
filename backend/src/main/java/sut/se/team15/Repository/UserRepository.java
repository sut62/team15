package sut.se.team15.Repository;

import sut.se.team15.Entity.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    // @Query( value = "SELECT * FROM where USERID = 1", nativeQuery = true)
    // Collection<User> findByUser (@Param("userid") String userid);

    List<User> findByUserid(String userid);

}