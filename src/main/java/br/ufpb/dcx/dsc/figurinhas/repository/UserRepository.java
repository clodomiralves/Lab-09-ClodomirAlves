package br.ufpb.dcx.dsc.figurinhas.repository;

import br.ufpb.dcx.dsc.figurinhas.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}