package org.barros.modules.service;


import org.barros.modules.core.IdDto;
import org.barros.modules.dto.request.ImagemRequestDTO;
import org.barros.modules.dto.response.ImagemResponseDTO;
import org.barros.modules.model.Imagem;
import org.barros.modules.repository.CursoRepository;
import org.barros.modules.repository.ImagemRepository;
import org.barros.modules.s3.MinioSendFile;
import org.barros.modules.s3.MinioService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ImagemService implements IImagemService {

    @Inject
    ImagemRepository imagemRepository;

    @Inject
    MinioService minioService;

    @Inject
    CursoRepository cursoRepository;

    @ConfigProperty(name = "minio.bucket-name")
    String bucketName;

    @Transactional
    @Override
    public IdDto uploadFoto(Long cursoId, ImagemRequestDTO imagemRequestDTO) {
        if (imagemRequestDTO.getFoto() == null)
            throw new ValidationException("Foto não informada");
        var extensao = imagemRequestDTO.getFileName().substring(imagemRequestDTO.getFileName().indexOf("."));
        imagemRequestDTO.setFileName(UUID.randomUUID().toString().concat(extensao));
        minioService.enviarArquivo(MinioSendFile.builder()
                .bucket(bucketName)
                .file(imagemRequestDTO.getFoto())
                .name(imagemRequestDTO.getFileName())
                .contentType(imagemRequestDTO.getContentType())
                .build());
        Imagem imagem = new Imagem();
        imagem.setImBucket(bucketName);
        imagem.setImHash(imagemRequestDTO.getFileName());
        imagem.setCurso(cursoRepository.findByIdOptional(cursoId).orElseThrow(() -> new NotFoundException("Curso não encontrado")));
        imagemRepository.persist(imagem);
        return new IdDto(imagem.getImId());
    }

    @Override
    public List<ImagemResponseDTO> buscarFotos(Long cursoId) {
        List<ImagemResponseDTO> fotos = new ArrayList<>();
        var curso = cursoRepository.findByIdOptional(cursoId).orElseThrow(() -> new NotFoundException("Curso não encontrado!"));
        curso.getImagens().forEach(foto -> fotos.add(minioService.buscarArquivo(foto.getImBucket(), foto.getImHash())));
        return fotos;
    }

    @Override
    public ImagemResponseDTO buscarFoto(Long fotoId) {
        var foto = imagemRepository.findByIdOptional(fotoId).orElseThrow(() -> new NotFoundException("Foto não encontrada"));
        return minioService.buscarArquivo(foto.getImBucket(), foto.getImHash());
    }
}
