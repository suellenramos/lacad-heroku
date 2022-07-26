package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.FavoritoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.FavoritoMapper;
import org.barros.modules.model.Favorito;
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
public class FavoritoService {

    private static final String FAVORITO_NAO_ENCONTRADO = "Favorito não encontrado";


    private final FavoritoMapper favoritoMapper;

    private final FavoritoRepository favoritoRepository     ;


    @Inject
    private AplicativoRepository aplicativoRepository;

    @Inject
    private ProfessorRepository professorRepository;

    public List<FavoritoDTO> findAll() {
        return this.favoritoMapper.toDTOList(favoritoRepository.findAll().list());
    }

    public Optional<FavoritoDTO> findById(@NonNull Long id) {
        return favoritoRepository.findByIdOptional(id)
                .map(favoritoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid FavoritoDTO favoritoDTO) {
        log.debug("Saving FavoritoDTO: {}", favoritoDTO);
        Favorito favorito = favoritoMapper.toModel(favoritoDTO);
        favorito.setProfessor(professorRepository.findById(favoritoDTO.getProfId()));
        favorito.setAplicativo(aplicativoRepository.findById(favoritoDTO.getApliId()));
        favoritoRepository.persist(favorito);
        favoritoMapper.updateDTOFromModel (favorito, favoritoDTO);
    }

    @Transactional
    public void excluir(Long id){
        var favorito  = favoritoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(FAVORITO_NAO_ENCONTRADO));
        favoritoRepository.delete(favorito);
    }
}




