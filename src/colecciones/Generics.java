package colecciones;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        // -------------------------------------------------------------------------
        // DEMO EJECUTABLE
        // -------------------------------------------------------------------------
        System.out.println("Tema: Generics");

        List<String> nombres = new ArrayList<>();
        nombres.add("Carlos");
        nombres.add("Ana");
        System.out.println("Lista tipada: " + nombres);

        Caja<String> cajaTexto = new Caja<>();
        cajaTexto.guardar("Hola desde una caja generica");
        System.out.println("Caja<String>: " + cajaTexto.obtener());


        // -------------------------------------------------------------------------
        // APUNTE ORIGINAL CONSERVADO
        // -------------------------------------------------------------------------
        /*
         * Todo lo que escribiste queda guardado aquí como comentario de estudio.
         * La idea es que el archivo compile y se pueda ejecutar, sin borrar tus notas.
         * -----------------------------------------------------------------------------
         *         Generics — Tipos Parametrizados y Wildcards
         *         Parte 1 — El problema antes de la solución
         *         En el tema anterior usaste List<String> y Map<String, String> sin entender qué era ese <...>. Solo lo escribiste porque te lo dije.
         *                 Para entender por qué existe imagina cómo era Java antes de los generics. Antes de Java 5 las listas no tenían tipo. Todas guardaban Object, que es la clase padre de todo en Java:
         *         java// Java antiguo - sin generics
         *         List productos = new ArrayList();
         * 
         *         productos.add("Laptop");
         *         productos.add(123);          // ⚠️ se aceptaba
         *         productos.add(true);         // ⚠️ se aceptaba
         *         productos.add(new Pedido()); // ⚠️ se aceptaba todo
         *         Problema 1: podías meter cualquier cosa. Una lista de productos podía terminar con números, booleanos, perros, lo que fuera.
         *         Problema 2: al sacar elementos necesitabas castear porque salían como Object:
         *         javaString primero = (String) productos.get(0); // cast necesario
         *         Problema 3: si el cast era incorrecto, el error explotaba en runtime:
         *         javaString numero = (String) productos.get(1); // ❌ ClassCastException en runtime
         *         Esto causaba miles de bugs en producción. Java necesitaba una forma de decir "esta lista solo guarda Strings, y punto". Por eso aparecieron los generics.
         * 
         *         Parte 2 — Qué son los generics
         *         Generics son una forma de parametrizar tipos. Suena rimbombante pero es simple. Significa que una clase o método puede trabajar con un tipo que tú eliges al usarla.
         *         Una analogía clarísima: piensa en un casillero del gimnasio. El casillero está diseñado para guardar tus cosas, pero no le importa qué cosas son. Puedes guardar ropa, zapatos, una mochila, libros. El casillero es genérico, las cosas que guardas son específicas.
         *                 Una lista genérica es igual. La lista está diseñada para guardar algo, pero qué tipo de algo lo decides tú cuando la creas:
         *         javaList<String> nombres = new ArrayList<>();    // lista para Strings
         *         List<Integer> edades = new ArrayList<>();    // lista para Integers
         *         List<Producto> productos = new ArrayList<>(); // lista para Productos
         *         La misma clase ArrayList, pero se especializa para el tipo que le indiques entre <...>.
         *         Beneficios principales:
         * 
         *         Java garantiza que solo metas el tipo correcto.
         *         No necesitas castear al sacar elementos.
         *         Los errores se detectan en compilación, no en runtime.
         * 
         * 
         *         Parte 3 — Usar generics existentes (lo que ya haces)
         *         Esto ya lo viste en el tema anterior. Solo lo reforzamos:
         *         javaList<String> nombres = new ArrayList<>();
         * 
         *         nombres.add("Carlos");
         *         nombres.add("Ana");
         * 
         * // No necesitas cast, Java sabe que son Strings
         *         String primero = nombres.get(0);
         *         System.out.println(primero.toUpperCase()); // funciona, es String
         *         Compara con sin generics:
         *         javaList nombres = new ArrayList();  // sin generics
         *         nombres.add("Carlos");
         * 
         *         String primero = (String) nombres.get(0); // cast obligatorio
         *         Generics eliminan los casts y previenen errores. Esa es la idea fundamental.
         * 
         *                 Parte 4 — Crear tu propia clase genérica
         *         Hasta aquí usaste generics de Java. Ahora vamos a crear tu propia clase genérica. Esto es lo que más confunde porque la sintaxis se ve rara, pero es simple cuando lo entiendes.
         *                 Imagina que quieres crear una clase llamada Caja que pueda guardar cualquier cosa. Sin generics tendrías que hacer una clase para cada tipo:
         *         java// Sin generics - una clase por tipo (absurdo)
         *         public class CajaDeString { private String contenido; }
         *         public class CajaDeInteger { private Integer contenido; }
         *         public class CajaDeProducto { private Producto contenido; }
         *         Con generics, una sola clase para todos los tipos:
         *         javapublic class Caja<T> {
         *             private T contenido;
         * 
         *             public void guardar(T contenido) {
         *                 this.contenido = contenido;
         *             }
         * 
         *             public T obtener() {
         *                 return this.contenido;
         *             }
         *         }
         *         Vamos línea por línea sin saltarnos nada.
         *                 La línea más importante
         *         javapublic class Caja<T> {
         *             Esa <T> se lee como "parámetro de tipo". Es como un parámetro de método, pero en vez de recibir un valor, recibe un tipo.
         *             Método normal:   public void saludar(String nombre)
         *                                        ↑
         *             parámetro de valor
         * 
         *             Clase genérica:  public class Caja<T>
         *                                   ↑
         *             parámetro de tipo
         *             T no es una palabra mágica. Es solo una letra que tú eliges. Podrías llamarla X, MiTipo, ElementoDeCaja. Pero hay una convención de Java:
         *             T  →  Type (tipo en general)
         *             E  →  Element (elemento de una colección)
         *             K  →  Key (clave de un map)
         *             V  →  Value (valor de un map)
         *             N  →  Number (número)
         *             Usa estas convenciones porque otros desarrolladores las reconocerán inmediatamente.
         *             Usar T dentro de la clase
         *             Una vez que declaraste <T> en la cabecera, puedes usar T como si fuera un tipo real dentro de toda la clase:
         *             javapublic class Caja<T> {
         *                 private T contenido;            // atributo de tipo T
         * 
         *                 public void guardar(T contenido) {  // parámetro de tipo T
         *                     this.contenido = contenido;
         *                 }
         * 
         *                 public T obtener() {            // retorno de tipo T
         *                     return this.contenido;
         *                 }
         *             }
         *             Java reemplaza mentalmente T por el tipo que tú especifiques al crear el objeto.
         *             Usar tu clase genérica
         *             java// Caja para Strings
         *             Caja<String> cajaTexto = new Caja<>();
         * cajaTexto.guardar("Hola mundo");
         *             String texto = cajaTexto.obtener();
         * 
         *             // Caja para números
         *             Caja<Integer> cajaNumero = new Caja<>();
         * cajaNumero.guardar(123);
         *             Integer numero = cajaNumero.obtener();
         * 
         *             // Caja para productos
         *             Caja<Producto> cajaProducto = new Caja<>();
         * cajaProducto.guardar(new Producto("Laptop", 1500.0));
         *             Producto p = cajaProducto.obtener();
         *             Mira lo bonito. Una sola clase Caja sirve para todos los tipos. Java se encarga internamente de adaptarla al tipo que indiques.
         *             Lo que pasa internamente
         *             Cuando creas Caja<String>, Java mentalmente lo ve así:
         *             javapublic class CajaDeString {
         *                 private String contenido;
         * 
         *                 public void guardar(String contenido) {
         *                     this.contenido = contenido;
         *                 }
         * 
         *                 public String obtener() {
         *                     return this.contenido;
         *                 }
         *             }
         *             T se reemplaza por String. Si creas Caja<Integer>, T se reemplaza por Integer. Y así con cualquier tipo. Por eso se llaman tipos parametrizados: el tipo es un parámetro que tú decides.
         * 
         *             Parte 5 — Clase genérica con varios parámetros de tipo
         *             Una clase puede tener varios parámetros de tipo separados por coma. El mejor ejemplo es algo como Map:
         *             javapublic class Par<K, V> {
         *                 private K clave;
         *                 private V valor;
         * 
         *                 public Par(K clave, V valor) {
         *                     this.clave = clave;
         *                     this.valor = valor;
         *                 }
         * 
         *                 public K getClave() {
         *                     return this.clave;
         *                 }
         * 
         *                 public V getValor() {
         *                     return this.valor;
         *                 }
         *             }
         *             Y se usa así:
         *             javaPar<String, Integer> edadDeCarlos = new Par<>("Carlos", 25);
         * System.out.println(edadDeCarlos.getClave());  // Carlos
         * System.out.println(edadDeCarlos.getValor());  // 25
         * 
         *             Par<Integer, String> codigoPais = new Par<>(1, "USA");
         *             Par<String, Producto> entrada = new Par<>("laptop", new Producto("Laptop", 1500.0));
         *             Cada Par puede tener tipos completamente diferentes para clave y valor. Esa flexibilidad es la magia de generics.
         * 
         *                     Parte 6 — Métodos genéricos
         *             No solo las clases pueden ser genéricas. Métodos individuales también pueden serlo, aunque la clase que los contiene no lo sea:
         *             javapublic class Utilidades {
         * 
         *                 // Método genérico
         *                 public static <T> T primero(List<T> lista) {
         *                     return lista.get(0);
         *                 }
         *             }
         *             La cosa nueva está en la firma:
         *             javapublic static <T> T primero(List<T> lista)
         *                 ↑   ↑
         *                         │   tipo de retorno
         *             declaración del tipo genérico
         *             Ese <T> antes del tipo de retorno declara que este método usa un tipo genérico llamado T. No depende de la clase, es propio del método.
         *             Y se usa así:
         *             javaList<String> nombres = new ArrayList<>(List.of("Carlos", "Ana"));
         *             String primerNombre = Utilidades.primero(nombres);
         * 
         *             List<Integer> numeros = new ArrayList<>(List.of(10, 20, 30));
         *             Integer primerNumero = Utilidades.primero(numeros);
         *             Java deduce automáticamente que en el primer caso T = String y en el segundo T = Integer.
         *                     En Spring Boot verás métodos genéricos constantemente, especialmente en repositorios y servicios reutilizables.
         * 
         *                     Parte 7 — Wildcards, el tema que asusta pero es simple
         *             Hasta aquí trabajamos con tipos específicos. Pero a veces necesitas escribir un método que acepte una lista de cualquier tipo, sin saber cuál.
         *             Imagina que quieres un método que imprima cualquier lista:
         *             javapublic void imprimir(List<???> lista) {
         *                 for (Object item : lista) {
         *                     System.out.println(item);
         *                 }
         *             }
         * ¿Qué pones en ???? List<String> solo aceptaría Strings. List<Integer> solo aceptaría Integers. Necesitas algo más flexible.
         *             La solución son los wildcards, representados por el símbolo ?. Significa "cualquier tipo".
         * 
         *             Wildcard ? simple
         *             javapublic void imprimir(List<?> lista) {
         *                 for (Object item : lista) {
         *                     System.out.println(item);
         *                 }
         *             }
         *             Eso List<?> se lee como "una lista de cualquier tipo". Acepta cualquier lista, sin importar de qué tipo sea:
         *             javaList<String> textos = List.of("Hola", "Mundo");
         *             List<Integer> numeros = List.of(1, 2, 3);
         *             List<Producto> productos = List.of(new Producto("Laptop", 1500.0));
         * 
         * utilidades.imprimir(textos);    // funciona
         * utilidades.imprimir(numeros);   // funciona
         * utilidades.imprimir(productos); // funciona
         *             Diferencia clave entre List<Object> y List<?>
         *             Esto confunde a todos al principio. Mira:
         *             javapublic void imprimir(List<Object> lista) { ... }
         *             List<Object> significa "una lista declarada específicamente como List<Object>". Solo acepta listas declaradas como Object, no acepta List<String> ni List<Integer>:
         *             javaList<String> textos = List.of("Hola");
         * 
         *             imprimir(textos); // ❌ ERROR si el método pide List<Object>
         *             imprimir(textos); // ✅ funciona si el método pide List<?>
         *             Esto es contraintuitivo pero importante:
         *             List<Object>   →  específicamente listas declaradas como Object
         *             List<?>        →  cualquier lista de cualquier tipo
         *             Regla práctica: cuando quieras aceptar cualquier lista, usa List<?>, no List<Object>.
         * 
         *             Parte 8 — Wildcards con límite: ? extends
         *             A veces no quieres "cualquier tipo", quieres "cualquier tipo que sea un tipo específico o sus hijos". Para eso existe ? extends.
         *             Imagina esta jerarquía de herencia:
         *             javapublic class Animal { ... }
         *             public class Perro extends Animal { ... }
         *             public class Gato extends Animal { ... }
         *             Y quieres un método que acepte una lista de animales o cualquier tipo de animal:
         *             javapublic void cuidar(List<? extends Animal> animales) {
         *                 for (Animal a : animales) {
         *                     a.hacerSonido();
         *                 }
         *             }
         *             List<? extends Animal> se lee como "una lista de Animal o cualquier subclase de Animal". Acepta:
         *             javaList<Animal> generales = ...;
         *             List<Perro> perros = ...;
         *             List<Gato> gatos = ...;
         * 
         *             cuidar(generales);  // ✅ funciona
         *             cuidar(perros);     // ✅ funciona, Perro extiende Animal
         *             cuidar(gatos);      // ✅ funciona, Gato extiende Animal
         *             Sin ? extends, el método solo aceptaría List<Animal> y rechazaría List<Perro> o List<Gato>. Eso es porque en generics, List<Perro> no es List<Animal> aunque Perro sea Animal. Es una particularidad de los generics que veremos en la siguiente parte.
         * 
         *             Parte 9 — Wildcards con límite: ? super
         *             Lo contrario. ? super significa "un tipo específico o cualquier ancestro de ese tipo".
         *             Volviendo a la jerarquía:
         *             javapublic class Animal { ... }
         *             public class Perro extends Animal { ... }
         *             Si tienes:
         *             javapublic void agregarPerros(List<? super Perro> lista) {
         *                 lista.add(new Perro("Firulais", 3));
         *             }
         *             List<? super Perro> acepta listas de Perro o de cualquier ancestro de Perro:
         *             javaList<Perro> listaPerros = ...;
         *             List<Animal> listaAnimales = ...;
         *             List<Object> listaObjects = ...;
         * 
         *             agregarPerros(listaPerros);    // ✅ Perro mismo
         *             agregarPerros(listaAnimales);  // ✅ Animal es ancestro de Perro
         *             agregarPerros(listaObjects);   // ✅ Object es ancestro de todo
         * ¿Por qué? Porque si la lista acepta Animal (o más general), entonces puedes meter un Perro adentro sin problemas (un Perro ES UN Animal).
         * 
         *             Comparación visual de los tres wildcards
         *             List<?>                    →  cualquier lista, sin restricciones
         *                               ↓
         *             cualquier tipo
         * 
         *             List<? extends Animal>     →  lista de Animal o sus hijos
         *                               ↓ ↓
         *             Animal, Perro, Gato
         * 
         *             List<? super Perro>        →  lista de Perro o sus padres
         *                               ↑ ↑
         *             Perro, Animal, Object
         * 
         *             Parte 10 — La regla PECS (truco para no confundirte)
         *             Hay una regla mnemotécnica famosa para saber cuándo usar extends y cuándo usar super:
         *             PECS  →  Producer Extends, Consumer Super
         *             Traducido:
         *             Si vas a LEER de la colección (consumirla)     →  ? extends
         *             Si vas a ESCRIBIR en la colección (producirla) →  ? super
         *             Ejemplo de leer (extends):
         *             javapublic double sumar(List<? extends Number> numeros) {
         *                 double total = 0;
         *                 for (Number n : numeros) {  // leyendo
         *                     total += n.doubleValue();
         *                 }
         *                 return total;
         *             }
         *             Ejemplo de escribir (super):
         *             javapublic void agregarPerros(List<? super Perro> lista) {
         *                 lista.add(new Perro("Firulais", 3));  // escribiendo
         *                 lista.add(new Perro("Toby", 5));
         *             }
         *             Si vas a hacer ambas cosas, probablemente debas usar un tipo específico sin wildcards.
         *             En la práctica, el 90% de las veces usarás ? extends. ? super es menos común. Si te encuentras dudando, empieza con ? extends.
         * 
         *             Parte 11 — Limitar tipos al declarar la clase
         *             Cuando creas una clase genérica también puedes limitar qué tipos acepta:
         *             javapublic class Calculadora<T extends Number> {
         *                 private T valor;
         * 
         *                 public Calculadora(T valor) {
         *                     this.valor = valor;
         *                 }
         * 
         *                 public double duplicar() {
         *                     return valor.doubleValue() * 2;
         *                 }
         *             }
         * <T extends Number> dice "T debe ser Number o cualquier subclase". Esto te permite usar métodos de Number dentro de la clase:
         *             javaCalculadora<Integer> calc1 = new Calculadora<>(10);   // ✅ Integer extiende Number
         *             Calculadora<Double> calc2 = new Calculadora<>(3.14);  // ✅ Double extiende Number
         *             Calculadora<String> calc3 = new Calculadora<>("Hola"); // ❌ String NO extiende Number
         *             Esto es muy útil cuando tu clase genérica necesita ciertas garantías sobre el tipo.
         * 
         *             Parte 12 — Por qué List<Perro> no es List<Animal>
         *             Esto confunde a casi todos. Tiene sentido matemático aunque parezca raro.
         *             Sabemos que Perro ES UN Animal por herencia:
         *             javaAnimal a = new Perro(); // ✅ funciona
         *             Pero esto NO funciona:
         *             javaList<Animal> animales = new ArrayList<Perro>(); // ❌ ERROR
         * ¿Por qué? Imagina que se permitiera:
         *             javaList<Perro> perros = new ArrayList<>();
         *             List<Animal> animales = perros;  // si fuera permitido
         * 
         * animales.add(new Gato()); // metiste un Gato en una lista de Perros 💥
         *             Romperíamos el tipado. Por eso Java prohíbe que List<Perro> sea List<Animal>. Los generics NO son covariantes por defecto en Java.
         *             La solución para flexibilidad son los wildcards:
         *             javaList<? extends Animal> animales = new ArrayList<Perro>(); // ✅ funciona con wildcards
         *             Por eso los wildcards existen. Para darte flexibilidad de aceptar jerarquías de tipos sin romper la seguridad de tipos.
         * 
         *             Parte 13 — Type erasure: el detalle técnico que debes saber
         *             Algo importante para tu cultura. Java implementa los generics con una técnica llamada type erasure (borrado de tipos). Significa que en runtime los generics se borran.
         *             Esto:
         *             javaList<String> textos = new ArrayList<>();
         *             List<Integer> numeros = new ArrayList<>();
         *             En runtime ambos se ven como:
         *             javaList textos = new ArrayList();
         *             List numeros = new ArrayList();
         *             Por eso no puedes hacer cosas como:
         *             javaif (lista instanceof List<String>) { } // ❌ ERROR, en runtime no existe esa info
         *             Los generics existen para que el compilador te proteja. Pero en runtime se borran. Es importante saberlo porque a veces aparecen comportamientos raros que solo se explican entendiendo type erasure.
         * 
         *             Parte 14 — Conexión con Spring Boot
         *             Generics están en todas partes en Spring Boot. Veamos los casos más típicos:
         *             Repositorios:
         *             javapublic interface ProductoRepository extends JpaRepository<Producto, Long> {
         *             }
         *             Ese <Producto, Long> le dice a Spring que el repositorio maneja entidades de tipo Producto con claves de tipo Long. Spring usa generics para generar automáticamente los métodos de CRUD con los tipos correctos.
         *             Respuestas REST:
         *             javapublic ResponseEntity<List<ProductoResponse>> obtenerProductos() {
         *                 List<ProductoResponse> productos = servicio.listar();
         *                 return ResponseEntity.ok(productos);
         *             }
         *             ResponseEntity<List<ProductoResponse>> significa "una respuesta HTTP que contiene una lista de ProductoResponse". Los generics permiten que Spring sepa exactamente qué tipo de datos devuelves.
         *             Servicios:
         *             javapublic Optional<Producto> buscarPorId(Long id) { ... }
         *             Optional<Producto> es un genérico que indica "puede haber un Producto o puede no haber nada".
         *             Sin entender generics el código de Spring Boot se ve como jeroglíficos. Con generics te das cuenta de que es un sistema muy bien diseñado.
         * 
         *             Resumen completo del Tema 2
         *             GENERICS              →  tipos parametrizados
         *             escribes código que funciona con tipos genéricos
         *             que tú especificas al usarlo
         * 
         *             Sintaxis básica:
         *             List<String>          →  lista de Strings
         *             Map<String, Integer>  →  map de String a Integer
         * 
         *             Crear clase genérica:
         *             public class Caja<T>              →  un parámetro de tipo
         *             public class Par<K, V>            →  dos parámetros de tipo
         * 
         *             Convenciones de letras:
         *             T  →  Type genérico
         *             E  →  Element
         *             K  →  Key
         *             V  →  Value
         *             N  →  Number
         * 
         *             Método genérico:
         *             public static <T> T primero(List<T> lista)
         *                   ↑
         *             declaración del tipo
         * 
         *             WILDCARDS - el símbolo ?
         *             List<?>                  →  cualquier lista de cualquier tipo
         *             List<? extends Animal>   →  lista de Animal o subclases
         *             List<? super Perro>      →  lista de Perro o sus padres
         * 
         *             Regla PECS:
         *             Producer Extends   →  para LEER de la colección, usa ? extends
         *             Consumer Super     →  para ESCRIBIR en la colección, usa ? super
         * 
         *             Limitar al declarar:
         *             class Calculadora<T extends Number>   →  T debe ser Number o subclase
         * 
         *             IMPORTANTE:
         *             List<Perro> NO es List<Animal>   →  generics no son covariantes
         *             La solución son los wildcards
         * 
         *             Type erasure:
         *             Los generics existen solo en compilación
         *             En runtime se borran
         *             Sirven para que el COMPILADOR detecte errores
         * 
         *             Beneficios principales:
         *                     ✅ Seguridad de tipos en compilación
         *    ✅ Eliminación de casts manuales
         *    ✅ Detección temprana de errores
         *    ✅ Código más reutilizable y limpio
         * 
         *             En Spring Boot:
         *             JpaRepository<Producto, Long>
         *             ResponseEntity<List<ProductoResponse>>
         *             Optional<Producto>
         *             están en todas partes
         */
    }

    static class Caja<T> {
        private T contenido;

        void guardar(T contenido) {
            this.contenido = contenido;
        }

        T obtener() {
            return this.contenido;
        }
    }
}
