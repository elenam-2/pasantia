class prueba {
    public static void main(String[] args) {
println("prueba"+new Date())


        def lista1 = [6,9,5,7,4,2,3]

        println(lista1)

        println(lista1[2])
        println(lista1[3,5])
        println(lista1[-2])
        println(lista1.sort())
        lista1.add(100)
        println(lista1)

        def lista2 = ["rojo","verde","azul"]
        println(lista2)
        def lista3 = [lista1,lista2]

        println(lista3)


        for (i in lista1){
            println(i)
        }
    }
}