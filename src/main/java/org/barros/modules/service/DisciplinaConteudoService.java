package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.DisciplinaConteudoDTO;
import org.barros.modules.mapper.DisciplinaConteudoMapper;
import org.barros.modules.model.DisciplinaConteudo;
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
public class DisciplinaConteudoService {

    private static final String DISCIPLINA_CONTEUDO_NAO_ENCONTRADO = "Disciplina e Conteudo não encontrado";

    private final DisciplinaConteudoMapper disciplinaConteudoMapper;

    private final DisciplinaConteudoRepository disciplinaConteudoRepository;


    @Inject
    private  CursoDisciplinaRepository cursoDisciplinaRepository;

    @Inject
    private ConteudoRepository conteudoRepository;

    public List<DisciplinaConteudoDTO> findAll() {
        return this.disciplinaConteudoMapper.toDTOList(disciplinaConteudoRepository.findAll().list());
    }

    public Optional<DisciplinaConteudoDTO> findById(@NonNull Long id) {
        return disciplinaConteudoRepository.findByIdOptional(id)
                .map(disciplinaConteudoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid DisciplinaConteudoDTO disciplinaConteudoDTO) {
        log.debug("Saving DisciplinaConteudoDTO: {}", disciplinaConteudoDTO);
        DisciplinaConteudo disciplinaConteudo = disciplinaConteudoMapper.toModel(disciplinaConteudoDTO);
      //  disciplinaConteudo.setConteudo(conteudoRepository.findById(disciplinaConteudoDTO.getConteId()));
        disciplinaConteudo.setCursoDisciplina(cursoDisciplinaRepository.findById(disciplinaConteudoDTO.getCdID()));
        disciplinaConteudoRepository.persist(disciplinaConteudo);
        disciplinaConteudoMapper.updateDTOFromModel (disciplinaConteudo, disciplinaConteudoDTO);
    }

//    @Transactional
//    public void update(@Valid DisciplinaConteudoDTO disciplinaConteudoDTO  ) {
//        log.debug("Updating DisciplinaConteudoDTO: {}", disciplinaConteudoDTO);
//        if (Objects.isNull(disciplinaConteudoDTO.getId())) {
//            throw new ServiceException("Id não encontrado");
//        }
//        DisciplinaConteudo disciplinaConteudo =
//                disciplinaConteudoRepository.findByIdOptional(disciplinaConteudoDTO.getId())
//                .orElseThrow(() -> new ServiceException(" Não foram encontrados Disciplinas e Conteúdos correspondente ao Id[%s]", disciplinaConteudoDTO.getId()));
//        disciplinaConteudoMapper.updateModelFromDTO(disciplinaConteudoDTO, disciplinaConteudo);
//        disciplinaConteudoRepository.persist(disciplinaConteudo);
//        disciplinaConteudoMapper.updateDTOFromModel(disciplinaConteudo, disciplinaConteudoDTO);
//    }

    @Transactional
    public void excluir(Long id){
        var disciplinaConteudo  = disciplinaConteudoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(DISCIPLINA_CONTEUDO_NAO_ENCONTRADO));
        disciplinaConteudoRepository.delete(disciplinaConteudo);
    }
}




