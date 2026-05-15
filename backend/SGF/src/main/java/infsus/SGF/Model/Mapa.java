package infsus.SGF.Model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.MultiPolygon;

@Entity
@Table(name = "mapa")
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmape")
    private Integer idMape;

    @Lob
    @Column(name = "slika")
    private byte[] slika;

    @Column(
            name = "oblik",
            nullable = false,
            columnDefinition = "geometry(MultiPolygon,4326)"
    )
    private MultiPolygon oblik;

    public Mapa() {
    }

    public Mapa(Integer idMape, byte[] slika, MultiPolygon oblik) {
        this.idMape = idMape;
        this.slika = slika;
        this.oblik = oblik;
    }

    public Integer getIdMape() {
        return idMape;
    }

    public void setIdMape(Integer idMape) {
        this.idMape = idMape;
    }

    public byte[] getSlika() {
        return slika;
    }

    public void setSlika(byte[] slika) {
        this.slika = slika;
    }

    public MultiPolygon getOblik() {
        return oblik;
    }

    public void setOblik(MultiPolygon oblik) {
        this.oblik = oblik;
    }
}