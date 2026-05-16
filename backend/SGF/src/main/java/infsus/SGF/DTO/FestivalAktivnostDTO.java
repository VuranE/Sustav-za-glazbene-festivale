package infsus.SGF.DTO;

import infsus.SGF.Model.Mapa;

import java.time.LocalDateTime;
import java.util.List;

public class FestivalAktivnostDTO {
    private Long id;
    private String nazivFestivala;
    private Integer dobnaSkupina;
    private LocalDateTime vrijemePocetka;
    private LocalDateTime vrijemeKraja;
    private String lokacijaFestivala;
    private Long mapaID;
    private List<ReturnAktivnostDTO> aktivnosti;

    public FestivalAktivnostDTO() {
    }

    public List<ReturnAktivnostDTO> getAktivnosti() {
        return aktivnosti;
    }

    public void setAktivnosti(List<ReturnAktivnostDTO> aktivnosti) {
        this.aktivnosti = aktivnosti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivFestivala() {
        return nazivFestivala;
    }

    public void setNazivFestivala(String nazivFestivala) {
        this.nazivFestivala = nazivFestivala;
    }

    public Integer getDobnaSkupina() {
        return dobnaSkupina;
    }

    public void setDobnaSkupina(Integer dobnaSkupina) {
        this.dobnaSkupina = dobnaSkupina;
    }

    public LocalDateTime getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(LocalDateTime vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public LocalDateTime getVrijemeKraja() {
        return vrijemeKraja;
    }

    public void setVrijemeKraja(LocalDateTime vrijemeKraja) {
        this.vrijemeKraja = vrijemeKraja;
    }

    public String getLokacijaFestivala() {
        return lokacijaFestivala;
    }

    public void setLokacijaFestivala(String lokacijaFestivala) {
        this.lokacijaFestivala = lokacijaFestivala;
    }

    public Long getMapaID() {
        return mapaID;
    }

    public void setMapaID(Long mapaID) {
        this.mapaID = mapaID;
    }
}
