/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ConsultaDetailComponent from '@/entities/consulta/consulta-details.vue';
import ConsultaClass from '@/entities/consulta/consulta-details.component';
import ConsultaService from '@/entities/consulta/consulta.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Consulta Management Detail Component', () => {
    let wrapper: Wrapper<ConsultaClass>;
    let comp: ConsultaClass;
    let consultaServiceStub: SinonStubbedInstance<ConsultaService>;

    beforeEach(() => {
      consultaServiceStub = sinon.createStubInstance<ConsultaService>(ConsultaService);

      wrapper = shallowMount<ConsultaClass>(ConsultaDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { consultaService: () => consultaServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundConsulta = { id: 123 };
        consultaServiceStub.find.resolves(foundConsulta);

        // WHEN
        comp.retrieveConsulta(123);
        await comp.$nextTick();

        // THEN
        expect(comp.consulta).toBe(foundConsulta);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundConsulta = { id: 123 };
        consultaServiceStub.find.resolves(foundConsulta);

        // WHEN
        comp.beforeRouteEnter({ params: { consultaId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.consulta).toBe(foundConsulta);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
