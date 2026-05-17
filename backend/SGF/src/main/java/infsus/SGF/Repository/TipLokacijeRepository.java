package infsus.SGF.Repository;

import infsus.SGF.Model.Enum.TipLokacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipLokacijeRepository extends JpaRepository<TipLokacije, Long> {
}
