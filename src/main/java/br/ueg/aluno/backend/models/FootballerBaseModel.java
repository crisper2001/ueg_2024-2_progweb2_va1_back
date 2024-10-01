package br.ueg.aluno.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FOOTBALLER")
public abstract class FootballerBaseModel implements IGenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "footballer_sequence",
            sequenceName = "footballer_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "footballer_sequence"
    )
    @Column(name = "id", nullable = false)
    private Long id;

}
