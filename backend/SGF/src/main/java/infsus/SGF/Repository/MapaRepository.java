package infsus.SGF.Repository;

import infsus.SGF.Model.Mapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long> {
}
