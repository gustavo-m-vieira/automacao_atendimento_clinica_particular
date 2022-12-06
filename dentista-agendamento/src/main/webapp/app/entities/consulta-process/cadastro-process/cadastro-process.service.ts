import axios from 'axios';
import { CadastroProcessContext } from './cadastro-process.model';

const baseApiUrl = 'api/consulta-process/cadastro-process';

export default class CadastroProcessService {
  public loadContext(taskId: number): Promise<CadastroProcessContext> {
    return new Promise<CadastroProcessContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<CadastroProcessContext> {
    return new Promise<CadastroProcessContext>((resolve, reject) => {
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

  public complete(cadastroProcessContext: CadastroProcessContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, cadastroProcessContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
