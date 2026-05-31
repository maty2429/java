package colecciones;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambdas {
    public static void main(String[] args) {
        // -------------------------------------------------------------------------
        // DEMO EJECUTABLE
        // -------------------------------------------------------------------------
        System.out.println("Tema: Lambdas");

        List<Producto> productos = List.of(
                new Producto("Laptop", 1500.0, 8, true),
                new Producto("Mouse", 25.0, 2, true),
                new Producto("Teclado", 80.0, 0, false)
        );

        Predicate<Producto> esCaro = producto -> producto.precio() > 100;
        Function<Producto, String> obtenerNombre = Producto::nombre;
        Consumer<Producto> imprimir = producto -> System.out.println("Producto caro: " + obtenerNombre.apply(producto));

        productos.stream()
                .filter(esCaro)
                .forEach(imprimir);


        // -------------------------------------------------------------------------
        // APUNTE ORIGINAL CONSERVADO
        // -------------------------------------------------------------------------
        /*
         * Todo lo que escribiste queda guardado aquí como comentario de estudio.
         * La idea es que el archivo compile y se pueda ejecutar, sin borrar tus notas.
         * -----------------------------------------------------------------------------
         *         Lambdas — Sintaxis y Contexto Funcional
         *         Parte 1 — El problema antes de la solución (aquí es donde verás la utilidad)
         *         Olvidémonos por un momento de la palabra "lambda". Vamos a un problema real de programación que te va a frustrar a propósito, y luego veremos cómo las lambdas lo resuelven elegantemente.
         *         Imagina que tienes una lista de productos y necesitas filtrarla de diferentes formas:
         *         Filtrar productos con precio mayor a 100
         *         Filtrar productos con stock menor a 5
         *         Filtrar productos cuyo nombre empiece con "L"
         *         Filtrar productos activos
         *         Sin lambdas tendrías que escribir un método distinto para cada filtro:
         *         javapublic List<Producto> filtrarPorPrecioMayorA(List<Producto> productos, double precio) {
         *             List<Producto> resultado = new ArrayList<>();
         *             for (Producto p : productos) {
         *                 if (p.getPrecio() > precio) {
         *                     resultado.add(p);
         *                 }
         *             }
         *             return resultado;
         *         }
         * 
         *         public List<Producto> filtrarPorStockMenorA(List<Producto> productos, int stock) {
         *             List<Producto> resultado = new ArrayList<>();
         *             for (Producto p : productos) {
         *                 if (p.getStock() < stock) {
         *                     resultado.add(p);
         *                 }
         *             }
         *             return resultado;
         *         }
         * 
         *         public List<Producto> filtrarPorNombreEmpiezaCon(List<Producto> productos, String inicio) {
         *             List<Producto> resultado = new ArrayList<>();
         *             for (Producto p : productos) {
         *                 if (p.getNombre().startsWith(inicio)) {
         *                     resultado.add(p);
         *                 }
         *             }
         *             return resultado;
         *         }
         * 
         *         public List<Producto> filtrarPorActivo(List<Producto> productos) {
         *             List<Producto> resultado = new ArrayList<>();
         *             for (Producto p : productos) {
         *                 if (p.isActivo()) {
         *                     resultado.add(p);
         *                 }
         *             }
         *             return resultado;
         *         }
         *         Mira lo horrible. Todos hacen exactamente lo mismo excepto por una línea: la condición del if. Recorrer, comparar, agregar al resultado, retornar. La única diferencia entre los métodos es la regla que se aplica.
         *         Esto es duplicación masiva. Y peor, cada filtro nuevo significa un método nuevo. Si mañana quieres "productos con precio entre 50 y 200", necesitas otro método. Y otro, y otro.
         * ¿No sería ideal poder hacer esto?
         *         javafiltrar(productos, REGLA);  // donde REGLA es una condición que tú defines
         *         Donde "REGLA" no sea un método separado sino algo que escribes en el momento, dentro de la llamada. Como un parámetro que es código.
         *         Eso es exactamente lo que hacen las lambdas. Te dejan pasar código como si fuera un valor.
         * 
         *                 Parte 2 — Qué es una lambda
         *         Una lambda es una función sin nombre que puedes pasar como argumento. Es código que se trata como un valor.
         *         Una analogía cotidiana. Imagina que vas a una pizzería y le dices al cocinero: "haz una pizza". Esa es una orden genérica. El cocinero necesita más información: ¿qué ingredientes? Hay dos formas:
         *         Forma 1 — sin lambdas: llamas a cocineros especializados.
         *         cocineroPepperoni.hacerPizza()
         *         cocineroHawaiana.hacerPizza()
         *         cocineroVegetariana.hacerPizza()
         *         Necesitas un cocinero por tipo. Mucha gente, mucho desorden.
         *                 Forma 2 — con lambdas: tienes un solo cocinero y le pasas la receta en el momento.
         *         cocinero.hacerPizza(RECETA)
         *         Donde RECETA puede ser "pon pepperoni y queso", "pon piña y jamón", "pon verduras". El cocinero ejecuta la receta que le digas. Una sola persona, infinitas combinaciones.
         *                 En programación, las lambdas son esas "recetas" que pasas en el momento.
         * 
         *                 Parte 3 — La sintaxis básica de lambdas
         *         Ya viste algo de esto en el tema de clases anónimas, pero ahora lo vamos a entender de verdad. La sintaxis de una lambda es:
         *         (parámetros) -> cuerpo
         *         Mira la flecha ->. Esa flecha es la marca registrada de las lambdas. Significa "convierte estos parámetros en este resultado". Se lee como "va a" o "produce".
         *                 Algunos ejemplos sueltos para que veas el patrón:
         *         java// Sin parámetros, imprime algo
         *                 () -> System.out.println("Hola")
         * 
         * // Un parámetro, lo imprime
         *         nombre -> System.out.println("Hola " + nombre)
         * 
         * // Dos parámetros, los suma
         *         (a, b) -> a + b
         * 
         * // Un parámetro, retorna si es mayor a 100
         *         precio -> precio > 100
         * 
         * // Un parámetro Producto, retorna si su precio es mayor a 100
         *         producto -> producto.getPrecio() > 100
         *         Por ahora no las uses todavía. Solo léelas para que la sintaxis empiece a parecerte familiar.
         * 
         *         Parte 4 — Aplicando lambdas al problema inicial
         *         Volvamos al problema de filtrar productos. Vamos a resolverlo con lambdas. Primero necesitamos una pieza clave: la interfaz funcional.
         *                 Una interfaz funcional es una interfaz con exactamente un método abstracto. Java tiene muchas predefinidas pero también puedes crear las tuyas. Para nuestro filtro vamos a usar una que ya viene en Java: Predicate.
         *                 Predicate<T> es una interfaz que tiene un solo método:
         *         javaboolean test(T elemento);
         *         Recibe un elemento de tipo T y retorna true o false. Eso es literalmente una regla. Es lo que necesitamos.
         *         Ahora reescribamos el método de filtrar usando Predicate:
         *         javaimport java.util.function.Predicate;
         * 
         *         public List<Producto> filtrar(List<Producto> productos, Predicate<Producto> regla) {
         *             List<Producto> resultado = new ArrayList<>();
         *             for (Producto p : productos) {
         *                 if (regla.test(p)) {  // aplica la regla que te pasaron
         *                     resultado.add(p);
         *                 }
         *             }
         *             return resultado;
         *         }
         *         Un solo método para todos los filtros. La diferencia: en vez de tener la condición fija dentro, le pasamos la condición como parámetro mediante un Predicate<Producto>.
         *         Y ahora viene la magia. ¿Cómo creas un Predicate<Producto>? Con una lambda:
         *         javaList<Producto> productos = ... // tu lista
         * 
         * // Filtrar por precio mayor a 100
         *         List<Producto> caros = filtrar(productos, p -> p.getPrecio() > 100);
         * 
         * // Filtrar por stock menor a 5
         *         List<Producto> bajoStock = filtrar(productos, p -> p.getStock() < 5);
         * 
         * // Filtrar por nombre empieza con "L"
         *         List<Producto> conL = filtrar(productos, p -> p.getNombre().startsWith("L"));
         * 
         * // Filtrar por activos
         *         List<Producto> activos = filtrar(productos, p -> p.isActivo());
         * ¿Ves lo que hicimos? Cuatro filtros completamente distintos, una sola línea cada uno, sin métodos repetidos. La lambda p -> p.getPrecio() > 100 es la regla. Java la convierte en un Predicate<Producto> automáticamente.
         *                 Compara con el código original. Antes tenías 4 métodos de 8 líneas cada uno = 32 líneas. Ahora tienes 1 método y 4 líneas de uso = 5 líneas para lo mismo. Eso es la utilidad de las lambdas.
         * 
         *                 Parte 5 — Entendiendo qué pasó por dentro
         *         Para que el click sea completo, te explico lo que Java está haciendo internamente.
         *                 Cuando escribes:
         *         javafiltrar(productos, p -> p.getPrecio() > 100);
         *         Java mira que el segundo parámetro debe ser Predicate<Producto>. Una interfaz con el método boolean test(Producto p). Entonces Java toma tu lambda p -> p.getPrecio() > 100 y automáticamente crea un objeto que implementa esa interfaz:
         *         java// Lo que Java hace internamente sin que tú lo veas
         *         new Predicate<Producto>() {
         *             @Override
         *             public boolean test(Producto p) {
         *                 return p.getPrecio() > 100;
         *             }
         *         }
         * ¿Recuerdas las clases anónimas del último tema de la Fase 2? Las lambdas son básicamente una forma corta de escribir clases anónimas que implementan interfaces con un solo método.
         *                 Forma larga con clase anónima:
         *         javaPredicate<Producto> regla = new Predicate<Producto>() {
         *             @Override
         *             public boolean test(Producto p) {
         *                 return p.getPrecio() > 100;
         *             }
         *         };
         *         Forma corta con lambda:
         *         javaPredicate<Producto> regla = p -> p.getPrecio() > 100;
         *         El resultado es idéntico. Solo cambia la forma de escribirlo. La lambda es azúcar sintáctica.
         * 
         *                 Parte 6 — La sintaxis de lambdas en detalle
         *         Ahora que viste para qué sirven, vamos a profundizar en cómo se escriben. Las lambdas tienen variaciones según el número de parámetros y el cuerpo.
         *         Sin parámetros
         *         java() -> System.out.println("Hola")
         *         Paréntesis vacíos. Se usa cuando la lambda no necesita datos de entrada.
         *         Un parámetro
         *         javanombre -> System.out.println("Hola " + nombre)
         *         Cuando hay solo un parámetro, los paréntesis son opcionales. Por elegancia se omiten. También puedes escribir así si prefieres:
         *         java(nombre) -> System.out.println("Hola " + nombre)
         *         Las dos formas son válidas. La sin paréntesis es la más usada.
         *                 Varios parámetros
         *         java(a, b) -> a + b
         *         Cuando hay más de un parámetro, los paréntesis son obligatorios.
         *                 Tipos opcionales en los parámetros
         *         Java deduce los tipos automáticamente, pero puedes escribirlos si quieres:
         *         java(int a, int b) -> a + b      // con tipos explícitos
         *                 (a, b) -> a + b              // tipos deducidos
         *         En código moderno se omiten los tipos siempre que se pueda. Es más limpio.
         *                 Cuerpo de una sola expresión
         *         Si el cuerpo es una sola expresión, no necesitas llaves ni return:
         *         java(a, b) -> a + b
         *         El resultado se retorna automáticamente.
         *                 Cuerpo de varias líneas
         *         Si necesitas varias líneas, usa llaves y return explícito:
         *         java(a, b) -> {
         *             int suma = a + b;
         *             int resultado = suma * 2;
         *             return resultado;
         *         }
         *         Cuando hay llaves, el return es obligatorio si la lambda debe retornar algo.
         * 
         *                 Parte 7 — Interfaces funcionales — el otro lado de la moneda
         *         Una lambda sola no significa nada. Necesita una interfaz funcional asociada. Para que Java sepa qué método estás implementando con la lambda, debe poder asociarla con una interfaz que tenga un solo método abstracto.
         *                 Java tiene varias interfaces funcionales predefinidas en java.util.function. Las cuatro más importantes:
         *         Predicate — para condiciones
         *         Recibe algo, retorna booleano.
         *                 javaPredicate<Producto> esCaro = p -> p.getPrecio() > 100;
         *         boolean resultado = esCaro.test(producto);
         *         Uso típico: filtros, condiciones, validaciones.
         *                 Function — para transformar
         *         Recibe algo, retorna otra cosa diferente.
         *                 javaFunction<Producto, String> obtenerNombre = p -> p.getNombre();
         *         String nombre = obtenerNombre.apply(producto);
         *         Uso típico: transformar un objeto en otro, extraer un campo.
         *         Consumer — para hacer algo sin retornar
         *         Recibe algo, no retorna nada. Solo "consume" el valor.
         *         javaConsumer<Producto> imprimir = p -> System.out.println(p.getNombre());
         *         imprimir.accept(producto);
         *         Uso típico: imprimir, guardar, enviar notificación.
         *                 Supplier — para generar
         *         No recibe nada, retorna algo. "Provee" valores.
         *                 javaSupplier<Producto> generarProductoDefault = () -> new Producto("Genérico", 0);
         *         Producto p = generarProductoDefault.get();
         *         Uso típico: factory methods, valores por defecto.
         *         Tabla resumen para memorizar
         *         Recibe     Retorna    Método principal
         *                     ────────   ────────   ─────────────────
         *         Predicate<T>        T          boolean    test()
         *         Function<T, R>      T          R          apply()
         *         Consumer<T>         T          (nada)     accept()
         *         Supplier<T>         (nada)     T          get()
         *         Esas cuatro cubren el 80% de los casos. Hay otras como BiFunction, UnaryOperator, etc., pero estas son las esenciales.
         * 
         *                 Parte 8 — Lambdas en métodos cotidianos de Java
         *         Ahora vas a ver dónde aparecen las lambdas naturalmente. Java agregó muchos métodos a las colecciones que reciben lambdas como parámetro. Los más útiles:
         *         forEach en colecciones
         *         javaList<String> nombres = List.of("Carlos", "Ana", "Bruno");
         * 
         * // Sin lambda - forma vieja
         *         for (String n : nombres) {
         *             System.out.println(n);
         *         }
         * 
         * // Con lambda - forma moderna
         *         nombres.forEach(n -> System.out.println(n));
         *         forEach recibe un Consumer. La lambda n -> System.out.println(n) es ese Consumer.
         *                 removeIf en colecciones
         *         javaList<Producto> productos = new ArrayList<>(...);
         * 
         * // Eliminar todos los productos con precio menor a 50
         *         productos.removeIf(p -> p.getPrecio() < 50);
         *         removeIf recibe un Predicate. Elimina los elementos que cumplan la condición.
         *                 sort con Comparator
         *         javaList<Producto> productos = new ArrayList<>(...);
         * 
         * // Ordenar por precio ascendente
         *         productos.sort((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()));
         * 
         * // Ordenar por nombre alfabéticamente
         *         productos.sort((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()));
         *         sort recibe un Comparator. La lambda define el criterio de ordenamiento.
         *                 Vas a ver estos métodos constantemente en Spring Boot. Sin lambdas serían inutilizables.
         * 
         *         Parte 9 — Method References — la versión aún más corta
         *         Hay un atajo para lambdas que solo llaman a un método existente. Se llama method reference. Usa ::.
         *         Mira esta lambda:
         *         javanombres.forEach(n -> System.out.println(n));
         *         La lambda solo hace System.out.println(n) con el parámetro. Java ofrece una sintaxis más corta:
         *         javanombres.forEach(System.out::println);
         *         Eso System.out::println se lee como "la referencia al método println de System.out". Java entiende que debe llamar a ese método con cada elemento.
         *         Otro ejemplo:
         *         java// Lambda
         *         productos.stream().map(p -> p.getNombre());
         * 
         * // Method reference equivalente
         *         productos.stream().map(Producto::getNombre);
         *         Producto::getNombre se lee como "el método getNombre de la clase Producto".
         *                 Cuándo usar method references:
         * 
         *         Cuando tu lambda solo llama a un método existente con el mismo parámetro.
         *                 Si la lambda hace algo más complejo, déjala como lambda.
         * 
         *         No te obsesiones con method references al principio. Cuando vayas leyendo código de Spring Boot las irás reconociendo naturalmente.
         * 
         *                 Parte 10 — Variables capturadas por la lambda
         *         Algo importante que debes saber. Una lambda puede usar variables que están fuera de ella:
         *         javadouble limite = 100.0;
         *         List<Producto> caros = filtrar(productos, p -> p.getPrecio() > limite);
         *         La lambda accede a limite aunque no es su parámetro. Esto se llama captura de variables.
         *         Pero hay una regla estricta: las variables capturadas deben ser final o effectively final. Significa que no pueden cambiar después de ser declaradas:
         *         javadouble limite = 100.0;
         *         limite = 200.0;  // se cambió el valor
         * 
         * // ❌ ERROR - la variable ya no es effectively final
         *         List<Producto> caros = filtrar(productos, p -> p.getPrecio() > limite);
         *         Si necesitas cambiar el valor, usa una variable nueva. La razón técnica es compleja pero la regla es simple: no cambies las variables que usas en lambdas.
         * 
         *         Parte 11 — Crear tus propias interfaces funcionales
         *         Java te permite crear tus propias interfaces funcionales para casos específicos. Solo agregas la anotación @FunctionalInterface (opcional pero recomendada):
         *         java@FunctionalInterface
         *         public interface CalculadoraDescuento {
         *             double calcular(double precio);
         *         }
         *         La anotación @FunctionalInterface le dice a Java que verifique que la interfaz tenga exactamente un método abstracto. Si agregas otro método abstracto, Java te avisa con error de compilación.
         *         Y la usas así:
         *         javaCalculadoraDescuento descuento10 = precio -> precio * 0.9;
         *         CalculadoraDescuento descuento20 = precio -> precio * 0.8;
         *         CalculadoraDescuento sinDescuento = precio -> precio;
         * 
         *         double resultado = descuento10.calcular(100.0);  // 90.0
         *         Esto es útil cuando los nombres semánticos importan. CalculadoraDescuento es más expresivo que Function<Double, Double>.
         * 
         *         Parte 12 — Casos típicos donde las lambdas son inevitables
         *         Para que termines de ver la utilidad, te muestro situaciones reales donde las lambdas son la única forma elegante.
         *                 Caso 1 — Stream API (próximo tema)
         *         javaList<String> nombresCaros = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .map(p -> p.getNombre())
         *                 .toList();
         *         Tres operaciones encadenadas, todas con lambdas. Esto es lo que harás constantemente en Spring Boot.
         *                 Caso 2 — Spring Boot Security
         *                 javahttp
         *     .authorizeHttpRequests(auth -> auth
         *                 .requestMatchers("/api/public").permitAll()
         *                 .requestMatchers("/api/admin").hasRole("ADMIN")
         *                 .anyRequest().authenticated()
         *         );
         *         Configurar reglas de seguridad con lambdas. Sin lambdas Spring Security antiguo era complejísimo.
         *                 Caso 3 — Manejo de excepciones funcional
         *         javaOptional<Producto> resultado = repositorio.findById(1);
         * 
         *         resultado.ifPresent(p -> System.out.println("Encontrado: " + p.getNombre()));
         *         resultado.orElseThrow(() -> new RuntimeException("No existe"));
         *         Lambdas para manejar diferentes casos sin if-else verbose.
         *                 Caso 4 — Tareas en hilos
         *         javaThread tarea = new Thread(() -> {
         *             System.out.println("Ejecutando en otro hilo");
         *             // más código
         *         });
         * 
         *         tarea.start();
         *         Sin lambdas tendrías que crear una clase anónima que implemente Runnable. Con lambda es una línea.
         * 
         *                 Parte 13 — Conexión profunda con Spring Boot
         *         En Spring Boot moderno las lambdas están en todas partes. Aquí algunos casos típicos que verás:
         *         - Manejar peticiones reactivas con WebFlux
         *         - Configurar Spring Security
         *         - Definir routers funcionales
         *         - Procesar streams de datos
         *                 - Configurar bean factories
         *         - Manejar eventos del sistema
         *                 - Definir handlers de excepciones
         *                 - Configurar el Builder pattern
         *         Si entiendes lambdas, todo eso se vuelve natural. Si no las entiendes, todo eso parece magia.
         * 
         *                 Resumen completo del Tema 3
         *         LAMBDA           →  función sin nombre que se pasa como argumento
         *         código que se trata como un valor
         * 
         *         Sintaxis:        (parámetros) -> cuerpo
         * 
         *         Variaciones:
         *         ()                       →  sin parámetros
         *         x                        →  un parámetro (paréntesis opcionales)
         *         (x, y)                   →  varios parámetros
         *         x -> expresión           →  cuerpo de una expresión
         *         (x) -> { ... return; }   →  cuerpo de varias líneas
         * 
         *         INTERFACES FUNCIONALES   →  interfaces con exactamente un método abstracto
         * 
         *         Las cuatro esenciales:
         *         Predicate<T>     T → boolean        para condiciones
         *         Function<T,R>    T → R              para transformar
         *         Consumer<T>      T → (nada)         para procesar sin retornar
         *         Supplier<T>      (nada) → T         para generar valores
         * 
         *         METHOD REFERENCE  →  atajo cuando la lambda solo llama a un método
         *         Clase::metodo            →  System.out::println
         * 
         *         CAPTURA DE VARIABLES   →  las lambdas pueden usar variables externas
         *         pero deben ser final o effectively final
         * 
         *         @FunctionalInterface   →  anotación para marcar interfaces funcionales
         *         el compilador verifica que tenga UN solo método
         * 
         *         UTILIDAD REAL DE LAMBDAS:
         *    ✅ Eliminar duplicación de código (mismo método con diferentes reglas)
         *    ✅ Pasar comportamiento como parámetro
         *    ✅ Código más conciso y expresivo
         *    ✅ Base de la Stream API
         *    ✅ Programación funcional en Java
         *    ✅ Configuraciones declarativas en Spring Boot
         * 
         *         MÉTODOS COMUNES QUE RECIBEN LAMBDAS:
         *         coleccion.forEach(...)
         *         coleccion.removeIf(...)
         *         coleccion.sort(...)
         *         optional.ifPresent(...)
         *         stream.filter(...)
         *         stream.map(...)
         * 
         *         LAS LAMBDAS NO SON UN CAPRICHO:
         *         Existen porque la única alternativa son las clases anónimas
         *         que ocupan 5-7 líneas para hacer lo mismo
         *         Las lambdas resuelven duplicación de código que ningún otro
         *         recurso del lenguaje puede resolver
         */
    }

    record Producto(String nombre, double precio, int stock, boolean activo) {
    }
}
