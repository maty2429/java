package basico;

/**
 * TEMA: ARRAYS — DESDE CERO ABSOLUTO
 */
public class ArraysEstudio {

    public static void main(String[] args) {

        // ============================================================
        // 1. IMAGINA EL PROBLEMA
        // ============================================================
        /*
         * Imagina que necesitas guardar el nombre de un producto. Fácil, una variable:
         * String producto = "Laptop";
         * 
         * Pero ahora imagina que necesitas guardar 5 productos. ¿Harías esto?
         * String producto1 = "Laptop";
         * String producto2 = "Mouse";
         * String producto3 = "Teclado";
         * String producto4 = "Monitor";
         * String producto5 = "Audífonos";
         * 
         * Funciona, pero es HORRIBLE. Si tuvieras 1000 productos sería imposible. 
         * Para eso existe el array, para guardar muchos valores en una sola variable.
         */

        // ============================================================
        // 2. CREAR UN ARRAY
        // ============================================================
        String[] productos = {"Laptop", "Mouse", "Teclado", "Monitor", "Audífonos"};
        
        /*
         * Se lee así:
         * String[]    → varias cosas de tipo String
         * productos   → el nombre de la variable
         * {"Laptop", "Mouse", ...} → los valores adentro
         * 
         * VISUALMENTE EN MEMORIA:
         * productos ->  [0]       [1]      [2]       [3]        [4]
         *              "Laptop"  "Mouse"  "Teclado" "Monitor"  "Audífonos"
         * 
         * Cada valor tiene una posición que empieza SIEMPRE desde 0, nunca desde 1.
         * Eso es importante grabárselo.
         */

        // ============================================================
        // 3. ACCEDER A UN VALOR DEL ARRAY
        // ============================================================
        /*
         * Para sacar un valor usas su posición entre corchetes:
         */
        System.out.println("--- Acceso Individual ---");
        System.out.println(productos[0]); // Laptop
        System.out.println(productos[1]); // Mouse
        System.out.println(productos[2]); // Teclado
        System.out.println(productos[3]); // Monitor
        System.out.println(productos[4]); // Audífonos

        // EL ERROR MÁS COMÚN:
        /*
         * System.out.println(productos[5]); // ❌ ERROR — solo existen del 0 al 4
         * Java te lanzará un error llamado: ArrayIndexOutOfBoundsException.
         * Traducido: "te pasaste del límite del array".
         */

        // ============================================================
        // 4. SABER CUÁNTOS ELEMENTOS TIENE (.length)
        // ============================================================
        /*
         * Todo array tiene una propiedad llamada length que te dice cuántos elementos tiene.
         */
        System.out.println("\n--- Propiedad Length ---");
        System.out.println(productos.length); // 5

        /*
         * IMPORTANTE: Aunque el último índice es 4, el length es 5 porque cuenta 
         * los elementos, no las posiciones. Esa diferencia importa mucho en los bucles.
         */

        // ============================================================
        // 5. RECORRER UN ARRAY CON FOR (CLÁSICO)
        // ============================================================
        /*
         * Aquí es donde todo se conecta con lo que ya aprendiste de bucles.
         */
        System.out.println("\n--- Recorrido con For Clásico ---");
        for (int i = 0; i < productos.length; i++) {
            System.out.println("Posición " + i + " → " + productos[i]);
        }

        /*
         * LO QUE PASA VUELTA POR VUELTA:
         * Vuelta 1: i=0 → productos[0] → "Posición 0 → Laptop"
         * Vuelta 2: i=1 → productos[1] → "Posición 1 → Mouse"
         * Vuelta 3: i=2 → productos[2] → "Posición 2 → Teclado"
         * Vuelta 4: i=3 → productos[3] → "Posición 3 → Monitor"
         * Vuelta 5: i=4 → productos[4] → "Posición 4 → Audífonos"
         * 
         * FÍJATE: usamos i < productos.length y no i <= productos.length.
         * Si usáramos <= en la última vuelta i sería 5 y productos[5] no existe. 
         * Por eso siempre <.
         */

        // ============================================================
        // 6. RECORRER UN ARRAY CON FOR-EACH
        // ============================================================
        /*
         * Cuando no necesitas saber la posición, solo el valor, el for-each es más simple.
         */
        System.out.println("\n--- Recorrido con For-Each ---");
        for (String producto : productos) {
            System.out.println(producto);
        }
        /*
         * Se lee como: "para cada producto en productos, imprímelo".
         * Java maneja las posiciones solo, tú solo te preocupas por el valor.
         */

        // ============================================================
        // 7. ¿CUÁNDO USAR CUAL?
        // ============================================================
        /*
         * Necesitas la posición [0], [1], [2]...  → for clásico
         * Solo necesitas el valor                  → for-each
         */

        // ============================================================
        // 8. ARRAYS DE NÚMEROS
        // ============================================================
        /*
         * Funciona exactamente igual pero con números.
         */
        System.out.println("\n--- Array de Números (Precios) ---");
        double[] precios = {15.0, 45.0, 120.0, 8.5, 200.0};

        for (double precio : precios) {
            System.out.println("Precio: " + precio);
        }

        // ============================================================
        // 9. MODIFICAR UN VALOR DEL ARRAY
        // ============================================================
        /*
         * Puedes cambiar el valor de cualquier posición después de crear el array:
         */
        System.out.println("\n--- Modificar valor ---");
        String[] productosMod = {"Laptop", "Mouse", "Teclado"};
        System.out.println("Antes: " + productosMod[1]); // Mouse

        productosMod[1] = "Audífonos"; // reemplazas el valor en posición 1

        System.out.println("Después: " + productosMod[1]); // Audífonos

        // ============================================================
        // 10. ARRAY VACÍO — CUANDO NO SABES LOS VALORES AÚN
        // ============================================================
        /*
         * A veces necesitas crear el array primero y llenarlo después:
         */
        System.out.println("\n--- Array Vacío ---");
        String[] productosVacios = new String[3]; // 3 espacios vacíos, todos null

        productosVacios[0] = "Laptop";
        productosVacios[1] = "Mouse";
        productosVacios[2] = "Teclado";

        /*
         * VISUALMENTE:
         * Al crear:    [null]    [null]    [null]
         * Después:    "Laptop"  "Mouse"  "Teclado"
         */

        // ============================================================
        // 11. BUSCAR UN VALOR DENTRO DEL ARRAY
        // ============================================================
        /*
         * Java no tiene un método mágico para buscar en arrays. 
         * Lo haces con un for y un if:
         */
        System.out.println("\n--- Buscar valor ---");
        String[] productosBusqueda = {"Laptop", "Mouse", "Teclado", "Monitor"};
        String buscar = "Teclado";
        boolean encontrado = false;

        for (String producto : productosBusqueda) {
            if (producto.equals(buscar)) {
                encontrado = true;
                break; // ya encontró, no necesita seguir buscando
            }
        }

        if (encontrado) {
            System.out.println(buscar + " está en el array");
        } else {
            System.out.println(buscar + " no está en el array");
        }

        // ============================================================
        // 12. LIMITACIÓN IMPORTANTE DE LOS ARRAYS
        // ============================================================
        /*
         * Los arrays tienen un problema grande: el tamaño es fijo. 
         * Una vez creado no puedes agregar ni quitar elementos:
         * 
         * String[] productos = {"Laptop", "Mouse", "Teclado"}; // tamaño 3, para siempre
         * 
         * // ❌ No puedes hacer esto
         * productos.add("Monitor"); // no existe este método en arrays
         * 
         * Por eso en Spring Boot casi nunca usarás arrays directamente. 
         * Usarás List que es como un array pero flexible, puedes agregar y quitar 
         * elementos cuando quieras. Eso lo veremos en Fase 3 y vas a notar la 
         * diferencia inmediatamente.
         * 
         * Los arrays son importantes aprenderlos porque son la base para entender 
         * las colecciones, pero en proyectos reales los verás poco.
         */

        /*
         * --- RESUMEN COMPLETO DE ARRAYS ---
         * String[] productos = {"Laptop", "Mouse"}  → crear con valores
         * String[] productos = new String[3]        → crear vacío
         * productos[0]                              → acceder por posición
         * productos[1] = "Audífonos"                → modificar valor
         * productos.length                          → cuántos elementos tiene
         * for clásico                               → cuando necesitas la posición
         * for-each                                  → cuando solo necesitas el valor
         */
    }
}
