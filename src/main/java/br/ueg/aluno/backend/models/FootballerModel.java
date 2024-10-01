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
public class FootballerModel extends FootballerBaseModel {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @NotNull
    @Column(name = "birthplace", nullable = false)
    private String birthplace;

    @NotNull
    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "club")
    private String club;

    @Column(name = "in_activity")
    private Boolean inActivity;
}
