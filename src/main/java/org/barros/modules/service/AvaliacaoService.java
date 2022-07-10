package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AvaliacaoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.AvaliacaoMapper;
import org.barros.modules.model.Avaliacao;
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
public class AvaliacaoService {

    private static final String AVALIACAO_NAO_ENCONTRADA = "Avaliacao não encontrada";

    private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoMapper avaliacaoMapper;

    @Inject
    private ProfessorRepository professorRepository;

    @Inject
    AplicativoRepository aplicativoRepository;

    public List<AvaliacaoDTO> findAll() {
        return this.avaliacaoMapper.toDTOList(avaliacaoRepository.findAll().list());
    }

    public Optional<AvaliacaoDTO> findById(@NonNull Long id) {
        return avaliacaoRepository.findByIdOptional(id)
                .map(avaliacaoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid AvaliacaoDTO avaliacaoDTO) {
        log.debug("Saving AvaliacaoTO: {}", avaliacaoDTO);
        Avaliacao avaliacao = avaliacaoMapper.toModel(avaliacaoDTO);
        avaliacao.setProfessor(professorRepository.findById(avaliacaoDTO.getProfId()));
        avaliacao.setAplicativo(aplicativoRepository.findById(avaliacaoDTO.getApliId()));
        avaliacaoRepository.persist(avaliacao);
        avaliacaoMapper.updateDTOFromModel (avaliacao, avaliacaoDTO);
    }

    @Transactional
    public void update(@Valid AvaliacaoDTO  avaliacaoDTO ) {
        log.debug("Updating AvaliacaoDTO: {}", avaliacaoDTO);
        if (Objects.isNull(avaliacaoDTO.getAvaId())) {
            throw new ServiceException("Id não encontrado");
        }
         Avaliacao avaliacao =
                avaliacaoRepository.findByIdOptional(avaliacaoDTO.getAvaId())
                .orElseThrow(() -> new ServiceException(" Avaliacao  não econtrada pelo Id[%s]", avaliacaoDTO.getAvaId()));
        avaliacaoMapper.updateModelFromDTO(avaliacaoDTO, avaliacao);
        avaliacaoRepository.persist(avaliacao);
        avaliacaoMapper.updateDTOFromModel(avaliacao, avaliacaoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var avaliacao  = avaliacaoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(AVALIACAO_NAO_ENCONTRADA));
        avaliacaoRepository.delete(avaliacao);
    }
}




