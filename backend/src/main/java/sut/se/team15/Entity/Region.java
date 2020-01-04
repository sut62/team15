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
@Table(name="Region")
public class Region {
	@Id
	@SequenceGenerator(name="Region_seq",sequenceName="Region_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Region_seq")
	@Column(name="Region_ID",unique = true, nullable = true)
	private @NonNull Long id;
    private @NonNull String regionName;
    
    

}