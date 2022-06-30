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

    private static final String CURSO_DISCIPLINA_NAO_ENCONTRADA = "Curso e Disciplina não encontrada";

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

    @Transactional
    public void update(@Valid CursoDisciplinaDTO cursoDisciplinaDTO) {
        log.debug("Updating CursoDisciplinaDTO: {}", cursoDisciplinaDTO);
        if (Objects.isNull(cursoDisciplinaDTO.getId())) {
            throw new ServiceException("Id não encontrado");
        }
        CursoDisciplina cursoDisciplina =
                cursoDisciplinaRepository.findByIdOptional(cursoDisciplinaDTO.getId())
                .orElseThrow(() -> new ServiceException(" Não foram encontrados Cursos e Disciplinas correspondente ao Id[%s]", cursoDisciplinaDTO.getId()));
        cursoDisciplinaMapper.updateModelFromDTO(cursoDisciplinaDTO, cursoDisciplina);
        cursoDisciplinaRepository.persist(cursoDisciplina);
        cursoDisciplinaMapper.updateDTOFromModel(cursoDisciplina, cursoDisciplinaDTO);
    }

    @Transactional
    public void excluir(Long id){
        var cursoDisciplina  = cursoDisciplinaRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(CURSO_DISCIPLINA_NAO_ENCONTRADA));
        cursoDisciplinaRepository.delete(cursoDisciplina);
    }
}




