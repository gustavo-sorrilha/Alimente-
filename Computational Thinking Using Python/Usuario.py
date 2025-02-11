import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Usuario:
    def __init__(self, id_cliente, nome, email, senha, termo):
        self.__id_cliente = id_cliente
        self.__nome = nome
        self.__email = email
        self.__senha = senha
        self.__termo = termo
    
    @property
    def id_cliente(self):
        return self.__id_cliente
    
    @id_cliente.setter
    def id_cliente(self, id_cliente):
        self.__id_cliente = id_cliente
    
    @property
    def nome(self):
        return self.__nome
    
    @nome.setter
    def nome(self, nome):
        self.__nome = nome
    
    @property
    def email(self):
        return self.__email
    
    @email.setter
    def email(self, email):
        self.__email = email
    
    @property
    def senha(self):
        return self.__senha
    
    @senha.setter
    def senha(self, senha):
        self.__senha = senha
    
    @property
    def termo(self):
        return self.__termo
    
    @termo.setter
    def termo(self, termo):
        self.__termo = termo

    def cadastrarUsuario():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar informações do cliente
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

            # Gerar o próximo valor da sequência para o id_cliente
            cursor.execute("SELECT sq_cliente.nextval FROM dual")
            id_cliente = cursor.fetchone()[0]

            # Executar o comando SQL para cadastrar o usuário comum
            cursor.execute("INSERT INTO cliente (id_cliente, nome, email, senha, termo) VALUES (:id_cliente, :nome, :email, :senha, :termo)",
                        {'id_cliente': id_cliente, 'nome': nome, 'email': email, 'senha': senha, 'termo': termo})

            # Confirmar a transação no banco de dados
            conn.commit()

            print(f"Parabéns! Seu cadastro foi realizado com sucesso. O número de identificação atribuído é: {id_cliente}")
        except Exception as e:
            # Em caso de erro, faça o rollback para desfazer as alterações
            conn.rollback()
            print("Erro ao cadastrar o usuário:", str(e))
        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()


    def direcionarUsuario():
        # Estabelecer conexão com o banco de dados Oracle
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            # Criar um cursor para executar comandos SQL
            cursor = conn.cursor()

            # Solicitar o id_cliente do usuário
            id_cliente = int(input("Digite o número de identificação que você possui: "))
            
            # Verificar se o id_cliente existe na tabela Cliente
            cursor.execute("SELECT COUNT(*) FROM Cliente WHERE id_cliente = :id_cliente", {'id_cliente': id_cliente})
            count = cursor.fetchone()[0]
            
            if count > 0:
                # Verificar se o usuário deseja ser beneficiário ou doador
                tipo_usuario = input("Digite 'B' para Beneficiário ou 'D' para Doador: ")

                if tipo_usuario.upper() == 'B':
                
                    cpf = input("Digite o CPF do beneficiário: ")
                    quantidade_recebida = int(input("Digite a quantidade recebida pelo beneficiário: "))

                    # Executar o comando SQL para cadastrar o Beneficiário
                    cursor.execute("INSERT INTO Beneficiario (id_cliente, cpf, quantidade_recebida) VALUES (:id_cliente, :cpf, :quantidade_recebida)",
                                {'id_cliente': id_cliente, 'cpf': cpf, 'quantidade_recebida': quantidade_recebida})
                    print("Beneficiário cadastrado com sucesso!")
                elif tipo_usuario.upper() == 'D':
                    # O usuário deseja ser um Doador
                    # Solicitar informações do doador
                    cnpj = input("Digite o CNPJ do doador: ")
                    telefone = input("Digite o telefone do doador: ")
                    endereco = input("Digite o endereço do doador: ")
                    quantidade_doada = int(input("Digite a quantidade doada pelo doador: "))

                    # Executar o comando SQL para cadastrar o Doador
                    cursor.execute("INSERT INTO Doador (id_cliente, cnpj, telefone, endereco, quantidade_doada) VALUES (:id_cliente, :cnpj, :telefone, :endereco, :quantidade_doada)",
                                {'id_cliente': id_cliente, 'cnpj': cnpj, 'telefone': telefone, 'endereco': endereco, 'quantidade_doada': quantidade_doada})
                    print("Doador cadastrado com sucesso!")
                else:
                    print("Opção inválida. Digite 'B' para Beneficiário ou 'D' para Doador.")
                    
                # Confirmar a transação no banco de dados
                conn.commit()
            else:
                print("Nenhum cliente encontrado com o id_cliente informado.")

        except cx_Oracle.Error as error:
            # Em caso de erro, faça o rollback para desfazer as alterações
            conn.rollback()
            print("Erro ao cadastrar o usuário:", error)

        finally:
            # Fechar o cursor e a conexão com o banco de dados
            cursor.close()
            conn.close()
