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
	private @NonNull Long RegisterInsuranceId;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CreateInsurance.class)
	@JoinColumn(name = "CreateInsuranceId", insertable = true)
	private CreateInsurance CreateInsurance;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Person.class)
	@JoinColumn(name = "PersonId", insertable = true)
	private Person person;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Title.class)
	@JoinColumn(name = "id", insertable = true)
	private  Title title;

	@Column(name = "Name")
	private @NonNull String name;

	@Column(name = "Surname")
	private @NonNull String surname;
    
}