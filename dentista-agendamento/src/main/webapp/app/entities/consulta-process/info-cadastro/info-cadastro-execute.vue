<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('jhipsterApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.infoCadastro.name')" for="info-cadastro-name">Name</label>
              <input
                type="text"
                class="form-control"
                name="name"
                id="info-cadastro-name"
                data-cy="name"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.name.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.name.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.name.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.infoCadastro.cpf')" for="info-cadastro-cpf">Cpf</label>
              <input
                type="text"
                class="form-control"
                name="cpf"
                id="info-cadastro-cpf"
                data-cy="cpf"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.cpf.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.cpf.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.cpf.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.infoCadastro.email')" for="info-cadastro-email">Email</label>
              <input
                type="text"
                class="form-control"
                name="email"
                id="info-cadastro-email"
                readonly
                data-cy="email"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.email.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.email.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.email.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./info-cadastro-execute.component.ts"></script>
