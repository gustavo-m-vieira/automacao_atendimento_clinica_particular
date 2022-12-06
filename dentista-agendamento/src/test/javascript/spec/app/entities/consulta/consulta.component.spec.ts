/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ConsultaComponent from '@/entities/consulta/consulta.vue';
import ConsultaClass from '@/entities/consulta/consulta.component';
import ConsultaService from '@/entities/consulta/consulta.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Consulta Management Component', () => {
    let wrapper: Wrapper<ConsultaClass>;
    let comp: ConsultaClass;
    let consultaServiceStub: SinonStubbedInstance<ConsultaService>;

    beforeEach(() => {
      consultaServiceStub = sinon.createStubInstance<ConsultaService>(ConsultaService);
      consultaServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ConsultaClass>(ConsultaComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          consultaService: () => consultaServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      consultaServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllConsultas();
      await comp.$nextTick();

      // THEN
      expect(consultaServiceStub.retrieve.called).toBeTruthy();
      expect(comp.consultas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
