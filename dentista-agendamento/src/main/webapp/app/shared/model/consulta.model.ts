export interface IConsulta {
  id?: number;
  name?: string | null;
  cpf?: string | null;
  date?: Date | null;
  email?: string | null;
  price?: number | null;
  durationInMinutes?: number | null;
}

export class Consulta implements IConsulta {
  constructor(
    public id?: number,
    public name?: string | null,
    public cpf?: string | null,
    public date?: Date | null,
    public email?: string | null,
    public price?: number | null,
    public durationInMinutes?: number | null
  ) {}
}
