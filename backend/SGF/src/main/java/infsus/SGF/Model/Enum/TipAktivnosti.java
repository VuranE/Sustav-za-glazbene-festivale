package infsus.SGF.Model.Enum;
import jakarta.persistence.*;

@Entity
@Table(name = "tip_aktivnosti")
public class TipAktivnosti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tip")
    private Integer idTip;

    @Column(name = "opis_tip", nullable = false, unique = true)
    private String opisTip;

    public TipAktivnosti() {
    }

    public TipAktivnosti(Integer idTip, String opisTip) {
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