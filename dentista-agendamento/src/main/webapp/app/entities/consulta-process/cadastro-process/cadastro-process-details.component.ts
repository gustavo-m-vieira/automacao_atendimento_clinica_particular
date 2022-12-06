import { Component, Vue, Inject } from 'vue-property-decorator';

import CadastroProcessService from './cadastro-process.service';
import { CadastroProcessContext } from './cadastro-process.model';

@Component
export default class CadastroProcessDetailsComponent extends Vue {
  private cadastroProcessService: CadastroProcessService = new CadastroProcessService();
  private taskContext: CadastroProcessContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.cadastroProcessService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
