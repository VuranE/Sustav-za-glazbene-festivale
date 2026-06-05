package infsus.SGF.Service;

import infsus.SGF.DTO.LoginResponse;

public interface AuthService {

    LoginResponse login(String username, String password);
}
