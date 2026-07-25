package java_avanzado;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class Enums {
    public static void main(String[] args) {

        // =========================================================================
        // Enums — Constantes con superpoderes (los usarás en CADA API REST)
        // =========================================================================

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // Imagina que en tu API de tienda necesitas guardar el estado de un pedido.
        // Un pedido puede estar: pendiente, pagado, enviado o entregado.
        // Con lo que sabes hasta ahora lo harías con Strings:
        //
        // String estado = "PENDIENTE";
        //
        // if (estado.equals("PAGADO")) { ... }
        //
        // Funciona... hasta que deja de funcionar. Mira todos los problemas:
        //
        // String estado1 = "PENDIENTE";
        // String estado2 = "pendiente";   // ❌ ¿es lo mismo? Java dice que no
        // String estado3 = "PENDENTE";    // ❌ typo, Java no se queja, tu API explota
        // String estado4 = "CANCELADO";   // ❌ ¿existe ese estado? nadie lo sabe
        // String estado5 = "banana";      // ❌ Java acepta cualquier String
        //
        // El compilador no te protege. Cualquier String es válido aunque no tenga
        // sentido. Los errores aparecen en runtime, en producción, a las 3 AM.
        //
        // Lo mismo pasa si usas números:
        // int estado = 3; // ¿3 es "enviado" o "entregado"? tienes que ir a buscar
        //                 // una tabla en algún lado. Y nada impide poner estado = 99.
        //
        // Lo que necesitas es decirle a Java: "esta variable SOLO puede tener uno de
        // estos 4 valores, ni uno más". Para eso existen los enums.

        // =========================================================================
        // Parte 2 — Qué es un enum
        // =========================================================================
        // Un enum (enumeración) es un tipo especial que define un conjunto CERRADO
        // de valores posibles. La sintaxis básica:
        //
        // public enum EstadoPedido {
        //     PENDIENTE,
        //     PAGADO,
        //     ENVIADO,
        //     ENTREGADO
        // }
        //
        // Una analogía clara: piensa en los días de la semana. No existen "días
        // inventados". Son exactamente 7, ni uno más, ni uno menos. Un enum es eso:
        // una lista oficial y cerrada de valores válidos.
        //
        // Convenciones:
        // - El nombre del enum va en PascalCase: EstadoPedido, RolUsuario
        // - Los valores van en MAYÚSCULAS con guión bajo: PENDIENTE, EN_CAMINO
        //   (porque conceptualmente son constantes, como las static final)

        // 💡 Demostración real de la Parte 2 en ejecución:
        {
            System.out.println("=== Parte 2: Crear y usar un enum ===");

            // Se usa el nombre del enum + punto + el valor. SIN new, los enums no se instancian.
            EstadoPedido estado = EstadoPedido.PENDIENTE;
            System.out.println("Estado del pedido: " + estado); // PENDIENTE

            // EstadoPedido estado2 = "PENDIENTE";   // ❌ ERROR de compilación - no es String
            // EstadoPedido estado3 = EstadoPedido.BANANA; // ❌ ERROR - BANANA no existe
            // Imposible tener un estado inválido. El compilador te protege.
            System.out.println();
        }

        // =========================================================================
        // Parte 3 — Comparar enums: aquí SÍ se usa == (al revés que los Strings)
        // =========================================================================
        // Con Strings te grabaste la regla "nunca compares con ==, usa equals".
        // Con enums es al revés: == es la forma correcta y segura.
        // ¿Por qué? Porque cada valor del enum existe UNA SOLA VEZ en memoria.
        // EstadoPedido.PAGADO siempre es exactamente el mismo objeto. No hay copias.
        //
        // Ventaja extra de == sobre equals: si comparas con null no explota.
        // estado.equals(otro)  → 💥 NullPointerException si estado es null
        // estado == otro       → false si alguno es null, sin explotar

        // 💡 Demostración real de la Parte 3 en ejecución:
        {
            System.out.println("=== Parte 3: Comparar enums con == ===");
            EstadoPedido estado = EstadoPedido.PAGADO;

            if (estado == EstadoPedido.PAGADO) { // ✅ correcto y seguro en enums
                System.out.println("El pedido ya está pagado, se puede enviar");
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 4 — Métodos que todo enum trae gratis
        // =========================================================================
        // Todos los enums heredan métodos útiles automáticamente:
        //
        // values()    → array con TODOS los valores del enum (para recorrerlos)
        // valueOf(s)  → convierte un String al valor del enum (clave en APIs REST)
        // name()      → el nombre del valor como String ("PENDIENTE")
        // ordinal()   → la posición del valor (0, 1, 2...) ⚠️ NO dependas de esto

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: values(), valueOf(), name(), ordinal() ===");

            // values() — recorrer todos los valores posibles
            System.out.println("Estados posibles de un pedido:");
            for (EstadoPedido e : EstadoPedido.values()) {
                System.out.println("  - " + e.name() + " (posición " + e.ordinal() + ")");
            }

            // valueOf() — de String a enum. ESTO pasa en cada request de tu API:
            // el cliente manda JSON con "PAGADO" (texto) y tú lo conviertes al enum.
            EstadoPedido desdeTexto = EstadoPedido.valueOf("PAGADO");
            System.out.println("Convertido desde String: " + desdeTexto);

            // ⚠️ valueOf lanza IllegalArgumentException si el texto no coincide EXACTO:
            try {
                EstadoPedido.valueOf("pagado"); // minúsculas → no existe
            } catch (IllegalArgumentException e) {
                System.out.println("Excepción capturada: \"pagado\" no es un valor válido (es case-sensitive)");
            }

            // ⚠️ Sobre ordinal(): NUNCA guardes el número en la base de datos ni
            // hagas lógica con él. Si mañana agregas un valor en medio del enum,
            // todos los números se corren y tus datos quedan corruptos.
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — Enums con atributos y métodos (el verdadero poder)
        // =========================================================================
        // Aquí los enums dejan de ser "listas de constantes" y se vuelven clases
        // completas. Un enum puede tener atributos, constructor y métodos:
        //
        // public enum EstadoPedido {
        //     PENDIENTE("Esperando pago", false),
        //     PAGADO("Pago confirmado", false),
        //     ENVIADO("En camino al cliente", false),
        //     ENTREGADO("Recibido por el cliente", true);
        //
        //     private final String descripcion;
        //     private final boolean esFinal;
        //
        //     // El constructor de un enum SIEMPRE es privado (no se pone public).
        //     // Solo Java lo llama, una vez por cada valor, al cargar el enum.
        //     EstadoPedido(String descripcion, boolean esFinal) {
        //         this.descripcion = descripcion;
        //         this.esFinal = esFinal;
        //     }
        //
        //     public String getDescripcion() { return descripcion; }
        //     public boolean esFinal() { return esFinal; }
        // }
        //
        // Mira la sintaxis: cada valor lleva paréntesis con los datos de su
        // constructor: PENDIENTE("Esperando pago", false). Es como si cada valor
        // fuera un mini-objeto pre-construido.

        // 💡 Demostración real de la Parte 5 en ejecución:
        {
            System.out.println("=== Parte 5: Enum con atributos y métodos ===");
            for (EstadoPedido e : EstadoPedido.values()) {
                System.out.println(e + " → " + e.getDescripcion() + (e.esFinal() ? " (estado final)" : ""));
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 6 — Enums con switch moderno: la pareja perfecta
        // =========================================================================
        // Los enums y el switch moderno (Java 14+) se llevan increíble. Como Java
        // conoce TODOS los valores posibles del enum, el switch puede ser exhaustivo:
        // si cubres todos los casos, NO necesitas default. Y si mañana agregas un
        // valor nuevo al enum, el compilador te OBLIGA a manejarlo. Imposible olvidarlo.

        // 💡 Demostración real de la Parte 6 en ejecución:
        {
            System.out.println("=== Parte 6: Enum + switch moderno ===");
            EstadoPedido estado = EstadoPedido.ENVIADO;

            // Sin default: el compilador verifica que cubriste todos los casos
            String mensajeCliente = switch (estado) {
                case PENDIENTE -> "Tu pedido está esperando el pago";
                case PAGADO -> "¡Pago recibido! Preparando tu pedido";
                case ENVIADO -> "Tu pedido va en camino 🚚";
                case ENTREGADO -> "Pedido entregado, ¡gracias por tu compra!";
            };
            // Nota: dentro del switch escribes PENDIENTE, no EstadoPedido.PENDIENTE.
            // Java ya sabe de qué enum hablas.

            System.out.println(mensajeCliente);
            System.out.println();
        }

        // =========================================================================
        // Parte 7 — EnumSet y EnumMap (mención rápida)
        // =========================================================================
        // Java tiene versiones optimizadas de Set y Map específicas para enums.
        // Son más rápidas y gastan menos memoria que HashSet/HashMap. Solo debes
        // saber que existen, las usarás poco al principio:

        // 💡 Demostración real de la Parte 7 en ejecución:
        {
            System.out.println("=== Parte 7: EnumSet y EnumMap ===");

            // EnumSet — conjunto de valores del enum
            Set<EstadoPedido> estadosActivos = EnumSet.of(EstadoPedido.PENDIENTE, EstadoPedido.PAGADO, EstadoPedido.ENVIADO);
            System.out.println("Estados que requieren seguimiento: " + estadosActivos);

            // EnumMap — mapa con claves de enum
            Map<EstadoPedido, Integer> conteoPorEstado = new EnumMap<>(EstadoPedido.class);
            conteoPorEstado.put(EstadoPedido.PENDIENTE, 12);
            conteoPorEstado.put(EstadoPedido.ENVIADO, 5);
            System.out.println("Pedidos por estado: " + conteoPorEstado);
            System.out.println();
        }

        // =========================================================================
        // Parte 8 — Ejemplo realista: roles de usuario en una API
        // =========================================================================
        // El otro caso omnipresente además de los estados: los roles. Toda API REST
        // con login tiene roles (ADMIN, USER, etc.) y son SIEMPRE enums.

        // 💡 Demostración real de la Parte 8 en ejecución:
        {
            System.out.println("=== Parte 8: Roles de usuario con enum ===");
            Usuario admin = new Usuario("mati@ejemplo.com", RolUsuario.ADMIN);
            Usuario cliente = new Usuario("carlos@ejemplo.com", RolUsuario.CLIENTE);

            System.out.println(admin.email() + " ¿puede borrar productos? " + admin.rol().puedeBorrarProductos());
            System.out.println(cliente.email() + " ¿puede borrar productos? " + cliente.rol().puedeBorrarProductos());
            System.out.println();
        }

        // =========================================================================
        // Parte 9 — Conexión con Spring Boot
        // =========================================================================
        // Los enums aparecen en TODA API REST que hagas:
        //
        // 1. En DTOs (Records): el JSON llega con texto y Jackson lo convierte
        //    automáticamente al enum (y rechaza valores inválidos con error 400):
        //
        //    public record CrearPedidoRequest(Long productoId, EstadoPedido estado) {}
        //
        //    JSON entrante: { "productoId": 1, "estado": "PENDIENTE" }  ✅
        //    JSON entrante: { "productoId": 1, "estado": "BANANA" }     ❌ 400 Bad Request automático
        //
        // 2. En entidades JPA (base de datos), SIEMPRE con esta anotación:
        //
        //    @Enumerated(EnumType.STRING)   // guarda "PENDIENTE" como texto
        //    private EstadoPedido estado;
        //
        //    ⚠️ REGLA DE ORO: siempre EnumType.STRING, NUNCA EnumType.ORDINAL.
        //    ORDINAL guarda la posición (0, 1, 2...) y si reordenas el enum,
        //    todos los datos de tu base de datos quedan corruptos en silencio.
        //
        // 3. En Spring Security los roles son enums o constantes:
        //    .requestMatchers("/api/admin").hasRole("ADMIN")
        //
        // 4. En lógica de negocio de los servicios:
        //    if (pedido.getEstado() == EstadoPedido.PAGADO) { enviar(pedido); }

        // =========================================================================
        // Resumen completo del Tema — Enums
        // =========================================================================
        // ENUM             →  conjunto CERRADO de valores válidos
        //                     el compilador impide valores inválidos
        //
        // SINTAXIS:
        // public enum EstadoPedido { PENDIENTE, PAGADO, ENVIADO, ENTREGADO }
        //
        // USAR:
        // EstadoPedido e = EstadoPedido.PAGADO;     → sin new
        //
        // COMPARAR:
        // ✅ con == (seguro, no explota con null)    → al revés que los Strings
        //
        // MÉTODOS GRATIS:
        // values()      → todos los valores (para recorrer)
        // valueOf(s)    → String → enum (lanza excepción si no existe)
        // name()        → enum → String
        // ordinal()     → posición numérica ⚠️ no dependas de ella
        //
        // PUEDEN TENER:
        // ✅ Atributos (final), constructor (privado) y métodos
        // ✅ Cada valor se define con sus datos: PENDIENTE("descripción", false)
        //
        // CON SWITCH MODERNO:
        // ✅ Exhaustivo sin default — el compilador te avisa si falta un caso
        //
        // EN SPRING BOOT:
        // ✅ Estados y roles SIEMPRE son enums
        // ✅ Jackson convierte JSON ↔ enum automáticamente
        // ✅ JPA: @Enumerated(EnumType.STRING), NUNCA ORDINAL
        //
        // CUÁNDO USAR ENUM:
        // ✅ Estados (pedido, pago, envío)
        // ✅ Roles y permisos
        // ✅ Categorías fijas, tipos, niveles
        // ❌ Valores que cambian o crecen dinámicamente (eso va en la base de datos)
    }

    // =========================================================================
    // --- ENUMS Y CLASES DE APOYO ---
    // =========================================================================

    // --- Partes 2 a 7: enum completo con atributos y métodos ---
    public enum EstadoPedido {
        PENDIENTE("Esperando pago", false),
        PAGADO("Pago confirmado", false),
        ENVIADO("En camino al cliente", false),
        ENTREGADO("Recibido por el cliente", true);

        private final String descripcion;
        private final boolean esFinal;

        // Constructor de enum: siempre privado, lo llama Java una vez por valor
        EstadoPedido(String descripcion, boolean esFinal) {
            this.descripcion = descripcion;
            this.esFinal = esFinal;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public boolean esFinal() {
            return esFinal;
        }
    }

    // --- Parte 8: roles con lógica propia ---
    public enum RolUsuario {
        ADMIN(true),
        VENDEDOR(true),
        CLIENTE(false);

        private final boolean puedeBorrarProductos;

        RolUsuario(boolean puedeBorrarProductos) {
            this.puedeBorrarProductos = puedeBorrarProductos;
        }

        public boolean puedeBorrarProductos() {
            return puedeBorrarProductos;
        }
    }

    // Record de apoyo (combina lo aprendido en JavaModerno/Records.java)
    public record Usuario(String email, RolUsuario rol) {
    }
}
