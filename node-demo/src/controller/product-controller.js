const db = require("../config/database");
const Request = require("request");


exports.createProduct = async (req, res) => {
  const { productname, quantity, price } = req.body;
  const { rows } = await db.query(
    "INSERT INTO products (productname, quantity, price) VALUES ($1, $2, $3)",
    [productname, quantity, price]
  );

  res.status(201).send({
    message: "Product added successfully!",
    body: {
      product: { productname, quantity, price }
    },
  });
};

exports.listAllProducts = async (req, res) => {
    const response = await db.query('SELECT * FROM products ORDER BY productid ASC');
    res.status(200).send(response.rows);
};

exports.findProductById = async (req, res) => {
    const productId = parseInt(req.params.id);
    const response = await db.query('SELECT * FROM products WHERE productid = $1', [productId]);
    res.status(200).send(response.rows);
};

exports.updateProductById = async (req, res) => {
    const productId = parseInt(req.params.id);
    const { productname, quantity, price } = req.body;
  
    const response = await db.query(
      "UPDATE products SET productname = $1, quantity = $2, price = $3 WHERE productId = $4",
      [productname, quantity, price, productId]
    );
  
    res.status(200).send({ message: "Product Updated Successfully!" });
};

exports.deleteProductById = async (req, res) => {
    const productId = parseInt(req.params.id);
    await db.query('DELETE FROM products WHERE productId = $1', [
      productId
    ]);
  
    res.status(200).send({ message: 'Product deleted successfully!', productId });
};


exports.findUsers = async (req, res) => {
Request.get("http://localhost:6000/dbtest/user/find", (error, response, body) => {
  if(error) {
      return console.dir(error);
  }
  res.status(200).send(JSON.parse(body));
});
};

exports.createUser = async (req, res) => {
  const { name, email} = req.body;
  Request.post({
    "headers": { "content-type": "application/json" },
    "url": "http://localhost:6000/dbtest/user/save",
    "body": JSON.stringify({ name, email })
}, (error, response, body) => {
    if(error) {
        console.dir({name, email})
        return console.dir(error);
    }
    res.status(200).send({ message: "User Created Successfully!" });
});
}