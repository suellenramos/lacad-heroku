package org.barros.modules.service;

import org.barros.modules.core.IdDto;
import org.barros.modules.dto.request.ImagemRequestDTO;
import org.barros.modules.dto.response.ImagemResponseDTO;

import java.util.List;

public interface IImagemService {

    IdDto uploadFoto(Long aplicativoId, ImagemRequestDTO imagemRequestDTO);

    ImagemResponseDTO buscarFotoByAplicativo(Long aplicativoId);

    ImagemResponseDTO buscarFoto(Long fotoId);
}
