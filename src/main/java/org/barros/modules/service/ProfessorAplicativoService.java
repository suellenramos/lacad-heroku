package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ProfessorAplicativoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.ProfessorAplicativoMapper;
import org.barros.modules.model.ConteudoAplicativo;
import org.barros.modules.model.ProfessorAplicativo;
import org.barros.modules.repository.*;

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
public class ProfessorAplicativoService {

    private static final String PROFESSOR_APLICATIVO_NAO_ENCONTRADO = "Professor e Aplicativo não encontrado";

    private final ProfessorAplicativoService professorAplicativoService;

    private final ProfessorAplicativoMapper professorAplicativoMapper;

    @Inject
    ProfessorAplicativoRepository professorAplicativoRepository;

    @Inject
    private ProfessorRepository professorRepository;

    @Inject
    private AplicativoRepository aplicativoRepository;

    public List<ProfessorAplicativoDTO> findAll() {
        return this.professorAplicativoMapper.toDTOList(professorAplicativoRepository.findAll().list());
    }

    public Optional<ProfessorAplicativoDTO> findById(@NonNull Long id) {
        return professorAplicativoRepository.findByIdOptional(id)
                .map(professorAplicativoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid ProfessorAplicativoDTO professorAplicativoDTO) {
        log.debug("Saving ProfessorAplicativoDTO: {}", professorAplicativoDTO);
        ProfessorAplicativo professorAplicativo = professorAplicativoMapper.toModel(professorAplicativoDTO);
        professorAplicativo.setProfessor(professorRepository.findById(professorAplicativoDTO.getProfId()));
        professorAplicativo.setAplicativo(aplicativoRepository.findById(professorAplicativoDTO.getApliId()));
        professorAplicativoRepository.persist(professorAplicativo);
        professorAplicativoMapper.updateDTOFromModel (professorAplicativo, professorAplicativoDTO);
    }

    @Transactional
    public void update(@Valid ProfessorAplicativoDTO professorAplicativoDTO) {
        log.debug("Updating PROFESSORAPLICATIVODTO: {}", professorAplicativoDTO);
        if (Objects.isNull(professorAplicativoDTO.getId())) {
            throw new ServiceException("Id não encontrado");
        }
        ProfessorAplicativo professorAplicativo =
                professorAplicativoRepository.findByIdOptional(professorAplicativoDTO.getId())
                .orElseThrow(() -> new ServiceException(" Não foram encontradoo Professor  e Aplicativo correspondente ao Id[%s]", professorAplicativoDTO.getId()));
        professorAplicativoMapper.updateModelFromDTO(professorAplicativoDTO, professorAplicativo);
        professorAplicativoRepository.persist(professorAplicativo);
        professorAplicativoMapper.updateDTOFromModel(professorAplicativo, professorAplicativoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var professorAplicativo  = professorAplicativoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(PROFESSOR_APLICATIVO_NAO_ENCONTRADO));
        professorAplicativoRepository.delete(professorAplicativo);
    }
}




