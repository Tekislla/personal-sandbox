const express = require('express');
const cors = require('cors');

const app = express();
const keycloak = require('./config/keycloak-config.js').initKeycloak();
app.use(keycloak.middleware());

const index = require('./routes/index');
const productRoute = require('./routes/product-routes');

app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.json({ type: 'application/vnd.api+json' }));
app.use(cors());

app.use(index);
app.use('/api/', productRoute);

module.exports = app;