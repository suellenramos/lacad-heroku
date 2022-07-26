package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AvaliacaoDTO;
import org.barros.modules.dto.response.ConteudoAplicativoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.ConteudoAplicativoMapper;
import org.barros.modules.model.Avaliacao;
import org.barros.modules.model.ConteudoAplicativo;
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
public class ConteudoAplicativoService {

    private static final String CONTEUDO_APLICATIVO_NAO_ENCONTRADO = "Conteudo e Aplicativo não encontrado";


    private final ConteudoAplicativoMapper conteudoAplicativoMapper;

    private final ConteudoAplicativoRepository conteudoAplicativoRepository;


    @Inject
    private AplicativoRepository aplicativoRepository;

    @Inject
    private  ConteudoRepository conteudoRepository;

    public List<ConteudoAplicativoDTO> findAll() {
        return this.conteudoAplicativoMapper.toDTOList(conteudoAplicativoRepository.findAll().list());
    }

    public Optional<ConteudoAplicativoDTO> findById(@NonNull Long id) {
        return conteudoAplicativoRepository.findByIdOptional(id)
                .map(conteudoAplicativoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid ConteudoAplicativoDTO conteudoAplicativoDTO) {
        log.debug("Saving ConteudoAplicativoDTO: {}", conteudoAplicativoDTO);
        ConteudoAplicativo conteudoAplicativo = conteudoAplicativoMapper.toModel(conteudoAplicativoDTO);
        conteudoAplicativo.setConteudo(conteudoRepository.findById(conteudoAplicativoDTO.getConteId()));
        conteudoAplicativo.setAplicativo(aplicativoRepository.findById(conteudoAplicativoDTO.getApliId()));
        conteudoAplicativoRepository.persist(conteudoAplicativo);
        conteudoAplicativoMapper.updateDTOFromModel (conteudoAplicativo, conteudoAplicativoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var conteudoAplicativo  = conteudoAplicativoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(CONTEUDO_APLICATIVO_NAO_ENCONTRADO));
        conteudoAplicativoRepository.delete(conteudoAplicativo);
    }
}




