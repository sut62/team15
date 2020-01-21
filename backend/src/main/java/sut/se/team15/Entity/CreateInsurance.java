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
import javax.validation.constraints.*;

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
	private Long CreateInsurance_id;

	@NotNull
	@Size(min = 5 ,max = 100)
	private String CreateInsurance_name;

	@NotNull
	@Size(min = 5 ,max = 100)
	private String CreateInsurance_accidentCoverage;

	@NotNull
	@Size(min = 5 ,max = 100)
	private String CreateInsurance_diseaseCoverage;

	@NotNull
	@Size(min = 5 ,max = 100)
	private String CreateInsurance_protectionRights;

	@NotNull
	@Size(min = 5 ,max = 100)
	private String CreateInsurance_termOfProtection;

	@NotNull
	@Max(999999)
    @Min(100)
	private Double CreateInsurance_insurancePremium;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = InsuranceType.class)
    @JoinColumn(name = "InsuranceType_ID", insertable = true)
    private InsuranceType InsuranceTypeId;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ContractDueDate.class)
    @JoinColumn(name = "ContractDueDate_ID", insertable = true)
	private ContractDueDate ContractDueDateId;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = PaymentPeriod.class)
    @JoinColumn(name = "PaymentPeriod_ID", insertable = true)
	private PaymentPeriod PaymentPeriodId;




}