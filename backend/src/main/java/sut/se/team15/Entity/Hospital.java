package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@NoArgsConstructor
@Table(name="Hospital")
public class Hospital {
	@Id
	@SequenceGenerator(name="Hospital_seq",sequenceName="Hospital_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Hospital_seq")
	@Column(name="Hospital_ID",unique = true, nullable = true)
	private @NonNull Long id;
    private @NonNull String hospitalName;
    private @NonNull String locationDetails;
    private @NonNull String telephoneNumber;

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = HospitalType.class)
    @JoinColumn(name = "HospitalType_ID", insertable = true)
    private HospitalType HospitalTypeId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Region.class)
    @JoinColumn(name = "Region_ID", insertable = true)
    private Region RegionId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private Province ProvinceId;


}