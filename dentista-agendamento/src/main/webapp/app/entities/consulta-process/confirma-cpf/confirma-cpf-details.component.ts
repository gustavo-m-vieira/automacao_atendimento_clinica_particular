import { Component, Vue, Inject } from 'vue-property-decorator';

import ConfirmaCPFService from './confirma-cpf.service';
import { ConfirmaCPFContext } from './confirma-cpf.model';

@Component
export default class ConfirmaCPFDetailsComponent extends Vue {
  private confirmaCPFService: ConfirmaCPFService = new ConfirmaCPFService();
  private taskContext: ConfirmaCPFContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.confirmaCPFService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
