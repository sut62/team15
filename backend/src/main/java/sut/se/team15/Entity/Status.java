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
@Table(name="Status")
public class Status {
	@Id
	@SequenceGenerator(name="Status_seq",sequenceName="Status_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Status_seq")
	@Column(name="Status_ID",unique = true, nullable = true)
	private @NonNull Long id;

	@NotNull
	private  String StatusName;
	

	/*@OneToMany(fetch = FetchType.EAGER)
	private Collection<User> user;*/
}
