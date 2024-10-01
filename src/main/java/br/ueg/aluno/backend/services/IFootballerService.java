package br.ueg.aluno.backend.services;

import br.ueg.aluno.backend.models.FootballerModel;

import java.util.List;

public interface IFootballerService {
    FootballerModel create(FootballerModel f);
    List<FootballerModel> getAll();
    FootballerModel getById(long id);
    FootballerModel updateById(FootballerModel f);
    FootballerModel deleteById(long id);
    void validateBusinessLogic(FootballerModel f);
    FootballerModel validateIdExists(long id);
    FootballerModel internalGet(long id);
    void updateDataDB(FootballerModel f, FootballerModel fUpdate);
}
