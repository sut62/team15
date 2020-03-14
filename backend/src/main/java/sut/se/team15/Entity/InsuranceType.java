package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.validation.constraints.NotNull;
import java.util.Collection;



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
	private Long InsuranceType_id;
	
	
	@NotNull
    @Column(name="InsuranceType_NAME")
	private String InsuranceType_name;
	
     @OneToMany(fetch = FetchType.EAGER)
     private Collection<CreateInsurance> CreateInsurance;
}
