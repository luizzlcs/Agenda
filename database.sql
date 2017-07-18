create database if not exists db_agenda;

use db_agenda;

create table if not exists tb_contatos (
	con_id int not null auto_increment,
	con_nome varchar(100) not null,
	con_fone varchar(11) not null,
	constraint pk_contatos primary key (con_id)
);

insert into tb_contatos 
	(con_nome, con_fone) 
values 
	('Carlos Augusto', '8432322514'),
	('Maria José', '84988772255');

select * from tb_contatos;