export interface Inseminacao {
  id?: number;
  animalId: number;
  animalBrinco: string;
  animalNome: string;
  dataInseminacao: Date;
  reprodutor?: string;
  observacoes?: string;
  status: StatusInseminacao;
  dataDiagnostico?: Date;
  resultadoDiagnostico: ResultadoDiagnostico;
}

export enum StatusInseminacao {
  REALIZADA = 'REALIZADA',
  CANCELADA = 'CANCELADA',
  REPETIDA = 'REPETIDA'
}

export enum ResultadoDiagnostico {
  PRENHA = 'PRENHA',
  NAO_PRENHA = 'NAO_PRENHA',
  PENDENTE = 'PENDENTE'
}
