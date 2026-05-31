package estructuras_control;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Exepciones {

    // =========================================================================
    // Métodos de Apoyo: Permiten dar soporte real a tus ejemplos de estudio
    // =========================================================================

    // Método buscarProducto de tus apuntes (Sección "Crear tus propias excepciones")
    public static String buscarProducto(int id) {
        if (id <= 0) {
            throw new ProductoNoEncontradoException(id);
        }
        return "Laptop";
    }

    // Método buscarProducto con Exception Chaining (Sección "Encadenar excepciones")
    public static String buscarProductoConChaining(int id) {
        try {
            // simula buscar en base de datos
            String[] productos = {"Laptop", "Mouse"};
            return productos[id];
        } catch (ArrayIndexOutOfBoundsException e) {
            // atrapa el error técnico y lanza uno de tu negocio
            // pasas el error original como causa
            throw new ProductoNoEncontradoException(id, e);
        }
    }

    // Método leerArchivo de tus apuntes (Sección "throw vs throws")
    public static String leerArchivo(String ruta) throws FileNotFoundException {
        FileReader archivo = new FileReader(ruta);
        return "contenido";
    }

    public static void main(String[] args) {
        
        // =========================================================================
        // Tema 5 — Manejo de excepciones
        // =========================================================================
        // ¿Qué es una excepción?
        // Una excepción es un error que ocurre mientras tu programa está corriendo. No es un error de escritura que el
        // compilador detecta, sino algo que falla en tiempo de ejecución.
        //
        // Ejemplos de la vida real:
        // Tu programa intenta dividir un número entre cero        → error
        // Tu programa intenta convertir "hola" a número           → error
        // Tu programa intenta acceder a la posición 10 de un
        // array que solo tiene 5 elementos                        → error
        // Tu programa intenta usar una variable que es null       → error
        //
        // Sin manejo de excepciones tu programa simplemente muere y muestra un mensaje feo. Con manejo de excepciones
        // tú controlas qué pasa cuando algo sale mal.
        // En Spring Boot esto es crítico porque si un endpoint falla sin control, el servidor devuelve un error 500
        // genérico al cliente. Con excepciones bien manejadas devuelves un mensaje claro y útil.

        // =========================================================================
        // ¿Qué pasa sin manejo de excepciones?
        // =========================================================================
        // javapublic class Main {
        //     public static void main(String[] args) {
        //         int resultado = 10 / 0; // división entre cero
        //         System.out.println(resultado);
        //         System.out.println("Esta línea nunca se ejecuta");
        //     }
        // }
        //
        // Java muestra esto y el programa muere:
        // Exception in thread "main" java.lang.ArithmeticException: /by zero
        // at Main.main(Main.java:3)

        // =========================================================================
        // try - catch — la base del manejo de excepciones
        // =========================================================================
        // try significa "intenta ejecutar esto". catch significa "si algo sale mal, atrápalo y haz esto":
        // try {
        //     // código que puede fallar
        // } catch(TipoDeExcepcion e){
        //     // qué hacer si falla
        // }
        //
        // Aplicado al ejemplo anterior:
        {
            try {
                int resultado = 10 / 0; // intenta esto
                System.out.println(resultado);
            } catch(ArithmeticException e){
                // si falla por división entre cero, llega aquí
                System.out.println("Error: no puedes dividir entre cero");
            }

            System.out.println("El programa sigue corriendo"); // esto sí se ejecuta
            // Ahora el programa no muere. Atrapa el error, muestra un mensaje útil y continúa.
        }

        // =========================================================================
        // La variable e — qué información tiene
        // =========================================================================
        // La variable e dentro del catch contiene información sobre el error:
        {
            try {
                int resultado = 10 / 0;
            } catch(ArithmeticException e){
                System.out.println("\n--- Información de la variable 'e' ---");
                System.out.println("e.getMessage(): " + e.getMessage());   // / by zero
                System.out.println("e.getClass(): " + e.getClass());     // class java.lang.ArithmeticException
                System.out.println("e.printStackTrace() (Comentado en ejecución real para mantener limpia la consola)");
                // e.printStackTrace();                  // muestra toda la pila del error
            }
            // En Spring Boot usarás e.getMessage() constantemente para incluir el mensaje del error en la respuesta JSON que
            // devuelves al cliente.
        }

        // =========================================================================
        // Múltiples catch — atrapar diferentes errores
        // =========================================================================
        // Puedes tener varios catch para manejar diferentes tipos de errores:
        {
            String[] productos = {"Laptop", "Mouse", "Teclado"};
            String texto = "abc";

            try {
                // puede fallar por índice fuera de rango
                System.out.println(productos[10]);

                // puede fallar por conversión inválida
                int numero = Integer.parseInt(texto);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nError: posición no existe en el array");
            } catch (NumberFormatException e) {
                System.out.println("Error: no se puede convertir a número");
            } catch (Exception e) {
                // atrapa cualquier otro error no contemplado
                System.out.println("Error inesperado: " + e.getMessage());
            }
            // Java revisa los catch de arriba hacia abajo y entra al primero que coincida con el error. El catch(Exception e)
            // siempre va al final porque es el más general.
        }

        // =========================================================================
        // finally — código que se ejecuta siempre
        // =========================================================================
        // El bloque finally se ejecuta siempre, haya error o no. Se usa para cerrar recursos, conexiones a base
        // de datos, archivos, etc:
        {
            System.out.println();
            try {
                System.out.println("Intentando conectar a la base de datos");
                int resultado = 10 / 0; // falla aquí
                System.out.println("Conexión exitosa");
            } catch(ArithmeticException e){
                System.out.println("Error: " + e.getMessage());
            } finally{
                // esto siempre se ejecuta, con error o sin error
                System.out.println("Cerrando conexión a la base de datos");
            }
            // Salida:
            // Intentando conectar a la base de datos
            // Error: /by zero
            // Cerrando conexión a la base de datos  ← siempre se ejecuta
            // En Spring Boot finally lo usarás para cerrar conexiones y liberar recursos aunque el código haya fallado.
        }

        // =========================================================================
        // Las excepciones más comunes que verás en Spring Boot
        // =========================================================================
        System.out.println("\n--- Ejecución de las Excepciones Comunes (Manejadas con try-catch para no detener la ejecución) ---");

        // 1. NullPointerException — usar algo que es null
        {
            try {
                String nombre = null;
                nombre.length(); // ❌ NullPointerException
            } catch (NullPointerException e) {
                System.out.println("1. NullPointerException atrapada con éxito: " + e);
            }
        }

        // 2. ArrayIndexOutOfBoundsException — posición que no existe
        {
            try {
                int[] numeros = {1, 2, 3};
                int valor = numeros[5]; // ❌ ArrayIndexOutOfBoundsException
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("2. ArrayIndexOutOfBoundsException atrapada con éxito: " + e);
            }
        }

        // 3. NumberFormatException — convertir texto inválido a número
        {
            try {
                Integer.parseInt("hola"); // ❌ NumberFormatException
            } catch (NumberFormatException e) {
                System.out.println("3. NumberFormatException atrapada con éxito: " + e);
            }
        }

        // 4. ArithmeticException — operación matemática inválida
        {
            try {
                int resultado = 10 / 0; // ❌ ArithmeticException
            } catch (ArithmeticException e) {
                System.out.println("4. ArithmeticException atrapada con éxito: " + e);
            }
        }

        // 5. ClassCastException — convertir un objeto al tipo incorrecto
        {
            try {
                Object texto = "Carlos";
                Integer numero = (Integer) texto; // ❌ ClassCastException
            } catch (ClassCastException e) {
                System.out.println("5. ClassCastException atrapada con éxito: " + e);
            }
        }

        // =========================================================================
        // Checked vs Unchecked — dos tipos de excepciones
        // =========================================================================
        // Esta es la parte que confunde a todos al principio. En Java hay dos categorías de excepciones:
        // Unchecked — el compilador no te obliga a manejarlas:
        // El compilador no se queja aunque no pongas try-catch
        // pero si ocurren en runtime el programa muere
        {
            // int resultado = 10 / 0;           // ArithmeticException
            // String texto = null;
            // texto.length();                    // NullPointerException
            // Integer.parseInt("hola");         // NumberFormatException
            // Son errores que generalmente ocurren por bugs en tu código. El compilador confía en que los evitarás.
        }
        //
        // Checked — el compilador te OBLIGA a manejarlas:
        // Esto no compila si no pones try-catch o declaras que la lanzas
        // porque son errores externos que pueden ocurrir sin importar
        // qué tan bien escribas tu código
        //
        // Leer un archivo — el archivo puede no existir
        // FileReader archivo = new FileReader("datos.txt"); // ❌ no compila sin try-catch
        //
        // Con try - catch:
        {
            try {
                FileReader archivo = new FileReader("datos.txt");
            } catch(FileNotFoundException e){
                System.out.println("\n--- Ejemplo de Checked Exception ---");
                System.out.println("El archivo no existe: " + e.getMessage());
            }
        }
        // Regla simple para recordarlo:
        // Unchecked → errores de tu código    → compilador no obliga
        // Checked   → errores externos        → compilador sí obliga

        // =========================================================================
        // Crear tus propias excepciones — muy usado en Spring Boot
        // =========================================================================
        // En Spring Boot crearás excepciones personalizadas para representar errores de tu negocio. Por ejemplo cuando un
        // producto no existe:
        //
        // Crear la excepción personalizada
        // (La clase ProductoNoEncontradoException está definida al final de este archivo de forma integrada)
        //
        // Usarla en tu código
        // (El método buscarProducto está definido a nivel de clase arriba)
        //
        // Atraparla donde la necesites:
        {
            try {
                String producto = buscarProducto(-1);
            } catch (ProductoNoEncontradoException e) {
                System.out.println("\n--- Excepción Personalizada en Acción ---");
                System.out.println(e.getMessage());
                // "No existe el producto con id: -1"
            }
            // En Spring Boot esto se conecta directamente con los códigos HTTP. Cuando lanzas
            // ProductoNoEncontradoException puedes configurar Spring para que devuelva automáticamente un error 404 al cliente.
            // Lo verás cuando lleguemos al framework.
        }

        // =========================================================================
        // throw vs throws — dos palabras parecidas pero diferentes
        // =========================================================================
        // throw → lanza una excepción en ese momento
        // throw new ProductoNoEncontradoException(id);
        //
        // throws → avisa que un método PUEDE lanzar una excepción
        // el que llame al método debe manejarla
        // public String leerArchivo (String ruta) throws FileNotFoundException {
        //     FileReader archivo = new FileReader(ruta);
        //     return "contenido";
        // }
        //
        // Regla simple:
        // throw  → lanza la excepción ahora mismo
        // throws → avisa que el método puede lanzar una excepción

        // =========================================================================
        // try - with - resources — para cerrar recursos automáticamente
        // =========================================================================
        // En Spring Boot cuando trabajas con conexiones a base de datos o archivos, Java puede cerrarlos
        // automáticamente sin necesitar finally:
        //
        // ❌ Forma antigua — necesitas finally para cerrar
        {
            FileReader archivo = null;
            try {
                archivo = new FileReader("datos.txt");
                // leer archivo
            } catch (Exception e) {
                System.out.println("\nError al abrir archivo (Forma antigua): " + e.getMessage());
            } finally {
                if (archivo != null) {
                    try {
                        archivo.close(); // cerrar manualmente
                    } catch (IOException e) {
                        System.out.println("Error al cerrar archivo (Forma antigua): " + e.getMessage());
                    }
                }
            }
        }
        //
        // ✅ Forma moderna — se cierra solo al terminar el try
        {
            try (FileReader archivo = new FileReader("datos.txt")) {
                // leer archivo
                // archivo se cierra automáticamente al salir del try
            } catch (Exception e) {
                System.out.println("Error al abrir archivo (Forma moderna/try-with-resources): " + e.getMessage());
            }
            // Mucho más limpio. En Spring Boot el framework maneja esto por ti en la mayoría de casos, pero es importante
            // saber que existe.
        }

        // =========================================================================
        // Resumen completo del Tema 5
        // =========================================================================
        // try          → intenta ejecutar este código
        // catch        → atrapa el error si ocurre
        // finally      → se ejecuta siempre, con o sin error
        // throw        → lanza una excepción en ese momento
        // throws       → avisa que el método puede lanzar una excepción
        //
        // Unchecked    → NullPointerException, NumberFormatException,
        //               ArithmeticException, ArrayIndexOutOfBoundsException
        //               el compilador NO obliga a manejarlas
        //
        // Checked      → FileNotFoundException, IOException
        //               el compilador SÍ obliga a manejarlas
        //
        // Excepción    → crear clase que extienda RuntimeException
        // personalizada para representar errores de tu negocio
        //
        // try - with - resources → cierra recursos automáticamente

        // =========================================================================
        // Encadenar excepciones — Exception Chaining
        // =========================================================================
        // A veces un error ocurre por culpa de otro error. En Spring Boot es muy común atrapar una excepción técnica y relanzarla como una excepción de tu negocio:
        // (El método buscarProductoConChaining está definido a nivel de clase arriba)
        {
            try {
                String producto = buscarProductoConChaining(10);
            } catch (ProductoNoEncontradoException e) {
                System.out.println("\n--- Exception Chaining (Encadenar excepciones) ---");
                System.out.println("Mensaje de negocio: " + e.getMessage());
                System.out.println("Causa técnica real (e.getCause()): " + e.getCause());
            }
            // Así quien use tu método recibe un error con significado de negocio, no un error técnico confuso.
        }

        // =========================================================================
        // Multi-catch — atrapar varios errores en un solo catch
        // =========================================================================
        // En vez de escribir un catch por cada tipo de error puedes agruparlos:
        {
            try {
                String texto = null;
                int numero = Integer.parseInt(texto);
                int resultado = 10 / 0;

            } catch (NumberFormatException | ArithmeticException | NullPointerException e) {
                // atrapa cualquiera de los tres
                System.out.println("\n--- Ejemplo de Multi-catch ---");
                System.out.println("Error controlado: " + e.getMessage());
            }
            // El símbolo | se lee como "o". Úsalo cuando vas a hacer lo mismo con varios tipos de error.
        }

        // =========================================================================
        // Buenas prácticas con excepciones en Spring Boot
        // =========================================================================
        // Esto es lo que verás en proyectos reales:
        // 1. Nunca atrapes Exception genérica si puedes ser más específico:
        // ❌ Demasiado general — atrapa cualquier cosa incluyendo errores que no esperabas
        /*
        try {
            // código
        } catch (Exception e) {
            System.out.println("Error");
        }
        */
        //
        // ✅ Específico — sabes exactamente qué error manejas
        /*
        try {
            // código
        } catch (NumberFormatException e) {
            System.out.println("El valor no es un número válido");
        }
        */
        //
        // 2. Nunca ignores una excepción:
        // ❌ Pésima práctica — el error desaparece en silencio
        /*
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            // vacío — nunca hagas esto
        }
        */
        //
        // ✅ Siempre haz algo con el error
        /*
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        */
        //
        // 3. Los mensajes de error deben ser útiles:
        // ❌ Mensaje inútil
        // throw new RuntimeException("Error");
        //
        // ✅ Mensaje que explica qué pasó y con qué dato
        // throw new RuntimeException("No se encontró producto con id: " + id);

        // =========================================================================
        // Jerarquía de excepciones — importante saberla
        // =========================================================================
        // En Java todas las excepciones son clases y tienen una jerarquía:
        // Throwable
        // ├── Error              → errores graves de la JVM, no los manejes
        // │   └── OutOfMemoryError
        // │   └── StackOverflowError
        // └── Exception          → los que sí manejas
        //     ├── IOException          → Checked (compilador obliga)
        //     │   └── FileNotFoundException
        //     ├── SQLException         → Checked
        //     └── RuntimeException     → Unchecked (compilador no obliga)
        //         ├── NullPointerException
        //         ├── ArithmeticException
        //         ├── NumberFormatException
        //         ├── ArrayIndexOutOfBoundsException
        //         └── ClassCastException
        // Lo importante de esta jerarquía:
        // Si atrapas Exception     → atrapa todo lo que está debajo
        // Si atrapas RuntimeException → atrapa todas las unchecked
        // Si atrapas IOException   → atrapa solo las checked de IO
        // Por eso cuando pones catch (Exception e) al final atrapa cualquier cosa que no atrapaste antes.

        // =========================================================================
        // En Spring Boot las excepciones se conectan con HTTP
        // =========================================================================
        // Esto lo verás en profundidad cuando llegues al framework, pero es bueno saber desde ahora que existe:
        // Tu excepción personalizada        →  código HTTP que devuelve
        // ProductoNoEncontradoException     →  404 Not Found
        // UsuarioNoAutorizadoException      →  401 Unauthorized
        // DatosInvalidosException           →  400 Bad Request
        // ErrorInternoException             →  500 Internal Server Error
        // Cuando lanzas una excepción en tu servicio, Spring Boot la atrapa y convierte automáticamente en una respuesta JSON con el código HTTP correcto. Esa es la magia que verás en la Fase de Spring Boot.

        // =========================================================================
        // Resumen final completo del Tema 5
        // =========================================================================
        // try-catch          → manejar errores controladamente
        // finally            → ejecutar siempre, con o sin error
        // try-with-resources → cerrar recursos automáticamente
        // throw              → lanzar excepción en ese momento
        // throws             → avisar que el método puede lanzar excepción
        // multi-catch        → atrapar varios errores con |
        // exception chaining → relanzar error técnico como error de negocio
        //
        // Unchecked → RuntimeException y sus hijos
        // compilador NO obliga a manejarlas
        // Checked   → Exception directa y sus hijos no Runtime
        // compilador SÍ obliga a manejarlas
        //
        // Excepción personalizada → extends RuntimeException
        // super("mensaje útil con contexto")
        //
        // Buenas prácticas:
        //   ✅ Sé específico en el catch
        //   ✅ Nunca dejes el catch vacío
        //   ✅ Mensajes de error con contexto útil
        //   ❌ No atrapes Exception genérica si puedes evitarlo
        //   ❌ No ignores excepciones en silencio
    }
}

// =============================================================================
// 💡 Clase de Apoyo: Excepción Personalizada
// =============================================================================
class ProductoNoEncontradoException extends RuntimeException {

    // Constructor 1 — Básico (De tus apuntes)
    public ProductoNoEncontradoException(int id) {
        super("No existe el producto con id: " + id);
    }

    // Constructor 2 — Con causa (Permite soportar Exception Chaining)
    public ProductoNoEncontradoException(int id, Throwable cause) {
        super("No existe el producto con id: " + id, cause);
    }
}
