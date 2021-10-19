DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  imagePath VARCHAR(250) DEFAULT NULL,
  thumbnailPath VARCHAR(250) DEFAULT NULL
);

INSERT INTO products (name, description, imagePath, thumbnailPath) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist', ''),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur',''),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate','');