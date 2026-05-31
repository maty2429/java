package estructuras_control;

/**
 * Guía de referencia sobre Estructuras de Control: Bucles.
 * Aquí se explican los diferentes tipos de bucles en Java.
 */
public class Bucles {

    public static void main(String[] args) {
        
        // --- BUCLE FOR CLÁSICO ---
        /*
        Sintaxis:
        for (inicio ; condición ; actualización) {
            // código que se repite
        }
        */

        System.out.println("--- Bucle for básico ---");
        for (int i = 0 ; i < 5 ; i++) {
            System.out.println(i);
        }

        /*
        Explicación:
        int i = 0 → crea una variable i que empieza en 0. Solo se ejecuta una vez al inicio.
        i < 5 → mientras esto sea true, el bucle sigue. Cuando sea false, para.
        i++ → después de cada vuelta, le suma 1 a i.

        Vuelta 1: i = 0 → ¿0 < 5? sí → imprime 0 → i++ → i = 1
        Vuelta 2: i = 1 → ¿1 < 5? sí → imprime 1 → i++ → i = 2
        Vuelta 3: i = 2 → ¿2 < 5? sí → imprime 2 → i++ → i = 3
        Vuelta 4: i = 3 → ¿3 < 5? sí → imprime 3 → i++ → i = 4
        Vuelta 5: i = 4 → ¿4 < 5? sí → imprime 4 → i++ → i = 5
        Revisión:  i = 5 → ¿5 < 5? NO → para el bucle
        */

        // Empezar desde 1 en vez de 0:
        System.out.println("\n--- Contar del 1 al 5 ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
        // imprime: 1, 2, 3, 4, 5
        // nota el <= en vez de <, esa es la diferencia clave

        // Contar hacia atrás:
        System.out.println("\n--- Cuenta regresiva ---");
        for (int i = 5; i >= 1; i--) {
            System.out.println("Cuenta regresiva: " + i);
        }
        // imprime: 5, 4, 3, 2, 1
        // i-- resta 1 en vez de sumar

        // Saltar de 2 en 2:
        System.out.println("\n--- Saltar de 2 en 2 ---");
        for (int i = 0; i <= 10; i = i + 2) {
            System.out.println(i);
        }
        // imprime: 0, 2, 4, 6, 8, 10
        // la actualización no tiene que ser siempre i++

        // Usar el valor de i dentro del bucle:
        System.out.println("\n--- Uso común (ej. productos) ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Procesando producto número " + i + " de 5");
        }

        // --- BUCLE FOR-EACH (Muy usado en Spring Boot) ---
        /*
        Cuando tienes una lista de cosas y quieres recorrerlas todas, usas for-each.
        No necesitas i, no necesitas contar, Java lo hace solo.
        */
        System.out.println("\n--- For-each con Array ---");
        String[] frutas = {"Manzana", "Pera", "Uva", "Mango"};

        for (String fruta : frutas) {
            System.out.println("Fruta: " + fruta);
        }
        // imprime cada fruta una por una

        // --- ERRORES COMUNES ---
        // ¿Cuántas veces imprime esto?
        // for (int i = 0; i < 5; i++) { ... } -> 5 veces (0, 1, 2, 3, 4)
        // for (int i = 0; i <= 5; i++) { ... } -> 6 veces (0, 1, 2, 3, 4, 5)


        // --- EJERCICIOS RESUELTOS ---

        // Ejercicio A: Imprimir los números del 1 al 10 usando for.
        System.out.println("\n--- Ejercicio A: 1 al 10 ---");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        // Ejercicio B: Imprime solo los números pares del 1 al 20.
        System.out.println("\n--- Ejercicio B: Pares del 1 al 20 ---");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        // Ejercicio C: Recorrer array de precios con for-each e imprimir cada precio.
        System.out.println("\n--- Ejercicio C: For-each con precios ---");
        double[] preciosArray = {15.0, 45.0, 120.0, 8.5, 200.0};
        for (double precio : preciosArray) {
            System.out.println("Precio: " + precio);
        }

        // Ejercicio D: Usar for clásico e imprimir precio con su posición.
        System.out.println("\n--- Ejercicio D: For clásico con posiciones ---");
        for (int i = 0; i < preciosArray.length; i++) {
            System.out.println("Posición " + i + " → precio: " + preciosArray[i]);
        }
    }
}
