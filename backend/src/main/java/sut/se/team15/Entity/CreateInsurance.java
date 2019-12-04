package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="CreateInsurance")
public class CreateInsurance {
	@Id
	@SequenceGenerator(name="CreateInsurance_seq",sequenceName="CreateInsurance_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CreateInsurance_seq")
	@Column(name="CreateInsurance_ID",unique = true, nullable = true)
	private @NonNull Long id;


	@ManyToOne(fetch = FetchType.EAGER, targetEntity = InsuranceType.class)
    @JoinColumn(name = "InsuranceType_ID", insertable = true)
	private InsuranceType insuranceType;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Price.class)
    @JoinColumn(name = "Price_ID", insertable = true)
	private Price price;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProtectionPeriod.class)
    @JoinColumn(name = "ProtectionPeriod_ID", insertable = true)
	private ProtectionPeriod protectionPeriod;	


}