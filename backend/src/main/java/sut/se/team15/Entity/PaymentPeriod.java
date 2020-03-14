package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.validation.constraints.NotNull;
import java.util.Collection;



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
	private Long PaymentPeriod_id;

	@NotNull
    @Column(name="PaymentPeriod_NAME")
	private String PaymentPeriod_name;
	
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<CreateInsurance> CreateInsurance;
	


}