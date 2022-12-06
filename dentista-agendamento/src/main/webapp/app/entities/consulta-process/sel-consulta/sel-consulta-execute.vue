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
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.name')" for="sel-consulta-name">Name</label>
              <input
                type="text"
                class="form-control"
                name="name"
                id="sel-consulta-name"
                readonly
                data-cy="name"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.name.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.name.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.name.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.cpf')" for="sel-consulta-cpf">Cpf</label>
              <input
                type="text"
                class="form-control"
                name="cpf"
                id="sel-consulta-cpf"
                readonly
                data-cy="cpf"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.cpf.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.cpf.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.cpf.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.date')" for="sel-consulta-date">Date</label>
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="sel-consulta-date"
                    v-model="$v.taskContext.consultaProcess.consulta.date.$model"
                    name="date"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="sel-consulta-date"
                  data-cy="date"
                  type="text"
                  class="form-control"
                  name="date"
                  :class="{
                    valid: !$v.taskContext.consultaProcess.consulta.date.$invalid,
                    invalid: $v.taskContext.consultaProcess.consulta.date.$invalid,
                  }"
                  v-model="$v.taskContext.consultaProcess.consulta.date.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.email')" for="sel-consulta-email">Email</label>
              <input
                type="text"
                class="form-control"
                name="email"
                id="sel-consulta-email"
                readonly
                data-cy="email"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.email.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.email.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.email.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('jhipsterApp.selConsulta.price')" for="sel-consulta-price">Price</label>
              <input
                type="text"
                class="form-control"
                name="price"
                id="sel-consulta-price"
                readonly
                data-cy="price"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.price.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.price.$invalid,
                }"
                v-model="$v.taskContext.consultaProcess.consulta.price.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('jhipsterApp.selConsulta.durationInMinutes')"
                for="sel-consulta-durationInMinutes"
                >Duration In Minutes</label
              >
              <input
                type="number"
                class="form-control"
                name="durationInMinutes"
                id="sel-consulta-durationInMinutes"
                data-cy="durationInMinutes"
                :class="{
                  valid: !$v.taskContext.consultaProcess.consulta.durationInMinutes.$invalid,
                  invalid: $v.taskContext.consultaProcess.consulta.durationInMinutes.$invalid,
                }"
                v-model.number="$v.taskContext.consultaProcess.consulta.durationInMinutes.$model"
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

<script lang="ts" src="./sel-consulta-execute.component.ts"></script>
