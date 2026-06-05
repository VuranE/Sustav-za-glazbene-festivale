package infsus.SGF.Controller;

import infsus.SGF.DTO.LoginDTO;
import infsus.SGF.DTO.LoginResponse;
import infsus.SGF.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginDTO request) {

        return authService.login(
                request.getUsername(),
                request.getPassword()
        );
    }
}