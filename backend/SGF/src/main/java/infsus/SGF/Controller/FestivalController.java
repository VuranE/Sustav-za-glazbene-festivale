package infsus.SGF.Controller;

import infsus.SGF.DTO.FestivalAktivnostDTO;
import infsus.SGF.DTO.FestivalDTO;
import infsus.SGF.Service.FestivalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/festival")
public class FestivalController {

    private final FestivalService festivalService;


    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FestivalDTO>> getAllFestivals(){
        return ResponseEntity.ok(festivalService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FestivalDTO> getFestivalByID(@PathVariable Long id){
        return ResponseEntity.ok(festivalService.fetchFestivalByID(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<FestivalDTO> getFestivalByName(@PathVariable String name){
        return ResponseEntity.ok(festivalService.fetchFestivalByName(name));
    }


    @PostMapping("/create-festival")
    public ResponseEntity<FestivalDTO> newFestival(@RequestBody FestivalDTO newFestival){
        return ResponseEntity.ok(festivalService.createFestival(newFestival));
    }

    @PatchMapping("/update-festival")
    public ResponseEntity<FestivalDTO> updateFestival(@RequestBody FestivalDTO updatedFestival){
        return ResponseEntity.ok(festivalService.updateFestival(updatedFestival));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FestivalDTO> deleteFestivalByID(@PathVariable Long id){
        return ResponseEntity.ok(festivalService.deleteFestivalByID(id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<FestivalAktivnostDTO> getFestivalDetailsByID(@PathVariable Long id){
        return ResponseEntity.ok(festivalService.fetchFestivalDetailsByID(id));
    }

}
