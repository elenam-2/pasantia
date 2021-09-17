

@GrabConfig(systemClassLoader=true)
@Grab(group='mysql', module='mysql-connector-java', version='8.0.22')


import java.sql.*
import groovy.sql.Sql
 

println("groovy Sql...")

def sql = Sql.newInstance("jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        "sunshine-2021",
        "com.mysql.cj.jdbc.Driver")


sql.execute("DROP TABLE IF EXISTS personas")

def createTbl = '''
CREATE TABLE personas (
  id INT PRIMARY KEY,
  nombre VARCHAR(50),
  apellido VARCHAR(100)
)
'''
def insertStr = "INSERT INTO personas VALUES(:id, :nombre, :apellido)"

sql.execute(createTbl)
sql.execute(insertStr, [id: 0, nombre: 'Alberto', apellido: 'Kim'])
sql.execute(insertStr, [id: 1, nombre: 'Marco', apellido: 'Maldonado'])
sql.execute(insertStr, [id: 2, nombre: 'Juan', apellido: 'Perez'])
sql.execute(insertStr, [id: 3, nombre: 'Pedro', apellido: 'Paramo'])
sql.execute(insertStr, [id: 4, nombre: 'Maria', apellido: 'Alvarez'])

def insertStr2 = "INSERT INTO personas VALUES(?,?,?)"
sql.execute(insertStr2, [5, 'Pedro',  'Galvez'])
sql.execute(insertStr2, [6, 'Alberto',  'Gonzalez'])


def query = """SELECT * FROM personas WHERE nombre = "Pedro" """

sql.eachRow(query) { row ->
  println "$row.id - ${row.nombre} $row.apellido"
}

//Housekeeping
sql.close()
