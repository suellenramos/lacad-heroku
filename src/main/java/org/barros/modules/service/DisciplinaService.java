package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.request.DisciplinaDTO;
import org.barros.modules.dto.response.DisciplinaResponseDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.DisciplinaMapper;
import org.barros.modules.model.Disciplina;
import org.barros.modules.model.Professor;
import org.barros.modules.repository.DisciplinaRepository;
import org.barros.modules.repository.ProfessorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@ApplicationScoped
public class DisciplinaService {

    private static final String DISCIPLINA_NAO_ENCONTRADA = "Disciplina não encontrada";

    private final DisciplinaRepository disciplinaRepository;

    private final DisciplinaMapper disciplinaMapper;

    @Inject
    private ProfessorRepository professorRepository;

    public List<DisciplinaResponseDTO> findAll() {
        return this.disciplinaMapper.toDTOList(disciplinaRepository.findAll().list());
    }

    public Optional<DisciplinaResponseDTO> findById(@NonNull Long id) {
        return disciplinaRepository.findByIdOptional(id)
                .map(disciplinaMapper::toDTObyId);
    }

    @Transactional
    public void save(@Valid DisciplinaDTO disciplinaDTO) {
        log.debug("Saving DisciplinaDTO: {}", disciplinaDTO);
        Disciplina disciplina = disciplinaMapper.toModel(disciplinaDTO);
        disciplinaRepository.persist(disciplina);
        disciplinaMapper.updateDTOFromModel (disciplina, disciplinaDTO);
        Professor professor = professorRepository.findById(disciplinaDTO.getProfessor().getProfId());
        professor.getDisciplinas().add(disciplina);
        professorRepository.persist(professor);

//        disciplinaRepository.persist(disciplina);
//        disciplinaMapper.updateDTOFromModel (disciplina, disciplinaDTO);
    }

    @Transactional
    public void update(@Valid DisciplinaDTO disciplinaDTO  ) {
        log.debug("Updating DisciplinaDTO: {}", disciplinaDTO);
        if (Objects.isNull(disciplinaDTO.getDiscId())) {
            throw new ServiceException("Id não encontrado");
        }
        Disciplina disciplina =
                disciplinaRepository.findByIdOptional(disciplinaDTO.getDiscId())
                .orElseThrow(() -> new ServiceException(" Disciplina  não econtrada pelo Id[%s]", disciplinaDTO.getDiscId()));
        disciplinaMapper.updateModelFromDTO(disciplinaDTO, disciplina);
        disciplinaRepository.persist(disciplina);
        disciplinaMapper.updateDTOFromModel(disciplina, disciplinaDTO);
    }

    @Transactional
    public void excluir(Long id){
        var disciplina  = disciplinaRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(DISCIPLINA_NAO_ENCONTRADA));
        disciplinaRepository.delete(disciplina);
    }
}




