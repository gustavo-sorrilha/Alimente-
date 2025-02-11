import cx_Oracle
from Produto import Produto
from Doacao import Doacao

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class doacao_produto(Doacao, Produto):
    def __init__(self, id_doacao, id_produto):
        super().__init__()
        self.id_doacao = id_doacao
        self.id_produto = id_produto
    
   
    def cadastrarDoacaoProduto():
        
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            
            cursor = conn.cursor()

            id_doacao = input("ID da doação: ")
            id_produto = input("ID do produto: ")

            
            cursor.execute("INSERT INTO doacao_produto (id_doacao, id_produto) VALUES (:id_doacao, :id_produto)",
                        {'id_doacao': id_doacao, 'id_produto': id_produto})

            
            conn.commit()

            print("doacao_produto cadastrado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar doacao_produto:", error)

        finally:
            
            cursor.close()
            conn.close()
    
    def exibirDoacaoProduto():
        
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            
            cursor = conn.cursor()

            cursor.execute("SELECT * FROM doacao_produto")

            print("doacao_produtos cadastrados:")
            for row in cursor:
                print("ID da doação:", row[0])
                print("ID do produto:", row[1])
                print("-----------------------")

        except cx_Oracle.Error as error:
            print("Erro ao exibir doacao_produtos:", error)

        finally:
            
            cursor.close()
            conn.close()

    
    def editarDoacaoProduto():
        
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            
            cursor = conn.cursor()

            id_doacao = input("ID da doação: ")
            id_produto = input("ID do produto: ")

            
            cursor.execute("UPDATE doacao_produto SET id_produto = :id_produto WHERE id_doacao = :id_doacao",
                        {'id_doacao': id_doacao, 'id_produto': id_produto})

            
            conn.commit()

            print("doacao_produto atualizado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar doacao_produto:", error)

        finally:
            
            cursor.close()
            conn.close()

    def excluirDoacaoProduto():
        
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            
            cursor = conn.cursor()

            id_doacao = input("ID da doação: ")

            
            cursor.execute("DELETE FROM doacao_produto WHERE id_doacao = :id_doacao", {'id_doacao': id_doacao})

            
            conn.commit()

            print("doacao_produto excluído com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir doacao_produto:", error)

        finally:
            
            cursor.close()
            conn.close()

