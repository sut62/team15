package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.persistence.Column;
import javax.persistence.Entity;

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
	private @NonNull String provinceName;
}
