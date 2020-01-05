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
@Table(name="PaymentPeriod")
public class PaymentPeriod {
	@Id
	@SequenceGenerator(name="PaymentPeriod_seq",sequenceName="PaymentPeriod_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PaymentPeriod_seq")
	@Column(name="PaymentPeriod_ID",unique = true, nullable = true)
	private @NonNull Long PaymentPeriod_id;
	private @NonNull String PaymentPeriod_name;


}