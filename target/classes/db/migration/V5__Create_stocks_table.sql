create table stocks (
    id int unsigned primary key auto_increment,
    store_id int not null,
    FOREIGN KEY(store_id) REFERENCES stores(id),
    product_id int not null,
    FOREIGN KEY(product_id) REFERENCES products(id),
    quantity int
);