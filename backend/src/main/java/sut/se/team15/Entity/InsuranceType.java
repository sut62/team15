package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="InsuranceType")
public class InsuranceType {
	@Id
	@SequenceGenerator(name="InsuranceType_seq",sequenceName="InsuranceType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="InsuranceType_seq")
	@Column(name="InsuranceType_ID",unique = true, nullable = true)
	private @NonNull Long InsuranceType_id;
	


}