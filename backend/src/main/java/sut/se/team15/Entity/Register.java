package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

//import com.cpe.backend.entity.ClubMember;;;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name="Register")
public class Register {

    @Id
    @SequenceGenerator(name="Register_seq",sequenceName="Register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Register_seq")
    @Column(name = "Register_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String user_id;
	private @NonNull String password;
	private @NonNull String FirstName;
    private @NonNull String LastName;
    private @NonNull LocalDate BirthDay;
    private @NonNull Long Age;
    private @NonNull String Career;
    private @NonNull String Disease;
    private @NonNull String PhoneNumber;
    private @NonNull String Address;
    private @NonNull Long PostalCode;
  
	

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "Status_ID", insertable = true)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Activity_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Title.class)
    @JoinColumn(name = "Title_ID", insertable = true)
    private Title title;

 
}