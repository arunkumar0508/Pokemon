package com.pokemon.Pokemon.Controller;
import com.pokemon.Pokemon.domain.Pokemon;
import com.pokemon.Pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable String id) {
        return pokemonService.getPokemonById(id);
    }
    @GetMapping("/external/{id}")
    public Pokemon fetchPokemonFromExternalAPI(@PathVariable String id) {
        return pokemonService.fetchPokemonFromExternalAPI(Integer.parseInt(id));
    }

    @PostMapping("/fetch/{id}")
    public void fetchAndSavePokemon(@PathVariable String id) {
        // Parse the String id to an int
        int pokemonId = Integer.parseInt(id);

        RestTemplate restTemplate = new RestTemplate();
        String pokeApiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        ResponseEntity<String> response = restTemplate.getForEntity(pokeApiUrl, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String pokemonDetails = response.getBody();
            pokemonService.savePokemonToDatabase(pokemonId, pokemonDetails);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable String id, @RequestBody Pokemon updatedPokemon) {
        Pokemon updated = pokemonService.updatePokemon(id, updatedPokemon);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable String id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }
}
