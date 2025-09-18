package br.ufpb.dcx.dsc.figurinhas.repository;

import br.ufpb.dcx.dsc.figurinhas.models.Figurinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigurinhaRepository extends JpaRepository<Figurinha, Long> {
}
