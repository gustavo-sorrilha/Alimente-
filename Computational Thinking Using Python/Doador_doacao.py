import cx_Oracle

dsn = cx_Oracle.makedsn(host='oracle.fiap.com.br', port=1521, sid='ORCL')

class Doador_doacao:
    def __init__(self, id_doador, id_doacao):
        self.id_doador = id_doador
        self.id_doacao = id_doacao
    
    
    def cadastrarDoadorDoacao():
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            
            cursor = conn.cursor()

            id_doador = input("ID do doador: ")
            id_doacao = input("ID da doação: ")

            
            cursor.execute("INSERT INTO Doador_doacao (id_doador, id_doacao) VALUES (:id_doador, :id_doacao)",
                        {'id_doador': id_doador, 'id_doacao': id_doacao})

            
            conn.commit()

            print("Doador_doacao cadastrado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao cadastrar Doador_doacao:", error)

        finally:
            
            cursor.close()
            conn.close()
    
    
    def exibirDoadorDoacao():
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            cursor = conn.cursor()

            cursor.execute("SELECT * FROM Doador_doacao")

            print("Doador_doacões cadastradas:")
            for row in cursor:
                print("ID do doador:", row[0])
                print("ID da doação:", row[1])
                print("-----------------------")

        except cx_Oracle.Error as error:
            print("Erro ao exibir Doador_doacoes:", error)

        finally:
            cursor.close()
            conn.close()

    
    def editarDoadorDoacao():
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            cursor = conn.cursor()

            id_doador = input("ID do doador: ")
            id_doacao = input("ID da doação: ")

            
            cursor.execute("UPDATE Doador_doacao SET id_doacao = :id_doacao WHERE id_doador = :id_doador",
                        {'id_doador': id_doador, 'id_doacao': id_doacao})

            
            conn.commit()

            print("Doador_doacao atualizado com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao editar Doador_doacao:", error)

        finally:
            
            cursor.close()
            conn.close()

    
    def excluirDoadorDoacao():
        
        conn = cx_Oracle.connect(user='RM97092', password='110602', dsn=dsn)

        try:
            cursor = conn.cursor()

            id_doador = input("ID do doador: ")

            cursor.execute("DELETE FROM Doador_doacao WHERE id_doador = :id_doador", {'id_doador': id_doador})

            conn.commit()

            print("Doador_doacao excluído com sucesso!")

        except cx_Oracle.Error as error:
            print("Erro ao excluir Doador_doacao:", error)

        finally:
            
            cursor.close()
            conn.close()
