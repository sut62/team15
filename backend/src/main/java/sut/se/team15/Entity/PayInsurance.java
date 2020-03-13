package sut.se.team15.Entity;
import sut.se.team15.Entity.Insurance_staff;

import javax.persistence.*;
import lombok.*;
import java.lang.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PAYINSURANCE ")
public class PayInsurance {
    @Id
    @SequenceGenerator(name = "payinsurance_seq", sequenceName = "payinsurance_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payinsurance_seq")
    @Column(name = "PAYINSURANCE_ID", unique = true, nullable = true)
    private @NonNull Long ins_id;

    @NotNull
	@Max(999999)
    @Min(100)
	private  Double amount;
    
   
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Insurance_staff.class)
    @JoinColumn(name = "STAFF_ID", insertable = true)
    @NotNull
    private Insurance_staff staffID;

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "USER_ID", insertable = true)
    @NotNull
    private   User regID;

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CreateInsurance.class)
    @JoinColumn(name = "INSURANCE_ID", insertable = true)
    @NotNull
    private CreateInsurance insID;
    

    
}