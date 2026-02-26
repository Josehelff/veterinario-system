import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prenhez } from '../models/prenhez.model';

@Injectable({
  providedIn: 'root'
})
export class PrenhezService {
  private apiUrl = 'http://localhost:8080/api/prenhezes';

  constructor(private http: HttpClient) { }

  criarPrenhez(prenhez: Prenhez): Observable<Prenhez> {
    return this.http.post<Prenhez>(this.apiUrl, prenhez);
  }

  obterPrenhez(id: number): Observable<Prenhez> {
    return this.http.get<Prenhez>(`${this.apiUrl}/${id}`);
  }

  listarTodas(): Observable<Prenhez[]> {
    return this.http.get<Prenhez[]>(this.apiUrl);
  }

  listarPorAnimal(animalId: number): Observable<Prenhez[]> {
    return this.http.get<Prenhez[]>(`${this.apiUrl}/animal/${animalId}`);
  }

  listarConfirmadas(): Observable<Prenhez[]> {
    return this.http.get<Prenhez[]>(`${this.apiUrl}/confirmadas`);
  }

  listarPrevisoesPorPeriodo(dataInicio: Date, dataFim: Date): Observable<Prenhez[]> {
    let params = new HttpParams();
    params = params.set('dataInicio', dataInicio.toISOString().split('T')[0]);
    params = params.set('dataFim', dataFim.toISOString().split('T')[0]);
    return this.http.get<Prenhez[]>(`${this.apiUrl}/previsoes`, { params });
  }

  atualizarPrenhez(id: number, prenhez: Prenhez): Observable<Prenhez> {
    return this.http.put<Prenhez>(`${this.apiUrl}/${id}`, prenhez);
  }

  deletarPrenhez(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
