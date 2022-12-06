import { Component, Vue, Inject } from 'vue-property-decorator';

import SelConsultaService from './sel-consulta.service';
import { SelConsultaContext } from './sel-consulta.model';

@Component
export default class SelConsultaDetailsComponent extends Vue {
  private selConsultaService: SelConsultaService = new SelConsultaService();
  private taskContext: SelConsultaContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.selConsultaService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
