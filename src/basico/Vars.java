package basico;

import java.util.HashMap;
import java.util.List;

public class Vars {
    public static void main(String[] args) {
        // =========================================================================
        // Tema 6 —var para variables locales (Java 10 +)
        // =========================================================================
        
        // ¿Qué es var ?
        // var le dice a Java "tú deduce el tipo, yo no lo escribo".Java mira el valor que le asignas
        // y automáticamente sabe qué tipo es:
        
        // ---------------------------------------------------------
        // Sin var — escribes el tipo explícitamente
        // ---------------------------------------------------------
        {
            String nombre = "Carlos";
            int edad = 25;
            double precio = 99.99;
            boolean activo = true;
        }

        // ---------------------------------------------------------
        // Con var — Java deduce el tipo solo
        // ---------------------------------------------------------
        {
            var nombre = "Carlos";   // Java sabe que es String
            var edad = 25;           // Java sabe que es int
            var precio = 99.99;      // Java sabe que es double
            var activo = true;       // Java sabe que es boolean
        }
        
        // El resultado es exactamente igual.var no cambia cómo funciona el código, solo te ahorra escribir el tipo.

        // ---------------------------------------------------------
        // Lo que var NO es
        // ---------------------------------------------------------
        // var no es como JavaScript donde una variable puede cambiar de tipo.En Java el tipo queda fijo desde que
        // lo declaras:
        {
            var nombre = "Carlos"; // Java decide que es String

            nombre = "Ana";        // ✅ funciona, sigue siendo String
            // nombre = 25;        // ❌ error — no puedes cambiar a int
        }
        // Java sigue siendo fuertemente tipado.var solo te ahorra escribir el tipo, no lo elimina.

        // ---------------------------------------------------------
        // Dónde SÍ puedes usar var
        // ---------------------------------------------------------
        // Solo funciona en variables locales, es decir dentro de métodos:
        // (Nota: Definimos el método procesarPedido() abajo en la clase para que el código compile y sea válido)
        
        /*
        public void procesarPedido () {
            var nombreProducto = "Laptop";    // ✅ variable local
            var precio = 1500.0;              // ✅ variable local
            var activo = true;                // ✅ variable local
        }
        */

        // ---------------------------------------------------------
        // Dónde NO puedes usar var
        // ---------------------------------------------------------
        /*
        public class Producto {
            var nombre = "Laptop";  // ❌ no funciona en atributos de clase
        }
        */
        
        // ❌ no funciona sin valor inicial
        // var nombre;
        // nombre = "Carlos";
        
        // ❌ no funciona con null solo
        // var nombre = null; // Java no sabe qué tipo es
        
        // ❌ no funciona en parámetros de métodos
        /*
        public void saludar (var nombre){
        } // error
        */

        // ---------------------------------------------------------
        // var con bucles —donde más lo usarás
        // ---------------------------------------------------------
        var productos = new String[]{"Laptop", "Mouse", "Teclado"};

        // for clásico
        System.out.println("--- For clásico ---");
        for (var i = 0; i < productos.length; i++) {
            System.out.println(productos[i]);
        }

        // for-each
        System.out.println("--- For-each ---");
        for (var producto : productos) {
            System.out.println(producto);
        }

        // ---------------------------------------------------------
        // var en Spring Boot —por qué importa
        // ---------------------------------------------------------
        // En Spring Boot los tipos pueden ser muy largos y difíciles de escribir.var los simplifica enormemente:
        
        // Sin var — tipos largos y repetitivos
        HashMap<String, List<String>> categoriasNormal = new HashMap<String, List<String>>();

        // Con var — mucho más limpio
        var categorias = new HashMap<String, List<String>>();
        
        // Cuando llegues a Spring Boot verás tipos como ResponseEntity<List<ProductoResponse>> que son muy largos.Con var
        // solo los escribes una vez.

        // ---------------------------------------------------------
        // Cuándo usar var y cuándo no
        // ---------------------------------------------------------
        // ✅ Úsalo cuando el tipo es obvio por el valor
        var nombreObvio = "Carlos";           // claramente un String
        var precioObvio = 99.99;              // claramente un double
        var activoObvio = true;               // claramente un boolean

        // ❌ No lo uses cuando el tipo no es obvio
        var resultado = calcular();      // ¿qué retorna calcular? no se sabe
        var x = metodoMisterioso();      // confuso para quien lea el código
        
        // La regla de oro:
        // Si quien lea tu código puede saber el tipo con solo mirar
        // el valor →usa var
        //
        // Si no puede saberlo sin ir a ver el método →escribe el tipo

        // ---------------------------------------------------------
        // Detalle adicional 1: El operador diamante (<>) y var (¡CUIDADO!)
        // ---------------------------------------------------------
        // Cuando usas el operador diamante (<>) sin especificar el tipo a la derecha,
        // Java no tiene suficiente información y deducirá Object. Esto es súper común.
        {
            // ⚠️ CUIDADO: Aquí lista1 se deduce como ArrayList<Object> (no es tipo-seguro para Strings)
            var lista1 = new java.util.ArrayList<>(); 
            lista1.add("Hola");
            lista1.add(123); // Permite meter cualquier cosa porque es ArrayList de Objects.
            
            // ✅ FORMA CORRECTA: Si quieres un tipo específico, escríbelo a la derecha de la igualdad:
            var lista2 = new java.util.ArrayList<String>(); // Java deduce correctamente ArrayList<String>
            lista2.add("Hola");
            // lista2.add(123); // ❌ ERROR DE COMPILACIÓN: solo acepta Strings
        }

        // ---------------------------------------------------------
        // Detalle adicional 2: Expresiones Lambda y var (❌ Restricción)
        // ---------------------------------------------------------
        // Las expresiones Lambda en Java necesitan un contexto (interfaz funcional) para saber qué tipo son.
        // Como var deduce del valor y la lambda por sí sola no tiene un tipo definido unívoco,
        // no se puede usar var directamente para declarar lambdas.
        {
            // ❌ ESTO DA ERROR DE COMPILACIÓN:
            // var saludo = () -> System.out.println("Hola"); // Java no puede deducir qué interfaz funcional es
            
            // ✅ FORMA CORRECTA: Debes usar la interfaz funcional explícitamente en el tipo de la izquierda:
            Runnable saludo = () -> System.out.println("Hola");
            saludo.run();
        }

        // ---------------------------------------------------------
        // Detalle adicional 3: Inicialización de Arrays simplificada
        // ---------------------------------------------------------
        // Al igual que con las lambdas, Java no puede deducir el tipo de un array a partir de llaves solas.
        {
            // ❌ ESTO DA ERROR DE COMPILACIÓN:
            // var numeros = {1, 2, 3}; // Java no sabe si es int[], double[], etc.
            
            // ✅ FORMA CORRECTA: Especifica el constructor del array con su tipo correspondiente:
            var numeros = new int[]{1, 2, 3}; // Java deduce correctamente que es un int[]
        }

        // ---------------------------------------------------------
        // Detalle adicional 4: Try-with-resources (Manejo automático de recursos)
        // ---------------------------------------------------------
        // En Spring Boot usarás esto frecuentemente para flujos de archivos, conexiones de red o sockets.
        // var brilla mucho aquí porque acorta mucho la declaración de recursos en el bloque try:
        /*
        // Ejemplo práctico conceptual:
        try (var lector = new java.io.BufferedReader(new java.io.FileReader("datos.txt"))) {
            System.out.println(lector.readLine());
        } catch (java.io.IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        */

        // ---------------------------------------------------------
        // Detalle adicional 5: var con Java Streams (Crucial para REST APIs en Spring Boot)
        // ---------------------------------------------------------
        // En las APIs REST con Spring Boot, constantemente harás mapeos y transformaciones de listas
        // (por ejemplo, convertir Entidades de Base de Datos a DTOs para responder al cliente).
        // Los Streams son la forma estándar en Java moderno, y var los hace súper limpios:
        {
            var nombresProductos = List.of("Laptop", "Mouse", "Teclado", "Monitor");
            
            // Filtramos los nombres que empiezan con "M", los convertimos a mayúsculas y los agrupamos en una nueva lista
            var productosConM = nombresProductos.stream()
                .filter(nombre -> nombre.startsWith("M"))
                .map(String::toUpperCase)
                .toList(); // .toList() produce una List<String>
                
            // Java deduce automáticamente que 'productosConM' es de tipo List<String>
            System.out.println("--- Productos que empiezan con M (Procesados con Streams y var) ---");
            System.out.println(productosConM); // Imprime: [MOUSE, MONITOR]
        }

        // -------------------------------------------------------------------------
        // Resumen completo de var
        // ---------------------------------------------------------
        // var         →Java deduce el tipo automáticamente
        // Solo        →en variables locales dentro de métodos
        // No          →en atributos de clase, parámetros, sin valor inicial
        // Tipo fijo   →el tipo queda fijo, no cambia como en JavaScript
        // Úsalo       →cuando el tipo es obvio por el valor asignado
        // No lo uses  →cuando el tipo no es obvio leyendo el código
    }

    // =========================================================================
    // Métodos auxiliares para los apuntes
    // =========================================================================

    // Dónde SÍ puedes usar var (Ejemplo de método real)
    public void procesarPedido () {
        var nombreProducto = "Laptop";    // ✅ variable local
        var precio = 1500.0;              // ✅ variable local
        var activo = true;                // ✅ variable local
    }

    private static int calcular() {
        return 42;
    }

    private static String metodoMisterioso() {
        return "Secreto";
    }
}

