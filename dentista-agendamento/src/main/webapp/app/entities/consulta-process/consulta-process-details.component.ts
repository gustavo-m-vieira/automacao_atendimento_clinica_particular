import { Component, Vue, Inject } from 'vue-property-decorator';

import { IConsultaProcess } from '@/shared/model/consulta-process.model';
import ConsultaProcessService from './consulta-process.service';

@Component
export default class ConsultaProcessDetailsComponent extends Vue {
  @Inject('consultaProcessService') private consultaProcessService: () => ConsultaProcessService;
  public consultaProcess: IConsultaProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveConsultaProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveConsultaProcess(consultaProcessId) {
    this.isFetching = true;
    this.consultaProcessService()
      .find(consultaProcessId)
      .then(
        res => {
          this.consultaProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
