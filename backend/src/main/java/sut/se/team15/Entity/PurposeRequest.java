package sut.se.team15.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @Column(name = "Purpose_Request_ID", unique = true, nullable = true)

    private @NonNull Long id;
    private @NonNull String purposeRequest;
}