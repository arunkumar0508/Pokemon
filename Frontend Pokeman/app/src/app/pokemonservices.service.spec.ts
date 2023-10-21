import { TestBed } from '@angular/core/testing';

import { PokemonservicesService } from './pokemonservices.service';

describe('PokemonservicesService', () => {
  let service: PokemonservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PokemonservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
