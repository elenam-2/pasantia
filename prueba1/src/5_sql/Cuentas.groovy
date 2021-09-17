

@GrabConfig(systemClassLoader=true)
@Grab(group='mysql', module='mysql-connector-java', version='8.0.22')


import groovy.sql.Sql


println("groovy Sql...")

def sql = Sql.newInstance("jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        "sunshine-2021",
        "com.mysql.cj.jdbc.Driver")


sql.execute("DROP TABLE IF EXISTS cuenta")

def createTbl = '''
    CREATE TABLE cuenta (
    version INT,
    tipo_cuenta VARCHAR(50),
    descripcion VARCHAR(50),
    cuenta VARCHAR(50),
    cliente_id INT,
    identificacion VARCHAR(50),
    saldo DOUBLE
)
'''

sql.execute(createTbl)

def insertStr = "INSERT INTO cuenta VALUES(:version, :tipo_cuenta, :descripcion, :cuenta, :cliente_id, :identificacion, :saldo)"

//define donde esta ubicado el archivo y cual es
def rutaArchivo = 'C:\\Users\\elena\\Documents\\Pasantia\\datos_cliente.csv'
File archivo = new File(rutaArchivo)
String linea
def conta = 0
archivo.withReader { reader ->
    linea = reader.readLine()
    while ((linea = reader.readLine()) != null) {
        String[] data = linea.split(',')
        def version = Integer.parseInt(data[0])
        def tipo_cuenta = data[1]
        def descripcion = data[2]
        def cuenta = data[3]
        def cliente_id = Integer.parseInt(data[4])
        def identificacion = data[5]
        def saldo = Double.parseDouble(data[6])
        if (conta % 100 == 0  ) {
            println "Work in progress ...${conta}"
        }
        sql.execute(insertStr, [version: version, tipo_cuenta: tipo_cuenta, descripcion: descripcion, cuenta: cuenta, cliente_id: cliente_id, identificacion: identificacion, saldo: saldo])
        conta++
    }
}

//def query = """SELECT * FROM cuenta"""
//def query = """ select * from cuenta where substr(identificacion, 1, 1) = '2' """
//sql.eachRow(queryProvincia) { row ->
//    println "$row.version - ${row.tipo_cuenta} $row.descripcion $row.cuenta $row.cliente_id $row.identificacion $row.saldo"
//}

def clienteId = 545
def cuenta = '66-22-50421312'
def provincia = '5'



println()
println 'Select para provincia  ${provincia} (cocle)'
def queryProvincia = """ select * from cuenta where substr(identificacion, 1, 1) = ? """
def rows = sql.eachRow(queryProvincia,[provincia])

rows.each{ row ->
    println "$row.version - ${row.tipo_cuenta} $row.descripcion $row.cuenta $row.cliente_id $row.identificacion $row.saldo"
}
println()

println 'Select para cliente_id ${clienteId}'

def queryNumeroCliente = """ select * from cuenta where cliente_id = ? """
queryNumeroCliente = """ select * from cuenta where cliente_id = """+ clienteId+ " "
//Inyeccion de SQL
sql.eachRow(queryNumeroCliente,[clienteId]) { row ->
    println "$row.version - ${row.tipo_cuenta} $row.descripcion $row.cuenta $row.cliente_id $row.identificacion $row.saldo"
}

println()
println 'Select para cuenta numero 66-22-50421312'
def queryCuenta = """ select * from cuenta where cuenta = ? """
sql.eachRow(queryCuenta,[cuenta]) { row ->
    println "$row.version - ${row.tipo_cuenta} $row.descripcion $row.cuenta $row.cliente_id $row.identificacion $row.saldo"
}
println()
println 'Select para cuenta numero 66-22-50421312'
 queryCuenta = """ select * from cuenta where cuenta = ? and cliente_id = ?"""
sql.eachRow(queryCuenta,[cuenta,clienteId]) { row ->
    println "$row.version - ${row.tipo_cuenta} $row.descripcion $row.cuenta $row.cliente_id $row.identificacion $row.saldo"
}


//Housekeeping
sql.close()

