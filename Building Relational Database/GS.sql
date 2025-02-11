-- CRIANDO A TABELA cliente -
CREATE TABLE cliente (
    id_cliente NUMBER(9) CONSTRAINT cliente_id_pk PRIMARY KEY,
    nome VARCHAR2(1000) CONSTRAINT cliente_nome_nn NOT NULL,
    email VARCHAR(3000) CONSTRAINT cliente_email_nn NOT NULL,
    senha VARCHAR2(100) CONSTRAINT cliente_senha_nn NOT NULL,
    termo NUMBER(1) CONSTRAINT cliente_termo_nn NOT NULL
);

CREATE SEQUENCE sq_cliente START WITH 1 INCREMENT BY 1;

-- INSERINDO DADOS NA TABELA cliente
INSERT INTO cliente VALUES (0, 'Vitor Rubim', 'vitor@email.com', '1234', 1);
INSERT INTO cliente VALUES (1, 'Gustavo Sorrilha', 'gustavo@email.com', '1234', 0);
INSERT INTO cliente VALUES (2, 'Natan Cruz', 'natan@email.com', '1234', 0);
INSERT INTO cliente VALUES (3, 'Felipe Merlo', 'felipe@email.com', '1234', 1);
INSERT INTO cliente VALUES (4, 'João Silva', 'joao@email.com', '5678', 1);
INSERT INTO cliente VALUES (5, 'Maria Santos', 'maria@email.com', '9012', 0);
INSERT INTO cliente VALUES (6, 'Pedro Almeida', 'pedro@email.com', '3456', 1);
INSERT INTO cliente VALUES (7, 'Ana Oliveira', 'ana@email.com', '7890', 0);
INSERT INTO cliente VALUES (8, 'Carlos Souza', 'carlos@email.com', '2345', 1);
INSERT INTO cliente VALUES (9, 'Laura Pereira', 'laura@email.com', '6789', 0);
INSERT INTO cliente VALUES (10, 'Rafaela Mendes', 'rafaela@email.com', '4567', 0);
INSERT INTO cliente VALUES (11, 'Lucas Ferreira', 'lucas@email.com', '8901', 1);
INSERT INTO cliente VALUES (12, 'Juliana Silva', 'juliana@email.com', '2345', 0);
INSERT INTO cliente VALUES (13, 'Bruno Oliveira', 'bruno@email.com', '6789', 1);
INSERT INTO cliente VALUES (14, 'Mariana Santos', 'mariana@email.com', '0123', 0);
INSERT INTO cliente VALUES (15, 'Luciana Costa', 'luciana@email.com', '3456', 1);
INSERT INTO cliente VALUES (16, 'Fernando Mendonça', 'fernando@email.com', '6789', 0);
INSERT INTO cliente VALUES (17, 'Vanessa Santos', 'vanessa@email.com', '0123', 0);
INSERT INTO cliente VALUES (18, 'Ricardo Alves', 'ricardo@email.com', '4567', 1);
INSERT INTO cliente VALUES (19, 'Patrícia Oliveira', 'patricia@email.com', '8901', 0);

-- CRIANDO A TABELA BENEFECIARIO --
CREATE TABLE beneficiario (
    id_cliente NUMBER(9) CONSTRAINT beneficiario_id_pk PRIMARY KEY, CONSTRAINT beneficiario_id_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    cpf VARCHAR2(11) CONSTRAINT beneficiario_cpf_nn_uk NOT NULL UNIQUE,
    quantidade_recebida NUMBER(3) CONSTRAINT beneficiario_quantidade_recebida_nn NOT NULL
);

-- INSERINDO DADOS NA TABELA BENEFECIARIO
INSERT INTO beneficiario VALUES(0, '52118238851', 2);
INSERT INTO beneficiario VALUES(1, '98567123098', 3);
INSERT INTO beneficiario VALUES(2, '77098654321', 1);
INSERT INTO beneficiario VALUES(3, '24567098761', 4);
INSERT INTO beneficiario VALUES(4, '89901235678', 0);
INSERT INTO beneficiario VALUES(5, '34567890123', 2);
INSERT INTO beneficiario VALUES(6, '71234567890', 3);
INSERT INTO beneficiario VALUES(7, '98765432109', 1);
INSERT INTO beneficiario VALUES(8, '45678901234', 4);
INSERT INTO beneficiario VALUES(9, '12345678901', 0);

-- CRIANDO A TABELA doador --
CREATE TABLE doador (
    id_cliente NUMBER(9) CONSTRAINT doador_id_pk PRIMARY KEY, CONSTRAINT doador_id_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    cnpj CHAR(14) CONSTRAINT doador_id_pk_cnpj_nn_uk NOT NULL UNIQUE,
    telefone CHAR(11) CONSTRAINT doador_telefone_nn NOT NULL,
    endereco VARCHAR2(2000) CONSTRAINT doador_endereco_nn NOT NULL,
    quantidade_doada NUMBER(3) CONSTRAINT doador_quantidade_doada_nn NOT NULL
);

INSERT INTO doador VALUES (10, '12345678000100', '987654321', 'Av. Paulista, 1001', 0);
INSERT INTO doador VALUES (11, '23456789000199', '876543210', 'Rua Augusta, 500', 2);
INSERT INTO doador VALUES (12, '34567890000188', '765432109', 'Av. Faria Lima, 2000', 0);
INSERT INTO doador VALUES (13, '45678901000177', '654321098', 'Rua Oscar Freire, 800', 0);
INSERT INTO doador VALUES (14, '56789010000166', '543210987', 'Av. Atlântica, 1500', 3);
INSERT INTO doador VALUES (15, '67890111000155', '432109876', 'Rua Padre João Manuel, 100', 0);
INSERT INTO doador VALUES (16, '78901222000144', '321098765', 'Av. Brigadeiro Faria Lima, 3000', 1);
INSERT INTO doador VALUES (17, '89012333000133', '210987654', 'Rua Itapeva, 500', 0);
INSERT INTO doador VALUES (18, '90123444000122', '109876543', 'Av. Presidente Juscelino Kubitschek, 1500', 0);
INSERT INTO doador VALUES (19, '01234555000111', '098765432', 'Rua Bela Cintra, 700', 5);

-- CRIANDO A TABELA TIPO PRODUTO --
CREATE TABLE tipo_produto (
    id_tipo_produto NUMBER(9) CONSTRAINT tipo_produto_id_pk PRIMARY KEY,
    medida VARCHAR2(100) CONSTRAINT tipo_produto_medida_nn NOT NULL
);

CREATE SEQUENCE sq_tipo_produto START WITH 1 INCREMENT BY 1;


INSERT INTO tipo_produto VALUES (0, 'Quilograma');
INSERT INTO tipo_produto VALUES (1, 'Litro');
INSERT INTO tipo_produto VALUES (2, 'Grama');
INSERT INTO tipo_produto VALUES (3, 'Mililitro');
INSERT INTO tipo_produto VALUES (4, 'Unidade');
INSERT INTO tipo_produto VALUES (5, 'TESTE 01');
INSERT INTO tipo_produto VALUES (7, 'TESTE 02');
INSERT INTO tipo_produto VALUES (8, 'TESTE 03');
INSERT INTO tipo_produto VALUES (9, 'TESTE 04');
INSERT INTO tipo_produto VALUES (10, 'TESTE 05');


CREATE SEQUENCE sq_produto START WITH 1 INCREMENT BY 1;


-- CRIANDO A TABELA PRODUTO --
CREATE TABLE produto(
    id_produto NUMBER(9) CONSTRAINT produto_id_produto_pk PRIMARY KEY,
    quantidade NUMBER(3) CONSTRAINT produto_quantidade_nn NOT NULL,
    nome_produto VARCHAR2(100) CONSTRAINT produto_nome_produto_nn NOT NULL,
    imagem_produto VARCHAR2(4000) CONSTRAINT produto_imagem_produto_nn NOT NULL,
    data_validade DATE CONSTRAINT produto_data_validade_nn NOT NULL,
    id_tipo_produto NUMBER(9)CONSTRAINT produto_id_tipo_produto_fk REFERENCES tipo_produto
);

INSERT INTO produto VALUES (1, 5, 'Arroz', 'https://exemplo.com/arroz.jpg', TO_DATE('2023-12-31', 'YYYY-MM-DD'), 0);
INSERT INTO produto VALUES (2, 3, 'Feijão', 'https://exemplo.com/feijao.jpg', TO_DATE('2023-11-30', 'YYYY-MM-DD'), 0);
INSERT INTO produto VALUES (3, 2, 'Macarrão', 'https://exemplo.com/macarrao.jpg', TO_DATE('2023-10-31', 'YYYY-MM-DD'), 0);
INSERT INTO produto VALUES (4, 8, 'Leite', 'https://exemplo.com/leite.jpg', TO_DATE('2023-09-30', 'YYYY-MM-DD'), 1);
INSERT INTO produto VALUES (5, 6, 'Óleo de Soja', 'https://exemplo.com/oleo_soja.jpg', TO_DATE('2023-08-31', 'YYYY-MM-DD'), 1);
INSERT INTO produto VALUES (6, 4, 'Açúcar', 'https://exemplo.com/acucar.jpg', TO_DATE('2023-07-31', 'YYYY-MM-DD'), 0);
INSERT INTO produto VALUES (7, 9, 'Café', 'https://exemplo.com/cafe.jpg', TO_DATE('2023-06-30', 'YYYY-MM-DD'), 3);
INSERT INTO produto VALUES (8, 7, 'Feijão Preto', 'https://exemplo.com/feijao_preto.jpg', TO_DATE('2023-05-31', 'YYYY-MM-DD'), 0);
INSERT INTO produto VALUES (9, 9, 'Farinha de Trigo', 'https://exemplo.com/farinha_trigo.jpg', TO_DATE('2023-04-30', 'YYYY-MM-DD'), 2);
INSERT INTO produto VALUES (10, 1, 'Sal', 'https://exemplo.com/sal.jpg', TO_DATE('2023-03-31', 'YYYY-MM-DD'), 4);

CREATE SEQUENCE sq_movimentacao START WITH 1 INCREMENT BY 1;

-- CRIANDO A TABELA MOVIMENTAÇÃO --
CREATE TABLE movimentacao (
    id_cliente NUMBER(9) CONSTRAINT movimentacao_cliente_id_fk REFERENCES cliente,
    id_produto NUMBER(9) CONSTRAINT movimentacao_produto_fk REFERENCES produto,
    data_movimentacao DATE CONSTRAINT movimentacao_data_movimentacao_nn NOT NULL,
    id_movimentacao NUMBER(9) CONSTRAINT movimentacao_id_movimentacao_pk PRIMARY KEY
);

INSERT INTO movimentacao VALUES (1, 1, TO_DATE('2023-01-01', 'YYYY-MM-DD'), 1);
INSERT INTO movimentacao VALUES (1, 2, TO_DATE('2023-02-05', 'YYYY-MM-DD'), 2);
INSERT INTO movimentacao VALUES (2, 3, TO_DATE('2023-03-10', 'YYYY-MM-DD'), 3);
INSERT INTO movimentacao VALUES (2, 4, TO_DATE('2023-04-15', 'YYYY-MM-DD'), 4);
INSERT INTO movimentacao VALUES (3, 5, TO_DATE('2023-05-20', 'YYYY-MM-DD'), 5);
INSERT INTO movimentacao VALUES (3, 6, TO_DATE('2023-06-25', 'YYYY-MM-DD'), 6);
INSERT INTO movimentacao VALUES (4, 7, TO_DATE('2023-07-30', 'YYYY-MM-DD'), 7);
INSERT INTO movimentacao VALUES (5, 9, TO_DATE('2023-09-09', 'YYYY-MM-DD'), 8);
INSERT INTO movimentacao VALUES (6, 10, TO_DATE('2023-10-14', 'YYYY-MM-DD'), 9);
INSERT INTO movimentacao VALUES (9, 8, TO_DATE('2023-08-04', 'YYYY-MM-DD'), 10);

CREATE SEQUENCE sq_selo START WITH 1 INCREMENT BY 1;

-- CRIANDO A TABELA SELO --
CREATE TABLE selo (
    id_selo NUMBER(9) CONSTRAINT selo_id_pk PRIMARY KEY,
    id_doador NUMBER(9) CONSTRAINT selo_id_doador_fk REFERENCES doador
);

INSERT INTO selo VALUES (0, 10);
INSERT INTO selo VALUES (1, 11);
INSERT INTO selo VALUES (2, 12);
INSERT INTO selo VALUES (3, 13);
INSERT INTO selo VALUES (4, 14);
INSERT INTO selo VALUES (5, 15);
INSERT INTO selo VALUES (6, 16);
INSERT INTO selo VALUES (7, 17);
INSERT INTO selo VALUES (8, 18);
INSERT INTO selo VALUES (9, 19);

CREATE SEQUENCE sq_doacao START WITH 1 INCREMENT BY 1;

-- CRIANDO A TABELA DOAÇÃO ---
CREATE TABLE doacao (
    id_doacao NUMBER(9) CONSTRAINT doacao_id_pk PRIMARY KEY,
    imagem VARCHAR2(4000) CONSTRAINT doacao_imagem_nn NOT NULL,
    descricao VARCHAR2(4000) CONSTRAINT doacao_descricao_nn NOT NULL,
    disponibilidade NUMBER(1) CONSTRAINT doacao_disponibilidade_nn NOT NULL
);

INSERT INTO doacao VALUES (1, 'https://exemplo.com/imagem1.jpg', 'Doação de 10 kg de arroz e 5 kg de feijão para auxiliar famílias em situação de vulnerabilidade social.', 1);
INSERT INTO doacao VALUES (2, 'https://exemplo.com/imagem2.jpg', 'Doação de 20 pacotes de macarrão e 15 latas de conservas para ajudar comunidades carentes.', 1);
INSERT INTO doacao VALUES (3, 'https://exemplo.com/imagem3.jpg', 'Doação de 30 litros de leite e 50 pães frescos para instituições que acolhem pessoas em necessidade.', 0);
INSERT INTO doacao VALUES (4, 'https://exemplo.com/imagem4.jpg', 'Doação de cestas de frutas e legumes orgânicos para apoiar comunidades de baixa renda.', 1);
INSERT INTO doacao VALUES (5, 'https://exemplo.com/imagem5.jpg', 'Doação de carnes e aves frescas para contribuir com refeitórios sociais.', 1);
INSERT INTO doacao VALUES (6, 'https://exemplo.com/imagem6.jpg', 'Doação de pães frescos e bolos caseiros para auxiliar abrigos de animais.', 0);
INSERT INTO doacao VALUES (7, 'https://exemplo.com/imagem7.jpg', 'Doação de alimentos enlatados e água potável para ajudar pessoas em situações de emergência.', 1);
INSERT INTO doacao VALUES (8, 'https://exemplo.com/imagem8.jpg', 'Doação de kits com alimentos não perecíveis para famílias desabrigadas.', 0);
INSERT INTO doacao VALUES (9, 'https://exemplo.com/imagem9.jpg', 'Doação de frutas e verduras frescas para programas de combate à fome e nutrição.', 1);
INSERT INTO doacao VALUES (10, 'https://exemplo.com/imagem10.jpg', 'Doação de diversos produtos alimentícios, como enlatados, massas, óleo e açúcar, para instituições de assistência social.', 0);

-- CRIANDO A TABELA DOACAO_PRODUTO  --
CREATE TABLE doacao_produto (
    id_doacao NUMBER(9) CONSTRAINT doacao_produto_id_doacao_fk REFERENCES doacao,
    id_produto NUMBER(9) CONSTRAINT doacao_produto_id_produto_fk REFERENCES produto
);

INSERT INTO doacao_produto VALUES (1, 3);
INSERT INTO doacao_produto VALUES (1, 6);
INSERT INTO doacao_produto VALUES (2, 4);
INSERT INTO doacao_produto VALUES (2, 5);
INSERT INTO doacao_produto VALUES (3, 8);
INSERT INTO doacao_produto VALUES (4, 7);
INSERT INTO doacao_produto VALUES (4, 10);
INSERT INTO doacao_produto VALUES (5, 2);
INSERT INTO doacao_produto VALUES (5, 6);
INSERT INTO doacao_produto VALUES (6, 3);
INSERT INTO doacao_produto VALUES (7, 7);
INSERT INTO doacao_produto VALUES (8, 8);
INSERT INTO doacao_produto VALUES (9, 1);

-- CRIANDO A TABELA doador_doacao --
CREATE TABLE doador_doacao (
    id_doador NUMBER(9) CONSTRAINT doador_doacao_id_doador_fk REFERENCES doador,
    id_doacao NUMBER(9) CONSTRAINT doador_doacao_id_doacao_fk REFERENCES doacao
);

INSERT INTO doador_doacao VALUES (10, 1);
INSERT INTO doador_doacao VALUES (10, 2);
INSERT INTO doador_doacao VALUES (11, 3);
INSERT INTO doador_doacao VALUES (11, 4);
INSERT INTO doador_doacao VALUES (12, 5);
INSERT INTO doador_doacao VALUES (12, 6);
INSERT INTO doador_doacao VALUES (13, 7);
INSERT INTO doador_doacao VALUES (14, 8);
INSERT INTO doador_doacao VALUES (14, 9);
INSERT INTO doador_doacao VALUES (15, 10);

-- Essa consulta SQL retorna todas as colunas das tabelas "cliente" e "beneficiario" para os registros em que o valor da coluna "id_cliente" da tabela "cliente" seja igual a 5. Ela realiza uma junção entre as tabelas "cliente" e "beneficiario" usando a condição de igualdade entre as colunas "id_cliente" das duas tabelas.
SELECT *
FROM cliente
JOIN beneficiario ON cliente.id_cliente = beneficiario.id_cliente
WHERE cliente.id_cliente = 5;

-- A consulta SQL seleciona todos os clientes cujos nomes começam com a letra "F" na tabela "cliente". Os resultados são ordenados em ordem crescente pelo ID do cliente.
SELECT * FROM cliente
WHERE nome LIKE 'F%'
ORDER BY id_cliente;

-- Recuperar quais são os produtos da doação com id "1", conta a quantidade de produtos e lista agrupando e ordenando os resultados pelo nome do produto. 
SELECT COUNT(p.id_produto) AS quantidade_produtos, p.nome_produto
FROM doacao d
JOIN doacao_produto dp ON d.id_doacao = dp.id_doacao
JOIN produto p ON dp.id_produto = p.id_produto
WHERE d.id_doacao = 1
GROUP BY p.nome_produto ORDER BY p.NOME_PRODUTO;

-- Recuperar o ID da doação e a quantidade de produtos associados, filtrando apenas as doações que possuem mais de um produto, usando GROUP BY e HAVING.
SELECT d.id_doacao, d.descricao, COUNT(p.id_produto) AS quantidade_produtos
FROM doacao d
JOIN doacao_produto dp ON d.id_doacao = dp.id_doacao
JOIN produto p ON dp.id_produto = p.id_produto
GROUP BY d.id_doacao, d.descricao
HAVING COUNT(p.id_produto) > 1;

-- Recupera e agrupa os clientes por nome e o número total de movimentações associadas a cada um deles, apenas para os clientes que possuem pelo menos uma movimentação registrada.
SELECT c.nome, COUNT(m.id_movimentacao) AS total_movimentacoes
FROM cliente c
JOIN movimentacao m ON c.id_cliente = m.id_cliente
GROUP BY c.nome
HAVING COUNT(m.id_movimentacao) >= 1;

-- Recuperar o nome do cliente, CPF do beneficiário e quantidade recebida, combinando dados das tabelas cliente e beneficiario usando uma junção.
SELECT c.nome, b.cpf, b.quantidade_recebida FROM cliente c JOIN beneficiario b ON c.id_cliente = b.id_cliente;

-- Recuperar o nome do doador e a quantidade de doações feitas por cada doador, filtrando apenas os doadores que fizeram pelo menos uma doação.
SELECT c.nome AS "DOADOR", d.quantidade_doada AS "QUANTIDADE DE DOAÇÕES FEITAS"
FROM doador d
JOIN cliente c ON c.id_cliente = d.id_cliente
WHERE d.quantidade_doada > 0;


COMMIT;
