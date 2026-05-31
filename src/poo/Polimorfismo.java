package poo;

public class Polimorfismo {
    public static void main(String[] args) {
        
        // =========================================================================
        // Polimorfismo, Referencia de Tipo Padre y Comportamiento Hijo
        // =========================================================================

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // Imagina que en tu sistema de animales del tema anterior necesitas hacer que cada animal haga su sonido. Sin polimorfismo lo harías así:
        // 
        // Perro firulais = new Perro("Firulais", 3);
        // Gato whiskers = new Gato("Whiskers", 2);
        // Vaca lola = new Vaca("Lola", 5);
        // 
        // firulais.hacerSonido();
        // whiskers.hacerSonido();
        // lola.hacerSonido();
        // Funciona. Pero ahora imagina que tienes 50 animales. Tendrías 50 variables y 50 llamadas. Y peor, cada vez que quieras agregar un animal nuevo tendrías que modificar el código del main.
        // Otro problema. Imagina que escribes un método que reciba un animal y lo haga sonar:
        // 
        // public void hacerSonarPerro(Perro perro) {
        //     perro.hacerSonido();
        // }
        // 
        // public void hacerSonarGato(Gato gato) {
        //     gato.hacerSonido();
        // }
        // 
        // public void hacerSonarVaca(Vaca vaca) {
        //     vaca.hacerSonido();
        // }
        // Un método por cada tipo. Es absurdo. ¿No podríamos tener un solo método que reciba cualquier animal?
        // La respuesta es sí. Eso es el polimorfismo.

        // =========================================================================
        // Parte 2 — Qué es el polimorfismo
        // =========================================================================
        // La palabra viene del griego: poli (muchas) + morfo (formas). Significa "muchas formas".
        // En programación significa que un mismo nombre puede comportarse de muchas formas diferentes según el contexto. Aplicado a Java: una misma referencia puede apuntar a diferentes tipos de objetos, y al llamar un método, Java elige automáticamente la versión correcta según el objeto real.
        // Una analogía clarísima: piensa en la palabra "tocar". Es la misma palabra, pero hace cosas completamente diferentes según el contexto:
        // Tocar una guitarra    →  pulsar cuerdas
        // Tocar una flauta      →  soplar y tapar agujeros
        // Tocar un piano        →  presionar teclas
        // Tocar un tambor       →  golpear con palillos
        // La misma palabra, comportamientos diferentes. El contexto decide. Eso es polimorfismo.
        // En Java sería:
        // animal.hacerSonido()
        // La misma llamada, pero si animal es un Perro ladra, si es un Gato maúlla, si es una Vaca muge. Java elige automáticamente la implementación correcta sin que tú tengas que decírselo.

        // =========================================================================
        // Parte 3 — Referencia de tipo padre apuntando a un objeto hijo
        // =========================================================================
        // Aquí está el corazón del polimorfismo. Vamos paso a paso.
        // En Java hay dos cosas separadas:
        // La referencia      →  el tipo de la VARIABLE
        // El objeto real     →  el tipo de lo que está en MEMORIA
        // Hasta ahora siempre los hacíamos coincidir:
        // 
        // Perro firulais = new Perro("Firulais", 3);
        // // referencia tipo Perro    objeto real tipo Perro
        // 
        // Pero Java permite algo mucho más interesante: la referencia puede ser del tipo padre, mientras el objeto real es del tipo hijo:
        // 
        // Animal firulais = new Perro("Firulais", 3);
        // // referencia tipo Animal    objeto real tipo Perro
        // 
        // Lee esto con cuidado. La variable firulais está declarada como Animal. Pero en memoria, el objeto que apunta es un Perro real. Esto es válido porque Perro ES UN Animal (recuerda la relación de herencia).
        // Visualmente en memoria:
        // Variable firulais         →    Objeto real en memoria
        // tipo: Animal                   ┌─────────────────┐
        // apunta a ↓                     │ es un Perro     │
        //                                │ nombre = Firulais│
        //                                │ edad = 3        │
        //                                │ hacerSonido() = "Guau guau"
        //                                └─────────────────┘
        // La etiqueta de la variable dice "Animal", pero adentro vive un Perro.

        // =========================================================================
        // Parte 4 — Lo asombroso: Java sabe qué método llamar
        // =========================================================================
        // Ahora viene la parte mágica del polimorfismo. Cuando llamas un método sobre esa referencia, Java mira el objeto real, no la etiqueta de la variable:
        // 
        // Animal firulais = new Perro("Firulais", 3);
        // firulais.hacerSonido(); // ¿Qué imprime?
        // ¿Qué crees que imprime? Aunque la variable es de tipo Animal, Java mira el objeto real (que es un Perro) y ejecuta la versión de Perro:
        // Firulais dice: Guau guau
        // Esa es la idea fundamental del polimorfismo:
        // La etiqueta dice "Animal"    →  pero el comportamiento es de Perro
        // Variable de tipo padre       →  método ejecutado del tipo hijo
        // A esto se le llama "despacho dinámico de métodos" o "late binding". Java decide en tiempo de ejecución cuál método llamar, mirando el objeto real.

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: Despacho Dinámico de Métodos ===");
            Animal miPerro = new Perro("Firulais", 3);
            System.out.print("Llamando a miPerro.hacerSonido() -> ");
            miPerro.hacerSonido(); // Ejecuta el de Perro aunque la variable sea Animal
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — El poder real del polimorfismo
        // =========================================================================
        // Ahora viene donde todo cobra sentido. Como las variables de tipo padre pueden contener objetos hijos, puedes hacer cosas como esta:
        // 
        // public class Main {
        //     public static void main(String[] args) {
        //         // Un array de Animal puede contener cualquier tipo de animal
        //         Animal[] animales = {
        //                 new Perro("Firulais", 3),
        //                 new Gato("Whiskers", 2),
        //                 new Vaca("Lola", 5),
        //                 new Perro("Toby", 4),
        //                 new Gato("Mishu", 1)
        //         };
        // 
        //         // Recorrer e invocar el mismo método para todos
        //         for (Animal animal : animales) {
        //             animal.hacerSonido();
        //         }
        //     }
        // }
        // 
        // Salida:
        // Firulais dice: Guau guau
        // Whiskers dice: Miau
        // Lola dice: Muuu
        // Toby dice: Guau guau
        // Mishu dice: Miau
        // Mira la elegancia. Un solo bucle, un solo método, pero cada animal hace su propio sonido. Java elige automáticamente la implementación correcta para cada objeto.
        // Si mañana agregas una clase nueva Caballo que extienda Animal, este bucle funciona sin modificarse. Solo agregas el caballo al array y listo. El código que recorre y ejecuta hacerSonido() no se toca.
        // Eso se llama código extensible. Es uno de los principios más importantes de la programación profesional: poder agregar comportamiento nuevo sin modificar código existente.

        // 💡 Demostración real de la Parte 5 en ejecución:
        {
            System.out.println("=== Parte 5: Demostración de Array Polimórfico ===");
            Animal[] animales = {
                    new Perro("Firulais", 3),
                    new Gato("Whiskers", 2),
                    new Vaca("Lola", 5),
                    new Perro("Toby", 4),
                    new Gato("Mishu", 1)
            };

            for (Animal animal : animales) {
                animal.hacerSonido();
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 6 — Métodos que reciben tipo padre como parámetro
        // =========================================================================
        // Esto es lo que más vas a usar en Spring Boot. Métodos que aceptan tipo padre y aceptan cualquier hijo:
        // 
        // public class Veterinario {
        //     // Este método acepta CUALQUIER tipo de Animal
        //     public void atender(Animal animal) {
        //         System.out.println("Atendiendo a " + animal);
        //         animal.hacerSonido();
        //         animal.respirar();
        //     }
        // }
        // 
        // Y se puede usar con cualquier hijo:
        // Veterinario vet = new Veterinario();
        // Perro firulais = new Perro("Firulais", 3);
        // Gato whiskers = new Gato("Whiskers", 2);
        // Vaca lola = new Vaca("Lola", 5);
        // 
        // vet.atender(firulais);  // funciona, Perro ES UN Animal
        // vet.atender(whiskers);  // funciona, Gato ES UN Animal
        // vet.atender(lola);      // funciona, Vaca ES UN Animal
        // El método atender no sabe qué tipo de animal le están pasando. Y no le importa. Solo sabe que es un Animal y que tiene hacerSonido() y respirar(). El polimorfismo se encarga del resto.

        // 💡 Demostración real de la Parte 6 en ejecución:
        {
            System.out.println("=== Parte 6: Parámetros de Tipo Padre (Veterinario) ===");
            Veterinario vet = new Veterinario();

            Perro firulais = new Perro("Firulais", 3);
            Gato whiskers = new Gato("Whiskers", 2);
            Vaca lola = new Vaca("Lola", 5);

            vet.atender(firulais);
            System.out.println("---");
            vet.atender(whiskers);
            System.out.println("---");
            vet.atender(lola);
            System.out.println();
        }

        // =========================================================================
        // Parte 7 — Polimorfismo con interfaces (la versión más usada)
        // =========================================================================
        // Recuerda que en el tema 4 vimos que las interfaces también permiten polimorfismo. Funciona exactamente igual:
        // 
        // public interface Imprimible {
        //     void imprimir();
        // }
        // 
        // public class Producto implements Imprimible {
        //     @Override
        //     public void imprimir() {
        //         System.out.println("Imprimiendo producto");
        //     }
        // }
        // 
        // public class Usuario implements Imprimible {
        //     @Override
        //     public void imprimir() {
        //         System.out.println("Imprimiendo usuario");
        //     }
        // }
        // 
        // Y puedes usar referencias de tipo interfaz que contengan objetos de cualquier clase implementadora:
        // Imprimible item1 = new Producto();
        // Imprimible item2 = new Usuario();
        // 
        // item1.imprimir(); // Imprimiendo producto
        // item2.imprimir(); // Imprimiendo usuario
        // Lo mismo que con herencia, pero con interfaces. Esto es lo que hace tan flexible a Spring Boot.

        // 💡 Demostración real de la Parte 7 en ejecución:
        {
            System.out.println("=== Parte 7: Polimorfismo mediante Interfaces ===");
            Imprimible item1 = new Producto();
            Imprimible item2 = new Usuario();

            item1.imprimir(); // Imprimiendo producto
            item2.imprimir(); // Imprimiendo usuario
            System.out.println();
        }

        // =========================================================================
        // Parte 8 — Qué métodos puedes llamar con una referencia padre
        // =========================================================================
        // Aquí hay un detalle importante que confunde a muchos. Si tienes una referencia de tipo padre, solo puedes llamar a los métodos que están definidos en el padre, aunque el objeto real sea del hijo:
        // 
        // public class Animal {
        //     public void respirar() { /* ... */ }
        //     public void hacerSonido() { /* ... */ }
        // }
        // 
        // public class Perro extends Animal {
        //     @Override
        //     public void hacerSonido() {
        //         System.out.println("Guau");
        //     }
        //     // Método propio del Perro
        //     public void mover_cola() {
        //         System.out.println("Moviendo la cola");
        //     }
        // }
        // 
        // Mira lo que pasa:
        // Animal animal = new Perro("Firulais", 3);
        // animal.respirar();      // ✅ funciona, está en Animal
        // animal.hacerSonido();   // ✅ funciona, está en Animal (ejecuta versión del Perro)
        // animal.mover_cola();    // ❌ ERROR - mover_cola no está en Animal
        // ¿Por qué la última línea da error? Aunque el objeto real es un Perro, la variable está declarada como Animal. Java solo te deja llamar métodos que están en el tipo de la variable. Como mover_cola() no existe en Animal, no se puede llamar a través de una referencia Animal.
        // Regla a memorizar:
        // La variable decide QUÉ MÉTODOS puedes llamar
        // El objeto real decide CUÁL IMPLEMENTACIÓN se ejecuta
        // Visualízalo así. La referencia es una lupa que solo deja ver ciertos métodos. Si la lupa es de tipo Animal, solo ves los métodos de Animal. Aunque adentro haya un Perro con métodos propios, esos quedan ocultos detrás de la lupa.

        // =========================================================================
        // Parte 9 — Casting, ver al hijo real
        // =========================================================================
        // Si quieres acceder a los métodos propios del hijo a través de una referencia padre, debes hacer un cast. Es decirle a Java "trátalo como hijo":
        // 
        // Animal animal = new Perro("Firulais", 3);
        // // Cast hacia abajo (downcasting)
        // Perro perro = (Perro) animal;
        // perro.mover_cola();  // ✅ funciona, ahora la referencia es Perro
        // 
        // Línea por línea:
        // Perro perro = (Perro) animal;
        // (Perro) → el cast, indica el tipo al que quieres convertir.
        // animal → la referencia padre que tiene un Perro adentro.
        // perro → la nueva variable, ahora del tipo correcto.
        // 
        // Peligro importante: si haces cast a un tipo equivocado, Java lanza una excepción ClassCastException en runtime:
        // Animal animal = new Perro("Firulais", 3);
        // Gato gato = (Gato) animal;  // ❌ ClassCastException - es Perro, no Gato
        // 
        // Para evitarlo siempre verifica el tipo con instanceof antes de hacer el cast:
        // if (animal instanceof Perro) {
        //     Perro perro = (Perro) animal;
        //     perro.mover_cola();
        // }
        // instanceof se lee como "es una instancia de". Si la condición es verdadera, sabes que el cast es seguro.
        // En Java 16+ hay una forma más limpia llamada pattern matching para instanceof (que verás en la Fase 4):
        // if (animal instanceof Perro perro) {
        //     perro.mover_cola();  // perro ya está casteado y disponible
        // }
        // Esto es lo que aprenderás cuando llegues a Java 21 moderno. Por ahora quédate con la forma clásica para que entiendas la mecánica.

        // 💡 Demostración real de las Partes 8 y 9 en ejecución (Lupa, Casting y control de excepciones):
        {
            System.out.println("=== Partes 8 y 9: Lupa de Tipos, Casting e instanceof ===");
            Animal animal = new Perro("Firulais", 3);
            
            animal.respirar();    // ✅ Funciona (Método del Padre)
            animal.hacerSonido(); // ✅ Funciona (Método del Padre, ejecuta versión de Perro)
            // animal.mover_cola(); // ❌ ERROR — El compilador no lo ve porque la variable es de tipo Animal!
            
            // Casting seguro usando instanceof:
            if (animal instanceof Perro) {
                System.out.println("Casting seguro detectado con instanceof:");
                Perro perroReal = (Perro) animal;
                perroReal.mover_cola(); // ✅ Funciona perfectamente
            }

            // Qué pasa si intentamos forzar un casteo incorrecto de Perro a Gato:
            try {
                System.out.println("Intentando castear de forma forzada a Gato...");
                Gato gatoInvalido = (Gato) animal; // Provocará ClassCastException
            } catch (ClassCastException e) {
                System.out.println("❌ Excepción capturada exitosamente: " + e.getMessage() + " (No puedes convertir un Perro a Gato)");
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 10 — Polimorfismo en accion: ejemplo completo
        // =========================================================================
        // Te dejo un ejemplo completo donde el polimorfismo brilla:
        // Interfaz MetodoPago:
        // 
        // public interface MetodoPago {
        //     void procesarPago(double monto);
        // }
        // 
        // Tres implementaciones diferentes:
        // public class PagoConTarjeta implements MetodoPago {
        //     @Override
        //     public void procesarPago(double monto) {
        //         System.out.println("Procesando $" + monto + " con tarjeta de crédito");
        //     }
        // }
        // 
        // public class PagoConTransferencia implements MetodoPago {
        //     @Override
        //     public void procesarPago(double monto) {
        //         System.out.println("Procesando $" + monto + " por transferencia bancaria");
        //     }
        // }
        // 
        // public class PagoConPaypal implements MetodoPago {
        //     @Override
        //     public void procesarPago(double monto) {
        //         System.out.println("Procesando $" + monto + " vía PayPal");
        //     }
        // }
        // 
        // Un servicio que usa polimorfismo:
        // public class ServicioCompras {
        //     // Este método acepta CUALQUIER MetodoPago
        //     public void comprar(double monto, MetodoPago metodoPago) {
        //         System.out.println("Iniciando compra de $" + monto);
        //         metodoPago.procesarPago(monto);
        //         System.out.println("Compra finalizada");
        //     }
        // }
        // 
        // Main que muestra la flexibilidad:
        // public class Main {
        //     public static void main(String[] args) {
        //         ServicioCompras servicio = new ServicioCompras();
        // 
        //         servicio.comprar(100.0, new PagoConTarjeta());
        //         System.out.println("---");
        // 
        //         servicio.comprar(50.0, new PagoConTransferencia());
        //         System.out.println("---");
        // 
        //         servicio.comprar(200.0, new PagoConPaypal());
        //     }
        // }
        // 
        // Salida:
        // Iniciando compra de $100.0
        // Procesando $100.0 con tarjeta de crédito
        // Compra finalizada
        // ---
        // Iniciando compra de $50.0
        // Procesando $50.0 por transferencia bancaria
        // Compra finalizada
        // ---
        // Iniciando compra de $200.0
        // Procesando $200.0 vía PayPal
        // Compra finalizada
        // Lo importante: el ServicioCompras no sabe ni le importa qué tipo de pago le pasas. Mañana puedes agregar PagoConCriptomonedas y el servicio funciona sin modificarse.
        // Esto es exactamente cómo funciona Spring Boot internamente.

        // 💡 Demostración real de la Parte 10 en ejecución:
        {
            System.out.println("=== Parte 10: Ejemplo Completo de Compra con Polimorfismo ===");
            ServicioCompras servicio = new ServicioCompras();

            servicio.comprar(100.0, new PagoConTarjeta());
            System.out.println("---");

            servicio.comprar(50.0, new PagoConTransferencia());
            System.out.println("---");

            servicio.comprar(200.0, new PagoConPaypal());
            System.out.println();
        }

        // =========================================================================
        // Parte 11 — Por qué importa tanto el polimorfismo
        // =========================================================================
        // Te quiero hacer ver el verdadero valor de esto. Sin polimorfismo, este código de ServicioCompras se vería horrible:
        // 
        // public class ServicioCompras {
        //     public void comprar(double monto, String tipoPago) {
        //         if (tipoPago.equals("tarjeta")) {
        //             System.out.println("Procesando con tarjeta");
        //         } else if (tipoPago.equals("transferencia")) {
        //             System.out.println("Procesando con transferencia");
        //         } else if (tipoPago.equals("paypal")) {
        //             System.out.println("Procesando con paypal");
        //         }
        //         // y así para cada nuevo tipo...
        //     }
        // }
        // Problemas graves:
        // - Cada vez que agregues un método de pago modificas esta clase.
        // - Si te olvidas de un caso, falla silenciosamente.
        // - El código crece y se vuelve imposible de mantener.
        // 
        // Con polimorfismo:
        // - Agregar un nuevo método de pago es crear una clase nueva.
        // - El servicio no se modifica.
        // - Java se encarga de elegir la implementación correcta.
        // 
        // Esto se conoce como el "Principio Abierto/Cerrado" de la programación profesional: el código debe estar abierto a extensión pero cerrado a modificación. El polimorfismo es la herramienta principal para lograrlo.

        // =========================================================================
        // Parte 12 — Tipos de polimorfismo en Java
        // =========================================================================
        // Para tu cultura general. Hay dos tipos principales:
        // 1. Polimorfismo por herencia (lo que hemos visto):
        //    Animal a = new Perro();
        //    a.hacerSonido();  // ejecuta versión del Perro
        //    Una referencia de tipo padre apuntando a objeto hijo, y el método correcto se elige automáticamente.
        // 2. Sobrecarga de métodos (overloading):
        //    public int sumar(int a, int b) { return a + b; }
        //    public double sumar(double a, double b) { return a + b; }
        //    public int sumar(int a, int b, int c) { return a + b + c; }
        //    El mismo nombre de método con diferentes parámetros. Java elige el correcto según los argumentos. Esto lo viste en el tema de métodos de la Fase 1.
        // Cuando se habla de "polimorfismo" en Java casi siempre se refiere al primero (el de herencia y referencias). El de sobrecarga existe pero se considera una forma más débil.

        // 💡 Demostración real de la Parte 12 (Sobrecarga de métodos / Overloading):
        {
            System.out.println("=== Parte 12: Demostración de Sobrecarga (Overloading) ===");
            System.out.println("Sumando dos enteros (sumar(5, 10)) -> " + sumar(5, 10));
            System.out.println("Sumando dos doubles (sumar(5.5, 4.5)) -> " + sumar(5.5, 4.5));
            System.out.println("Sumando tres enteros (sumar(1, 2, 3)) -> " + sumar(1, 2, 3));
            System.out.println();
        }

        // =========================================================================
        // Parte 13 — Conexión con Spring Boot
        // =========================================================================
        // El polimorfismo es la base de Spring Boot. Cuando llegues al framework verás cosas así:
        // 
        // // Una interfaz que define un contrato
        // public interface NotificacionService {
        //     void enviar(String mensaje);
        // }
        // 
        // // Múltiples implementaciones
        // public class EmailNotificacionService implements NotificacionService {
        //     public void enviar(String mensaje) { /* enviar email */ }
        // }
        // 
        // public class SMSNotificacionService implements NotificacionService {
        //     public void enviar(String mensaje) { /* enviar SMS */ }
        // }
        // 
        // public class PushNotificacionService implements NotificacionService {
        //     public void enviar(String mensaje) { /* enviar push */ }
        // }
        // 
        // // Y un controlador que usa polimorfismo
        // public class UsuarioController {
        //     private NotificacionService servicio;  // referencia de tipo interfaz
        // 
        //     public void registrarse() {
        //         servicio.enviar("Bienvenido");  // ejecuta la implementación elegida
        //     }
        // }
        // Spring Boot decide en tiempo de ejecución cuál implementación usar (Email, SMS o Push) y la inyecta automáticamente. El controlador no sabe ni le importa cuál es. Eso es polimorfismo puro aplicado a la arquitectura del sistema.
        // Esto es lo que hace que Spring Boot sea tan flexible. Sin polimorfismo no existiría.

        // 💡 Demostración real de la Parte 13 en ejecución (Simulando Inyección por Interfaces de Spring Boot):
        {
            System.out.println("=== Parte 13: Simulación de Polimorfismo en Spring Boot ===");
            
            // Registramos enviando SMS
            System.out.println("--- Escenario 1: Spring Boot inyecta SMS Service ---");
            UsuarioController controllerSMS = new UsuarioController(new SMSNotificacionService());
            controllerSMS.registrarse();
            
            // Intercambiamos dinámicamente sin cambiar el código de UsuarioController a Email
            System.out.println("--- Escenario 2: Spring Boot inyecta Email Service ---");
            UsuarioController controllerEmail = new UsuarioController(new EmailNotificacionService());
            controllerEmail.registrarse();
            System.out.println();
        }

        // =========================================================================
        // Resumen completo del Tema 6
        // =========================================================================
        // POLIMORFISMO          →  un mismo nombre puede comportarse de muchas formas
        //                          según el objeto real que esté detrás
        // 
        // Referencia de tipo padre + objeto hijo:
        // Animal a = new Perro()
        // ─────────   ──────────
        // etiqueta    objeto real
        // 
        // Regla fundamental:
        // La variable decide QUÉ MÉTODOS puedes llamar
        // El objeto real decide CUÁL IMPLEMENTACIÓN se ejecuta
        // 
        // Polimorfismo funciona con:
        // ✅ Herencia (extends)
        // ✅ Interfaces (implements)
        // ✅ Clases abstractas
        // 
        // Beneficios del polimorfismo:
        // ✅ Un solo método para múltiples tipos
        // ✅ Código extensible sin modificar lo existente
        // ✅ Arrays y colecciones de tipos mixtos
        // ✅ Principio Abierto/Cerrado
        // 
        // Casting:
        // (Perro) animal   →  convertir referencia padre a hijo
        // instanceof       →  verificar el tipo antes del cast
        // ClassCastException →  error si haces cast incorrecto
        // 
        // Conexión con Spring Boot:
        // El framework usa polimorfismo masivamente
        // Inyección de dependencias funciona por interfaces
        // Permite intercambiar implementaciones sin tocar código
        // 
        // Y con esto cierras el corazón de la POO en Java. Has completado los temas más importantes:
        // ✅ Tema 1 → Clases, objetos, constructores y atributos
        // ✅ Tema 2 → Encapsulamiento, getters y setters
        // ✅ Tema 3 → Herencia, extends, super y sobreescritura
        // ✅ Tema 4 → Interfaces e implementación múltiple
        // ✅ Tema 5 → Clases abstractas vs interfaces
        // ✅ Tema 6 → Polimorfismo, referencia padre y comportamiento hijo

        System.out.println("=================================================");
        System.out.println("¡Guía del Tema 6 completada y ejecutada con éxito!");
        System.out.println("=================================================");
    }

    // =========================================================================
    // ESTRUCTURA DE SOPORTE - CLASES ANIDADAS ESTÁTICAS E INTERFACES
    // =========================================================================

    // Sobrecarga de métodos para la Parte 12:
    public static int sumar(int a, int b) { return a + b; }
    public static double sumar(double a, double b) { return a + b; }
    public static int sumar(int a, int b, int c) { return a + b + c; }

    // --- Partes 1 a 6, 8 y 9: Animales ---
    static abstract class Animal {
        protected String nombre;
        protected int edad;

        public Animal(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        public void respirar() {
            System.out.println(nombre + " está respirando");
        }

        public abstract void hacerSonido();

        @Override
        public String toString() {
            return nombre + " (tipo: " + getClass().getSimpleName() + ", edad: " + edad + ")";
        }
    }

    static class Perro extends Animal {
        public Perro(String nombre, int edad) {
            super(nombre, edad);
        }

        @Override
        public void hacerSonido() {
            System.out.println(nombre + " dice: Guau guau");
        }

        // Método propio
        public void mover_cola() {
            System.out.println(nombre + " está moviendo la cola felizmente");
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

    static class Vaca extends Animal {
        public Vaca(String nombre, int edad) {
            super(nombre, edad);
        }

        @Override
        public void hacerSonido() {
            System.out.println(nombre + " dice: Muuu");
        }
    }

    static class Veterinario {
        public void atender(Animal animal) {
            System.out.println("Atendiendo a: " + animal);
            animal.hacerSonido();
            animal.respirar();
        }
    }

    // --- Parte 7: Interfaces ---
    interface Imprimible {
        void imprimir();
    }

    static class Producto implements Imprimible {
        @Override
        public void imprimir() {
            System.out.println("Imprimiendo producto");
        }
    }

    static class Usuario implements Imprimible {
        @Override
        public void imprimir() {
            System.out.println("Imprimiendo usuario");
        }
    }

    // --- Parte 10: Procesamiento de Pagos ---
    interface MetodoPago {
        void procesarPago(double monto);
    }

    static class PagoConTarjeta implements MetodoPago {
        @Override
        public void procesarPago(double monto) {
            System.out.println("Procesando $" + monto + " con tarjeta de crédito");
        }
    }

    static class PagoConTransferencia implements MetodoPago {
        @Override
        public void procesarPago(double monto) {
            System.out.println("Procesando $" + monto + " por transferencia bancaria");
        }
    }

    static class PagoConPaypal implements MetodoPago {
        @Override
        public void procesarPago(double monto) {
            System.out.println("Procesando $" + monto + " vía PayPal");
        }
    }

    static class ServicioCompras {
        public void comprar(double monto, MetodoPago metodoPago) {
            System.out.println("Iniciando compra de $" + monto);
            metodoPago.procesarPago(monto);
            System.out.println("Compra finalizada");
        }
    }

    // --- Parte 13: Inyección en Spring Boot ---
    interface NotificacionService {
        void enviar(String mensaje);
    }

    static class EmailNotificacionService implements NotificacionService {
        @Override
        public void enviar(String mensaje) {
            System.out.println("Enviando Email con el mensaje: \"" + mensaje + "\"");
        }
    }

    static class SMSNotificacionService implements NotificacionService {
        @Override
        public void enviar(String mensaje) {
            System.out.println("Enviando SMS con el mensaje: \"" + mensaje + "\"");
        }
    }

    static class PushNotificacionService implements NotificacionService {
        @Override
        public void enviar(String mensaje) {
            System.out.println("Enviando notificación Push: \"" + mensaje + "\"");
        }
    }

    static class UsuarioController {
        private NotificacionService servicio;

        public UsuarioController(NotificacionService servicio) {
            this.servicio = servicio;
        }

        public void registrarse() {
            servicio.enviar("Bienvenido");
        }
    }
}
