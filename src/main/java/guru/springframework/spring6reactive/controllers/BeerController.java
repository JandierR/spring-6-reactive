package guru.springframework.spring6reactive.controllers;

import guru.springframework.spring6reactive.model.BeerDTO;
import guru.springframework.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @DeleteMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable Integer beerId) {
        return beerService.deleteBeerById(beerId)
                .map(response -> ResponseEntity.noContent().build());
    }

    @PatchMapping(BEER_PATH_ID)
    Mono<BeerDTO> patchExistingBeer(@PathVariable Integer beerId,
                                    @RequestBody BeerDTO beerDTO) {

//        beerService.patchBeer(beerId, beerDTO).subscribe();

        return beerService.patchBeer(beerId, beerDTO);
//        return ResponseEntity.ok().build();

    }

    @PutMapping(BEER_PATH_ID)
    ResponseEntity<Void> updateExistingBeer(@PathVariable Integer beerId,
                                                  @RequestBody BeerDTO beerDTO) {
         beerService.updateBeer(beerId, beerDTO).subscribe();

        return ResponseEntity.ok().build();
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> createNewBeer(@RequestBody BeerDTO beerDTO) {
       return beerService.saveNewBeer(beerDTO)
               .map(savedDto-> ResponseEntity.created(UriComponentsBuilder
                       .fromUriString("http://localhost:8080" + BEER_PATH
                               + "/" + savedDto.getId())
                       .build().toUri())
                       .build());
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }
}
