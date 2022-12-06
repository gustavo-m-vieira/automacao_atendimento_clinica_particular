<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('jhipsterApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.name')">name</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="name"
                id="consulta-name"
                data-cy="name"
                v-model="taskContext.consultaProcess.consulta.name"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.cpf')">cpf</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cpf"
                id="consulta-cpf"
                data-cy="cpf"
                v-model="taskContext.consultaProcess.consulta.cpf"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.date')">date</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="date"
                id="consulta-date"
                data-cy="date"
                v-model="taskContext.consultaProcess.consulta.date"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.email')">email</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="email"
                id="consulta-email"
                data-cy="email"
                v-model="taskContext.consultaProcess.consulta.email"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.price')">price</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="price"
                id="consulta-price"
                data-cy="price"
                v-model="taskContext.consultaProcess.consulta.price"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.durationInMinutes')">durationInMinutes</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="durationInMinutes"
                id="consulta-durationInMinutes"
                data-cy="durationInMinutes"
                v-model="taskContext.consultaProcess.consulta.durationInMinutes"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/Consulta/task/SelConsulta/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./sel-consulta-details.component.ts"></script>
