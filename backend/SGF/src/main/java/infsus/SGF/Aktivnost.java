package infsus.SGF;

import infsus.SGF.Model.Enum.TipAktivnosti;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "aktivnost")
public class Aktivnost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaktivnost")
    private Integer idAktivnost;

    @Column(name = "vrijemeaktivnosti", nullable = false)
    private LocalDateTime vrijemeAktivnosti;

    @Enumerated(EnumType.STRING)
    @Column(name = "idtipaktivnosti", nullable = false)
    private TipAktivnosti tipAktivnosti;

    @ManyToOne
    @JoinColumn(name = "idfestival")
    private Festival festival;

    @ManyToOne
    @JoinColumn(name = "idlokacija")
    private Lokacija lokacija;

    public Aktivnost() {
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

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}