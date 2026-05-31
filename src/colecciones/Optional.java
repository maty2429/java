package colecciones;

public class Optional {
    public static void main(String[] args) {
        // -------------------------------------------------------------------------
        // DEMO EJECUTABLE
        // -------------------------------------------------------------------------
        System.out.println("Tema: Optional");

        java.util.Optional<Producto> encontrado = buscarPorId(1L);
        java.util.Optional<Producto> noEncontrado = buscarPorId(99L);

        encontrado.ifPresent(producto -> System.out.println("Encontrado: " + producto.nombre()));

        Producto productoDefault = noEncontrado.orElse(new Producto("Producto default", 0.0));
        System.out.println("Cuando no existe: " + productoDefault.nombre());


        // -------------------------------------------------------------------------
        // APUNTE ORIGINAL CONSERVADO
        // -------------------------------------------------------------------------
        /*
         * Todo lo que escribiste queda guardado aquí como comentario de estudio.
         * La idea es que el archivo compile y se pueda ejecutar, sin borrar tus notas.
         * -----------------------------------------------------------------------------
         *         Optional — Para evitar NullPointerException en servicios
         *         Parte 1 — El problema antes de la solución
         *         Hay un error que es el más famoso de Java. Ha causado más bugs en producción que cualquier otro en la historia del lenguaje. Se llama NullPointerException.
         *                 El creador del concepto de null, Tony Hoare, llegó a llamarlo "mi error de mil millones de dólares" porque calculó cuánto dinero ha costado al mundo en bugs.
         *         Mira el problema. Imagina que tienes un método que busca un producto:
         *         javapublic Producto buscarPorId(Long id) {
         *             if (id == 1L) {
         *                 return new Producto("Laptop", 1500.0);
         *             }
         *             return null; // si no existe retorna null
         *         }
         *         Y lo usas así:
         *         javaProducto p = buscarPorId(999L);
         *         System.out.println(p.getNombre()); // 💥 NullPointerException
         *         El programa explota porque p es null y no puedes llamar a getNombre() en null. Y lo peor: Java no te avisa. No te obliga a verificar si es null. El error aparece en runtime, sorprendiéndote.
         *         Para evitarlo tendrías que verificar manualmente cada vez:
         *         javaProducto p = buscarPorId(999L);
         *         if (p != null) {
         *             System.out.println(p.getNombre());
         *         } else {
         *             System.out.println("No encontrado");
         *         }
         *         Pero es muy fácil olvidar esa verificación. Y si trabajas con muchos métodos que retornan null, tu código se llena de if (x != null) por todas partes. Es horrible.
         *         Java necesitaba una forma de decir explícitamente "este valor puede o no existir" y forzarte a manejar ambos casos. Para eso existe Optional.
         * 
         *         Parte 2 — Qué es Optional
         *         Optional<T> es una caja que puede contener un valor de tipo T, o estar vacía. En vez de retornar null cuando algo no existe, retornas un Optional vacío. Y para obtener el valor adentro, Java te obliga a manejar el caso de que esté vacío.
         *         Una analogía perfecta. Piensa en una caja de regalo. Cuando alguien te entrega una caja, no sabes si tiene algo adentro o está vacía. No puedes asumir que tiene algo. Tienes que abrirla con cuidado y verificar.
         *                 Optional es exactamente eso. Es una caja que sabes que puede estar vacía. El lenguaje te obliga a tratarla con respeto.
         *         Importa así:
         *         javaimport java.util.Optional;
         * 
         *         Parte 3 — Crear Optionals
         *         Hay tres formas principales de crear un Optional:
         *         Optional.of — cuando estás seguro que NO es null
         *         javaOptional<String> nombre = Optional.of("Carlos");
         *         Si le pasas null a Optional.of, lanza NullPointerException inmediatamente:
         *         javaOptional<String> mal = Optional.of(null); // 💥 NullPointerException
         *         Usa Optional.of cuando estás 100% seguro que el valor no es null. Si tienes dudas, usa la siguiente forma.
         *                 Optional.ofNullable — cuando puede ser null
         *         javaString posibleNombre = obtenerNombre(); // puede retornar null
         * 
         *         Optional<String> nombre = Optional.ofNullable(posibleNombre);
         *         ofNullable acepta null sin reclamar. Si recibe null, crea un Optional vacío. Si recibe un valor, crea un Optional con ese valor adentro.
         *         Es la forma más segura y la que más vas a usar al envolver valores externos.
         *         Optional.empty — Optional explícitamente vacío
         *         javaOptional<String> vacio = Optional.empty();
         *         Lo usas cuando quieres retornar explícitamente "no hay nada". Por ejemplo en un método de búsqueda:
         *         javapublic Optional<Producto> buscarPorId(Long id) {
         *             if (id == 1L) {
         *                 return Optional.of(new Producto("Laptop", 1500.0));
         *             }
         *             return Optional.empty(); // explícito: no hay producto
         *         }
         *         Mira cómo cambió la firma del método. Antes retornaba Producto (que podía ser null). Ahora retorna Optional<Producto>, que es honesto: "puede haber un producto, o puede que no". El que llame al método tiene que manejar ambos casos.
         * 
         *                 Parte 4 — Verificar si tiene valor
         *         La forma más básica es preguntarle al Optional si tiene algo adentro:
         *         isPresent — ¿tiene valor?
         *         javaOptional<Producto> resultado = buscarPorId(1L);
         * 
         *         if (resultado.isPresent()) {
         *             Producto p = resultado.get();
         *             System.out.println(p.getNombre());
         *         } else {
         *             System.out.println("No encontrado");
         *         }
         *         isPresent retorna true si hay valor, false si está vacío. Y get() extrae el valor adentro.
         *         isEmpty — ¿está vacío?
         *         javaOptional<Producto> resultado = buscarPorId(999L);
         * 
         *         if (resultado.isEmpty()) {
         *             System.out.println("No encontrado");
         *         }
         *         isEmpty (Java 11+) es lo contrario de isPresent. A veces leer "está vacío" es más natural que "no está presente".
         *                 El método get tiene un problema
         *         Mira esto. get() extrae el valor del Optional, pero si está vacío lanza NoSuchElementException:
         *         javaOptional<Producto> vacio = Optional.empty();
         *         Producto p = vacio.get(); // 💥 NoSuchElementException
         *         Por eso usar get() directamente sin verificar antes con isPresent() es antipatrón. Da igual usar null o Optional.get() sin verificar, los dos te explotan.
         *                 Por suerte, Optional tiene métodos mucho mejores que no tienen este problema. Vamos a verlos.
         * 
         *                 Parte 5 — Los métodos modernos de Optional (los que sí debes usar)
         *         Aquí está la verdadera utilidad de Optional. No es solo "envolver un null". Es tener métodos elegantes para manejar valores que pueden no existir, sin if-else manual.
         *                 orElse — valor por defecto
         *         javaOptional<Producto> resultado = buscarPorId(999L);
         * 
         *         Producto p = resultado.orElse(new Producto("Default", 0));
         *         System.out.println(p.getNombre()); // "Default"
         *         orElse retorna el valor del Optional si lo tiene, o el valor que le pases como argumento si está vacío. Una línea, cero if-else.
         *         orElseGet — valor por defecto con Supplier
         *         javaOptional<Producto> resultado = buscarPorId(999L);
         * 
         *         Producto p = resultado.orElseGet(() -> new Producto("Default", 0));
         *         Casi lo mismo que orElse, pero recibe un Supplier (recuerda el tema 4). La diferencia clave: el Supplier solo se ejecuta si el Optional está vacío.
         * ¿Por qué importa esto? Mira:
         *         javaOptional<Producto> resultado = buscarPorId(1L); // SÍ tiene valor
         * 
         * // orElse - SIEMPRE crea el default aunque no se use
         *         Producto p1 = resultado.orElse(new Producto("Default", 0)); // se crea aunque no se necesite
         * 
         * // orElseGet - SOLO crea el default si está vacío
         *         Producto p2 = resultado.orElseGet(() -> new Producto("Default", 0)); // NO se crea
         *         Si crear el default es costoso (consultar base de datos, hacer un cálculo pesado, etc.), usa siempre orElseGet. Si es barato (un valor simple), da igual.
         *                 orElseThrow — lanzar excepción si está vacío
         *         javaOptional<Producto> resultado = buscarPorId(999L);
         * 
         *         Producto p = resultado.orElseThrow(
         *                 () -> new ProductoNoEncontradoException("ID 999 no existe")
         *         );
         *         Si el Optional tiene valor, lo retorna. Si está vacío, lanza la excepción que le pasas (mediante un Supplier).
         *                 Esta es la forma estándar en Spring Boot para manejar el caso "no se encontró el recurso":
         *         javapublic Producto obtenerPorId(Long id) {
         *             return repositorio.findById(id)
         *                     .orElseThrow(() -> new ProductoNoEncontradoException(id));
         *         }
         *         Limpio, expresivo, sin if-else. Y obligas al código a manejar el caso de no encontrarlo.
         *                 ifPresent — hacer algo si tiene valor
         *         javaOptional<Producto> resultado = buscarPorId(1L);
         * 
         *         resultado.ifPresent(p -> System.out.println("Encontrado: " + p.getNombre()));
         *         Recibe un Consumer. Si el Optional tiene valor, ejecuta la lambda con ese valor. Si está vacío, no hace nada.
         *         Es perfecto cuando solo te interesa el caso "sí existe" y no te importa el caso "no existe".
         *                 ifPresentOrElse — manejar ambos casos
         *         javaOptional<Producto> resultado = buscarPorId(999L);
         * 
         *         resultado.ifPresentOrElse(
         *                 p -> System.out.println("Encontrado: " + p.getNombre()),
         *                 () -> System.out.println("No encontrado")
         *         );
         *         Recibe dos lambdas. La primera se ejecuta si hay valor, la segunda si está vacío. Reemplaza completamente el if-else tradicional.
         * 
         *                 Parte 6 — Optional con operaciones tipo Stream
         *         Aquí es donde Optional se vuelve realmente poderoso. Tiene métodos similares a Stream API: puedes encadenar transformaciones y filtros.
         *                 map — transformar el valor (si existe)
         *         javaOptional<Producto> resultado = buscarPorId(1L);
         * 
         *         Optional<String> nombre = resultado.map(p -> p.getNombre());
         *         Si el Optional tiene valor, aplica la lambda y devuelve un nuevo Optional con el resultado. Si está vacío, devuelve un Optional vacío sin ejecutar la lambda.
         *                 Esto es elegante porque puedes encadenar:
         *         javaOptional<String> nombreEnMayus = buscarPorId(1L)
         *                 .map(p -> p.getNombre())
         *                 .map(String::toUpperCase);
         * 
         *         System.out.println(nombreEnMayus.orElse("Sin nombre"));
         *         Si en cualquier paso el Optional está vacío, los map siguientes simplemente no se ejecutan. No tienes que verificar null en cada paso.
         *                 Compara con código sin Optional:
         *         java// Sin Optional - verificaciones manuales en cada paso
         *         Producto p = buscarPorId(1L);
         *         String nombre = null;
         *         if (p != null) {
         *             nombre = p.getNombre();
         *             if (nombre != null) {
         *                 nombre = nombre.toUpperCase();
         *             }
         *         }
         *         String resultado = nombre != null ? nombre : "Sin nombre";
         *         Horrible. Con Optional es una sola cadena clara.
         *                 filter — filtrar el valor (si existe)
         *         javaOptional<Producto> resultado = buscarPorId(1L)
         *                 .filter(p -> p.getPrecio() > 100);
         *         Si el Optional tiene valor y cumple el Predicate, lo deja pasar. Si no cumple o está vacío, retorna Optional vacío.
         *         Útil para validaciones encadenadas:
         *         javaOptional<String> nombreValido = buscarPorId(1L)
         *                 .filter(p -> p.isActivo())
         *                 .filter(p -> p.getStock() > 0)
         *                 .map(Producto::getNombre);
         *         Solo obtienes el nombre si el producto existe, está activo y tiene stock. Si falla cualquier paso, obtienes Optional vacío.
         *         flatMap — encadenar Optionals
         *         flatMap es como map pero cuando la transformación ya retorna un Optional. Sin flatMap te quedarías con Optionals dentro de Optionals.
         *                 Imagina que tienes:
         *         javapublic class Usuario {
         *             private Optional<Direccion> direccionPrincipal;
         * 
         *             public Optional<Direccion> getDireccionPrincipal() {
         *                 return direccionPrincipal;
         *             }
         *         }
         *         Si usas map:
         *         javaOptional<Optional<Direccion>> raro = buscarUsuario(1L)
         *                 .map(u -> u.getDireccionPrincipal());
         * // Optional<Optional<Direccion>> - ¡aplastado dentro de otro!
         *         Con flatMap se aplana:
         *         javaOptional<Direccion> mejor = buscarUsuario(1L)
         *                 .flatMap(u -> u.getDireccionPrincipal());
         *         Regla simple:
         *         map      →  cuando la transformación retorna T
         *         flatMap  →  cuando la transformación retorna Optional<T>
         * 
         *         Parte 7 — Ejemplo integrado del poder de Optional
         *         Mira la diferencia abismal entre código sin Optional y con Optional.
         *                 Imagina este escenario: buscar un usuario, obtener su dirección principal, obtener el código postal y validar que sea válido. Si en cualquier paso algo falla, retornar "Desconocido".
         *                 Versión sin Optional (estilo Java antiguo)
         *         javapublic String obtenerCodigoPostal(Long idUsuario) {
         *             Usuario u = buscarUsuario(idUsuario);
         *             if (u == null) {
         *                 return "Desconocido";
         *             }
         * 
         *             Direccion d = u.getDireccionPrincipal();
         *             if (d == null) {
         *                 return "Desconocido";
         *             }
         * 
         *             String cp = d.getCodigoPostal();
         *             if (cp == null || cp.isBlank()) {
         *                 return "Desconocido";
         *             }
         * 
         *             return cp;
         *         }
         *         12 líneas, lleno de verificaciones, fácil de olvidar una.
         *                 Versión con Optional
         *         javapublic String obtenerCodigoPostal(Long idUsuario) {
         *             return buscarUsuario(idUsuario)
         *                     .flatMap(Usuario::getDireccionPrincipal)
         *                     .map(Direccion::getCodigoPostal)
         *                     .filter(cp -> !cp.isBlank())
         *                     .orElse("Desconocido");
         *         }
         *         5 líneas, sin un solo if, imposible olvidar manejar el caso vacío. Y se lee casi como inglés: "busca el usuario, obtén su dirección principal, obtén el código postal, fíltralo si no está vacío, o usa 'Desconocido'".
         *                 Esa es la verdadera utilidad de Optional. No es solo evitar null, es escribir código más declarativo y seguro.
         * 
         *         Parte 8 — Optional como retorno: la regla de oro
         *         Hay una regla importantísima sobre cómo usar Optional bien:
         *         Usa Optional como TIPO DE RETORNO de métodos que pueden no encontrar resultado
         *         NO uses Optional como atributo de clase
         *         NO uses Optional como parámetro de método
         *         Mira ejemplos buenos:
         *         java// ✅ Correcto - retorno de método
         *         public Optional<Producto> buscarPorId(Long id) { ... }
         * 
         *         public Optional<Usuario> buscarPorEmail(String email) { ... }
         * 
         *         public Optional<String> obtenerCodigoSeguro() { ... }
         *         Y ejemplos malos:
         *         java// ❌ Mal - como atributo
         *         public class Producto {
         *             private Optional<String> descripcion; // ❌ NO hagas esto
         *         }
         * 
         * // ❌ Mal - como parámetro
         *         public void procesar(Optional<Producto> producto) { ... } // ❌ NO hagas esto
         * ¿Por qué? Optional fue diseñado específicamente para representar "este método puede no encontrar resultado". Para atributos, usa simplemente el tipo con la posibilidad de null. Para parámetros, sobrecarga métodos o usa null explícito (aunque mejor diseña tu API para no tener parámetros opcionales).
         *                 Esto es una convención fuerte en la comunidad Java y Spring Boot. Síguela.
         * 
         *                 Parte 9 — Optional con Stream API
         *         Optional y Stream se combinan naturalmente. Algunos métodos de Stream retornan Optional:
         *         javaList<Producto> productos = ...;
         * 
         * // findFirst retorna Optional
         *         Optional<Producto> primero = productos.stream()
         *                 .filter(p -> p.getPrecio() > 100)
         *                 .findFirst();
         * 
         *         primero.ifPresent(p -> System.out.println(p.getNombre()));
         *         java// max y min retornan Optional
         *         Optional<Producto> masCaro = productos.stream()
         *                 .max(Comparator.comparingDouble(Producto::getPrecio));
         * 
         *         String nombre = masCaro
         *                 .map(Producto::getNombre)
         *                 .orElse("No hay productos");
         *         Y al revés, puedes convertir un Optional a un Stream:
         *         javaOptional<Producto> resultado = buscarPorId(1L);
         * 
         *         Stream<Producto> stream = resultado.stream();
         * // stream con un elemento si tiene valor, o vacío si no
         *         Esto es útil para combinar varios Optionals en un Stream:
         *         javaList<Optional<Producto>> resultados = List.of(
         *                 buscarPorId(1L),
         *                 buscarPorId(2L),
         *                 buscarPorId(3L)
         *         );
         * 
         *         List<Producto> encontrados = resultados.stream()
         *                 .flatMap(Optional::stream)
         *                 .toList();
         *         Optional::stream convierte cada Optional en un stream de 0 o 1 elementos. El flatMap aplana todo, quedándote solo con los que tenían valor.
         * 
         *         Parte 10 — Lo que NO debes hacer con Optional
         *         Hay anti-patrones comunes. Te los muestro para que los identifiques y los evites.
         *         NO uses isPresent + get
         *         java// ❌ MAL - vuelves al estilo if-null
         *         if (resultado.isPresent()) {
         *             Producto p = resultado.get();
         *             System.out.println(p.getNombre());
         *         }
         * 
         * // ✅ BIEN - usa ifPresent
         *         resultado.ifPresent(p -> System.out.println(p.getNombre()));
         *         Usar isPresent + get es básicamente regresar al estilo de verificar null. Pierde toda la elegancia de Optional. Usa los métodos que toman lambdas.
         *         NO uses Optional.get sin verificar
         *                 java// ❌ MAL
         *         Producto p = resultado.get(); // puede explotar
         * 
         * // ✅ BIEN
         *         Producto p = resultado.orElseThrow(() -> new RuntimeException("No existe"));
         *         get sin verificación es tan malo como usar null sin verificar. Usa orElse, orElseGet o orElseThrow.
         *         NO retornes null en un método que devuelve Optional
         *         java// ❌ MAL - mezclar null con Optional
         *         public Optional<Producto> buscar(Long id) {
         *             if (idNoExiste) {
         *                 return null; // ❌ NUNCA hagas esto
         *             }
         *             return Optional.of(producto);
         *         }
         * 
         * // ✅ BIEN
         *         public Optional<Producto> buscar(Long id) {
         *             if (idNoExiste) {
         *                 return Optional.empty(); // ✅
         *             }
         *             return Optional.of(producto);
         *         }
         *         Si tu método retorna Optional, nunca retornes null. Sería contradictorio y peligrosísimo.
         *                 NO uses Optional para todo
         *         Optional es para valores que pueden no existir de forma significativa. No lo uses solo porque queda bonito.
         *                 java// ❌ MAL - no tiene sentido
         *         public Optional<Integer> sumar(int a, int b) {
         *             return Optional.of(a + b);
         *         }
         * 
         * // ✅ BIEN - una suma siempre da un resultado
         *         public int sumar(int a, int b) {
         *             return a + b;
         *         }
         *         Optional tiene un pequeño costo de rendimiento (es un objeto envoltorio). Solo úsalo cuando la ausencia es una posibilidad real.
         * 
         *         Parte 11 — Optional con tipos primitivos
         *         Como con Streams, hay versiones primitivas de Optional para evitar el costo de envolver/desenvolver:
         *         javaOptionalInt    →  Optional<Integer>
         *         OptionalLong   →  Optional<Long>
         *         OptionalDouble →  Optional<Double>
         *         Se usan con streams primitivos:
         *         javaList<Producto> productos = ...;
         * 
         *         OptionalDouble promedio = productos.stream()
         *                 .mapToDouble(Producto::getPrecio)
         *                 .average();
         * 
         *         double valor = promedio.orElse(0.0);
         *         En la práctica casi siempre usarás Optional<T> normal. Las versiones primitivas son específicas para optimización.
         * 
         *                 Parte 12 — Conexión profunda con Spring Boot
         *         Optional es omnipresente en Spring Boot. Te muestro los casos típicos:
         *         Spring Data Repository
         *         Todos los métodos findById de Spring Data retornan Optional:
         *         javapublic interface ProductoRepository extends JpaRepository<Producto, Long> {
         *             // findById ya viene definido y retorna Optional<Producto>
         *         }
         * 
         * // Uso típico
         *         Optional<Producto> resultado = repositorio.findById(1L);
         * 
         *         Producto p = resultado.orElseThrow(
         *                 () -> new ProductoNoEncontradoException(1L)
         *         );
         *         Spring Data te fuerza a manejar el caso "no existe". No hay forma de saltarse esto.
         *                 Métodos de búsqueda personalizados
         *         Tus repositorios deberían retornar Optional cuando puede no encontrar:
         *         javapublic interface UsuarioRepository extends JpaRepository<Usuario, Long> {
         *             Optional<Usuario> findByEmail(String email);
         *         }
         *         Spring Data automáticamente genera el método y devuelve Optional vacío si no encuentra.
         *                 Servicios en capas
         *         java@Service
         *         public class ProductoService {
         * 
         *             private final ProductoRepository repositorio;
         * 
         *             public ProductoResponse obtenerPorId(Long id) {
         *                 return repositorio.findById(id)
         *                         .map(p -> new ProductoResponse(p.getId(), p.getNombre(), p.getPrecio()))
         *                         .orElseThrow(() -> new ProductoNoEncontradoException(id));
         *             }
         * 
         *             public List<ProductoResponse> obtenerActivos() {
         *                 return repositorio.findAll().stream()
         *                         .filter(Producto::isActivo)
         *                         .map(p -> new ProductoResponse(p.getId(), p.getNombre(), p.getPrecio()))
         *                         .toList();
         *             }
         *         }
         *         Mira cómo Optional se combina con Stream para hacer código declarativo, sin if-else.
         *         Manejo de errores en controllers
         *         java@RestController
         *         public class ProductoController {
         * 
         *             @GetMapping("/productos/{id}")
         *             public ProductoResponse obtener(@PathVariable Long id) {
         *                 return servicio.obtenerPorId(id);
         *                 // si lanza ProductoNoEncontradoException
         *                 // Spring la traduce automáticamente a 404 Not Found
         *             }
         *         }
         *         Optional + excepciones personalizadas = manejo elegante de errores en APIs REST.
         * 
         *         Resumen completo del Tema 6
         *         OPTIONAL<T>   →  caja que puede contener un valor de T o estar vacía
         *         reemplaza el uso de null en retornos de métodos
         * 
         *         CREAR OPTIONALS:
         *         Optional.of(valor)         →  si estás SEGURO que no es null
         *         Optional.ofNullable(valor) →  si puede ser null (más seguro)
         *         Optional.empty()           →  explícitamente vacío
         * 
         *         VERIFICAR:
         *         isPresent()   →  true si tiene valor
         *         isEmpty()     →  true si está vacío (Java 11+)
         * 
         *         EXTRAER VALOR:
         *         get()                          →  EVITAR, lanza excepción si vacío
         *         orElse(valor)                  →  retorna valor o el default
         *         orElseGet(Supplier)            →  retorna valor o ejecuta Supplier
         *         orElseThrow(Supplier)          →  retorna valor o lanza excepción
         * 
         *         EJECUTAR ACCIONES:
         *         ifPresent(Consumer)            →  ejecuta si tiene valor
         *         ifPresentOrElse(C, Runnable)   →  maneja ambos casos
         * 
         *         OPERACIONES TIPO STREAM:
         *         map(Function)        →  transforma el valor si existe
         *         filter(Predicate)    →  conserva valor si cumple condición
         *         flatMap(Function)    →  cuando la función retorna Optional
         * 
         *         VERSIONES PRIMITIVAS:
         *         OptionalInt, OptionalLong, OptionalDouble
         *         Para evitar boxing/unboxing
         * 
         *         REGLA DE ORO:
         *    ✅ Optional como RETORNO de métodos
         *    ❌ NO como atributo de clase
         *    ❌ NO como parámetro de método
         * 
         *         ANTIPATRONES (NO HACER):
         *    ❌ isPresent + get manual (usa ifPresent o orElse)
         *    ❌ get() sin verificar
         *    ❌ Retornar null en método que retorna Optional
         *    ❌ Optional para valores que SIEMPRE existen
         *    ❌ Optional como atributos o parámetros
         * 
         *         UTILIDAD REAL:
         *    ✅ Eliminar NullPointerException
         *    ✅ Forzar al código a manejar el caso "no existe"
         *    ✅ Código más declarativo, sin if-else
         *    ✅ Encadenamiento de transformaciones seguras
         *    ✅ APIs más expresivas y autodocumentadas
         * 
         *         EN SPRING BOOT:
         *         repositorio.findById(id)  →  retorna Optional<T>
         *                 Métodos custom de búsqueda retornan Optional
         *         Combinación con Stream API para servicios elegantes
         *                 .orElseThrow() es el patrón estándar para 404
         */
    }

    static java.util.Optional<Producto> buscarPorId(Long id) {
        if (id == 1L) {
            return java.util.Optional.of(new Producto("Laptop", 1500.0));
        }
        return java.util.Optional.empty();
    }

    record Producto(String nombre, double precio) {
    }
}
