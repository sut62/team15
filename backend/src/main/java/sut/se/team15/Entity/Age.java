package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Age")
public class Age {
	@Id
	@SequenceGenerator(name="Age_seq",sequenceName="Age_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Age_seq")
	@Column(name="Age_ID",unique = true, nullable = true)
	private @NonNull Long id;
	

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Register> register;
}
