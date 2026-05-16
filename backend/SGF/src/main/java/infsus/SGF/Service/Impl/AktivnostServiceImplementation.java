package infsus.SGF.Service.Impl;

import infsus.SGF.DTO.CreateAktivnostDTO;
import infsus.SGF.DTO.FestivalDTO;
import infsus.SGF.DTO.ReturnAktivnostDTO;
import infsus.SGF.Model.Aktivnost;
import infsus.SGF.Model.Enum.TipAktivnosti;
import infsus.SGF.Model.Festival;
import infsus.SGF.Model.Lokacija;
import infsus.SGF.Repository.AktivnostRepostitory;
import infsus.SGF.Repository.Exception.InputIsNullException;
import infsus.SGF.Repository.FestivalRepository;
import infsus.SGF.Repository.LokacijaRepository;
import infsus.SGF.Repository.TipAktivnostiRepository;
import infsus.SGF.Service.AktivnostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AktivnostServiceImplementation implements AktivnostService {

    private final AktivnostRepostitory aktivnostRepository;
    private final FestivalRepository festivalRepository;
    private final LokacijaRepository lokacijaRepository;
    private final TipAktivnostiRepository tipAktivnostiRepository;

    public AktivnostServiceImplementation(AktivnostRepostitory aktivnostRepostitory, FestivalRepository festivalRepository, LokacijaRepository lokacijaRepository, TipAktivnostiRepository tipAktivnostiRepository) {
        this.aktivnostRepository = aktivnostRepostitory;
        this.festivalRepository = festivalRepository;
        this.lokacijaRepository = lokacijaRepository;
        this.tipAktivnostiRepository = tipAktivnostiRepository;
    }

    @Override
    public ReturnAktivnostDTO fetchAktivnostByID(Long id) {
        Optional<Aktivnost> akt = aktivnostRepository.findById(id);
        if(akt.isPresent()){
            ReturnAktivnostDTO dto = new ReturnAktivnostDTO();
            return dtoFillInfoAktivnost(dto, akt.get());
        }
        else {
            throw new InputIsNullException("ne postoji aktivnost s tim ID-jem");
        }
    }

    @Override
    public ReturnAktivnostDTO fetchAktivnostByName(String name) {
        Optional<Aktivnost> akt = aktivnostRepository.findByNazivAktivnosti(name);
        if(akt.isPresent()){
            ReturnAktivnostDTO dto = new ReturnAktivnostDTO();
            return dtoFillInfoAktivnost(dto, akt.get());
        }
        else {
            throw new InputIsNullException("ne postoji aktivnost s tim nazivom");
        }
    }

    @Override
    public ReturnAktivnostDTO createAktivnost(CreateAktivnostDTO newAktivnost) {
        Aktivnost akt = new Aktivnost();
        akt.setNazivAktivnosti(newAktivnost.getNaziv());
        akt.setVrijemeAktivnosti(newAktivnost.getVrijemeAktivnosti());

        Optional<Festival> fest = festivalRepository.findById(newAktivnost.getFestivalID());
        Optional<Lokacija> lok = lokacijaRepository.findById(newAktivnost.getLokacijaID());
        Optional<TipAktivnosti> tip = tipAktivnostiRepository.findById(newAktivnost.getTipAktivnostiID());

        if(fest.isPresent() && lok.isPresent() && tip.isPresent()){
            akt.setFestival(fest.get());
            akt.setLokacija(lok.get());
            akt.setTipAktivnosti(tip.get());

            aktivnostRepository.save(akt);
            ReturnAktivnostDTO dto = new ReturnAktivnostDTO();
            return dtoFillInfoAktivnost(dto, akt);
        } else {
            throw new InputIsNullException("U bazi ne postoji neki od podataka");
        }

    }

    @Override
    public ReturnAktivnostDTO updateAktivnost(CreateAktivnostDTO updatedAktivnost) {
        Optional<Aktivnost> akt = aktivnostRepository.findByNazivAktivnosti(updatedAktivnost.getNaziv());
        if(akt.isPresent()){
            Aktivnost a = akt.get();
            a.setVrijemeAktivnosti(updatedAktivnost.getVrijemeAktivnosti());
            a.setNazivAktivnosti(updatedAktivnost.getNaziv());
            Optional<Festival> fest = festivalRepository.findById(updatedAktivnost.getFestivalID());
            Optional<Lokacija> lok = lokacijaRepository.findById(updatedAktivnost.getLokacijaID());
            Optional<TipAktivnosti> tip = tipAktivnostiRepository.findById(updatedAktivnost.getTipAktivnostiID());

            if(fest.isPresent() && lok.isPresent() && tip.isPresent()){
                a.setFestival(fest.get());
                a.setLokacija(lok.get());
                a.setTipAktivnosti(tip.get());

                aktivnostRepository.save(a);
                //TODO: Promijeni
                return dtoFillInfoAktivnost(new ReturnAktivnostDTO(), a);
            } else {
                throw new InputIsNullException("U bazi ne postoji neki od podataka");
            }
        }
        else{
            throw new InputIsNullException("ne postoji taj festival u bazi");
        }
    }

    @Override
    public ReturnAktivnostDTO deleteAktivnostByID(Long id) {
        Optional<Aktivnost> akt = aktivnostRepository.findById(id);

        if(akt.isPresent()){
            ReturnAktivnostDTO dto = new ReturnAktivnostDTO();
            aktivnostRepository.delete(akt.get());
            return dtoFillInfoAktivnost(dto, akt.get());
        }
        else {
            throw new InputIsNullException("Ne postoji aktivnost s tim ID-jem");
        }
    }


    public ReturnAktivnostDTO dtoFillInfoAktivnost(ReturnAktivnostDTO dto, Aktivnost akt){
        dto.setIdAktivnost(akt.getIdAktivnost());
        dto.setVrijemeAktivnosti(akt.getVrijemeAktivnosti());
        dto.setTipAktivnosti(akt.getTipAktivnosti());
       // dto.setFestival(akt.getFestival());
        dto.setLokacija(akt.getLokacija().getOpisLok());
        dto.setNaziv(akt.getNazivAktivnosti());
        return dto;
    }
}
