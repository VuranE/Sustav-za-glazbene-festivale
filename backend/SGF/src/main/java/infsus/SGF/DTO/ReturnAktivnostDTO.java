package infsus.SGF.DTO;

import infsus.SGF.Model.Enum.TipAktivnosti;
import infsus.SGF.Model.Festival;
import infsus.SGF.Model.Lokacija;

import java.time.LocalDateTime;

public class ReturnAktivnostDTO {
    private Integer idAktivnost;
    private LocalDateTime vrijemeAktivnosti;
    private TipAktivnosti tipAktivnosti;
   // private Festival festival;
    private String lokacija;
    private String naziv;

    public ReturnAktivnostDTO() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getIdAktivnost() {
        return idAktivnost;
    }

    public void setIdAktivnost(Integer idAktivnost) {
        this.idAktivnost = idAktivnost;
    }

    public LocalDateTime getVrijemeAktivnosti() {
        return vrijemeAktivnosti;
    }

    public void setVrijemeAktivnosti(LocalDateTime vrijemeAktivnosti) {
        this.vrijemeAktivnosti = vrijemeAktivnosti;
    }

    public TipAktivnosti getTipAktivnosti() {
        return tipAktivnosti;
    }

    public void setTipAktivnosti(TipAktivnosti tipAktivnosti) {
        this.tipAktivnosti = tipAktivnosti;
    }

    /*public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }*/

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
