import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hola Mundo!")

    // VARIABLES

    // Inmutables (No Se Reasignan "=")
    val inmutable: String = "Daniel";
    //  inmutable: "Alejandro";

    // Mutables (Re Asignar)
    var mutable: String = "Alejandro";
    mutable = "Daniel";

    // val > var
    // Duck Typing : Entiende los tipos de datos automaticamente
    var ejemploVariable = "Daniel Carvajal"
    val edadEjemplo: Int = 12;
    ejemploVariable.trim() //Le asigno como tipo String

    // VARIABLES PRIMITIVAS
    val nombreProfesor: String = "Adrian Eguez";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;
    // Importar Clases Java (java.util)
    val fechaNacimiento: Date = Date();

    // SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No Se Sabe")
        }
    }

    // FUNCIONES

    // if - else
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"


    // void = unit
    // Declarar Funciones

    fun imprimirNombre(nombre: String): Unit{
        // "Nombre : " + nombre (otra forma de declarar)
        println("Nombre : ${nombre}") //Template String
    }


    // Usando la Funcion Calcular Sueldo

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)

    calcularSueldo(10.00, 12.00, 25.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) // Named Parameters

    // Probando la Suma
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // FOR EACH -> Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    // It significa el elemento iterado
    arregloDinamico.forEach{ println("Valor actual: ${it}") }

    arregloEstatico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviamos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    // FILTER -> Filtrar el Arreglo
    // 1) Devolver una expresion (True o False)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            // Expresion de Condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter {
        it <= 5
    }

    println(respuestaFilter)
    println(respuestaFilterDos)


    // OR AND
    // OR -> ANY (Alguno Cumple)
    // AND -> ALL (Todos Cumplen)

    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual: Int ->
                return@any (valorActual > 5)
        }
    println(respuestaAny) // True

    val respuestaAll: Boolean = arregloDinamico
        .all {valorActual: Int ->
                return@all (valorActual > 5)
        }
    println(respuestaAll) // False

    // REDUCE -> Valor Acumulado
    /* Valor Acumulado = 0 (Siempre 0 en lenguajes Kotlin)
        [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
        valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
        valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
        valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
        valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
        valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5
     */

    val respuestaReduce: Int = arregloDinamico
        .reduce{ // Acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica Negocio
        }
    println(respuestaReduce) // 78

    // Acumulado + (itemCarrito.cantidad + itemCarrito.precio)

}

    // Funcion Completa

    fun calcularSueldo(
        // Declaracion de Variables
        sueldo: Double, // Requerido
        tasa: Double = 12.00, // Opcional (Valores x Defecto)
        bonoEspecial: Double? = null, // Opcion null -> nullable (Puede tener el valor null en algun momento)
    ): Double{
        // Int -> Int? (nullable)
        // String -> String? (nullable)
        // Date -> Date? (nullable)

        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo * (100/tasa) + bonoEspecial
        }

    }


    // CLASES

    // Clase Abstracta
    abstract class NumerosJava{
        protected val numeroUno: Int
        private val numeroDos: Int

        constructor(
            uno: Int,
            dos: Int
        ){ // Bloque de codigo del Constructor
            this.numeroUno = uno
            this.numeroDos = dos
            println("Inicializando")
        }
    }

    // Clase
    abstract class Numeros( // Constructor PRIMARIO
        // Ejemplo:
        // uno: Int, (Parametro (Sin modificador de acceso))
        // private var uno: Int, // Propiedad Publica Clase numeros.uno
        // var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
        // public var uno: Int,
        protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
        protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos
    ){
        // var cedula: string = "" (public es por defecto)
        // private valorCalculado: Int = 0 (private)
        init { // Bloque constructor primario
            this.numeroUno; this.numeroDos; // this es opcional
            numeroUno; numeroDos; //sin el this es lo mismo
            println("Inicializando")
        }

    }

    // Clase

    class Suma( // Constructor Primario Suma
        unoParametro: Int,  // Parametro
        dosParametro: Int,  // Parametro
    ): Numeros(unoParametro, dosParametro){ // Extendiendo y Mandado los Parametros (super)
        init { // Bloque Codigo Constructor Primario
            this.numeroDos
            this.numeroDos
        }

        constructor( // Segundo constructor
            uno: Int?,  // Parametros
            dos: Int    // Parametros
        ):this(
            if(uno == null) 0 else uno,
            dos
        )

        constructor( // Tercer Constructor
            uno: Int,   // Parametros
            dos: Int?   // Parametros
        ):this(
            uno,
            if(dos == null) 0 else dos,
        )

        constructor( // Cuarto Constructor
            uno: Int?,  // Parametro
            dos: Int?   // Parametro
        ) :this(    // Llamada Constructor Primario
            if (uno == null) 0 else uno,
            if (dos == null) 0 else uno
        )

        // Public por defecto, o usar private o protected
        public fun sumar(): Int{
            val total = numeroUno + numeroDos
            // Suma.agregarHistorial(total)
            agregarHistorial(total)
            return total
        }

        // Atributos y Metodoso "Compartidos"
        companion object {
            // Entre las Instancias
            val pi = 3.14

            fun elevarAlCuadrado(num: Int):Int{
                return num*num
            }

            val historialSumas = arrayListOf<Int>()

            fun agregarHistorial(valorNuevaSuma:Int){
                historialSumas.add(valorNuevaSuma)
            }
        }

    }



