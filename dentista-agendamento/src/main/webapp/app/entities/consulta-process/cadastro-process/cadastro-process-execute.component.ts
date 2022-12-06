import { Component, Vue, Inject } from 'vue-property-decorator';

import CadastroProcessService from './cadastro-process.service';
import { CadastroProcessContext } from './cadastro-process.model';

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
export default class CadastroProcessExecuteComponent extends Vue {
  private cadastroProcessService: CadastroProcessService = new CadastroProcessService();
  private taskContext: CadastroProcessContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.cadastroProcessService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.cadastroProcessService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
