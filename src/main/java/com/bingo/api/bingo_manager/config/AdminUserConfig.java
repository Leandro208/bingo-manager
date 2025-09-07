package com.bingo.api.bingo_manager.config;

import com.bingo.api.bingo_manager.domain.Role;
import com.bingo.api.bingo_manager.domain.Usuario;
import com.bingo.api.bingo_manager.repository.RoleRepository;
import com.bingo.api.bingo_manager.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminUserConfig.class);

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByNome(Role.Values.ADMIN.name());
        var userAdmin = usuarioRepository.findByEmail("admin@gmail.com");

        userAdmin.ifPresentOrElse(
                user -> {
                    log.info("Usuário admin já existe");
                    },
                () -> {
                    var user = new Usuario();
                    user.setEmail("admin@gmail.com");
                    user.setNome(Role.Values.ADMIN.name());
                    user.setSenha(passwordEncoder.encode("123456"));
                    user.setRoles(Set.of(roleAdmin));
                    usuarioRepository.save(user);
                    log.info("Usuário admin criado com sucesso");
                }
        );
    }
}
