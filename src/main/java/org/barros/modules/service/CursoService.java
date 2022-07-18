package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.request.CursoDTO;
import org.barros.modules.dto.response.CursoResponseDTO;
import org.barros.modules.exception.NotFoundException;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.CursoMapper;
import org.barros.modules.model.Curso;
import org.barros.modules.model.Professor;
import org.barros.modules.repository.CursoRepository;
import org.barros.modules.repository.ProfessorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@ApplicationScoped
public class CursoService {

    private static final String CURSO_NAO_ENCONTRADO = "Curso não encontrado";

    private final CursoRepository cursoRepository;

    private final CursoMapper cursoMapper;

    @Inject
    ProfessorRepository professorRepository;

    public List<CursoResponseDTO> findAll() {
        return this.cursoMapper.toDTOList(cursoRepository.findAll().list());
    }

    public Optional<CursoDTO> findById(@NonNull Long id) {
        return cursoRepository.findByIdOptional(id)
                .map(cursoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid CursoDTO cursoDTO) {
        log.debug("Saving CursoDTO: {}", cursoDTO);
        Curso curso = cursoMapper.toModel(cursoDTO);
        cursoRepository.persist(curso);
        Professor professor = professorRepository.findById(cursoDTO.getProfessor().getProfId());
        professor.getCursos().add(curso);
        professorRepository.persist(professor);
    }

    @Transactional
    public void update(@Valid CursoDTO cursoDTO) {
        log.debug("Updating CursoDTO: {}", cursoDTO);
        if (Objects.isNull(cursoDTO.getCurId())) {
            throw new ServiceException("Id não encontrado");
        }
        Curso curso =
                cursoRepository.findByIdOptional(cursoDTO.getCurId())
                .orElseThrow(() -> new ServiceException("Curso não econtrado pelo Id[%s]", cursoDTO.getCurId()));
        cursoMapper.updateModelFromDTO(cursoDTO, curso);
        cursoRepository.persist(curso);
        cursoMapper.updateDTOFromModel(curso, cursoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var curso  = cursoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(CURSO_NAO_ENCONTRADO));
        cursoRepository.delete(curso);
    }
}




