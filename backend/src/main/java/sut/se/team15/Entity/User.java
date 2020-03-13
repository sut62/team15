package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Past;


//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

//import com.cpe.backend.entity.ClubMember;;;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @SequenceGenerator(name = "User_seq", sequenceName = "User_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_seq")
    @Column(name = "User_ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String userid;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @Size(min = 2, max = 200)
    private String FirstName;

    @NotNull
    @Size(min = 2, max = 200)
    private String LastName;

    @NotNull
    @Past
    private LocalDate BirthDay;

    @NotNull
    @Max(100)
    @Min(5)
    private Long Age;

    @NotNull
    @Size(min = 5, max = 200)
    private String Career;

    @NotNull
    @Size(min = 5, max = 200)
    private String Disease;

    @NotNull
    @Size(min = 5, max = 10)
    private String PhoneNumber;

    @NotNull
    @Size(min = 5, max = 250)
    private String Address;

    @NotNull
    @Max(99999)
    @Min(10000)
    private Long PostalCode;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name = "Status_ID", insertable = true)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Title.class)
    @JoinColumn(name = "Title_ID", insertable = true)
    private Title title;

}