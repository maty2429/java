package poo;

public class Clases {
    public static void main(String[] args) {
        
        // =========================================================================
        // Parte 1 — Entendiendo el problema antes de la solución
        // =========================================================================
        // Antes de mostrarte qué es una clase, quiero que veas por qué existen. Las clases nacen de un problema real.
        // Imagina que estás programando una tienda online y necesitas guardar información de productos. 
        // Con lo que sabes hasta ahora harías esto:
        {
            String nombreProducto1 = "Laptop";
            double precioProducto1 = 1500.0;
            int stockProducto1 = 10;
            boolean activoProducto1 = true;

            String nombreProducto2 = "Mouse";
            double precioProducto2 = 25.0;
            int stockProducto2 = 50;
            boolean activoProducto2 = true;

            String nombreProducto3 = "Teclado";
            double precioProducto3 = 45.0;
            int stockProducto3 = 30;
            boolean activoProducto3 = true;
        }
        // Mira lo horrible que es. Cuatro variables sueltas por cada producto. Si tienes 100 productos serían 
        // 400 variables sueltas. Y peor, no hay forma de saber que esas cuatro variables pertenecen al mismo 
        // producto. Están sueltas en el aire.
        //
        // Hay un problema más grave. Imagina que necesitas calcular el precio con descuento de un producto. 
        // Tendrías que hacer una función para cada uno o pasar todas las variables como parámetros:
        /*
        double calcularPrecioConDescuento(double precio, int stock, boolean activo) {
            // mucho código aquí
        }
        */
        // Eso no escala. Necesitamos una forma de agrupar datos que pertenecen al mismo concepto y poder trabajar 
        // con ellos como una sola cosa. Para eso existen las clases.

        // =========================================================================
        // Parte 2 — Qué es una clase
        // =========================================================================
        // Una clase es un molde o un plano para crear cosas. No es la cosa en sí, es la receta para fabricarla.
        // Una analogía muy clara: piensa en el plano de una casa. El plano no es una casa, es el diseño. 
        // Con un mismo plano puedes construir 100 casas diferentes, todas con la misma estructura pero 
        // independientes entre sí. Cada casa tendrá sus propios habitantes, sus propios muebles, su propia dirección. 
        // Pero todas siguen el mismo plano.
        // En Java es igual:
        // Clase Producto    →  el plano, el diseño
        // Objeto Producto   →  una casa construida con ese plano
        // Una clase define qué tendrá y qué podrá hacer cualquier objeto creado con ella.

        // =========================================================================
        // Parte 3 — Tu primera clase, paso a paso
        // =========================================================================
        // Voy a crear una clase llamada Producto. Te muestro la versión final primero y luego la desmenuzo línea por línea:
        /*
        public class Producto {
            String nombre;
            double precio;
            int stock;
            boolean activo;
        }
        */
        // Eso es. Una clase básica completa. Ahora vamos parte por parte sin saltarnos nada:
        // Línea 1:
        // public class Producto {
        //
        //     public → significa que esta clase puede ser usada desde cualquier parte del proyecto. 
        //              Por ahora todas tus clases serán public.
        //     class → la palabra reservada de Java para decir "estoy creando una clase".
        //     Producto → el nombre de la clase. Por convención en Java los nombres de clases empiezan en mayúscula. 
        //                Si tiene varias palabras se llama PascalCase: ProductoElectronico, UsuarioRegistrado, 
        //                CarritoDeCompras.
        //     { → abre el cuerpo de la clase. Todo lo que esté dentro de las llaves pertenece a esta clase.
        //
        // Líneas 2 a 5:
        //     String nombre;
        //     double precio;
        //     int stock;
        //     boolean activo;
        //
        //     Esos son los atributos de la clase. Los atributos son las características que tendrá cada objeto 
        //     creado con esta clase. Se llaman también "campos" o "propiedades".
        //     Fíjate que solo declaramos los atributos, no les damos valor. Eso es importante. En la clase decimos 
        //     qué atributos existirán, en el objeto les damos valores específicos.

        // =========================================================================
        // Parte 4 — Crear un objeto a partir de la clase
        // =========================================================================
        // Ya tenemos el plano. Ahora vamos a construir una casa con él. Ese es el momento donde aparece new:
        {
            Producto laptop = new Producto();
        }
        // Esta línea es densa. Vamos a partirla en pedacitos:
        // Producto laptop = new Producto();
        //
        // Parte izquierda — declaración de la variable:
        // Producto laptop
        //
        // Producto → el tipo de la variable. Como cuando dices int edad, aquí dices Producto laptop. 
        //            La variable laptop va a guardar un objeto de tipo Producto.
        // laptop → el nombre que tú eliges para esta variable. Podría ser cualquier nombre.
        //
        // Parte derecha — creación del objeto:
        // new Producto()
        //
        // new → es la instrucción que crea el objeto en memoria. Sin new no hay objeto, solo hay un plano vacío.
        // Producto() → llama al "constructor" de la clase. Por ahora piensa que es como decir "fabrica un Producto nuevo". 
        //              Más adelante en este tema verás los constructores en profundidad.
        // () → los paréntesis son obligatorios. Estás invocando al constructor.
        //
        // El signo = en el medio:
        // Conecta ambas partes. Crea el objeto y lo guarda en la variable laptop.
        //
        // Visualmente lo que pasa en memoria:
        // Antes de la línea:    laptop → no existe nada todavía
        //
        // Java ejecuta new Producto():
        // crea un objeto Producto vacío en memoria
        //
        // Objeto creado:
        // ┌─────────────────┐
        // │ nombre  = null  │
        // │ precio  = 0.0   │
        // │ stock   = 0     │
        // │ activo  = false │
        // └─────────────────┘
        //
        // Java asigna ese objeto a la variable:    laptop → apunta al objeto
        // Importante: cuando creas un objeto sin darle valores, los atributos quedan con valores por defecto:
        // String  → null
        // int     → 0
        // double  → 0.0
        // boolean → false

        // =========================================================================
        // Parte 5 — Asignar valores a los atributos
        // =========================================================================
        // Tenemos un objeto laptop pero está vacío. Vamos a darle valores. Para acceder a los atributos 
        // de un objeto se usa el punto:
        {
            Producto laptop = new Producto();

            laptop.nombre = "Laptop HP";
            laptop.precio = 1500.0;
            laptop.stock = 10;
            laptop.activo = true;
            
            // Cada línea hace lo siguiente:
            // laptop.nombre = "Laptop HP";
            //
            // laptop → la variable que apunta al objeto.
            // . → el punto significa "accede a algo dentro de este objeto".
            // nombre → el atributo al que estás accediendo.
            // = → le asignas un valor.
            // "Laptop HP" → el valor.
        }
        // Después de estas cuatro líneas el objeto en memoria queda así:
        // Objeto laptop:
        // ┌──────────────────────┐
        // │ nombre  = "Laptop HP"│
        // │ precio  = 1500.0     │
        // │ stock   = 10         │
        // │ activo  = true       │
        // └──────────────────────┘

        // =========================================================================
        // Parte 6 — Crear varios objetos del mismo plano
        // =========================================================================
        // Ahora viene la parte que hace todo esto poderoso. Puedes crear muchos objetos del mismo plano 
        // y cada uno es independiente:
        {
            // Primer objeto
            Producto laptop = new Producto();
            laptop.nombre = "Laptop HP";
            laptop.precio = 1500.0;
            laptop.stock = 10;
            laptop.activo = true;

            // Segundo objeto
            Producto mouse = new Producto();
            mouse.nombre = "Mouse Logitech";
            mouse.precio = 25.0;
            mouse.stock = 50;
            mouse.activo = true;

            // Tercer objeto
            Producto teclado = new Producto();
            teclado.nombre = "Teclado mecánico";
            teclado.precio = 80.0;
            teclado.stock = 30;
            teclado.activo = false;
            
            // En memoria ahora tienes tres objetos completamente separados:
            // Objeto laptop:                Objeto mouse:                Objeto teclado:
            // ┌──────────────────────┐      ┌────────────────────────┐    ┌──────────────────────┐
            // │ nombre  = "Laptop HP"│      │ nombre = "Mouse Logitech"│   │ nombre = "Teclado..."│
            // │ precio  = 1500.0     │      │ precio = 25.0           │   │ precio = 80.0        │
            // │ stock   = 10         │      │ stock  = 50             │   │ stock  = 30          │
            // │ activo  = true       │      │ activo = true           │   │ activo = false       │
            // └──────────────────────┘      └────────────────────────┘    └──────────────────────┘
            
            laptop.precio = 1400.0; // solo cambia laptop, mouse y teclado siguen igual
        }
        // Esto es lo que hace poderosa a la POO. Una sola clase te permite crear infinitos objetos independientes.

        // =========================================================================
        // Parte 7 — Imprimir los datos de un objeto
        // =========================================================================
        {
            Producto laptop = new Producto();
            laptop.nombre = "Laptop HP";
            laptop.precio = 1500.0;
            laptop.stock = 10;
            laptop.activo = true;

            System.out.println("--- Imprimir Atributos individuales ---");
            System.out.println("Nombre: " + laptop.nombre);   // Nombre: Laptop HP
            System.out.println("Precio: " + laptop.precio);   // Precio: 1500.0
            System.out.println("Stock: " + laptop.stock);     // Stock: 10
            System.out.println("Activo: " + laptop.activo);   // Activo: true
            
            System.out.println("--- Imprimir Objeto sin método ToString ---");
            System.out.println(laptop); // Imprime el objeto. ¡Ahora se imprime bonito gracias a nuestro toString() de abajo!
        }
        // Eso es la dirección de memoria del objeto. Para que se imprima bonito necesitas un método especial 
        // llamado toString que veremos más adelante. (Nota: ¡Ya lo definimos abajo de la clase Producto para 
        // que lo veas en acción!).

        // =========================================================================
        // Parte 8 — Constructores, la forma profesional de crear objetos
        // =========================================================================
        // Hasta ahora creamos objetos así:
        // Producto laptop = new Producto();
        // laptop.nombre = "Laptop HP";
        // laptop.precio = 1500.0;
        // laptop.stock = 10;
        // laptop.activo = true;
        //
        // Funciona pero es horrible. Cinco líneas para crear un producto. Y peor, si te olvidas de asignar 
        // nombre el objeto queda con nombre = null y nadie te avisa. Es propenso a errores.
        //
        // Los constructores resuelven esto. Un constructor es un método especial que se ejecuta automáticamente 
        // cuando creas un objeto con new. Sirve para inicializar el objeto con valores desde el primer momento.
        //
        // Constructor con parámetros:
        /*
        public class Producto {
            String nombre;
            double precio;
            int stock;
            boolean activo;

            // Este es el constructor
            public Producto(String nombre, double precio, int stock, boolean activo) {
                this.nombre = nombre;
                this.precio = precio;
                this.stock = stock;
                this.activo = activo;
            }
        }
        */
        // Vamos a desmenuzar el constructor línea por línea:
        // Línea 1:
        // public Producto(String nombre, double precio, int stock, boolean activo) {
        //
        // public → cualquiera puede usar este constructor.
        // No hay tipo de retorno. Los constructores no retornan nada, ni siquiera void. Esto es lo que los 
        // identifica como constructores.
        // Producto → el nombre del constructor debe ser idéntico al nombre de la clase. Esto es obligatorio. 
        //            Si la clase se llama Producto, el constructor se llama Producto. Si se llamara Usuario, 
        //            el constructor se llamaría Usuario.
        // (String nombre, double precio, int stock, boolean activo) → los parámetros que recibe el constructor.
        //
        // Líneas 2 a 5:
        // this.nombre = nombre;
        // this.precio = precio;
        // this.stock = stock;
        // this.activo = activo;
        //
        // Aquí aparece una palabra nueva e importantísima: this.

        // =========================================================================
        // Parte 9 — Qué significa this
        // =========================================================================
        // this se refiere al objeto que se está creando ahora mismo. Es la forma de diferenciar el atributo 
        // de la clase con el parámetro del constructor cuando tienen el mismo nombre.
        // Mira esta línea:
        // this.nombre = nombre;
        //
        // this.nombre → el atributo del objeto que se está creando.
        // nombre (sin this) → el parámetro que llegó al constructor.
        //
        // Se lee así: "el atributo nombre de este objeto se llena con el valor del parámetro nombre".
        // ¿Por qué se ponen iguales los nombres? Por claridad. Si pusieras nombres diferentes para no usar this, 
        // el código se vería confuso:
        /*
        // Sin this — funciona pero confuso
        public Producto(String n, double p, int s, boolean a) {
            nombre = n;
            precio = p;
            stock = s;
            activo = a;
        }
        */
        // n, p, s, a no dicen nada. Por eso usamos los mismos nombres con this:
        /*
        // Con this — claro y profesional
        public Producto(String nombre, double precio, int stock, boolean activo) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
            this.activo = activo;
        }
        */
        // Lee la primera línea en voz alta: "el nombre de este objeto se llena con el parámetro nombre". Esa es la idea.

        // =========================================================================
        // Parte 10 — Usar el constructor
        // =========================================================================
        {
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true);
            Producto mouse = new Producto("Mouse Logitech", 25.0, 50, true);
            Producto teclado = new Producto("Teclado mecánico", 80.0, 30, false);
        }
        // Compara con la versión anterior. Pasaste de cinco líneas por producto a una sola línea. Y como el 
        // constructor exige los cuatro parámetros, es imposible que se te olvide asignar un atributo. 
        // Java no te dejará compilar si no pasas los cuatro.

        // =========================================================================
        // Parte 11 — Múltiples constructores (sobrecarga)
        // =========================================================================
        // Puedes tener varios constructores en una misma clase, cada uno con diferentes parámetros. 
        // Java sabe cuál usar según lo que le pases:
        /*
        public class Producto {
            String nombre;
            double precio;
            int stock;
            boolean activo;

            // Constructor 1 — con todos los parámetros
            public Producto(String nombre, double precio, int stock, boolean activo) {
                this.nombre = nombre;
                this.precio = precio;
                this.stock = stock;
                this.activo = activo;
            }

            // Constructor 2 — solo nombre y precio
            // los demás toman valores por defecto
            public Producto(String nombre, double precio) {
                this.nombre = nombre;
                this.precio = precio;
                this.stock = 0;
                this.activo = true;
            }

            // Constructor 3 — sin parámetros
            // todo toma valores por defecto
            public Producto() {
                this.nombre = "Sin nombre";
                this.precio = 0.0;
                this.stock = 0;
                this.activo = false;
            }
        }
        */
        // Y los usas así según lo que tengas a mano:
        {
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true); // usa constructor 1
            Producto mouse = new Producto("Mouse Logitech", 25.0);          // usa constructor 2
            Producto vacio = new Producto();                                // usa constructor 3
            
            // Java mira los parámetros que le pasas y elige automáticamente el constructor correcto.
        }

        // =========================================================================
        // Parte 12 — El constructor por defecto
        // =========================================================================
        // Hay una regla importante. Si no escribes ningún constructor, Java te crea uno invisible llamado 
        // constructor por defecto que no recibe parámetros:
        /*
        public class Producto {
            String nombre;
            double precio;
            // sin constructor escrito
        }

        // Java internamente crea esto automáticamente:
        // public Producto() { }
        */
        // Por eso desde el inicio del tema podíamos hacer new Producto() aunque no habíamos escrito ningún 
        // constructor. Java nos prestaba uno.
        //
        // Pero ojo: en el momento que escribes tu propio constructor, Java deja de prestarte el por defecto. 
        // Mira este error común:
        /*
        public class Producto {
            String nombre;
            double precio;

            public Producto(String nombre, double precio) {
                this.nombre = nombre;
                this.precio = precio;
            }
        }
        
        // En el main
        Producto vacio = new Producto();  // ❌ ERROR — ya no existe el constructor sin parámetros
        */
        // Si quieres seguir teniendo ambos, los escribes los dos explícitamente:
        /*
        public class Producto {
            String nombre;
            double precio;

            public Producto() { } // constructor vacío explícito

            public Producto(String nombre, double precio) {
                this.nombre = nombre;
                this.precio = precio;
            }
        }
        */

        // =========================================================================
        // Parte 13 — Ejemplo completo y limpio
        // =========================================================================
        // Vamos a juntar todo en un ejemplo completo, así lo verás todo unido:
        // Archivo Producto.java: (Definido al final de este archivo de forma integrada)
        // Archivo Main.java: (Ejecutándose en este bloque de código)
        {
            // Crear tres productos diferentes
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true);
            Producto mouse = new Producto("Mouse Logitech", 25.0, 50, true);
            Producto teclado = new Producto("Teclado mecánico", 80.0, 30, false);

            // Acceder a los atributos
            System.out.println("--- Ejemplo Completo en Ejecución ---");
            System.out.println("Producto: " + laptop.nombre);
            System.out.println("Precio: " + laptop.precio);
            System.out.println("Stock: " + laptop.stock);
            System.out.println("Activo: " + laptop.activo);

            System.out.println("---");

            System.out.println("Producto: " + mouse.nombre);
            System.out.println("Precio: " + mouse.precio);

            // Modificar un atributo después de crear el objeto
            laptop.precio = 1400.0;
            System.out.println("Nuevo precio laptop: " + laptop.precio);
        }
        /*
        Salida:
        Producto: Laptop HP
        Precio: 1500.0
        Stock: 10
        Activo: true
        ---
        Producto: Mouse Logitech
        Precio: 25.0
        Nuevo precio laptop: 1400.0
        */

        // =========================================================================
        // Parte 14 — Conexión con Spring Boot
        // =========================================================================
        // Esto que acabas de aprender es literalmente lo que harás en Spring Boot. Cada vez que crees una clase 
        // para representar un producto, un usuario, un pedido, lo harás así. La única diferencia es que tendrás 
        // anotaciones encima:
        /*
        // En Spring Boot verás esto — la estructura es idéntica
        public class Producto {
            private String nombre;
            private double precio;
            private int stock;
            private boolean activo;

            public Producto(String nombre, double precio, int stock, boolean activo) {
                this.nombre = nombre;
                this.precio = precio;
                this.stock = stock;
                this.activo = activo;
            }
        }
        */
        // La palabra private aparecerá en el siguiente tema (encapsulamiento). Pero la estructura de atributos 
        // y constructor es exactamente la que ya dominas.


        // =========================================================================
        // 💡 COMPLEMENTO AVANZADO: Conexión Real con Spring Boot (Lombok y JPA)
        // =========================================================================
        // Para que te vayas familiarizando con lo que verás en Spring Boot: en proyectos reales casi 
        // nunca escribirás los constructores a mano porque usamos una herramienta llamada Lombok, 
        // y para conectarlo a la Base de Datos usamos JPA:
        /*
        @Entity // Le dice a Spring Boot que esta clase mapea una tabla en la Base de Datos
        @Table(name = "productos")
        @Data  // Genera automáticamente los métodos Getters, Setters y toString() por debajo
        @NoArgsConstructor  // Genera el constructor vacío (Producto()) requerido por JPA
        @AllArgsConstructor // Genera el constructor con todos los parámetros
        public class Producto {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String nombre;
            private double precio;
            private int stock;
            private boolean activo;
        }
        */


        // =========================================================================
        // Resumen completo del Tema 1
        // =========================================================================
        // Clase            → el plano o molde para crear objetos
        // Objeto           → una instancia concreta creada con new
        // Atributos        → las características que tendrá cada objeto
        // Constructor      → método especial que inicializa el objeto
        // this             → se refiere al objeto que se está creando
        // new ClaseX()     → crea un objeto y llama al constructor
        //
        // Reglas importantes:
        //   ✅ El nombre del constructor = nombre de la clase
        //   ✅ El constructor no tiene tipo de retorno
        //   ✅ Si no escribes constructor, Java te da uno vacío gratis
        //   ✅ Si escribes un constructor con parámetros, pierdes el vacío
        //   ✅ Puedes tener varios constructores (sobrecarga)
        //   ✅ Usa this para diferenciar atributo del parámetro
        //
        // Acceso:
        //   objeto.atributo     → leer o modificar un atributo
        //   objeto.metodo()     → invocar un método (próximo tema)
    }

// =============================================================================
// 💡 Clase de Apoyo: Producto (Definida para dar soporte real a tus ejemplos de estudio)
// =============================================================================
// Al estar dentro de la clase pública 'Clases' como clase static de apoyo, esta clase
// permite ejecutar los ejemplos del main sin chocar con otros temas que también usan "Producto".
static class Producto {
    String nombre;
    double precio;
    int stock;
    boolean activo;

    // Constructor 1 — con todos los parámetros
    public Producto(String nombre, double precio, int stock, boolean activo) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.activo = activo;
    }

    // Constructor 2 — solo nombre y precio (los demás toman valores por defecto)
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = 0;
        this.activo = true;
    }

    // Constructor 3 — sin parámetros (vacío explícito - todo toma valores por defecto)
    public Producto() {
        this.nombre = "Sin nombre";
        this.precio = 0.0;
        this.stock = 0;
        this.activo = false;
    }

    // 💡 Método toString() personalizado
    // Este método especial de Java es el que se ejecuta cuando haces System.out.println(objeto);
    // ¡Permite que los datos se impriman súper limpios y legibles en vez de direcciones raras!
    @Override
    public String toString() {
        return "Producto[nombre='" + nombre + "', precio=" + precio + ", stock=" + stock + ", activo=" + activo + "]";
    }
}
}
