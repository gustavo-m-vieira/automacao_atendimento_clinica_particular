import axios from 'axios';
import { SelConsultaContext } from './sel-consulta.model';

const baseApiUrl = 'api/consulta-process/sel-consulta';

export default class SelConsultaService {
  public loadContext(taskId: number): Promise<SelConsultaContext> {
    return new Promise<SelConsultaContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<SelConsultaContext> {
    return new Promise<SelConsultaContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(selConsultaContext: SelConsultaContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, selConsultaContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
