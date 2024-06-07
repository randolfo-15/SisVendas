CREATE TABLE User (
  id    INTEGER     PRIMARY KEY AUTOINCREMENT,
  name  TEXT        NOT NULL,
  uname TEXT        NOT NULL,
  email TEXT        UNIQUE NOT NULL,
  phone VARCHAR(18) UNIQUE NOT NULL,
  passw TEXT        UNIQUE NOT NULL,
  adm   INTEGER     DEFAULT 0
);

INSERT INTO User (name,uname, email, phone, passw, adm) VALUES
('Randolfo','Rag', 'randolfo@gmail.com', '31981059465', '654321', 1);

INSERT INTO User (name,uname, email, phone, passw, adm) VALUES
('Pablo Rangel Abreu Andrade','Pablo', 'pablo.rangel2506@gmail.com', '(31)991919086', '123456', 1);

CREATE TABLE Product(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name      TEXT  NOT NULL,
    category  TEXT,
    code      TEXT  NOT NULL UNIQUE,
    price     FLOAT NOT NULL CHECK (price >= 0),
    amount    INT   CHECK (amount >= 0)
);

INSERT INTO Product (name,category,code,price,amount) VALUES ('Café 3 corações','Bebidas','1212123',13.45,4);
