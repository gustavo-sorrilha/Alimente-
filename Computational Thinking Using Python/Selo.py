from Doador import Doador
import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')





class Selo(Doador):
    def __init__(self, id_selo, id_doador):
        super().__init__(id_doador)
        self.__id_selo = id_selo

    @property
    def id_selo(self):
        return self.__id_selo

    @id_selo.setter
    def id_selo(self, id_selo):
        self.__id_selo = id_selo

    def cadastrarSelo():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            cursor.execute("SELECT sq_selo.nextval FROM dual")
            id_selo = cursor.fetchone()[0]

            # Solicitar informações do selo
            id_doador = int(input("Digite o ID do doador (Que ainda não possui um selo): "))

            # Executar o comando SQL para cadastrar o selo
            cursor.execute("INSERT INTO Selo (id_selo, id_doador) VALUES (:id_selo, :id_doador)",
                           {'id_selo': id_selo, 'id_doador': id_doador})

            # Confirmar a transação no banco de dados
            conn.commit()

            print("Selo cadastrado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar selo:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()
    
    def exibirSelo():
            # Estabelecer conexão com o banco de dados Oracle
            conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

            try:
                # Criar um cursor para executar comandos SQL
                cursor = conn.cursor()

                print("----- MENU -----")
                print("1. Procurar selo individualmente")
                print("2. Exibir todos os selos")
                print("3. Voltar")

                opcao = input("Digite a opção desejada: ")

                if opcao == "1":
                    id_selo = int(input("Digite o ID do selo: "))

                    cursor.execute("SELECT * FROM selo WHERE id_selo = :id_selo",
                                {'id_selo': id_selo})
                    selo = cursor.fetchone()

                    if selo:
                        print("Selo encontrado:")
                        print("ID do Selo:", selo[0])
                        print("ID do Doador:", selo[1])
                        print("-----------------------")
                    else:
                        print("Nenhum selo encontrado com o ID informado.")
                elif opcao == "2":
                    cursor.execute("SELECT * FROM selo")

                    print("Selos cadastrados:")
                    for row in cursor:
                        print("ID do Selo:", row[0])
                        print("ID do Doador:", row[1])
                        print("-----------------------")
                elif opcao == "3":
                    return
                else:
                    print("Opção inválida.")

            except cx_Oracle.Error as error:
                print("Erro ao exibir selos:", error)

            finally:
                # Fechar o cursor e a conexão com o banco de dados
                cursor.close()
                conn.close()

    def editarSelo():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID do selo a ser editado
            id_doador = int(input("Digite o ID do doador do selo a ser editado: "))

            # Executar o comando SQL para verificar se o selo existe
            cursor.execute("SELECT * FROM Selo WHERE id_doador = :id_doador", {'id_doador': id_doador})
            result = cursor.fetchone()

            if result is None:
                print("Selo não encontrado.")
            else:
                # Solicitar as novas informações do selo
                novo_id_selo = int(input("Digite o novo ID do selo: "))

                # Executar o comando SQL para atualizar as informações do selo
                cursor.execute("UPDATE Selo SET id_selo = :id_selo WHERE id_doador = :id_doador",
                            {'id_selo': novo_id_selo, 'id_doador': id_doador})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Selo atualizado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar selo:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def excluirSelo():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID do selo a ser excluído
            id_selo = input("Digite o ID do selo a ser excluído: ")

            # Executar o comando SQL para verificar se o selo existe
            cursor.execute("SELECT * FROM Selo WHERE id_selo = :id_selo", {'id_selo': id_selo})
            result = cursor.fetchone()

            if result is None:
                print("Selo não encontrado.")
            else:
                # Executar o comando SQL para excluir o selo
                cursor.execute("DELETE FROM Selo WHERE id_selo = :id_selo", {'id_selo': id_selo})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Selo excluído com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir selo:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

