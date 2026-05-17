package infsus.SGF.Service;

import infsus.SGF.DTO.CreateAktivnostDTO;
import infsus.SGF.DTO.ReturnAktivnostDTO;

public interface AktivnostService {
    ReturnAktivnostDTO fetchAktivnostByID(Long id);

    ReturnAktivnostDTO fetchAktivnostByName(String name);

    ReturnAktivnostDTO createAktivnost(CreateAktivnostDTO newAktivnost);

    ReturnAktivnostDTO updateAktivnost(CreateAktivnostDTO updatedAktivnost);

    ReturnAktivnostDTO deleteAktivnostByID(Long id);
}
