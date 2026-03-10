import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Desmame } from '../models/desmame.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class DesmameService {
  private apiUrl = `${environment.apiUrl}/desmames`;
  constructor(private http: HttpClient) {}
  listarTodos(): Observable<Desmame[]> { return this.http.get<Desmame[]>(this.apiUrl); }
  criarDesmame(d: Desmame): Observable<Desmame> { return this.http.post<Desmame>(this.apiUrl, d); }
  atualizarDesmame(id: number, d: Desmame): Observable<Desmame> { return this.http.put<Desmame>(`${this.apiUrl}/${id}`, d); }
  deletarDesmame(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
