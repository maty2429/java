package colecciones;

import java.util.Comparator;
import java.util.List;

public class Stream {
    public static void main(String[] args) {
        // -------------------------------------------------------------------------
        // DEMO EJECUTABLE
        // -------------------------------------------------------------------------
        System.out.println("Tema: Stream API");

        List<Producto> productos = List.of(
                new Producto("Laptop", 1500.0, 8),
                new Producto("Mouse", 25.0, 2),
                new Producto("Monitor", 300.0, 4),
                new Producto("Teclado", 80.0, 0)
        );

        List<String> nombresCarosConStock = productos.stream()
                .filter(producto -> producto.stock() > 0)
                .filter(producto -> producto.precio() > 100)
                .sorted(Comparator.comparingDouble(Producto::precio))
                .map(Producto::nombre)
                .toList();

        System.out.println("Productos caros con stock: " + nombresCarosConStock);


        // -------------------------------------------------------------------------
        // APUNTE ORIGINAL CONSERVADO
        // -------------------------------------------------------------------------
        /*
         * Todo lo que escribiste queda guardado aquí como comentario de estudio.
         * La idea es que el archivo compile y se pueda ejecutar, sin borrar tus notas.
         * -----------------------------------------------------------------------------
         *         Stream API — La Forma Moderna de Manipular Datos
         *         Parte 1 — El problema antes de la solución
         *         Imagina que tienes una lista de productos y necesitas hacer esto:
         *         1. Filtrar solo los productos con stock mayor a 0
         *         2. Filtrar solo los que cuestan más de 100
         *         3. Obtener solo sus nombres
         *         4. Ordenarlos alfabéticamente
         *         5. Quedarte solo con los primeros 5
         *         Sin Stream API tendrías que escribir algo así de horrible:
         *         javaList<Producto> productos = obtenerProductos();
         * 
         * // Paso 1 - filtrar con stock
         *         List<Producto> conStock = new ArrayList<>();
         *         for (Producto p : productos) {
         *             if (p.getStock() > 0) {
         *                 conStock.add(p);
         *             }
         *         }
         * 
         * // Paso 2 - filtrar por precio
         *         List<Producto> caros = new ArrayList<>();
         *         for (Producto p : conStock) {
         *             if (p.getPrecio() > 100) {
         *                 caros.add(p);
         *             }
         *         }
         * 
         * // Paso 3 - obtener nombres
         *         List<String> nombres = new ArrayList<>();
         *         for (Producto p : caros) {
         *             nombres.add(p.getNombre());
         *         }
         * 
         * // Paso 4 - ordenar
         *         Collections.sort(nombres);
         * 
         * // Paso 5 - quedarse con los primeros 5
         *         List<String> resultado = new ArrayList<>();
         *         for (int i = 0; i < 5 && i < nombres.size(); i++) {
         *             resultado.add(nombres.get(i));
         *         }
         *         5 listas intermedias, 5 bucles, 30 líneas de código. Y lo peor: tres meses después nadie entiende qué hace este código sin leerlo línea por línea.
         *         Con Stream API el mismo problema se resuelve así:
         *         javaList<String> resultado = productos.stream()
         *                 .filter(p -> p.getStock() > 0)
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .map(p -> p.getNombre())
         *                 .sorted()
         *                 .limit(5)
         *                 .toList();
         *         Una sola operación encadenada. Cada línea dice qué hace en lenguaje casi humano: filtrar, filtrar, mapear, ordenar, limitar. Sin listas intermedias, sin bucles manuales.
         *         Esa es la utilidad de Stream API: describir QUÉ quieres hacer con los datos, no CÓMO recorrerlos paso a paso.
         * 
         *                 Parte 2 — Qué es un Stream
         *         Un Stream no es una colección. Es una secuencia de elementos que fluyen a través de operaciones, como agua por una tubería.
         *         Una analogía perfecta. Piensa en una línea de producción de una fábrica:
         *         Productos entran → filtro de calidad → empaquetado → etiquetado → caja final
         *         Los productos van pasando por cada estación. Cada estación hace una sola cosa. Al final salen procesados. La fábrica no almacena los productos entre estaciones; van fluyendo.
         *         Un Stream es exactamente eso. Los datos entran, pasan por operaciones encadenadas, y al final obtienes un resultado. Pero a diferencia de las colecciones:
         *         List<Producto>    →  contenedor que guarda los productos
         *         Stream<Producto>  →  flujo de productos pasando por operaciones
         *         Diferencias clave que debes grabarte:
         *         Una colección almacena datos
         *         Un stream procesa datos
         * 
         *         Una colección la recorres muchas veces
         *         Un stream se usa una sola vez y se "consume"
         * 
         *         Una colección modifica los datos directamente
         *         Un stream NO modifica la colección original
         *         Esa última es importante. Stream nunca modifica la lista original. Siempre retorna nuevos streams o resultados nuevos.
         * 
         *                 Parte 3 — Crear un Stream
         *         Hay varias formas de crear un Stream. La más común es desde una colección:
         *         javaList<String> nombres = List.of("Carlos", "Ana", "Bruno");
         * 
         * // Crear stream desde una lista
         *         Stream<String> stream = nombres.stream();
         *         También puedes crear streams desde valores directos:
         *         java// Desde valores sueltos
         *         Stream<String> stream = Stream.of("Carlos", "Ana", "Bruno");
         * 
         * // Desde un array
         *         String[] array = {"Carlos", "Ana", "Bruno"};
         *         Stream<String> stream = Arrays.stream(array);
         *         Pero el 95% del tiempo crearás streams desde colecciones con .stream(). Es tan común que casi nunca creas el stream a mano: lo creas y lo usas en la misma cadena de operaciones.
         * 
         *                 Parte 4 — Estructura básica de un Stream
         *         Un stream se compone de tres etapas. Memoriza estos términos:
         *         1. CREACIÓN          →  obtienes el stream
         *         2. OPERACIONES       →  encadenas transformaciones
         *         INTERMEDIAS          (filter, map, sorted, etc.)
         *         3. OPERACIÓN         →  obtienes el resultado final
         *         TERMINAL             (toList, count, forEach, etc.)
         *         Visualmente:
         *         javaproductos.stream()           ← creación
         *                 .filter(...)             ← operación intermedia
         *     .map(...)                ← operación intermedia
         *     .sorted()                ← operación intermedia
         *     .toList();               ← operación terminal
         *         Una característica importantísima: las operaciones intermedias son lazy (perezosas). No se ejecutan inmediatamente. Solo se ejecutan cuando llegas a la operación terminal. Esto significa que Java puede optimizar internamente toda la cadena.
         *         Cada vez que llamas a una operación intermedia, obtienes otro Stream. Por eso puedes encadenarlas. Cuando llamas a una operación terminal, obtienes el resultado final y el stream se cierra.
         * 
         *                 Parte 5 — Operaciones intermedias esenciales
         *         Vamos a ver las operaciones que más vas a usar. Cada una recibe una lambda (recuerdas el tema anterior) y retorna otro Stream.
         *         filter — quedarse con los que cumplen una condición
         *         filter recibe un Predicate<T> (la interfaz funcional que retorna boolean):
         *         javaList<Producto> productos = ...;
         * 
         *         List<Producto> caros = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .toList();
         *         Mira la línea importante:
         *         java.filter(p -> p.getPrecio() > 100)
         * 
         *         filter → la operación.
         *         p → cada producto del stream.
         *         p.getPrecio() > 100 → la condición. Si retorna true, el producto pasa al siguiente paso. Si retorna false, se descarta.
         * 
         *                 Imagina un colador en la cocina. filter es ese colador. Solo pasan los elementos que cumplen la regla. Los demás caen y se descartan.
         *         Puedes encadenar varios filters:
         *         javaList<Producto> resultado = productos.stream()
         *                 .filter(p -> p.getStock() > 0)
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .filter(p -> p.getCategoria().equals("electronica"))
         *                 .toList();
         *         Cada filter agrega una condición. Es equivalente a combinarlos con && pero más legible.
         *         map — transformar cada elemento
         *         map recibe una Function<T, R> (recibe T, retorna R):
         *         javaList<String> nombres = productos.stream()
         *                 .map(p -> p.getNombre())
         *                 .toList();
         *         Línea importante:
         *         java.map(p -> p.getNombre())
         * 
         *         map → la operación.
         *         p → cada producto del stream.
         *         p.getNombre() → el valor de salida. Por cada Producto que entra, sale un String.
         * 
         *         Una analogía. Imagina un convertidor de monedas. Cada billete que entra sale convertido a otra moneda. La cantidad de billetes es la misma, pero el contenido cambia.
         *                 map no cambia la cantidad de elementos. Si entran 10 productos, salen 10 valores. Solo cambia el tipo o el contenido.
         *                 Otros usos típicos:
         *         java// Producto → precio con IVA
         *         List<Double> conIVA = productos.stream()
         *                 .map(p -> p.getPrecio() * 1.19)
         *                 .toList();
         * 
         * // Producto → mayúsculas del nombre
         *         List<String> nombresMayus = productos.stream()
         *                 .map(p -> p.getNombre().toUpperCase())
         *                 .toList();
         * 
         * // Producto → DTO de respuesta
         *         List<ProductoResponse> dtos = productos.stream()
         *                 .map(p -> new ProductoResponse(p.getId(), p.getNombre(), p.getPrecio()))
         *                 .toList();
         *         Esa última es literalmente lo que harás en Spring Boot todo el tiempo. Mapear entidades a DTOs.
         *         sorted — ordenar
         *         sorted tiene dos versiones. Sin parámetros usa el orden natural:
         *         javaList<Integer> numeros = List.of(3, 1, 4, 1, 5, 9, 2, 6);
         * 
         *         List<Integer> ordenados = numeros.stream()
         *                 .sorted()
         *                 .toList(); // [1, 1, 2, 3, 4, 5, 6, 9]
         *         Funciona para tipos que tienen orden natural: números, strings, etc.
         *                 Para tipos personalizados o criterios custom, pasas un Comparator:
         *         java// Ordenar productos por precio ascendente
         *         List<Producto> porPrecio = productos.stream()
         *                 .sorted((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()))
         *                 .toList();
         * 
         * // Lo mismo más limpio con Comparator
         *         List<Producto> porPrecio = productos.stream()
         *                 .sorted(Comparator.comparingDouble(Producto::getPrecio))
         *                 .toList();
         * 
         * // Ordenar descendente (precio mayor primero)
         *         List<Producto> porPrecioDesc = productos.stream()
         *                 .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
         *                 .toList();
         *         Comparator.comparingDouble(Producto::getPrecio) se lee como "compara por el método getPrecio". Y .reversed() invierte el orden.
         *                 distinct — eliminar duplicados
         *         javaList<String> ciudades = List.of("Santiago", "Buenos Aires", "Santiago", "Lima", "Lima");
         * 
         *         List<String> unicas = ciudades.stream()
         *                 .distinct()
         *                 .toList(); // [Santiago, Buenos Aires, Lima]
         *         distinct usa el método equals para determinar duplicados. Para tus clases personalizadas, debes sobrescribir equals y hashCode (lo veremos cuando lleguemos a la parte de Object methods).
         *         limit — quedarse con los primeros N
         *         javaList<Producto> top5 = productos.stream()
         *                 .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
         *                 .limit(5)
         *                 .toList();
         *         limit(5) toma los primeros 5 elementos del stream. Se combina perfecto con sorted para hacer top N.
         *                 skip — saltarse los primeros N
         *         java// Saltar los primeros 10, quedarse con el resto
         *         List<Producto> resto = productos.stream()
         *                 .skip(10)
         *                 .toList();
         * 
         * // Paginación: saltar 20, tomar 10 = página 3 con 10 por página
         *         List<Producto> pagina3 = productos.stream()
         *                 .skip(20)
         *                 .limit(10)
         *                 .toList();
         *         Combinando skip y limit puedes hacer paginación manual. En Spring Boot normalmente Spring Data se encarga, pero el concepto es el mismo.
         *                 peek — espiar sin modificar
         *         peek te permite "espiar" los elementos en medio del stream sin modificarlos. Útil para debugging:
         *         javaList<String> resultado = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .peek(p -> System.out.println("Después de filter: " + p.getNombre()))
         *                 .map(p -> p.getNombre())
         *                 .peek(nombre -> System.out.println("Después de map: " + nombre))
         *                 .toList();
         *         Recibe un Consumer (no retorna nada, solo hace algo con el elemento). Útil para ver qué está pasando entre operaciones.
         * 
         *                 Parte 6 — Operaciones terminales esenciales
         *         Las operaciones terminales cierran el stream y producen un resultado final. Sin una terminal, las operaciones intermedias no se ejecutan.
         *                 toList — convertir a lista (la más usada)
         *         javaList<String> nombres = productos.stream()
         *                 .map(p -> p.getNombre())
         *                 .toList();
         *         toList() (Java 16+) retorna una lista inmutable con el resultado. Es la operación terminal que más vas a usar.
         *                 En Java más antiguo se usaba .collect(Collectors.toList()), que sigue funcionando pero es más verboso. Usa .toList() siempre que puedas.
         *                 count — contar cuántos elementos hay
         *         javalong cantidad = productos.stream()
         *                 .filter(p -> p.getStock() > 0)
         *                 .count();
         *         Retorna un long con la cantidad. Útil para saber cuántos elementos cumplen una condición sin necesitar la lista completa.
         *                 forEach — hacer algo con cada elemento
         *         forEach recibe un Consumer<T>:
         *         javaproductos.stream()
         *                 .filter(p -> p.getStock() > 0)
         *                 .forEach(p -> System.out.println(p.getNombre()));
         *         Es la terminal cuando solo quieres procesar cada elemento sin obtener un resultado. Imprimir, guardar, enviar, etc.
         *                 findFirst — el primer elemento que cumpla
         *         javaOptional<Producto> primero = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .findFirst();
         *         Retorna un Optional<T>. Si hay al menos un elemento, lo devuelve. Si no hay ninguno, el Optional está vacío.
         *                 Importante: retorna Optional porque puede que no haya ningún elemento que cumpla. Java te obliga a manejar ese caso:
         *         javaOptional<Producto> primero = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .findFirst();
         * 
         *         if (primero.isPresent()) {
         *             System.out.println(primero.get().getNombre());
         *         }
         * 
         * // O más elegante con lambda
         *         primero.ifPresent(p -> System.out.println(p.getNombre()));
         * 
         * // O con valor por defecto
         *         Producto p = primero.orElse(new Producto("Default", 0));
         *         Optional es un tema que ameritaría su propio capítulo. Por ahora quédate con que es una caja que puede contener un valor o estar vacía.
         *         anyMatch, allMatch, noneMatch — verificaciones rápidas
         *         Estas tres operaciones terminales retornan boolean:
         *         javaList<Producto> productos = ...;
         * 
         * // ¿Existe al menos uno con precio > 1000?
         *         boolean hayCarisimo = productos.stream()
         *                 .anyMatch(p -> p.getPrecio() > 1000);
         * 
         * // ¿Todos tienen stock?
         *         boolean todosConStock = productos.stream()
         *                 .allMatch(p -> p.getStock() > 0);
         * 
         * // ¿Ninguno está sin nombre?
         *         boolean ningunoSinNombre = productos.stream()
         *                 .noneMatch(p -> p.getNombre().isEmpty());
         *         Son muy útiles para validaciones rápidas. Java optimiza estas operaciones: en cuanto encuentra una respuesta definitiva, deja de procesar el resto. Por ejemplo, anyMatch para en cuanto encuentra el primer elemento que cumple.
         *                 min y max — encontrar el menor o mayor
         *         javaOptional<Producto> masCaro = productos.stream()
         *                 .max(Comparator.comparingDouble(Producto::getPrecio));
         * 
         *         Optional<Producto> masBarato = productos.stream()
         *                 .min(Comparator.comparingDouble(Producto::getPrecio));
         *         Retornan Optional porque el stream podría estar vacío.
         *         reduce — combinar todos los elementos en uno
         *         reduce es poderosa pero un poco compleja al principio. Combina todos los elementos en un solo resultado:
         *         javaList<Integer> numeros = List.of(1, 2, 3, 4, 5);
         * 
         * // Sumar todos
         *         Optional<Integer> suma = numeros.stream()
         *                 .reduce((a, b) -> a + b); // Optional[15]
         * 
         * // Con valor inicial - retorna Integer en vez de Optional
         *         Integer sumaConInicio = numeros.stream()
         *                 .reduce(0, (a, b) -> a + b); // 15
         *         La idea: toma dos elementos, los combina, toma el resultado y lo combina con el siguiente, y así. Como ir acumulando.
         *                 En la práctica reduce se usa poco directamente porque para casos comunes hay métodos más específicos. Pero es bueno saber que existe.
         * 
         *         Parte 7 — Stream con tipos primitivos: IntStream, LongStream, DoubleStream
         *         Cuando trabajas con números, los streams tienen versiones especializadas más eficientes y con métodos útiles:
         *         javaIntStream    →  stream de ints
         *         LongStream   →  stream de longs
         *         DoubleStream →  stream de doubles
         *         La utilidad principal: tienen métodos directos para operaciones numéricas:
         *         javaList<Producto> productos = ...;
         * 
         * // Convertir a IntStream con mapToInt
         *         int totalStock = productos.stream()
         *                 .mapToInt(Producto::getStock)
         *                 .sum();
         * 
         *         double promedio = productos.stream()
         *                 .mapToDouble(Producto::getPrecio)
         *                 .average()
         *                 .orElse(0);
         * 
         *         int precioMaximo = productos.stream()
         *                 .mapToInt(p -> (int) p.getPrecio())
         *                 .max()
         *                 .orElse(0);
         * 
         *         long totalProductos = productos.stream()
         *                 .mapToInt(Producto::getStock)
         *                 .count();
         *         mapToInt, mapToDouble, mapToLong te dan acceso a esos streams especiales con sum(), average(), min(), max() directamente.
         *                 También puedes crear rangos directos:
         *         java// Stream de números del 1 al 10
         *         IntStream.rangeClosed(1, 10).forEach(System.out::println);
         * 
         * // Stream de números del 1 al 9 (exclusivo del 10)
         *         IntStream.range(1, 10).forEach(System.out::println);
         *         Útil para reemplazar bucles tipo for (int i = 1; i <= 10; i++) cuando quieres encadenarlos con operaciones funcionales.
         * 
         *         Parte 8 — Collectors — agrupar y combinar resultados
         *         Collectors es una clase con métodos estáticos para crear "recolectores" que terminan un stream y agrupan los resultados de formas avanzadas. Se usa con la operación terminal collect.
         *                 Collectors.toList — la versión vieja de toList
         *         javaList<String> nombres = productos.stream()
         *                 .map(Producto::getNombre)
         *                 .collect(Collectors.toList());
         *         Como dije antes, en Java 16+ usa mejor .toList(). Pero verás Collectors.toList() en código antiguo.
         *                 Collectors.toSet — convertir a Set
         *         javaSet<String> categorias = productos.stream()
         *                 .map(Producto::getCategoria)
         *                 .collect(Collectors.toSet());
         *         Retorna un Set sin duplicados.
         *                 Collectors.toMap — convertir a Map
         *         javaMap<Long, Producto> porId = productos.stream()
         *                 .collect(Collectors.toMap(
         *                         p -> p.getId(),        // clave: el id
         *                         p -> p                  // valor: el producto completo
         *                 ));
         *         Toma cada elemento y lo convierte en un par clave-valor en un Map. Muy útil para crear índices de búsqueda.
         *                 Otra forma usando method references:
         *         javaMap<Long, String> idANombre = productos.stream()
         *                 .collect(Collectors.toMap(
         *                         Producto::getId,
         *                         Producto::getNombre
         *                 ));
         *         Collectors.groupingBy — agrupar por una propiedad
         *         Esta es una de las operaciones más poderosas y usadas. Agrupa elementos por un criterio:
         *         javaMap<String, List<Producto>> porCategoria = productos.stream()
         *                 .collect(Collectors.groupingBy(p -> p.getCategoria()));
         *         Resultado: un Map donde la clave es la categoría y el valor es la lista de productos de esa categoría.
         *                 Si tienes productos como:
         *         Laptop (electronica)
         *         Mouse (electronica)
         *         Manzana (comida)
         *         Pan (comida)
         *         El resultado sería:
         *         {
         *             "electronica": [Laptop, Mouse],
         *             "comida": [Manzana, Pan]
         *         }
         *         Esto reemplaza decenas de líneas de código manual con bucles y maps. Increíble.
         *                 Puedes combinar groupingBy con otros collectors:
         *         java// Agrupar por categoría y contar cuántos hay
         *         Map<String, Long> conteoPorCategoria = productos.stream()
         *                 .collect(Collectors.groupingBy(
         *                         p -> p.getCategoria(),
         *                         Collectors.counting()
         *                 ));
         * 
         * // Resultado: {electronica=2, comida=2}
         *         java// Agrupar por categoría y obtener el precio promedio
         *         Map<String, Double> promedioPorCategoria = productos.stream()
         *                 .collect(Collectors.groupingBy(
         *                         p -> p.getCategoria(),
         *                         Collectors.averagingDouble(Producto::getPrecio)
         *                 ));
         *         java// Agrupar por categoría y obtener solo los nombres
         *         Map<String, List<String>> nombresPorCategoria = productos.stream()
         *                 .collect(Collectors.groupingBy(
         *                         p -> p.getCategoria(),
         *                         Collectors.mapping(Producto::getNombre, Collectors.toList())
         *                 ));
         *         Collectors.partitioningBy — dividir en dos grupos
         *         partitioningBy es como groupingBy pero con un Predicate, dividiendo en true/false:
         *         javaMap<Boolean, List<Producto>> divididos = productos.stream()
         *                 .collect(Collectors.partitioningBy(p -> p.getPrecio() > 100));
         * 
         *         List<Producto> caros = divididos.get(true);
         *         List<Producto> baratos = divididos.get(false);
         *         Útil cuando quieres separar elementos en dos grupos según una condición.
         *                 Collectors.joining — concatenar Strings
         *         javaString todosLosNombres = productos.stream()
         *                 .map(Producto::getNombre)
         *                 .collect(Collectors.joining(", "));
         * 
         * // "Laptop, Mouse, Teclado, Monitor"
         *         Une todos los strings con el separador que indiques.
         *                 Con prefijo y sufijo:
         *         javaString listado = productos.stream()
         *                 .map(Producto::getNombre)
         *                 .collect(Collectors.joining(", ", "[", "]"));
         * 
         * // "[Laptop, Mouse, Teclado, Monitor]"
         *         Collectors.counting — solo contar
         *         javalong total = productos.stream()
         *                 .collect(Collectors.counting());
         *         Es equivalente a .count() pero útil cuando lo combinas con groupingBy como viste antes.
         *         Collectors.summingInt, summingDouble — sumar
         *         javaint totalStock = productos.stream()
         *                 .collect(Collectors.summingInt(Producto::getStock));
         * 
         *         double totalDinero = productos.stream()
         *                 .collect(Collectors.summingDouble(Producto::getPrecio));
         *         Útil dentro de groupingBy para sumar por grupo.
         *         Collectors.averagingInt, averagingDouble — promediar
         *         javadouble promedioStock = productos.stream()
         *                 .collect(Collectors.averagingInt(Producto::getStock));
         * 
         *         double promedioPrecio = productos.stream()
         *                 .collect(Collectors.averagingDouble(Producto::getPrecio));
         * 
         *         Parte 9 — flatMap — el caso especial
         *         flatMap es como map pero aplana estructuras anidadas. Suena raro pero es muy útil.
         *         Imagina que tienes una lista de pedidos, y cada pedido tiene una lista de productos:
         *         javapublic class Pedido {
         *             private List<Producto> productos;
         *             // ...
         *         }
         * 
         *         List<Pedido> pedidos = ...;
         *         Si usas map para obtener los productos de cada pedido:
         *         javaList<List<Producto>> resultado = pedidos.stream()
         *                 .map(Pedido::getProductos)
         *                 .toList();
         *         Obtienes una lista de listas. Eso no es lo que quieres normalmente. Quieres una sola lista con todos los productos de todos los pedidos.
         *         Ahí entra flatMap:
         *         javaList<Producto> todosLosProductos = pedidos.stream()
         *                 .flatMap(pedido -> pedido.getProductos().stream())
         *                 .toList();
         *         flatMap toma cada lista de productos, la convierte en un stream y los une todos en un solo stream plano. Por eso "flat" (plano).
         *                 Regla simple:
         *         map      →  T → R (transformar uno en otro)
         *         flatMap  →  T → Stream<R> (transformar uno en varios y aplanar)
         *         Lo verás en Spring Boot cuando navegues relaciones de entidades. Un Usuario tiene varios Pedidos, cada Pedido tiene varios Items. Para obtener todos los items de un usuario:
         *         javaList<Item> items = usuario.getPedidos().stream()
         *                 .flatMap(p -> p.getItems().stream())
         *                 .toList();
         * 
         *         Parte 10 — Ejemplo integrado completo
         *         Vamos a juntar todo en un ejemplo realista. Imagina que tienes un servicio que procesa pedidos:
         *         javapublic class Pedido {
         *             private Long id;
         *             private String cliente;
         *             private List<Producto> productos;
         *             private LocalDate fecha;
         *             private boolean pagado;
         * 
         *             // getters omitidos
         *         }
         * 
         *         public class Producto {
         *             private String nombre;
         *             private double precio;
         *             private String categoria;
         * 
         *             // getters omitidos
         *         }
         *         Y quieres responder estas preguntas con un solo método cada una:
         *         javapublic class ServicioReportes {
         * 
         *             private List<Pedido> pedidos;
         * 
         *             public ServicioReportes(List<Pedido> pedidos) {
         *                 this.pedidos = pedidos;
         *             }
         * 
         *             // ¿Cuántos pedidos hay pagados?
         *             public long contarPagados() {
         *                 return pedidos.stream()
         *                         .filter(Pedido::isPagado)
         *                         .count();
         *             }
         * 
         *             // Lista de clientes únicos
         *             public List<String> clientesUnicos() {
         *                 return pedidos.stream()
         *                         .map(Pedido::getCliente)
         *                         .distinct()
         *                         .sorted()
         *                         .toList();
         *             }
         * 
         *             // ¿Cuál es el pedido con el cliente con nombre más largo?
         *             public Optional<Pedido> pedidoClienteNombreMasLargo() {
         *                 return pedidos.stream()
         *                         .max(Comparator.comparingInt(p -> p.getCliente().length()));
         *             }
         * 
         *             // Total facturado de los pedidos pagados
         *             public double totalFacturado() {
         *                 return pedidos.stream()
         *                         .filter(Pedido::isPagado)
         *                         .flatMap(p -> p.getProductos().stream())
         *                         .mapToDouble(Producto::getPrecio)
         *                         .sum();
         *             }
         * 
         *             // Pedidos agrupados por cliente
         *             public Map<String, List<Pedido>> pedidosPorCliente() {
         *                 return pedidos.stream()
         *                         .collect(Collectors.groupingBy(Pedido::getCliente));
         *             }
         * 
         *             // Total gastado por cada cliente
         *             public Map<String, Double> totalPorCliente() {
         *                 return pedidos.stream()
         *                         .collect(Collectors.groupingBy(
         *                                 Pedido::getCliente,
         *                                 Collectors.summingDouble(p ->
         *                                         p.getProductos().stream()
         *                                                 .mapToDouble(Producto::getPrecio)
         *                                                 .sum()
         *                                 )
         *                         ));
         *             }
         * 
         *             // Top 5 productos más vendidos (más aparecen)
         *             public List<String> top5ProductosMasVendidos() {
         *                 return pedidos.stream()
         *                         .flatMap(p -> p.getProductos().stream())
         *                         .map(Producto::getNombre)
         *                         .collect(Collectors.groupingBy(
         *                                 Function.identity(),
         *                                 Collectors.counting()
         *                         ))
         *                         .entrySet().stream()
         *                         .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
         *                         .limit(5)
         *                         .map(Map.Entry::getKey)
         *                         .toList();
         *             }
         *         }
         *         Mira la última. Hace algo complejísimo en 8 líneas: contar cuántas veces aparece cada producto, ordenar por cantidad, tomar los 5 primeros, devolver solo los nombres. Sin Stream API esto serían 50+ líneas.
         * 
         *                 Parte 11 — Reglas y advertencias importantes
         *         Hay algunas cosas que debes saber para no cometer errores comunes:
         *         Un Stream se usa UNA sola vez
         *         javaStream<Producto> stream = productos.stream();
         * 
         *         stream.filter(p -> p.getPrecio() > 100).toList();
         *         stream.map(Producto::getNombre).toList(); // ❌ ERROR - stream ya cerrado
         *         Una vez que ejecutas una operación terminal, el stream se cierra. Si necesitas hacer dos cosas diferentes, crea dos streams:
         *         javaList<Producto> caros = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .toList();
         * 
         *         List<String> nombres = productos.stream()
         *                 .map(Producto::getNombre)
         *                 .toList();
         *         Streams NO modifican la colección original
         *         javaList<Producto> productos = new ArrayList<>(...);
         * 
         *         productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .toList(); // crea una nueva lista
         * 
         * // productos sigue intacta con todos los productos
         *         Si quieres modificar la lista original, debes asignar el resultado:
         *         javaproductos = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .collect(Collectors.toCollection(ArrayList::new));
         *         O usar removeIf directamente sobre la lista (que sí modifica):
         *         javaproductos.removeIf(p -> p.getPrecio() <= 100); // sí modifica
         *         Cuidado con efectos secundarios
         *         Las lambdas dentro de streams deben ser puras: no deben modificar variables externas:
         *         javaList<String> resultado = new ArrayList<>();
         * 
         * // ❌ MAL - modificar lista externa desde un stream
         *         productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .forEach(p -> resultado.add(p.getNombre()));
         * 
         * // ✅ BIEN - usar toList o collect
         *         List<String> resultado2 = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .map(Producto::getNombre)
         *                 .toList();
         *         El primer caso funciona en streams secuenciales, pero falla en streams paralelos y va contra el espíritu de la programación funcional.
         *                 Streams paralelos: existen pero usa con cuidado
         *         Puedes paralelizar un stream con .parallelStream():
         *         javaList<String> resultado = productos.parallelStream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .map(Producto::getNombre)
         *                 .toList();
         *         Java reparte el trabajo en varios hilos. Suena genial pero no siempre es más rápido. Solo úsalo cuando:
         * 
         *         Tienes muchos elementos (miles o más)
         *         Las operaciones son costosas (no triviales como un simple getNombre)
         *         El orden no importa o los puedes reordenar después
         * 
         *         En Spring Boot el procesamiento paralelo se maneja de otras formas más controladas. No uses parallelStream a la ligera.
         * 
         *         Parte 12 — Conexión profunda con Spring Boot
         *         En Spring Boot vas a usar Streams todo el tiempo. Algunos casos reales:
         *         Mapear entidades a DTOs
         *         java@GetMapping
         *         public List<ProductoResponse> obtenerTodos() {
         *             return repositorio.findAll().stream()
         *                     .map(p -> new ProductoResponse(p.getId(), p.getNombre(), p.getPrecio()))
         *                     .toList();
         *         }
         *         Filtrar resultados de consulta
         *         javapublic List<Producto> obtenerActivosConStock() {
         *             return repositorio.findAll().stream()
         *                     .filter(Producto::isActivo)
         *                     .filter(p -> p.getStock() > 0)
         *                     .toList();
         *         }
         *         Estadísticas por grupo
         *         javapublic Map<String, Double> totalPorCategoria() {
         *             return repositorio.findAll().stream()
         *                     .collect(Collectors.groupingBy(
         *                             Producto::getCategoria,
         *                             Collectors.summingDouble(Producto::getPrecio)
         *                     ));
         *         }
         *         Validaciones masivas
         *         javapublic boolean todosTienenStock(List<Long> ids) {
         *             return repositorio.findAllById(ids).stream()
         *                     .allMatch(p -> p.getStock() > 0);
         *         }
         *         Procesar y enviar notificaciones
         *         javapublic void notificarBajoStock() {
         *             repositorio.findAll().stream()
         *                     .filter(p -> p.getStock() < 5)
         *                     .forEach(p -> servicioNotificacion.enviar(p));
         *         }
         *         Cada uno de estos patrones lo verás constantemente en proyectos reales. Por eso Stream API es imprescindible para Spring Boot.
         * 
         *                 Resumen completo del Tema 5
         *         STREAM API   →  forma moderna de procesar colecciones
         *         describe QUÉ hacer, no CÓMO hacerlo
         * 
         *         Estructura básica:
         *         coleccion.stream()           ← creación
         *                 .operacionIntermedia()    ← retorna otro Stream
         *                 .operacionIntermedia()    ← se pueden encadenar
         *                 .operacionTerminal();     ← retorna el resultado final
         * 
         *         Streams son LAZY:
         *         Las operaciones intermedias NO se ejecutan
         *         hasta que llega una operación terminal
         * 
         *         Streams se usan UNA SOLA VEZ:
         *         Después de una terminal, el stream se cierra
         * 
         *         Streams NO modifican la colección original:
         *         Siempre retornan nuevos resultados
         * 
         *         OPERACIONES INTERMEDIAS (retornan Stream):
         *         filter(Predicate)       →  filtrar por condición
         *         map(Function)           →  transformar cada elemento
         *         flatMap(Function)       →  transformar y aplanar
         *         sorted()                →  ordenar (natural o con Comparator)
         *         distinct()              →  eliminar duplicados
         *         limit(N)                →  quedarse con los primeros N
         *         skip(N)                 →  saltar los primeros N
         *         peek(Consumer)          →  espiar sin modificar
         * 
         *         OPERACIONES TERMINALES (cierran el stream):
         *         toList()                →  convertir a List (la más usada)
         *         count()                 →  contar elementos
         *         forEach(Consumer)       →  ejecutar acción por cada uno
         *         findFirst()             →  obtener el primero (Optional)
         *         findAny()               →  cualquiera (Optional)
         *         anyMatch(Predicate)     →  ¿alguno cumple?
         *         allMatch(Predicate)     →  ¿todos cumplen?
         *         noneMatch(Predicate)    →  ¿ninguno cumple?
         *         min(Comparator)         →  el menor (Optional)
         *                 max(Comparator)         →  el mayor (Optional)
         *                 reduce(BinaryOperator)  →  combinar todos en uno
         *         collect(Collector)      →  agrupar con Collectors
         * 
         *         STREAMS PRIMITIVOS:
         *         IntStream, LongStream, DoubleStream
         *         Tienen sum(), average(), min(), max() directos
         *         Se obtienen con mapToInt, mapToLong, mapToDouble
         *         También IntStream.range() para crear rangos
         * 
         *         COLLECTORS (clase Collectors):
         *         toList()                →  convertir a List
         *         toSet()                 →  convertir a Set
         *         toMap(K, V)             →  convertir a Map
         *         groupingBy(K)           →  agrupar por criterio
         *         partitioningBy(P)       →  dividir en dos grupos true/false
         *         joining(separador)      →  concatenar Strings
         *         counting()              →  contar
         *         summingInt/Double()     →  sumar
         *         averagingInt/Double()   →  promediar
         *         mapping(F, downstream)  →  transformar antes de recolectar
         * 
         *         REGLAS:
         *    ✅ Un stream se usa UNA sola vez
         *    ✅ Los streams NO modifican la colección original
         *    ✅ Las operaciones intermedias son LAZY
         *    ✅ Evita efectos secundarios en lambdas de streams
         *    ⚠️ Usa parallelStream con cuidado
         *    ⚠️ Java 16+: usa .toList() en vez de Collectors.toList()
         */
    }

    record Producto(String nombre, double precio, int stock) {
    }
}
