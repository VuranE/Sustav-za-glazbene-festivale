package infsus.SGF.Controller;

import infsus.SGF.Model.Enum.TipAktivnosti;
import infsus.SGF.Service.TipAktivnostiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tip-aktivnosti")
public class TipAktivnostiController {

    private final TipAktivnostiService tipAktivnostiService;

    public TipAktivnostiController(TipAktivnostiService tipAktivnostiService) {
        this.tipAktivnostiService = tipAktivnostiService;
    }


    @PostMapping("/novi-tip")
    public ResponseEntity<TipAktivnosti> newType(@RequestBody TipAktivnosti newActivityType){
        return ResponseEntity.ok(tipAktivnostiService.newActivityType(newActivityType));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipAktivnosti>> all(){
        return ResponseEntity.ok(tipAktivnostiService.fetchAll());
    }
}
