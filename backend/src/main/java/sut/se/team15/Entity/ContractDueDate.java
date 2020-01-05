package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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
	private @NonNull Long ContractDueDate_id;
	private @NonNull String ContractDueDate_name;
	

}