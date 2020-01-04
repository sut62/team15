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
import javax.persistence.Column;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PAYINSURANCE")
public class PayInsurance {
    @Id
    @SequenceGenerator(name = "payinsurance_seq", sequenceName = "payinsurance_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payinsurance_seq")
    @Column(name = "PAYINSURANCE_ID", unique = true, nullable = true)
    private @NonNull Long ins_id;
	private @NonNull double amount;
    
}