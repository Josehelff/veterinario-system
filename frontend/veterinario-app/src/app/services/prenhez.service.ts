import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prenhez } from '../models/prenhez.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PrenhezService {
  private apiUrl = `${environment.apiUrl}/prenhezes`;
  constructor(private http: HttpClient) {}
  listarTodas(): Observable<Prenhez[]> { return this.http.get<Prenhez[]>(this.apiUrl); }
  criarPrenhez(p: Prenhez): Observable<Prenhez> { return this.http.post<Prenhez>(this.apiUrl, p); }
  atualizarPrenhez(id: number, p: Prenhez): Observable<Prenhez> { return this.http.put<Prenhez>(`${this.apiUrl}/${id}`, p); }
  deletarPrenhez(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}
