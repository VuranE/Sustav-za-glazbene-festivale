package infsus.SGF.Repository;

import infsus.SGF.DTO.ReturnAktivnostDTO;
import infsus.SGF.Model.Aktivnost;
import infsus.SGF.Model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AktivnostRepostitory extends JpaRepository<Aktivnost, Long> {
    Optional<Aktivnost> findByNazivAktivnosti(String name);

    List<Aktivnost> findAllByFestival(Festival festival);
}
