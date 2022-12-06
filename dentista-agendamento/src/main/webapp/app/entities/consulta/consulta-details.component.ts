import { Component, Vue, Inject } from 'vue-property-decorator';

import { IConsulta } from '@/shared/model/consulta.model';
import ConsultaService from './consulta.service';

@Component
export default class ConsultaDetails extends Vue {
  @Inject('consultaService') private consultaService: () => ConsultaService;
  public consulta: IConsulta = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.consultaId) {
        vm.retrieveConsulta(to.params.consultaId);
      }
    });
  }

  public retrieveConsulta(consultaId) {
    this.consultaService()
      .find(consultaId)
      .then(res => {
        this.consulta = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
