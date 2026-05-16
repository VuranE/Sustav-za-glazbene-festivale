package infsus.SGF.Repository;

import infsus.SGF.Model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
    List<Festival> findAll();

    Optional<Festival> findById(Long id);

    Optional<Festival> findByNazivFestivala(String name);
}
