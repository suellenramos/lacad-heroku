package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AplicativoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.AplicativoMapper;
import org.barros.modules.model.*;
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
public class AplicativoService {

    private static final String APLICATIVO_NAO_ENCONTRADO = "Aplicativo não encontrado";

    private final AplicativoRepository aplicativoRepository;

    private final AplicativoMapper aplicativoMapper;

    @Inject
    ConteudoRepository conteudoRepository;

    public List<AplicativoDTO> findAll() {
        return this.aplicativoMapper.toDTOList(aplicativoRepository.findAll().list());
    }

    public Optional<AplicativoDTO> findById(@NonNull Long id) {
        return aplicativoRepository.findByIdOptional(id)
                .map(aplicativoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid AplicativoDTO aplicativoDTO) {
        log.debug("Saving AplicativoDTO: {}", aplicativoDTO);
        Aplicativo aplicativo = aplicativoMapper.toModel(aplicativoDTO);
        aplicativoRepository.persist(aplicativo);
        aplicativoMapper.updateDTOFromModel (aplicativo, aplicativoDTO);
    }

    @Transactional
    public void update(@Valid AplicativoDTO aplicativoDTO  ) {
        log.debug("Updating AplicativoDTO: {}", aplicativoDTO);
        if (Objects.isNull(aplicativoDTO.getApliId())) {
            throw new ServiceException("Id não encontrado");
        }
        Aplicativo aplicativo =
                aplicativoRepository.findByIdOptional(aplicativoDTO.getApliId())
                .orElseThrow(() -> new ServiceException(" Aplicativo  não econtrado pelo Id[%s]", aplicativoDTO.getApliId()));
        aplicativoMapper.updateModelFromDTO(aplicativoDTO, aplicativo);
        aplicativoRepository.persist(aplicativo);
        aplicativoMapper.updateDTOFromModel(aplicativo, aplicativoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var aplcativo  = aplicativoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(APLICATIVO_NAO_ENCONTRADO));
        aplicativoRepository.delete(aplcativo);
    }
}




