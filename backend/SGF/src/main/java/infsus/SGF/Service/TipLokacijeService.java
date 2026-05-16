package infsus.SGF.Service;

import infsus.SGF.Model.Enum.TipLokacije;

import java.util.List;

public interface TipLokacijeService {
    TipLokacije newLocationType(TipLokacije newLocationType);

    List<TipLokacije> fetchAll();
}
