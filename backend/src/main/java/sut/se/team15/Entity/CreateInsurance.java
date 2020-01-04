package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="CreateInsurance")
public class CreateInsurance {
	@Id
	@SequenceGenerator(name="CreateInsurance_seq",sequenceName="CreateInsurance_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CreateInsurance_seq")
	@Column(name="CreateInsurance_ID",unique = true, nullable = true)
	private @NonNull Long CreateInsurance_id;
	private @NonNull String CreateInsurance_name;
	private @NonNull String CreateInsurance_details;
	private @NonNull Double CreateInsurance_price;





}