package infsus.SGF.Service;

import infsus.SGF.DTO.FestivalAktivnostDTO;
import infsus.SGF.DTO.FestivalDTO;

import java.util.List;

public interface FestivalService {

    List<FestivalDTO> fetchAll();

    FestivalDTO fetchFestivalByID(Long id);

    FestivalDTO createFestival(FestivalDTO newFestival);

    FestivalDTO updateFestival(FestivalDTO updatedFestival);

    FestivalDTO fetchFestivalByName(String name);

    FestivalDTO deleteFestivalByID(Long id);

    FestivalAktivnostDTO fetchFestivalDetailsByID(Long id);
}
