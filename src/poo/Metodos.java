package poo;

/**
 * TEMA 3: MÉTODOS Y OBJETOS
 * 
 * CONCEPTOS CLAVE:
 * 1. CLASE: Es el molde o plano (ej: El plano de una casa).
 * 2. OBJETO: Es lo real creado con el molde (ej: La casa construida).
 * 3. MÉTODO: Son las funciones que puede hacer ese objeto.
 */
public class Metodos {

    // ============================================================
    // SECCIÓN 1: ¿CÓMO CREAR UN OBJETO REAL? (La palabra 'new')
    // ============================================================
    /*
     * Piénsalo así: Una clase es como un molde y 'new' es lo que usa ese
     * molde para crear un objeto real en memoria.
     * 
     * ANALOGÍA SIMPLE:
     * Metodos             -> El PLANO de papel (la definición de abajo).
     * new Metodos()       -> FABRICAR el objeto real usando ese plano.
     * app                 -> El NOMBRE que le das para poder usarlo.
     * 
     * BREAKDOWN DE LA LÍNEA: Metodos app = new Metodos();
     * Metodos   -> El tipo de objeto que vas a crear.
     * app       -> El nombre con el que lo vas a usar.
     * =         -> Asignación.
     * new       -> CREA el objeto real en la memoria del ordenador.
     * Metodos() -> Usa el molde 'Metodos' para crearlo.
     */

    // ============================================================
    // SECCIÓN 2: ¿QUÉ ES CADA PARTE DE UN MÉTODO?
    // ============================================================
    /*
     * Ejemplo: public void saludar() { ... }
     * 
     * public  -> MODIFICADOR DE ACCESO: Cualquiera puede llamarlo.
     * void    -> TIPO DE RETORNO: El método "no devuelve nada", solo hace algo.
     * saludar -> NOMBRE DEL MÉTODO: El nombre que usas para llamarlo.
     * ()      -> PARÁMETROS: La puerta de entrada para datos.
     */

    // --- EJEMPLOS DE MÉTODOS DE INSTANCIA (Necesitan 'new') ---
    
    public void saludar() {
        System.out.println("Hola desde un método simple");
    }

    public void saludarPersona(String nombre, int edad) {
        System.out.println("Hola " + nombre + ", tienes " + edad + " años");
    }

    public int sumarInstancia(int a, int b) {
        return a + b;
    }

    // ============================================================
    // SECCIÓN 3: EL MODIFICADOR 'static' (Sin necesidad de 'new')
    // ============================================================
    /*
     * static -> Métodos que NO necesitan 'new'.
     * Cuando un método es static, lo puedes llamar directamente con el
     * nombre de la clase, sin fabricar un objeto real.
     * 
     * REGLA SIMPLE:
     * Sin static -> El método necesita datos del objeto para funcionar.
     * Con static -> El método solo necesita sus parámetros para funcionar.
     */

    // --- COMPARACIÓN DIRECTA ---
    /*
     * CON static -> Lo llamas directo con el nombre de la clase.
     * Ejemplo: Metodos.sumarStatic(10, 5); // Directo, sin nada más.
     * 
     * SIN static -> Primero creas una variable (objeto) y luego lo llamas.
     * Ejemplo: 
     * Metodos app = new Metodos(); 
     * app.sumarInstancia(10, 5); // Necesitas la variable 'app' primero.
     * 
     * RESUMEN DE USO:
     * static     -> Metodos.sumar(...)   (Llamada directa)
     * sin static -> app.sumar(...)       (Necesitas crear 'app' primero)
     */

    public static int sumarStatic(int a, int b) {
        return a + b;
    }

    public static double calcularIva(double precio) {
        return precio * 0.21; // Ejemplo con IVA del 21%
    }

    public static boolean esEmailValido(String email) {
        return email.contains("@");
    }

    // ============================================================
    // SECCIÓN 4: EL MÉTODO PRINCIPAL (Donde todo cobra vida)
    // ============================================================
    public static void main(String[] args) {
        
        System.out.println("--- 1. MÉTODOS CON 'new' (Instancia) ---");
        // Necesitamos fabricar el objeto 'app'
        Metodos app = new Metodos(); 
        app.saludar();
        System.out.println("Suma instancia: " + app.sumarInstancia(10, 5));

        System.out.println("\n--- 2. MÉTODOS CON 'static' (Directos) ---");
        // NO usamos 'app', usamos directamente el nombre de la clase 'Metodos'
        int resStatic = Metodos.sumarStatic(20, 30);
        double iva = Metodos.calcularIva(100.0);
        boolean esValido = Metodos.esEmailValido("usuario@correo.com");

        System.out.println("Suma static: " + resStatic);
        System.out.println("IVA de 100: " + iva);
        System.out.println("¿Email válido?: " + esValido);

        /*
         * --- RESUMEN FINAL DE MÉTODOS ---
         * ✅ Método sin retorno    -> void
         * ✅ Método con retorno    -> int, double, String, etc.
         * ✅ Parámetros           -> Datos que recibe para trabajar.
         * ✅ Sobrecarga           -> Mismo nombre, diferentes parámetros.
         * ✅ static               -> NO necesita 'new' para usarse.
         * 
         * EN SPRING BOOT:
         * Verás 'static' en utilidades y constantes.
         * Los Servicios y Controladores NO suelen usar 'static'.
         */
    }
}
