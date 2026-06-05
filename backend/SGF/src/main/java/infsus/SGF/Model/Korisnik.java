package infsus.SGF.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idkorisnik;

    @Column
    private String imekorisnik;

    @Column
    private String lozinka;

    @Column
    private int idtipkorisnika; // 1 admin 2 posjetitelj 3 organizator 4 osoblje

    public Long getIdkorisnik() {
        return idkorisnik;
    }

    public void setIdkorisnik(Long idkorisnik) {
        this.idkorisnik = idkorisnik;
    }

    public String getImekorisnik() {
        return imekorisnik;
    }

    public void setImekorisnik(String imekorisnik) {
        this.imekorisnik = imekorisnik;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getIdtipkorisnika() {
        return idtipkorisnika;
    }

    public void setIdtipkorisnika(int idtipkorisnika) {
        this.idtipkorisnika = idtipkorisnika;
    }
}
