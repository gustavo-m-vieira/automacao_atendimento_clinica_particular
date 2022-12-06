<template>
  <div>
    <h2 id="page-heading" data-cy="ConsultaHeading">
      <span v-text="$t('jhipsterApp.consulta.home.title')" id="consulta-heading">Consultas</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('jhipsterApp.consulta.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && consultas && consultas.length === 0">
      <span v-text="$t('jhipsterApp.consulta.home.notFound')">No consultas found</span>
    </div>
    <div class="table-responsive" v-if="consultas && consultas.length > 0">
      <table class="table table-striped" aria-describedby="consultas">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.consulta.name')">Name</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.consulta.cpf')">Cpf</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.consulta.date')">Date</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.consulta.email')">Email</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.consulta.price')">Price</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.consulta.durationInMinutes')">Duration In Minutes</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="consulta in consultas" :key="consulta.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ConsultaView', params: { consultaId: consulta.id } }">{{ consulta.id }}</router-link>
            </td>
            <td>{{ consulta.name }}</td>
            <td>{{ consulta.cpf }}</td>
            <td>{{ consulta.date }}</td>
            <td>{{ consulta.email }}</td>
            <td>{{ consulta.price }}</td>
            <td>{{ consulta.durationInMinutes }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ConsultaView', params: { consultaId: consulta.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="jhipsterApp.consulta.delete.question" data-cy="consultaDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-consulta-heading" v-text="$t('jhipsterApp.consulta.delete.question', { id: removeId })">
          Are you sure you want to delete this Consulta?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-consulta"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeConsulta()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./consulta.component.ts"></script>
