package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AplicativoFavoritoDTO;
import org.barros.modules.exception.NotFoundException;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.mapper.AplicativoFavoritoMapper;
import org.barros.modules.model.AplicativoFavorito;
import org.barros.modules.repository.AplicativoFavoritoRepository;
import org.barros.modules.repository.ConteudoAplicativoRepository;

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
public class AplicativoFavoritoService {

    private static final String APLICATIVO_FAVORITO_NAO_ENCONTRADO = "Aplicativo Favorito não encontrado";


    private final AplicativoFavoritoMapper aplicativoFavoritoMapper;

    private final AplicativoFavoritoRepository aplicativoFavoritoRepository;

//    @Inject
//    private ProfessorRepository professorRepository;

    @Inject
    private ConteudoAplicativoRepository conteudoAplicativoRepository;

    public List<AplicativoFavoritoDTO> findAll() {
        return this.aplicativoFavoritoMapper.toDTOList(aplicativoFavoritoRepository.findAll().list());
    }


    public Optional<AplicativoFavoritoDTO> findById(@NonNull Long id) {
        return aplicativoFavoritoRepository.findByIdOptional(id)
                .map(aplicativoFavoritoMapper::toDTO);
    }

    @Transactional
    public void save(@Valid AplicativoFavoritoDTO aplicativoFavoritoDTO) {
        log.debug("Saving AplicativoFavoritoDTO: {}", aplicativoFavoritoDTO);
        AplicativoFavorito aplicativoFavorito = aplicativoFavoritoMapper.toModel(aplicativoFavoritoDTO);
       // aplicativoFavorito.setProfessor(professorRepository.findById(aplicativoFavoritoDTO.getProfId()));
        aplicativoFavorito.setConteudoAplicativo(conteudoAplicativoRepository.findById(aplicativoFavoritoDTO.getCaID()));
        aplicativoFavoritoRepository.persist(aplicativoFavorito);
        aplicativoFavoritoMapper.updateDTOFromModel (aplicativoFavorito, aplicativoFavoritoDTO);
    }

//    @Transactional
//    public void update(@Valid AplicativoFavoritoDTO aplicativoFavoritoDTO) {
//        log.debug("Updating APLICATIVOFAVORITODTO: {}", aplicativoFavoritoDTO);
//        if (Objects.isNull(aplicativoFavoritoDTO.getId())) {
//            throw new ServiceException("Id não encontrado");
//        }
//        AplicativoFavorito aplicativoFavorito =
//                aplicativoFavoritoRepository.findByIdOptional(aplicativoFavoritoDTO.getId())
//                .orElseThrow(() -> new ServiceException(" Não foi encontrado Aplicativo Favorito correspondente ao Id[%s]", aplicativoFavoritoDTO.getId()));
//        aplicativoFavoritoMapper.updateModelFromDTO(aplicativoFavoritoDTO, aplicativoFavorito);
//        aplicativoFavoritoRepository.persist(aplicativoFavorito);
//        aplicativoFavoritoMapper.updateDTOFromModel(aplicativoFavorito, aplicativoFavoritoDTO);
//    }

    @Transactional
    public void excluir(Long id){
        var aplicativoFavorito  = aplicativoFavoritoRepository.findByIdOptional(id)
                .orElseThrow(()-> new NotFoundException(APLICATIVO_FAVORITO_NAO_ENCONTRADO));
        aplicativoFavoritoRepository.delete(aplicativoFavorito);
    }
}




