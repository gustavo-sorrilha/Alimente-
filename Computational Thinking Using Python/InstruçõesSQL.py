import cx_Oracle
cx_Oracle.init_oracle_client(lib_dir=r"C:\Users\gusta\Downloads\instantclient-basic-windows.x64-21.10.0.0.0dbru\instantclient_21_10")

dsn = cx_Oracle.makedsn(host = 'oracle.fiap.com.br', port=1521, sid="ORCL")
conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

try:

    cursor = conn.cursor()

    cursor.execute("""
    CREATE TABLE cliente (
        id_cliente NUMBER(9) CONSTRAINT cliente_id_pk PRIMARY KEY,
        nome VARCHAR2(1000) CONSTRAINT cliente_nome_nn NOT NULL,
        email VARCHAR2(3000) CONSTRAINT cliente_email_nn NOT NULL,
        senha VARCHAR2(100) CONSTRAINT cliente_senha_nn NOT NULL,
        termo NUMBER(1) CONSTRAINT cliente_termo_nn NOT NULL
    )""")

    cursor.execute("""
    CREATE TABLE beneficiario (
        id_cliente NUMBER(9) CONSTRAINT beneficiario_id_pk PRIMARY KEY,
        cpf VARCHAR2(11) CONSTRAINT beneficiario_cpf_nn_uk NOT NULL UNIQUE,
        quantidade_recebida NUMBER CONSTRAINT beneficiario_quantidade_recebida_nn NOT NULL,
        CONSTRAINT beneficiario_id_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
    )""")

    cursor.execute("""
    CREATE TABLE doador (
        id_cliente NUMBER(9) CONSTRAINT doador_id_pk PRIMARY KEY,
        cnpj CHAR(14) CONSTRAINT doador_id_pk_cnpj_nn_uk NOT NULL UNIQUE,
        telefone CHAR(11) CONSTRAINT doador_telefone_nn NOT NULL,
        endereco VARCHAR2(2000) CONSTRAINT doador_endereco_nn NOT NULL,
        quantidade_doada NUMBER(3) CONSTRAINT doador_quantidade_doada_nn NOT NULL,
        CONSTRAINT doador_id_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
    )""")

    cursor.execute("""
    CREATE TABLE tipo_produto (
        id_tipo_produto NUMBER(9) CONSTRAINT tipo_produto_id_pk PRIMARY KEY,
        medida VARCHAR2(100) CONSTRAINT tipo_produto_medida_nn NOT NULL
    )""")

    cursor.execute("""
    CREATE TABLE produto(
        id_produto NUMBER(9) CONSTRAINT produto_id_produto_pk PRIMARY KEY,
        quantidade NUMBER(3) CONSTRAINT produto_quantidade_nn NOT NULL,
        nome_produto VARCHAR2(100) CONSTRAINT produto_nome_produto_nn NOT NULL,
        imagem_produto VARCHAR2(4000) CONSTRAINT produto_imagem_produto_nn NOT NULL,
        data_validade DATE CONSTRAINT produto_data_validade_nn NOT NULL,
        id_tipo_produto NUMBER(9) CONSTRAINT produto_id_tipo_produto_fk REFERENCES tipo_produto
    )""")

    cursor.execute("""
    CREATE TABLE movimentacao (
        id_cliente NUMBER(9) CONSTRAINT movimentacao_cliente_id_fk REFERENCES cliente,
        id_produto NUMBER(9) CONSTRAINT movimentacao_produto_fk REFERENCES produto,
        data_movimentacao DATE CONSTRAINT movimentacao_data_movimentacao_nn NOT NULL,
        id_movimentacao NUMBER(9) CONSTRAINT movimentacao_id_movimentacao_pk PRIMARY KEY
    )""")

    cursor.execute("""
    CREATE TABLE selo (
        id_selo NUMBER(9) CONSTRAINT selo_id_pk PRIMARY KEY,
        id_doador NUMBER(14) CONSTRAINT selo_id_doador_fk REFERENCES doador
    )""")

    cursor.execute("""
    CREATE TABLE doacao (
        id_doacao NUMBER(9) CONSTRAINT doacao_id_pk PRIMARY KEY,
        imagem VARCHAR2(4000) CONSTRAINT doacao_imagem_nn NOT NULL,
        descricao VARCHAR2(4000) CONSTRAINT doacao_descricao_nn NOT NULL,
        disponibilidade NUMBER(1) CONSTRAINT doacao_disponibilidade_nn NOT NULL
    )""")

    cursor.execute("""
    CREATE TABLE doacao_produto (
        id_doacao NUMBER(9) CONSTRAINT doacao_produto_id_doacao_fk REFERENCES doacao,
        id_produto NUMBER(9) CONSTRAINT doacao_produto_id_produto_fk REFERENCES produto
    )""")

    cursor.execute("""
    CREATE TABLE doador_doacao (
        id_doador NUMBER(9) CONSTRAINT doador_doacao_id_doador_fk REFERENCES doador,
        id_doacao NUMBER(9) CONSTRAINT doador_doacao_id_doacao_fk REFERENCES doacao
    )""")

    conn.commit()

finally:
    
    cursor.close()
    conn.close()
