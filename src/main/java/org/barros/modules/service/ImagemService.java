package org.barros.modules.service;


import org.barros.modules.core.IdDto;
import org.barros.modules.dto.request.ImagemRequestDTO;
import org.barros.modules.dto.response.ImagemResponseDTO;
import org.barros.modules.model.Imagem;
import org.barros.modules.repository.AplicativoRepository;
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
    AplicativoRepository aplicativoRepository;

    @ConfigProperty(name = "minio.bucket-name")
    String bucketName;

    @Transactional
    @Override
    public IdDto uploadFoto(Long aplicativoId, ImagemRequestDTO imagemRequestDTO) {
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
        imagem.setAplicativo(aplicativoRepository.findByIdOptional(aplicativoId).orElseThrow(() -> new NotFoundException("Aplicativo não encontrado")));
        imagemRepository.persist(imagem);
        return new IdDto(imagem.getImId());
    }

    @Override
    public ImagemResponseDTO buscarFotoByAplicativo(Long aplicativoId) {
        var aplicativo = aplicativoRepository.findByIdOptional(aplicativoId).orElseThrow(() -> new NotFoundException("Aplicativo não encontrado!"));
        var imagem =  imagemRepository.find("aplicativo.apliId = ?1", aplicativoId).singleResult();
        return   minioService.buscarArquivo(imagem.getImBucket(), imagem.getImHash());
    }

    @Override
    public ImagemResponseDTO buscarFoto(Long fotoId) {
        var foto = imagemRepository.findByIdOptional(fotoId).orElseThrow(() -> new NotFoundException("Foto não encontrada"));
        return minioService.buscarArquivo(foto.getImBucket(), foto.getImHash());
    }
}
