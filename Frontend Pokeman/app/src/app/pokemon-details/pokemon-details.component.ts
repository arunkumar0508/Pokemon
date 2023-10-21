import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PokemonService } from '../pokemonservices.service';

@Component({
  selector: 'app-pokemon-details',
  templateUrl: './pokemon-details.component.html',
  styleUrls: ['./pokemon-details.component.css']
})
export class PokemonDetailsComponent implements OnInit {
  pokemon: any;

  constructor(private route: ActivatedRoute, private pokemonService: PokemonService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      const pokemonId = +params['id'];
      this.pokemonService.getPokemonData(pokemonId).subscribe((data: any) => {
        this.pokemon = data;
      });
    });
  }
}
