create table carts (id_cart bigint not null auto_increment, data_acquisto date, data_pagamento date, pagato varchar(255), price double precision, id_customer bigint, primary key (id_cart)) engine=InnoDB;
create table customers (id bigint not null auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), type varchar(255), primary key (id)) engine=InnoDB;
create table products (id_product bigint not null auto_increment, name_product varchar(255), price double precision, cart_id bigint, supplier_id bigint, primary key (id_product)) engine=InnoDB;
create table suppliers (id_supplier bigint not null auto_increment, name varchar(255), p_iva varchar(255), primary key (id_supplier)) engine=InnoDB;
alter table carts add constraint FK8tqkocddyymf7fdk4f5wictd0 foreign key (id_customer) references customers (id);
alter table products add constraint FKsivjj1mvubbgnyp9i8jxjacbp foreign key (cart_id) references carts (id_cart);
alter table products add constraint FK6i174ixi9087gcvvut45em7fd foreign key (supplier_id) references suppliers (id_supplier);
