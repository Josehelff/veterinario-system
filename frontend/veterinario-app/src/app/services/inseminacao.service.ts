import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inseminacao } from '../models/inseminacao.model';

@Injectable({
  providedIn: 'root'
})
export class InseminacaoService {
  private apiUrl = 'http://localhost:8080/api/inseminacoes';

  constructor(private http: HttpClient) { }

  criarInseminacao(inseminacao: Inseminacao): Observable<Inseminacao> {
    return this.http.post<Inseminacao>(this.apiUrl, inseminacao);
  }

  obterInseminacao(id: number): Observable<Inseminacao> {
    return this.http.get<Inseminacao>(`${this.apiUrl}/${id}`);
  }

  listarTodas(): Observable<Inseminacao[]> {
    return this.http.get<Inseminacao[]>(this.apiUrl);
  }

  listarPorAnimal(animalId: number): Observable<Inseminacao[]> {
    return this.http.get<Inseminacao[]>(`${this.apiUrl}/animal/${animalId}`);
  }

  listarPorPeriodo(dataInicio: Date, dataFim: Date): Observable<Inseminacao[]> {
    let params = new HttpParams();
    params = params.set('dataInicio', dataInicio.toISOString().split('T')[0]);
    params = params.set('dataFim', dataFim.toISOString().split('T')[0]);
    return this.http.get<Inseminacao[]>(`${this.apiUrl}/periodo`, { params });
  }

  atualizarInseminacao(id: number, inseminacao: Inseminacao): Observable<Inseminacao> {
    return this.http.put<Inseminacao>(`${this.apiUrl}/${id}`, inseminacao);
  }

  deletarInseminacao(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
