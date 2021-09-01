import java.text.MessageFormat

class Asignacion1 {
    static void main(String[] args) {
        //define donde esta ubicado el archivo y cual es
        def rutaArchivo = 'C:\\Users\\elena\\Documents\\datos_cliente.csv'

        File archivo = new File(rutaArchivo)
        int numeroLineas = 0
        String linea
        double promedioGeneral = 0

        //Leer el archivo linea a linea
   /*     archivo.withReader { reader ->
            while ((linea = reader.readLine()) != null) {
                if(!linea.startsWith('version')){
                    String [] data = linea.split(',')
                    def saldo = data [6]
                    def id = data [5]
                    promedioGeneral = promedioGeneral + Double.valueOf(saldo)
                    numeroLineas++
                }
            }
        }

        println 'Cantidad de lineas : ' + numeroLineas
        println 'Promedio general : ' + promedioGeneral / numeroLineas

        def provincias = [0:"Panama Oeste", 1:"Bocas del Toro"...]

        def provincias =[:]
        provincias[0] ="Panama Oeste"
        provincias[1] = "Bocas del Toro"


*/
        //Map para traducir numeros a nombre de provincias
        HashMap<Integer, String> provincias = new HashMap<>()
        provincias.put(0, 'Panama Oeste')
        provincias.put(1, 'Bocas del Toro')
        provincias.put(2, 'Cocle')
        provincias.put(3, 'Colon')
        provincias.put(4, 'Chiriqui')
        provincias.put(5, 'Darien')
        provincias.put(6, 'Los Santos')
        provincias.put(7, 'Herrera')
        provincias.put(8, 'Panama')
        provincias.put(9, 'Veraguas')

        //Leer el archivo linea a linea para promedio por provincias

        def accumulador = [:]
        def provincia_conta = [:]

        archivo.withReader { reader ->
            linea = reader.readLine()
            println(linea)
            while ((linea = reader.readLine()) != null) {
              //  if(!linea.startsWith('version')) {
                    String[] data = linea.split(',')
                    def cedula = data[5]
                    String[] digitos = cedula.split('-')
                    def provincia_id = Integer.parseInt(digitos[0])
                    def saldo = Double.parseDouble(data[6])

                    promedioGeneral = promedioGeneral +saldo
                    numeroLineas++
                    if (accumulador[provincia_id] == null) {
                        accumulador[provincia_id] =  saldo
                        provincia_conta[provincia_id] =1
                    } else {
                        //provincia = 2

                        accumulador[provincia_id] = accumulador[provincia_id] + saldo
                //}
                //println provincias.get(Integer.parseInt(digitos[0]))

                       //sumando todo lo que inicie por 2
                }
            }
        }
        println accumulador
        println provincia_conta
        println 'Cantidad de lineas : ' + numeroLineas
        println 'Promedio general : ' + promedioGeneral / numeroLineas

        def reporte = new File("reporte.txt")
        reporte.delete()
        reporte.createNewFile()
        reporte.write('=====================|====================\n')
        reporte.append(String.format("%-20s | %s\n", "Provincia", "Saldo promedio"))
        reporte.append('=====================|====================\n')

        (0..9).each{cod_provincia ->
            reporte.append(String.format("%-20s | %,10.4f\n", provincias[cod_provincia],accumulador[cod_provincia]/provincia_conta[cod_provincia]))
        }
    }
}
