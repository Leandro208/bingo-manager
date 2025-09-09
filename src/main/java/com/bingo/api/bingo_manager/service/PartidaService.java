package com.bingo.api.bingo_manager.service;

import java.util.List;
import com.bingo.api.bingo_manager.repository.UsuarioRepository;

import com.bingo.api.bingo_manager.exception.PartidaEncerradaException;
import com.bingo.api.bingo_manager.exception.PartidaNaoIniciadaException;

import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.StatusPartida;
import com.bingo.api.bingo_manager.dto.PartidaDTO;
import com.bingo.api.bingo_manager.dto.PartidaDetalhesDTO;
import com.bingo.api.bingo_manager.dto.input.PartidaInput;
import com.bingo.api.bingo_manager.exception.PartidaNaoAguardandoException;
import com.bingo.api.bingo_manager.repository.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartidaService {

	private final PartidaRepository partidaRepository;
	private final CartelaService cartelaService;

	private final ModelMapper modelMapper;
	private final UsuarioRepository usuarioRepository;

	public PartidaService(PartidaRepository partidaRepository, ModelMapper modelMapper,
			CartelaService cartelaService, UsuarioRepository usuarioRepository) {
		this.partidaRepository = partidaRepository;
		this.cartelaService = cartelaService;
		this.modelMapper = modelMapper;
		this.usuarioRepository = usuarioRepository;
	}

	public PartidaDTO create(PartidaInput partidaInput, JwtAuthenticationToken token) {
		Partida partida = modelMapper.map(partidaInput, Partida.class);
		partida.setCriador(usuarioRepository.findById((Long.parseLong(token.getName()))).get());
		partida = partidaRepository.save(partida);
		return modelMapper.map(partida, PartidaDTO.class);
	}

	@Transactional(readOnly = true)
	public List<PartidaDetalhesDTO> findAll() {
		List<Partida> partidas = partidaRepository.findAllWithCartelas();
		if (!partidas.isEmpty()) {
			partidaRepository.findAllWithNumerosSorteados(partidas);
		}
		return partidas.stream()
				.map(partida -> modelMapper.map(partida, PartidaDetalhesDTO.class))
				.toList();
	}

	@Transactional(readOnly = true)
	public List<PartidaDetalhesDTO> findAllPartidasDisponiveis() {
		List<Partida> partidas = partidaRepository.findAllByStatusPartida(StatusPartida.AGUARDANDO);
		if (!partidas.isEmpty()) {
			partidaRepository.findAllWithNumerosSorteados(partidas);
		}
		return partidas.stream()
				.map(partida -> modelMapper.map(partida, PartidaDetalhesDTO.class))
				.toList();
	}

	public PartidaDTO iniciar(Long idPartida) {
		Partida partida = partidaRepository.findById(idPartida).orElseThrow(() -> new  EntityNotFoundException("Nenhuma partida encontrada"));
		if(!partida.isAguardando()) {
			throw new PartidaNaoAguardandoException();
		}
		partida.setStatusPartida(StatusPartida.EM_ANDAMENTO);
		return modelMapper.map(partidaRepository.save(partida), PartidaDTO.class);
	}

    public PartidaDTO encerrarPartida(Long idPartida) {
        Partida partida = partidaRepository.findById(idPartida).orElseThrow(() -> new  EntityNotFoundException("Nenhuma partida encontrada"));
        if (!partida.getStatusPartida().equals(StatusPartida.EM_ANDAMENTO)) {
            throw new PartidaNaoIniciadaException();
        }
        partida.setStatusPartida(StatusPartida.FINALIZADA);
        return modelMapper.map(partidaRepository.save(partida), PartidaDTO.class);
    }
	
	@Transactional
	public PartidaDetalhesDTO entrar(Long partidaId, JwtAuthenticationToken token) {
		if (!partidaRepository.existsById(partidaId)) {
			throw new EntityNotFoundException("Partida não encontrada");
		}
        StatusPartida statusPartida = partidaRepository.findStatusPartidaById(partidaId);

        if(statusPartida.equals(StatusPartida.EM_ANDAMENTO)) {
            throw new PartidaNaoAguardandoException("Essa partida já está em andamento");
        }
        if(statusPartida.equals(StatusPartida.FINALIZADA)) {
            throw new PartidaEncerradaException();
        }
		cartelaService.criaCartela(partidaId, token);
		return findPartidaDetalhesById(partidaId);
	}
	

	
	@Transactional(readOnly = true)
	public PartidaDetalhesDTO findPartidaDetalhesById(Long idPartida) {
		Partida partida = partidaRepository.findById(idPartida)
		        .orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));
		partida.getCartelas().iterator();
		partida.getNumerosSorteados().iterator();
		partida.getVencedores().iterator();
		return modelMapper.map(partida, PartidaDetalhesDTO.class);
	}
}
