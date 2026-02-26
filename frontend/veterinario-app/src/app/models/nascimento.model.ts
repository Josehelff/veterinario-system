import { SexoAnimal } from './animal.model';

export interface Nascimento {
  id?: number;
  animalId: number;
  animalBrinco: string;
  animalNome: string;
  prenhezId?: number;
  dataNascimento: Date;
  brincoFilhote: string;
  nomeFilhote?: string;
  sexoFilhote: SexoAnimal;
  pesoNascimento?: number;
  statusNascimento: StatusNascimento;
  observacoes?: string;
}

export enum StatusNascimento {
  VIVO = 'VIVO',
  MORTO = 'MORTO',
  ABORTADO = 'ABORTADO'
}
