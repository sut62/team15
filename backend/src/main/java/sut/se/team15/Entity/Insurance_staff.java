package com.cpe.backend.entity;

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
@Table(name = "INSURANCE_STAFF")
public class Insurance_staff {
	@Id
	@SequenceGenerator(name = "insurance_staff_seq", sequenceName = "insurance_staff_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_staff_seq")
	@Column(name = "INSURANCE_STAFF_ID", unique = true, nullable = true)
	private @NonNull Long insurance_staff_id;
	private @NonNull String insurance_staff_name;

}