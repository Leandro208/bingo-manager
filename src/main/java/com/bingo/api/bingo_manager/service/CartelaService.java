package com.bingo.api.bingo_manager.service;

import com.bingo.api.bingo_manager.domain.CartelaNumero;
import com.bingo.api.bingo_manager.exception.CartelaNaoCriadaException;
import com.bingo.api.bingo_manager.exception.PartidaEncerradaException;
import com.bingo.api.bingo_manager.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.domain.Cartela;
import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.dto.CartelaDTO;
import com.bingo.api.bingo_manager.dto.JogadorSimplesDTO;
import com.bingo.api.bingo_manager.dto.PartidaSimplesDTO;
import com.bingo.api.bingo_manager.repository.CartelaNumeroRepository;
import com.bingo.api.bingo_manager.repository.CartelaRepository;
import com.bingo.api.bingo_manager.repository.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartelaService {

	private final CartelaRepository cartelaRepository;
	private final PartidaRepository partidaRepository;
	private final UsuarioRepository usuarioRepository;
	private final CartelaNumeroRepository cartelaNumeroRepository;
	private final ModelMapper modelMapper;
    private final int QTD_NUMEROS_CARTELA = 24;

	public CartelaService(CartelaRepository cartelaRepository, PartidaRepository partidaRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper, CartelaNumeroRepository cartelaNumeroRepository) {
		this.cartelaRepository = cartelaRepository;
		this.partidaRepository = partidaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cartelaNumeroRepository = cartelaNumeroRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional(readOnly = true)
	public List<CartelaDTO> buscarCartelasDoUsuario(Long idPartida, JwtAuthenticationToken token) {
		if (!partidaRepository.existsById(idPartida)) {
			throw new EntityNotFoundException("Partida com ID " + idPartida + " não encontrada.");
		}
		Long idUsuarioLogado = Long.parseLong(token.getName());
		List<Cartela> cartela = cartelaRepository.findAllByPartidaIdAndUserIdWithDetails(idPartida, idUsuarioLogado)
				.orElseThrow(() -> new EntityNotFoundException("Você não possui uma cartela nesta partida."));
		return cartela.stream()
                .map(c -> modelMapper.map(c, CartelaDTO.class))
                .collect(Collectors.toList());
	}

    @Transactional
    public CartelaDTO criaCartela(Long partidaId, JwtAuthenticationToken token) {
        Cartela cartela = new Cartela();
        Partida partida = partidaRepository.findById(partidaId).get();
        if(cartelaRepository.isLimiteAtingidoDeCartelasPorUsuario(partidaId, Long.parseLong(token.getName()))) {
            throw new CartelaNaoCriadaException("O limite de Cartelas por usuário foi atingido! Apague uma das Cartelas");
        }
        cartela.setPartida(partida);
        cartela.setUsuario(usuarioRepository.findById((Long.parseLong(token.getName()))).get());
        cartela.getUsuario().setId(Long.parseLong(token.getName()));
        cartelaRepository.save(cartela);
        return criaNumerosCartela(cartela);
    }

    public void apagarCartela(Long idCartela, Long idPartida ,JwtAuthenticationToken token) {
        if(!partidaRepository.existsById(idPartida)) {
            throw  new PartidaEncerradaException("Partida inexistente");
        }
        if(!cartelaRepository.existsById(idCartela)) {
            throw new CartelaNaoCriadaException("Cartela não encontrada.");
        }
        cartelaRepository.deleteById(idCartela);
    }

    private CartelaDTO criaNumerosCartela(Cartela cartela){
        List<CartelaNumero> listaNumeros = new ArrayList<>();
        List<Integer> numerosGerados = sorteiaNumerosCartela();
        Collections.sort(numerosGerados);
        int hashCode = numerosGerados.hashCode();
        while(cartelaRepository.containsByHashCodeAndPartidaId(hashCode, cartela.getPartida().getId())){
            numerosGerados = sorteiaNumerosCartela();
        }
        System.out.println("Numeros Gerados : " + numerosGerados);
        for (int i = 0; i < QTD_NUMEROS_CARTELA; i++) {
            CartelaNumero cartelaNumero = new CartelaNumero();
            cartelaNumero.setCartela(cartela);
            cartelaNumero.setMarcado(false);
            cartelaNumero.setNumero(numerosGerados.get(i));
            listaNumeros.add(cartelaNumero);
        }
        cartela.setHashCodeNumeros(hashCode);
        cartelaNumeroRepository.saveAll(listaNumeros);
        return modelMapper.map(cartela, CartelaDTO.class);
    }

    private List<Integer> sorteiaNumerosCartela(){
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);
        return numeros.subList(0, QTD_NUMEROS_CARTELA);
    }
}
