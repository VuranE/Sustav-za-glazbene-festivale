package infsus.SGF.Repository;

import infsus.SGF.Model.Enum.TipAktivnosti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipAktivnostiRepository extends JpaRepository<TipAktivnosti, Long> {
}
