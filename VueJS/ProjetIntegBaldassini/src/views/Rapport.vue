<template>
  <div class="container">
    <h1>
      Intervention de {{ rapport.prenomTechnicien }} {{ rapport.nomTechnicien }}
    </h1>
    <br />
    <ul class="list-group text-center">
      <li class="list-group-item list-group-item-primary">
        Informations client
      </li>
      <li class="list-group-item">
        Nom : {{ rapport.nomClient }} <br />
        Prénom : {{ rapport.prenomClient }} <br />
        Adresse : {{ rapport.adresse }}
      </li>
    </ul>
    <br />
    <ul class="list-group text-center">
      <li class="list-group-item list-group-item-info">
        Informations chaudière
      </li>
      <li class="list-group-item">
        Marque : {{ rapport.marqueChaudiere }} <br />
        Modèle : {{ rapport.modeleChaudiere }} <br />
        Numéro de série : {{ rapport.numSerie }} <br />
        Date de mise en service : {{ format_date(rapport.dateMiseService) }}
      </li>
    </ul>
    <br />
    <ul class="list-group text-center">
      <li class="list-group-item list-group-item-success">
        Informations intervention
      </li>
      <li class="list-group-item">
        Date de l'intervention : {{ format_date(rapport.dateIntervention) }} <br />
        Temps passé : {{ format_hour(rapport.temps) }} <br />
        Description : {{ rapport.description }}
      </li>
    </ul>
  </div>
</template>

<script>
import api from "../api.js";
import moment from "moment";

export default {
  props: ["id"],
  data() {
    return {
      loading: false,
      rapport: {},
    };
  },
  mounted() {
    this.refreshRapport();
  },
  methods: {
    refreshRapport() {
      this.loading = true;
      api
        .get("rapport/" + this.id)
        .then((response) => {
          this.rapport = response.data.data;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    format_date(value) {
      if (value) {
        return moment(value).format("DD/MM/YYYY");
      }
    },
    format_hour(value) {
      if (value) {
        return moment(value, "HH:mm:ss").format("HH:mm");
      }
    },
  },
};
</script>
