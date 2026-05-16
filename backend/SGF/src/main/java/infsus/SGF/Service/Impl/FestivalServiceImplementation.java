package infsus.SGF.Service.Impl;

import infsus.SGF.DTO.FestivalAktivnostDTO;
import infsus.SGF.DTO.FestivalDTO;
import infsus.SGF.DTO.ReturnAktivnostDTO;
import infsus.SGF.Model.Aktivnost;
import infsus.SGF.Model.Festival;
import infsus.SGF.Repository.AktivnostRepostitory;
import infsus.SGF.Repository.Exception.InputIsNullException;
import infsus.SGF.Repository.FestivalRepository;
import infsus.SGF.Service.AktivnostService;
import infsus.SGF.Service.FestivalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FestivalServiceImplementation implements FestivalService {

    private final FestivalRepository festivalRepository;
    private final AktivnostRepostitory aktivnostRepostitory;
    private final AktivnostServiceImplementation aktivnostServiceImplementation;

    public FestivalServiceImplementation(FestivalRepository festivalRepository, AktivnostRepostitory aktivnostRepostitory, AktivnostServiceImplementation aktivnostServiceImplementation) {
        this.festivalRepository = festivalRepository;
        this.aktivnostRepostitory = aktivnostRepostitory;
        this.aktivnostServiceImplementation = aktivnostServiceImplementation;
    }

    @Override
    public List<FestivalDTO> fetchAll() {
        List<Festival> list = festivalRepository.findAll();
        List<FestivalDTO> dtoList = new ArrayList<>();
        for(Festival fest : list){
            FestivalDTO dto = new FestivalDTO();
            dtoFillInfo(dto, fest);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public FestivalDTO fetchFestivalByID(Long id) {
        Optional<Festival> fest = festivalRepository.findById(id);
        if(fest.isPresent()){
            FestivalDTO dto = new FestivalDTO();
            return dtoFillInfo(dto, fest.get());
        }
        else {
           throw new InputIsNullException("ne postoji festival s tim ID-jem");
        }
    }

    @Override
    public FestivalDTO createFestival(FestivalDTO newFestival) {
        Festival fest = new Festival();
        fest.setDobnaSkupina(newFestival.getDobnaSkupina());
        fest.setMapa(newFestival.getMapa());
        fest.setLokacijaFestivala(newFestival.getLokacijaFestivala());
        fest.setVrijemeKraja(newFestival.getVrijemeKraja());
        fest.setNazivFestivala(newFestival.getNazivFestivala());
        fest.setVrijemePocetka(newFestival.getVrijemePocetka());
        festivalRepository.save(fest);
        //TODO: PROMIJENI
        return newFestival;
    }



    @Override
    public FestivalDTO updateFestival(FestivalDTO updatedFestival) {
        Optional<Festival> fest = festivalRepository.findByNazivFestivala(updatedFestival.getNazivFestivala());
        if(fest.isPresent()){
            Festival f = fest.get();
            f.setVrijemePocetka(updatedFestival.getVrijemePocetka());
            f.setMapa(updatedFestival.getMapa());
            f.setNazivFestivala(updatedFestival.getNazivFestivala());
            f.setVrijemeKraja(updatedFestival.getVrijemeKraja());
            f.setLokacijaFestivala(updatedFestival.getLokacijaFestivala());
            f.setDobnaSkupina(updatedFestival.getDobnaSkupina());
            festivalRepository.save(f);
            //TODO: Promijeni
            return updatedFestival;
        }
        else{
            throw new InputIsNullException("ne postoji taj festival u bazi");
        }

    }

    @Override
    public FestivalDTO fetchFestivalByName(String name) {
        Optional<Festival> fest = festivalRepository.findByNazivFestivala(name);
        if(fest.isPresent()){
            FestivalDTO dto = new FestivalDTO();
            return dtoFillInfo(dto, fest.get());
        }
        else {
            throw new InputIsNullException("ne postoji festival s tim nazivom");
        }

    }

    @Override
    public FestivalDTO deleteFestivalByID(Long id) {

        Optional<Festival> fest = festivalRepository.findById(id);

        if(fest.isPresent()){
            FestivalDTO dto = new FestivalDTO();
            festivalRepository.delete(fest.get());
            return dtoFillInfo(dto, fest.get());
        }
        else {
            throw new InputIsNullException("Ne postoji festival s tim ID-jem");
        }
    }

    @Override
    public FestivalAktivnostDTO fetchFestivalDetailsByID(Long id) {
        Optional<Festival> fest = festivalRepository.findById(id);
        if(fest.isPresent()){
            FestivalAktivnostDTO details = new FestivalAktivnostDTO();
            details.setId(fest.get().getIdFestival().longValue());
            details.setNazivFestivala(fest.get().getNazivFestivala());
            details.setDobnaSkupina(fest.get().getDobnaSkupina());
            details.setLokacijaFestivala(fest.get().getLokacijaFestivala());
            details.setVrijemeKraja(fest.get().getVrijemeKraja());
            details.setVrijemePocetka(fest.get().getVrijemePocetka());
            details.setMapa(fest.get().getMapa());

            List<Aktivnost> aktivnosti = aktivnostRepostitory.findAllByFestival(fest.get());
            List<ReturnAktivnostDTO> dtos = new ArrayList<>();
            for(Aktivnost a : aktivnosti){
                ReturnAktivnostDTO dto = new ReturnAktivnostDTO();
                dtos.add(aktivnostServiceImplementation.dtoFillInfoAktivnost(dto, a));

            }

            details.setAktivnosti(dtos);

            return details;

        }
        else {
            throw new InputIsNullException("ne postoji festival s tim ID-jem");
        }
    }

    private FestivalDTO dtoFillInfo(FestivalDTO dto, Festival fest){
        dto.setId(fest.getIdFestival().longValue());
        dto.setNazivFestivala(fest.getNazivFestivala());
        dto.setDobnaSkupina(fest.getDobnaSkupina());
        dto.setLokacijaFestivala(fest.getLokacijaFestivala());
        dto.setVrijemeKraja(fest.getVrijemeKraja());
        dto.setVrijemePocetka(fest.getVrijemePocetka());
        dto.setMapa(fest.getMapa());

        return dto;
    }
}
