<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="consultaProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="consultaProcessDetailsHeading">
          <span v-text="$t('jhipsterApp.consultaProcess.detail.title')">ConsultaProcess</span> {{ consultaProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="consultaProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('jhipsterApp.consultaProcess.detail.title')">ConsultaProcess</span> #{{ consultaProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="consultaProcess.consulta">
              <h5 class="card-header">
                <span class="title"> Consulta #{{ consultaProcess.consulta.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaProcess.name')">name</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="name"
                    id="consulta-name"
                    data-cy="name"
                    v-model="consultaProcess.consulta.name"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaProcess.cpf')">cpf</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cpf"
                    id="consulta-cpf"
                    data-cy="cpf"
                    v-model="consultaProcess.consulta.cpf"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaProcess.date')">date</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="date"
                    id="consulta-date"
                    data-cy="date"
                    v-model="consultaProcess.consulta.date"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaProcess.email')">email</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="email"
                    id="consulta-email"
                    data-cy="email"
                    v-model="consultaProcess.consulta.email"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaProcess.price')">price</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="price"
                    id="consulta-price"
                    data-cy="price"
                    v-model="consultaProcess.consulta.price"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('jhipsterApp.consultaProcess.durationInMinutes')">durationInMinutes</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="durationInMinutes"
                    id="consulta-durationInMinutes"
                    data-cy="durationInMinutes"
                    v-model="consultaProcess.consulta.durationInMinutes"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./consulta-process-details.component.ts"></script>
