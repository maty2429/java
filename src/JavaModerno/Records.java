package JavaModerno;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Records {
    public static void main(String[] args) {
        
        // =========================================================================
        // Records — Constructor Compacto, Validación, Accessors e Inmutabilidad
        // =========================================================================

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // En la Fase 2 aprendiste a crear clases con encapsulamiento. Recuerdas una clase tipo DTO así:
        // 
        // public class ProductoResponse {
        //     private final String nombre;
        //     private final double precio;
        //     private final int stock;
        // 
        //     public ProductoResponse(String nombre, double precio, int stock) {
        //         this.nombre = nombre;
        //         this.precio = precio;
        //         this.stock = stock;
        //     }
        // 
        //     public String getNombre() {
        //         return this.nombre;
        //     }
        // 
        //     public double getPrecio() {
        //         return this.precio;
        //     }
        // 
        //     public int getStock() {
        //         return this.stock;
        //     }
        // 
        //     @Override
        //     public boolean equals(Object o) {
        //         if (this == o) return true;
        //         if (!(o instanceof ProductoResponse)) return false;
        //         ProductoResponse that = (ProductoResponse) o;
        //         return Double.compare(that.precio, precio) == 0 &&
        //                 stock == that.stock &&
        //                 Objects.equals(nombre, that.nombre);
        //     }
        // 
        //     @Override
        //     public int hashCode() {
        //         return Objects.hash(nombre, precio, stock);
        //     }
        // 
        //     @Override
        //     public String toString() {
        //         return "ProductoResponse{nombre='" + nombre + "', precio=" + precio + ", stock=" + stock + '}';
        //     }
        // }
        // 
        // 40 + líneas para representar tres datos. Y la mayoría es código repetitivo que el compilador podría
        // generar automáticamente. Atributos final, constructor, getters, equals, hashCode, toString. Todo
        // predecible. Todo aburrido. Todo propenso a errores cuando agregas un campo y olvidas actualizar algún método.
        // Por años los desarrolladores Java usaron herramientas como Lombok para esconder este código
        // repetitivo con anotaciones. Pero Lombok es una librería externa, requiere un plugin en el IDE, y no es
        // parte oficial del lenguaje.
        // Java 14 (en preview) y Java 16 (estable) introdujeron Records, una característica nativa del lenguaje
        // que reemplaza todo ese código boilerplate con una sola línea.

        // 💡 Demostración real de la Parte 1 en ejecución:
        {
            System.out.println("=== Parte 1: El problema antes de la solución (Clase POJO Tradicional) ===");
            ProductoResponsePojo pPojo = new ProductoResponsePojo("Laptop HP", 1500.0, 10);
            System.out.println("POJO creado: " + pPojo.getNombre() + ", $" + pPojo.getPrecio() + ", Stock: " + pPojo.getStock());
            System.out.println("POJO toString: " + pPojo);
            System.out.println();
        }

        // =========================================================================
        // Parte 2 — Qué es un Record
        // =========================================================================
        // Un Record es una clase especial diseñada para representar datos inmutables. Cuando declaras un Record, el
        // compilador genera automáticamente:
        // ✅ Atributos privados y finales
        // ✅ Constructor canónico (con todos los parámetros)
        // ✅ Métodos accessors (uno por cada campo)
        // ✅ equals() basado en los valores de los campos
        // ✅ hashCode() basado en los valores de los campos
        // ✅ toString() que muestra todos los campos
        // Todo eso, gratis, sin escribir una sola línea.
        // La sintaxis básica es radicalmente simple:
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        // }
        // 
        // Una sola línea. Y reemplaza completamente las 40 + líneas de antes.
        // Una analogía clara. Si una clase tradicional es como construir un mueble desde cero con todas las
        // piezas individuales, un Record es como un mueble prefabricado al que solo le indicas las medidas. Java se
        // encarga de armar todo lo demás.

        // =========================================================================
        // Parte 3 — Anatomía de un Record
        // =========================================================================
        // Vamos a desmenuzar la sintaxis línea por línea:
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        // }
        // 
        // public → modificador de acceso normal.
        // record → palabra clave especial. Le dice a Java "esto es un Record, no una clase normal".
        // ProductoResponse → el nombre, sigue las mismas convenciones que las clases (PascalCase).
        // (String nombre, double precio, int stock) → la lista de componentes del Record. Cada componente se convierte
        // automáticamente en un campo privado final.
        // {
        // } → el cuerpo. Generalmente vacío para Records básicos. Puede contener métodos adicionales si los necesitas.
        // 
        // Esos parámetros entre paréntesis son lo más particular. Se llaman componentes. No son
        // "parámetros de constructor" en el sentido tradicional. Son la definición misma del Record.

        // =========================================================================
        // Parte 4 — Crear y usar un Record
        // =========================================================================
        // Se usa exactamente como cualquier otra clase. Con new:
        // 
        // ProductoResponse p = new ProductoResponse("Laptop", 1500.0, 10);
        // 
        // Y para acceder a los valores, no usas getters tradicionales. Usas el nombre del componente como método:
        // 
        // String nombre = p.nombre();     // sin "get", directamente "nombre()"
        // double precio = p.precio();
        // int stock = p.stock();
        // 
        // Mira la diferencia importante. En una clase tradicional con encapsulamiento:
        // 
        // producto.getNombre();   // estilo tradicional con get
        // 
        // En un Record:
        // 
        // producto.nombre();      // estilo Record - sin get
        // 
        // Esto es una convecnión del lenguaje. Los Records no usan el prefijo get. El método accessor lleva el mismo
        // nombre que el componente.
        // Esto al principio confunde a desarrolladores que vienen de POJOs tradicionales, pero es muy
        // intencional. Los Records representan datos puros, no objetos con comportamiento, por eso no se les llama
        // "propiedades" sino "componentes".

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: Crear y usar un Record (Accessor sin get) ===");
            ProductoResponseDemo p = new ProductoResponseDemo("Laptop", 1500.0, 10);
            System.out.println("Nombre (p.nombre()): " + p.nombre());
            System.out.println("Precio (p.precio()): " + p.precio());
            System.out.println("Stock (p.stock()): " + p.stock());
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — Inmutabilidad: los Records no se pueden modificar
        // =========================================================================
        // Esta es una característica fundamental. Una vez creado un Record, no puedes cambiar sus valores:
        // 
        // ProductoResponse p = new ProductoResponse("Laptop", 1500.0, 10);
        // p.precio = 1400.0;        // ❌ ERROR de compilación
        // p.setPrecio(1400.0);      // ❌ no existe ningún setter
        // 
        // No hay setters. No puedes asignar valores después de la creación. Esto es por diseño.
        // ¿Por qué la inmutabilidad es valiosa? Tres razones principales:
        // 1. Predictibilidad
        //    Si nadie puede cambiar el Record, sabes exactamente qué contiene en cualquier momento
        // 2. Seguridad en concurrencia
        //    Múltiples hilos pueden leer el Record sin riesgo de race conditions. No hay nada que se pueda corromper porque nada cambia
        // 3. Uso seguro como clave de Map o elemento de Set
        //    Si los valores no cambian, hashCode tampoco cambia. Esencial para colecciones basadas en hash
        // 
        // En Spring Boot esto es crítico. Los DTOs deben ser inmutables porque viajan entre capas y entre el cliente y
        // el servidor. Si alguien pudiera modificarlos en medio del recorrido, sería un desastre.
        // ¿Cómo "modificas" un Record entonces?
        // No lo modificas. Creas uno nuevo basado en el anterior:
        // 
        // ProductoResponse original = new ProductoResponse("Laptop", 1500.0, 10);
        // // Crear uno nuevo con precio modificado
        // ProductoResponse modificado = new ProductoResponse(
        //         original.nombre(),
        //         1400.0,  // precio cambiado
        //         original.stock()
        // );
        // 
        // Esto puede parecer tedioso pero es intencional. Te obliga a pensar en cada cambio como una nueva versión, no
        // como mutación. Es el estilo de programación funcional aplicado.
        // Para hacer esto más cómodo, puedes agregar métodos al Record que faciliten estas "modificaciones":
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        //     public ProductoResponse conPrecio(double nuevoPrecio){
        //         return new ProductoResponse(nombre, nuevoPrecio, stock);
        //     }
        //     public ProductoResponse conStock(int nuevoStock){
        //         return new ProductoResponse(nombre, precio, nuevoStock);
        //     }
        // }
        // 
        // Y los usas así:
        // 
        // ProductoResponse modificado = original
        //         .conPrecio(1400.0)
        //         .conStock(5);
        // 
        // Encadenas cambios creando nuevos Records. Limpio y expresivo.

        // 💡 Demostración real de la Parte 5 en ejecución:
        {
            System.out.println("=== Parte 5: Inmutabilidad y Modificación con métodos 'con' ===");
            ProductoResponseConMethods original = new ProductoResponseConMethods("Laptop", 1500.0, 10);
            // original.precio = 1400.0; // ❌ ERROR de compilación: fields are final
            
            // Simular modificación a través de la creación de un nuevo objeto
            ProductoResponseConMethods modificado = original
                    .conPrecio(1400.0)
                    .conStock(5);
            
            System.out.println("Original: " + original);
            System.out.println("Modificado: " + modificado);
            System.out.println();
        }

        // =========================================================================
        // Parte 6 — equals, hashCode y toString automáticos
        // =========================================================================
        // Esta es una de las características más valiosas. El compilador genera estos métodos basándose en los valores de
        // los componentes.
        // equals automático
        // 
        // ProductoResponse a = new ProductoResponse("Laptop", 1500.0, 10);
        // ProductoResponse b = new ProductoResponse("Laptop", 1500.0, 10);
        // ProductoResponse c = new ProductoResponse("Mouse", 25.0, 50);
        // 
        // System.out.println(a.equals(b)); // true - mismos valores
        // System.out.println(a.equals(c)); // false - valores distintos
        // System.out.println(a == b);       // false - son objetos diferentes en memoria
        // 
        // Esto es diferente al comportamiento de las clases tradicionales donde equals por defecto solo
        // compara referencias. En Records, dos Records con los mismos valores son iguales.
        // hashCode automático
        // 
        // ProductoResponse a = new ProductoResponse("Laptop", 1500.0, 10);
        // ProductoResponse b = new ProductoResponse("Laptop", 1500.0, 10);
        // 
        // System.out.println(a.hashCode() == b.hashCode()); // true - mismos valores, mismo hash
        // 
        // Como equals y hashCode son consistentes (Records con valores iguales tienen el mismo hash), puedes usar
        // Records como claves de HashMap o elementos de HashSet sin problemas.
        // toString automático
        // 
        // ProductoResponse p = new ProductoResponse("Laptop", 1500.0, 10);
        // System.out.println(p);
        // // ProductoResponse[nombre=Laptop, precio=1500.0, stock=10]
        // 
        // Mucho más útil que el ProductoResponse@1540e19d de las clases tradicionales. Útil para logs y debugging en
        // Spring Boot.

        // 💡 Demostración real de la Parte 6 en ejecución:
        {
            System.out.println("=== Parte 6: equals, hashCode y toString Automáticos ===");
            ProductoResponseDemo a = new ProductoResponseDemo("Laptop", 1500.0, 10);
            ProductoResponseDemo b = new ProductoResponseDemo("Laptop", 1500.0, 10);
            ProductoResponseDemo c = new ProductoResponseDemo("Mouse", 25.0, 50);

            System.out.println("a.equals(b) -> " + a.equals(b) + " (mismos valores)");
            System.out.println("a.equals(c) -> " + a.equals(c) + " (valores distintos)");
            System.out.println("a == b -> " + (a == b) + " (diferente referencia en memoria)");
            System.out.println("a.hashCode() == b.hashCode() -> " + (a.hashCode() == b.hashCode()));
            System.out.println("toString de c -> " + c);
            System.out.println();
        }

        // =========================================================================
        // Parte 7 — Constructor canónico: el constructor que Java genera
        // =========================================================================
        // Cuando declaras:
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        // }
        // 
        // Java genera automáticamente un constructor llamado canónico con todos los componentes en el mismo orden:
        // 
        // // Lo que Java genera internamente
        // public ProductoResponse(String nombre, double precio, int stock){
        //     this.nombre = nombre;
        //     this.precio = precio;
        //     this.stock = stock;
        // }
        // 
        // Este constructor canónico es el que se llama cuando haces new ProductoResponse(...). Como cualquier constructor.
        // Pero aquí viene lo interesante. Puedes personalizar el constructor canónico sin tener que
        // reescribirlo completo. Hay dos formas:
        // el constructor compacto (especial de Records) y el constructor canónico tradicional.

        // =========================================================================
        // Parte 8 — Constructor compacto: validación elegante
        // =========================================================================
        // El constructor compacto es la forma única de los Records de personalizar el constructor canónico. Te permite
        // agregar validaciones o transformaciones sin tener que listar de nuevo los parámetros.
        // Sintaxis del constructor compacto:
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        //     public ProductoResponse {
        //         // validaciones y transformaciones aquí
        //     }
        // }
        // 
        // Mira con atención. No tiene paréntesis con parámetros. Es solo public ProductoResponse { ... }. Esto es lo que
        // lo diferencia del constructor canónico tradicional.
        // Veamos un ejemplo con validaciones:
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        //     public ProductoResponse {
        //         if (nombre == null || nombre.isBlank()) {
        //             throw new IllegalArgumentException("El nombre no puede estar vacío");
        //         }
        //         if (precio < 0) {
        //             throw new IllegalArgumentException("El precio no puede ser negativo");
        //         }
        //         if (stock < 0) {
        //             throw new IllegalArgumentException("El stock no puede ser negativo");
        //         }
        //     }
        // }
        // 
        // Ahora cuando intentes crear un Record inválido, lanza excepción inmediatamente:
        // new ProductoResponse("Laptop", 1500.0, 10);    // ✅ válido
        // new ProductoResponse("", 1500.0, 10);          // ❌ IllegalArgumentException
        // new ProductoResponse("Laptop", -100.0, 10);    // ❌ IllegalArgumentException
        // new ProductoResponse("Laptop", 1500.0, -5);    // ❌ IllegalArgumentException
        // 
        // Imposible crear un Record en estado inválido. Combinas inmutabilidad con validación. El resultado es código
        // bulletproof.
        // 
        // Cómo funciona el constructor compacto por dentro:
        // Internamente, Java toma tu constructor compacto y lo expande automáticamente:
        // 
        // // Lo que escribiste
        // public ProductoResponse {
        //     if (precio < 0) {
        //         throw new IllegalArgumentException("...");
        //     }
        // }
        // 
        // // Lo que Java genera internamente
        // public ProductoResponse(String nombre, double precio, int stock){
        //     if (precio < 0) {
        //         throw new IllegalArgumentException("...");
        //     }
        //     this.nombre = nombre;    // Java agrega esto automáticamente al final
        //     this.precio = precio;
        //     this.stock = stock;
        // }
        // 
        // Las asignaciones this.X = X se agregan automáticamente al final. Tú solo escribes la lógica de validación o
        // transformación.
        // 
        // Transformaciones en el constructor compacto:
        // No solo puedes validar, también puedes normalizar los valores antes de que se asignen:
        // 
        // public record Usuario(String email, String nombre) {
        //     public Usuario {
        //         // Validaciones
        //         if (email == null || !email.contains("@")) {
        //             throw new IllegalArgumentException("Email inválido");
        //         }
        //         // Transformaciones - estos valores se asignan al final
        //         email = email.trim().toLowerCase();
        //         nombre = nombre.trim();
        //     }
        // }
        // 
        // Cuando creas:
        // Usuario u = new Usuario("  CARLOS@gmail.com  ", "  Carlos  ");
        // System.out.println(u.email());   // "carlos@gmail.com" - ya está normalizado
        // System.out.println(u.nombre());  // "Carlos" - sin espacios
        // 
        // Los Records ya nacen con los datos limpios y normalizados. No tienes que normalizar después en cada lugar
        // donde los uses.
        // 
        // El constructor compacto vs el canónico tradicional:
        // También puedes escribir el constructor canónico completo si quieres más control:
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        //     // Constructor canónico tradicional (con paréntesis y parámetros)
        //     public ProductoResponse(String nombre, double precio, int stock){
        //         if (precio < 0) {
        //             throw new IllegalArgumentException("Precio inválido");
        //         }
        //         this.nombre = nombre;
        //         this.precio = precio;
        //         this.stock = stock;
        //     }
        // }
        // 
        // Diferencias:
        // Constructor compacto:
        // public ProductoResponse { ... }
        // ✅ Sin paréntesis ni parámetros
        // ✅ Java asigna los valores automáticamente al final
        // ✅ Más limpio y expresivo
        // ❌ Requiere que los nombres de parámetros coincidan con los componentes
        // 
        // Constructor canónico tradicional:
        // public ProductoResponse(String nombre, double precio, int stock){ ... }
        // ✅ Mayor control total
        // ⚠️ Tienes que asignar manualmente con this.X = X
        // ⚠️ Más verboso
        // 
        // Recomendación: usa el constructor compacto siempre que puedas. Es la forma idiomática.

        // 💡 Demostración real de la Parte 8 en ejecución:
        {
            System.out.println("=== Parte 8: Constructor Compacto (Validación y Normalización) ===");
            
            // 1. Intentar validar
            try {
                new ProductoResponseDemo("", 1500.0, 10);
            } catch (IllegalArgumentException e) {
                System.out.println("Excepción capturada al validar nombre vacío: " + e.getMessage());
            }

            try {
                new ProductoResponseDemo("Laptop", -150.0, 10);
            } catch (IllegalArgumentException e) {
                System.out.println("Excepción capturada al validar precio negativo: " + e.getMessage());
            }

            // 2. Normalización de campos
            Usuario u = new Usuario("  CARLOS@gmail.com  ", "  Carlos  ");
            System.out.println("Email normalizado: '" + u.email() + "'");
            System.out.println("Nombre normalizado: '" + u.nombre() + "'");
            System.out.println();
        }

        // =========================================================================
        // Parte 9 — Constructores adicionales
        // =========================================================================
        // Puedes agregar otros constructores con diferentes firmas (sobrecarga):
        // 
        // public record ProductoResponse(String nombre, double precio, int stock){
        //     // Constructor compacto con validación
        //     public ProductoResponse {
        //         if (precio < 0) {
        //             throw new IllegalArgumentException("Precio inválido");
        //         }
        //     }
        //     // Constructor adicional - sin stock, asume 0
        //     public ProductoResponse(String nombre, double precio){
        //         this(nombre, precio, 0);  // delega al constructor canónico
        //     }
        //     // Constructor adicional - solo nombre, valores por defecto
        //     public ProductoResponse(String nombre) {
        //         this(nombre, 0.0, 0);
        //     }
        // }
        // 
        // Importante: los constructores adicionales deben llamar al canónico con this(...) en la primera línea. No pueden
        // asignar valores directamente. Esto garantiza que las validaciones del constructor canónico siempre se ejecuten.
        // Y los usas según necesites:
        // ProductoResponse a = new ProductoResponse("Laptop");
        // ProductoResponse b = new ProductoResponse("Mouse", 25.0);
        // ProductoResponse c = new ProductoResponse("Teclado", 80.0, 30);

        // 💡 Demostración real de la Parte 9 en ejecución:
        {
            System.out.println("=== Parte 9: Constructores Adicionales Sobrecargados ===");
            ProductoResponseDemo a = new ProductoResponseDemo("Laptop");
            ProductoResponseDemo b = new ProductoResponseDemo("Mouse", 25.0);
            ProductoResponseDemo c = new ProductoResponseDemo("Teclado", 80.0, 30);

            System.out.println("a (solo nombre): " + a);
            System.out.println("b (nombre y precio): " + b);
            System.out.println("c (todos los datos): " + c);
            System.out.println();
        }

        // =========================================================================
        // Parte 10 — Accessors personalizados
        // =========================================================================
        // Por defecto, los accessors retornan el valor del componente tal cual. Pero puedes
        // personalizarlos sobreescribiéndolos:
        // 
        // public record Producto(String nombre, double precio, int stock){
        //     // Accessor personalizado para precio - retorna con 2 decimales
        //     @Override
        //     public double precio() {
        //         return Math.round(precio * 100.0) / 100.0;
        //     }
        //     // Accessor personalizado para nombre - retorna en mayúsculas
        //     @Override
        //     public String nombre() {
        //         return nombre.toUpperCase();
        //     }
        // }
        // 
        // Cuando accedas a estos valores, obtienes la versión personalizada:
        // Producto p = new Producto("laptop", 1500.999, 10);
        // System.out.println(p.nombre()); // "LAPTOP"
        // System.out.println(p.precio()); // 1501.00 (redondeado a 2 decimales)
        // 
        // Pero hay una sutileza importante. El valor interno sigue siendo el original. Solo el accessor lo transforma cuando
        // lo lees.
        // Esta funcionalidad se usa con cuidado. Generalmente es mejor normalizar en el constructor compacto
        // que personalizar accessors, porque mantiene la coherencia del Record. Pero existe la opción para casos
        // específicos como formato de salida.

        // 💡 Demostración real de la Parte 10 en ejecución:
        {
            System.out.println("=== Parte 10: Accessors Personalizados ===");
            Producto p = new Producto("laptop", 1500.994, 10);
            System.out.println("Accessor nombre() personalizado: " + p.nombre());
            System.out.println("Accessor precio() personalizado (redondea 2 decs): " + p.precio());
            System.out.println();
        }

        // =========================================================================
        // Parte 11 — Métodos adicionales en Records
        // =========================================================================
        // Aunque los Records están diseñados para datos puros, pueden tener métodos. Solo no pueden tener
        // atributos adicionales más allá de los componentes:
        // 
        // public record Producto(String nombre, double precio, int stock){
        //     // Método de instancia - calcula algo basado en los componentes
        //     public double precioConIVA() {
        //         return precio * 1.19;
        //     }
        //     public boolean tieneStock() {
        //         return stock > 0;
        //     }
        //     public boolean esCaro() {
        //         return precio > 1000;
        //     }
        //     // Método estático - utilidad relacionada con el Record
        //     public static Producto crearVacio() {
        //         return new Producto("", 0, 0);
        //     }
        // }
        // 
        // Y los usas como cualquier método:
        // Producto p = new Producto("Laptop", 1500.0, 10);
        // double conIVA = p.precioConIVA();    // 1785.0
        // boolean hayStock = p.tieneStock();   // true
        // boolean caro = p.esCaro();           // true
        // Producto vacio = Producto.crearVacio();
        // 
        // Lo que NO puedes hacer:
        // public record Producto(String nombre, double precio, int stock){
        //     private double descuento;  // ❌ ERROR - no puedes agregar atributos nuevos
        // }
        // 
        // Los Records solo pueden tener como atributos los componentes que definiste entre paréntesis. Nada más.
        // Esto refuerza su naturaleza de "datos puros".

        // 💡 Demostración real de la Parte 11 en ejecución:
        {
            System.out.println("=== Parte 11: Métodos de Instancia y Estáticos ===");
            Producto p = new Producto("Laptop", 1000.0, 10);
            System.out.println("Precio con IVA (p.precioConIVA()): " + p.precioConIVA());
            System.out.println("Tiene stock? (p.tieneStock()): " + p.tieneStock());
            System.out.println("Es caro? (p.esCaro()): " + p.esCaro());

            Producto vacio = Producto.crearVacio();
            System.out.println("Record creado con método estático crearVacio(): " + vacio);
            System.out.println();
        }

        // =========================================================================
        // Parte 12 — Records implementan interfaces
        // =========================================================================
        // Los Records pueden implementar interfaces como cualquier clase:
        // 
        // public interface Imprimible {
        //     void imprimir();
        // }
        // 
        // public record Producto(String nombre, double precio) implements Imprimible {
        //     @Override
        //     public void imprimir() {
        //         System.out.println("Producto: " + nombre + " - $" + precio);
        //     }
        // }
        // 
        // Esto los hace muy versátiles. Puedes combinarlos con polimorfismo:
        // List<Imprimible> items = new ArrayList<>();
        // items.add(new Producto("Laptop", 1500.0));
        // items.add(new Usuario("Carlos", "carlos@gmail.com"));
        // 
        // for (Imprimible item : items) {
        //     item.imprimir();
        // }
        // 
        // Esto es muy común en Spring Boot. Combinas Records con interfaces para crear jerarquías de DTOs polimórficos.

        // 💡 Demostración real de la Parte 12 en ejecución:
        {
            System.out.println("=== Parte 12: Records Implementando Interfaces ===");
            List<Imprimible> items = new ArrayList<>();
            items.add(new ProductoImprimible("Laptop", 1500.0));
            // Hacemos que Usuario también implemente Imprimible para que encaje con el ejemplo
            // En nuestra demo, representamos la impresión usando una clase anónima o mapeando los elementos:
            items.add(new Imprimible() {
                @Override
                public void imprimir() {
                    System.out.println("Usuario: Carlos - email: carlos@gmail.com");
                }
            });

            for (Imprimible item : items) {
                item.imprimir();
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 13 — Records NO pueden heredar
        // =========================================================================
        // Una limitación importante. Los Records no pueden extender otras clases. Implícitamente extienden de java.
        // lang.Record y eso no se puede cambiar:
        // 
        // public record Producto(String nombre, double precio) extends Item {
        // } // ❌ ERROR
        // 
        // Pero pueden implementar interfaces, como acabamos de ver, y pueden ser parte de jerarquías a través de
        // interfaces selladas (que veremos en el próximo tema).
        // Esto es por diseño. Los Records están pensados para ser datos finales, no parte de jerarquías de herencia clásica.
        // Si necesitas herencia, usa una clase normal. Si necesitas datos puros con polimorfismo limitado, combina
        // Records con sealed interfaces.

        // =========================================================================
        // Parte 14 — Records anizados
        // =========================================================================
        // Puedes declarar Records dentro de otras clases, interfaces, o incluso dentro de métodos. Son
        // static implícitamente cuando están dentro de una clase:
        // 
        // public class ServicioProductos {
        //     // Record anidado dentro de la clase
        //     public record EstadisticasProducto(int totalVentas, double ingresos) {
        //     }
        //     public EstadisticasProducto calcular(Long idProducto) {
        //         // lógica
        //         return new EstadisticasProducto(150, 45000.0);
        //     }
        // }
        // 
        // Esto es muy común para Records que solo se usan internamente en una clase. En Spring Boot lo verás en clases de
        // servicio que necesitan retornar estructuras específicas.

        // 💡 Demostración real de la Parte 14 en ejecución:
        {
            System.out.println("=== Parte 14: Records Anidados ===");
            ServicioProductos servicio = new ServicioProductos();
            ServicioProductos.EstadisticasProducto stats = servicio.calcular(1L);
            System.out.println("Stats calculadas: total ventas = " + stats.totalVentas() + ", ingresos = " + stats.ingresos());
            System.out.println();
        }

        // =========================================================================
        // Parte 15 — Records y Jackson en Spring Boot
        // =========================================================================
        // Aquí está la parte que más te va a interesar para Spring Boot. Jackson es la librería que usa Spring Boot
        // para convertir JSON a objetos Java y viceversa. Y desde Jackson 2.12, los Records son soportados nativamente.
        // Imagina que tu API recibe este JSON:
        // {
        //     "nombre": "Laptop",
        //     "precio": 1500.0,
        //     "stock": 10
        // }
        // Con una clase tradicional necesitarías:
        // 
        // public class ProductoRequest {
        //     private String nombre;
        //     private double precio;
        //     private int stock;
        //     public ProductoRequest() {} // constructor vacío que Jackson necesita
        //     // getters, setters, etc.
        // }
        // 
        // Con un Record es solo:
        // 
        // public record ProductoRequest(String nombre, double precio, int stock){
        // }
        // 
        // Y Jackson lo convierte automáticamente desde el JSON sin configuración adicional.
        // Para que esto funcione perfectamente hay un detalle técnico: debes compilar con la opción -parameters
        // para que Jackson pueda leer los nombres de los componentes. En Maven se configura una vez en el pom.xml, IntelliJ
        // lo activa automáticamente en proyectos modernos.
        // Anotaciones de Jackson que funcionan con Records:
        // 
        // public record ProductoRequest(
        //         @JsonProperty("nombre_completo") String nombre,
        //         double precio,
        //         @JsonAlias({"cantidad", "qty"}) int stock
        // ){
        // }
        // 
        // @JsonProperty permite mapear un nombre JSON diferente al nombre del componente.
        // @JsonAlias permite que el JSON use varios nombres alternativos.
        // Estas anotaciones van sobre los componentes, en la lista de parámetros del Record. Es la sintaxis estándar.

        // =========================================================================
        // Parte 16 — Records vs Clases tradicionales en Spring Boot
        // =========================================================================
        // Una guía rápida para decidir:
        // USA RECORDS cuando:
        // ✅ Es un DTO (Request o Response)
        // ✅ Representa datos puros, sin lógica compleja
        // ✅ Necesitas inmutabilidad
        // ✅ No requiere herencia
        // ✅ equals / hashCode basado en valores tiene sentido
        // 
        // USA CLASES TRADICIONALES cuando:
        // ✅ Es una entidad JPA (necesita mutabilidad y constructor vacío)
        // ✅ Necesita lógica de negocio compleja
        // ✅ Requiere herencia entre tipos
        // ✅ Tiene estado que cambia (services, controllers)
        // ✅ Necesita campos no presentes en el constructor
        // 
        // En Spring Boot moderno verás esta separación clara:
        // DTOs (Records)             → ProductoRequest, ProductoResponse, UsuarioDTO
        // Entidades JPA (Clases)      → Producto, Usuario, Pedido (con @Entity)
        // Servicios (Clases)          → ProductoService, UsuarioService
        // Controllers (Clases)        → ProductoController, UsuarioController
        // Cada cosa en su lugar. Los Records cubren los DTOs, las clases tradicionales el resto.

        // =========================================================================
        // Parte 17 — Ejemplo completo en estilo Spring Boot
        // =========================================================================
        // Te muestro cómo se ven los Records en un contexto realista. Imagina un controlador REST que maneja productos:
        // 
        // // DTOs como Records con validaciones
        // public record CrearProductoRequest(
        //         String nombre,
        //         double precio,
        //         int stock,
        //         String categoria
        // ) {
        //     public CrearProductoRequest {
        //         if (nombre == null || nombre.isBlank()) {
        //             throw new IllegalArgumentException("El nombre es obligatorio");
        //         }
        //         if (precio < 0) {
        //             throw new IllegalArgumentException("El precio no puede ser negativo");
        //         }
        //         if (stock < 0) {
        //             throw new IllegalArgumentException("El stock no puede ser negativo");
        //         }
        //         // Normalización
        //         nombre = nombre.trim();
        //         categoria = categoria != null ? categoria.trim().toLowerCase() : "general";
        //     }
        // }
        // 
        // public record ProductoResponse(
        //         Long id,
        //         String nombre,
        //         double precio,
        //         int stock,
        //         String categoria,
        //         LocalDateTime fechaCreacion
        // ) {
        //     // Método de conveniencia
        //     public boolean disponible() {
        //         return stock > 0;
        //     }
        //     public double precioConIVA() {
        //         return precio * 1.19;
        //     }
        // }
        // 
        // public record ActualizarStockRequest(int nuevoStock) {
        //     public ActualizarStockRequest {
        //         if (nuevoStock < 0) {
        //             throw new IllegalArgumentException("El stock no puede ser negativo");
        //         }
        //     }
        // }
        // 
        // Mira lo compacto y expresivo que queda. Cada Record es una unidad clara con su propósito definido.
        // Validaciones donde corresponden. Sin getters ni setters ni constructor verboso.
        // Cuando este código se conecte con Spring Boot, Jackson serializa y deserializa estos
        // Records automáticamente. Sin configuración. Sin anotaciones extras (a menos que las necesites).

        // 💡 Demostración real de la Parte 17 en ejecución:
        {
            System.out.println("=== Parte 17: Simulación Estilo Spring Boot (DTOs con Records) ===");
            CrearProductoRequest req = new CrearProductoRequest("   PlayStation 5   ", 499.99, 15, "   ELECTRONICS   ");
            System.out.println("Request recibida y limpia:");
            System.out.println("Nombre: '" + req.nombre() + "'");
            System.out.println("Categoría: '" + req.categoria() + "'");

            ProductoResponseCompleto res = new ProductoResponseCompleto(
                    1L,
                    req.nombre(),
                    req.precio(),
                    req.stock(),
                    req.categoria(),
                    LocalDateTime.now()
            );

            System.out.println("Response generada para el cliente:");
            System.out.println("Disponible? (res.disponible()): " + res.disponible());
            System.out.println("Precio con IVA: " + res.precioConIVA());
            System.out.println();
        }

        // =========================================================================
        // Parte 18 — Pequeñas limitaciones a tener presentes
        // =========================================================================
        // Algunos detalles que debes saber:
        // ❌ No pueden extender clases (solo implementar interfaces)
        // ❌ No pueden declarar atributos de instancia adicionales
        // ❌ Son implícitamente final (no pueden ser extendidos)
        // ❌ No funcionan con bibliotecas que requieren constructor sin args
        //      (como JPA Entity, aunque Hibernate 6 + tiene mejor soporte)
        // ⚠️ Componentes mutables (Lists, Maps) tienen referencia inmutable,
        //      pero su contenido puede cambiar si no usas List.of o Map.of
        // 
        // Sobre la última:
        // si tienes un Record con un componente tipo List:
        // 
        // public record Pedido(Long id, List<String> productos) {
        // }
        // 
        // List<String> lista = new ArrayList<>();
        // lista.add("Laptop");
        // Pedido p = new Pedido(1L, lista);
        // 
        // // La referencia a la lista no puede cambiar
        // // p.productos = otraLista; // ❌ no se puede
        // 
        // // PERO el contenido de la lista sí puede cambiar
        // lista.add("Mouse");
        // System.out.println(p.productos()); // [Laptop, Mouse] - cambió!
        // 
        // Para verdadera inmutabilidad, usa colecciones inmutables o copia defensivamente en el constructor compacto:
        // 
        // public record Pedido(Long id, List<String> productos) {
        //     public Pedido {
        //         productos = List.copyOf(productos); // copia inmutable
        //     }
        // }
        // 
        // List.copyOf crea una copia inmutable. Ahora ni la referencia ni el contenido pueden cambiar.

        // 💡 Demostración real de la Parte 18 en ejecución:
        {
            System.out.println("=== Parte 18: Copia Defensiva en Colecciones Mutables ===");
            List<String> mutableList = new ArrayList<>();
            mutableList.add("Laptop");
            
            Pedido p = new Pedido(1L, mutableList);
            System.out.println("Contenido inicial del Pedido: " + p.productos());

            // Intentamos alterar la lista original mutable
            mutableList.add("Mouse");

            // Gracias a List.copyOf(productos) en el constructor compacto, el Record no se alteró
            System.out.println("Pedido después de alterar lista externa: " + p.productos() + " (¡Sigue inmutable!)");

            // Además, si intentamos alterar la lista que expone el Record directamente, fallará:
            try {
                p.productos().add("Teclado");
            } catch (UnsupportedOperationException e) {
                System.out.println("Excepción capturada al intentar alterar la lista del Record directamente: UnsupportedOperationException");
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 19 — Conexión profunda con Java 21 y Spring Boot
        // =========================================================================
        // Los Records son la base de varias características modernas de Java que veremos en los siguientes temas:
        // Records + Sealed Classes  → jerarquías de DTOs polimórficas
        // Records + Pattern Matching → deconstrucción elegante (próximos temas)
        // Records + Stream API      → manipulación de DTOs en servicios
        // Records + Optional        → retornos seguros en repositorios
        // 
        // En Spring Boot 3.x con Java 21 todo el código moderno gira alrededor de Records. Cada endpoint REST que
        // escribas tendrá:
        // - Un Record como Request (lo que recibe)
        // - Un Record como Response (lo que devuelve)
        // - Eventualmente, sealed interfaces de Records para casos polimórficos
        // Sin Records el código moderno de Spring Boot sería 3 veces más largo y propenso a errores. Por eso son el
        // tema fundacional de la Fase 4.

        // =========================================================================
        // Resumen completo del Tema 1
        // =========================================================================
        // RECORDS (Java 16 +)
        // Clases especiales para datos inmutables
        // El compilador genera automáticamente:
        // ✅ Atributos privados final
        // ✅ Constructor canónico
        // ✅ Accessors (sin prefijo "get")
        // ✅ equals, hashCode, toString basados en valores
        // 
        // SINTAXIS BÁSICA:
        // public record NombreRecord(Tipo1 comp1, Tipo2 comp2) {
        // }
        // 
        // ACCESSORS:
        // producto.nombre()       → sin "get", igual al nombre del componente
        // 
        // CREAR:
        // new ProductoResponse("Laptop", 1500.0, 10)
        // 
        // INMUTABILIDAD:
        // ✅ No hay setters
        // ✅ Los componentes son final
        // ✅ Para "modificar" creas un Record nuevo
        // ⚠️ Componentes tipo colección requieren copia defensiva
        // 
        // CONSTRUCTOR COMPACTO:
        // public NombreRecord { ... }
        // ✅ Sin paréntesis ni parámetros
        // ✅ Para validaciones y normalizaciones
        // ✅ Java asigna los valores al final automáticamente
        // 
        // CONSTRUCTOR CANÓNICO TRADICIONAL:
        // public NombreRecord(Tipo1 comp1, Tipo2 comp2) { ... }
        // ✅ Mayor control, pero más verboso
        // 
        // CONSTRUCTORES ADICIONALES:
        // ✅ Deben llamar a this(...) en la primera línea
        // ✅ Permiten múltiples formas de crear el Record
        // 
        // ACCESSORS PERSONALIZADOS:
        // ✅ Sobreescribir el método del componente
        // ⚠️ El valor interno sigue siendo el original
        // ⚠️ Generalmente mejor normalizar en constructor compacto
        // 
        // MÉTODOS PERMITIDOS:
        // ✅ Métodos de instancia
        // ✅ Métodos estáticos
        // ❌ Atributos de instancia adicionales
        // 
        // HERENCIA:
        // ❌ No pueden extender clases
        // ✅ Pueden implementar interfaces
        // ✅ Son implícitamente final
        // 
        // JACKSON EN SPRING BOOT:
        // ✅ Soporte nativo desde Jackson 2.12 +
        // ✅ Compilar con -parameters para leer nombres
        // ✅ Funcionan @JsonProperty, @JsonAlias sobre componentes
        // 
        // CUÁNDO USAR RECORDS:
        // ✅ DTOs (Request y Response)
        // ✅ Value Objects inmutables
        // ✅ Datos puros sin lógica compleja
        // ❌ Entidades JPA (necesitan mutabilidad)
        // ❌ Servicios o controllers con estado
        // ❌ Cuando necesitas herencia de clases
        // 
        // VENTAJAS:
        // ✅ Sin código boilerplate
        // ✅ Inmutabilidad por diseño
        // ✅ equals / hashCode automáticos basados en valores
        // ✅ toString útil para debugging
        // ✅ Validaciones centralizadas en constructor compacto
        // ✅ Más expresivo y menos propenso a errores
        // 
        // Lista de lo que cubrimos completo:
        // ✅ Qué problema resuelven los Records
        // ✅ Sintaxis básica de declaración
        // ✅ Cómo crear y usar Records
        // ✅ Inmutabilidad y por qué importa
        // ✅ Cómo "modificar" Records con métodos with
        // ✅ equals, hashCode, toString automáticos
        // ✅ Constructor canónico generado por Java
        // ✅ Constructor compacto para validaciones
        // ✅ Constructores adicionales con sobrecarga
        // ✅ Accessors personalizados
        // ✅ Métodos adicionales permitidos
        // ✅ Records implementando interfaces
        // ✅ Limitaciones de herencia
        // ✅ Records anizados
        // ✅ Integración con Jackson en Spring Boot
        // ✅ Cuándo usar Records vs clases tradicionales
        // ✅ Ejemplo realista en contexto Spring Boot
        // ✅ Conexión con el resto de Java 21
        
    }

    // =========================================================================
    // --- ESTRUCTURAS ANIDADAS DE SOPORTE PARA RECORDS ---
    // =========================================================================

    // --- Parte 1 ---
    public static class ProductoResponsePojo {
        private final String nombre;
        private final double precio;
        private final int stock;

        public ProductoResponsePojo(String nombre, double precio, int stock) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        public String getNombre() {
            return this.nombre;
        }

        public double getPrecio() {
            return this.precio;
        }

        public int getStock() {
            return this.stock;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ProductoResponsePojo)) return false;
            ProductoResponsePojo that = (ProductoResponsePojo) o;
            return Double.compare(that.precio, precio) == 0 &&
                    stock == that.stock &&
                    Objects.equals(nombre, that.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, precio, stock);
        }

        @Override
        public String toString() {
            return "ProductoResponsePojo{nombre='" + nombre + "', precio=" + precio + ", stock=" + stock + '}';
        }
    }

    // --- Parte 2, 3, 4, 6, 7, 8, 9 ---
    public record ProductoResponseDemo(String nombre, double precio, int stock) {
        
        // Constructor compacto con validaciones (Parte 8)
        public ProductoResponseDemo {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }
            if (precio < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo");
            }
            if (stock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
        }

        // Constructor adicional Sobrecargado (Parte 9) - sin stock, asume 0
        public ProductoResponseDemo(String nombre, double precio) {
            this(nombre, precio, 0); // delega al constructor canónico
        }

        // Constructor adicional Sobrecargado (Parte 9) - solo nombre, valores por defecto
        public ProductoResponseDemo(String nombre) {
            this(nombre, 0.0, 0);
        }
    }

    // --- Parte 5 ---
    public record ProductoResponseConMethods(String nombre, double precio, int stock) {
        public ProductoResponseConMethods conPrecio(double nuevoPrecio) {
            return new ProductoResponseConMethods(nombre, nuevoPrecio, stock);
        }

        public ProductoResponseConMethods conStock(int nuevoStock) {
            return new ProductoResponseConMethods(nombre, precio, nuevoStock);
        }
    }

    // --- Parte 8 ---
    public record Usuario(String email, String nombre) {
        public Usuario {
            // Validaciones
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Email inválido");
            }

            // Transformaciones
            email = email.trim().toLowerCase();
            nombre = nombre.trim();
        }
    }

    // --- Parte 10, 11 ---
    public record Producto(String nombre, double precio, int stock) {
        
        // Accessor personalizado para precio (Parte 10) - retorna redondeado a 2 decs
        @Override
        public double precio() {
            return Math.round(precio * 100.0) / 100.0;
        }

        // Accessor personalizado para nombre (Parte 10) - retorna en mayúsculas
        @Override
        public String nombre() {
            return nombre.toUpperCase();
        }

        // Método de instancia (Parte 11) - calcula algo basado en los componentes
        public double precioConIVA() {
            return precio * 1.19;
        }

        public boolean tieneStock() {
            return stock > 0;
        }

        public boolean esCaro() {
            return precio > 1000;
        }

        // Método estático (Parte 11) - utilidad relacionada con el Record
        public static Producto crearVacio() {
            return new Producto("", 0, 0);
        }
    }

    // --- Parte 12 ---
    public interface Imprimible {
        void imprimir();
    }

    public record ProductoImprimible(String nombre, double precio) implements Imprimible {
        @Override
        public void imprimir() {
            System.out.println("Producto: " + nombre + " - $" + precio);
        }
    }

    // --- Parte 14 ---
    public static class ServicioProductos {
        // Record anidado dentro de la clase
        public record EstadisticasProducto(int totalVentas, double ingresos) {
        }

        public EstadisticasProducto calcular(Long idProducto) {
            // lógica simulada
            return new EstadisticasProducto(150, 45000.0);
        }
    }

    // --- Parte 15 ---
    public record ProductoRequest(String nombre, double precio, int stock) {
    }

    // --- Parte 17 ---
    public record CrearProductoRequest(
            String nombre,
            double precio,
            int stock,
            String categoria
    ) {
        public CrearProductoRequest {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre es obligatorio");
            }
            if (precio < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo");
            }
            if (stock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }

            // Normalización
            nombre = nombre.trim();
            categoria = categoria != null ? categoria.trim().toLowerCase() : "general";
        }
    }

    public record ProductoResponseCompleto(
            Long id,
            String nombre,
            double precio,
            int stock,
            String categoria,
            LocalDateTime fechaCreacion
    ) {
        // Métodos de conveniencia
        public boolean disponible() {
            return stock > 0;
        }

        public double precioConIVA() {
            return precio * 1.19;
        }
    }

    public record ActualizarStockRequest(int nuevoStock) {
        public ActualizarStockRequest {
            if (nuevoStock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
        }
    }

    // --- Parte 18 ---
    public record Pedido(Long id, List<String> productos) {
        public Pedido {
            productos = List.copyOf(productos); // copia defensiva e inmutable
        }
    }
}
