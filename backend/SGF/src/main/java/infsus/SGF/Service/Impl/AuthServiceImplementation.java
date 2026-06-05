package infsus.SGF.Service.Impl;

import infsus.SGF.DTO.LoginResponse;
import infsus.SGF.Model.Korisnik;
import infsus.SGF.Repository.KorisnikRepository;
import infsus.SGF.Service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthService {

    private final KorisnikRepository korisnikRepository;

    public AuthServiceImplementation(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public LoginResponse login(String username, String password) {

        Optional<Korisnik> korisnik =
                korisnikRepository.findByImekorisnik(username);

        if(korisnik.isPresent()){
            if(korisnik.get().getLozinka().equals(password)){
                System.out.println(korisnik.get().getIdtipkorisnika());
                return new LoginResponse(true, korisnik.get().getIdtipkorisnika());
            }
        }

        return new LoginResponse(false, 0);


    }
}