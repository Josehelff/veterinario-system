import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nascimento } from '../models/nascimento.model';

@Injectable({
  providedIn: 'root'
})
export class NascimentoService {
  private apiUrl = 'http://localhost:8080/api/nascimentos';

  constructor(private http: HttpClient) { }

  criarNascimento(nascimento: Nascimento): Observable<Nascimento> {
    return this.http.post<Nascimento>(this.apiUrl, nascimento);
  }

  obterNascimento(id: number): Observable<Nascimento> {
    return this.http.get<Nascimento>(`${this.apiUrl}/${id}`);
  }

  listarTodos(): Observable<Nascimento[]> {
    return this.http.get<Nascimento[]>(this.apiUrl);
  }

  listarPorAnimal(animalId: number): Observable<Nascimento[]> {
    return this.http.get<Nascimento[]>(`${this.apiUrl}/animal/${animalId}`);
  }

  listarPorPeriodo(dataInicio: Date, dataFim: Date): Observable<Nascimento[]> {
    let params = new HttpParams();
    params = params.set('dataInicio', dataInicio.toISOString().split('T')[0]);
    params = params.set('dataFim', dataFim.toISOString().split('T')[0]);
    return this.http.get<Nascimento[]>(`${this.apiUrl}/periodo`, { params });
  }

  atualizarNascimento(id: number, nascimento: Nascimento): Observable<Nascimento> {
    return this.http.put<Nascimento>(`${this.apiUrl}/${id}`, nascimento);
  }

  deletarNascimento(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
