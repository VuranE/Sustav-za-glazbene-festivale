package infsus.SGF.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "festival")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfestival")
    private Integer idFestival;

    @Column(name = "nazivfestivala", nullable = false, length = 100)
    private String nazivFestivala;

    @Column(name = "dobnaskupina")
    private Integer dobnaSkupina;

    @Column(name = "vrijemepocetka", nullable = false)
    private LocalDateTime vrijemePocetka;

    @Column(name = "vrijemekraja", nullable = false)
    private LocalDateTime vrijemeKraja;

    @Column(name = "lokacijafestivala", nullable = false, length = 100)
    private String lokacijaFestivala;

    @ManyToOne
    @JoinColumn(name = "idmape")
    private Mapa mapa;



    public Festival() {
    }



    public Integer getIdFestival() {
        return idFestival;
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

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
}