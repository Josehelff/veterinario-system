import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estatisticas } from '../models/estatisticas.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class EstatisticasService {
  private apiUrl = `${environment.apiUrl}/estatisticas`;
  constructor(private http: HttpClient) {}
  obterEstatisticas(): Observable<Estatisticas> {
    return this.http.get<Estatisticas>(this.apiUrl);
  }
}
