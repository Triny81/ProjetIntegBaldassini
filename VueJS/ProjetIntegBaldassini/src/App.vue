<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-danger">
      <div class="container">
        <router-link to="/" class="navbar-brand text-light">Accueil</router-link>
      </div>
    </nav>
    <div class="container mt-4">
      <router-view />
    </div>
  </div>
</template>