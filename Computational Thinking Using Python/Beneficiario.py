from Usuario import Usuario
import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Beneficiario(Usuario):
    def __init__(self, id_cliente, nome, email, senha, termo, cpf, quantidade_recebida, fk_cliente_id_cliente):
        super().__init__(id_cliente, nome, email, senha, termo)
        self.__cpf = cpf
        self.__quantidade_recebida = quantidade_recebida

    @property
    def cpf(self):
        return self.__cpf

    @cpf.setter
    def cpf(self, cpf):
        self.__cpf = cpf

    @property
    def quantidade_recebida(self):
        return self.__quantidade_recebida

    @quantidade_recebida.setter
    def quantidade_recebida(self, quantidade_recebida):
        self.__quantidade_recebida = quantidade_recebida

    def cadastrarBeneficiario():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Recuperar o próximo valor da sequência para o id_cliente
            cursor.execute("SELECT sq_cliente.nextval FROM dual")
            id_cliente = cursor.fetchone()[0]

            nome = input("Digite o nome do cliente: ")
            email = input("Digite o email do cliente: ")
            senha = input("Digite a senha do cliente: ")

            # Solicitar o termo do cliente com validação
            while True:
                termo = input("Digite o termo do cliente: 0 (não lido) ou 1 (lido): ")
                if termo == '0' or termo == '1':
                    break
                else:
                    print("Opção inválida. Digite 0 para não lido ou 1 para lido.")
                    return
            cursor.execute("INSERT INTO cliente (id_cliente, nome, email, senha, termo) VALUES (:id_cliente, :nome, :email, :senha, :termo)",
                        {'id_cliente': id_cliente, 'nome': nome, 'email': email, 'senha': senha, 'termo': termo})
            tipo_usuario = 1
            if tipo_usuario == 1 :
                cpf = input("Digite o CPF do beneficiário: ")
                quantidade_recebida = int(input("Digite a quantidade recebida pelo beneficiário: "))

                # Executar o comando SQL para cadastrar o beneficiário
                cursor.execute("INSERT INTO Beneficiario (id_cliente, cpf, quantidade_recebida) VALUES (:id_cliente, :cpf, :quantidade)",
                            {'id_cliente': id_cliente, 'cpf': cpf, 'quantidade': quantidade_recebida})

            # Confirmar a transação no banco de dados
            conn.commit()

            print("Beneficiário cadastrado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar beneficiário:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()


    def exibirBeneficiario():
    
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            print("----- MENU -----")
            print("1. Procurar beneficiário individualmente")
            print("2. Exibir todos os beneficiários")
            print("3. Voltar")

            opcao = input("Digite a opção desejada: ")

            if opcao == "1":
                id_beneficiario = int(input("Digite o ID do beneficiário: "))

                cursor.execute("SELECT * FROM cliente JOIN beneficiario ON cliente.id_cliente = beneficiario.id_cliente WHERE cliente.id_cliente = :id_beneficiario",
                            {'id_beneficiario': id_beneficiario})
                beneficiario = cursor.fetchone()

                if beneficiario:
                    print("Beneficiário encontrado:")
                    print("ID do Cliente:", beneficiario[0])
                    print("Nome:", beneficiario[1])
                    print("CPF:", beneficiario[6])
                    print("Quantidade Recebida:", beneficiario[7])
                else:
                    print("Nenhum beneficiário encontrado com o ID informado.")
            elif opcao == "2":
                
                cursor.execute("SELECT c.id_cliente, c.nome, b.cpf, b.quantidade_recebida FROM cliente c JOIN Beneficiario b ON c.id_cliente = b.id_cliente")
                # cursor.execute("SELECT * FROM cliente JOIN beneficiario ON cliente.id_cliente = beneficiario.id_cliente")

                
                print("Beneficiários cadastrados:")
                for row in cursor:
                    print("ID do Cliente:", row[0])
                    print("Nome:", row[1])
                    print("CPF:", row[2])
                    print("Quantidade Recebida:", row[3])
                    print("-----------------------")
            elif opcao == "3":
                return
            else:
                print("Opção inválida.")

        except cx_Oracle.Error as error:
            print("Erro ao exibir beneficiários:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()


    def editarBeneficiario():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID do beneficiário a ser editado
            id_cliente = int(input("Digite o ID do beneficiário a ser editado: "))

            # Executar o comando SQL para verificar se o beneficiário existe
            cursor.execute("SELECT * FROM Beneficiario WHERE id_cliente = :id_cliente", {'id_cliente': id_cliente})
            result = cursor.fetchone()

            if result is None:
                print("Beneficiário não encontrado.")
            else:
                # Solicitar as novas informações do beneficiário
                novo_nome = input("Digite o novo nome do beneficiário: ")
                nova_quantidade_recebida = int(input("Digite a nova quantidade recebida pelo beneficiário: "))

                # Executar o comando SQL para atualizar as informações do beneficiário
                cursor.execute("UPDATE Cliente SET nome = :nome WHERE id_cliente = :id_cliente",
                            {'nome': novo_nome, 'id_cliente': id_cliente})
                cursor.execute("UPDATE Beneficiario SET quantidade_recebida = :quantidade WHERE id_cliente = :id_cliente",
                            {'quantidade': nova_quantidade_recebida, 'id_cliente': id_cliente})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Beneficiário atualizado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar beneficiário:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()



    def excluirBeneficiario():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o ID_CLIENTE do beneficiário a ser excluído
            id_cliente = input("Digite o id_cliente do beneficiário a ser excluído: ")

            # Executar o comando SQL para verificar se o beneficiário existe
            cursor.execute("SELECT * FROM Beneficiario WHERE id_cliente = :id_cliente", {'id_cliente': id_cliente})
            result = cursor.fetchone()

            if result is None:
                print("Beneficiário não encontrado.")
            else:
                # Executar o comando SQL para excluir o beneficiário
                cursor.execute("DELETE FROM Beneficiario WHERE id_cliente = :id_cliente", {'id_cliente': id_cliente})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Beneficiário excluído com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir beneficiário:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()