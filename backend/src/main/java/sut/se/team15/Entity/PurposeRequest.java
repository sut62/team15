package sut.se.team15.Entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Purpose_Request")
public class PurposeRequest {
    @Id
    @SequenceGenerator(name = "Purpose_Request_SEQ", sequenceName = "Purpose_Request_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Purpose_Request_SEQ")

    private @NonNull Long id;

    @NotNull
    @Size(min=5, max=200)
    @Column(name = "Purpose_Request_ID")
    private String purposeRequest;
}