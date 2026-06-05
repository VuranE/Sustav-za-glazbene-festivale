package infsus.SGF.Controller;

import infsus.SGF.DTO.CreateNotificationRequest;
import infsus.SGF.Model.Obavijest;
import infsus.SGF.Service.ObavijestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class ObavijestController {

    private final ObavijestService obavijestService;

    public ObavijestController(ObavijestService obavijestService) {
        this.obavijestService = obavijestService;
    }

    @PostMapping
    public ResponseEntity<Obavijest> createNotification(@RequestBody CreateNotificationRequest request) {
        Obavijest obavijest =
                obavijestService.createNotification(request);

        return ResponseEntity.ok(obavijest);
    }

    @GetMapping("/{userType}")
    public List<Obavijest> getNotifications(
            @PathVariable Integer userType) {

        return obavijestService.getNotificationsForUserType(
                userType);
    }
}
