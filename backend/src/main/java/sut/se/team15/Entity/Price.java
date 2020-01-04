package sut.se.team15.Entity;

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
@Table(name="Price")
public class Price {
	@Id
	@SequenceGenerator(name="Price_seq",sequenceName="Price_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Price_seq")
	@Column(name="Price_ID",unique = true, nullable = true)
	private @NonNull Long Price_id;
	private @NonNull String Price_name;

	
}