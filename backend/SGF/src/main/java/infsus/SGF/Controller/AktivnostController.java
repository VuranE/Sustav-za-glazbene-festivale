package infsus.SGF.Controller;

import infsus.SGF.DTO.CreateAktivnostDTO;
import infsus.SGF.DTO.ReturnAktivnostDTO;
import infsus.SGF.Service.AktivnostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aktivnost")
public class AktivnostController {

    private final AktivnostService aktivnostService;

    public AktivnostController(AktivnostService aktivnostService) {
        this.aktivnostService = aktivnostService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnAktivnostDTO> getAktivnostByID(@PathVariable Long id){
        return ResponseEntity.ok(aktivnostService.fetchAktivnostByID(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ReturnAktivnostDTO> getFestivalByName(@PathVariable String name){
        return ResponseEntity.ok(aktivnostService.fetchAktivnostByName(name));
    }


    @PostMapping("/create-aktivnost")
    public ResponseEntity<ReturnAktivnostDTO> newFestival(@RequestBody CreateAktivnostDTO newAktivnost){
        return ResponseEntity.ok(aktivnostService.createAktivnost(newAktivnost));
    }

    @PatchMapping("/update-aktivnost")
    public ResponseEntity<ReturnAktivnostDTO> updateAktivnost(@RequestBody CreateAktivnostDTO updatedAktivnost){
        return ResponseEntity.ok(aktivnostService.updateAktivnost(updatedAktivnost));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ReturnAktivnostDTO> deleteAktivnostByID(@PathVariable Long id){
        return ResponseEntity.ok(aktivnostService.deleteAktivnostByID(id));
    }
}
