@GrabConfig(systemClassLoader=true)
@Grab(group='mysql', module='mysql-connector-java', version='8.0.22')

import groovy.sql.Sql
import groovy.json.*

println("groovy Sql...")

def sql = Sql.newInstance("jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
        "root",
        "sunshine-2021",
        "com.mysql.cj.jdbc.Driver")

def clienteId = args[0]

println "Select para cliente_id ${clienteId}"

def queryNumeroCliente = """ select * from cuenta where cliente_id =  ? """

def listaClientes = []

//Inyeccion de SQL
sql.eachRow(queryNumeroCliente,[clienteId]) { row ->
    Cliente c = new Cliente()
    c.setVersion(row.version)
    c.setTipoCuenta(row.tipo_cuenta)
    c.setDescripcion(row.descripcion)
    c.setCuenta(row.cuenta)
    c.setClienteId(row.cliente_id)
    c.setIdentificacion(row.identificacion)
    c.setSaldo(row.saldo)

    listaClientes.add(c)
}

def reporte = new File("clientes-${clienteId}.json")
reporte.delete()
reporte.createNewFile()
reporte.write(JsonOutput.prettyPrint(JsonOutput.toJson(listaClientes)))

sql.close()

//objeto cliente
class Cliente {
    def version
    def tipoCuenta
    def descripcion
    def cuenta
    def clienteId
    def identificacion
    def saldo
}