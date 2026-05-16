package infsus.SGF.Service.Impl;

import infsus.SGF.Model.Enum.TipAktivnosti;
import infsus.SGF.Repository.TipAktivnostiRepository;
import infsus.SGF.Service.TipAktivnostiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipAktivnostiServiceImplementation implements TipAktivnostiService {

    private final TipAktivnostiRepository tipAktivnostiRepository;

    public TipAktivnostiServiceImplementation(TipAktivnostiRepository tipAktivnostiRepository) {
        this.tipAktivnostiRepository = tipAktivnostiRepository;
    }

    @Override
    public TipAktivnosti newActivityType(TipAktivnosti newActivityType) {
        return tipAktivnostiRepository.save(newActivityType);
    }

    @Override
    public List<TipAktivnosti> fetchAll() {
        return tipAktivnostiRepository.findAll();
    }
}
