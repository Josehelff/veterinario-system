export interface Animal {
  id?: number;
  brinco: string;
  nome: string;
  tipo: TipoAnimal;
  sexo: SexoAnimal;
  dataNascimento: Date;
  raca?: string;
  peso?: number;
  observacoes?: string;
  status: StatusAnimal;
}

export enum TipoAnimal {
  BOVINO = 'BOVINO',
  EQUINO = 'EQUINO',
  CAPRINO = 'CAPRINO',
  OVINO = 'OVINO',
  SUINO = 'SUINO',
  OUTRO = 'OUTRO'
}

export enum SexoAnimal {
  MACHO = 'MACHO',
  FEMEA = 'FEMEA'
}

export enum StatusAnimal {
  ATIVO = 'ATIVO',
  INATIVO = 'INATIVO',
  VENDIDO = 'VENDIDO',
  FALECIDO = 'FALECIDO'
}
