import { Component, Vue, Inject } from 'vue-property-decorator';

import InfoCadastroService from './info-cadastro.service';
import { InfoCadastroContext } from './info-cadastro.model';

const validations: any = {
  taskContext: {
    consultaProcess: {
      consulta: {
        name: {},
        cpf: {},
        email: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class InfoCadastroExecuteComponent extends Vue {
  private infoCadastroService: InfoCadastroService = new InfoCadastroService();
  private taskContext: InfoCadastroContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.infoCadastroService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.infoCadastroService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
