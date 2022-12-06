import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IConsulta } from '@/shared/model/consulta.model';

import ConsultaService from './consulta.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Consulta extends Vue {
  @Inject('consultaService') private consultaService: () => ConsultaService;
  private removeId: number = null;

  public consultas: IConsulta[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllConsultas();
  }

  public clear(): void {
    this.retrieveAllConsultas();
  }

  public retrieveAllConsultas(): void {
    this.isFetching = true;

    this.consultaService()
      .retrieve()
      .then(
        res => {
          this.consultas = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
