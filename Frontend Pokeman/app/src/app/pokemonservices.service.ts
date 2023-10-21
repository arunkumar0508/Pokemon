import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  private apiUrl = 'https://pokeapi.co/api/v2/pokemon/';

  constructor(private http: HttpClient) {}

  getPokemonData(id: number): Observable<any> {
    const url = `${this.apiUrl}${id}/`;
    console.log('service')
    return this.http.get(url);
  }
}
