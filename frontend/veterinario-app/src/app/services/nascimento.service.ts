import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nascimento } from '../models/nascimento.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class NascimentoService {
  private apiUrl = `${environment.apiUrl}/nascimentos`;
  constructor(private http: HttpClient) {}
  listarTodos(): Observable<Nascimento[]> { return this.http.get<Nascimento[]>(this.apiUrl); }
  criarNascimento(n: Nascimento): Observable<Nascimento> { return this.http.post<Nascimento>(this.apiUrl, n); }
  atualizarNascimento(id: number, n: Nascimento): Observable<Nascimento> { return this.http.put<Nascimento>(`${this.apiUrl}/${id}`, n); }
  deletarNascimento(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
