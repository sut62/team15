package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="ProtectionPeriod")
public class ProtectionPeriod {
	@Id
	@SequenceGenerator(name="ProtectionPeriod_seq",sequenceName="ProtectionPeriod_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ProtectionPeriod_seq")
	@Column(name="ProtectionPeriod_ID",unique = true, nullable = true)
	private @NonNull Long ProtectionPeriod_id;
	

	@OneToMany(fetch = FetchType.EAGER)
    private Collection<ProtectionPeriod> protectionPeriod;
	
}