package infsus.SGF.Repository;

import infsus.SGF.Model.Obavijest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObavijestRepository extends JpaRepository<Obavijest, Integer> {
    List<Obavijest> findByTargetGroup(String targetGroup);
}