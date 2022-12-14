import { Component, Vue, Inject } from 'vue-property-decorator';
import { IConsultaProcess } from '@/shared/model/consulta-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ConsultaProcessService from './consulta-process.service';

@Component
export default class ConsultaProcessListComponent extends Vue {
  @Inject('consultaProcessService') private consultaProcessService: () => ConsultaProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'Consulta';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public consultaProcessList: IConsultaProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.consultaProcessService()
      .retrieve()
      .then(
        res => {
          this.consultaProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
