from Usuario import Usuario
import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Doador(Usuario):
    def __init__(self, id_cliente, nome, email, senha, termo, cnpj, telefone, endereco, quantidade_doada):
        super().__init__(id_cliente, nome, email, senha, termo)
        self.__cnpj = cnpj
        self.__telefone = telefone
        self.__endereco = endereco
        self.__quantidade_doada = quantidade_doada
    
    @property
    def cnpj(self):
        return self.__cnpj
    
    @cnpj.setter
    def cnpj(self, cnpj):
        self.__cnpj = cnpj
    
    @property
    def telefone(self):
        return self.__telefone
    
    @telefone.setter
    def telefone(self, telefone):
        self.__telefone = telefone
    
    @property
    def endereco(self):
        return self.__endereco
    
    @endereco.setter
    def endereco(self, endereco):
        self.__endereco = endereco
    
    @property
    def quantidade_doada(self):
        return self.__quantidade_doada
    
    @quantidade_doada.setter
    def quantidade_doada(self, quantidade_doada):
        self.__quantidade_doada = quantidade_doada

    def cadastrarDoador():
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

                # Solicitar informações do doador
                cnpj = input("Digite o CNPJ do doador: ")
                endereco = input("Digite o endereço do doador: ")
                telefone = input("Digite o telefone do doador: ")
                quantidade_doada = input("Digite a quantidade doada do doador: ")

                # Executar o comando SQL para cadastrar o doador
                cursor.execute("INSERT INTO doador (id_cliente, cnpj, telefone, endereco, quantidade_doada) VALUES (:id_cliente, :cnpj, :telefone, :endereco, :quantidade_doada)",
                            {'id_cliente': id_cliente, 'cnpj': cnpj, 'telefone': telefone, 'endereco': endereco, 'quantidade_doada': quantidade_doada})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Doador cadastrado com sucesso!")
        except cx_Oracle.Error as error:
            print("Erro ao cadastrar doador:", error)
        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()



    def exibirDoador():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            print("----- MENU -----")
            print("1. Procurar doador individualmente")
            print("2. Exibir todos os doadores")
            print("3. Voltar")

            opcao = input("Digite a opção desejada: ")

            if opcao == "1":
                id_doador = int(input("Digite o ID do doador: "))

                cursor.execute("SELECT * FROM cliente JOIN doador ON cliente.id_cliente = doador.id_cliente WHERE cliente.id_cliente = :id_doador",
                            {'id_doador': id_doador})
                doador = cursor.fetchone()

                if doador:
                    print("Doador encontrado:")
                    print("ID do Cliente:", doador[0])
                    print("Nome:", doador[1])
                    print("CNPJ:", doador[2])
                    print("Endereço:", doador[3])
                    print("Telefone:", doador[4])
                    print("Quantidade Doada:", doador[5])
                    print("-----------------------")
                else:
                    print("Nenhum doador encontrado com o ID informado.")
            elif opcao == "2":
                cursor.execute("SELECT c.id_cliente, c.nome, d.cnpj, d.endereco, d.telefone, d.quantidade_doada FROM cliente c JOIN doador d ON c.id_cliente = d.id_cliente")

                print("Doadores cadastrados:")
                for row in cursor:
                    print("ID do Cliente:", row[0])
                    print("Nome:", row[1])
                    print("CNPJ:", row[2])
                    print("Endereço:", row[3])
                    print("Telefone:", row[4])
                    print("Quantidade Doada:", row[5])
                    print("-----------------------")
            elif opcao == "3":
                return
            else:
                print("Opção inválida.")
        except cx_Oracle.Error as error:
            print("Erro ao exibir doadores:", error)
        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()



    def editarDoador():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o CPF do doador a ser editado
            cnpj = input("Digite o CNPJ do doador a ser editado: ")

            # Executar o comando SQL para verificar se o doador existe
            cursor.execute("SELECT * FROM doador WHERE cnpj = :cnpj", {'cnpj': cnpj})
            result = cursor.fetchone()

            if result is None:
                print("Doador não encontrado.")
            else:
                # Solicitar as novas informações do doador
                endereco = input("Digite o endereço atualizado do doador: ")
                telefone = input("Digite o telefone atualizado do doador: ")
                quantidade_doada = input("Digite a quantidade doada atualizada do doador: ")

                # Executar o comando SQL para atualizar as informações do doador
                cursor.execute("UPDATE doador SET endereco = :endereco, telefone = :telefone, quantidade_doada = :quantidade_doada WHERE cnpj = :cnpj",
                       {'endereco': endereco, 'telefone': telefone, 'quantidade_doada': quantidade_doada, 'cnpj': cnpj})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Doador atualizado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar doador:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()


    def excluirDoador():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o CPF do doador a ser excluído
            id_cliente = input("Digite o id do cliente que corresponde ao doador a ser excluído: ")

            # Executar o comando SQL para verificar se o doador existe
            cursor.execute("SELECT * FROM doador WHERE id_cliente = :id_cliente", {'id_cliente': id_cliente})
            result = cursor.fetchone()

            if result is None:
                print("Doador não encontrado.")
            else:
                # Executar o comando SQL para excluir o doador
                cursor.execute("DELETE FROM doador WHERE id_cliente = :id_cliente", {'id_cliente': id_cliente})

                # Confirmar a transação no banco de dados
                conn.commit()

                print("Doador excluído com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir doador:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()