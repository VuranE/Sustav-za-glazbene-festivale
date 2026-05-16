package infsus.SGF.Service;

import infsus.SGF.Model.Enum.TipAktivnosti;

import java.util.List;

public interface TipAktivnostiService {
    TipAktivnosti newActivityType(TipAktivnosti newActivityType);

    List<TipAktivnosti> fetchAll();
}
