package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name = "RegisterInsurance")
public class RegisterInsurance {
	@Id
	@SequenceGenerator(name = "RegisterInsurance_seq", sequenceName = "RegisterInsurance_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RegisterInsurance_seq")
	@Column(name = "RegisterInsurance_ID", unique = true, nullable = true)
	private  Long RegisterInsuranceId;
	
	@NotNull
	@Pattern(regexp = "\\D+")
	@Size(min = 2 ,max = 200)
	private  String name;

	@NotNull
	@Pattern(regexp = "\\D+")
	@Size(min = 5 ,max = 200)
	private String surname;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CreateInsurance.class)
	@JoinColumn(name = "CreateInsuranceId", insertable = true)
	@NotNull
	private CreateInsurance CreateInsurance;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Person.class)
	@JoinColumn(name = "PersonId", insertable = true)
	@NotNull
	private Person person;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Title.class)
	@JoinColumn(name = "Titleid", insertable = true)
	@NotNull
	private  Title title;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "Userid", insertable = true)
	@NotNull
	private  User user;

	
}