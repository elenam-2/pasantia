package list_map

lista1 = [6,9,5,7,4,2,3]

println(lista1)

println(lista1[2])
println(lista1[3,5])
println(lista1[-2])
println(lista1.sort())
lista1.add(100)
println(lista1)

lista2 = ["rojo","verde","azul"]
println(lista2)
lista3 = [lista1,lista2]

println(lista3)


for (i in lista1){
    println(i)
}