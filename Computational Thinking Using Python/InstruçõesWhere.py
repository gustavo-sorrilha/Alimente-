import cx_Oracle

cx_Oracle.init_oracle_client(lib_dir=r"C:\Users\sorri\Downloads\instantclient-basic-windows.x64-21.10.0.0.0dbru\instantclient_21_10")

dsn = cx_Oracle.makedsn(host = 'oracle.fiap.com.br', port=1521, sid="ORCL")
conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

try:
    
    cursor = conn.cursor()

    # Relatório da tabela cliente
    cursor.execute("SELECT * FROM cliente WHERE id_cliente = 0")
    result_cliente = cursor.fetchall()
    print("Relatório da tabela cliente:")
    for row in result_cliente:
        print(row)

    # Relatório da tabela beneficiario
    cursor.execute("SELECT * FROM beneficiario WHERE id_cliente = 2")
    result_beneficiario = cursor.fetchall()
    print("\nRelatório da tabela beneficiario:")
    for row in result_beneficiario:
        print(row)

    # Relatório da tabela doador
    cursor.execute("SELECT * FROM doador WHERE id_cliente = 11")
    result_doador = cursor.fetchall()
    print("\nRelatório da tabela doador:")
    for row in result_doador:
        print(row)

    # Relatório da tabela tipo_produto
    cursor.execute("SELECT * FROM tipo_produto WHERE id_tipo_produto = 1")
    result_tipo_produto = cursor.fetchall()
    print("\nRelatório da tabela tipo_produto:")
    for row in result_tipo_produto:
        print(row)

    # Relatório da tabela produto
    cursor.execute("SELECT * FROM produto WHERE id_produto = 1")
    result_produto = cursor.fetchall()
    print("\nRelatório da tabela produto:")
    for row in result_produto:
        print(row)

    # Relatório da tabela movimentacao
    cursor.execute("SELECT * FROM movimentacao WHERE id_cliente = 1")
    result_movimentacao = cursor.fetchall()
    print("\nRelatório da tabela movimentacao:")
    for row in result_movimentacao:
        print(row)

    # Relatório da tabela selo
    cursor.execute("SELECT * FROM selo WHERE id_selo = 1")
    result_selo = cursor.fetchall()
    print("\nRelatório da tabela selo:")
    for row in result_selo:
        print(row)

    # Relatório da tabela doacao
    cursor.execute("SELECT * FROM doacao WHERE id_doacao = 1")
    result_doacao = cursor.fetchall()
    print("\nRelatório da tabela doacao:")
    for row in result_doacao:
        print(row)

    # Relatório da tabela doacao_produto
    cursor.execute("SELECT * FROM doacao_produto WHERE id_doacao = 1")
    result_doacao_produto = cursor.fetchall()
    print("\nRelatório da tabela doacao_produto:")
    for row in result_doacao_produto:
        print(row)

    # Relatório da tabela doador_doacao
    cursor.execute("SELECT * FROM doador_doacao WHERE id_doador = 1")
    result_doador_doacao = cursor.fetchall()
    print("\nRelatório da tabela doador_doacao:")
    for row in result_doador_doacao:
        print(row)

finally:
    
    cursor.close()
    conn.close()
