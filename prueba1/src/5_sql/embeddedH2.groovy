

@GrabConfig(systemClassLoader=true)
@Grab(group='com.h2database', module='h2', version='1.3.176')

import java.sql.*
import groovy.sql.Sql
 

println("groovy Sql...")

def sql = Sql.newInstance("jdbc:h2:~/personas", "sa", "sa", "org.h2.Driver")


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

def query = "SELECT * FROM personas "

sql.eachRow(query) { row ->
  println "$row.id - ${row.nombre}::$row.apellido"
}

//Housekeeping
sql.close()
