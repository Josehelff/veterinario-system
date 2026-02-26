import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Desmame } from '../models/desmame.model';

@Injectable({
  providedIn: 'root'
})
export class DesmameService {
  private apiUrl = 'http://localhost:8080/api/desmames';

  constructor(private http: HttpClient) { }

  criarDesmame(desmame: Desmame): Observable<Desmame> {
    return this.http.post<Desmame>(this.apiUrl, desmame);
  }

  obterDesmame(id: number): Observable<Desmame> {
    return this.http.get<Desmame>(`${this.apiUrl}/${id}`);
  }

  listarTodos(): Observable<Desmame[]> {
    return this.http.get<Desmame[]>(this.apiUrl);
  }

  listarPorAnimal(animalId: number): Observable<Desmame[]> {
    return this.http.get<Desmame[]>(`${this.apiUrl}/animal/${animalId}`);
  }

  listarPendentes(): Observable<Desmame[]> {
    return this.http.get<Desmame[]>(`${this.apiUrl}/pendentes`);
  }

  listarPorPeriodo(dataInicio: Date, dataFim: Date): Observable<Desmame[]> {
    let params = new HttpParams();
    params = params.set('dataInicio', dataInicio.toISOString().split('T')[0]);
    params = params.set('dataFim', dataFim.toISOString().split('T')[0]);
    return this.http.get<Desmame[]>(`${this.apiUrl}/periodo`, { params });
  }

  atualizarDesmame(id: number, desmame: Desmame): Observable<Desmame> {
    return this.http.put<Desmame>(`${this.apiUrl}/${id}`, desmame);
  }

  deletarDesmame(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
