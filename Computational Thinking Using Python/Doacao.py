import cx_Oracle


dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Doacao:
    def __init__(self, id_doacao, imagem, descricao, disponibilidade):
        self.__id_doacao = id_doacao
        self.__imagem = imagem
        self.__descricao = descricao
        self.__disponibilidade = disponibilidade
    
    @property
    def id_doacao(self):
        return self.__id_doacao
    
    @id_doacao.setter
    def id_doacao(self, id_doacao):
        self.__id_doacao = id_doacao
    
    @property
    def imagem(self):
        return self.__imagem
    
    @imagem.setter
    def imagem(self, imagem):
        self.__imagem = imagem
    
    @property
    def descricao(self):
        return self.__descricao
    
    @descricao.setter
    def descricao(self, descricao):
        self.__descricao = descricao
    
    @property
    def disponibilidade(self):
        return self.__disponibilidade
    
    @disponibilidade.setter
    def disponibilidade(self, disponibilidade):
        self.__disponibilidade = disponibilidade
    
    def cadastrarDoacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            cursor.execute("SELECT sq_doacao.nextval FROM dual")
            id_doacao = cursor.fetchone()[0]

            # Solicitar informações da doação
            imagem = input("Digite o caminho da imagem da doação: ")
            descricao = input("Digite a descrição da doação: ")
            disponibilidade = int(input("Digite a disponibilidade da doação (0 - indisponível, 1 - disponível): "))

            # Executar o comando SQL para cadastrar a doação
            cursor.execute("INSERT INTO Doacao (id_doacao, imagem, descricao, disponibilidade) VALUES (:id_doacao, :imagem, :descricao, :disponibilidade)",
                           {'id_doacao': id_doacao, 'imagem': imagem, 'descricao': descricao, 'disponibilidade': disponibilidade})

            # Confirmar a transação no banco de dados
            conn.commit()

            print("Doação cadastrada com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar doação:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def exibirDoacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            print("----- MENU -----")
            print("1. Procurar doação individualmente")
            print("2. Exibir todas as doações")
            print("3. Voltar")

            opcao = input("Digite a opção desejada: ")

            if opcao == "1":
                id_doacao = int(input("Digite o ID da doação: "))

                cursor.execute("SELECT * FROM doacao WHERE id_doacao = :id_doacao",
                            {'id_doacao': id_doacao})
                doacao = cursor.fetchone()

                if doacao:
                    print("Doação encontrada:")
                    print("ID da Doação:", doacao[0])
                    print("Imagem:", doacao[1])
                    print("Descrição:", doacao[2])
                    print("Disponibilidade:", doacao[3])
                    print("-----------------------")
                else:
                    print("Nenhuma doação encontrada com o ID informado.")
            elif opcao == "2":
                cursor.execute("SELECT * FROM doacao")

                print("Doações cadastradas:")
                for row in cursor:
                    print("ID da Doação:", row[0])
                    print("Imagem:", row[1])
                    print("Descrição:", row[2])
                    print("Disponibilidade:", row[3])
                    print("-----------------------")
            elif opcao == "3":
                return
            else:
                print("Opção inválida.")

        except cx_Oracle.Error as error:
            print("Erro ao exibir doações:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def editarDoacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID da doação a ser editada
            id_doacao = int(input("Digite o ID da doação a ser editada: "))

            # Executar o comando SQL para verificar se a doação existe
            cursor.execute("SELECT * FROM doacao WHERE id_doacao = :id_doacao", {'id_doacao': id_doacao})
            result = cursor.fetchone()

            if result is None:
                print("Doação não encontrada.")
            else:
                # Solicitar as novas informações da doação
                nova_imagem = input("Digite a nova imagem da doação: ")
                nova_descricao = input("Digite a nova descrição da doação: ")
                nova_disponibilidade = input("Digite a nova disponibilidade da doação: ")

                # Executar o comando SQL para atualizar as informações da doação
                cursor.execute("UPDATE doacao SET imagem = :imagem, descricao = :descricao, disponibilidade = :disponibilidade WHERE id_doacao = :id_doacao",
                            {'imagem': nova_imagem, 'descricao': nova_descricao, 'disponibilidade': nova_disponibilidade, 'id_doacao': id_doacao})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Doação atualizada com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar doação:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()

    def excluirDoacao():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID da doação a ser excluída
            id_doacao = int(input("Digite o ID da doação a ser excluída: "))

            # Executar o comando SQL para verificar se a doação existe
            cursor.execute("SELECT * FROM Doacao WHERE id_doacao = :id_doacao", {'id_doacao': id_doacao})
            result = cursor.fetchone()

            if result is None:
                print("Doação não encontrada.")
            else:
                # Executar o comando SQL para excluir a doação
                cursor.execute("DELETE FROM Doacao WHERE id_doacao = :id_doacao", {'id_doacao': id_doacao})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Doação excluída com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir doação:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()
        
