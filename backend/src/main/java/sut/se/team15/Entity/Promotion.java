  
package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Data
@Entity
@NoArgsConstructor
@Table(name="Promotion")
public class Promotion {
	@Id
	@SequenceGenerator(name="Promotion_seq",sequenceName="Promotion_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Promotion_seq")
	@Column(name="Promotion_ID",unique = true, nullable = true)
	private Long Promotion_id;

	@NotNull
	@Size(min = 5 ,max = 100)
	private String Promotion_name;

    @NotNull
	@FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Promotion_StartDate;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Promotion_EndDate;

	@NotNull
	@Max(999999)
    @Min(100)
	private Double promotion_Discount;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CreateInsurance.class)
	@JoinColumn(name = "CreateInsurance_ID", insertable = true)
	private CreateInsurance createInsurance;

	




}