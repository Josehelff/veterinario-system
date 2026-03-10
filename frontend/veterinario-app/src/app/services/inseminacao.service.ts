import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inseminacao } from '../models/inseminacao.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class InseminacaoService {
  private apiUrl = `${environment.apiUrl}/inseminacoes`;
  constructor(private http: HttpClient) {}
  listarTodas(): Observable<Inseminacao[]> { return this.http.get<Inseminacao[]>(this.apiUrl); }
  obterInseminacao(id: number): Observable<Inseminacao> { return this.http.get<Inseminacao>(`${this.apiUrl}/${id}`); }
  listarPorAnimal(animalId: number): Observable<Inseminacao[]> { return this.http.get<Inseminacao[]>(`${this.apiUrl}/animal/${animalId}`); }
  criarInseminacao(ins: Inseminacao): Observable<Inseminacao> { return this.http.post<Inseminacao>(this.apiUrl, ins); }
  atualizarInseminacao(id: number, ins: Inseminacao): Observable<Inseminacao> { return this.http.put<Inseminacao>(`${this.apiUrl}/${id}`, ins); }
  deletarInseminacao(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
