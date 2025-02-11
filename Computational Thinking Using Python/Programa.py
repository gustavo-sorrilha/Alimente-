# OS DADOS PARA VALIDAÇÃO DAS ALTERAÇÕES NO BANCO SÃO:  
# dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')
# conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)
# PARA LOGAR , UTILIZAR O USERNAME "admin" E A PASSWORD "admin123"
from Funcaoo import Funcaoo
import cx_Oracle
from Produto  import Produto
from Doador  import Doador
from Beneficiario import Beneficiario
from Usuario import Usuario


dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

def main():
    print("==> Aliment+ <==")
    print("------------------------------------------")

    while True:
               
                # MENU INICIAL
                print("------------------------------------------")
                print(Funcaoo.menuInicial())
                opcao = input("Opção: ")

                if opcao == "1":
                    Usuario.cadastrarUsuario()

                elif opcao == "3":
                    Funcaoo.admin()
                
                elif opcao == "2":
                    Usuario.direcionarUsuario()

                elif opcao == "4":
                    Funcaoo.sair()
                    print("Obrigado por utilizar o sistema. Até logo!")
                    break
                else:
                    print("Opção inválida. Por favor, selecione novamente.")

if __name__ == "__main__":
    main()
