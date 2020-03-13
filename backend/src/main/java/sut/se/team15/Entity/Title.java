package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;


import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Title")
public class Title {
	@Id
	@SequenceGenerator(name="Title_seq",sequenceName="Title_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Title_seq")
	@Column(name="Title_ID",unique = true, nullable = true)
	private @NonNull Long id;
	
	@NotNull
	private String Title;

}