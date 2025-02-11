import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

from Produto import Produto
from Usuario import Usuario



class Movimentacao(Produto, Usuario):
    def __init__(self, id_cliente, id_produto, data_movimentacao, id_movimentacao):
        super().__init__(id_produto)  
        Usuario.__init__(self, id_cliente)  
        self.__data_movimentacao = data_movimentacao
        self.__id_movimentacao = id_movimentacao
    
    @property
    def data_movimentacao(self):
        return self.__data_movimentacao
    
    @data_movimentacao.setter
    def data_movimentacao(self, data_movimentacao):
        self.__data_movimentacao = data_movimentacao
    
    @property
    def id_movimentacao(self):
        return self.__id_movimentacao
    
    @id_movimentacao.setter
    def id_movimentacao(self, id_movimentacao):
        self.__id_movimentacao = id_movimentacao

    def cadastrarMovimentacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Gerar o ID da movimentação
            cursor.execute("SELECT sq_movimentacao.nextval FROM dual")
            id_movimentacao = cursor.fetchone()[0]

            # Solicitar informações da movimentação
            id_cliente = int(input("Digite o ID do cliente: "))
            id_produto = int(input("Digite o ID do produto: "))
            data_movimentacao = input("Digite a data da movimentação (YYYY-MM-DD): ")

            # Executar o comando SQL para cadastrar a movimentação
            cursor.execute("INSERT INTO Movimentacao (id_cliente, id_produto, data_movimentacao, id_movimentacao) VALUES (:id_cliente, :id_produto, TO_DATE(:data_movimentacao, 'YYYY-MM-DD'), :id_movimentacao)",
                           {'id_cliente': id_cliente, 'id_produto': id_produto, 'data_movimentacao': data_movimentacao, 'id_movimentacao': id_movimentacao})

            # Confirmar a transação no banco de dados
            conn.commit()

            print("Movimentação cadastrada com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar movimentação:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def exibirMovimentacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            print("----- MENU -----")
            print("1. Procurar movimentação individualmente")
            print("2. Exibir todas as movimentações")
            print("3. Voltar")

            opcao = input("Digite a opção desejada: ")

            if opcao == "1":
                id_movimentacao = int(input("Digite o ID da movimentação: "))

                cursor.execute("SELECT * FROM movimentacao WHERE id_movimentacao = :id_movimentacao",
                            {'id_movimentacao': id_movimentacao})
                movimentacao = cursor.fetchone()

                if movimentacao:
                    print("Movimentação encontrada:")
                    print("ID da Movimentação:", movimentacao[0])
                    print("ID do Cliente:", movimentacao[1])
                    print("ID do Produto:", movimentacao[3])
                    print("Data da Movimentação:", movimentacao[2])
                    print("-----------------------")
                else:
                    print("Nenhuma movimentação encontrada com o ID informado.")
            elif opcao == "2":
                cursor.execute("SELECT * FROM movimentacao")

                print("Movimentações cadastradas:")
                for row in cursor:
                    print("ID da Movimentação:", row[0])
                    print("ID do Cliente:", row[1])
                    print("ID do Produto:", row[3])
                    print("Data da Movimentação:", row[2])
                    print("-----------------------")
            elif opcao == "3":
                return
            else:
                print("Opção inválida.")

        except cx_Oracle.Error as error:
            print("Erro ao exibir movimentações:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def editarMovimentacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID da movimentação a ser editada
            id_movimentacao = int(input("Digite o ID da movimentação a ser editada: "))

            # Executar o comando SQL para verificar se a movimentação existe
            cursor.execute("SELECT * FROM Movimentacao WHERE id_movimentacao = :id_movimentacao", {'id_movimentacao': id_movimentacao})
            result = cursor.fetchone()

            if result is None:
                print("Movimentação não encontrada.")
            else:
                # Solicitar as novas informações da movimentação
                novo_id_cliente = int(input("Digite o novo ID do cliente: "))
                novo_id_produto = int(input("Digite o novo ID do produto: "))
                nova_data_movimentacao = input("Digite a nova data da movimentação: ")

                # Executar o comando SQL para atualizar as informações da movimentação
                cursor.execute("UPDATE Movimentacao SET id_cliente = :id_cliente, id_produto = :id_produto, data_movimentacao = :data_movimentacao WHERE id_movimentacao = :id_movimentacao",
                            {'id_cliente': novo_id_cliente, 'id_produto': novo_id_produto, 'data_movimentacao': nova_data_movimentacao, 'id_movimentacao': id_movimentacao})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Movimentação atualizada com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar movimentação:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def excluirMovimentacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID da movimentação a ser excluída
            id_movimentacao = input("Digite o ID da movimentação a ser excluída: ")

            # Executar o comando SQL para verificar se a movimentação existe
            cursor.execute("SELECT * FROM Movimentacao WHERE id_movimentacao = :id_movimentacao", {'id_movimentacao': id_movimentacao})
            result = cursor.fetchone()

            if result is None:
                print("Movimentação não encontrada.")
            else:
                # Executar o comando SQL para excluir a movimentação
                cursor.execute("DELETE FROM Movimentacao WHERE id_movimentacao = :id_movimentacao", {'id_movimentacao': id_movimentacao})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Movimentação excluída com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir movimentação:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()


