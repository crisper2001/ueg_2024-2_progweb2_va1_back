package br.ueg.aluno.backend.repositories;

import br.ueg.aluno.backend.models.FootballerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballerRepository extends JpaRepository<FootballerModel, Long> {
}
