

def auto1 = new Auto(color:"Verde",marca: "Ford",modelo:"150F",año:2018)
def auto2 = new Auto(color:"azul",marca: "Mazda",modelo:"CX-5",año:2015)
def auto3 = new Camioneta(color:"azul",marca: "Honda",modelo:"Pilot",año:2012,puertas: 5)
println(auto1.toString())
println(auto2.toString())
println(auto3.toString())

def auto4 = new Auto(color:"azul",marca: "Mazda")
auto4.año = 2020
auto4.modelo  = "ZX-200"
println(auto4.toString())