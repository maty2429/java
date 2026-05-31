package estructuras_control;

/**
 * Guía de referencia sobre Estructuras de Control: Condicionales.
 */
public class Condicionales {

    public static void main(String[] args) {
        
        // --- IF - ELSE IF - ELSE ---
        int hora = 14;

        System.out.println("--- Estructura if-else ---");
        if (hora < 12) {
            System.out.println("Buenos días");
        } else if (hora < 18) {
            System.out.println("Buenas tardes");
        } else {
            System.out.println("Buenas noches");
        }

        // --- OPERADOR TERNARIO ---
        // Para condiciones simples en una sola línea.
        int edad = 20;
        String resultado = edad >= 18 ? "Mayor de edad" : "Menor de edad";
        System.out.println("\n--- Operador Ternario ---");
        System.out.println(resultado);

        // --- SWITCH CLÁSICO ---
        String dia = "LUNES";

        System.out.println("\n--- Switch Clásico ---");
        switch (dia) {
            case "LUNES":
            case "MARTES":
            case "MIERCOLES":
            case "JUEVES":
            case "VIERNES":
                System.out.println("Día laboral");
                break; // ← Importante: sin break cae al siguiente case
            case "SABADO":
            case "DOMINGO":
                System.out.println("Fin de semana");
                break;
            default:
                System.out.println("Día inválido");
        }

        // --- SWITCH MODERNO (Java 14+) ---
        // Más limpio, sin break, usa flechas (->)
        System.out.println("\n--- Switch Moderno ---");
        String tipoDia = switch (dia) {
            case "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" -> "Día laboral";
            case "SABADO", "DOMINGO" -> "Fin de semana";
            default -> "Día inválido";
        };

        System.out.println("Tipo de día: " + tipoDia);
    }
}
