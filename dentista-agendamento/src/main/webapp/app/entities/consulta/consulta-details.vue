<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="consulta">
        <h2 class="jh-entity-heading" data-cy="consultaDetailsHeading">
          <span v-text="$t('jhipsterApp.consulta.detail.title')">Consulta</span> {{ consulta.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('jhipsterApp.consulta.name')">Name</span>
          </dt>
          <dd>
            <span>{{ consulta.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.consulta.cpf')">Cpf</span>
          </dt>
          <dd>
            <span>{{ consulta.cpf }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.consulta.date')">Date</span>
          </dt>
          <dd>
            <span>{{ consulta.date }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.consulta.email')">Email</span>
          </dt>
          <dd>
            <span>{{ consulta.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.consulta.price')">Price</span>
          </dt>
          <dd>
            <span>{{ consulta.price }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.consulta.durationInMinutes')">Duration In Minutes</span>
          </dt>
          <dd>
            <span>{{ consulta.durationInMinutes }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./consulta-details.component.ts"></script>
