import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InseminacaoService } from '../../services/inseminacao.service';
import { AnimalService } from '../../services/animal.service';
import { Inseminacao, StatusInseminacao, ResultadoDiagnostico } from '../../models/inseminacao.model';
import { Animal } from '../../models/animal.model';

@Component({
  selector: 'app-inseminacao-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './inseminacao-list.component.html',
  styleUrls: ['./inseminacao-list.component.css']
})
export class InseminacaoListComponent implements OnInit {
  inseminacoes: Inseminacao[] = [];
  animais: Animal[] = [];
  carregando = true;
  erro: string | null = null;
  mostraFormulario = false;
  inseminacaoEditando: Inseminacao | null = null;

  novaInseminacao: Inseminacao = {
    animalId: 0,
    animalBrinco: '',
    animalNome: '',
    dataInseminacao: new Date(),
    reprodutor: '',
    observacoes: '',
    status: StatusInseminacao.REALIZADA,
    resultadoDiagnostico: ResultadoDiagnostico.PENDENTE
  };

  constructor(
    private inseminacaoService: InseminacaoService,
    private animalService: AnimalService
  ) {}

  ngOnInit(): void {
    this.carregarAnimais();
    this.carregarInseminacoes();
  }

  carregarAnimais(): void {
    this.animalService.listarFemeasAtivas().subscribe({
      next: (dados) => {
        this.animais = dados;
      },
      error: (err) => {
        console.error('Erro ao carregar animais', err);
      }
    });
  }

  carregarInseminacoes(): void {
    this.carregando = true;
    this.erro = null;
    this.inseminacaoService.listarTodas().subscribe({
      next: (dados) => {
        this.inseminacoes = dados;
        this.carregando = false;
      },
      error: (err) => {
        this.erro = 'Erro ao carregar inseminações';
        console.error(err);
        this.carregando = false;
      }
    });
  }

  abrirFormulario(): void {
    this.mostraFormulario = true;
    this.inseminacaoEditando = null;
    this.novaInseminacao = {
      animalId: 0,
      animalBrinco: '',
      animalNome: '',
      dataInseminacao: new Date(),
      reprodutor: '',
      observacoes: '',
      status: StatusInseminacao.REALIZADA,
      resultadoDiagnostico: ResultadoDiagnostico.PENDENTE
    };
  }

  fecharFormulario(): void {
    this.mostraFormulario = false;
    this.inseminacaoEditando = null;
  }

  salvarInseminacao(): void {
    if (this.inseminacaoEditando) {
      this.inseminacaoService.atualizarInseminacao(this.inseminacaoEditando.id!, this.novaInseminacao).subscribe({
        next: () => {
          this.carregarInseminacoes();
          this.fecharFormulario();
        },
        error: (err) => {
          this.erro = 'Erro ao atualizar inseminação';
          console.error(err);
        }
      });
    } else {
      this.inseminacaoService.criarInseminacao(this.novaInseminacao).subscribe({
        next: () => {
          this.carregarInseminacoes();
          this.fecharFormulario();
        },
        error: (err) => {
          this.erro = 'Erro ao criar inseminação';
          console.error(err);
        }
      });
    }
  }

  editarInseminacao(inseminacao: Inseminacao): void {
    this.inseminacaoEditando = inseminacao;
    this.novaInseminacao = { ...inseminacao };
    this.mostraFormulario = true;
  }

  deletarInseminacao(id: number): void {
    if (confirm('Tem certeza que deseja deletar esta inseminação?')) {
      this.inseminacaoService.deletarInseminacao(id).subscribe({
        next: () => {
          this.carregarInseminacoes();
        },
        error: (err) => {
          this.erro = 'Erro ao deletar inseminação';
          console.error(err);
        }
      });
    }
  }

  obterNomeAnimal(animalId: number): string {
    const animal = this.animais.find(a => a.id === animalId);
    return animal ? `${animal.brinco} - ${animal.nome}` : '';
  }
}
