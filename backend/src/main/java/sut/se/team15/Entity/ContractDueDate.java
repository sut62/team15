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
@Table(name="ContractDueDate")
public class ContractDueDate {
	@Id
	@SequenceGenerator(name="ContractDueDate_seq",sequenceName="ContractDueDate_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ContractDueDate_seq")
	@Column(name="ContractDueDate_ID",unique = true, nullable = true)
	private Long ContractDueDate_id;


	@NotNull
    @Column(name="ContractDueDate_NAME")
	private String ContractDueDate_name;
	
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<CreateInsurance> CreateInsurance;
	
	
	

}