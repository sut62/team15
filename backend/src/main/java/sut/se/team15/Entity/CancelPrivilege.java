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

import java.util.Date;

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
@Table(name = "Cancel_Privilege")
public class CancelPrivilege {
    @Id
    @SequenceGenerator(name = "Cancel_Privilege_SEQ", sequenceName = "Cancel_Privilege_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Cancel_Privilege_SEQ")
    @Column(name = "Cancel_Privilege_ID", unique = true, nullable = true)

    private @NonNull Long id;

    @NotNull
    private Date cancelDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PrivilegeInsurance.class)
    @JoinColumn(name = "Privilege_Insurance_ID", insertable = true)
    private PrivilegeInsurance privilegeInsurance;

}