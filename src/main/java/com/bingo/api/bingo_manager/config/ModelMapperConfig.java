package com.bingo.api.bingo_manager.config;

import com.bingo.api.bingo_manager.domain.Cartela;
import com.bingo.api.bingo_manager.domain.Partida;
import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.dto.JogadorSimplesDTO;
import com.bingo.api.bingo_manager.dto.PartidaDetalhesDTO;
import com.bingo.api.bingo_manager.dto.input.UsuarioInput;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		Converter<List<Cartela>, List<JogadorSimplesDTO>> cartelasParaJogadores = context ->
				context.getSource() == null ? null : context.getSource().stream()
						.map(Cartela::getUsuario)
						.map(usuario -> mapper.map(usuario, JogadorSimplesDTO.class))
						.collect(Collectors.toList());

		mapper.typeMap(Partida.class, PartidaDetalhesDTO.class).addMappings(modelMapper ->
				modelMapper.using(cartelasParaJogadores).map(Partida::getCartelas, PartidaDetalhesDTO::setJogadores)
		);
		mapper.typeMap(UsuarioInput.class, Usuario.class)
				.addMappings(m -> m.skip(Usuario::setSenha));

		return mapper;
	}
}
