package br.ueg.aluno.backend.mappers;

import br.ueg.aluno.backend.models.FootballerModel;
import br.ueg.aluno.backend.models.dtos.FootballerDTO;
import org.springframework.stereotype.Component;

@Component
public class FootballerMapper {
    public FootballerModel toModel(FootballerDTO dto) {
        FootballerModel f = new FootballerModel();
        f.setName(dto.getName());
        f.setBirthday(dto.getBirthday());
        f.setBirthplace(dto.getBirthplace());
        f.setPosition(dto.getPosition());
        f.setClub(dto.getClub());
        f.setInActivity(dto.getInActivity());
        return f;
    }
    public FootballerDTO toDTO(FootballerModel m) {
        FootballerDTO dto = new FootballerDTO();
        dto.setName(m.getName());
        dto.setBirthday(m.getBirthday());
        dto.setBirthplace(m.getBirthplace());
        dto.setPosition(m.getPosition());
        dto.setClub(m.getClub());
        dto.setInActivity(m.getInActivity());
        return dto;
    }
}
