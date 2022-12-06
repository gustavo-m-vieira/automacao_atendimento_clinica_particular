import { IConsulta } from '@/shared/model/consulta.model';

export interface IConsultaProcess {
  id?: number;
  processInstance?: any | null;
  consulta?: IConsulta | null;
}

export class ConsultaProcess implements IConsultaProcess {
  constructor(public id?: number, public processInstance?: any | null, public consulta?: IConsulta | null) {}
}
