import cx_Oracle
from Produto import Produto
from Usuario import Usuario
from Beneficiario import Beneficiario
from Doador import Doador
from datetime import datetime
import time
from Doador_doacao import Doador_doacao
from Movimentacao import Movimentacao
from Doacao import Doacao
from Doacao_produto import doacao_produto
from Selo import Selo


cx_Oracle.init_oracle_client(lib_dir=r"C:\Users\sorri\Downloads\instantclient-basic-windows.x64-21.10.0.0.0dbru\instantclient_21_10")

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Funcaoo:
    @staticmethod
    def menuCabecalho():
        return "==> Alimente+ <==\n" \
               "------------------------------------------\n"

    @staticmethod
    def menuRodape():
        return "------------------------------------------\n" \
               "DIGITE A OPÇÃO DESEJADA: \n"

    @staticmethod
    def menuInicial():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRO USUARIO\n" \
               "02. Direcionamento de USUARIO\n" \
               "03. LOGIN ADMIN\n" \
               "04. SAIR\n" + \
               Funcaoo.menuRodape()
    
    def selecionar_opcao():
        opcao = input("Selecione uma opção: ")
        return opcao

    def admin():
        print("Opção selecionada: LOGIN ADMIN")
        
        username = input("Username: ")
        password = input("Password: ")
        
        if verificarCredenciais(username, password):
            print("Login do administrador realizado com sucesso!")
            while True:
                            print("------------------------------------------")
                            print(Funcaoo.menuAdmin()) 
                            opcao_admin1 = input("Opção: ")   
                            if opcao_admin1 == "1":
                                while True:
                                    print(Funcaoo.menuAdminBeneficiario())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Beneficiario.cadastrarBeneficiario()
                                    elif opcao_admin == "2":
                                        Beneficiario.exibirBeneficiario()
                                    elif opcao_admin == "3":
                                        Beneficiario.editarBeneficiario()
                                    elif opcao_admin == "4":
                                        Beneficiario.excluirBeneficiario()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")

                            elif opcao_admin1 == "2":
                                while True:
                                    print(Funcaoo.menuAdminDoador())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Doador.cadastrarDoador()
                                    elif opcao_admin == "2":
                                        Doador.exibirDoador()
                                    elif opcao_admin == "3":
                                        Doador.editarDoador()
                                    elif opcao_admin == "4":
                                        Doador.excluirDoador()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")

                            elif opcao_admin1 == "3":
                                while True:
                                    print(Funcaoo.menuAdminProduto())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Produto.cadastrarProduto()
                                    elif opcao_admin == "2":
                                        Produto.exibirProduto()
                                    elif opcao_admin == "3":
                                        Produto.editarProduto()
                                    elif opcao_admin == "4":
                                        Produto.excluirProduto()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")
                            elif opcao_admin1 == "4":
                                while True:
                                    print(Funcaoo.menuAdminMovimentação())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Movimentacao.cadastrarMovimentacao()
                                    elif opcao_admin == "2":
                                        Movimentacao.exibirMovimentacao()
                                    elif opcao_admin == "3":
                                        Movimentacao.editarMovimentacao()
                                    elif opcao_admin == "4":
                                        Movimentacao.excluirMovimentacao()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")
                            elif opcao_admin1 == "5":
                                while True:
                                    print(Funcaoo.menuAdminSelo())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Selo.cadastrarSelo()
                                    elif opcao_admin == "2":
                                        Selo.exibirSelo()
                                    elif opcao_admin == "3":
                                        Selo.editarSelo()
                                    elif opcao_admin == "4":
                                        Selo.excluirSelo()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")
                            elif opcao_admin1 == "6":
                                while True:
                                    print(Funcaoo.menuAdminDoacao())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Doacao.cadastrarDoacao()
                                    elif opcao_admin == "2":
                                        Doacao.exibirDoacao()
                                    elif opcao_admin == "3":
                                        Doacao.editarDoacao()
                                    elif opcao_admin == "4":
                                        Doacao.excluirDoacao()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")
                            elif opcao_admin1 == "7":
                                while True:
                                    print(Funcaoo.menuAdminDoacao_produto())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        doacao_produto.cadastrarDoacaoProduto()
                                    elif opcao_admin == "2":
                                        doacao_produto.exibirDoacaoProduto()
                                    elif opcao_admin == "3":
                                        doacao_produto.editarDoacaoProduto()
                                    elif opcao_admin == "4":
                                        doacao_produto.excluirDoacaoProduto()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")
                            elif opcao_admin1 == "8":
                                while True:
                                    print(Funcaoo.menuAdminDoador_doacao())
                                    opcao_admin = input("Opção: ")

                                    if opcao_admin == "1":
                                        Doador_doacao.cadastrarDoadorDoacao()
                                    elif opcao_admin == "2":
                                        Doador_doacao.exibirDoadorDoacao()
                                    elif opcao_admin == "3":
                                        Doador_doacao.editarDoadorDoacao()
                                    elif opcao_admin == "4":
                                        Doador_doacao.excluirDoadorDoacao()
                                    elif opcao_admin == "5":
                                        break
                                    else:
                                        print("Opção inválida.")

                            elif opcao_admin1 == "9":
                                break

            else:
                print("Opção inválida.")
        else:
            print("Credenciais inválidas. Tente novamente.")
            return


    def sair():
        print("Opção selecionada: SAIR")
        # Coloque aqui o código para sair do programa
        import sys
        sys.exit(0)


    @staticmethod
    def menuAdmin():
        return Funcaoo.menuCabecalho() + \
               "01. Beneficiários\n" \
               "02. Doadores\n" \
               "03. Produtos\n" \
               "04. Movimentação\n" \
               "05. Selo\n" \
               "06. Doação\n" \
               "07. Doação_Produto\n" \
               "08. Doador_Doação\n" \
               "09. SAIR\n" + \
               Funcaoo.menuRodape()

    @staticmethod
    def menuAdminBeneficiario():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR BENEFICIÁRIO\n" \
               "02. EXIBIR BENEFICIÁRIO\n" \
               "03. EDITAR BENEFICIÁRIO\n" \
               "04. EXCLUIR BENEFICIÁRIO\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()

    @staticmethod
    def menuAdminDoador():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR DOADOR\n" \
               "02. EXIBIR DOADOR\n" \
               "03. EDITAR DOADOR\n" \
               "04. EXCLUIR DOADOR\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()
    @staticmethod
    def menuAdminDoacao_produto():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR Doacao_produto\n" \
               "02. EXIBIR Doacao_produto\n" \
               "03. EDITAR Doacao_produto\n" \
               "04. EXCLUIR Doacao_produto\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()
    @staticmethod
    def menuAdminDoador_doacao():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR Doador_doacao\n" \
               "02. EXIBIR Doador_doacao\n" \
               "03. EDITAR Doador_doacao\n" \
               "04. EXCLUIR Doador_doacao\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()

    @staticmethod
    def menuAdminProduto():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR PRODUTO\n" \
               "02. EXIBIR PRODUTOS\n" \
               "03. EDITAR PRODUTO\n" \
               "04. EXCLUIR PRODUTO\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()
    @staticmethod
    def menuAdminMovimentação():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR Movimentação\n" \
               "02. EXIBIR Movimentaçãoes\n" \
               "03. EDITAR Movimentação\n" \
               "04. EXCLUIR Movimentação\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()
    @staticmethod
    def menuAdminSelo():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR Selo\n" \
               "02. EXIBIR Selo\n" \
               "03. EDITAR Selo\n" \
               "04. EXCLUIR Selo\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()
    @staticmethod
    def menuAdminDoacao():
        return Funcaoo.menuCabecalho() + \
               "01. CADASTRAR Doação\n" \
               "02. EXIBIR Doação\n" \
               "03. EDITAR Doação\n" \
               "04. EXCLUIR Doação\n" \
               "05. SAIR\n" + \
               Funcaoo.menuRodape()

    
    import cx_Oracle

def verificarCredenciais(username, password):

    conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

    try:
        
        cursor = conn.cursor()

        
        cursor.execute("SELECT COUNT(*) FROM cliente WHERE nome = :username AND senha = :password",
                       {'username': username, 'password': password})
        count = cursor.fetchone()[0]
        
        return count == 1

    except cx_Oracle.Error as error:
        print("Erro ao verificar credenciais:", error)

    finally:
        
        cursor.close()
        conn.close()

