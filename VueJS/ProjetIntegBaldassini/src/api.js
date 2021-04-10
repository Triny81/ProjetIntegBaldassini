import axios from 'axios';

const api = axios.create({
  baseURL: 'http://lakartxela.iutbayonne.univ-pau.fr/~fbaldassini/ProjetIntegBaldassini/public/api',
});

export default api;