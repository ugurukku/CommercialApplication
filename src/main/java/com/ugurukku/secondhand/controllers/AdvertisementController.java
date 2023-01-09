package com.ugurukku.secondhand.controllers;


import com.ugurukku.secondhand.dto.AdDto;
import com.ugurukku.secondhand.models.Advertisement;
import com.ugurukku.secondhand.repositories.AdvertisementElasticSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/ad")
public class AdvertisementController {

    private final AdvertisementElasticSearchRepository repository;

    public AdvertisementController(AdvertisementElasticSearchRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/byTitle")
    public Page<Advertisement> getByTitle(@RequestParam(name = "title") String title) {
        System.out.println(title);
        return repository.findAdvertisementsByTitleFuzzy(title, Pageable.unpaged());

    }@GetMapping("/byDesc")
    public Page<Advertisement> getByDesc(@RequestParam(name = "desc") String desc) {
        System.out.println(desc);
        return repository.findAdvertisementByDescriptionLikeIgnoreCase(desc, Pageable.unpaged());
    }

    @PostMapping("/add")
    public ResponseEntity<Advertisement> add(@RequestBody AdDto advertisement) {
        return ResponseEntity
                .ok(repository
                        .save(
                                new Advertisement(advertisement.title(),
                                        advertisement.description(),
                                        advertisement.price())));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Advertisement>> getAll(){
        return ResponseEntity.ok(repository.findAll(Pageable.unpaged()));
    }

}
