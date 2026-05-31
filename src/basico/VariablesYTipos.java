package basico;

/**
 * Guía de referencia sobre Variables y Tipos de Datos en Java.
 * Este es un punto de partida fundamental.
 */
public class VariablesYTipos {

    public static void main(String[] args) {
        
        // --- TIPOS PRIMITIVOS ---
        
        // Enteros
        byte edadMuyJoven = 25;       // byte: -128 a 127 (ocupa 1 byte)
        short anio = 2024;           // short: -32,768 a 32,767 (ocupa 2 bytes)
        int cantidad = 100000;       // int: el más usado para enteros (4 bytes)
        long poblacionMundial = 8000000000L; // long: 8 bytes. Nota la 'L' al final obligatoria.
        
        // Decimales
        float precioFloat = 9.99f;    // float: nota la 'f' al final obligatoria.
        double temperatura = 36.6;   // double: el más usado para decimales.
        
        // Otros
        boolean activo = true;        // boolean: solo true o false
        char inicial = 'J';           // char: un solo carácter, con comillas simples
        
        // --- STRINGS (CADENAS DE TEXTO) ---
        // Son objetos, no primitivos. Se usan comillas dobles.
        String nombre = "Carlos";
        String apellido = "López";

        // Métodos útiles de String
        int longitud = nombre.length();           // 6
        String mayusculas = nombre.toUpperCase(); // "CARLOS"
        String completo = nombre + " " + apellido; // Concatenación: "Carlos López"
        boolean vacio = nombre.isEmpty();         // false
        
        // --- INFERENCIA DE TIPOS (var) ---
        // Java 10+. Se usa mucho en Spring Boot para abreviar.
        var ciudad = "Madrid";       // Java deduce que es String
        var stock = 50;              // Java deduce que es int
        
        // --- VALORES POR DEFECTO ---
        /*
        Cuando un atributo de clase (no local) no se inicializa:
        int -> 0
        double -> 0.0
        boolean -> false
        Object (String, etc) -> null (Causa el famoso NullPointerException)
        */
        
        // Imprimir resultados
        System.out.println("Nombre completo: " + completo);
        System.out.println("Longitud del nombre: " + longitud);
        System.out.println("¿Está activo?: " + activo);
        
        /*
        RECOMENDACIÓN PARA SPRING BOOT:
        En JPA (bases de datos), solemos usar Integer, Long, Double 
        en lugar de int, long, double porque permiten null.
        */
    }
}
