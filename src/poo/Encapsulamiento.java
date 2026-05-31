package poo;

public class Encapsulamiento {
    public static void main(String[] args) {

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // En el tema anterior creamos esta clase:
        // javapublic class Producto {
        //     String nombre;
        //     double precio;
        //     int stock;
        //     boolean activo;
        // 
        //     public Producto(String nombre, double precio, int stock, boolean activo) {
        //         this.nombre = nombre;
        //         this.precio = precio;
        //         this.stock = stock;
        //         this.activo = activo;
        //     }
        // }
        // Y la usamos así:
        // javaProducto laptop = new Producto("Laptop HP", 1500.0, 10, true);
        // 
        // System.out.println(laptop.precio); // 1500.0
        // laptop.precio = 1400.0;            // modificar precio
        // Hasta aquí todo funciona. Pero hay un problema grave que no se ve a primera vista.
        // Como los atributos están abiertos al mundo, cualquier persona que use tu clase puede hacer cosas absurdas y romper tu objeto:
        // javaProducto laptop = new Producto("Laptop HP", 1500.0, 10, true);
        // 
        // laptop.precio = -500.0;       // ❌ precio negativo, no tiene sentido
        // laptop.stock = -100;          // ❌ stock negativo, imposible
        // laptop.nombre = "";           // ❌ nombre vacío, producto sin nombre
        // laptop.nombre = null;         // ❌ peor todavía, nombre nulo
        // Java no se queja. Compila y ejecuta. Tu objeto queda corrupto y cuando intentes usarlo más adelante todo va a fallar.
        // Otro problema: imagina que tienes 500 lugares en tu código donde se asigna el precio. Si después necesitas validar que el precio no sea negativo, tendrías que poner la validación en 500 lugares. Es imposible mantenerlo.
        // La solución es el encapsulamiento.

        // 💡 Demostración real en ejecución de la Parte 1 (usando la clase ProductoPublico de abajo):
        {
            System.out.println("--- Parte 1: El problema de los campos públicos (Sin encapsular) ---");
            ProductoPublico laptopMalo = new ProductoPublico("Laptop HP", 1500.0, 10, true);
            System.out.println("Precio inicial: " + laptopMalo.precio); // 1500.0
            
            // Modificaciones absurdas que Java permite porque no está encapsulado:
            laptopMalo.precio = -500.0;
            laptopMalo.stock = -100;
            laptopMalo.nombre = "";
            
            System.out.println("Producto corrupto en memoria -> Nombre: '" + laptopMalo.nombre + "' | Precio: " + laptopMalo.precio + " | Stock: " + laptopMalo.stock);
        }

        // =========================================================================
        // Parte 2 — Qué es el encapsulamiento
        // =========================================================================
        // Encapsulamiento significa proteger los datos del objeto para que nadie pueda modificarlos directamente. En vez de dejar los atributos abiertos, los cierras y solo permites acceder a ellos a través de métodos controlados.
        // Una analogía clarísima: piensa en un cajero automático.
        // Tu cuenta bancaria tiene un saldo. ¿El banco te deja meter la mano dentro del sistema y cambiar el número directamente? No. Te da botones y pantallas controladas. Tú dices "quiero retirar 100" y el cajero verifica:
        // ¿Tienes saldo suficiente?     →  si no, te niega la operación
        // ¿El monto es positivo?        →  si no, te niega la operación
        // ¿Está dentro del límite?      →  si no, te niega la operación
        // Recién entonces actualiza el saldo
        // El saldo está protegido. No lo modificas tú directamente, lo modificas a través de operaciones validadas. Eso es encapsulamiento.
        // En Java se hace en dos pasos:
        // Paso 1: cerrar los atributos con private
        // Paso 2: dar acceso controlado con getters y setters

        // =========================================================================
        // Parte 3 — Modificadores de acceso
        // =========================================================================
        // Antes de los getters y setters, necesitas entender los modificadores de acceso. Son palabras que ponen delante de los atributos y métodos para controlar quién puede verlos.
        // Hay cuatro niveles, de más abierto a más cerrado:
        // public      → todos pueden verlo y usarlo
        // protected   → solo la misma clase y sus clases hijas (Fase 2 herencia)
        // (sin nada)  → solo clases del mismo paquete
        // private     → solo la propia clase
        // Para encapsulamiento usarás dos: private para los atributos y public para los métodos que los manejan.
        // Aplicado a la clase Producto:
        // javapublic class Producto {
        //     private String nombre;     // ahora cerrado al mundo
        //     private double precio;     // ahora cerrado al mundo
        //     private int stock;         // ahora cerrado al mundo
        //     private boolean activo;    // ahora cerrado al mundo
        // }
        // Con esto, si alguien intenta hacer laptop.precio = -500.0 desde otra clase, Java no compila:
        // javaProducto laptop = new Producto("Laptop HP", 1500.0, 10, true);
        // laptop.precio = -500.0; // ❌ ERROR — precio has private access in Producto
        // Excelente. Los atributos quedan blindados. Pero ahora aparece otro problema: tampoco puedes leerlos. Si quieres mostrar el precio en pantalla no puedes:
        // javaSystem.out.println(laptop.precio); // ❌ ERROR — no puedes acceder
        // Para solucionarlo necesitas crear puertas controladas. Esas puertas son los getters y setters.

        // =========================================================================
        // Parte 4 — Getters, las puertas para LEER
        // =========================================================================
        // Un getter es un método público que lee un atributo privado y lo devuelve. Su única misión es darte el valor sin permitir modificarlo.
        // La convención en Java es estricta y debes seguirla. Si el atributo se llama precio, el getter se llama getPrecio. Si se llama nombre, el getter se llama getNombre. Siempre con get adelante y la primera letra del atributo en mayúscula.
        // javapublic class Producto {
        //     private String nombre;
        //     private double precio;
        //     private int stock;
        //     private boolean activo;
        // 
        //     public Producto(String nombre, double precio, int stock, boolean activo) {
        //         this.nombre = nombre;
        //         this.precio = precio;
        //         this.stock = stock;
        //         this.activo = activo;
        //     }
        // 
        //     // Getter de nombre
        //     public String getNombre() {
        //         return this.nombre;
        //     }
        // 
        //     // Getter de precio
        //     public double getPrecio() {
        //         return this.precio;
        //     }
        // 
        //     // Getter de stock
        //     public int getStock() {
        //         return this.stock;
        //     }
        // }
        // Vamos a desmenuzar un getter, línea por línea:
        // javapublic double getPrecio() {
        //     return this.precio;
        // }
        // 
        // public → este método sí es accesible desde fuera. Esa es la idea, dar acceso controlado.
        // double → el tipo de dato que retorna. Como el atributo precio es double, el getter retorna double. Siempre coinciden.
        // getPrecio → el nombre. La convención es get + nombre del atributo con la primera letra en mayúscula.
        // () → no recibe parámetros. Un getter solo entrega información, no necesita nada.
        // return this.precio → devuelve el valor del atributo privado.
        // 
        // Y se usa así desde otra clase:
        // javaProducto laptop = new Producto("Laptop HP", 1500.0, 10, true);
        // 
        // double precioActual = laptop.getPrecio();
        // System.out.println("Precio: " + precioActual); // Precio: 1500.0
        // 
        // // O directamente
        // System.out.println(laptop.getPrecio()); // 1500.0
        // System.out.println(laptop.getNombre()); // Laptop HP
        // 
        // Caso especial — getters para boolean
        // Cuando el atributo es boolean, la convención cambia ligeramente. En vez de get se usa is:
        // javaprivate boolean activo;
        // 
        // // ✅ Convención correcta para boolean
        // public boolean isActivo() {
        //     return this.activo;
        // }
        // Se llama así porque se lee mejor en código:
        // javaif (laptop.isActivo()) {
        //     System.out.println("El producto está activo");
        // }
        // Lee la condición: "si laptop está activo". Más natural que if (laptop.getActivo()).

        // 💡 Demostración real en ejecución de la Parte 4 (usando la clase Producto final de abajo):
        {
            System.out.println("\n--- Parte 4: Demostración de Getters y caso especial Boolean ---");
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true);
            
            double precioActual = laptop.getPrecio();
            System.out.println("Precio (variable): " + precioActual); // Precio: 1500.0
            
            System.out.println("Precio (directo): " + laptop.getPrecio()); // 1500.0
            System.out.println("Nombre (directo): " + laptop.getNombre()); // Laptop HP
            
            if (laptop.isActivo()) {
                System.out.println("El producto está activo (Verificación con isActivo())");
            }
        }

        // =========================================================================
        // Parte 5 — Setters, las puertas para MODIFICAR
        // =========================================================================
        // Un setter es un método público que modifica un atributo privado. Pero la gracia es que puedes validar antes de modificar. Esto es lo que protege tu objeto de quedar en estado inválido.
        // La convención es la misma pero con set en vez de get:
        // javapublic class Producto {
        //     private String nombre;
        //     private double precio;
        //     private int stock;
        //     private boolean activo;
        // 
        //     // Setter de nombre — sin validación por ahora
        //     public void setNombre(String nombre) {
        //         this.nombre = nombre;
        //     }
        // 
        //     // Setter de precio — sin validación por ahora
        //     public void setPrecio(double precio) {
        //         this.precio = precio;
        //     }
        // }
        // Vamos a desmenuzar un setter:
        // javapublic void setPrecio(double precio) {
        //     this.precio = precio;
        // }
        // 
        // public → accesible desde fuera.
        // void → no retorna nada. Solo modifica el atributo internamente.
        // setPrecio → nombre con set + nombre del atributo con primera letra en mayúscula.
        // (double precio) → recibe un parámetro del mismo tipo que el atributo.
        // this.precio = precio → asigna el parámetro al atributo. Aquí aparece this por la misma razón que en el constructor: para diferenciar el atributo del parámetro cuando tienen el mismo nombre.
        // 
        // Y se usa así:
        // javaProducto laptop = new Producto("Laptop HP", 1500.0, 10, true);
        // 
        // laptop.setPrecio(1400.0);
        // System.out.println(laptop.getPrecio()); // 1400.0

        // 💡 Demostración real en ejecución de la Parte 5:
        {
            System.out.println("\n--- Parte 5: Demostración de Setters básicos ---");
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true);
            laptop.setPrecio(1400.0);
            System.out.println("Precio modificado: " + laptop.getPrecio()); // 1400.0
        }

        // =========================================================================
        // Parte 6 — Setters CON validación, el verdadero poder
        // =========================================================================
        // Hasta aquí el setter no es muy diferente a tener el atributo público. La magia aparece cuando validas antes de modificar:
        // javapublic void setPrecio(double precio) {
        //     if (precio < 0) {
        //         System.out.println("Error: el precio no puede ser negativo");
        //         return; // no modifica el atributo
        //     }
        //     this.precio = precio;
        // }
        // Ahora mira la diferencia:
        // javaProducto laptop = new Producto("Laptop HP", 1500.0, 10, true);
        // 
        // laptop.setPrecio(1400.0);   // ✅ funciona, precio = 1400.0
        // laptop.setPrecio(-500.0);   // ❌ rechazado, precio sigue siendo 1400.0
        // 
        // System.out.println(laptop.getPrecio()); // 1400.0
        // El objeto nunca queda con precio negativo. Es imposible romperlo. Esa es la verdadera razón por la que existe el encapsulamiento.
        //
        // Setter con validación completa para todos los atributos:
        // (Ver la clase Producto al final de este archivo)

        // =========================================================================
        // Parte 7 — Lanzar excepciones en los setters (forma profesional)
        // =========================================================================
        // En proyectos reales y especialmente en Spring Boot no se usa System.out.println para errores. Se usan excepciones. Recuerdas el Tema 5 de Fase 1, ¿verdad? Aquí es donde conecta todo:
        // javapublic void setPrecio(double precio) {
        //     if (precio < 0) {
        //         throw new IllegalArgumentException("El precio no puede ser negativo: " + precio);
        //     }
        //     this.precio = precio;
        // }
        // 
        // public void setNombre(String nombre) {
        //     if (nombre == null || nombre.isBlank()) {
        //         throw new IllegalArgumentException("El nombre no puede estar vacío");
        //     }
        //     this.nombre = nombre;
        // }
        // 
        // public void setStock(int stock) {
        //     if (stock < 0) {
        //         throw new IllegalArgumentException("El stock no puede ser negativo: " + stock);
        //     }
        //     this.stock = stock;
        // }
        // IllegalArgumentException es una excepción de Java que significa exactamente eso: "argumento ilegal o inválido". Es la excepción estándar para validaciones en setters.
        // Ahora cuando alguien intente usar un valor inválido:
        // javalaptop.setPrecio(-500.0);
        // // ❌ Lanza: IllegalArgumentException: El precio no puede ser negativo: -500.0
        // El programa se detiene si no se atrapa con try-catch. Esto fuerza al programador a manejar el error correctamente.

        // 💡 Demostración real en ejecución de la Parte 7 (Atrapando la excepción con try-catch):
        {
            System.out.println("\n--- Parte 7: Lanzar excepciones en Setters (IllegalArgumentException) ---");
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true);
            try {
                laptop.setPrecio(-500.0);
            } catch (IllegalArgumentException e) {
                System.out.println("Error esperado capturado exitosamente: " + e.getMessage());
            }
        }

        // =========================================================================
        // Parte 8 — Los constructores también deben validar
        // =========================================================================
        // Hay una cosa importante que mucha gente olvida. Si en los setters validas pero en el constructor no, el objeto puede nacer corrupto:
        // // ❌ Constructor sin validación
        // public Producto(String nombre, double precio, int stock, boolean activo) {
        //     this.nombre = nombre;
        //     this.precio = precio;
        //     this.stock = stock;
        //     this.activo = activo;
        // }
        // 
        // Producto creado con precio negativo desde el inicio
        // Producto malo = new Producto("Algo", -500.0, -10, true); // ❌ permite crear basura
        //
        // La solución profesional es usar los setters dentro del constructor para reutilizar las validaciones:
        // javapublic Producto(String nombre, double precio, int stock, boolean activo) {
        //     setNombre(nombre);    // valida antes de asignar
        //     setPrecio(precio);    // valida antes de asignar
        //     setStock(stock);      // valida antes de asignar
        //     setActivo(activo);
        // }
        // Ahora es imposible crear un Producto con valores inválidos, ni en el constructor ni después:
        // javaProducto malo = new Producto("Algo", -500.0, 10, true);
        // // ❌ Lanza: IllegalArgumentException: El precio no puede ser negativo: -500.0

        // 💡 Demostración real en ejecución de la Parte 8:
        {
            System.out.println("\n--- Parte 8: Validación en Constructores ---");
            try {
                Producto malo = new Producto("Algo", -500.0, 10, true);
            } catch (IllegalArgumentException e) {
                System.out.println("Nacimiento de producto corrupto impedido: " + e.getMessage());
            }
        }

        // =========================================================================
        // Parte 9 — Ejemplo completo profesional
        // =========================================================================
        // Te dejo la versión final completa de la clase Producto con todo lo aprendido:
        // (Ver la clase Producto al final del archivo)
        // Y se usa así desde fuera:
        {
            System.out.println("\n--- Parte 9: Ejemplo Completo en Ejecución (Tus apuntes) ---");
            Producto laptop = new Producto("Laptop HP", 1500.0, 10, true);

            // Leer con getters
            System.out.println(laptop.getNombre());  // Laptop HP
            System.out.println(laptop.getPrecio());  // 1500.0
            System.out.println(laptop.isActivo());   // true

            // Modificar con setters validados
            laptop.setPrecio(1400.0);
            System.out.println(laptop.getPrecio());  // 1400.0

            // Intentar modificar con valor inválido
            try {
                laptop.setPrecio(-100.0);
            } catch (IllegalArgumentException e) {
                System.out.println("Error capturado: " + e.getMessage());
            }

            System.out.println(laptop.getPrecio()); // sigue siendo 1400.0
        }

        // =========================================================================
        // Parte 10 — Cuándo NO crear setter
        // =========================================================================
        // Aquí va una decisión profesional importante. No todos los atributos necesitan setter. Pregúntate siempre:
        // ¿Tiene sentido que este valor cambie después de crear el objeto?
        // 
        // SÍ → crea setter
        // NO → solo crea getter (el atributo se asigna en el constructor y nunca cambia)
        // Por ejemplo, el id de un usuario no debería cambiar nunca. La fechaCreacion tampoco. Para esos no crees setter:
        // (Ver la clase Usuario al final del archivo)
        // Resultado: nadie puede cambiar el id ni la fecha de creación una vez creado el usuario. Esto se llama hacer un atributo inmutable.

        // 💡 Demostración real en ejecución de la Parte 10:
        {
            System.out.println("\n--- Parte 10: Inmutabilidad (Atributos sin Setter) ---");
            Usuario usuario = new Usuario(101, "mati@ejemplo.com");
            System.out.println("ID Usuario: " + usuario.getId()); // 101
            System.out.println("Fecha de Creación: " + usuario.getFechaCreacion()); // 2024-01-01
            System.out.println("Email: " + usuario.getEmail());
            
            // Cambiando solo lo mutable
            usuario.setEmail("mati_nuevo@ejemplo.com");
            System.out.println("Email Actualizado: " + usuario.getEmail());
            
            // id y fechaCreacion están protegidos contra escritura externa porque no tienen set.
        }

        // =========================================================================
        // Parte 11 — Conexión con Spring Boot
        // =========================================================================
        // En Spring Boot esto es literalmente lo que harás. Cada entidad, cada DTO, cada modelo tendrá exactamente esta estructura. Atributos privados, constructor, getters y setters.
        // De hecho hay tantos casos donde necesitas getters y setters que existe una librería llamada Lombok que los genera automáticamente con una anotación. Pero antes de usar Lombok debes entender qué hace, y eso es exactamente lo que acabas de aprender.
        // En Java 21 hay una alternativa moderna llamada Records que escribimos en el documento avanzado. Los Records son una forma corta de crear clases con atributos inmutables y getters automáticos. Pero los Records solo sirven para clases inmutables. Cuando necesites setters seguirás usando clases tradicionales como las que ya dominas.

        // =========================================================================
        // Resumen completo del Tema 2
        // =========================================================================
        // ENCAPSULAMIENTO    →  proteger los atributos del objeto
        // 
        // private            →  atributo solo accesible dentro de la clase
        // public             →  método accesible desde cualquier parte
        // 
        // Getter             →  método para LEER un atributo privado
        //                       retorna el tipo del atributo
        //                       no recibe parámetros
        //                       convención: get + Nombre  (o is + Nombre si es boolean)
        // 
        // Setter             →  método para MODIFICAR un atributo privado
        //                       retorna void
        //                       recibe un parámetro del tipo del atributo
        //                       convención: set + Nombre
        //                       DEBE validar antes de asignar
        // 
        // Validaciones       →  lanzar IllegalArgumentException si el dato es inválido
        // 
        // Constructor        →  debe usar los setters para reutilizar validaciones
        // 
        // Sin setter         →  atributo inmutable, no puede cambiar después de creado
    }

// =============================================================================
// 💡 Clases de Apoyo: Implementación real de tus apuntes
// =============================================================================

// Clase sin encapsular usada en la Parte 1
static class ProductoPublico {
    String nombre;
    double precio;
    int stock;
    boolean activo;

    public ProductoPublico(String nombre, double precio, int stock, boolean activo) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.activo = activo;
    }
}

// Clase Producto profesional completa (Parte 9)
static class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private boolean activo;

    public Producto(String nombre, double precio, int stock, boolean activo) {
        setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
        setActivo(activo);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo: " + precio);
        }
        this.precio = precio;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo: " + stock);
        }
        this.stock = stock;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

// Clase Usuario inmutable en ciertos atributos (Parte 10)
static class Usuario {
    private int id;                  // nunca cambia
    private String fechaCreacion;    // nunca cambia
    private String email;            // sí puede cambiar

    public Usuario(int id, String email) {
        this.id = id;
        this.email = email;
        this.fechaCreacion = "2024-01-01";
    }

    // Solo getters para id y fechaCreacion (sin setters)
    public int getId() { return this.id; }
    public String getFechaCreacion() { return this.fechaCreacion; }

    // Getter y setter para email
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
}
}
