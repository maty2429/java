package colecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sequenced {
    public static void main(String[] args) {
        // -------------------------------------------------------------------------
        // DEMO EJECUTABLE
        // -------------------------------------------------------------------------
        System.out.println("Tema: Sequenced Collections (notas Java 21, demo compatible con Java 17)");

        List<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Bruno"));
        String primero = nombres.get(0);
        String ultimo = nombres.get(nombres.size() - 1);
        System.out.println("Primero estilo Java 17: " + primero);
        System.out.println("Ultimo estilo Java 17: " + ultimo);

        List<String> invertidos = new ArrayList<>(nombres);
        Collections.reverse(invertidos);
        System.out.println("Vista invertida simulada con copia: " + invertidos);

        Map<String, Integer> edades = new LinkedHashMap<>();
        edades.put("Carlos", 25);
        edades.put("Ana", 30);
        System.out.println("LinkedHashMap mantiene orden de insercion: " + edades);


        // -------------------------------------------------------------------------
        // APUNTE ORIGINAL CONSERVADO
        // -------------------------------------------------------------------------
        /*
         * Todo lo que escribiste queda guardado aquí como comentario de estudio.
         * La idea es que el archivo compile y se pueda ejecutar, sin borrar tus notas.
         * -----------------------------------------------------------------------------
         *         Sequenced Collections (Java 21)
         *         Parte 1 —El problema antes de la solución
         *         Antes de Java 21 las colecciones tenían un problema sutil pero molesto:no había una forma consistente de
         *         acceder al primer y último elemento.Cada tipo de colección tenía sus propios métodos para hacer lo mismo, y
         *         algunas ni siquiera lo tenían.
         *                 Mira esta inconsistencia:
         *         java// Para List - usar get con índices
         *         List<String> lista = new ArrayList<>(List.of("A", "B", "C"));
         *         String primero = lista.get(0);
         *         String ultimo = lista.get(lista.size() - 1);
         * 
         * // Para Deque - tiene sus propios métodos
         *         Deque<String> deque = new ArrayDeque<>(List.of("A", "B", "C"));
         *         String primero = deque.getFirst();
         *         String ultimo = deque.getLast();
         * 
         * // Para LinkedHashSet - no había forma directa
         *         LinkedHashSet<String> set = new LinkedHashSet<>(List.of("A", "B", "C"));
         *         String primero = set.iterator().next(); // feo
         *         String ultimo = ??? // ni siquiera había forma fácil
         * 
         * // Para LinkedHashMap - igual de raro
         *         LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
         *         map.put("A", 1);
         *         map.put("B", 2);
         * // ¿Primera entrada? complicadísimo
         *         Cada colección hablaba su propio idioma.El programador tenía que recordar la sintaxis específica de cada
         *         tipo.Y para LinkedHashSet y LinkedHashMap, que sí mantienen orden de inserción, no había métodos limpios para
         *         acceder al primer y último.
         *                 También otro problema:invertir el orden de una colección.Para List existía Collections.reverse()
         *         pero modifica la lista original.No había forma elegante de obtener una vista invertida sin copiar.
         *                 Java 21 resolvió todo esto introduciendo las Sequenced Collections(JEP 431).
         * 
         *                 Parte 2 —Qué son las Sequenced Collections
         *         Sequenced Collections son tres nuevas interfaces que unifican la API para colecciones con orden definido:
         *         SequencedCollection<E>   →para colecciones con orden (List, Deque)
         *         SequencedSet<E>          →para sets con orden (LinkedHashSet, SortedSet)
         *         SequencedMap<K, V>       →para maps con orden (LinkedHashMap, SortedMap)
         *         Estas interfaces agregan métodos comunes a todas las colecciones con orden:
         *         getFirst()      →obtener el primer elemento
         *         getLast()       →obtener el último elemento
         *         addFirst(e)     →agregar al inicio
         *         addLast(e)      →agregar al final
         *         removeFirst()   →eliminar el primero
         *         removeLast()    →eliminar el último
         *         reversed()      →obtener una vista invertida
         *         Ahora todas las colecciones con orden hablan el mismo idioma.Sin tener que recordar sintaxis específicas.
         * 
         *         Parte 3 —La jerarquía completa
         *         Java 21 reorganizó internamente la jerarquía de colecciones para integrar estas nuevas interfaces.Visualmente:
         *         Collection
         *    └──SequencedCollection      ←NUEVA en Java 21
         *          ├──List
         *          │     ├──ArrayList
         *          │     └──LinkedList
         *          │
         *          ├──SequencedSet        ←NUEVA en Java 21
         *          │     ├──LinkedHashSet
         *          │     └──SortedSet
         *          │            └──TreeSet
         *          │
         *          └──Deque
         *                ├──ArrayDeque
         *                └──LinkedList
         * 
         *                 Map
         *    └──SequencedMap              ←NUEVA en Java 21
         *          ├──LinkedHashMap
         *          └──SortedMap
         *                └──TreeMap
         *         Todas las colecciones que ya tenían orden ahora implementan estas nuevas interfaces.Tu código existente sigue
         *         funcionando sin tocar nada, pero ahora tienes métodos nuevos disponibles.
         * 
         *                 Parte 4 —Los nuevos métodos en acción
         *         Vamos a ver los métodos uno por uno con ejemplos prácticos.
         *                 getFirst y getLast
         *         javaList<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Bruno", "Diana"));
         * 
         *         String primero = nombres.getFirst();  // "Carlos"
         *         String ultimo = nombres.getLast();    // "Diana"
         *         Antes:
         *         javaString primero = nombres.get(0);
         *         String ultimo = nombres.get(nombres.size() - 1); // verboso y propenso a errores
         *         Ahora con getFirst y getLast es expresivo y consistente para cualquier tipo de colección con orden.
         *         Funciona igual para LinkedHashSet:
         *         javaLinkedHashSet<String> set = new LinkedHashSet<>(List.of("A", "B", "C"));
         * 
         *         String primero = set.getFirst(); // "A"
         *         String ultimo = set.getLast();   // "C"
         *         Y para TreeSet(que ordena alfabéticamente):
         *         javaTreeSet<String> tree = new TreeSet<>(List.of("Bruno", "Carlos", "Ana"));
         * 
         *         String primero = tree.getFirst(); // "Ana" (el menor en orden alfabético)
         *         String ultimo = tree.getLast();   // "Carlos" (el mayor)
         *         Mismo método, comportamiento consistente según el orden de cada colección.
         *                 Importante:comportamiento con colección vacía
         *         Si la colección está vacía, getFirst y getLast lanzan NoSuchElementException:
         *         javaList<String> vacia = new ArrayList<>();
         *         String x = vacia.getFirst(); // 💥 NoSuchElementException
         *         Por eso es buena práctica verificar antes:
         *         javaif(!lista.isEmpty()) {
         *             String primero = lista.getFirst();
         *         }
         *         addFirst y addLast
         *         Permiten agregar elementos al inicio o al final:
         *         javaList<String> nombres = new ArrayList<>(List.of("Ana", "Bruno"));
         * 
         *         nombres.addFirst("Carlos");
         *         nombres.addLast("Diana");
         * 
         *         System.out.println(nombres); // [Carlos, Ana, Bruno, Diana]
         *         Antes para agregar al inicio tenías que hacer:
         *         javanombres.add(0, "Carlos"); // verboso
         *         Ahora es directo y expresivo.
         *                 removeFirst y removeLast
         *         Eliminan y retornan el primer o último elemento:
         *         javaList<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Bruno", "Diana"));
         * 
         *         String quitado1 = nombres.removeFirst(); // "Carlos"
         *         String quitado2 = nombres.removeLast();  // "Diana"
         * 
         *         System.out.println(nombres); // [Ana, Bruno]
         *         Útil para usar listas como colas o pilas sin tener que recurrir a clases específicas.
         * 
         *                 Parte 5 —El método estrella:
         *         reversed
         *         reversed() es probablemente el método más interesante de Sequenced Collections.Retorna una vista invertida de la
         *         colección original.
         *         Lo importante:no es una copia.Es una vista.Si modificas la vista, se modifica la original.Y al revés.
         *                 javaList<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Bruno"));
         * 
         *         List<String> invertidos = nombres.reversed();
         * 
         *         System.out.println(nombres);     // [Carlos, Ana, Bruno]
         *         System.out.println(invertidos);  // [Bruno, Ana, Carlos]
         *         Como es una vista, cualquier cambio en cualquiera se refleja en ambos:
         *         javanombres.addLast("Diana");
         *         System.out.println(invertidos); // [Diana, Bruno, Ana, Carlos]
         *         Diana se agregó al final de la lista original, lo que en la vista invertida aparece al principio.
         *         Cuándo usar reversed
         *                 java// Iterar al revés sin modificar la lista original
         *         for (String nombre : nombres.reversed()) {
         *             System.out.println(nombre);
         *         }
         * 
         * // Combinar con Stream para procesar al revés
         *         nombres.reversed().stream()
         *                 .map(String::toUpperCase)
         *                 .toList();
         *         Antes para iterar al revés tenías que hacer:
         *         javafor( int i = nombres.size() - 1;
         *         i >= 0;
         *         i--){
         *             System.out.println(nombres.get(i));
         *         }
         *         Feo y propenso a errores con índices.Con reversed () es directo.
         *         reversed es lazy(sin copiar)
         *         Esto es clave.reversed() no crea una nueva colección.Solo crea una vista que reordena el acceso a los
         *         elementos originales.Esto significa:
         * ✅Es eficiente -no copia datos
         * ✅Refleja cambios en tiempo real
         * ⚠️La colección original sigue siendo la única fuente real
         *         Si quieres una copia invertida real, debes hacer una nueva colección:
         *         javaList<String> copiaInvertida = new ArrayList<>(nombres.reversed());
         * 
         *         Parte 6 —SequencedMap, la novedad más útil
         *         SequencedMap es lo que más estaba haciendo falta.Antes era una pesadilla acceder al primer o último par en un
         *         LinkedHashMap.
         *                 javaLinkedHashMap<String, Integer> edades = new LinkedHashMap<>();
         *         edades.put("Carlos", 25);
         *         edades.put("Ana", 30);
         *         edades.put("Bruno", 22);
         *         edades.put("Diana", 28);
         *         firstEntry y lastEntry
         *         javaMap.Entry<String, Integer> primero = edades.firstEntry();
         * // {Carlos=25}
         * 
         *         Map.Entry<String, Integer> ultimo = edades.lastEntry();
         * // {Diana=28}
         * 
         *         System.out.println(primero.getKey() + " tiene " + primero.getValue());
         * // "Carlos tiene 25"
         *         pollFirstEntry y pollLastEntry
         *         Como removeFirst y removeLast pero para Map.Eliminan y retornan la entrada:
         *         javaMap.Entry<String, Integer> quitado = edades.pollFirstEntry();
         *         System.out.println(quitado); // {Carlos=25}
         *         System.out.println(edades);   // {Ana=30, Bruno=22, Diana=28}
         *         putFirst y putLast
         *         Agregar entradas al inicio o final:
         *         javaedades.putFirst("Elena", 40);
         *         edades.putLast("Felipe", 35);
         * 
         *         System.out.println(edades);
         * // {Elena=40, Ana=30, Bruno=22, Diana=28, Felipe=35}
         *         sequencedKeySet, sequencedValues, sequencedEntrySet
         *         Las versiones ordenadas de las vistas tradicionales:
         *         javaSequencedSet<String> claves = edades.sequencedKeySet();
         *         SequencedCollection<Integer> valores = edades.sequencedValues();
         *         SequencedSet<Map.Entry<String, Integer>> entradas = edades.sequencedEntrySet();
         *         Cada uno tiene reversed (), getFirst(), getLast(), etc.
         *                 reversed para Map
         *         Igual que para colecciones:
         *         javaSequencedMap<String, Integer> invertido = edades.reversed();
         * 
         *         System.out.println(invertido);
         * // {Diana=28, Bruno=22, Ana=30, Carlos=25}
         *         Una vista invertida del mapa, sin copiar.
         * 
         *                 Parte 7 —Caso de uso realista:histórico de eventos
         *         Imagina que tienes un servicio que registra eventos con timestamp:
         *         javapublic class ServicioEventos {
         * 
         *             private LinkedHashMap<LocalDateTime, String> historico = new LinkedHashMap<>();
         * 
         *             public void registrar(String evento) {
         *                 historico.put(LocalDateTime.now(), evento);
         *             }
         * 
         *             // Obtener el último evento registrado
         *             public String ultimoEvento() {
         *                 if (historico.isEmpty()) {
         *                     return "Sin eventos";
         *                 }
         *                 return historico.lastEntry().getValue();
         *             }
         * 
         *             // Obtener los últimos 10 eventos en orden cronológico inverso
         *             public List<String> ultimos10() {
         *                 return historico.reversed().values().stream()
         *                         .limit(10)
         *                         .toList();
         *             }
         * 
         *             // Obtener el primer evento (más antiguo)
         *             public String primerEvento() {
         *                 if (historico.isEmpty()) {
         *                     return "Sin eventos";
         *                 }
         *                 return historico.firstEntry().getValue();
         *             }
         *         }
         *         Mira lo limpio que queda.Sin Sequenced Collections, este código sería mucho más verboso.
         * 
         *                 Parte 8 —Cuidado con List.of y Set.of inmutables
         *         Las colecciones inmutables creadas con List.of y Set.of también implementan Sequenced, pero no permiten
         *         modificación:
         *         javaList<String> inmutable = List.of("A", "B", "C");
         * 
         *         inmutable.getFirst();    // ✅ "A"
         *         inmutable.reversed();    // ✅ [C, B, A]
         * 
         *         inmutable.addFirst("Z"); // ❌ UnsupportedOperationException
         *         Los métodos de lectura funcionan.Los de modificación lanzan excepción porque la colección es inmutable.
         *         Es importante saberlo para no confundirte.
         * 
         *         Parte 9 —Aplicaciones prácticas en Spring Boot
         *         En Spring Boot estos métodos te ahorran código real:
         *         Paginación de resultados
         *         javapublic List<Producto > obtenerUltimosProductos( int cantidad){
         *             return repositorio.findAllOrdenadosPorFecha().reversed().stream()
         *                     .limit(cantidad)
         *                     .toList();
         *         }
         *         Obtienes los productos más recientes sin tener que invertir la lista o cambiar la consulta.
         *                 Cache LRU manual
         *         javapublic class CacheLRU<K, V> {
         *             private final LinkedHashMap<K, V> cache = new LinkedHashMap<>();
         *             private final int maxSize;
         * 
         *             public CacheLRU(int maxSize) {
         *                 this.maxSize = maxSize;
         *             }
         * 
         *             public V get(K key) {
         *                 V valor = cache.get(key);
         *                 if (valor != null) {
         *                     // Mover al final como el más reciente
         *                     cache.remove(key);
         *                     cache.putLast(key, valor);
         *                 }
         *                 return valor;
         *             }
         * 
         *             public void put(K key, V valor) {
         *                 if (cache.size() >= maxSize) {
         *                     cache.pollFirstEntry(); // eliminar el menos usado
         *                 }
         *                 cache.putLast(key, valor);
         *             }
         *         }
         *         Implementar caches de este tipo es muchísimo más limpio con SequencedMap.
         *                 Histórico ordenado de operaciones
         *         java
         *         @Service
         *         public class HistorialService {
         * 
         *             private LinkedHashMap<LocalDateTime, Operacion> historial = new LinkedHashMap<>();
         * 
         *             public Operacion ultimaOperacion() {
         *                 return historial.isEmpty() ? null : historial.lastEntry().getValue();
         *             }
         * 
         *             public List<Operacion> ultimasN(int n) {
         *                 return historial.reversed().values().stream()
         *                         .limit(n)
         *                         .toList();
         *             }
         *         }
         *         Procesar respuestas en orden inverso
         *         javapublic List<ProductoResponse > obtenerProductosMasRecientesPrimero() {
         *             return repositorio.findAll().stream()
         *                     .sorted(Comparator.comparing(Producto::getFechaCreacion))
         *                     .toList()
         *                     .reversed();
         *         }
         * 
         *         Parte 10 —Por qué importa esto en Spring Boot 3. x
         *         Spring Boot 3. x está construido sobre Java 17 mínimo y soporta plenamente Java 21. Si estás trabajando con Java
         *         21 (que es lo recomendado para proyectos nuevos):
         * ✅Spring Data devolverá colecciones que ya implementan Sequenced
         * ✅Tu código podrá usar getFirst, getLast, reversed directamente
         * ✅Streams + Sequenced Collections es una combinación poderosa
         * ✅Menos código boilerplate en servicios
         *         Es un cambio pequeño en el lenguaje pero que va a estar en todo el código moderno que escribas.
         * 
         *                 Parte 11 —Comparación:
         *         antes y después
         *         Una tabla para que veas el contraste:
         *         OPERACIÓN Antes Java 21 Java 21 +
         * ─────────────────────────  ────────────────────────  ──────────────────
         *         Primer elemento de List lista.get(0) lista.getFirst()
         *         Último elemento de List lista.get(size() - 1) lista.getLast()
         *         Primer LinkedHashSet set.iterator().next() set.getFirst()
         *         Último LinkedHashSet complicado set.getLast()
         *         Primer LinkedHashMap map.entrySet()
         *                 .iterator().next() map.firstEntry()
         *         Invertir List Collections.reverse() lista.reversed()
         *         (modifica original)(vista lazy)
         *         Agregar al inicio lista.add(0, x) lista.addFirst(x)
         *         Quitar último lista.remove(size() - 1) lista.removeLast()
         *         Verás la diferencia es cuestión de elegancia y consistencia, no de capacidades nuevas.Pero esa elegancia importa
         *         cuando lees y mantienes código.
         * 
         *                 Resumen completo del Tema 7
         *         SEQUENCED COLLECTIONS (Java 21, JEP 431)
         *         Tres nuevas interfaces que unifican APIs de colecciones con orden
         * 
         *         INTERFACES NUEVAS:
         *         SequencedCollection<E>    →List, Deque
         *         SequencedSet<E>           →LinkedHashSet, SortedSet(TreeSet)
         *         SequencedMap<K, V>        →LinkedHashMap, SortedMap(TreeMap)
         * 
         *         MÉTODOS COMUNES PARA COLECCIONES:
         *         getFirst()           →primer elemento
         *         getLast()            →último elemento
         *         addFirst(e)          →agregar al inicio
         *         addLast(e)           →agregar al final
         *         removeFirst()        →eliminar y retornar el primero
         *         removeLast()         →eliminar y retornar el último
         *         reversed()           →vista invertida (NO copia)
         * 
         *         MÉTODOS ESPECÍFICOS DE SEQUENCED MAP:
         *         firstEntry()             →primera entrada
         *         lastEntry()              →última entrada
         *         pollFirstEntry()         →quitar primera entrada
         *         pollLastEntry()          →quitar última entrada
         *         putFirst(k, v)           →agregar al inicio
         *         putLast(k, v)            →agregar al final
         *         sequencedKeySet()        →vista ordenada de claves
         *         sequencedValues()        →vista ordenada de valores
         *         sequencedEntrySet()      →vista ordenada de entradas
         *         reversed()               →vista invertida del map
         * 
         *         COMPORTAMIENTO:
         *    ✅Colección vacía +getFirst / getLast lanza NoSuchElementException
         *    ✅reversed() devuelve VISTA lazy, no copia
         *    ✅Modificar la vista modifica la original
         *    ❌List.of, Set.of inmutables lanzan excepción al modificar
         * 
         *         CUÁNDO USAR:
         *    ✅Acceder a primer / último elemento de cualquier colección con orden
         *    ✅Iterar al revés sin modificar la original
         *    ✅Implementar caches LRU
         *    ✅Históricos ordenados (eventos, transacciones)
         *    ✅Pilas y colas usando List
         *    ✅Manipular respuestas paginadas
         * 
         *         BENEFICIOS:
         *    ✅API consistente entre tipos diferentes de colección
         *    ✅Código más legible y expresivo
         *    ✅Menos verbosidad (sin get(size() - 1))
         *    ✅reversed() es eficiente (sin copia)
         * 
         *         EN SPRING BOOT:
         *         Repositorios devuelven colecciones que ya soportan Sequenced
         *         Streams + Sequenced es combinación natural
         *         Patrones de paginación, histórico, cache más limpios
         */
    }
}
