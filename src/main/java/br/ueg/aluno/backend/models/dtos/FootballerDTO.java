package br.ueg.aluno.backend.models.dtos;

import br.ueg.aluno.backend.annotations.Get;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FootballerDTO {

    @Schema(description = "Nome do jogador", example = "Kylian Mbappé")
    private String name;

    @Schema(description = "Data de nascimento do jogador no formato YYYY-MM-DD (ano-mês-dia)", example = "1998-12-20")
    private LocalDate birthday;

    @Schema(description = "País onde o jogador nasceu", example = "França")
    private String birthplace;

    @Schema(description = "Posição principal do jogador", example = "Atacante")
    private String position;

    @Schema(description = "Clube em que jogador atua (se houver)", example = "Paris Saint-Germain")
    private String club;

    @Schema(description = "Indicar com 'true' ou 'false' se o jogador está em atividade ou não", example = "true")
    private Boolean inActivity;

    @Get
    public String getName() {
        return name;
    }

    @Get
    public LocalDate getBirthday() {
        return birthday;
    }

    @Get
    public String getBirthplace() {
        return birthplace;
    }

    @Get
    public String getPosition() {
        return position;
    }

    @Get
    public String getClub() {
        return club;
    }

    @Get
    public Boolean getInActivity() {
        return inActivity;
    }

}
