package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="HospitalType")
public class HospitalType {
	@Id
	@SequenceGenerator(name="HospitalType_seq",sequenceName="HospitalType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HospitalType_seq")
	@Column(name="HospitalType_ID",unique = true, nullable = true)
	private @NonNull Long id;
	@NotNull
	@Size(min = 10, max = 20)
    private String typeName;

}