package br.ueg.aluno.backend.services;

import br.ueg.aluno.backend.exceptions.CustomException;
import br.ueg.aluno.backend.models.FootballerModel;
import br.ueg.aluno.backend.repositories.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FootballerService implements IFootballerService {

    @Autowired
    private FootballerRepository repository;

    @Override
    public FootballerModel create(FootballerModel f) {
        f.setId(null);
        validateBusinessLogic(f);
        return repository.save(f);
    }

    @Override
    public List<FootballerModel> getAll() {
        return repository.findAll();
    }

    @Override
    public FootballerModel getById(long id) {
        return validateIdExists(id);
    }

    @Override
    public FootballerModel updateById(FootballerModel fUpdate) {
        FootballerModel f = validateIdExists(fUpdate.getId());
        validateBusinessLogic(fUpdate);
        updateDataDB(f, fUpdate);
        return repository.save(f);
    }

    @Override
    public FootballerModel deleteById(long id) {
        FootballerModel f = validateIdExists(id);
        repository.delete(f);
        return f;
    }

    @Override
    public void validateBusinessLogic(FootballerModel f) {
        if (!f.getClub().isEmpty() && !f.getInActivity()) {
            throw new CustomException("ERRO: Um jogador inativo não pode estar atualmente em um clube.");
        }
    }

    @Override
    public FootballerModel validateIdExists(long id) {
        FootballerModel f = null;
        if (Objects.nonNull(id)) {
            f = this.internalGet(id);
        }
        if (f == null) {
            throw new CustomException("ERRO: Não existe um jogador com o ID informado.");
        }
        return f;
    }

    @Override
    public FootballerModel internalGet(long id) {
        Optional<FootballerModel> f = repository.findById(id);
        return f.orElse(null);
    }

    @Override
    public void updateDataDB(FootballerModel f, FootballerModel fUpdate) {
        f.setName(fUpdate.getName());
        f.setBirthday(fUpdate.getBirthday());
        f.setBirthplace(fUpdate.getBirthplace());
        f.setPosition(fUpdate.getPosition());
        f.setClub(fUpdate.getClub());
        f.setInActivity(fUpdate.getInActivity());
    }

}
