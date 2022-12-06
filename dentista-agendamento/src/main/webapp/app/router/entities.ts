import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Consulta = () => import('@/entities/consulta/consulta.vue');
// prettier-ignore
const ConsultaDetails = () => import('@/entities/consulta/consulta-details.vue');
// prettier-ignore
const ConsultaProcess_CadastroProcessDetails = () => import('@/entities/consulta-process/cadastro-process/cadastro-process-details.vue');
// prettier-ignore
const ConsultaProcess_CadastroProcessExecute = () => import('@/entities/consulta-process/cadastro-process/cadastro-process-execute.vue');
// prettier-ignore
const ConsultaProcessDetails = () => import('@/entities/consulta-process/consulta-process-details.vue');
// prettier-ignore
const ConsultaProcessList = () => import('@/entities/consulta-process/consulta-process-list.vue');
// prettier-ignore
const ConsultaStartFormInit = () => import('@/entities/consulta-process/consulta-start-form-init.vue');
// prettier-ignore
const ConsultaProcess_InfoCadastroDetails = () => import('@/entities/consulta-process/info-cadastro/info-cadastro-details.vue');
// prettier-ignore
const ConsultaProcess_InfoCadastroExecute = () => import('@/entities/consulta-process/info-cadastro/info-cadastro-execute.vue');
// prettier-ignore
const ConsultaProcess_SelConsultaDetails = () => import('@/entities/consulta-process/sel-consulta/sel-consulta-details.vue');
// prettier-ignore
const ConsultaProcess_SelConsultaExecute = () => import('@/entities/consulta-process/sel-consulta/sel-consulta-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/consulta',
    name: 'Consulta',
    component: Consulta,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/consulta/:consultaId/view',
    name: 'ConsultaView',
    component: ConsultaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/task/CadastroProcess/:taskInstanceId/view',
    name: 'ConsultaProcess_CadastroProcessDetails',
    component: ConsultaProcess_CadastroProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/task/CadastroProcess/:taskInstanceId/execute',
    name: 'ConsultaProcess_CadastroProcessExecute',
    component: ConsultaProcess_CadastroProcessExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/instance/:processInstanceId/view',
    name: 'ConsultaProcessView',
    component: ConsultaProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/instances',
    name: 'ConsultaProcessList',
    component: ConsultaProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/init',
    name: 'ConsultaStartFormInit',
    component: ConsultaStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/task/InfoCadastro/:taskInstanceId/view',
    name: 'ConsultaProcess_InfoCadastroDetails',
    component: ConsultaProcess_InfoCadastroDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/task/InfoCadastro/:taskInstanceId/execute',
    name: 'ConsultaProcess_InfoCadastroExecute',
    component: ConsultaProcess_InfoCadastroExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/task/SelConsulta/:taskInstanceId/view',
    name: 'ConsultaProcess_SelConsultaDetails',
    component: ConsultaProcess_SelConsultaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/Consulta/task/SelConsulta/:taskInstanceId/execute',
    name: 'ConsultaProcess_SelConsultaExecute',
    component: ConsultaProcess_SelConsultaExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
