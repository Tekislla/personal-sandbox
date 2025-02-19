const router = require('express-promise-router')();
const productController = require('../controller/product-controller');
const keycloak = require('../config/keycloak-config.js').getKeycloak();

router.post('/products', keycloak.protect(), productController.createProduct);
router.get('/products', keycloak.protect(), productController.listAllProducts);
router.get('/products/:id', keycloak.protect(), productController.findProductById);
router.put('/products/:id', keycloak.protect(), productController.updateProductById);
router.delete('/products/:id', keycloak.protect(), productController.deleteProductById);
router.get('/users', keycloak.protect(), productController.findUsers);
router.post('/users', keycloak.protect(), productController.createUser);

module.exports = router;