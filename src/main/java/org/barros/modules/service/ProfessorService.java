package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.ProfessorMapper;
import org.barros.modules.model.Professor;
import org.barros.modules.repository.ProfessorRepository;
import org.barros.modules.security.service.PBKDF2Encoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@ApplicationScoped
public class ProfessorService implements IProfessorService {

    private static final String PROFESSOR_NAO_ENCONTRADO = "Professor não encontrado";

    private final ProfessorRepository professorRepository;

   // private final ProfessorMapper professorMapper;
    @Inject
    IProfessorService iProfessorService;


    @Inject
    ProfessorMapper professorMapper;

    @Inject
    PBKDF2Encoder passwordEncoder;

    @Override
    @Transactional
    public void insert(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toResource(professorDTO);
        professor.setPassword(passwordEncoder.encode(professorDTO.getPassword()));

    }

    //@Override
    public Professor findByNomeAndSenha(String login, String senha) {
        return professorRepository.findByLoginSenha(login, senha);
    }

//    @Override
//    public List<ProfessorDTO> listaProfessores() {
//        List<Professor> professores = professorRepository.findAll().list();
//        if (professores != null && !professores.isEmpty()) {
//            List<ProfessorDTO> resultado = new ArrayList<>();
//            for (Professor profs : professores) {
//                ProfessorDTO dto = professorMapper.toDTO(profs);
//                resultado.add(dto);
//            }
//            return resultado;
//        } else {
//            return null;
//        }
//    }
//        public List<ProfessorDTO> findAll() {
//            return this.professorMapper.toDTOList(professorRepository.findAll().list());
//        }
//
//        public Optional<ProfessorDTO> findById (@NonNull Long id){
//            return professorRepository.findByIdOptional(id)
//                    .map(professorMapper::toDTO);
//        }

//        @Transactional
//        public void save (@Valid ProfessorDTO professorDTO){
//            log.debug("Saving ProfessorDTO: {}", professorDTO);
//            Professor professor = professorMapper.toModel(professorDTO);
//            professorRepository.persist(professor);
//            professorMapper.updateDTOFromModel(professor, professorDTO);
//        }

//        @Transactional
//        public void update (@Valid ProfessorDTO professorDTO){
//            log.debug("Updating ProfessorDTO: {}", professorDTO);
//            if (Objects.isNull(professorDTO.getProfId())) {
//                throw new ServiceException("Id não encontrado");
//            }
//            Professor professor =
//                    professorRepository.findByIdOptional(professorDTO.getProfId())
//                            .orElseThrow(() -> new ServiceException("Professor não econtrado pelo Id[%s]", professorDTO.getProfId()));
//            professorMapper.updateModelFromDTO(professorDTO, professor);
//            professorRepository.persist(professor);
//            professorMapper.updateDTOFromModel(professor, professorDTO);
//        }
//
//        @Transactional
//        public void excluir (Long id){
//            var professor = professorRepository.findByIdOptional(id)
//                    .orElseThrow(() -> new NotFoundException(PROFESSOR_NAO_ENCONTRADO));
//            professorRepository.delete(professor);
//        }
}


