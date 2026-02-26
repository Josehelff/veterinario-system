export interface Desmame {
  id?: number;
  animalId: number;
  animalBrinco: string;
  animalNome: string;
  nascimentoId?: number;
  dataNascimento: Date;
  diasDesmame: number;
  previsaoDesmame: Date;
  dataDesmameReal?: Date;
  pesoDesmame?: number;
  observacoes?: string;
  status: StatusDesmame;
}

export enum StatusDesmame {
  PENDENTE = 'PENDENTE',
  REALIZADO = 'REALIZADO',
  CANCELADO = 'CANCELADO'
}
