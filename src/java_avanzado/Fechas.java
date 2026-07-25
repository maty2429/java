package java_avanzado;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fechas {
    public static void main(String[] args) {

        // =========================================================================
        // Fechas con java.time — LocalDate, LocalDateTime, Instant y formateo
        // =========================================================================

        // =========================================================================
        // Parte 1 — El problema antes de la solución
        // =========================================================================
        // Toda API REST maneja fechas constantemente: fecha de creación de un
        // registro, fecha de vencimiento, fecha de nacimiento, timestamps de logs...
        //
        // Java tiene clases viejas para fechas: Date y Calendar (de 1996). Son tan
        // malas que la comunidad entera las abandonó:
        //
        // ❌ Date es MUTABLE: cualquiera puede cambiar tu fecha por debajo
        // ❌ Los meses empiezan en 0: enero = 0, diciembre = 11 (fuente infinita de bugs)
        // ❌ Calendar es verboso y confuso
        // ❌ No son thread-safe (problemas en servidores con muchos usuarios)
        //
        // // Así de feo era crear el 25 de diciembre de 2024 con Calendar:
        // Calendar cal = Calendar.getInstance();
        // cal.set(2024, 11, 25); // ¿11? sí, porque diciembre es 11. Horrible.
        //
        // Java 8 introdujo el paquete java.time, diseñado desde cero. Es lo ÚNICO
        // que debes usar. Si ves Date o Calendar en un tutorial, es un tutorial viejo.
        //
        // Las clases principales:
        // LocalDate      → solo fecha            (2026-06-10)
        // LocalTime      → solo hora             (14:30:00)
        // LocalDateTime  → fecha y hora          (2026-06-10T14:30:00)
        // Instant        → momento exacto en UTC (timestamp universal)
        // "Local" significa "sin zona horaria": es la fecha/hora "del calendario",
        // sin saber en qué país estás.

        // =========================================================================
        // Parte 2 — Crear fechas
        // =========================================================================
        // Dos formas principales: now() para el momento actual, of() para una
        // fecha específica. Nota que NO se usa new — se usan métodos estáticos
        // (igual que Optional.of() que ya conoces).

        // 💡 Demostración real de la Parte 2 en ejecución:
        {
            System.out.println("=== Parte 2: Crear fechas ===");

            // El momento actual
            LocalDate hoy = LocalDate.now();
            LocalTime ahora = LocalTime.now();
            LocalDateTime ahoraMismo = LocalDateTime.now();

            System.out.println("Hoy: " + hoy);
            System.out.println("Hora actual: " + ahora);
            System.out.println("Fecha y hora: " + ahoraMismo);

            // Una fecha específica con of() — ¡enero es 1, como debe ser!
            LocalDate navidad = LocalDate.of(2026, 12, 25);
            LocalDateTime lanzamiento = LocalDateTime.of(2026, 12, 25, 10, 30, 0);

            System.out.println("Navidad: " + navidad);
            System.out.println("Lanzamiento: " + lanzamiento);

            // Leer las partes de una fecha
            System.out.println("Año: " + navidad.getYear());           // 2026
            System.out.println("Mes: " + navidad.getMonthValue());      // 12
            System.out.println("Día: " + navidad.getDayOfMonth());      // 25
            System.out.println("Día de la semana: " + navidad.getDayOfWeek()); // FRIDAY
            System.out.println();
        }

        // =========================================================================
        // Parte 3 — Inmutabilidad: las fechas NO se modifican, se crean nuevas
        // =========================================================================
        // Igual que los Strings y los Records: las clases de java.time son
        // INMUTABLES. Los métodos plusDays(), minusMonths(), withYear() NO cambian
        // la fecha original. Retornan una fecha NUEVA.
        //
        // Este es EL error clásico de principiante:
        //
        // LocalDate fecha = LocalDate.of(2026, 1, 15);
        // fecha.plusDays(10);              // ❌ no hace nada visible, el resultado se pierde
        // System.out.println(fecha);      // sigue siendo 2026-01-15
        //
        // ✅ Siempre guarda el resultado:
        // LocalDate nuevaFecha = fecha.plusDays(10);  // 2026-01-25

        // 💡 Demostración real de la Parte 3 en ejecución:
        {
            System.out.println("=== Parte 3: Inmutabilidad y aritmética de fechas ===");
            LocalDate fecha = LocalDate.of(2026, 1, 15);

            LocalDate enDiezDias = fecha.plusDays(10);
            LocalDate haceUnMes = fecha.minusMonths(1);
            LocalDate elProximoAnio = fecha.plusYears(1);
            LocalDate cambioDeAnio = fecha.withYear(2030); // reemplaza solo el año

            System.out.println("Original (no cambió): " + fecha);
            System.out.println("Más 10 días: " + enDiezDias);
            System.out.println("Menos 1 mes: " + haceUnMes);
            System.out.println("Más 1 año: " + elProximoAnio);
            System.out.println("Con año 2030: " + cambioDeAnio);

            // Se pueden encadenar (igual que los Streams):
            LocalDate vencimiento = LocalDate.now().plusMonths(1).plusDays(15);
            System.out.println("Vencimiento (hoy + 1 mes + 15 días): " + vencimiento);
            System.out.println();
        }

        // =========================================================================
        // Parte 4 — Comparar fechas
        // =========================================================================
        // Para comparar fechas NUNCA uses == (compara referencias, como en Strings).
        // java.time tiene métodos que se leen como inglés:
        //
        // isBefore(otra)  → ¿es anterior a la otra?
        // isAfter(otra)   → ¿es posterior a la otra?
        // isEqual(otra)   → ¿es la misma fecha?

        // 💡 Demostración real de la Parte 4 en ejecución:
        {
            System.out.println("=== Parte 4: Comparar fechas ===");
            LocalDate hoy = LocalDate.now();
            LocalDate vencimiento = LocalDate.of(2026, 12, 31);

            System.out.println("¿Hoy es antes del vencimiento? " + hoy.isBefore(vencimiento));
            System.out.println("¿Hoy es después del vencimiento? " + hoy.isAfter(vencimiento));

            // Caso real de API: validar si una promoción sigue activa
            if (hoy.isBefore(vencimiento)) {
                System.out.println("✅ La promoción sigue activa");
            } else {
                System.out.println("❌ La promoción venció");
            }
            System.out.println();
        }

        // =========================================================================
        // Parte 5 — Period y Duration: medir tiempo entre dos momentos
        // =========================================================================
        // Dos clases que se confunden, la diferencia es simple:
        //
        // Period   → diferencia en AÑOS / MESES / DÍAS    (entre LocalDate)
        //            "¿cuántos años tiene este usuario?"
        // Duration → diferencia en HORAS / MINUTOS / SEGUNDOS (entre tiempos)
        //            "¿cuánto tardó esta petición?"

        // 💡 Demostración real de la Parte 5 en ejecución:
        {
            System.out.println("=== Parte 5: Period y Duration ===");

            // Period: calcular la edad de un usuario (caso real de API)
            LocalDate fechaNacimiento = LocalDate.of(2000, 3, 15);
            Period edad = Period.between(fechaNacimiento, LocalDate.now());
            System.out.println("Edad: " + edad.getYears() + " años, " + edad.getMonths() + " meses y " + edad.getDays() + " días");

            // Duration: cuánto tiempo pasó entre dos momentos
            LocalDateTime inicioSesion = LocalDateTime.of(2026, 6, 10, 9, 0, 0);
            LocalDateTime finSesion = LocalDateTime.of(2026, 6, 10, 11, 45, 30);
            Duration duracion = Duration.between(inicioSesion, finSesion);
            System.out.println("La sesión duró: " + duracion.toHours() + " horas y " + duracion.toMinutesPart() + " minutos");

            // Días totales entre dos fechas (útil para vencimientos)
            long diasParaNavidad = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2026, 12, 25));
            System.out.println("Días hasta navidad: " + diasParaNavidad);
            System.out.println();
        }

        // =========================================================================
        // Parte 6 — DateTimeFormatter: convertir fecha ↔ texto
        // =========================================================================
        // En una API REST las fechas viajan como TEXTO (JSON no tiene tipo fecha).
        // Necesitas dos operaciones constantemente:
        //
        // format() → fecha → String     (para mostrar/enviar)
        // parse()  → String → fecha     (para recibir del cliente)
        //
        // El formato estándar mundial de las APIs es ISO-8601:
        // "2026-06-10"           (fecha)
        // "2026-06-10T14:30:00"  (fecha y hora — la T separa fecha de hora)
        // Es el formato que java.time usa por defecto y el que Spring Boot
        // usa automáticamente en el JSON. Si puedes elegir, SIEMPRE usa ISO.

        // 💡 Demostración real de la Parte 6 en ejecución:
        {
            System.out.println("=== Parte 6: Formatear y parsear fechas ===");
            LocalDate fecha = LocalDate.of(2026, 6, 10);

            // format(): de fecha a texto con el formato que quieras
            DateTimeFormatter formatoLatino = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");

            System.out.println("ISO (por defecto): " + fecha);                       // 2026-06-10
            System.out.println("Formato latino: " + fecha.format(formatoLatino));    // 10/06/2026
            System.out.println("Formato largo: " + fecha.format(formatoLargo));

            // Las letras del patrón:
            // dd   → día (10)        MM   → mes en número (06)
            // MMMM → mes en palabra  yyyy → año (2026)
            // HH   → hora 24h        mm   → minutos       ss → segundos
            // ⚠️ MM es mes y mm es minutos. Mayúsculas/minúsculas IMPORTAN.

            // parse(): de texto a fecha (lo que haces al recibir datos del cliente)
            LocalDate desdeIso = LocalDate.parse("2026-12-25"); // ISO no necesita formatter
            LocalDate desdeLatino = LocalDate.parse("25/12/2026", formatoLatino);
            System.out.println("Parseado desde ISO: " + desdeIso);
            System.out.println("Parseado desde dd/MM/yyyy: " + desdeLatino);

            // Si el texto no coincide con el formato → DateTimeParseException
            try {
                LocalDate.parse("25-12-2026"); // guiones pero en orden no-ISO
            } catch (DateTimeParseException e) {
                System.out.println("Excepción capturada: el texto no tiene formato ISO válido");
            }
            // En tu API esto se traduce a: validar la fecha que manda el cliente
            // y responder 400 Bad Request si viene mal formada.
            System.out.println();
        }

        // =========================================================================
        // Parte 7 — Instant: el timestamp universal
        // =========================================================================
        // LocalDateTime no sabe de zonas horarias: "las 10:00" en Chile y en Japón
        // son momentos distintos del universo. Para registrar un momento EXACTO
        // (independiente del país) existe Instant: un timestamp en UTC.
        //
        // Regla práctica para tus APIs:
        // Instant / LocalDateTime en UTC → para registrar CUÁNDO pasó algo en el
        //                                   servidor (createdAt, updatedAt, logs)
        // LocalDate                      → para fechas "de calendario" sin hora
        //                                   (fecha de nacimiento, vencimiento)
        //
        // Existe también ZonedDateTime (fecha + hora + zona horaria, ej:
        // "10:00 en America/Santiago"). Solo la necesitas cuando muestras horas
        // al usuario en SU zona. El estándar en backend es: guarda todo en UTC,
        // convierte a zona local solo al mostrar.

        // 💡 Demostración real de la Parte 7 en ejecución:
        {
            System.out.println("=== Parte 7: Instant (timestamp UTC) ===");
            Instant ahora = Instant.now();
            System.out.println("Instant actual (UTC): " + ahora);
            // Nota la Z al final: significa "Zulu time" = UTC

            // Medir cuánto tarda una operación (típico en logs de APIs)
            Instant inicio = Instant.now();
            for (int i = 0; i < 1_000_000; i++) { /* simula trabajo */ }
            Instant fin = Instant.now();
            System.out.println("La operación tardó: " + Duration.between(inicio, fin).toMillis() + " ms");
            System.out.println();
        }

        // =========================================================================
        // Parte 8 — Ejemplo integrado estilo Spring Boot
        // =========================================================================
        // Juntemos todo en un caso real: un producto con fecha de creación y una
        // promoción con vencimiento, como lo tendrías en tu API.

        // 💡 Demostración real de la Parte 8 en ejecución:
        {
            System.out.println("=== Parte 8: Ejemplo integrado (DTO con fechas) ===");

            ProductoResponse producto = new ProductoResponse(
                    1L,
                    "Laptop HP",
                    1500.0,
                    LocalDateTime.now(),                  // createdAt — lo pone el servidor
                    LocalDate.now().plusDays(30)          // promoción válida por 30 días
            );

            System.out.println("Producto: " + producto.nombre());
            System.out.println("Creado: " + producto.fechaCreacion());
            System.out.println("Promo vence: " + producto.finPromocion());
            System.out.println("¿Promo activa? " + producto.promocionActiva());
            System.out.println();
        }

        // =========================================================================
        // Parte 9 — Conexión con Spring Boot
        // =========================================================================
        // 1. Jackson convierte las fechas a ISO-8601 en el JSON automáticamente:
        //
        //    public record ProductoResponse(Long id, String nombre, LocalDateTime fechaCreacion) {}
        //
        //    JSON que genera Spring Boot:
        //    { "id": 1, "nombre": "Laptop", "fechaCreacion": "2026-06-10T14:30:00" }
        //
        //    Y al revés: si el cliente manda "2026-06-10", Jackson lo convierte a
        //    LocalDate solo. Si viene mal formada → 400 Bad Request automático.
        //
        // 2. Si necesitas otro formato en el JSON, existe @JsonFormat:
        //    @JsonFormat(pattern = "dd/MM/yyyy")
        //    private LocalDate fechaNacimiento;
        //    (Pero el consejo profesional es: deja ISO y que el frontend formatee.)
        //
        // 3. En entidades JPA verás los campos de auditoría que toda tabla tiene:
        //    @CreationTimestamp
        //    private LocalDateTime createdAt;
        //    @UpdateTimestamp
        //    private LocalDateTime updatedAt;
        //
        // 4. Los tipos de java.time mapean directo a columnas SQL:
        //    LocalDate → DATE,  LocalDateTime → TIMESTAMP,  Instant → TIMESTAMP (UTC)

        // =========================================================================
        // Resumen completo del Tema — Fechas
        // =========================================================================
        // USA SIEMPRE java.time (Java 8+). NUNCA Date ni Calendar (obsoletos).
        //
        // CLASES:
        // LocalDate       → solo fecha (2026-06-10)
        // LocalTime       → solo hora (14:30)
        // LocalDateTime   → fecha + hora (2026-06-10T14:30)
        // Instant         → momento exacto universal en UTC (timestamps)
        // ZonedDateTime   → fecha + hora + zona horaria (solo para mostrar al usuario)
        //
        // CREAR:
        // LocalDate.now()              → hoy
        // LocalDate.of(2026, 12, 25)   → fecha específica (¡enero es 1!)
        //
        // INMUTABLES:
        // plusDays / minusMonths / withYear → retornan fecha NUEVA
        // ⚠️ fecha.plusDays(10) sin guardar el resultado no hace nada
        //
        // COMPARAR:
        // isBefore / isAfter / isEqual     → nunca con ==
        //
        // DIFERENCIAS:
        // Period.between(f1, f2)     → años/meses/días (edad)
        // Duration.between(t1, t2)   → horas/minutos/segundos
        // ChronoUnit.DAYS.between()  → total de días
        //
        // TEXTO ↔ FECHA:
        // fecha.format(formatter)    → fecha → String
        // LocalDate.parse(texto)     → String → fecha (DateTimeParseException si falla)
        // ISO-8601 ("2026-06-10")    → EL formato estándar de las APIs REST
        //
        // EN SPRING BOOT:
        // ✅ Jackson maneja fechas ISO en JSON automáticamente
        // ✅ createdAt/updatedAt en cada entidad (LocalDateTime o Instant)
        // ✅ Guarda en UTC, muestra en la zona del usuario
        // ✅ @JsonFormat solo si necesitas un formato distinto a ISO
    }

    // =========================================================================
    // --- ESTRUCTURAS DE APOYO ---
    // =========================================================================

    // DTO estilo Spring Boot que combina Records (JavaModerno) con fechas
    public record ProductoResponse(
            Long id,
            String nombre,
            double precio,
            LocalDateTime fechaCreacion,
            LocalDate finPromocion
    ) {
        // Método de conveniencia: lógica simple basada en las fechas
        public boolean promocionActiva() {
            return LocalDate.now().isBefore(finPromocion);
        }
    }
}
