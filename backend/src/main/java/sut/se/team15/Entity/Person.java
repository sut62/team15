package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Person_type")
public class Person {
	@Id
	@SequenceGenerator(name = "Person_seq", sequenceName = "Person_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Person_seq")
	
	private @NonNull Long PersonId;
	
	@NotNull
	@Size(min = 2, max = 200)
	@Column(name = "Persontype")
	private String Persontype;
	
	
}