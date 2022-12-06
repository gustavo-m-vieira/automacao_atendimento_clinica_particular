<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="jhipsterApp.consultaStartForm.home.createOrEditLabel"
          data-cy="ConsultaStartFormCreateUpdateHeading"
          v-text="$t('jhipsterApp.consultaStartForm.home.createOrEditLabel')"
        >
          Create or edit a ConsultaStartForm
        </h2>
        <div v-if="consultaProcess.processInstance">
          <akip-show-process-definition :processDefinition="consultaProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="consultaProcess.consulta">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaStartForm.name')" for="consulta-start-form-name"
                    >Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="name"
                    id="consulta-start-form-name"
                    data-cy="name"
                    :class="{ valid: !$v.consultaProcess.consulta.name.$invalid, invalid: $v.consultaProcess.consulta.name.$invalid }"
                    v-model="$v.consultaProcess.consulta.name.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaStartForm.cpf')" for="consulta-start-form-cpf"
                    >Cpf</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="cpf"
                    id="consulta-start-form-cpf"
                    data-cy="cpf"
                    :class="{ valid: !$v.consultaProcess.consulta.cpf.$invalid, invalid: $v.consultaProcess.consulta.cpf.$invalid }"
                    v-model="$v.consultaProcess.consulta.cpf.$model"
                  />
                </div>
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaStartForm.email')" for="consulta-start-form-email"
                    >Email</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="email"
                    id="consulta-start-form-email"
                    data-cy="email"
                    :class="{ valid: !$v.consultaProcess.consulta.email.$invalid, invalid: $v.consultaProcess.consulta.email.$invalid }"
                    v-model="$v.consultaProcess.consulta.email.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.consultaProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./consulta-start-form-init.component.ts"></script>
