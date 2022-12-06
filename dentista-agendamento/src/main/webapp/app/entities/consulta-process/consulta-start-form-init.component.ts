import { Component, Vue, Inject } from 'vue-property-decorator';

import { IConsultaProcess, ConsultaProcess } from '@/shared/model/consulta-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Consulta } from '@/shared/model/consulta.model';
import ConsultaProcessService from './consulta-process.service';

const validations: any = {
  consultaProcess: {
    consulta: {
      name: {},
      cpf: {},
      email: {},
    },
  },
};

@Component({
  validations,
})
export default class ConsultaStartFormInitComponent extends Vue {
  @Inject('consultaProcessService') private consultaProcessService: () => ConsultaProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'ConsultaProcess';
  public consultaProcess: IConsultaProcess = new ConsultaProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initConsultaStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.consultaProcessService()
      .create(this.consultaProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('jhipsterApp.consultaStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initConsultaStartForm(): void {
    this.consultaProcess.consulta = new Consulta();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.consultaProcess.processInstance = new ProcessInstance();
      this.consultaProcess.processInstance.processDefinition = res;
    });
  }
}
