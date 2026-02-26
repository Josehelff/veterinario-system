import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EstatisticasService } from '../../services/estatisticas.service';
import { Estatisticas } from '../../models/estatisticas.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  estatisticas: Estatisticas | null = null;
  carregando = true;
  erro: string | null = null;

  constructor(private estatisticasService: EstatisticasService) {}

  ngOnInit(): void {
    this.carregarEstatisticas();
  }

  carregarEstatisticas(): void {
    this.carregando = true;
    this.erro = null;
    this.estatisticasService.obterEstatisticas().subscribe({
      next: (dados) => {
        this.estatisticas = dados;
        this.carregando = false;
      },
      error: (err) => {
        this.erro = 'Erro ao carregar estatísticas';
        console.error(err);
        this.carregando = false;
      }
    });
  }
}
