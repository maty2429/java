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

5. **Java avanzado para Spring Boot** *(pendiente)*
   Lambdas, streams, `Optional`, fechas y otros temas que se usan mucho en APIs REST.

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
- [Excepciones: `try`, `catch`, `finally` y errores personalizados](./estructuras_control/Exepciones.java)

> Nota: el archivo se llama `Exepciones.java` actualmente. En esta mejora no se renombró para no romper configuraciones o referencias que ya puedas tener en IntelliJ.

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

- [Colecciones: `List`, `Set`, `Map`](./colecciones/Coleccione.java)
- [Generics: tipos parametrizados y wildcards](./colecciones/Generics.java)
- [Lambdas](./colecciones/Lambdas.java)
- [Interfaces funcionales](./colecciones/InterfacesFuncionales.java)
- [Stream API](./colecciones/Stream.java)
- [Optional](./colecciones/Optional.java)
- [Sequenced Collections](./colecciones/Sequenced.java)

> Nota: `Sequenced.java` explica APIs de Java 21, pero el ejemplo ejecutable está hecho compatible con Java 17 para que el proyecto completo compile con tu JDK actual.

> Nota: `InterfacesFuncionales.java` conserva el apunte original tal como fue creado. Hoy parece cubrir bastante contenido de Stream API, así que queda marcado como tema para revisar y pulir más adelante sin borrar nada.

### 5. Java avanzado *(en preparación)*

La carpeta [`java_avanzado`](./java_avanzado/) existe como espacio futuro para:

- lambdas
- streams
- `Optional`
- fechas con `LocalDate`, `LocalDateTime`
- manejo avanzado de errores
- conceptos que conectan directamente con Spring Boot

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
- `estructuras_control.Exepciones`
- `poo.Clases`
- `poo.Encapsulamiento`
- `poo.Herencia`

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
java -cp /private/tmp/javaex-classes estructuras_control.Exepciones
java -cp /private/tmp/javaex-classes poo.Clases
java -cp /private/tmp/javaex-classes poo.Encapsulamiento
java -cp /private/tmp/javaex-classes poo.Herencia
java -cp /private/tmp/javaex-classes colecciones.Coleccione
java -cp /private/tmp/javaex-classes colecciones.Generics
java -cp /private/tmp/javaex-classes colecciones.Lambdas
java -cp /private/tmp/javaex-classes colecciones.InterfacesFuncionales
java -cp /private/tmp/javaex-classes colecciones.Stream
java -cp /private/tmp/javaex-classes colecciones.Optional
java -cp /private/tmp/javaex-classes colecciones.Sequenced
```

---

## 🧩 Nota sobre las clases de apoyo

Algunos temas de POO usan clases de ejemplo llamadas `Producto`, `Usuario`, `BaseEntity`, etc. Es normal que se repitan los nombres, porque cada archivo explica un concepto distinto desde cero.

Para que el proyecto completo compile sin conflictos, esas clases de apoyo viven dentro de su archivo de lección como clases internas `static`. Así se mantiene la explicación clara y, al mismo tiempo, Java no confunde el `Producto` del tema de clases con el `Producto` del tema de herencia o encapsulamiento.

---

## ✅ Estado actual del proyecto

- Los módulos básicos, estructuras de control, POO y colecciones tienen apuntes ejecutables.
- El proyecto completo puede compilar junto.
- La carpeta `java_avanzado` está reservada para próximos apuntes.
- No es una aplicación final: es una base de estudio para volver, leer, ejecutar y recordar.
