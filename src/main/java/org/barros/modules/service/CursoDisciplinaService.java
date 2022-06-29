package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.CursoDisciplinaDTO;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.CursoDisciplinaMapper;
import org.barros.modules.model.CursoDisciplina;
import org.barros.modules.model.Disciplina;
import org.barros.modules.repository.CursoDisciplinaRepository;
import org.barros.modules.repository.CursoRepository;
import org.barros.modules.repository.DisciplinaRepository;

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
public class CursoDisciplinaService {

    private static final String CURSO_DISCIPLINA_NAO_ENCONTRADA = "Curso e Disciplina não encontrado";

    private final CursoDisciplinaService cursoDisciplinaService;

    private final CursoDisciplinaMapper cursoDisciplinaMapper;

    @Inject
    CursoDisciplinaRepository cursoDisciplinaRepository;

    @Inject
    private CursoRepository cursoRepository;

    @Inject
    private DisciplinaRepository disciplinaRepository;

    public List<CursoDisciplinaDTO> findAll() {
        return this.cursoDisciplinaMapper.toDTOList(cursoDisciplinaRepository.findAll().list());
    }

    public Optional<CursoDisciplinaDTO> findById(@NonNull Long id) {
        return cursoDisciplinaRepository.findByIdOptional(id)
                .map(cursoDisciplinaMapper::toDTO);
    }

    @Transactional
    public void save(@Valid CursoDisciplinaDTO cursoDisciplinaDTO) {
        log.debug("Saving CursoDisciplinaDTO: {}", cursoDisciplinaDTO);
        CursoDisciplina cursoDisciplina = cursoDisciplinaMapper.toModel(cursoDisciplinaDTO);
        cursoDisciplina.setCurso(cursoRepository.findById(cursoDisciplinaDTO.getCurId()));
        cursoDisciplina.setDisciplina(disciplinaRepository.findById(cursoDisciplinaDTO.getDiscId()));
        cursoDisciplinaRepository.persist(cursoDisciplina);
        cursoDisciplinaMapper.updateDTOFromModel (cursoDisciplina, cursoDisciplinaDTO);
    }

//    @Transactional
//    public void update(@Valid DisciplinaDTO disciplinaDTO  ) {
//        log.debug("Updating DisciplinaDTO: {}", disciplinaDTO);
//        if (Objects.isNull(disciplinaDTO.getDiscId())) {
//            throw new ServiceException("Id não encontrado");
//        }
//        Disciplina disciplina =
//                disciplinaRepository.findByIdOptional(disciplinaDTO.getDiscId())
//                .orElseThrow(() -> new ServiceException(" Disciplina  não econtrada pelo Id[%s]", disciplinaDTO.getDiscId()));
//        disciplinaMapper.updateModelFromDTO(disciplinaDTO, disciplina);
//        disciplinaRepository.persist(disciplina);
//        disciplinaMapper.updateDTOFromModel(disciplina, disciplinaDTO);
//    }
//
//    @Transactional
//    public void excluir(Long id){
//        var disciplina  = disciplinaRepository.findByIdOptional(id)
//                .orElseThrow(()-> new NotFoundException(DISCIPLINA_NAO_ENCONTRADA));
//        disciplinaRepository.delete(disciplina);
//    }
}




