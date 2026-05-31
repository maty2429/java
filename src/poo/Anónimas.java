package poo;

public class Anónimas {
    public static void main(String[] args) {
        
        // =========================================================================
        // Clases Internas, Anónimas y Estáticas Anidadas
        // =========================================================================

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // Hasta ahora todas las clases que creaste están en archivos separados:
        // Producto.java
        // Usuario.java
        // Pedido.java
        // Pero a veces necesitas una clase muy chiquita que solo se usa dentro de otra clase. Crear un archivo completo para ella sería exagerado.
        // Imagina que tienes una clase Carrito y necesita representar internamente cada item del carrito. Ese concepto solo tiene sentido dentro del carrito. Fuera no se usa para nada.
        // Sin clases internas tendrías que crear ItemCarrito.java como archivo separado, exponer esa clase a todo el proyecto, aunque solo la use el carrito. Es contaminación del espacio de nombres.
        // La solución es declarar clases dentro de otras clases. Eso es exactamente lo que veremos.

        // =========================================================================
        // Parte 2 — Los cuatro tipos de clases anidadas en Java
        // =========================================================================
        // Java permite cuatro tipos diferentes. Te los presento todos primero y luego cada uno con detalle:
        // 1. Clase estática anidada    →  clase definida dentro de otra con la palabra static
        // no necesita una instancia de la clase externa
        // 
        // 2. Clase interna             →  clase definida dentro de otra sin static
        // necesita una instancia de la clase externa
        // 
        // 3. Clase anónima             →  clase sin nombre, creada en el momento que se usa
        // muy común con interfaces
        // 
        // 4. Clase local               →  clase definida dentro de un método
        // solo existe dentro de ese método
        // De estas cuatro, las que más usarás son la estática anidada y la anónima. Las otras dos las verás menos pero es importante saberlas.

        // =========================================================================
        // Parte 3 — Clase estática anidada (static nested class)
        // =========================================================================
        // Es la más simple. Es una clase definida dentro de otra con la palabra static. La palabra static significa lo mismo que en métodos: no necesita una instancia para existir.
        // 
        // public class Carrito {
        //     private String dueño;
        //     private double total;
        // 
        //     // Clase estática anidada
        //     public static class Item {
        //         private String producto;
        //         private int cantidad;
        // 
        //         public Item(String producto, int cantidad) {
        //             this.producto = producto;
        //             this.cantidad = cantidad;
        //         }
        // 
        //         public String getProducto() { return this.producto; }
        //         public int getCantidad() { return this.cantidad; }
        //     }
        // 
        //     // resto del carrito
        //     public Carrito(String dueño) {
        //         this.dueño = dueño;
        //     }
        // }
        // 
        // Línea por línea de lo nuevo:
        // public static class Item {
        // 
        // public → modificador de acceso normal.
        // static → palabra clave que la convierte en estática anidada.
        // class Item → es una clase como cualquier otra.
        // 
        // Lo importante: como tiene static, se puede crear sin necesidad de un Carrito. Se usa así desde fuera:
        // Carrito.Item item = new Carrito.Item("Laptop", 2);
        // Nota la sintaxis. Para acceder a la clase anidada escribes ClaseExterna.ClaseAnidada. Es como decirle a Java "la clase Item que está dentro de Carrito".
        // Otra forma con import:
        // import com.ejemplo.Carrito.Item;
        // 
        // Item item = new Item("Laptop", 2);
        // 
        // Cuándo usar clases estáticas anidadas
        // Cuando una clase conceptualmente pertenece a otra pero no necesita acceder a sus datos internos:
        // Carrito tiene Items   →  Item es estática anidada dentro de Carrito
        // Map tiene Entry       →  Map.Entry es estática anidada dentro de Map (real en Java)
        // Builder pattern       →  el Builder suele ser estática anidada dentro de la clase
        // En Spring Boot las verás mucho en patrones Builder y en clases auxiliares de configuración. Por ejemplo:
        // HttpHeaders.Builder
        // MockMvcBuilders.MockMvcConfigurer
        // Son clases que solo tienen sentido dentro de su clase contenedora.

        // 💡 Demostración real de la Parte 3 en ejecución:
        {
            System.out.println("=== Parte 3: Demostración de Clase Estática Anidada ===");
            // Nota la sintaxis para crear el item estático sin instanciar la clase contenedora
            CarritoEstatico.Item item = new CarritoEstatico.Item("Laptop HP", 2);
            System.out.println("Item creado: " + item.getProducto() + ", Cantidad: " + item.getCantidad());
            System.out.println();
        }

        // =========================================================================
        // Parte 4 — Clase interna (inner class)
        // =========================================================================
        // Es como la estática anidada pero sin la palabra static. Esta diferencia parece pequeña pero cambia todo:
        // 
        // public class Carrito {
        //     private String dueño;
        //     private double total;
        // 
        //     // Clase interna NO static
        //     public class Item {
        //         private String producto;
        //         private int cantidad;
        // 
        //         public Item(String producto, int cantidad) {
        //             this.producto = producto;
        //             this.cantidad = cantidad;
        //         }
        // 
        //         public void mostrarInfo() {
        //             // PUEDE acceder a atributos del Carrito directamente
        //             System.out.println("Item de " + dueño);
        //             System.out.println("Producto: " + producto);
        //         }
        //     }
        // }
        // 
        // Mira la diferencia importante. El método mostrarInfo() accede a dueño que es un atributo de Carrito. Esto solo funciona en clases internas sin static. Una clase estática anidada no puede acceder a los atributos de la clase externa.
        // ¿Por qué? Porque una clase interna está vinculada a una instancia de la clase externa. Cada Item nace ligado a un Carrito específico. Por eso puede ver lo que ese carrito tiene.
        // Para crear una clase interna necesitas primero crear la clase externa:
        // Carrito carrito = new Carrito("Juan");
        // Carrito.Item item = carrito.new Item("Laptop", 2);
        // Mira la sintaxis rara: carrito.new Item(...). Es la forma de Java de decir "crea un Item asociado a este carrito específico". Es una sintaxis poco común porque las clases internas no se usan tanto.
        // 
        // Diferencia clave: estática anidada vs interna
        // Estática anidada    Interna (sin static)
        // ─────────────────   ─────────────────────
        // Lleva la palabra static       ✅                   ❌
        // Se puede crear sin instancia  ✅                   ❌
        // de la clase externa
        // Puede acceder a atributos     ❌                   ✅
        // de la clase externa
        // Sintaxis para crear           new Externa.Interna() externa.new Interna()
        // Regla práctica: por defecto usa estática anidada salvo que realmente necesites acceder a los atributos de la clase externa. La estática anidada es más limpia y más eficiente en memoria.

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: Demostración de Clase Interna (NO static) ===");
            // Para crear una clase interna, primero necesitamos la instancia de la clase externa:
            CarritoInterno carrito = new CarritoInterno("Juan");
            // Sintaxis especial: carrito.new Item(...)
            CarritoInterno.Item item = carrito.new Item("Laptop", 2);
            item.mostrarInfo();
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — Clase anónima, la más usada de todas
        // =========================================================================
        // Aquí entramos al tema más importante de la lección. Las clases anónimas son clases sin nombre que se crean en el mismo momento en que se usan.
        // ¿Cuándo las necesitas? Cuando necesitas crear una única instancia de algo que implementa una interfaz o extiende una clase, sin tener que crear un archivo completo para ella.
        // Sin clases anónimas tendrías que hacer esto. Imagina que tienes una interfaz Saludable:
        // 
        // public interface Saludable {
        //     void saludar();
        // }
        // 
        // Para usarla normalmente crearías una clase entera:
        // 
        // public class SaludadorEspañol implements Saludable {
        //     @Override
        //     public void saludar() {
        //         System.out.println("Hola mundo");
        //     }
        // }
        // 
        // // Y usarla
        // Saludable s = new SaludadorEspañol();
        // s.saludar();
        // 
        // Pero si solo necesitas usar esa implementación una sola vez en todo el proyecto, crear una clase entera es exagerado. Con clase anónima haces lo mismo en el momento:
        // 
        // Saludable s = new Saludable() {
        //     @Override
        //     public void saludar() {
        //         System.out.println("Hola mundo");
        //     }
        // };
        // 
        // s.saludar();
        // 
        // Vamos a desmenuzar esa sintaxis nueva con cuidado:
        // 
        // Saludable s = new Saludable() {
        //     @Override
        //     public void saludar() {
        //         System.out.println("Hola mundo");
        //     }
        // };
        // 
        // Parte 1:
        // Saludable s = new Saludable() {
        // Aquí pasan varias cosas a la vez:
        // 
        // new Saludable() → parece que estás creando una instancia de una interfaz, lo cual es imposible normalmente.
        // Las llaves { que siguen → cambian todo. Le dicen a Java "voy a crear aquí mismo una clase que implementa Saludable, sin nombre".
        // 
        // Parte 2:
        //     @Override
        //     public void saludar() {
        //         System.out.println("Hola mundo");
        //     }
        // };
        // Aquí está la implementación de la interfaz. Es como un mini-cuerpo de clase. Termina con }; (la llave que cierra la clase anónima, y el punto y coma que cierra la instrucción Saludable s = ...).
        // Internamente Java genera una clase con un nombre raro tipo MainClass$1 que implementa Saludable, pero tú no te enteras. Por eso se llama "anónima".
        // 
        // Otro ejemplo con clase abstracta
        // Las anónimas también funcionan con clases abstractas:
        // 
        // public abstract class Animal {
        //     public abstract void hacerSonido();
        // }
        // 
        // // Clase anónima que extiende Animal
        // Animal misterioso = new Animal() {
        //     @Override
        //     public void hacerSonido() {
        //         System.out.println("Sonido raro");
        //     }
        // };
        // 
        // misterioso.hacerSonido(); // Sonido raro
        // 
        // Mismo principio. Creas una subclase de Animal en el momento, sin nombre, con la implementación que necesitas.
        // 
        // Por qué las clases anónimas son tan importantes en Spring Boot
        // En proyectos de Java tradicionales y Spring Boot antiguos las clases anónimas eran omnipresentes. Mira un ejemplo de listeners de eventos:
        // 
        // boton.addEventListener(new EventListener() {
        //     @Override
        //     public void onClick() {
        //         System.out.println("Botón presionado");
        //     }
        // });
        // 
        // Esto se usaba constantemente. Pero desde Java 8 apareció algo aún más corto que reemplazó muchas clases anónimas: las lambdas.

        // 💡 Demostración real de la Parte 5 en ejecución:
        {
            System.out.println("=== Parte 5: Demostración de Clases Anónimas ===");
            
            // 1. Caso de Interfaz: Usando la clase concreta SaludadorEspañol
            Saludable s1 = new SaludadorEspañol();
            s1.saludar();

            // 2. Caso de Interfaz: Usando una Clase Anónima en el momento
            Saludable s2 = new Saludable() {
                @Override
                public void saludar() {
                    System.out.println("Hola mundo (desde clase anónima)");
                }
            };
            s2.saludar();

            // 3. Caso de Clase Abstracta: Usando Clase Anónima para extender Animal
            Animal misterioso = new Animal() {
                @Override
                public void hacerSonido() {
                    System.out.println("Sonido raro (desde clase anónima)");
                }
            };
            misterioso.hacerSonido();
            System.out.println();
        }

        // =========================================================================
        // Parte 6 — Lambdas, la evolución de las clases anónimas
        // =========================================================================
        // Las lambdas son una versión ultra-compacta de las clases anónimas. Solo funcionan con interfaces que tengan exactamente un método abstracto (llamadas "interfaces funcionales"):
        // 
        // public interface Saludable {
        //     void saludar();
        // }
        // 
        // Recuerda la clase anónima:
        // 
        // Saludable s = new Saludable() {
        //     @Override
        //     public void saludar() {
        //         System.out.println("Hola");
        //     }
        // };
        // 
        // Y ahora la lambda equivalente:
        // 
        // Saludable s = () -> System.out.println("Hola");
        // 
        // ¡Una sola línea! Mira lo que cambió:
        // new Saludable() { @Override public void saludar() { ... } }
        //               ↓
        //               () -> { ... }
        // 
        // Java sabe que s es de tipo Saludable. Sabe que Saludable solo tiene un método (saludar). Sabe que () -> ... define ese método. No necesita nada más.
        // 
        // Sintaxis básica de lambdas:
        // // Sin parámetros
        // () -> System.out.println("Hola")
        // 
        // // Con un parámetro
        // nombre -> System.out.println("Hola " + nombre)
        // 
        // // Con varios parámetros
        // (a, b) -> a + b
        // 
        // // Con cuerpo de varias líneas
        // (a, b) -> {
        //     int suma = a + b;
        //     return suma;
        // }
        // 
        // Las lambdas son uno de los pilares de Java moderno y la base de la Stream API que verás en la Fase 3. Pero las clases anónimas siguen existiendo y siguen siendo útiles cuando:
        // La interfaz tiene MÁS de un método (no se puede usar lambda)
        // Necesitas atributos propios en la clase anónima
        // Quieres heredar de una clase abstracta

        // 💡 Demostración real de la Parte 6 en ejecución:
        {
            System.out.println("=== Parte 6: Demostración de Lambdas ===");
            // Lambda equivalente de una sola línea
            Saludable s = () -> System.out.println("Hola (desde una lambda)");
            s.saludar();
            System.out.println();
        }

        // =========================================================================
        // Parte 7 — Clase local, dentro de un método
        // =========================================================================
        // Es el tipo menos común. Es una clase declarada dentro de un método:
        // 
        // public class Servicio {
        //     public void procesar() {
        //         // Clase local
        //         class Resultado {
        //             String mensaje;
        //             int codigo;
        // 
        //             Resultado(String mensaje, int codigo) {
        //                 this.mensaje = mensaje;
        //                 this.codigo = codigo;
        //             }
        //         }
        // 
        //         Resultado r = new Resultado("OK", 200);
        //         System.out.println(r.mensaje + " - " + r.codigo);
        //     }
        // }
        // 
        // La clase Resultado solo existe dentro del método procesar. Fuera del método no se puede usar.
        // Estas se ven muy poco en proyectos reales. Solo las menciono para que sepas que existen. Si las ves en algún código antiguo no te sorprendas.

        // 💡 Demostración real de la Parte 7 en ejecución:
        {
            System.out.println("=== Parte 7: Demostración de Clase Local ===");
            ServicioLocal servicio = new ServicioLocal();
            servicio.procesar();
            System.out.println();
        }

        // =========================================================================
        // Parte 8 — Cuándo usar cada tipo
        // =========================================================================
        // Una guía de decisión simple:
        // Necesito una clase pequeña que solo se usa dentro de otra clase
        // → Clase estática anidada
        // Ejemplo: Carrito.Item, Map.Entry
        // 
        // Necesito una clase que dependa del estado de otra clase
        // → Clase interna (sin static)
        // Ejemplo: una clase para iterar sobre datos de la externa
        // 
        // Necesito una implementación única de una interfaz solo en un lugar
        // → Clase anónima
        // Ejemplo: un listener específico para un solo botón
        // 
        // La interfaz tiene exactamente un método abstracto
        // → Lambda (más limpia que la clase anónima)
        // Ejemplo: un Runnable, un Comparator, un Predicate
        // 
        // Necesito una clase auxiliar dentro de un método específico
        // → Clase local
        // Casi nunca en proyectos modernos

        // =========================================================================
        // Parte 9 — Ejemplo completo donde se ven todas
        // =========================================================================
        // Te muestro un ejemplo que junta varios tipos para que veas las diferencias:
        // 
        // public class Servicio {
        // 
        //     private String nombre;
        // 
        //     public Servicio(String nombre) {
        //         this.nombre = nombre;
        //     }
        // 
        //     // 1. Clase estática anidada - no depende de instancia
        //     public static class Configuracion {
        //         private String url;
        //         private int timeout;
        // 
        //         public Configuracion(String url, int timeout) {
        //             this.url = url;
        //             this.timeout = timeout;
        //         }
        // 
        //         public void mostrar() {
        //             System.out.println("URL: " + url + ", Timeout: " + timeout);
        //             // No puede acceder a nombre del Servicio porque es static
        //         }
        //     }
        // 
        //     // 2. Clase interna - depende de instancia
        //     public class Conexion {
        //         private boolean activa;
        // 
        //         public Conexion(boolean activa) {
        //             this.activa = activa;
        //         }
        // 
        //         public void mostrar() {
        //             // SÍ puede acceder a nombre del Servicio porque NO es static
        //             System.out.println("Conexión del servicio " + nombre + ": " + activa);
        //         }
        //     }
        // 
        //     public void ejecutar() {
        //         // 3. Clase anónima - implementa interfaz en el momento
        //         Runnable tarea = new Runnable() {
        //             @Override
        //             public void run() {
        //                 System.out.println("Ejecutando tarea de " + nombre);
        //             }
        //         };
        // 
        //         tarea.run();
        // 
        //         // 4. Lo mismo con lambda - más corto
        //         Runnable tarea2 = () -> System.out.println("Tarea con lambda en " + nombre);
        //         tarea2.run();
        //     }
        // }
        // 
        // Y en el main:
        // public class Main {
        //     public static void main(String[] args) {
        // 
        //         // Crear y usar la estática anidada
        //         Servicio.Configuracion config = new Servicio.Configuracion("http://api.com", 5000);
        //         config.mostrar();
        // 
        //         // Crear el servicio
        //         Servicio servicio = new Servicio("ServicioPagos");
        // 
        //         // Crear y usar la interna
        //         Servicio.Conexion conn = servicio.new Conexion(true);
        //         conn.mostrar();
        // 
        //         // Ejecutar el método que usa anónimas y lambdas
        //         servicio.ejecutar();
        //     }
        // }
        // 
        // Salida:
        // URL: http://api.com, Timeout: 5000
        // Conexión del servicio ServicioPagos: true
        // Ejecutando tarea de ServicioPagos
        // Tarea con lambda en ServicioPagos

        // 💡 Demostración real de la Parte 9 en ejecución:
        {
            System.out.println("=== Parte 9: Demostración Completa Combinada ===");
            
            // Crear y usar la estática anidada
            Servicio.Configuracion config = new Servicio.Configuracion("http://api.com", 5000);
            config.mostrar();

            // Crear el servicio
            Servicio servicio = new Servicio("ServicioPagos");

            // Crear y usar la interna
            Servicio.Conexion conn = servicio.new Conexion(true);
            conn.mostrar();

            // Ejecutar el método que usa anónimas y lambdas
            servicio.ejecutar();
            System.out.println();
        }

        // =========================================================================
        // Parte 10 — Conexión con Spring Boot
        // =========================================================================
        // En Spring Boot verás estos patrones constantemente:
        // Clases estáticas anidadas:
        // Para configuraciones internas, para implementar el patrón Builder, para clases auxiliares:
        // Spring tiene HttpHeaders, MockMvc, varios Builder.builder() que son estáticas anidadas
        // Lombok genera builders como clases estáticas anidadas
        // Clases anónimas:
        // Para implementar interfaces que se usan una sola vez en un lugar específico:
        // ResponseBodyAdvice
        // HttpMessageConverter implementaciones puntuales
        // Authentication providers personalizados
        // Lambdas:
        // En todas partes. Spring Boot moderno está lleno de lambdas:
        // .stream().filter(p -> p.getPrecio() > 100)
        // .map(producto -> producto.getNombre())
        // SecurityFilterChain con lambdas para configurar reglas
        // Sin entender clases anónimas y lambdas el código de Spring Boot moderno parece magia.

        // =========================================================================
        // Resumen completo del Tema 7
        // =========================================================================
        // Clases anidadas en Java - cuatro tipos:
        // 
        // 1. ESTÁTICA ANIDADA      static nested class
        // ─────────────────
        // ✅ Lleva la palabra static
        // ✅ Se crea sin instancia de la clase externa
        // ❌ No puede acceder a atributos de la externa
        // 📌 Uso: clases auxiliares como Builder, Configuracion, Item
        // 
        // 2. INTERNA               inner class
        // ─────────────
        // ❌ No lleva static
        // ❌ Necesita una instancia de la clase externa
        // ✅ Puede acceder a atributos de la externa
        // 📌 Uso: cuando la clase está estrechamente ligada a una instancia
        // 
        // 3. ANÓNIMA               anonymous class
        // ─────────
        // ✅ No tiene nombre, se crea en el momento
        // ✅ Implementa una interfaz o extiende una clase
        // ✅ Una sola instancia, único uso
        // 📌 Uso: listeners, callbacks, implementaciones puntuales
        // 
        // 4. LOCAL                 local class
        // ─────
        // ✅ Declarada dentro de un método
        // ✅ Solo existe en ese método
        // 📌 Uso: poco común, casi nunca en proyectos modernos
        // 
        // LAMBDAS - desde Java 8
        // ─────────────────────
        // ✅ Versión ultra-corta de clase anónima
        // ✅ Solo para interfaces con UN método abstracto
        // 📌 Sintaxis: (parametros) -> cuerpo
        // 📌 Reemplaza muchas clases anónimas en código moderno
        // 
        // Regla práctica para elegir:
        // Default                 → estática anidada
        // Necesitas la externa    → interna
        // Uso único               → anónima o lambda
        // Interfaz con 1 método   → lambda
        // Interfaz con varios     → anónima
        // Dentro de un método     → local (raro)
        
    }

    // =========================================================================
    // --- CLASES ANIDADAS ESTÁTICAS DE SOPORTE PARA ANÓNIMAS ---
    // =========================================================================

    // --- Parte 3 ---
    public static class CarritoEstatico {
        private String dueño;
        private double total;

        // Clase estática anidada
        public static class Item {
            private String producto;
            private int cantidad;

            public Item(String producto, int cantidad) {
                this.producto = producto;
                this.cantidad = cantidad;
            }

            public String getProducto() { return this.producto; }
            public int getCantidad() { return this.cantidad; }
        }

        // resto del carrito
        public CarritoEstatico(String dueño) {
            this.dueño = dueño;
        }
    }

    // --- Parte 4 ---
    public static class CarritoInterno {
        private String dueño;
        private double total;

        // Clase interna NO static
        public class Item {
            private String producto;
            private int cantidad;

            public Item(String producto, int cantidad) {
                this.producto = producto;
                this.cantidad = cantidad;
            }

            public void mostrarInfo() {
                // PUEDE acceder a atributos del Carrito directamente
                System.out.println("Item de " + dueño);
                System.out.println("Producto: " + producto);
            }
        }

        public CarritoInterno(String dueño) {
            this.dueño = dueño;
        }
    }

    // --- Parte 5 ---
    public interface Saludable {
        void saludar();
    }

    public static class SaludadorEspañol implements Saludable {
        @Override
        public void saludar() {
            System.out.println("Hola mundo (desde SaludadorEspañol)");
        }
    }

    public static abstract class Animal {
        public abstract void hacerSonido();
    }

    // --- Parte 7 ---
    public static class ServicioLocal {
        public void procesar() {
            // Clase local
            class Resultado {
                String mensaje;
                int codigo;

                Resultado(String mensaje, int codigo) {
                    this.mensaje = mensaje;
                    this.codigo = codigo;
                }
            }

            Resultado r = new Resultado("OK", 200);
            System.out.println(r.mensaje + " - " + r.codigo);
        }
    }

    // --- Parte 9 ---
    public static class Servicio {
        private String nombre;

        public Servicio(String nombre) {
            this.nombre = nombre;
        }

        // 1. Clase estática anidada - no depende de instancia
        public static class Configuracion {
            private String url;
            private int timeout;

            public Configuracion(String url, int timeout) {
                this.url = url;
                this.timeout = timeout;
            }

            public void mostrar() {
                System.out.println("URL: " + url + ", Timeout: " + timeout);
                // No puede acceder a nombre del Servicio porque es static
            }
        }

        // 2. Clase interna - depende de instancia
        public class Conexion {
            private boolean activa;

            public Conexion(boolean activa) {
                this.activa = activa;
            }

            public void mostrar() {
                // SÍ puede acceder a nombre del Servicio porque NO es static
                System.out.println("Conexión del servicio " + nombre + ": " + activa);
            }
        }

        public void ejecutar() {
            // 3. Clase anónima - implementa interfaz en el momento
            Runnable tarea = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Ejecutando tarea de " + nombre);
                }
            };

            tarea.run();

            // 4. Lo mismo con lambda - más corto
            Runnable tarea2 = () -> System.out.println("Tarea con lambda en " + nombre);
            tarea2.run();
        }
    }
}
