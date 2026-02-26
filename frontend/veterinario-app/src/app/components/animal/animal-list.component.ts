import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AnimalService } from '../../services/animal.service';
import { Animal, StatusAnimal } from '../../models/animal.model';

@Component({
  selector: 'app-animal-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {
  animais: Animal[] = [];
  carregando = true;
  erro: string | null = null;
  mostraFormulario = false;
  animalEditando: Animal | null = null;

  novoAnimal: Animal = {
    brinco: '',
    nome: '',
    tipo: 'BOVINO' as any,
    sexo: 'FEMEA' as any,
    dataNascimento: new Date(),
    raca: '',
    peso: 0,
    observacoes: '',
    status: StatusAnimal.ATIVO
  };

  constructor(private animalService: AnimalService) {}

  ngOnInit(): void {
    this.carregarAnimais();
  }

  carregarAnimais(): void {
    this.carregando = true;
    this.erro = null;
    this.animalService.listarTodos().subscribe({
      next: (dados) => {
        this.animais = dados;
        this.carregando = false;
      },
      error: (err) => {
        this.erro = 'Erro ao carregar animais';
        console.error(err);
        this.carregando = false;
      }
    });
  }

  abrirFormulario(): void {
    this.mostraFormulario = true;
    this.animalEditando = null;
    this.novoAnimal = {
      brinco: '',
      nome: '',
      tipo: 'BOVINO' as any,
      sexo: 'FEMEA' as any,
      dataNascimento: new Date(),
      raca: '',
      peso: 0,
      observacoes: '',
      status: StatusAnimal.ATIVO
    };
  }

  fecharFormulario(): void {
    this.mostraFormulario = false;
    this.animalEditando = null;
  }

  salvarAnimal(): void {
    if (this.animalEditando) {
      this.animalService.atualizarAnimal(this.animalEditando.id!, this.novoAnimal).subscribe({
        next: () => {
          this.carregarAnimais();
          this.fecharFormulario();
        },
        error: (err) => {
          this.erro = 'Erro ao atualizar animal';
          console.error(err);
        }
      });
    } else {
      this.animalService.criarAnimal(this.novoAnimal).subscribe({
        next: () => {
          this.carregarAnimais();
          this.fecharFormulario();
        },
        error: (err) => {
          this.erro = 'Erro ao criar animal';
          console.error(err);
        }
      });
    }
  }

  editarAnimal(animal: Animal): void {
    this.animalEditando = animal;
    this.novoAnimal = { ...animal };
    this.mostraFormulario = true;
  }

  deletarAnimal(id: number): void {
    if (confirm('Tem certeza que deseja deletar este animal?')) {
      this.animalService.deletarAnimal(id).subscribe({
        next: () => {
          this.carregarAnimais();
        },
        error: (err) => {
          this.erro = 'Erro ao deletar animal';
          console.error(err);
        }
      });
    }
  }
}
