<template>
  <div class="container">
    <h1>Liste des interventions</h1>
    <form class="searchbar row mt-3 mb-3" @submit.prevent="onSubmit">
      <div class="input-group w-50 col-6">
        <input
          type="text"
          class="form-control"
          placeholder="Rechercher un nom de technicien"
          v-model="searchNom"
        />
      </div>
      <div class="col-4">
        <input class="form-control" type="date" v-model="searchDate" />
      </div>
      <div class="col-2">
        <button class="btn btn-primary" type="sumbit">Rechercher</button>
      </div>
    </form>

    <i class="fas fa-sync fa-spin" v-if="loading"></i>
    
    <div v-else class="row">
      <div class="col col-lg-4 mb-3" v-for="rapport in rapports" :key="rapport.id" >
        <router-link :to="{ name: 'Rapport', params: { id: rapport.id } }">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">
                Intervention de {{ rapport.nomTechnicien }}
                {{ rapport.prenomTechnicien }}
              </h5>
              <p class="card-text">
                Client : {{ rapport.nomClient }} {{ rapport.prenomClient }}
              </p>
              <p class="card-text">
                Intervenu le : {{ format_date(rapport.dateIntervention) }}
              </p>
            </div>
          </div>
        </router-link>
      </div>
      <div v-if="empty">Aucune intervention n'a été trouvée</div>
    </div>
  </div>
</template>

<script>
import api from "../api.js";
import moment from "moment";

export default {
  data() {
    return {
      loading: false,
      rapports: [],
      searchNom: "",
      searchDate: "",
      empty: false,
    };
  },
  mounted() {
    this.refreshRapports();
  },
  methods: {
    onSubmit() {
      this.refreshRapports();
    },
    refreshRapports() {
      this.loading = true;
      this.empty = false;
      api
        .get("rapport")
        .then((response) => {
          if (this.searchNom && this.searchDate) {
            this.rapports = response.data.data.filter(
              (rapport) =>
                rapport.dateIntervention
                  .toLowerCase()
                  .includes(this.searchDate) &&
                rapport.nomTechnicien.toLowerCase().includes(this.searchNom)
            );
          } else if (this.searchNom) {
            this.rapports = response.data.data.filter((rapport) =>
              rapport.nomTechnicien.toLowerCase().includes(this.searchNom)
            );
          } else if (this.searchDate) {
            this.rapports = response.data.data.filter((rapport) =>
              rapport.dateIntervention.toLowerCase().includes(this.searchDate)
            );
          } else {
            this.rapports = response.data.data;
          }
        })
        .finally(() => {
          this.loading = false;
          if(this.rapports.length == 0){
            this.empty = true;
          }
        });
    },
    format_date(value) {
      if (value) {
        return moment(value).format("DD/MM/YYYY");
      }
    },
  },
};
</script>

<style>


.card:hover {
  box-shadow: 2px 2px 2px  red;
  transform: scale(1.1);
  color: black;
}

.card {
  border: 1px solid black;
  transition: 0.1s;
}

a {
  text-decoration: none;
  color: black;
}
</style>