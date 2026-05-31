package poo;

public class Interfaces {
    public static void main(String[] args) {
        
        // =========================================================================
        // Clases Abstractas vs Interfaces
        // =========================================================================

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // Ya conoces dos herramientas:
        // Clase normal  →  define un molde completo con atributos y métodos con cuerpo
        // Interfaz      →  define un contrato puro, solo dice QUÉ debe hacerse
        // Pero hay una situación intermedia que ninguna de las dos resuelve bien.
        // Imagina que estás modelando animales. Todos los animales respiran de la misma forma. Pero cada animal hace un sonido diferente:
        // Perro    →  respira igual que cualquier animal, pero ladra
        // Gato     →  respira igual que cualquier animal, pero maúlla
        // Vaca     →  respira igual que cualquier animal, pero muge
        // Si usas una clase normal para Animal, tendrías que escribir el método hacerSonido() con algún cuerpo. Pero ¿cuál? Un animal genérico no tiene un sonido. Es absurdo.
        // Si usas una interfaz para Animal, podrías definir respirar() y hacerSonido() como contratos. Pero entonces cada hijo tendría que reescribir respirar() desde cero, aunque sea exactamente igual para todos. Es duplicación absurda.
        // Lo que necesitas es algo intermedio:
        // "Quiero definir respirar() CON cuerpo (porque es igual para todos)
        // y hacerSonido() SIN cuerpo (porque cada animal lo hace diferente)"
        // Para eso existen las clases abstractas.

        // =========================================================================
        // Parte 2 — Qué es una clase abstracta
        // =========================================================================
        // Una clase abstracta es un híbrido entre clase normal e interfaz. Puede tener:
        // ✅ Atributos comunes (como una clase normal)
        // ✅ Constructor (como una clase normal)
        // ✅ Métodos con cuerpo completo (como una clase normal)
        // ✅ Métodos sin cuerpo, abstractos (como una interfaz)
        // La diferencia clave con una clase normal: una clase abstracta NO se puede instanciar directamente. No puedes hacer new Animal(). Tienes que crear una clase hija que la extienda y que implemente sus métodos abstractos.
        // Una analogía perfecta: piensa en el concepto de "vehículo". El concepto vehículo no existe físicamente, no puedes comprar "un vehículo" en concreto. Compras un auto, una moto, un camión. Pero todos comparten cosas: tienen ruedas, tienen motor, se pueden encender. El concepto vehículo es abstracto, sirve para agrupar lo común pero no existe por sí solo.
        // Las clases abstractas son justamente eso. Definen lo común pero no pueden existir por sí solas. Solo existen a través de sus clases hijas.

        // =========================================================================
        // Parte 3 — Tu primera clase abstracta
        // =========================================================================
        // Vamos a crear el ejemplo del problema:
        // 
        // public abstract class Animal {
        //     protected String nombre;
        //     protected int edad;
        // 
        //     public Animal(String nombre, int edad) {
        //         this.nombre = nombre;
        //         this.edad = edad;
        //     }
        // 
        //     // Método CONCRETO - igual para todos los animales
        //     public void respirar() {
        //         System.out.println(nombre + " está respirando");
        //     }
        // 
        //     // Método ABSTRACTO - cada animal lo implementa diferente
        //     public abstract void hacerSonido();
        // }
        // 
        // Línea por línea:
        // Línea 1:
        // public abstract class Animal {
        // public → accesible desde cualquier parte.
        // abstract → la palabra clave. Significa "esta clase es abstracta, no se puede instanciar".
        // class Animal → el resto es como una clase normal.
        // 
        // Líneas 2 y 3:
        //     protected String nombre;
        //     protected int edad;
        // Atributos normales. Los marqué protected para que las hijas puedan accederlos directamente, pero podrían ser private con getters también.
        // 
        // Líneas 5 a 7:
        //     public Animal(String nombre, int edad) {
        //         this.nombre = nombre;
        //         this.edad = edad;
        //     }
        // Una clase abstracta sí puede tener constructor. Aunque no se pueda instanciar directamente, las hijas necesitan llamarlo con super(...) para inicializar los atributos heredados.
        // 
        // Líneas 9 a 12:
        //     public void respirar() {
        //         System.out.println(nombre + " está respirando");
        //     }
        // Método concreto, con cuerpo. Todas las clases hijas heredarán esta implementación tal cual. No tendrán que reescribirla.
        // 
        // Línea 14:
        //     public abstract void hacerSonido();
        // Aquí está la magia. Método abstracto:
        // public abstract → tiene la palabra abstract que lo marca como sin cuerpo.
        // void hacerSonido() → la firma del método.
        // ; → termina con punto y coma, sin llaves. Igual que en una interfaz.
        // Este método dice: "cualquier clase que extienda Animal está obligada a implementar hacerSonido".

        // =========================================================================
        // Parte 4 — Crear clases hijas
        // =========================================================================
        // Las hijas extienden a Animal con extends (como cualquier herencia) y deben implementar los métodos abstractos:
        // 
        // public class Perro extends Animal {
        //     public Perro(String nombre, int edad) {
        //         super(nombre, edad);
        //     }
        //     @Override
        //     public void hacerSonido() {
        //         System.out.println(nombre + " dice: Guau guau");
        //     }
        // }
        // 
        // public class Gato extends Animal {
        //     public Gato(String nombre, int edad) {
        //         super(nombre, edad);
        //     }
        //     @Override
        //     public void hacerSonido() {
        //         System.out.println(nombre + " dice: Miau");
        //     }
        // }
        // 
        // Mira el patrón:
        // Las hijas heredan respirar() automáticamente. No la reescriben.
        // Las hijas están obligadas a implementar hacerSonido(). Si no lo hacen, Java no compila.
        // 
        // Y en el main:
        // public class Main {
        //     public static void main(String[] args) {
        //         Animal a = new Animal("Genérico", 5); // ❌ ERROR - no se puede instanciar abstracta
        // 
        //         Perro firulais = new Perro("Firulais", 3);
        //         Gato whiskers = new Gato("Whiskers", 2);
        // 
        //         firulais.respirar();      // Firulais está respirando
        //         firulais.hacerSonido();   // Firulais dice: Guau guau
        // 
        //         whiskers.respirar();      // Whiskers está respirando
        //         whiskers.hacerSonido();   // Whiskers dice: Miau
        //     }
        // }
        // Mira lo bonito. respirar() se hereda gratis, igual para todos. hacerSonido() se obliga a implementar, diferente para cada uno. Lo común se reutiliza, lo específico se obliga a personalizar.

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: Demostración de Clases Hijas de una Abstracta ===");
            // Intentar instanciar la clase abstracta directamente causaría error de compilación:
            // Animal a = new Animal("Genérico", 5); // ❌ ERROR - Animal is abstract; cannot be instantiated

            Perro firulais = new Perro("Firulais", 3);
            Gato whiskers = new Gato("Whiskers", 2);

            firulais.respirar();      // Firulais está respirando
            firulais.hacerSonido();   // Firulais dice: Guau guau

            whiskers.respirar();      // Whiskers está respirando
            whiskers.hacerSonido();   // Whiskers dice: Miau
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — Qué pasa si la hija no implementa el método abstracto
        // =========================================================================
        // Si una clase hija no implementa todos los métodos abstractos del padre, ella misma debe ser declarada abstracta:
        // 
        // public abstract class Mamifero extends Animal {
        //     public Mamifero(String nombre, int edad) {
        //         super(nombre, edad);
        //     }
        //     // No implementa hacerSonido() porque también es abstracta
        // }
        // 
        // Esto crea una jerarquía abstracta. Mamifero hereda de Animal pero sigue siendo abstracta. La obligación de implementar hacerSonido() se pasa a las clases hijas de Mamifero:
        // 
        // public class Perro extends Mamifero {
        //     public Perro(String nombre, int edad) {
        //         super(nombre, edad);
        //     }
        //     @Override
        //     public void hacerSonido() {
        //         System.out.println("Guau guau");
        //     }
        // }
        // 
        // Esto es muy útil para crear niveles intermedios en la jerarquía sin obligar a implementar todo de inmediato.

        // 💡 Demostración real de la Parte 5 en ejecución:
        {
            System.out.println("=== Parte 5: Jerarquías de Clases Abstractas ===");
            // Perro hereda de Mamifero, que a su vez hereda de Animal
            Perro firu = new Perro("Firu", 4);
            firu.respirar();    // Heredado desde Animal a través de Mamifero
            firu.hacerSonido(); // Implementación concreta final obligatoria
            System.out.println();
        }

        // =========================================================================
        // Parte 6 — Comparación visual: clase, clase abstracta e interfaz
        // =========================================================================
        // Aquí está la tabla que más necesitas memorizar de Java:
        // Clase normal    Clase abstracta    Interfaz
        //               ─────────────   ───────────────    ────────
        // Se puede instanciar       ✅              ❌                 ❌
        // Puede tener atributos     ✅              ✅                 ⚠️ solo constantes
        // Puede tener constructor   ✅              ✅                 ❌
        // Métodos con cuerpo        ✅              ✅                 ⚠️ solo default
        // Métodos sin cuerpo        ❌              ✅                 ✅ (lo normal)
        // Una clase puede heredar
        // de varias                 N/A             ❌ solo una        ✅ varias

        // =========================================================================
        // Parte 7 — La pregunta clave: ¿clase abstracta o interfaz?
        // =========================================================================
        // Aquí está la decisión que más confunde a desarrolladores incluso con experiencia. Te doy reglas claras:
        // Usa CLASE ABSTRACTA cuando:
        // 1. Necesitas compartir código entre clases hijas.
        //    Si varias clases comparten métodos con la misma implementación y atributos, una clase abstracta evita duplicación:
        //    public abstract class Animal {
        //        protected String nombre;
        //        public void respirar() {              // código compartido
        //            System.out.println("Respirando");
        //        }
        //        public abstract void hacerSonido();   // específico de cada hija
        //    }
        // 2. Las clases hijas son variantes especializadas de un mismo concepto.
        //    Perro y Gato son tipos específicos de Animal. Existe una relación "ES UN" entre ellos:
        //    Perro ES UN Animal
        //    Gato ES UN Animal
        // 3. Necesitas atributos comunes con estado.
        //    Las clases abstractas pueden tener atributos normales con valores, como nombre y edad. Las interfaces no pueden tener atributos con estado mutable.
        // 4. Necesitas un constructor para inicializar la parte común.
        //    Las clases abstractas tienen constructor. Las interfaces no.
        // 
        // Usa INTERFAZ cuando:
        // 1. Quieres definir un contrato puro, sin imponer código compartido.
        //    Si solo necesitas decir "estas clases deben tener estos métodos" pero no quieres compartir implementación:
        //    public interface Imprimible {
        //        void imprimir();
        //    }
        // 2. Una clase necesita cumplir múltiples contratos.
        //    Como una clase solo puede extender de una clase, pero puede implementar muchas interfaces, esto es clave:
        //    public class Pedido implements Imprimible, Guardable, Enviable {
        //        // cumple tres contratos a la vez
        //    }
        // 3. Los implementadores no comparten una relación natural.
        //    Una interfaz puede ser implementada por clases que no tienen nada en común:
        //    Producto, Usuario y Pedido pueden ser todos Imprimibles
        //    sin que tengan ninguna relación entre sí
        // 4. Quieres lograr flexibilidad máxima en el diseño.
        //    Las interfaces son la base de la programación orientada a contratos. Permiten cambiar implementaciones sin tocar el código que las usa. Esto es lo que hace tan flexible a Spring Boot.

        // =========================================================================
        // Parte 8 — Reglas para decidir paso a paso
        // =========================================================================
        // Una decisión guiada:
        // Paso 1: ¿Las clases comparten código común (métodos con cuerpo)?
        // SÍ → considera clase abstracta
        // NO → considera interfaz
        // 
        // Paso 2: ¿Las clases comparten atributos con estado?
        // SÍ → clase abstracta
        // NO → interfaz
        // 
        // Paso 3: ¿Las clases tienen una relación natural ES UN?
        // SÍ → clase abstracta
        // NO → interfaz
        // 
        // Paso 4: ¿Una clase necesita cumplir varios "contratos" diferentes?
        // SÍ → interfaces (porque puedes implementar varias)
        // NO → cualquiera de las dos puede servir

        // =========================================================================
        // Parte 9 — Combinarlas, lo que se hace en proyectos reales
        // =========================================================================
        // En proyectos reales casi siempre se combinan. Es lo normal. Mira este ejemplo:
        // 
        // // Interfaces para definir capacidades
        // public interface Imprimible {
        //     void imprimir();
        // }
        // 
        // public interface Guardable {
        //     void guardar();
        // }
        // 
        // // Clase abstracta para compartir código entre productos
        // public abstract class Producto implements Imprimible, Guardable {
        //     protected String nombre;
        //     protected double precio;
        // 
        //     public Producto(String nombre, double precio) {
        //         this.nombre = nombre;
        //         this.precio = precio;
        //     }
        // 
        //     // Implementación común de imprimir
        //     @Override
        //     public void imprimir() {
        //         System.out.println("Producto: " + nombre + " - $" + precio);
        //     }
        // 
        //     // Implementación común de guardar
        //     @Override
        //     public void guardar() {
        //         System.out.println("Guardando " + nombre + " en base de datos");
        //     }
        // 
        //     // Método abstracto que cada producto debe implementar
        //     public abstract double calcularImpuesto();
        // }
        // 
        // public class ProductoElectronico extends Producto {
        //     public ProductoElectronico(String nombre, double precio) {
        //         super(nombre, precio);
        //     }
        //     @Override
        //     public double calcularImpuesto() {
        //         return precio * 0.19;  // 19% de impuesto
        //     }
        // }
        // 
        // public class ProductoLibro extends Producto {
        //     public ProductoLibro(String nombre, double precio) {
        //         super(nombre, precio);
        //     }
        //     @Override
        //     public double calcularImpuesto() {
        //         return 0;  // los libros no pagan impuesto
        //     }
        // }
        // 
        // Mira lo que logramos:
        // Las interfaces definen contratos genéricos (Imprimible, Guardable).
        // La clase abstracta agrupa lo común a productos (atributos, implementación de imprimir y guardar).
        // Las clases concretas solo se enfocan en lo específico (cómo se calcula el impuesto de cada tipo).
        // Esto es exactamente como están organizados los proyectos profesionales de Java y Spring Boot.

        // 💡 Demostración real de la Parte 9 en ejecución:
        {
            System.out.println("=== Parte 9: Demostración Combinando Interfaces y Clase Abstracta ===");
            ProductoElectronico laptop = new ProductoElectronico("MacBook Pro", 2000.0);
            ProductoLibro cleanCode = new ProductoLibro("Clean Code", 45.0);

            // Uso de contratos comunes (Imprimible)
            laptop.imprimir();
            cleanCode.imprimir();

            // Uso de contratos comunes (Guardable)
            laptop.guardar();
            cleanCode.guardar();

            // Lógica particular obligatoria de impuestos
            System.out.println("Impuesto Laptop: $" + laptop.calcularImpuesto()); // 19% -> 380
            System.out.println("Impuesto Libro: $" + cleanCode.calcularImpuesto());   // 0% -> 0
            System.out.println();
        }

        // =========================================================================
        // Parte 10 — Una clase abstracta sin métodos abstractos
        // =========================================================================
        // Algo interesante que vale la pena saber. Una clase puede ser abstracta aunque no tenga ningún método abstracto:
        // 
        // public abstract class BaseEntity {
        //     private int id;
        //     private String fechaCreacion;
        // 
        //     public BaseEntity(int id) {
        //         this.id = id;
        //         this.fechaCreacion = "2024-12-25";
        //     }
        // 
        //     public int getId() { return this.id; }
        //     public String getFechaCreacion() { return this.fechaCreacion; }
        // }
        // 
        // No tiene ningún método abstracto, pero la marcaste como abstract. Java no te dejará instanciarla directamente. Solo puede usarse como padre de otras clases.
        // ¿Por qué hacer esto? Porque a veces tienes una clase que conceptualmente no debe instanciarse, solo sirve como base. Marcarla abstracta le dice al programador "esta clase no se debe usar directamente, solo como punto de partida".
        // Esto es muy común en Spring Boot. Tendrás clases como BaseEntity o BaseController que se marcan abstractas aunque no tengan métodos abstractos.

        // 💡 Demostración real de la Parte 10 en ejecución (usando la clase abstracta BaseEntity):
        {
            System.out.println("=== Parte 10: Clase Abstracta sin Métodos Abstractos ===");
            // Intentar hacer: BaseEntity entity = new BaseEntity(1); // ❌ No compila, es abstracta
            
            EntidadConcreta entity = new EntidadConcreta(99);
            System.out.println("Entidad concreta heredando atributos de una clase abstracta pura:");
            System.out.println("ID heredado: " + entity.getId());
            System.out.println("Fecha creación heredada: " + entity.getFechaCreacion());
            System.out.println();
        }

        // =========================================================================
        // Parte 11 — Métodos default vs métodos en clase abstracta
        // =========================================================================
        // Una pregunta que surge naturalmente. Desde Java 8 las interfaces pueden tener métodos default con cuerpo. Entonces ¿no son lo mismo que clases abstractas?
        // No son lo mismo. Diferencias importantes:
        // Métodos default de interfaz:
        // ❌ NO pueden acceder a atributos de instancia (porque interfaces no tienen)
        // ❌ NO se pueden tener constructores
        // ✅ Útiles para agregar funcionalidad sin romper código existente
        // ✅ Una clase puede heredar default de VARIAS interfaces
        // 
        // Métodos de clase abstracta:
        // ✅ SÍ pueden acceder a atributos de la clase
        // ✅ SÍ pueden usar el constructor para inicializar
        // ✅ Útiles para definir comportamiento compartido completo
        // ❌ Una clase solo puede extender UNA clase abstracta
        // En resumen: si necesitas estado (atributos) y constructor, usa clase abstracta. Si solo necesitas comportamiento sin estado, una interfaz con default puede ser suficiente.

        // =========================================================================
        // Parte 12 — Conexión con Spring Boot
        // =========================================================================
        // Esto es importantísimo. En Spring Boot verás constantemente ambas cosas:
        // Interfaces típicas que verás:
        // JpaRepository       →  contrato para repositorios
        // UserDetailsService  →  contrato para cargar usuarios
        // HttpMessageConverter →  contrato para convertir datos
        // Las interfaces son la columna vertebral de Spring Boot. El framework te pide implementar interfaces para personalizar comportamiento.
        // 
        // Clases abstractas típicas que verás:
        // BaseEntity              →  clase abstracta con id, fechas, métodos comunes
        //                            todas las entidades extienden de ella
        // 
        // BaseController          →  clase abstracta con métodos compartidos
        //                            todos los controladores extienden de ella
        // 
        // AbstractAuthenticationProcessingFilter →  filtro de Spring Security
        //                                           extiendes para personalizar autenticación
        // Las clases abstractas se usan para evitar duplicación entre clases relacionadas.

        // =========================================================================
        // Parte 13 — Tabla de decisión final
        // =========================================================================
        // Para que te quede grabado:
        // PREGUNTA                                 ABSTRACTA    INTERFAZ
        // ─────────────────────────────────────    ─────────    ────────
        // ¿Comparten código común?                 ✅           ❌
        // ¿Comparten atributos con estado?         ✅           ❌
        // ¿Necesitas constructor?                  ✅           ❌
        // ¿Relación natural ES UN?                 ✅           ❌
        // ¿Clase debe cumplir varios contratos?    ❌           ✅
        // ¿Solo contrato puro sin implementación?  ❌           ✅
        // ¿Clases sin relación natural?            ❌           ✅
        // 
        // Si tienes muchos ✅ en la columna de abstracta → usa abstracta
        // Si tienes muchos ✅ en la columna de interfaz → usa interfaz
        // Si tienes mezcla → probablemente uses ambas combinadas (lo más común en proyectos reales)

        // =========================================================================
        // Resumen completo del Tema 5
        // =========================================================================
        // CLASE ABSTRACTA   →  híbrido entre clase normal e interfaz
        // puede tener atributos, constructor, métodos con cuerpo
        // y también métodos abstractos sin cuerpo
        // NO se puede instanciar con new
        // 
        // abstract class    →  palabras para declararla
        // abstract void m() →  método sin cuerpo, las hijas deben implementarlo
        // termina con ; sin llaves
        // 
        // Una clase abstracta sin métodos abstractos sigue siendo abstracta
        // y no se puede instanciar, sirve solo como padre
        // 
        // INTERFAZ          →  contrato puro
        // solo dice QUÉ debe hacer una clase
        // no tiene constructor ni atributos con estado
        // una clase puede implementar VARIAS
        // 
        // Cuándo usar clase abstracta:
        // ✅ Comparten código común con cuerpo
        // ✅ Comparten atributos con estado
        // ✅ Relación ES UN entre las clases
        // ✅ Necesitas constructor para la parte común
        // 
        // Cuándo usar interfaz:
        // ✅ Solo necesitas un contrato sin código compartido
        // ✅ Una clase debe cumplir varios contratos
        // ✅ Las clases no tienen relación natural entre sí
        // ✅ Quieres máxima flexibilidad en el diseño
        // 
        // En proyectos reales se combinan: interfaces para contratos generales
        // y clases abstractas para compartir código entre clases relacionadas

        System.out.println("=================================================");
        System.out.println("¡Guía del Tema 5 completada y ejecutada con éxito!");
        System.out.println("=================================================");
    }

    // =========================================================================
    // ESTRUCTURA DE SOPORTE - CLASES ANIDADAS ESTÁTICAS E INTERFACES
    // =========================================================================

    // --- Parte 3 y 4: Definición de Animal y clases hijas ---
    static abstract class Animal {
        protected String nombre;
        protected int edad;

        public Animal(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        // Método CONCRETO
        public void respirar() {
            System.out.println(nombre + " está respirando");
        }

        // Método ABSTRACTO
        public abstract void hacerSonido();
    }

    // --- Parte 5: Jerarquía abstracta intermedia ---
    static abstract class Mamifero extends Animal {
        public Mamifero(String nombre, int edad) {
            super(nombre, edad);
        }
        // No implementa hacerSonido(), pasa la responsabilidad a la clase hija final
    }

    static class Perro extends Mamifero {
        public Perro(String nombre, int edad) {
            super(nombre, edad);
        }

        @Override
        public void hacerSonido() {
            System.out.println(nombre + " dice: Guau guau");
        }
    }

    static class Gato extends Animal {
        public Gato(String nombre, int edad) {
            super(nombre, edad);
        }

        @Override
        public void hacerSonido() {
            System.out.println(nombre + " dice: Miau");
        }
    }

    // --- Parte 9: Ejemplo combinado real ---
    interface Imprimible {
        void imprimir();
    }

    interface Guardable {
        void guardar();
    }

    static abstract class Producto implements Imprimible, Guardable {
        protected String nombre;
        protected double precio;

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public void imprimir() {
            System.out.println("Producto: " + nombre + " - $" + precio);
        }

        @Override
        public void guardar() {
            System.out.println("Guardando " + nombre + " en base de datos");
        }

        // Método abstracto particular
        public abstract double calcularImpuesto();
    }

    static class ProductoElectronico extends Producto {
        public ProductoElectronico(String nombre, double precio) {
            super(nombre, precio);
        }

        @Override
        public double calcularImpuesto() {
            return precio * 0.19; // 19% de impuesto
        }
    }

    static class ProductoLibro extends Producto {
        public ProductoLibro(String nombre, double precio) {
            super(nombre, precio);
        }

        @Override
        public double calcularImpuesto() {
            return 0; // los libros no pagan impuesto
        }
    }

    // --- Parte 10: Clase abstracta sin métodos abstractos ---
    static abstract class BaseEntity {
        private int id;
        private String fechaCreacion;

        public BaseEntity(int id) {
            this.id = id;
            this.fechaCreacion = "2024-12-25";
        }

        public int getId() { return this.id; }
        public String getFechaCreacion() { return this.fechaCreacion; }
    }

    static class EntidadConcreta extends BaseEntity {
        public EntidadConcreta(int id) {
            super(id);
        }
    }
}
