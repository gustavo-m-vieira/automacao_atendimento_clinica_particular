import axios from 'axios';
import { ConfirmaCPFContext } from './confirma-cpf.model';

const baseApiUrl = 'api/consulta-process/confirma-cpf';

export default class ConfirmaCPFService {
  public loadContext(taskId: number): Promise<ConfirmaCPFContext> {
    return new Promise<ConfirmaCPFContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<ConfirmaCPFContext> {
    return new Promise<ConfirmaCPFContext>((resolve, reject) => {
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

  public complete(confirmaCPFContext: ConfirmaCPFContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, confirmaCPFContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
