import axios from 'axios';

import { IConsultaProcess } from '@/shared/model/consulta-process.model';

const baseApiUrl = 'api/consulta-processes';

export default class ConsultaProcessService {
  public find(id: number): Promise<IConsultaProcess> {
    return new Promise<IConsultaProcess>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IConsultaProcess): Promise<IConsultaProcess> {
    return new Promise<IConsultaProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
