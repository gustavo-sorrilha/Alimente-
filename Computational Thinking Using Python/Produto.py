import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Produto:
    def __init__(self, id_produto, quantidade, nome_produto, imagem_produto, data_validade, tipo_produto):
        self.__id_produto = id_produto
        self.__quantidade = quantidade
        self.__nome_produto = nome_produto
        self.__imagem_produto = imagem_produto
        self.__data_validade = data_validade
        self.__tipo_produto = tipo_produto
    
    @property
    def id_produto(self):
        return self.__id_produto
    
    @id_produto.setter
    def id_produto(self, id_produto):
        self.__id_produto = id_produto
    
    @property
    def quantidade(self):
        return self.__quantidade
    
    @quantidade.setter
    def quantidade(self, quantidade):
        self.__quantidade = quantidade
    
    @property
    def nome_produto(self):
        return self.__nome_produto
    
    @nome_produto.setter
    def nome_produto(self, nome_produto):
        self.__nome_produto = nome_produto
    
    @property
    def imagem_produto(self):
        return self.__imagem_produto
    
    @imagem_produto.setter
    def imagem_produto(self, imagem_produto):
        self.__imagem_produto = imagem_produto
    
    @property
    def data_validade(self):
        return self.__data_validade
    
    @data_validade.setter
    def data_validade(self, data_validade):
        self.__data_validade = data_validade
    
    @property
    def tipo_produto(self):
        return self.__tipo_produto
    
    @tipo_produto.setter
    def tipo_produto(self, tipo_produto):
        self.__tipo_produto = tipo_produto

    def cadastrarProduto():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            cursor.execute("SELECT sq_produto.nextval FROM dual")
            id_produto = cursor.fetchone()[0]

            # Solicitar informações do produto
            nome = input("Digite o nome do produto: ")
            quantidade = int(input("Digite a quantidade do produto: "))
            imagem_produto = input("Digite o caminho da imagem do produto: ")
            data_validade = input("Digite a data de validade do produto (YYYY-MM-DD): ")

            # Validar a resposta do ID do tipo de produto
            id_tipo_produto = None
            while id_tipo_produto not in [0, 1, 2, 3, 4]:
                id_tipo_produto = int(input("Digite o ID do tipo do produto (0 - 'Quilograma' 1 - 'Litro' 2 - 'Grama' 3 - 'Mililitro' 4 - 'Unidade'): "))

                if id_tipo_produto not in [0, 1, 2, 3, 4]:
                    print("Opção inválida. Digite um ID de tipo de produto válido.")

            # Executar o comando SQL para cadastrar o produto
            cursor.execute("INSERT INTO Produto (id_produto, quantidade, nome_produto, imagem_produto, data_validade, id_tipo_produto) VALUES (:id_produto, :quantidade, :nome_produto, :imagem_produto, TO_DATE(:data_validade, 'YYYY-MM-DD'), :id_tipo_produto)",
                        {'id_produto': id_produto, 'quantidade': quantidade, 'nome_produto': nome, 'imagem_produto': imagem_produto, 'data_validade': data_validade, 'id_tipo_produto': id_tipo_produto})

            # Confirmar a transação no banco de dados
            conn.commit()

            print("Produto cadastrado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar produto:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()




    def exibirProduto():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            print("----- MENU -----")
            print("1. Procurar produto individualmente")
            print("2. Exibir todos os produtos")
            print("3. Voltar")

            opcao = input("Digite a opção desejada: ")

            if opcao == "1":
                id_produto = int(input("Digite o ID do produto: "))

                cursor.execute("SELECT id_produto, nome_produto, quantidade, imagem_produto, TO_CHAR(data_validade, 'DD-MM-YYYY'), id_tipo_produto FROM produto WHERE id_produto = :id_produto",
                            {'id_produto': id_produto})
                produto = cursor.fetchone()

                if produto:
                    print("Produto encontrado:")
                    print("ID do Produto:", produto[0])
                    print("Nome:", produto[1])
                    print("Quantidade:", produto[2])
                    print("Imagem:", produto[3])
                    print("Data de Validade:", produto[4])
                    print("Tipo:", produto[5])
                else:
                    print("Nenhum produto encontrado com o ID informado.")
            elif opcao == "2":
                
                cursor.execute("SELECT id_produto, nome_produto, quantidade, imagem_produto, TO_CHAR(data_validade, 'DD-MM-YYYY'), id_tipo_produto FROM produto ORDER BY id_produto")

                print("Produtos cadastrados:")
                print("ID     | Nome                  | Quantidade | Imagem           | Data Validade | Tipo")
                print("-------+-----------------------+------------+------------------+---------------+-----")
                for row in cursor:
                    id_produto = row[0]
                    nome_produto = row[1]
                    quantidade = row[2]
                    imagem_produto = row[3]
                    data_validade = row[4]
                    id_tipo_produto = row[5]

                    print(f"{id_produto:6} | {nome_produto:22} | {quantidade:10} | {imagem_produto:17} | {data_validade:13} | {id_tipo_produto}")

            elif opcao == "3":
                return
            else:
                print("Opção inválida.")

        except cx_Oracle.Error as error:
            print("Erro ao exibir produtos:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()




    def editarProduto():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID do produto a ser editado
            id_produto = int(input("Digite o ID do produto a ser editado: "))

            # Executar o comando SQL para verificar se o produto existe
            cursor.execute("SELECT * FROM Produto WHERE id_produto = :id_produto", {'id_produto': id_produto})
            result = cursor.fetchone()

            if result is None:
                print("Produto não encontrado.")
            else:
                # Solicitar as novas informações do produto
                novo_nome = input("Digite o novo nome do produto: ")
                nova_quantidade = int(input("Digite a nova quantidade do produto: "))

                # Executar o comando SQL para atualizar as informações do produto
                cursor.execute("UPDATE Produto SET nome_produto = :nome_produto, quantidade = :quantidade WHERE id_produto = :id_produto",
                            {'nome_produto': novo_nome, 'quantidade': nova_quantidade, 'id_produto': id_produto})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Produto atualizado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar produto:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()


    def excluirProduto():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID do produto a ser excluído
            id_produto = int(input("Digite o ID do produto a ser excluído: "))

            # Executar o comando SQL para verificar se o produto existe
            cursor.execute("SELECT * FROM Produto WHERE id_produto = :id_produto", {'id_produto': id_produto})
            result = cursor.fetchone()

            if result is None:
                print("Produto não encontrado.")
            else:
                # Executar o comando SQL para excluir o produto
                cursor.execute("DELETE FROM Produto WHERE id_produto = :id_produto", {'id_produto': id_produto})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Produto excluído com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir produto:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()