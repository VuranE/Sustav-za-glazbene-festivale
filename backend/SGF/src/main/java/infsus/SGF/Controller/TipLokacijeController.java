package infsus.SGF.Controller;

import infsus.SGF.Model.Enum.TipAktivnosti;
import infsus.SGF.Model.Enum.TipLokacije;
import infsus.SGF.Service.TipLokacijeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tip-lokacije")
public class TipLokacijeController {
    private final TipLokacijeService tipLokacijeService;

    public TipLokacijeController(TipLokacijeService tipLokacijeService) {
        this.tipLokacijeService = tipLokacijeService;
    }


    @PostMapping("/novi-tip")
    public ResponseEntity<TipLokacije> newType(@RequestBody TipLokacije newLocationType){
        return ResponseEntity.ok(tipLokacijeService.newLocationType(newLocationType));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipLokacije>> all(){
        return ResponseEntity.ok(tipLokacijeService.fetchAll());
    }
}
