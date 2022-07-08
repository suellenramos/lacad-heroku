package org.barros.modules.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.model.Curso;

import javax.enterprise.context.ApplicationScoped;

@AllArgsConstructor
@Slf4j
@ApplicationScoped
public class CursoService {
//
//    private static final String CURSO_NAO_ENCONTRADO = "Curso não encontrado";
//
//    private final CursoRepository cursoRepository;
//
//    private final CursoMapper cursoMapper;
//
//    public List<CursoDTO> findAll() {
//        return this.cursoMapper.toDTOList(cursoRepository.findAll().list());
//    }
//
//    public Optional<CursoDTO> findById(@NonNull Long id) {
//        return cursoRepository.findByIdOptional(id)
//                .map(cursoMapper::toDTO);
//    }
//
//    @Transactional
//    public void saveCurso(@Valid CursoDTO cursoDTO) {
//        log.debug("Saving CursoDTO: {}", cursoDTO);
//        Curso curso = cursoMapper.toModel(cursoDTO);
//        cursoRepository.persist(curso);
//        cursoMapper.updateDTOFromModel(curso, cursoDTO);
//    }
//
//    @Transactional
//    public void update(@Valid CursoDTO cursoDTO) {
//        log.debug("Updating CursoDTO: {}", cursoDTO);
//        if (Objects.isNull(cursoDTO.getCurId())) {
//            throw new ServiceException("Id não encontrado");
//        }
//        Curso curso =
//                cursoRepository.findByIdOptional(cursoDTO.getCurId())
//                .orElseThrow(() -> new ServiceException("Curso não econtrado pelo Id[%s]", cursoDTO.getCurId()));
//        cursoMapper.updateModelFromDTO(cursoDTO, curso);
//        cursoRepository.persist(curso);
//        cursoMapper.updateDTOFromModel(curso, cursoDTO);
//    }
//
//    @Transactional
//    public void excluir(Long id){
//        var curso  = cursoRepository.findByIdOptional(id)
//                .orElseThrow(()-> new NotFoundException(CURSO_NAO_ENCONTRADO));
//        cursoRepository.delete(curso);
//    }
}




