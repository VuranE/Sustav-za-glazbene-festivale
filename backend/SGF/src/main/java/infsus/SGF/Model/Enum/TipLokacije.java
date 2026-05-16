package infsus.SGF.Model.Enum;
import jakarta.persistence.*;

@Entity
@Table(name = "TipLokacije")
public class TipLokacije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTip")
    private Integer idTip;

    @Column(name = "opisTip", nullable = false, unique = true)
    private String opisTip;

    public TipLokacije() {
    }

    public TipLokacije(Integer idTip, String opisTip) {
        this.idTip = idTip;
        this.opisTip = opisTip;
    }

    public Integer getIdTip() {
        return idTip;
    }

    public void setIdTip(Integer idTip) {
        this.idTip = idTip;
    }

    public String getOpisTip() {
        return opisTip;
    }

    public void setOpisTip(String opisTip) {
        this.opisTip = opisTip;
    }
}