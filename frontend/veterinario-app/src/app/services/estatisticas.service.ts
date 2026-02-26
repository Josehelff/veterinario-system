import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estatisticas } from '../models/estatisticas.model';

@Injectable({
  providedIn: 'root'
})
export class EstatisticasService {
  private apiUrl = 'http://localhost:8080/api/estatisticas';

  constructor(private http: HttpClient) { }

  obterEstatisticas(): Observable<Estatisticas> {
    return this.http.get<Estatisticas>(this.apiUrl);
  }
}
