package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ConteudoDTO;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.ConteudoMapper;
import org.barros.modules.model.Aplicativo;
import org.barros.modules.model.Conteudo;
import org.barros.modules.model.Curso;
import org.barros.modules.model.Disciplina;
import org.barros.modules.repository.*;

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
public class ConteudoService {

    private static final String CONTEUDO_NAO_ENCONTRADO = "Conteudo não encontrado";

    private final ConteudoMapper conteudoMapper;

    private final ConteudoRepository conteudoRepository;


    public List<ConteudoDTO> findAll() {
        return this.conteudoMapper.toDTOList(conteudoRepository.findAll().list());
    }

    public Optional<ConteudoDTO> findById(@NonNull Long id) {
        return conteudoRepository.findByIdOptional(id)
                .map(conteudoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid ConteudoDTO conteudoDTO) {
        log.debug("Saving ConteudoDTO: {}", conteudoDTO);
        Conteudo conteudo = conteudoMapper.toModel(conteudoDTO);
        conteudoRepository.persist(conteudo);
        conteudoMapper.updateDTOFromModel (conteudo, conteudoDTO);
    }

    @Transactional
    public void update(@Valid ConteudoDTO conteudoDTO  ) {
        log.debug("Updating ConteudoDTO: {}", conteudoDTO);
        if (Objects.isNull(conteudoDTO.getConteId())) {
            throw new ServiceException("Id não encontrado");
        }
        Conteudo conteudo =
                conteudoRepository.findByIdOptional(conteudoDTO.getConteId())
                .orElseThrow(() -> new ServiceException(" Conteudo  não econtrado pelo Id[%s]", conteudoDTO.getConteId()));
        conteudoMapper.updateModelFromDTO(conteudoDTO, conteudo);
        conteudoRepository.persist(conteudo);
        conteudoMapper.updateDTOFromModel(conteudo, conteudoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var conteudo  = conteudoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(CONTEUDO_NAO_ENCONTRADO));
        conteudoRepository.delete(conteudo);
    }
}




