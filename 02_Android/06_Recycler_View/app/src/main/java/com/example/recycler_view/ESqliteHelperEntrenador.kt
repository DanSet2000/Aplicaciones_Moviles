package com.example.recycler_view

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(contexto: Context?, /* This */):
    SQLiteOpenHelper(contexto, "moviles" /*Nombre de la Base De Datos*/,null, 1)
{

    override fun onCreate(db: SQLiteDatabase?) {

        // Crear Scripts de SQL para una Base de Datos

        // Se realiza la estructura de las tablas para usar SQLite en lenguaje SQL

        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    // Funcion para Crear un Entrenador con la Base de Datos

    // Dos tipos de Variables de Base de Datos: Escritura y Lectura

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ):Boolean{                                          // Devolver un Boolean para saber si se creo o no la base de datos
        val basedatosEscritura = writableDatabase       // Escritura
        val valoresAGuardar = ContentValues()           // Guardar los datos del registro
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        // Nombre de la Tabla                   // Valores
        val resultadoaGuardar = basedatosEscritura.insert("ENTRENADOR", null, valoresAGuardar)

        basedatosEscritura.close()
        return if (resultadoaGuardar.toInt() == -1) false else true     // Devolver si se creo o no el registro
    }


    // Función para Eliminar un Registro por su ID

    fun eliminarEntrenadorFormulario(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "ENTRENADOR",   // Nombre Tabla
            "id=?",    // Consulta SQL
            parametrosConsultaDelete
        )

        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    // Funcion para Actualizar un Registro de la Tabla

    fun actualizarEntrenadorFormulario(nombre: String, descripcion: String, id: Int):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()

        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        // Where ID = ?
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "ENTRENADOR",            // Nombre Tabla
            valoresAActualizar,           // Valores
            "id=?",            //  Consulta Where
            parametrosConsultaActualizar
        )

        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    // Logica de Consultas

    fun consultarEntrenadorPorID(id: Int): BEntrenador{
        val baseDatosLectura = readableDatabase     // Lectura
        val scriptConsultaLectura =
            """
                SELECT * FROM ENTRENADOR WHERE ID = ?
            """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,      // Consulta
            parametrosConsultaLectura   // Parametros
        )


        // Logica de Busqueda

        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)                 // Indice 0
                val nombre = resultadoConsultaLectura.getString(1)          // Indice 1
                val descripcion = resultadoConsultaLectura.getString(2)     // Indice 2
                if (id != null){
                    // Llenar el arreglo con un nuevo BEntrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }

            }while(resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}