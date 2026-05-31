package colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Coleccione {
    public static void main(String[] args) {
        // -------------------------------------------------------------------------
        // DEMO EJECUTABLE
        // -------------------------------------------------------------------------
        System.out.println("Tema: Colecciones (List, Set, Map)");

        List<String> productos = new ArrayList<>();
        productos.add("Laptop");
        productos.add("Mouse");
        productos.add("Laptop");
        System.out.println("List mantiene orden y permite duplicados: " + productos);

        Set<String> categorias = new HashSet<>();
        categorias.add("electronica");
        categorias.add("hogar");
        categorias.add("electronica");
        System.out.println("Set evita duplicados: " + categorias);

        Map<String, Double> precios = new HashMap<>();
        precios.put("Laptop", 1500.0);
        precios.put("Mouse", 25.0);
        System.out.println("Map busca por clave: Mouse cuesta " + precios.get("Mouse"));


        // -------------------------------------------------------------------------
        // APUNTE ORIGINAL CONSERVADO
        // -------------------------------------------------------------------------
        /*
         * Todo lo que escribiste queda guardado aquí como comentario de estudio.
         * La idea es que el archivo compile y se pueda ejecutar, sin borrar tus notas.
         * -----------------------------------------------------------------------------
         *         Colecciones (List, Set, Map)
         *         Parte 1 — El problema antes de la solución
         *         En la Fase 1 aprendiste arrays. Y recordarás que tenían un problema enorme: el tamaño es fijo. Una vez creado el array no puedes agregar ni quitar elementos.
         *                 Mira el problema con un ejemplo. Imagina que estás recibiendo productos en tu API. No sabes cuántos llegarán. Pueden ser 5, pueden ser 500. Con arrays no puedes manejar esto:
         *         javaString[] productos = new String[?]; // ¿qué número pongo aquí?
         *         Y aunque adivines un número, si llega un producto más del límite ya no puedes agregarlo. Si llegan menos, te sobran espacios vacíos.
         *                 Hay otros problemas con arrays:
         * 
         *         No tienen métodos útiles. Si quieres buscar un elemento debes recorrer manualmente.
         *         No tienen forma fácil de remover elementos.
         *                 No te dicen si tienen duplicados.
         *                 Para guardar pares de clave-valor (tipo email → usuario) son inservibles.
         * 
         *         Para resolver esto Java tiene un sistema completo llamado Java Collections Framework. Son estructuras de datos flexibles, con métodos útiles y diferentes tipos según lo que necesites.
         * 
         *                 Parte 2 — Qué es una colección
         *         Una colección es un objeto que contiene varios elementos y te permite manipularlos fácilmente. Es como un array pero con superpoderes.
         *                 Java tiene tres tipos principales de colecciones que cubren el 90% de los casos:
         *         List   →  lista ordenada, permite duplicados, acceso por posición
         *         Set    →  conjunto sin duplicados, sin orden garantizado
         *         Map    →  pares clave-valor, búsqueda por clave
         *         Vamos a ver cada uno en detalle.
         * 
         *                 Parte 3 — List, la colección que más usarás
         *         Una List es como un array pero flexible. Crece y decrece automáticamente. Mantiene el orden de inserción y permite elementos duplicados.
         *                 La implementación más común es ArrayList:
         *         javaimport java.util.ArrayList;
         * import java.util.List;
         * 
         *         public class Main {
         *             public static void main(String[] args) {
         * 
         *                 // Crear una lista vacía
         *                 List<String> productos = new ArrayList<>();
         * 
         *                 // Agregar elementos
         *                 productos.add("Laptop");
         *                 productos.add("Mouse");
         *                 productos.add("Teclado");
         *                 productos.add("Monitor");
         *                 productos.add("Laptop"); // permite duplicados
         * 
         *                 // Imprimir la lista completa
         *                 System.out.println(productos);
         *                 // [Laptop, Mouse, Teclado, Monitor, Laptop]
         *             }
         *         }
         *         Vamos a desmenuzar cosas nuevas que aparecieron.
         *                 Los imports
         *         javaimport java.util.ArrayList;
         * import java.util.List;
         *         Java tiene millones de clases. Para usar List y ArrayList debes importarlas desde el paquete java.util. IntelliJ los agrega automáticamente cuando usas la clase, no te preocupes mucho por esto.
         *                 La sintaxis <String>
         *         javaList<String> productos = new ArrayList<>();
         *         Eso <String> se llama generics (lo veremos en profundidad en el Tema 2 de esta fase). Por ahora quédate con que le dice a Java qué tipo de elementos guardará la lista. En este caso solo Strings.
         *                 Si intentas meter algo que no sea String, Java no compila:
         *         javaList<String> productos = new ArrayList<>();
         *         productos.add("Laptop"); // ✅
         *         productos.add(123);      // ❌ ERROR - no es String
         *         Esto es genial porque previene errores. Antes de generics todas las listas guardaban Object y podías meter cualquier cosa, lo que causaba muchos bugs.
         *         El <> vacío en ArrayList
         *         javanew ArrayList<>()
         *         Esto se llama operador diamante. Java es inteligente y deduce el tipo a partir de la variable. Lo correcto sería:
         *         javanew ArrayList<String>()
         *         Pero como productos ya está declarada como List<String>, Java sabe el tipo. Por eso puedes dejar <> vacío. Es solo para escribir menos.
         *                 Por qué List y no ArrayList en la variable
         *         Esto es una buena práctica importante:
         *         java// ✅ Recomendado
         *         List<String> productos = new ArrayList<>();
         * 
         * // ❌ Menos flexible
         *         ArrayList<String> productos = new ArrayList<>();
         *         List es la interfaz (recuerda Fase 2 tema 4). ArrayList es una implementación concreta de esa interfaz. Hay otras implementaciones como LinkedList.
         *                 Al declarar la variable como List, mañana puedes cambiar la implementación a otra sin romper el código que usa esa variable. Eso es polimorfismo en acción (recuerda Fase 2 tema 6).
         * 
         *                 Parte 4 — Métodos esenciales de List
         *         Estos son los métodos que vas a usar todos los días en Spring Boot:
         *         javaList<String> productos = new ArrayList<>();
         * 
         * // Agregar al final
         *         productos.add("Laptop");
         *         productos.add("Mouse");
         * 
         * // Agregar en una posición específica
         *         productos.add(1, "Teclado"); // inserta en posición 1, desplaza el resto
         * 
         * // Obtener un elemento por posición
         *         String primero = productos.get(0); // "Laptop"
         * 
         * // Saber cuántos elementos tiene
         *         int total = productos.size();
         * 
         * // Verificar si está vacía
         *         boolean vacia = productos.isEmpty();
         * 
         * // Verificar si contiene un elemento
         *         boolean tieneTeclado = productos.contains("Teclado");
         * 
         * // Buscar la posición de un elemento
         *         int posicion = productos.indexOf("Mouse"); // -1 si no existe
         * 
         * // Reemplazar un elemento en una posición
         *         productos.set(0, "Laptop HP"); // cambia "Laptop" por "Laptop HP"
         * 
         * // Eliminar por posición
         *         productos.remove(0); // elimina el de la posición 0
         * 
         * // Eliminar por valor
         *         productos.remove("Mouse"); // elimina la primera coincidencia
         * 
         * // Vaciar la lista completa
         *         productos.clear();
         *         Diferencia importante con arrays
         *         Arrays                          ArrayList
         * ─────────                       ──────────
         *         productos[0]            →       productos.get(0)
         *         productos[0] = "X"      →       productos.set(0, "X")
         *         productos.length        →       productos.size()
         *         Mucha gente se confunde al principio porque mezcla la sintaxis. En ArrayList no se usan corchetes. Todo se hace con métodos.
         * 
         *                 Parte 5 — Recorrer una List
         *         Como aprendiste en la Fase 1, los bucles funcionan igual:
         *         javaList<String> productos = new ArrayList<>();
         *         productos.add("Laptop");
         *         productos.add("Mouse");
         *         productos.add("Teclado");
         * 
         * // for-each - el más común
         *         for (String producto : productos) {
         *             System.out.println(producto);
         *         }
         * 
         * // for clásico con índice
         *         for (int i = 0; i < productos.size(); i++) {
         *             System.out.println("Posición " + i + ": " + productos.get(i));
         *         }
         * 
         * // forEach con lambda - moderno y elegante
         *         productos.forEach(producto -> System.out.println(producto));
         *         La tercera forma usa una lambda (que viste en el tema anterior). Es la forma más moderna y la que más verás en código moderno de Spring Boot.
         * 
         *         Parte 6 — Crear una List con valores iniciales
         *         A veces ya tienes los valores y quieres crear la lista de una sola vez. Hay varias formas:
         *         java// Forma 1: List.of - inmutable (no puedes agregar ni quitar)
         *         List<String> productos = List.of("Laptop", "Mouse", "Teclado");
         * 
         *         productos.add("Monitor"); // ❌ ERROR - es inmutable
         *         java// Forma 2: con new ArrayList y List.of dentro - mutable
         *         List<String> productos = new ArrayList<>(List.of("Laptop", "Mouse", "Teclado"));
         * 
         *         productos.add("Monitor"); // ✅ funciona
         *         Cuándo usar cada una:
         *         List.of(...)                    →  cuando la lista NO debe cambiar nunca
         *         new ArrayList<>(List.of(...))   →  cuando vas a modificar la lista después
         *         En Spring Boot verás muchísimo List.of para configuraciones que no cambian, y ArrayList para datos que se modifican.
         * 
         *         Parte 7 — List con objetos de tus clases
         *         Lo más común no es guardar Strings sino objetos de las clases que creaste:
         *         javapublic class Producto {
         *             private String nombre;
         *             private double precio;
         * 
         *             public Producto(String nombre, double precio) {
         *                 this.nombre = nombre;
         *                 this.precio = precio;
         *             }
         * 
         *             public String getNombre() { return this.nombre; }
         *             public double getPrecio() { return this.precio; }
         *         }
         *         javaList<Producto> productos = new ArrayList<>();
         * 
         *         productos.add(new Producto("Laptop", 1500.0));
         *         productos.add(new Producto("Mouse", 25.0));
         *         productos.add(new Producto("Teclado", 80.0));
         * 
         * // Recorrer y usar los métodos del objeto
         *         for (Producto p : productos) {
         *             System.out.println(p.getNombre() + " cuesta $" + p.getPrecio());
         *         }
         *         Esto es exactamente lo que harás en Spring Boot. Tendrás listas de Productos, Usuarios, Pedidos, y los manipularás así.
         * 
         *                 Parte 8 — Set, la colección sin duplicados
         *         Un Set es una colección que no permite elementos duplicados. Si intentas agregar algo que ya existe, simplemente no lo agrega y no avisa.
         *         Casos de uso típicos:
         * 
         *         Lista de emails únicos
         *         IDs de usuarios sin repetir
         *         Categorías de productos sin duplicar
         *         Cualquier conjunto donde la unicidad importe
         * 
         *         La implementación más común es HashSet:
         *         javaimport java.util.HashSet;
         * import java.util.Set;
         * 
         *         public class Main {
         *             public static void main(String[] args) {
         * 
         *                 Set<String> emails = new HashSet<>();
         * 
         *                 emails.add("carlos@gmail.com");
         *                 emails.add("ana@hotmail.com");
         *                 emails.add("pedro@yahoo.com");
         *                 emails.add("carlos@gmail.com"); // duplicado, se ignora
         * 
         *                 System.out.println(emails.size()); // 3, no 4
         * 
         *                 System.out.println(emails);
         *                 // [carlos@gmail.com, ana@hotmail.com, pedro@yahoo.com]
         *                 // OJO: el orden puede variar, no está garantizado
         *             }
         *         }
         *         Diferencias importantes con List
         *         List               Set
         *                             ────               ────
         *         Permite duplicados          ✅                 ❌
         *         Orden garantizado           ✅ (inserción)     ❌
         *         Acceso por índice           ✅ get(i)          ❌
         *         Verificar si existe         contains()         contains()
         *         (lento, recorre)   (rápido, hash)
         *         El Set es mucho más rápido que List para verificar si un elemento existe. List tiene que recorrer todos los elementos; Set usa una técnica llamada hashing que va directo.
         *                 Métodos de Set
         *         Son casi los mismos que List, pero sin los que dependen de posición:
         *         javaSet<String> emails = new HashSet<>();
         * 
         *         emails.add("carlos@gmail.com");        // agregar
         *         emails.contains("carlos@gmail.com");   // verificar si existe
         *         emails.remove("carlos@gmail.com");     // eliminar
         *         emails.size();                          // cuántos elementos
         *         emails.isEmpty();                       // verificar si está vacío
         *         emails.clear();                         // vaciar todo
         * 
         * // NO existe:
         * // emails.get(0)        ❌ no hay índice
         * // emails.set(0, ...)   ❌ no hay posiciones
         * // emails.indexOf(...)  ❌ no hay índices
         *         Cuándo usar List vs Set
         *         Necesitas duplicados                       →  List
         *         Necesitas orden por posición               →  List
         *         Necesitas acceder por índice (get(0))      →  List
         *         Quieres garantizar unicidad                →  Set
         *         Solo quieres verificar si algo existe      →  Set (mucho más rápido)
         *         No te importa el orden                     →  Set
         * 
         *         Parte 9 — Tipos de Set
         *         Hay tres implementaciones principales:
         *         java// HashSet - el más usado, sin orden garantizado, el más rápido
         *         Set<String> hash = new HashSet<>();
         * 
         * // LinkedHashSet - mantiene el orden de inserción
         *         Set<String> linked = new LinkedHashSet<>();
         * 
         * // TreeSet - ordena automáticamente
         *         Set<String> tree = new TreeSet<>();
         *         Ejemplo de diferencia:
         *         javaHashSet<String> hash = new HashSet<>();
         *         hash.add("Carlos");
         *         hash.add("Ana");
         *         hash.add("Bruno");
         *         System.out.println(hash); // orden impredecible: [Ana, Carlos, Bruno] o cualquier orden
         * 
         *         LinkedHashSet<String> linked = new LinkedHashSet<>();
         *         linked.add("Carlos");
         *         linked.add("Ana");
         *         linked.add("Bruno");
         *         System.out.println(linked); // [Carlos, Ana, Bruno] - orden de inserción
         * 
         *         TreeSet<String> tree = new TreeSet<>();
         *         tree.add("Carlos");
         *         tree.add("Ana");
         *         tree.add("Bruno");
         *         System.out.println(tree); // [Ana, Bruno, Carlos] - orden alfabético
         *         En la práctica el 95% del tiempo usarás HashSet. Solo necesitas las otras cuando importa el orden.
         * 
         *         Parte 10 — Map, la colección de pares clave-valor
         *         Aquí entra una estructura completamente diferente. Un Map guarda pares clave-valor. Es como un diccionario: buscas por clave y obtienes el valor.
         *                 Ejemplos del mundo real:
         * 
         *         Diccionario palabra → significado
         *         Email → datos del usuario
         *         ID de producto → producto completo
         *         Código de país → nombre del país
         * 
         *         La implementación más común es HashMap:
         *         javaimport java.util.HashMap;
         * import java.util.Map;
         * 
         *         public class Main {
         *             public static void main(String[] args) {
         * 
         *                 Map<String, String> capitales = new HashMap<>();
         * 
         *                 // Agregar pares clave-valor
         *                 capitales.put("Chile", "Santiago");
         *                 capitales.put("Argentina", "Buenos Aires");
         *                 capitales.put("Perú", "Lima");
         *                 capitales.put("Colombia", "Bogotá");
         * 
         *                 // Obtener un valor por su clave
         *                 String capitalChile = capitales.get("Chile");
         *                 System.out.println(capitalChile); // Santiago
         * 
         *                 // Si la clave no existe retorna null
         *                 String capitalUSA = capitales.get("USA");
         *                 System.out.println(capitalUSA); // null
         *             }
         *         }
         *         La sintaxis con dos tipos
         *         javaMap<String, String> capitales = new HashMap<>();
         * //   ↑       ↑
         * //   clave   valor
         *         Map siempre necesita dos tipos: el tipo de la clave y el tipo del valor. Pueden ser distintos:
         *         javaMap<String, Producto> catalogo = new HashMap<>();
         * // clave: String (id del producto)
         * // valor: Producto (objeto completo)
         * 
         *         Map<Integer, String> traduccion = new HashMap<>();
         * // clave: Integer (número)
         * // valor: String (traducción)
         *         Métodos esenciales de Map
         *         javaMap<String, String> capitales = new HashMap<>();
         * 
         * // Agregar o actualizar un par
         *         capitales.put("Chile", "Santiago");
         * 
         * // Obtener un valor
         *         String valor = capitales.get("Chile");
         * 
         * // Obtener con valor por defecto si la clave no existe
         *         String valorSeguro = capitales.getOrDefault("USA", "Desconocido");
         * 
         * // Verificar si existe una clave
         *         boolean tieneChile = capitales.containsKey("Chile");
         * 
         * // Verificar si existe un valor
         *         boolean tieneSantiago = capitales.containsValue("Santiago");
         * 
         * // Eliminar por clave
         *         capitales.remove("Chile");
         * 
         * // Saber cuántos pares tiene
         *         int total = capitales.size();
         * 
         * // Verificar si está vacío
         *         boolean vacio = capitales.isEmpty();
         * 
         * // Vaciar todo
         *         capitales.clear();
         *         Recorrer un Map
         *         Esto confunde al principio porque tiene varias formas. La más usada y limpia:
         *         javaMap<String, String> capitales = new HashMap<>();
         *         capitales.put("Chile", "Santiago");
         *         capitales.put("Argentina", "Buenos Aires");
         * 
         * // Recorrer pares clave-valor
         *         for (Map.Entry<String, String> par : capitales.entrySet()) {
         *             String pais = par.getKey();
         *             String capital = par.getValue();
         *             System.out.println(pais + " → " + capital);
         *         }
         *         Eso Map.Entry representa un par completo. Tiene getKey() y getValue().
         *                 También puedes recorrer solo claves o solo valores:
         *         java// Solo las claves
         *         for (String pais : capitales.keySet()) {
         *             System.out.println(pais);
         *         }
         * 
         * // Solo los valores
         *         for (String capital : capitales.values()) {
         *             System.out.println(capital);
         *         }
         *         Y la forma moderna con lambda (que verás en Spring Boot):
         *         javacapitales.forEach((pais, capital) ->
         *                 System.out.println(pais + " → " + capital)
         *         );
         *         Las claves de un Map son únicas
         *         Importante: en un Map cada clave es única. Si haces put con una clave que ya existe, reemplaza el valor anterior:
         *         javaMap<String, String> capitales = new HashMap<>();
         * 
         *         capitales.put("Chile", "Santiago");
         *         capitales.put("Chile", "Valparaíso"); // sobrescribe el anterior
         * 
         *         System.out.println(capitales.get("Chile")); // Valparaíso
         * 
         *         Parte 11 — Tipos de Map
         *         Similares a Set:
         *         java// HashMap - el más usado, sin orden
         *         Map<String, String> hash = new HashMap<>();
         * 
         * // LinkedHashMap - mantiene orden de inserción
         *         Map<String, String> linked = new LinkedHashMap<>();
         * 
         * // TreeMap - ordena por clave automáticamente
         *         Map<String, String> tree = new TreeMap<>();
         *         En el 95% de los casos usarás HashMap. Es el más rápido y rara vez necesitas el orden.
         * 
         *         Parte 12 — La jerarquía completa de colecciones
         *         Te muestro la estructura para que veas cómo se relacionan:
         *         Collection (interfaz)
         *     ├── List (interfaz)
         *     │       ├── ArrayList     ← el más usado
         *     │       ├── LinkedList
         *     │       └── Vector
         *     │
         *     └── Set (interfaz)
         *             ├── HashSet       ← el más usado
         *             ├── LinkedHashSet
         *             └── TreeSet
         * 
         *         Map (interfaz, NO está en Collection)
         *     ├── HashMap               ← el más usado
         *     ├── LinkedHashMap
         *     └── TreeMap
         *         Map está separado porque no es una "colección de elementos" sino una "colección de pares".
         * 
         *                 Parte 13 — Resumen comparativo final
         *         Tabla clave para que memorices cuándo usar cada uno:
         *         List              Set                Map
         *                     ─────────         ─────────          ─────────
         *         Estructura          [a, b, c, b]      {a, b, c}          {k1→v1, k2→v2}
         *         Duplicados          ✅                 ❌                  ❌ en claves
         *         Orden               ✅ inserción       ❌ (HashSet)        ❌ en claves
         *         Acceso              get(índice)       contains()          get(clave)
         *         Velocidad de        Lento O(n)         Rápido O(1)         Rápido O(1)
         *         búsqueda
         *         Caso típico         Lista de items    Conjunto de IDs    Diccionario
         * 
         *         Parte 14 — Conexión con Spring Boot
         *         Las colecciones son omnipresentes en Spring Boot. Algunos ejemplos:
         *         List<Producto>      →  resultado de una consulta a la base de datos
         *         respuesta JSON con varios productos
         * 
         *         Map<String, Object> →  representación genérica de JSON
         *         parámetros dinámicos de configuración
         * 
         *         Set<String>         →  permisos únicos de un usuario
         *         roles asignados
         *         Cada endpoint REST de Spring Boot que devuelve "una lista de algo" retorna un List. Cada vez que recibes un JSON con un arreglo, llega como List. No hay escape de las colecciones en Spring Boot.
         * 
         *                 Resumen completo del Tema 1
         *         COLECCIONES         →  estructuras de datos flexibles, mejor que arrays
         * 
         *         Las tres principales:
         * 
         *         LIST                →  lista ordenada con duplicados
         *         Implementación:     ArrayList (la más usada)
         *         Métodos clave:      add, get, set, remove, size, contains
         *         Cuándo usar:        siempre que necesites orden o duplicados
         * 
         *         SET                 →  conjunto sin duplicados
         *         Implementación:     HashSet (la más usada)
         *         Métodos clave:      add, contains, remove, size
         *         Cuándo usar:        cuando la unicidad importa
         * 
         *         MAP                 →  pares clave-valor
         *         Implementación:     HashMap (la más usada)
         *         Métodos clave:      put, get, containsKey, remove, size, entrySet
         *         Cuándo usar:        cuando buscas por una clave
         * 
         *         Sintaxis con generics:
         *         List<String>             →  lista de Strings
         *         Set<Integer>             →  conjunto de números
         *         Map<String, Producto>    →  diccionario String → Producto
         * 
         *         Buena práctica:
         *         Variable: interfaz  →  List<X>, Set<X>, Map<K,V>
         *         New:      implementación →  new ArrayList<>(), new HashSet<>(), new HashMap<>()
         * 
         *         Creación rápida:
         *         List.of(...)             →  inmutable
         *         Set.of(...)              →  inmutable
         *         Map.of(k1,v1, k2,v2)     →  inmutable
         * 
         *         Recorrer:
         *         for-each                 →  for (X x : coleccion)
         *             forEach con lambda       →  coleccion.forEach(x -> ...)
         *         Map necesita entrySet    →  for (Map.Entry<K,V> e : map.entrySet())
         * 
         *             Lista de lo que cubrimos para que lo tengas claro antes del siguiente tema:
         * ✅ List, ArrayList y sus métodos
         * ✅ Set, HashSet y la unicidad
         * ✅ Map, HashMap y los pares clave-valor
         * ✅ Generics básicos <String>
         * ✅ Crear listas inmutables con List.of
         * ✅ Recorrer cada tipo
         * ✅ Diferencias y cuándo usar cada uno
         */
    }
}
