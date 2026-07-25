# 📚 Mi Biblia de Java para Spring Boot

Este proyecto es mi documentación personal de Java. No busca ser una aplicación real: es un cuaderno de estudio organizado por módulos, con ejemplos ejecutables y explicaciones largas para volver después y recordar qué hace cada cosa.

La idea principal es aprender Java pensando en Spring Boot, pero sin saltarse la base. Por eso cada archivo `.java` explica un tema con comentarios, ejemplos, errores comunes y conexiones con lo que después aparece en proyectos reales.

---

## 🧭 Ruta recomendada de estudio

Si vuelves a este proyecto después de un tiempo, este orden te ayuda a retomar sin perderte:

1. **Conceptos básicos**
   Variables, tipos, operadores, strings, arrays y `var`.

2. **Estructuras de control**
   Condicionales, bucles y manejo de errores.

3. **Programación Orientada a Objetos**
   Clases, métodos, encapsulamiento, herencia, polimorfismo, interfaces y clases anónimas.

4. **Colecciones y Java funcional**
   Listas, mapas, sets, generics, lambdas, streams, Optional y notas de Sequenced Collections.

5. **Java moderno**
   Records: la base de los DTOs que usarás en cada endpoint REST.

6. **Java avanzado para Spring Boot**
   Enums (estados y roles) y fechas con `java.time`. Próximamente: sealed interfaces, pattern matching y más temas que conectan directo con APIs REST.

---

## 🗂️ Índice de temas actuales

### 1. [Conceptos básicos](./basico/)

- [Variables y tipos de datos](./basico/VariablesYTipos.java)
- [Operadores](./basico/Operadores.java)
- [Strings](./basico/Strings.java)
- [Arrays](./basico/ArraysEstudio.java)
- [`var` e inferencia de tipos](./basico/Vars.java)

### 2. [Estructuras de control](./estructuras_control/)

- [Condicionales: `if`, `else`, `switch`](./estructuras_control/Condicionales.java)
- [Bucles: `for` y `for-each`](./estructuras_control/Bucles.java)
- [Excepciones: `try`, `catch`, `finally` y errores personalizados](./estructuras_control/Excepciones.java)

### 3. [Programación Orientada a Objetos](./poo/)

- [Clases y objetos](./poo/Clases.java)
- [Métodos](./poo/Metodos.java)
- [Encapsulamiento](./poo/Encapsulamiento.java)
- [Herencia](./poo/Herencia.java)
- [Polimorfismo](./poo/Polimorfismo.java)
- [Interfaces y clases abstractas](./poo/Interfaces.java)
- [Clases anónimas](./poo/Anónimas.java)

### 4. [Colecciones y Java funcional](./colecciones/)

Este módulo reúne las estructuras y herramientas que más vas a usar al trabajar con datos en Java y Spring Boot.

- [Colecciones: `List`, `Set`, `Map`](./colecciones/Colecciones.java)
- [Generics: tipos parametrizados y wildcards](./colecciones/Generics.java)
- [Lambdas](./colecciones/Lambdas.java)
- [Interfaces funcionales](./colecciones/InterfacesFuncionales.java)
- [Stream API](./colecciones/Stream.java)
- [Optional](./colecciones/Optional.java)
- [Sequenced Collections](./colecciones/Sequenced.java)

> Nota: `Sequenced.java` explica APIs de Java 21, pero el ejemplo ejecutable está hecho compatible con Java 17 para que el proyecto completo compile con tu JDK actual.

> Nota: `InterfacesFuncionales.java` conserva el apunte original tal como fue creado. Hoy parece cubrir bastante contenido de Stream API, así que queda marcado como tema para revisar y pulir más adelante sin borrar nada.

### 5. [Java moderno](./JavaModerno/)

Características de Java 16+ que son la base del código moderno de Spring Boot.

- [Records: DTOs inmutables, constructor compacto y validación](./JavaModerno/Records.java)

### 6. [Java avanzado para Spring Boot](./java_avanzado/)

Temas de Java que vas a usar sí o sí en cada API REST.

- [Enums: estados, roles y constantes con superpoderes](./java_avanzado/Enums.java)
- [Fechas: `LocalDate`, `LocalDateTime`, `Instant`, formateo ISO](./java_avanzado/Fechas.java)

Temas futuros para esta carpeta:

- sealed interfaces + pattern matching (Java 21)
- anotaciones: qué son y por qué Spring vive de ellas
- `BigDecimal` para dinero
- manejo avanzado de errores

---

## ▶️ Cómo ejecutar los ejemplos

Cada archivo principal tiene su propio método `main`, así que puedes ejecutar los temas de forma independiente.

### Desde IntelliJ IDEA

1. Abre el archivo que quieres estudiar.
2. Busca el método `public static void main(String[] args)`.
3. Presiona el botón verde de ejecutar junto al método o la clase.

Ejemplos recomendados:

- `basico.VariablesYTipos`
- `estructuras_control.Bucles`
- `estructuras_control.Excepciones`
- `poo.Clases`
- `poo.Encapsulamiento`
- `poo.Herencia`
- `JavaModerno.Records`
- `java_avanzado.Enums`
- `java_avanzado.Fechas`

### Desde terminal

Desde la carpeta raíz del proyecto:

```bash
javac -encoding UTF-8 -d /private/tmp/javaex-classes $(find src -name "*.java")
```

Luego puedes ejecutar una clase:

```bash
java -cp /private/tmp/javaex-classes Main
java -cp /private/tmp/javaex-classes basico.VariablesYTipos
java -cp /private/tmp/javaex-classes estructuras_control.Bucles
java -cp /private/tmp/javaex-classes estructuras_control.Excepciones
java -cp /private/tmp/javaex-classes poo.Clases
java -cp /private/tmp/javaex-classes poo.Encapsulamiento
java -cp /private/tmp/javaex-classes poo.Herencia
java -cp /private/tmp/javaex-classes colecciones.Colecciones
java -cp /private/tmp/javaex-classes colecciones.Generics
java -cp /private/tmp/javaex-classes colecciones.Lambdas
java -cp /private/tmp/javaex-classes colecciones.InterfacesFuncionales
java -cp /private/tmp/javaex-classes colecciones.Stream
java -cp /private/tmp/javaex-classes colecciones.Optional
java -cp /private/tmp/javaex-classes colecciones.Sequenced
java -cp /private/tmp/javaex-classes JavaModerno.Records
java -cp /private/tmp/javaex-classes java_avanzado.Enums
java -cp /private/tmp/javaex-classes java_avanzado.Fechas
```

---

## 🧩 Nota sobre las clases de apoyo

Algunos temas de POO usan clases de ejemplo llamadas `Producto`, `Usuario`, `BaseEntity`, etc. Es normal que se repitan los nombres, porque cada archivo explica un concepto distinto desde cero.

Para que el proyecto completo compile sin conflictos, esas clases de apoyo viven dentro de su archivo de lección como clases internas `static`. Así se mantiene la explicación clara y, al mismo tiempo, Java no confunde el `Producto` del tema de clases con el `Producto` del tema de herencia o encapsulamiento.

---

## 🚀 Camino a Spring Boot — qué sigue después de esta biblia

Esta biblia cubre el Java que necesitas ANTES de tocar Spring Boot. Esta tabla te dice dónde estás y qué viene después (lo de Spring va en un proyecto aparte, no en este repo):

### ✅ Java que ya tienes cubierto aquí

| Tema | Por qué importa en una API REST |
|---|---|
| POO completa (clases, herencia, interfaces, polimorfismo) | Services, Controllers y la inyección de dependencias son POO pura |
| Excepciones personalizadas | Se convierten en respuestas HTTP (404, 400, 500) |
| Colecciones (`List`, `Map`, `Set`) | Toda respuesta JSON con varios elementos es una `List` |
| Lambdas + Streams + Optional | El día a día de los Services (`findById().orElseThrow()`) |
| Records | Tus DTOs: Request y Response de cada endpoint |
| Enums | Estados de pedidos, roles de usuario |
| Fechas (`java.time`) | `createdAt`, vencimientos, timestamps en JSON |

### 📋 Lo que viene cuando empieces Spring Boot (en orden)

1. **Maven y el `pom.xml`** — cómo se gestionan las dependencias de un proyecto real.
2. **Anotaciones** — `@RestController`, `@Service`, `@Autowired`: entender que son "etiquetas" que Spring lee para configurar todo.
3. **Tu primer endpoint** — `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestBody`.
4. **Arquitectura en capas** — Controller → Service → Repository, y por qué se separa así.
5. **JPA / Hibernate** — `@Entity`, `JpaRepository`: hablar con la base de datos sin escribir SQL.
6. **Validación** — `@Valid`, `@NotBlank`, `@Min`: validar los Request automáticamente.
7. **Manejo global de errores** — `@RestControllerAdvice`: convertir tus excepciones en respuestas JSON limpias.
8. **Testing** — JUnit y MockMvc para probar tus endpoints.

> Consejo: crea el proyecto Spring Boot en [start.spring.io](https://start.spring.io) con las dependencias **Spring Web**, **Spring Data JPA** y **H2 Database** para empezar.

---

## ✅ Estado actual del proyecto

- Los módulos básicos, estructuras de control, POO, colecciones, Java moderno y Java avanzado tienen apuntes ejecutables.
- El proyecto completo puede compilar junto.
- Se corrigieron los nombres `Exepciones.java` → `Excepciones.java` y `Coleccione.java` → `Colecciones.java`.
- La carpeta `java_avanzado` ya tiene Enums y Fechas; quedan pendientes sealed interfaces, pattern matching, anotaciones y `BigDecimal`.
- No es una aplicación final: es una base de estudio para volver, leer, ejecutar y recordar.
