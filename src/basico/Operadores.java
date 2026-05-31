package basico;

/**
 * Guía de referencia sobre Operadores en Java.
 */
public class Operadores {

    public static void main(String[] args) {
        
        // --- ARITMÉTICOS ---
        int a = 10;
        int b = 3;

        int suma        = a + b;  // 13
        int resta       = a - b;  // 7
        int multiplicar = a * b;  // 30
        int division    = a / b;  // 3  ← ojo, división entera, no 3.33
        double divReal  = (double) a / b; // 3.333... ← cast necesario
        int modulo      = a % b;  // 1  ← el resto de la división

        System.out.println("--- Aritméticos ---");
        System.out.println("División entera: " + division);
        System.out.println("División real: " + divReal);

        // --- INCREMENTO Y DECREMENTO ---
        int x = 5;
        x++;  // x ahora es 6
        x--;  // x ahora es 5
        int y = ++x; // primero incrementa, luego asigna -> y = 6
        int z = x++; // primero asigna, luego incrementa -> z = 6, x = 7

        // --- COMPARACIÓN (Retornan boolean) ---
        int edad = 20;
        boolean esMayor    = edad > 18;   // true
        boolean esIgual    = edad == 18;  // false  ← doble signo igual
        boolean noEsIgual  = edad != 18;  // true
        boolean mayorIgual = edad >= 20;  // true

        // --- LÓGICOS ---
        boolean p = true;
        boolean q = false;
        boolean and = p && q; // false — ambos deben ser true
        boolean or  = p || q; // true  — al menos uno debe ser true
        boolean not = !p;     // false — invierte el valor

        System.out.println("\n--- Lógicos ---");
        System.out.println("True AND False: " + and);
        System.out.println("True OR False: " + or);
    }
}
