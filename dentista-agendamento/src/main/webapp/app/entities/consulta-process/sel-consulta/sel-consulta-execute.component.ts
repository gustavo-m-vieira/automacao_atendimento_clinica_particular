import { Component, Vue, Inject } from 'vue-property-decorator';

import SelConsultaService from './sel-consulta.service';
import { SelConsultaContext } from './sel-consulta.model';

const validations: any = {
  taskContext: {
    consultaProcess: {
      consulta: {
        name: {},
        cpf: {},
        date: {},
        email: {},
        price: {},
        durationInMinutes: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class SelConsultaExecuteComponent extends Vue {
  private selConsultaService: SelConsultaService = new SelConsultaService();
  private taskContext: SelConsultaContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.selConsultaService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.selConsultaService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
