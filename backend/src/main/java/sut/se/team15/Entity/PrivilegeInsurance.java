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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Privilege_Insurance")
public class PrivilegeInsurance {
    @Id
    @SequenceGenerator(name = "Privilege_Insurance_SEQ", sequenceName = "Privilege_Insurance_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Privilege_Insurance_SEQ")
    @Column(name = "Privilege_Insurance_ID", unique = true, nullable = true)

    private @NonNull Long id;

    @NotNull
    @FutureOrPresent
    @Column(name = "PrivilegeDate")
    private LocalDate privilegeDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hospital.class)
    @JoinColumn(name = "Hospital_ID", insertable = true)
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PurposeRequest.class)
    @JoinColumn(name = "Purpose_Request_ID", insertable = true)
    private PurposeRequest purposeRequest;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RegisterInsurance.class)
    @JoinColumn(name = "RegisterInsurance_ID", insertable = true)
    private RegisterInsurance registerInsurance;
}