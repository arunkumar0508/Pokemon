package com.pokemon.Pokemon.service;
import com.pokemon.Pokemon.domain.Pokemon;
import com.pokemon.Pokemon.respository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, RestTemplate restTemplate) {
        this.pokemonRepository = pokemonRepository;
        this.restTemplate = restTemplate;
    }

    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    public Pokemon getPokemonById(String id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon addPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon(String id, Pokemon pokemon) {
        if (pokemonRepository.existsById(id)) {
            pokemon.setName(pokemon.getName());
            pokemon.setHeight(pokemon.getHeight());
            pokemon.setBaseExperience(pokemon.getBaseExperience());
            pokemon.setWeight(pokemon.getWeight());
            pokemon.setOrder(pokemon.getOrder());
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    public void deletePokemon(String id) {
        pokemonRepository.deleteById(id);
    }

public Pokemon fetchPokemonFromApi(int pokemonId, String pokemonDetails) {
    String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
    ResponseEntity<Pokemon> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Pokemon.class);

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
        Pokemon pokemon = responseEntity.getBody();
        pokemon.setId(String.valueOf(pokemonId));

        return pokemon;
    } else {
        return null;
    }
}


    public Pokemon fetchPokemonFromExternalAPI(int pokemonId) {
        System.out.println("fetching");
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        return restTemplate.getForObject(apiUrl, Pokemon.class);
    }

    public void savePokemonToDatabase(int pokemonId, String pokemonDetails) {
        Pokemon pokemon = fetchPokemonFromApi(pokemonId, pokemonDetails);
        System.out.println(pokemonDetails);
        if (pokemon != null) {
            pokemonRepository.save(pokemon);
            System.out.println("working");
        }
    }

}
