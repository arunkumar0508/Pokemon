// pokemon.component.ts
import { Component, OnInit } from "@angular/core";
import { PokemonService } from "../pokemonservices.service";

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.css']
})
export class PokemonComponent implements OnInit {
  pokemonData: any[] = [];
  displayedPokemon: any[] = [];
  itemsPerPage: number = 8;
  currentPage: number = 1;

  constructor(private pokemonService: PokemonService) {}

  ngOnInit() {
    this.fetchPokemons(); // Fetch initial data
  }

  fetchPokemons() {
    const numberOfPokemons = 100;
    for (let id = 1; id <= numberOfPokemons; id++) {
      this.pokemonService.getPokemonData(id).subscribe((data: any) => {
        this.pokemonData.push(data);
        console.log(data);
        this.loadInitialData(); // Load the first 8 cards after fetching data

      });
    }
  }

  loadInitialData() {
    const endIndex = this.itemsPerPage;
    this.displayedPokemon = this.pokemonData.slice(0, endIndex);
  }

  loadMoreData() {
    const startIndex = this.displayedPokemon.length;
    const endIndex = startIndex + this.itemsPerPage;
    const newPokemon = this.pokemonData.slice(startIndex, endIndex);
    this.displayedPokemon = [...this.displayedPokemon, ...newPokemon];
  }
}
