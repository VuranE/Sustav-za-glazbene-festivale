package infsus.SGF.Model;

import infsus.SGF.Model.Enum.TipLokacije;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "lokacija")
public class Lokacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlokacija")
    private Integer idLokacija;

    @Column(nullable = false)
    private Integer kapacitet;

    @Column(
            name = "polozaj",
            nullable = false,
            columnDefinition = "geometry(Point,4326)"
    )
    private Point polozaj;

    @Column(name = "opislok", nullable = false, length = 100)
    private String opisLok;

    @Enumerated(EnumType.STRING)
    @Column(name = "idtiplok", nullable = false)
    private TipLokacije tipLokacije;

    @ManyToOne
    @JoinColumn(name = "idmape")
    private Mapa mapa;

    public Lokacija() {
    }

    public Integer getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(Integer idLokacija) {
        this.idLokacija = idLokacija;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    public Point getPolozaj() {
        return polozaj;
    }

    public void setPolozaj(Point polozaj) {
        this.polozaj = polozaj;
    }

    public String getOpisLok() {
        return opisLok;
    }

    public void setOpisLok(String opisLok) {
        this.opisLok = opisLok;
    }

    public TipLokacije getTipLokacije() {
        return tipLokacije;
    }

    public void setTipLokacije(TipLokacije tipLokacije) {
        this.tipLokacije = tipLokacije;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
}