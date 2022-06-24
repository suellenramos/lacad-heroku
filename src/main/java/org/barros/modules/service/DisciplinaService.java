package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.DisciplinaMapper;
import org.barros.modules.model.Conteudo;
import org.barros.modules.model.Curso;
import org.barros.modules.model.Disciplina;
import org.barros.modules.repository.ConteudoRepository;
import org.barros.modules.repository.CursoRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Slf4j
@ApplicationScoped
public class DisciplinaService {

    private static final String DISCIPLINA_NAO_ENCONTRADA = "Disciplina não encontrada";

    private final DisciplinaRepository disciplinaRepository;

    private final DisciplinaMapper disciplinaMapper;

    @Inject
    private ProfessorRepository professorRepository;

    @Inject
    CursoRepository cursoRepository;

    @Inject
    ConteudoRepository conteudoRepository;

    public List<DisciplinaDTO> findAll() {
        return this.disciplinaMapper.toDTOList(disciplinaRepository.findAll().list());
    }

    public Optional<DisciplinaDTO> findById(@NonNull Long id) {
        return disciplinaRepository.findByIdOptional(id)
                .map(disciplinaMapper::toDTO);
    }



    @Transactional
    public void save(@Valid DisciplinaDTO disciplinaDTO) {
        log.debug("Saving DisciplinaDTO: {}", disciplinaDTO);
        Disciplina disciplina = disciplinaMapper.toModel(disciplinaDTO);
        disciplina.setProfessor(professorRepository.findById(disciplinaDTO.getProfId()));
        var ids = Stream.of(disciplinaDTO.getCursos().split(",")).map(ass -> Long.valueOf(ass.trim())).collect(Collectors.toList());
        var cursos = disciplinaRepository.getEntityManager().createQuery("select c from Curso c where curId in(?1)", Curso.class).setParameter(1, ids).getResultStream().collect(Collectors.toList());
        disciplina.setCursos(cursos);
        var idsConteudos = Stream.of(disciplinaDTO.getConteudos().split(",")).map(ass -> Long.valueOf(ass.trim())).collect(Collectors.toList());
        var conteudos = disciplinaRepository.getEntityManager().createQuery("select c from Conteudo c where conteId in(?1)", Conteudo.class).setParameter(1, idsConteudos).getResultStream().collect(Collectors.toList());
        disciplina.setConteudos(conteudos);
        disciplinaRepository.persist(disciplina);
        disciplinaMapper.updateDTOFromModel (disciplina, disciplinaDTO);
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




