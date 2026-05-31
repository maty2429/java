package basico;

public class Strings {
    public static void main(String[] args) {

        // =========================================================================
        // Tema: Strings en Java (Apuntes de Estudio)
        // =========================================================================
        // Un String es simplemente texto. Es el tipo de dato que más vas a usar en Spring Boot 
        // porque todo lo que llega y sale de una API REST es texto: nombres, emails, tokens, 
        // URLs, mensajes de error.

        // ---------------------------------------------------------
        // Crear un String
        // ---------------------------------------------------------
        {
            String nombre = "Carlos";
            String apellido = "López";
            String vacio = "";        // String vacío
            String nulo = null;       // Sin valor, cuidado con esto
        }

        // ---------------------------------------------------------
        // Unir Strings
        // ---------------------------------------------------------
        {
            String nombre = "Carlos";
            String apellido = "López";

            // Con el operador +
            String completo = nombre + " " + apellido;
            System.out.println("--- Unir Strings (operador +) ---");
            System.out.println(completo); // Carlos López

            // Con el método formatted — más limpio y moderno
            String mensaje = "Hola %s, tienes %d años".formatted("Carlos", 25);
            System.out.println("--- Unir Strings (.formatted) ---");
            System.out.println(mensaje); // Hola Carlos, tienes 25 años
        }
        
        // Los símbolos dentro de formatted:
        // %s  → reemplaza un String
        // %d  → reemplaza un número entero
        // %f  → reemplaza un decimal

        // ---------------------------------------------------------
        // Métodos más usados en Spring Boot
        // ---------------------------------------------------------
        
        // Verificar contenido:
        {
            String email = "carlos@gmail.com";

            boolean vacio = email.isEmpty();           // false — solo detecta ""
            boolean enBlanco = email.isBlank();           // false — detecta "" y "   " espacios
            boolean contieneArroba = email.contains("@");       // true  — contiene ese texto
            boolean empiezaCon = email.startsWith("carlos");// true  — empieza con ese texto
            boolean terminaCon = email.endsWith(".com");    // true  — termina con ese texto
            boolean esIgual = email.equals("otro@gmail.com");       // false — compara exacto
            boolean esIgualIgnoraMayus = email.equalsIgnoreCase("CARLOS@GMAIL.COM"); // true — ignora mayúsculas
        }
        
        // Transformar:
        {
            String nombre = "  Carlos López  ";

            String recortado = nombre.trim();          // "Carlos López"     — quita espacios inicio y fin
            String mayuscula = nombre.toUpperCase();   // "  CARLOS LÓPEZ  " — todo mayúsculas
            String minuscula = nombre.toLowerCase();   // "  carlos lópez  " — todo minúsculas
            String reemplazado = nombre.replace("Carlos", "Ana"); // "  Ana López  " — reemplaza texto
            
            // 💡 NOTA DE COMPLEMENTO: Inmutabilidad
            // En Java, los Strings son INMUTABLES en memoria. Ninguno de estos métodos cambia el
            // valor original dentro de la variable 'nombre'. Siempre retornan un nuevo String modificado.
        }
        
        // Extraer información:
        {
            String email = "carlos@gmail.com";

            int longitud = email.length();         // 16       — cuántos caracteres tiene
            int posicionArroba = email.indexOf("@");     // 6        — posición donde está el @
            String usuario = email.substring(0, 6);  // "carlos" — extrae del índice 0 al 5
            String dominio = email.substring(7);     // "gmail.com" — extrae desde el índice 7 hasta el final
        }
        
        // Dividir:
        {
            String fecha = "2024-12-25";

            String[] partes = fecha.split("-"); // divide por el guión
            System.out.println("--- Dividir String (.split) ---");
            System.out.println(partes[0]); // 2024
            System.out.println(partes[1]); // 12
            System.out.println(partes[2]); // 25
        }

        // ---------------------------------------------------------
        // Comparar Strings — el error más común en Java
        // ---------------------------------------------------------
        {
            String a = "Carlos";
            String b = "Carlos";

            // ❌ NUNCA compares Strings con ==
            if (a == b) { } // puede fallar aunque parezcan iguales

            // ✅ SIEMPRE usa equals
            if (a.equals(b)) { } // true, siempre confiable

            // ✅ Si el String puede ser null usa esta forma
            if ("Carlos".equals(a)) { } // evita NullPointerException
        }
        // ¿Por qué == falla con Strings? Porque == compara si son el mismo objeto en memoria, 
        // no si tienen el mismo texto. equals compara el contenido. Esto lo entenderás mejor 
        // en Fase 2 cuando veamos objetos, por ahora grábate la regla: Strings siempre con equals.

        // ---------------------------------------------------------
        // Verificar si un String es null o vacío
        // ---------------------------------------------------------
        // En Spring Boot recibirás datos de usuarios que pueden llegar vacíos o nulos. Siempre debes validarlos:
        {
            String nombre = null;

            // ❌ Esto lanza NullPointerException (descoméntalo si quieres ver cómo "explota" el código):
            // if (nombre.isEmpty()) { }

            // ✅ Primero verifica null, luego el contenido
            if (nombre == null || nombre.isBlank()) {
                System.out.println("--- Validación Null/Vacío ---");
                System.out.println("Nombre inválido");
            }
        }
        // El orden importa. Primero == null y luego isBlank(). Si lo pones al revés y el String es null, 
        // Java explota antes de llegar al isBlank().

        // ---------------------------------------------------------
        // StringBuilder — para construir texto en bucles
        // ---------------------------------------------------------
        // Cuando construyes texto dentro de un bucle no uses + porque es lento. Usa StringBuilder:
        
        // ❌ Lento — crea un String nuevo en cada vuelta
        /*
        String resultadoLento = "";
        for (int i = 1; i <= 5; i++) {
            resultadoLento = resultadoLento + "Producto " + i + ", ";
        }
        */

        // ✅ Eficiente — modifica el mismo objeto
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= 5; i++) {
                sb.append("Producto ").append(i).append(", ");
            }
            String resultado = sb.toString();
            System.out.println("--- Construir Texto (StringBuilder) ---");
            System.out.println(resultado);
            // Producto 1, Producto 2, Producto 3, Producto 4, Producto 5,
        }

        // ---------------------------------------------------------
        // Ejemplo integrado real de Spring Boot
        // ---------------------------------------------------------
        // En un endpoint REST recibirás datos de un usuario y tendrás que validarlos y transformarlos. 
        // Así se ve con lo que ya sabes:
        {
            String emailRecibido = "  CARLOS@Gmail.COM  ";

            // Limpiar y normalizar el email
            String emailLimpio = emailRecibido.trim().toLowerCase();
            System.out.println("--- Ejemplo Spring Boot (Email Limpio) ---");
            System.out.println(emailLimpio); // carlos@gmail.com

            // Validar que sea un email válido
            System.out.println("--- Ejemplo Spring Boot (Validaciones) ---");
            if (emailLimpio.isBlank()) {
                System.out.println("Error: email vacío");
            } else if (!emailLimpio.contains("@")) {
                System.out.println("Error: email sin @");
            } else {
                // Extraer el dominio
                String[] partes = emailLimpio.split("@");
                String usuario = partes[0]; // carlos
                String dominio = partes[1]; // gmail.com
                System.out.println("Usuario: " + usuario);
                System.out.println("Dominio: " + dominio);
            }
        }
        // Eso es exactamente lo que harás en un servicio REST de Spring Boot cuando valides datos de entrada.

        // ---------------------------------------------------------
        // Convertir otros tipos a String y viceversa
        // ---------------------------------------------------------
        // En Spring Boot constantemente recibirás números como texto y necesitarás convertirlos, o al revés.
        
        // De número a String:
        {
            int edad = 25;
            double precio = 99.99;

            // Convertir a String
            String edadTexto   = String.valueOf(edad);   // "25"
            String precioTexto = String.valueOf(precio); // "99.99"

            // También funciona así
            String edadTexto2 = Integer.toString(edad);  // "25"
        }
        
        // De String a número:
        {
            String edadTexto   = "25";
            String precioTexto = "99.99";

            // Convertir a número
            int edad       = Integer.parseInt(edadTexto);      // 25
            double precio  = Double.parseDouble(precioTexto);  // 99.99
        }

        // ---------------------------------------------------------
        // El error más común al convertir
        // ---------------------------------------------------------
        // Si el String no es un número válido Java lanza un error:
        /*
        String texto = "hola";
        int numero = Integer.parseInt(texto); // ❌ NumberFormatException
        */
        
        // Por eso en Spring Boot siempre validas antes de convertir:
        {
            String valor = "25abc";

            // Verificar que solo tenga dígitos antes de convertir
            System.out.println("--- Ejemplo Conversión Segura ---");
            if (valor.matches("\\d+")) {
                int numero = Integer.parseInt(valor);
                System.out.println("Número válido: " + numero);
            } else {
                System.out.println("Error: no es un número válido");
            }
        }
        // \\d+ significa "uno o más dígitos". No te preocupes por aprenderlo a fondo ahora, 
        // en Spring Boot hay formas más elegantes de validar. Solo recuerda que existe.


        // =========================================================================
        // 💡 CONCEPTOS COMPLEMENTARIOS MODERNOS (Agregados para complementar tus apuntes)
        // =========================================================================

        // 1. Text Blocks (Java 15+): Escribir bloques de texto multilínea fácilmente
        // Al probar endpoints de APIs REST en Spring Boot, muchas veces vas a simular JSONs completos.
        // Escribir JSONs con comillas simples o dobles escapadas (\") era horrible. ¡Hoy usamos bloques de texto!
        {
            String jsonMock = """
                {
                    "nombre": "Carlos",
                    "rol": "ADMIN",
                    "activo": true
                }
                """;
            System.out.println("--- Bloque de Texto Multilínea (Text Block) ---");
            System.out.println(jsonMock);
        }

        // 2. String.join(): Unir una lista de textos con un delimitador
        // Ideal en Spring Boot cuando quieres crear textos separados por comas o construir rutas dinámicas.
        {
            String etiquetas = String.join(", ", "Spring-Boot", "REST-API", "Java-17");
            System.out.println("--- String.join() ---");
            System.out.println(etiquetas); // Imprime: Spring-Boot, REST-API, Java-17
        }
    }
}
