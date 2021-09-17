

conta = 1
new File("Example.txt").eachLine {
    line -> println "Linea:  $conta  : $line";
        conta++
}

new File('./','ejemplo2.txt').withWriter('utf-8') {
    writer -> writer.writeLine ('Hello World ' +new Date())
}