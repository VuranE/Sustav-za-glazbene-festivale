package infsus.SGF.DTO;

import infsus.SGF.Model.Enum.TipAktivnosti;
import infsus.SGF.Model.Festival;
import infsus.SGF.Model.Lokacija;

import java.time.LocalDateTime;

public class CreateAktivnostDTO {


    private LocalDateTime vrijemeAktivnosti;
    private Long tipAktivnostiID;
    private Long festivalID;
    private Long lokacijaID;
    private String naziv;

    public CreateAktivnostDTO() {
    }

    public LocalDateTime getVrijemeAktivnosti() {
        return vrijemeAktivnosti;
    }

    public void setVrijemeAktivnosti(LocalDateTime vrijemeAktivnosti) {
        this.vrijemeAktivnosti = vrijemeAktivnosti;
    }

    public Long getTipAktivnostiID() {
        return tipAktivnostiID;
    }

    public void setTipAktivnostiID(Long tipAktivnostiID) {
        this.tipAktivnostiID = tipAktivnostiID;
    }

    public Long getFestivalID() {
        return festivalID;
    }

    public void setFestivalID(Long festivalID) {
        this.festivalID = festivalID;
    }

    public Long getLokacijaID() {
        return lokacijaID;
    }

    public void setLokacijaID(Long lokacijaID) {
        this.lokacijaID = lokacijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
