insert into estado (nome) values ('mg');
insert into estado (nome) values ('sp');
insert into estado (nome) values ('to');
insert into estado (nome) values ('pr');
insert into cidade (nome,estado_id) values ('paracatu', 1)
insert into cidade (nome,estado_id) values ('sao paulo', 2)
insert into cidade (nome,estado_id) values ('senador', 1)
insert into cidade (nome,estado_id) values ('uba', 4)
insert into cliente (cpf, nome, telefone, email,data_nascimento, cidade_id) values ('11111111111', 'Josimar', '22555554444','josimar@gmail.com','2003-02-12', 1);
insert into cliente (cpf, nome, telefone, email,data_nascimento,cidade_id) values ('22222222222', 'Maria', '22555554445','maria@gmail.com','2004-02-12',2);
insert into cliente (cpf, nome, telefone, email,data_nascimento,cidade_id) values ('33333333333', 'Jose', '22555554446','jose@gmail.com','2004-02-12',3);
insert into produto (nome, qtd_estoque, valor_unitario) values ("macã", "10","2.20");
insert into produto (nome, qtd_estoque, valor_unitario) values ("alface", "10","3.50");
insert into produto (nome, qtd_estoque, valor_unitario) values ("tomate", "10","7.20");
insert into produto (nome, qtd_estoque, valor_unitario) values ("pão", "10","5");
