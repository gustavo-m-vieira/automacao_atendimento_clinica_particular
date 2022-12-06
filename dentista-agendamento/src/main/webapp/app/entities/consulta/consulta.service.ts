import axios from 'axios';

import { IConsulta } from '@/shared/model/consulta.model';

const baseApiUrl = 'api/consultas';

export default class ConsultaService {
  public find(id: number): Promise<IConsulta> {
    return new Promise<IConsulta>((resolve, reject) => {
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
}
