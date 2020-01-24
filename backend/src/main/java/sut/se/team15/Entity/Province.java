package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Region.class)
    @JoinColumn(name = "Region_ID", insertable = true)
    private Region regionId;

}
