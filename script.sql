
create table cliente(
    id serial not null primary key,
    nome varchar(120) not null,
    endereco varchar(150) not null
);

create table entregador(
    id serial not null primary key,
    nome varchar(120) not null,
    email varchar(100) not null unique,
    telefone varchar(15),
    senha text not null,
 
);

create table pedido(
    id serial not null primary key,
    data date,
    valor double precision not null,
    status varchar(15) not null,
    id_cliente integer not null,
    id_entregador integer null,

    constraint fk_cliente foreign key(id_cliente) references cliente(id),
    constraint fk_entregador foreign key(id_entregador) references entregador(id)
);

create table geolocalizacao(
    id serial not null primary key,
    momento timestamp,
    coordenadas text,
    id_pedido integer not null,
    num_entregador integer not null,

    constraint fk_pedido foreign key(id_pedido) references pedido(id),
    constraint fk_entregador foreign key(num_entregador) references entregador(id)
);