import axios from 'axios';
import { InfoCadastroContext } from './info-cadastro.model';

const baseApiUrl = 'api/consulta-process/info-cadastro';

export default class InfoCadastroService {
  public loadContext(taskId: number): Promise<InfoCadastroContext> {
    return new Promise<InfoCadastroContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<InfoCadastroContext> {
    return new Promise<InfoCadastroContext>((resolve, reject) => {
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

  public complete(infoCadastroContext: InfoCadastroContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, infoCadastroContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
