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

import javax.validation.constraints.*;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RENEWINSURANCE")
public class RenewInsurance {
    @Id
    @SequenceGenerator(name = "renewinsurance_seq", sequenceName = "renewinsurance_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "renewinsurance_seq")
    @Column(name = "RENEWINSURANCE_ID", unique = true, nullable = true)
    private @NonNull Long reins_id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "USER_ID", insertable = true)
    @NotNull
    private User useID;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RegisterInsurance.class)
    @JoinColumn(name = "REGISTER_ID", insertable = true)
    @NotNull
    private RegisterInsurance regID;

    
}