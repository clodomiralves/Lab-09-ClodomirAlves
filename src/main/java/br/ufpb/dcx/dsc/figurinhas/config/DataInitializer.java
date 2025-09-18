package br.ufpb.dcx.dsc.figurinhas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.ufpb.dcx.dsc.figurinhas.models.User;
import br.ufpb.dcx.dsc.figurinhas.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
            }
        };
    }
}

