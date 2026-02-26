export interface Prenhez {
  id?: number;
  animalId: number;
  animalBrinco: string;
  animalNome: string;
  inseminacaoId?: number;
  dataConfirmacao: Date;
  periodoGestacao: number;
  previsaoNascimento: Date;
  observacoes?: string;
  status: StatusPrenhez;
}

export enum StatusPrenhez {
  CONFIRMADA = 'CONFIRMADA',
  ABORTADA = 'ABORTADA',
  FINALIZADA = 'FINALIZADA'
}
