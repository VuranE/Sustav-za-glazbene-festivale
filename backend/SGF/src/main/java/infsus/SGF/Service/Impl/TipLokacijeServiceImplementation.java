package infsus.SGF.Service.Impl;

import infsus.SGF.Model.Enum.TipLokacije;
import infsus.SGF.Repository.TipLokacijeRepository;
import infsus.SGF.Service.TipLokacijeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipLokacijeServiceImplementation implements TipLokacijeService {

    private final TipLokacijeRepository tipLokacijeRepository;

    public TipLokacijeServiceImplementation(TipLokacijeRepository tipLokacijeRepository) {
        this.tipLokacijeRepository = tipLokacijeRepository;
    }

    @Override
    public TipLokacije newLocationType(TipLokacije newLocationType) {
        return tipLokacijeRepository.save(newLocationType);
    }

    @Override
    public List<TipLokacije> fetchAll() {
        return tipLokacijeRepository.findAll();
    }
}
