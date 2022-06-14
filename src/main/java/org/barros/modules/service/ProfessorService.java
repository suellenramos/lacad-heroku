package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.ProfessorMapper;
import org.barros.modules.model.Professor;
import org.barros.modules.repository.ProfessorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@ApplicationScoped
public class ProfessorService {

    private static final String PROFESSOR_NAO_ENCONTRADO = "Professor não encontrado";

    private final ProfessorRepository professorRepository;

    private final ProfessorMapper professorMapper;

    public List<ProfessorDTO> findAll() {
        return this.professorMapper.toDTOList(professorRepository.findAll().list());
    }

    public Optional<ProfessorDTO> findById(@NonNull Long id) {
        return professorRepository.findByIdOptional(id)
                .map(professorMapper::toDTO);
    }

    @Transactional
    public void save(@Valid ProfessorDTO professorDTO) {
        log.debug("Saving ProfessorDTO: {}", professorDTO);
        Professor professor = professorMapper.toModel(professorDTO);
        professorRepository.persist(professor);
        professorMapper.updateDTOFromModel (professor, professorDTO);
    }

    @Transactional
    public void update(@Valid ProfessorDTO professorDTO) {
        log.debug("Updating ProfessorDTO: {}", professorDTO);
        if (Objects.isNull(professorDTO.getProfId())) {
            throw new ServiceException("Id não encontrado");
        }
        Professor professor =
                professorRepository.findByIdOptional(professorDTO.getProfId())
                .orElseThrow(() -> new ServiceException("Professor não econtrado pelo Id[%s]", professorDTO.getProfId()));
        professorMapper.updateModelFromDTO(professorDTO, professor);
        professorRepository.persist(professor);
        professorMapper.updateDTOFromModel(professor, professorDTO);
    }

    @Transactional
    public void excluir(Long id){
        var professor  = professorRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(PROFESSOR_NAO_ENCONTRADO));
        professorRepository.delete(professor);
    }
}




