package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    // Get all manga
    @GetMapping
    public List<Manga> getAllManga() {
        return mangaRepository.findAll();
    }
    @GetMapping("/")
    public String home() {
        return "Welcome to the Manga Store API!";
    }


    // Get manga by ID
    @GetMapping("/{id}")
    public Optional<Manga> getMangaById(@PathVariable Long id) {
        return mangaRepository.findById(id);
    }

    // Add new manga
    @PostMapping
    public Manga createManga(@RequestBody Manga manga) {
        return mangaRepository.save(manga);
    }

    // Update manga by ID
    @PutMapping("/{id}")
    public Manga updateManga(@PathVariable Long id, @RequestBody Manga updatedManga) {
        return mangaRepository.findById(id).map(manga -> {
            manga.setTitle(updatedManga.getTitle());
            manga.setAuthor(updatedManga.getAuthor());
            manga.setGenre(updatedManga.getGenre());
            manga.setPrice(updatedManga.getPrice());
            return mangaRepository.save(manga);
        }).orElseThrow(() -> new RuntimeException("Manga not found!"));
    }

    // Delete manga by ID
    @DeleteMapping("/{id}")
    public String deleteManga(@PathVariable Long id) {
        mangaRepository.deleteById(id);
        return "Manga deleted successfully!";
    }
}
