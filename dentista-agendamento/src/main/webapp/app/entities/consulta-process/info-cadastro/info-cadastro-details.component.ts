import { Component, Vue, Inject } from 'vue-property-decorator';

import InfoCadastroService from './info-cadastro.service';
import { InfoCadastroContext } from './info-cadastro.model';

@Component
export default class InfoCadastroDetailsComponent extends Vue {
  private infoCadastroService: InfoCadastroService = new InfoCadastroService();
  private taskContext: InfoCadastroContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.infoCadastroService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
