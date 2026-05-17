package infsus.SGF.repository;


import infsus.SGF.Model.Festival;
import infsus.SGF.Model.Mapa;
import infsus.SGF.Repository.FestivalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class FestivalRepositoryTest {

    @Autowired
    private FestivalRepository festivalRepository;

    private Festival buildFestival() {
        Festival f = new Festival();
        f.setNazivFestivala("Ultra Festival");
        f.setDobnaSkupina(18);
        f.setLokacijaFestivala("Split");
        f.setMapa(new Mapa());
        f.setVrijemeKraja(LocalDateTime.of(2026, 7, 1, 10, 0));
        f.setVrijemePocetka(LocalDateTime.of(2026, 7, 5, 10, 0));
        return f;
    }

    @Test
    @DisplayName("save should persist Festival")
    void save_shouldPersistFestival() {

        Festival festival = buildFestival();

        Festival saved = festivalRepository.save(festival);

        assertThat(saved.getIdFestival()).isNotNull();

        Optional<Festival> found =
                festivalRepository.findById(saved.getIdFestival().longValue());

        assertThat(found).isPresent();
        assertThat(found.get().getNazivFestivala())
                .isEqualTo("Ultra Festival");
    }

    @Test
    @DisplayName("delete should remove Festival")
    void delete_shouldRemoveFestival() {

        Festival festival = festivalRepository.save(buildFestival());

        Long id = festival.getIdFestival().longValue();

        assertThat(festivalRepository.findById(id)).isPresent();

        festivalRepository.deleteById(id);

        assertThat(festivalRepository.findById(id)).isNotPresent();
    }
}
