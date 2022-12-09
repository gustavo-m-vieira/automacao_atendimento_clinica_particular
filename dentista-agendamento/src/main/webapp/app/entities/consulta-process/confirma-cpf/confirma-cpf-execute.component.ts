import { Component, Vue, Inject } from 'vue-property-decorator';

import ConfirmaCPFService from './confirma-cpf.service';
import { ConfirmaCPFContext } from './confirma-cpf.model';

const validations: any = {
  taskContext: {
    consultaProcess: {
      consulta: {
        cpf: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class ConfirmaCPFExecuteComponent extends Vue {
  private confirmaCPFService: ConfirmaCPFService = new ConfirmaCPFService();
  private taskContext: ConfirmaCPFContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.confirmaCPFService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.confirmaCPFService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
