import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Animal } from '../models/animal.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {
  private apiUrl = `${environment.apiUrl}/animais`;

  constructor(private http: HttpClient) { }

  criarAnimal(animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(this.apiUrl, animal);
  }

  obterAnimal(id: number): Observable<Animal> {
    return this.http.get<Animal>(`${this.apiUrl}/${id}`);
  }

  listarTodos(): Observable<Animal[]> {
    return this.http.get<Animal[]>(this.apiUrl);
  }

  listarFemeasAtivas(): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${this.apiUrl}/femeas/ativas`);
  }

  listarMachosAtivos(): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${this.apiUrl}/machos/ativos`);
  }

  atualizarAnimal(id: number, animal: Animal): Observable<Animal> {
    return this.http.put<Animal>(`${this.apiUrl}/${id}`, animal);
  }

  deletarAnimal(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  obterPorBrinco(brinco: string): Observable<Animal> {
    return this.http.get<Animal>(`${this.apiUrl}/brinco/${brinco}`);
  }
}
