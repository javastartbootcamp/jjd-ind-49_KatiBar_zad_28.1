package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public List<OfferDto> findFiltered(@RequestParam(required = false) Long categoryId,
                                       @RequestParam(required = false) String title) {

        return offerService.findFiltered(categoryId, title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findById(@PathVariable Long id) {
        Optional<OfferDto> offerDtoOptional = offerService.findById(id);
        return offerDtoOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public int countOffers() {
        return offerService.findAllOffers().size();
    }

    @PostMapping
    public ResponseEntity<OfferDto> addOffer(@RequestBody OfferInsertDto offerInsertDto) {
        OfferDto offerDto = offerService.insert(offerInsertDto);
        return ResponseEntity.ok(offerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> addOfferDto(@PathVariable Long id, @RequestBody OfferInsertDto offerInsertDto) {
        return offerService.update(id, offerInsertDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        offerService.deleteById(id);
    }
}
