package list_map

import groovy.json.*


def http = [
        100 : 'CONTINUE',
        200 : 'OK',
        400 : 'BAD REQUEST'
]
def persona = [:]
persona["nombre"]= "Juan Perez"
persona["ID"]= "6-30-3939"
persona["direccion"] ="Loma colorada #123"
persona["telefono"] ="290-3039"
println(http)
println(persona)

def mapAsJson = JsonOutput.toJson(persona)
println(mapAsJson)

def cliente  = [
        persona : persona,
        cliente: 1021982,
        cuentas:[1298192889,21982922,291288]
]

  mapAsJson = JsonOutput.toJson(cliente)
println(mapAsJson)