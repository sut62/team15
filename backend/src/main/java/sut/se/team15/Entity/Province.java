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

//import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name="Province")
public class Province {
	@Id
	@SequenceGenerator(name="Province_seq",sequenceName="Province_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Province_seq")
	@Column(name="Province_ID",unique = true, nullable = true)
	private @NonNull Long id;
	

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Register> register;
}
