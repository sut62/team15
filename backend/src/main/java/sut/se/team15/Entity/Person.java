package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Person")
public class Person {
	@Id
	@SequenceGenerator(name = "Person_seq", sequenceName = "Person_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Person_seq")
	@Column(name = "Person_ID", unique = true, nullable = true)
    private @NonNull Long PersonId;
    
}