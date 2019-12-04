package com.okta.springbootvue.Entity;

import lombok.*;

import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.FetchType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RegisterInsurance")
public class RegisterInsurance {
	@Id
	@SequenceGenerator(name = "RegisterInsurance_seq", sequenceName = "RegisterInsurance_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RegisterInsurance_seq")
	@Column(name = "RegisterInsurance_ID", unique = true, nullable = true)
    private @NonNull Long RegisterInsuranceId;
    
}