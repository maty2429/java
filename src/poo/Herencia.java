package poo;

public class Herencia {
    public static void main(String[] args) {
        
        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // Imagina que en tu tienda online tienes diferentes tipos de productos. Tienes productos electrónicos, productos de ropa y productos de comida. Todos tienen cosas en común pero también cosas únicas:
        // Productos electrónicos → nombre, precio, stock, marca, voltaje, garantía
        // Productos de ropa      → nombre, precio, stock, talla, color
        // Productos de comida    → nombre, precio, stock, fechaVencimiento, esVegetariano
        // Mira lo que se repite:
        // nombre, precio, stock → están en TODOS los productos
        // Sin herencia tendrías que escribir tres clases completas con los mismos atributos copiados:
        // 
        // public class ProductoElectronico {
        //     private String nombre;     // repetido
        //     private double precio;     // repetido
        //     private int stock;         // repetido
        //     private String marca;
        //     private int voltaje;
        //     private int garantia;
        //     // + constructor + getters + setters de TODOS
        // }
        // 
        // public class ProductoRopa {
        //     private String nombre;     // repetido otra vez
        //     private double precio;     // repetido otra vez
        //     private int stock;         // repetido otra vez
        //     private String talla;
        //     private String color;
        //     // + constructor + getters + setters de TODOS
        // }
        // 
        // public class ProductoComida {
        //     private String nombre;     // repetido otra vez
        //     private double precio;     // repetido otra vez
        //     private int stock;         // repetido otra vez
        //     private String fechaVencimiento;
        //     private boolean esVegetariano;
        //     // + constructor + getters + setters de TODOS
        // }
        // 
        // Tres problemas graves:
        // 1. Duplicación masiva de código
        // 2. Si cambias nombre a String[] tienes que cambiarlo en TRES clases
        // 3. Java no sabe que esas tres clases son "tipos de Producto", las ve como cosas separadas
        // La solución es la herencia.

        // =========================================================================
        // Parte 2 — Qué es la herencia
        // =========================================================================
        // La herencia te permite crear una clase basada en otra. La nueva clase hereda automáticamente todos los atributos y métodos de la clase original, y le puedes agregar cosas propias.
        // Una analogía clarísima: imagina que tu papá tiene una receta de pasta. Tú aprendes esa receta de él y haces la misma pasta. Pero un día le agregas champiñones porque te gustan. Ahora tienes "la receta del papá + champiñones". No reescribiste la receta desde cero, heredaste la base y agregaste lo tuyo.
        // En Java es igual:
        // Clase padre  →  tiene los atributos comunes (nombre, precio, stock)
        // Clase hija   →  hereda esos atributos y agrega los suyos propios
        // Vocabulario importante:
        // Clase padre / superclase     →  la clase base, la que tiene lo común
        // Clase hija / subclase        →  la clase que hereda de la padre
        // extends                      →  la palabra que crea la relación de herencia

        // =========================================================================
        // Parte 3 — Crear la primera herencia
        // =========================================================================
        // Vamos a refactorizar lo del ejemplo. Primero creamos la clase padre con lo que es común a todos los productos:
        // 
        // public class Producto {
        //     private String nombre;
        //     private double precio;
        //     private int stock;
        // 
        //     public Producto(String nombre, double precio, int stock) {
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
        // }
        // 
        // Hasta aquí es una clase normal de las que ya sabes hacer. Ahora viene lo nuevo. Creamos una clase hija que hereda de Producto:
        // 
        // public class ProductoElectronico extends Producto {
        // 
        // }
        // 
        // Esa palabra extends es la clave. Significa "extiende de", "hereda de". Se lee así:
        // 
        // public class ProductoElectronico extends Producto
        // 
        // Léelo en voz alta: "ProductoElectronico extiende de Producto". Significa que ProductoElectronico es un tipo especializado de Producto.
        // Aunque la clase ProductoElectronico está vacía, ya tiene todo lo de Producto heredado. Mira la magia:
        // 
        // public class Main {
        //     public static void main(String[] args) {
        //         ProductoElectronico tv = new ProductoElectronico("TV Samsung", 800.0, 5);
        // 
        //         System.out.println(tv.getNombre());  // TV Samsung
        //         System.out.println(tv.getPrecio());  // 800.0
        //         System.out.println(tv.getStock());   // 5
        //     }
        // }
        // 
        // ¿Ves? ProductoElectronico nunca declaró los atributos nombre, precio, stock. Tampoco escribió los getters. Pero los heredó de Producto. Es como si los tuviera.
        // Espera, pero hay algo raro. ¿Cómo pude hacer new ProductoElectronico("TV Samsung", 800.0, 5) si nunca escribí ese constructor en ProductoElectronico?
        // Buena pregunta. No puedes. Java se va a quejar. Necesitas crear el constructor de la clase hija, y ahí entra la palabra super.

        // 💡 Demostración real de la Parte 3 en ejecución:
        {
            System.out.println("=== Parte 3: Demostración de la Primera Herencia ===");
            // Usamos ProductoElectronico que hereda de Producto (definidas al final del archivo)
            // Se usa el constructor que llama a super(...)
            ProductoElectronico tv = new ProductoElectronico("TV Samsung", 800.0, 5, "Samsung");

            System.out.println("Nombre heredado del padre: " + tv.getNombre());  // TV Samsung
            System.out.println("Precio heredado del padre: " + tv.getPrecio());  // 800.0
            System.out.println("Stock heredado del padre: " + tv.getStock());   // 5
            System.out.println("Marca del hijo: " + tv.getMarca());              // Samsung
            System.out.println();
        }

        // =========================================================================
        // Parte 4 — La palabra super, el corazón de la herencia
        // =========================================================================
        // super se refiere a la clase padre. Es la forma de decir "oye, padre, hazme algo".
        // Cuando creas un objeto de una clase hija, Java necesita inicializar primero la parte del padre y después la parte del hijo. Imagina construir una casa. Primero los cimientos (lo del padre), luego las paredes (lo del hijo). No puedes empezar por las paredes.
        // Por eso el constructor del hijo debe llamar primero al constructor del padre. Eso se hace con super(...):
        // 
        // public class ProductoElectronico extends Producto {
        //     private String marca;
        //     private int voltaje;
        // 
        //     public ProductoElectronico(String nombre, double precio, int stock,
        //                                 String marca, int voltaje) {
        //         super(nombre, precio, stock); // llama al constructor del padre
        //         this.marca = marca;            // luego asigna lo propio del hijo
        //         this.voltaje = voltaje;
        //     }
        // }
        // 
        // Vamos a desmenuzar línea por línea, sin saltarnos nada:
        // Línea 1:
        // public class ProductoElectronico extends Producto {
        // ProductoElectronico es hija de Producto. Hereda nombre, precio, stock y sus getters.
        // 
        // Línea 2 y 3:
        //     private String marca;
        //     private int voltaje;
        // Atributos propios de ProductoElectronico. Solo los electrónicos los tendrán. Productos de ropa o comida no.
        // 
        // Línea 5 a 6:
        //     public ProductoElectronico(String nombre, double precio, int stock,
        //                                 String marca, int voltaje) {
        // Constructor de ProductoElectronico.
        // Recibe todos los parámetros: los que necesita el padre (nombre, precio, stock) más los suyos propios (marca, voltaje).
        // 
        // Línea 7 — la más importante de este tema:
        //         super(nombre, precio, stock);
        // Aquí está la magia. Esta línea dice: "oye padre Producto, ejecuta tu constructor con estos tres valores y deja inicializada tu parte".
        // Lo que pasa internamente:
        // 1. Java llama al constructor de Producto pasándole nombre, precio, stock
        // 2. El constructor del padre ejecuta:
        //    this.nombre = nombre;
        //    this.precio = precio;
        //    this.stock = stock;
        // 3. La parte heredada queda lista
        // 4. Recién entonces Java continúa con el resto del constructor del hijo
        // 
        // Líneas 8 y 9:
        //         this.marca = marca;
        //         this.voltaje = voltaje;
        // Ahora sí, el constructor del hijo asigna sus atributos propios.
        // 
        // Resumen del orden de ejecución:
        // Tú escribes:   new ProductoElectronico("TV Samsung", 800.0, 5, "Samsung", 220)
        // Java hace:
        //    Paso 1: ejecuta super(...) → constructor de Producto inicializa nombre, precio, stock
        //    Paso 2: continúa el constructor del hijo → asigna marca y voltaje
        //    Paso 3: el objeto queda completamente construido

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: Demostración de Constructor con super(...) ===");
            ProductoElectronico tv220 = new ProductoElectronico("TV Samsung", 800.0, 5, "Samsung", 220);
            System.out.println("Producto creado con super(...):");
            System.out.println("Nombre: " + tv220.getNombre());
            System.out.println("Marca: " + tv220.getMarca());
            System.out.println("Voltaje: " + tv220.getVoltaje());
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — Reglas obligatorias de super
        // =========================================================================
        // Hay reglas estrictas que debes conocer porque si las violas Java no compila:
        // Regla 1: super(...) debe ser la PRIMERA línea del constructor del hijo.
        // 
        // // ❌ ERROR — super no está en la primera línea
        // public ProductoElectronico(String nombre, double precio, int stock, String marca) {
        //     this.marca = marca;
        //     super(nombre, precio, stock); // ❌ Java no compila
        // }
        // 
        // // ✅ Correcto — super es la primera línea
        // public ProductoElectronico(String nombre, double precio, int stock, String marca) {
        //     super(nombre, precio, stock);
        //     this.marca = marca;
        // }
        // ¿Por qué? Porque la parte del padre debe inicializarse antes que la del hijo. No puedes asignar this.marca cuando la parte heredada del objeto todavía no existe.
        // 
        // Regla 2: Si no escribes super(...), Java lo agrega automáticamente sin parámetros.
        // 
        // // Tu código
        // public ProductoElectronico() {
        //     this.marca = "Genérico";
        // }
        // 
        // // Java internamente lo ve así
        // public ProductoElectronico() {
        //     super(); // agregado automáticamente
        //     this.marca = "Genérico";
        // }
        // Pero ojo, eso solo funciona si la clase padre tiene un constructor sin parámetros. Si Producto solo tiene constructor con parámetros, Java se quejará y deberás llamar super(...) explícitamente con valores.
        // 
        // Regla 3: Solo puedes llamar a super(...) desde un constructor del hijo.
        // No puedes llamarlo desde un método normal. super(...) es exclusivo de constructores.

        // =========================================================================
        // Parte 6 — super también sirve para llamar métodos del padre
        // =========================================================================
        // Hasta aquí vimos super(...) con paréntesis para llamar al constructor. Pero super también se usa con punto para llamar métodos del padre:
        // super.algunMetodoDelPadre();
        // Esto lo vas a entender al 100% en la siguiente parte cuando veamos sobreescritura. Por ahora quédate con esta diferencia:
        // super(...)         →  llama al CONSTRUCTOR del padre (solo en constructores)
        // super.metodo()     →  llama a un MÉTODO del padre (en cualquier parte)

        // =========================================================================
        // Parte 7 — Sobreescritura de métodos (override)
        // =========================================================================
        // Aquí viene la otra parte poderosa de la herencia. A veces el hijo necesita comportarse diferente que el padre en algún método. Por ejemplo, imagina que Producto tiene un método para mostrar su información:
        // 
        // public class Producto {
        //     private String nombre;
        //     private double precio;
        //     private int stock;
        // 
        //     // constructor y getters omitidos
        // 
        //     public void mostrarInfo() {
        //         System.out.println("Nombre: " + nombre);
        //         System.out.println("Precio: " + precio);
        //         System.out.println("Stock: " + stock);
        //     }
        // }
        // 
        // Si ProductoElectronico solo hereda eso, al llamar mostrarInfo() mostrará solo lo del padre. Pero ProductoElectronico tiene marca y voltaje, debería mostrarlos también.
        // Aquí entra la sobreescritura. La clase hija puede reescribir un método heredado para que se comporte distinto:
        // 
        // public class ProductoElectronico extends Producto {
        //     private String marca;
        //     private int voltaje;
        // 
        //     public ProductoElectronico(String nombre, double precio, int stock,
        //                                 String marca, int voltaje) {
        //         super(nombre, precio, stock);
        //         this.marca = marca;
        //         this.voltaje = voltaje;
        //     }
        // 
        //     // Sobreescritura del método mostrarInfo del padre
        //     @Override
        //     public void mostrarInfo() {
        //         System.out.println("Nombre: " + getNombre());
        //         System.out.println("Precio: " + getPrecio());
        //         System.out.println("Stock: " + getStock());
        //         System.out.println("Marca: " + marca);
        //         System.out.println("Voltaje: " + voltaje);
        //     }
        // }
        // 
        // Vamos a entender la línea más importante:
        // @Override
        // public void mostrarInfo() {
        // @Override es una anotación. Se pone justo encima del método. Le dice a Java: "este método está sobreescribiendo uno del padre". Es opcional pero muy recomendada porque si te equivocas en el nombre del método, Java te avisa.
        // Ejemplo de utilidad de @Override:
        // @Override
        // public void mostraInfo() {  // ← faltó la "r" de "mostrar"
        //     // ...
        // }
        // Sin @Override Java pensaría que estás creando un método nuevo llamado mostraInfo y no se quejaría. Con @Override Java detecta que no existe ningún método llamado así en el padre y te avisa del error.
        // Regla: siempre pon @Override cuando sobreescribas un método. Te ahorrará bugs.
        // 
        // Qué pasa al ejecutarlo:
        // public class Main {
        //     public static void main(String[] args) {
        //         Producto generico = new Producto("Producto X", 100.0, 20);
        //         generico.mostrarInfo();
        //         // Nombre: Producto X
        //         // Precio: 100.0
        //         // Stock: 20
        // 
        //         System.out.println("---");
        // 
        //         ProductoElectronico tv = new ProductoElectronico("TV Samsung", 800.0, 5, "Samsung", 220);
        //         tv.mostrarInfo();
        //         // Nombre: TV Samsung
        //         // Precio: 800.0
        //         // Stock: 5
        //         // Marca: Samsung
        //         // Voltaje: 220
        //     }
        // }
        // Mira lo que pasó. Cuando llamas tv.mostrarInfo(), Java automáticamente usa la versión del hijo, no la del padre. Porque tv es un ProductoElectronico, no un Producto genérico.
        // Eso se llama polimorfismo, lo veremos a fondo en un tema más adelante. Por ahora solo entiende que el método que ejecuta Java es el del tipo real del objeto.

        // 💡 Demostración real de la Parte 7 en ejecución:
        {
            System.out.println("=== Parte 7: Demostración de Sobreescritura (Override) ===");
            Producto generico = new Producto("Producto X", 100.0, 20);
            generico.mostrarInfo();

            System.out.println("---");

            ProductoElectronico tv = new ProductoElectronico("TV Samsung", 800.0, 5, "Samsung", 220);
            tv.mostrarInfo(); // Se ejecuta la versión sobreescrita del hijo
            System.out.println();
        }

        // =========================================================================
        // Parte 8 — super.metodo() dentro de la sobreescritura
        // =========================================================================
        // A veces no quieres reemplazar completamente el método del padre, solo extenderlo. Quieres que haga todo lo del padre y además algo extra. Para eso se usa super.metodo():
        // 
        // @Override
        // public void mostrarInfo() {
        //     super.mostrarInfo();  // ejecuta primero el método del padre
        //     System.out.println("Marca: " + marca);
        //     System.out.println("Voltaje: " + voltaje);
        // }
        // Mira lo bonito que queda. La línea super.mostrarInfo() ejecuta el método del padre con su lógica original (mostrar nombre, precio, stock). Después el hijo agrega lo suyo (marca, voltaje).
        // Salida:
        // Nombre: TV Samsung
        // Precio: 800.0
        // Stock: 5
        // Marca: Samsung
        // Voltaje: 220
        // Esto es muy útil cuando el padre tiene lógica compleja que no quieres copiar y pegar. Solo la invocas con super.metodo() y agregas lo del hijo.
        // 
        // Comparativa de las dos formas:
        // 
        // // Forma 1 — reemplaza completamente
        // @Override
        // public void mostrarInfo() {
        //     System.out.println("Nombre: " + getNombre());
        //     System.out.println("Precio: " + getPrecio());
        //     System.out.println("Stock: " + getStock());
        //     System.out.println("Marca: " + marca);
        //     System.out.println("Voltaje: " + voltaje);
        // }
        // 
        // // Forma 2 — extiende reutilizando el padre con super
        // @Override
        // public void mostrarInfo() {
        //     super.mostrarInfo();
        //     System.out.println("Marca: " + marca);
        //     System.out.println("Voltaje: " + voltaje);
        // }
        // La forma 2 es preferible porque si el padre cambia su mostrarInfo(), el hijo automáticamente refleja esos cambios sin tocar el código.

        // 💡 Demostración real de la Parte 8 en ejecución (extender con super.mostrarInfo()):
        {
            System.out.println("=== Parte 8: Demostración de Reutilización con super.mostrarInfo() ===");
            ProductoElectronico tvIntel = new ProductoElectronico("TV Samsung", 800.0, 5, "Samsung", 220);
            tvIntel.mostrarInfo(); // Llama a la versión que internamente usa super.mostrarInfo()
            System.out.println();
        }

        // =========================================================================
        // Parte 9 — protected, el modificador que aparece con herencia
        // =========================================================================
        // ¿Recuerdas que en el tema anterior vimos los modificadores public y private? Faltaba uno: protected.
        // public     → todos pueden ver
        // private    → solo la misma clase
        // protected  → la misma clase Y sus clases hijas
        // protected es muy útil en herencia. Mira el problema actual:
        // 
        // public class Producto {
        //     private String nombre;  // privado
        //     // ...
        // }
        // 
        // public class ProductoElectronico extends Producto {
        //     @Override
        //     public void mostrarInfo() {
        //         System.out.println(nombre); // ❌ ERROR — nombre es privado, no se puede acceder
        //     }
        // }
        // La clase hija no puede acceder directamente a los atributos privados del padre. Tendría que usar el getter getNombre(). Eso está bien, es la forma profesional.
        // Pero a veces quieres que las hijas accedan directamente. Para eso usas protected:
        // 
        // public class Producto {
        //     protected String nombre;  // accesible para hijas
        //     protected double precio;
        //     protected int stock;
        //     // ...
        // }
        // 
        // public class ProductoElectronico extends Producto {
        //     @Override
        //     public void mostrarInfo() {
        //         System.out.println(nombre); // ✅ funciona, las hijas sí pueden acceder
        //     }
        // }
        // Cuándo usar cada uno:
        // private    → atributos que solo la propia clase debe tocar (más estricto)
        // protected  → atributos que las hijas también pueden tocar
        // public     → todos pueden tocar (raramente para atributos, casi siempre para métodos)
        // En la práctica de Spring Boot lo más común es:
        // Atributos    → private (siempre)
        // Getters      → public
        // Setters      → public
        // Constructor  → public
        // Métodos      → public
        // Y usar protected solo cuando realmente necesitas que las hijas tengan acceso directo, que es raro.

        // 💡 Demostración real de la Parte 9 en ejecución (usando la clase ProductoProtected y ProductoElectronicoProtected):
        {
            System.out.println("=== Parte 9: Demostración de Atributos protected ===");
            ProductoElectronicoProtected prodProt = new ProductoElectronicoProtected("Laptop Pro", 1200.0, 15, "HP");
            System.out.println("Mostrando información accediendo directamente a campos 'protected' del padre:");
            prodProt.mostrarInfoConProtected();
            System.out.println();
        }

        // =========================================================================
        // Parte 10 — Una herencia más completa
        // =========================================================================
        // Vamos a juntar todo. Pondré tres clases en un ejemplo completo:
        // [Las clases Producto, ProductoElectronico y ProductoComida completas están declaradas al final]
        //
        // Main de la Parte 10 en ejecución:
        {
            System.out.println("=== Parte 10: Demostración de Herencia Completa ===");
            ProductoElectronico tv = new ProductoElectronico(
                "TV Samsung", 800.0, 5, "Samsung", 24, 220); // 24 meses de garantía

            ProductoComida pizza = new ProductoComida(
                "Pizza", 12.5, 100, "2024-12-31", false);

            tv.mostrarInfo();
            System.out.println("---");
            pizza.mostrarInfo();
            System.out.println();
            
            // Si mañana agregas un atributo descuento en Producto, automáticamente todas las hijas lo tienen sin tocar una línea.
        }

        // =========================================================================
        // Parte 11 — Reglas y limitaciones de la herencia en Java
        // =========================================================================
        // Tres reglas importantes que debes saber:
        // Regla 1: Java solo permite herencia simple (una sola clase padre).
        // 
        // // ❌ No se puede en Java
        // public class ProductoElectronicoComida extends Producto, ProductoComida { } 
        // 
        // // ✅ Solo un padre
        // public class ProductoElectronico extends Producto { }
        // Lenguajes como C++ permiten heredar de varios padres a la vez. Java no. Si necesitas comportamiento de varios padres se usan interfaces, tema que veremos más adelante.
        // 
        // Regla 2: Los atributos private del padre NO son accesibles directamente desde el hijo.
        // Solo se accede a ellos con getters o a través de protected. Esto ya lo vimos.
        // 
        // Regla 3: Los constructores NO se heredan.
        // Aunque parezca raro. La hija debe escribir sus propios constructores y llamar a super(...). No se heredan automáticamente.

        // =========================================================================
        // Parte 12 — La clase Object, el ancestro de todos
        // =========================================================================
        // Algo curioso que debes saber. Cuando creas una clase normal sin extends, automáticamente hereda de una clase llamada Object:
        // 
        // public class Producto { }
        // 
        // // Java internamente lo ve así
        // public class Producto extends Object { }
        // Object es la clase raíz de todo Java. Todas las clases heredan de ella, directa o indirectamente. Por eso todos los objetos tienen métodos como toString(), equals(), hashCode(). Son heredados de Object.
        // Esto te permite sobreescribir toString() para que tus objetos se impriman bonito:
        // 
        // public class Producto {
        //     private String nombre;
        //     private double precio;
        // 
        //     @Override
        //     public String toString() {
        //         return "Producto{nombre='" + nombre + "', precio=" + precio + "}";
        //     }
        // }
        // Ahora cuando hagas:
        // Producto laptop = new Producto("Laptop", 1500.0);
        // System.out.println(laptop);
        // // Producto{nombre='Laptop', precio=1500.0}
        // En vez del feo Producto@1540e19d. En Spring Boot esto es muy útil para debugging y logs.

        // 💡 Demostración real de la Parte 12 en ejecución:
        {
            System.out.println("=== Parte 12: La clase Object y el método toString() ===");
            ProductoConToString laptop = new ProductoConToString("Laptop", 1500.0);
            System.out.println("Objeto impreso con toString() sobreescrito:");
            System.out.println(laptop); // Imprime el formato personalizado gracias a toString()
            System.out.println();
        }

        // =========================================================================
        // Parte 13 — Conexión con Spring Boot
        // =========================================================================
        // La herencia se usa muchísimo en Spring Boot. Algunos ejemplos típicos:
        // 1. Tienes una clase BaseEntity con id, fechaCreacion, fechaActualizacion
        //    Todas tus entidades (Usuario, Producto, Pedido) heredan de BaseEntity
        //    Así no repites esos tres campos en cada entidad
        // 2. Tienes una excepción base llamada NegocioException
        //    Creas hijas como ProductoNoEncontradoException, UsuarioNoAutorizadoException
        //    Spring Boot puede manejarlas todas con un solo catch de NegocioException
        // 3. Tienes un controlador base BaseController con métodos comunes
        //    Tus controladores específicos heredan de él y solo agregan lo propio
        // Sin herencia tu código de Spring Boot estaría lleno de duplicaciones. Con herencia es elegante y mantenible.

        // 💡 Demostración ficticia de Entity Base en ejecución (simulación de Spring Boot):
        {
            System.out.println("=== Parte 13: Simulación de Herencia en Spring Boot ===");
            // Un objeto Usuario que hereda id, fechaCreacion y fechaActualizacion de BaseEntity
            Usuario usuario = new Usuario(1L, "2026-01-01", "2026-05-21", "alumno@antigravity.com");
            System.out.println("Usuario ID (heredado): " + usuario.getId());
            System.out.println("Fecha Creación (heredada): " + usuario.getFechaCreacion());
            System.out.println("Email (propio del hijo): " + usuario.getEmail());
            System.out.println();
        }

        // =========================================================================
        // Resumen completo del Tema 3
        // =========================================================================
        // HERENCIA       → una clase hija obtiene atributos y métodos de una clase padre
        // 
        // extends        → palabra que define la relación de herencia
        //                  public class Hija extends Padre
        // 
        // super(...)     → llama al CONSTRUCTOR del padre
        //                  debe ser la PRIMERA línea del constructor del hijo
        //                  obligatorio si el padre no tiene constructor sin parámetros
        // 
        // super.metodo() → llama a un MÉTODO del padre desde el hijo
        //                  se usa para extender comportamiento sin reemplazarlo
        // 
        // @Override      → anotación que marca que un método sobreescribe al del padre
        //                  opcional pero altamente recomendada
        //                  te avisa si te equivocas en el nombre o firma
        // 
        // protected      → modificador entre public y private
        //                  accesible para la misma clase Y sus hijas
        // 
        // Object         → la clase raíz de todo en Java
        //                  todas las clases heredan de ella implícitamente
        //                  ofrece toString, equals, hashCode
        // 
        // Reglas:
        //   ✅ Solo herencia simple (un solo padre)
        //   ✅ Los constructores NO se heredan
        //   ✅ Los atributos private del padre solo se acceden por getters
        //   ✅ super(...) siempre primera línea del constructor del hijo
        //   ✅ Pon @Override siempre que sobreescribas

        System.out.println("=================================================");
        System.out.println("¡Guía del Tema 3 completada y ejecutada con éxito!");
        System.out.println("=================================================");
    }

// =========================================================================
// CLASES SOPORTE (static internas) PARA LAS DEMOSTRACIONES
// =========================================================================

static class Producto {
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
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

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Stock: " + stock);
    }
}

static class ProductoElectronico extends Producto {
    private String marca;
    private int voltaje; // Para los ejemplos de la parte 4 y 7
    private int garantiaMeses; // Para los ejemplos de la parte 10

    // Constructor completo adaptado para manejar marca, voltaje
    public ProductoElectronico(String nombre, double precio, int stock, String marca, int voltaje) {
        super(nombre, precio, stock);
        this.marca = marca;
        this.voltaje = voltaje;
        this.garantiaMeses = 12; // Valor por defecto
    }

    // Constructor de la parte 10 (con garantiaMeses y voltaje)
    public ProductoElectronico(String nombre, double precio, int stock, String marca, int garantiaMeses, int voltaje) {
        super(nombre, precio, stock);
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
        this.voltaje = voltaje;
    }

    // Constructor simple para la Parte 3 y 5
    public ProductoElectronico(String nombre, double precio, int stock, String marca) {
        super(nombre, precio, stock);
        this.marca = marca;
        this.voltaje = 0;
        this.garantiaMeses = 12;
    }

    public String getMarca() {
        return this.marca;
    }

    public int getVoltaje() {
        return this.voltaje;
    }

    public int getGarantiaMeses() {
        return this.garantiaMeses;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Marca: " + marca);
        if (voltaje > 0) {
            System.out.println("Voltaje: " + voltaje);
        }
        System.out.println("Garantía: " + garantiaMeses + " meses");
    }
}

static class ProductoComida extends Producto {
    private String fechaVencimiento;
    private boolean esVegetariano;

    public ProductoComida(String nombre, double precio, int stock, String fechaVencimiento, boolean esVegetariano) {
        super(nombre, precio, stock);
        this.fechaVencimiento = fechaVencimiento;
        this.esVegetariano = esVegetariano;
    }

    public String getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public boolean isEsVegetariano() {
        return this.esVegetariano;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Vence: " + fechaVencimiento);
        System.out.println("Vegetariano: " + esVegetariano);
    }
}

// Para la Parte 9 (protected vs private)
static class ProductoProtected {
    protected String nombre;
    protected double precio;
    protected int stock;

    public ProductoProtected(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}

static class ProductoElectronicoProtected extends ProductoProtected {
    private String marca;

    public ProductoElectronicoProtected(String nombre, double precio, int stock, String marca) {
        super(nombre, precio, stock);
        this.marca = marca;
    }

    public void mostrarInfoConProtected() {
        // Acceso directo a atributos del padre gracias a protected
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Marca: " + marca);
    }
}

// Para la Parte 12: Demostración de toString()
static class ProductoConToString {
    private String nombre;
    private double precio;

    public ProductoConToString(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{nombre='" + nombre + "', precio=" + precio + "}";
    }
}

// Clases ficticias para simular los ejemplos de Spring Boot (Parte 13)
static class BaseEntity {
    private Long id;
    private String fechaCreacion;
    private String fechaActualizacion;

    public BaseEntity(Long id, String fechaCreacion, String fechaActualizacion) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() { return id; }
    public String getFechaCreacion() { return fechaCreacion; }
    public String getFechaActualizacion() { return fechaActualizacion; }
}

static class Usuario extends BaseEntity {
    private String email;

    public Usuario(Long id, String fechaCreacion, String fechaActualizacion, String email) {
        super(id, fechaCreacion, fechaActualizacion);
        this.email = email;
    }

    public String getEmail() { return email; }
}
}
