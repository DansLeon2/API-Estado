# 🏛️ API-Estado (Spring Boot + SQL Server)

API RESTful desarrollada en **Java 17** con **Spring Boot 3** para la gestión y administración de catálogo de Estados (CRUD), diseñada para ser consumida por un cliente web en **Angular**.

---

## 🛠️ Tecnologías y Dependencias

- **Java**: 17
- **Framework**: Spring Boot 3.3.1
- **Persistencia**: Spring Data JPA / Hibernate
- **Base de Datos**: Microsoft SQL Server (`mssql-jdbc`)
- **Herramientas de Productividad**: Project Lombok
- **Gestor de Dependencias**: Maven
- **CORS Configurado para**: `http://localhost:4200` (Angular)

---

## 🗄️ Estructura de la Base de Datos

Tabla mapeada: `TEstado`

```sql
CREATE TABLE TEstado (
    EST_Id INT IDENTITY(1,1) PRIMARY KEY,
    EST_Nombre VARCHAR(100) NOT NULL,
    EST_Descripcion VARCHAR(255),
    EST_Activo INT DEFAULT 1 -- (1: Activo, 0: Inactivo)
);
```

---

## ⚙️ Configuración (`application.properties`)

Asegúrate de configurar la conexión a tu base de datos en `src/main/resources/application.properties`:

```properties
spring.application.name=API-Estado
server.port=8080

# Conexión a SQL Server
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=TuBaseDeDatos;trustServerCertificate=true;encrypt=false
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 📡 Documentación de Endpoints

### Base URL: `http://localhost:8080`

| Método | Endpoint | Descripción | Body Requerido |
| :--- | :--- | :--- | :--- |
| `GET` | `/Lista` | Obtiene todos los estados registrados | No |
| `GET` | `/ListarActivo` | Obtiene solo los estados activos (`activo = 1`) | No |
| `POST` | `/Crear` | Registra un nuevo estado | JSON (`Estado`) |
| `PUT` | `/Actualizar` | Actualiza un estado existente | JSON (`Estado` con `id`) |
| `DELETE` | `/Eliminar/{id}` | Elimina un estado por su ID | No |

---

### 📝 Ejemplos de Peticiones

#### 1. Crear Estado (`POST /Crear`)
```json
{
  "nombre": "En Proceso",
  "descripcion": "El trámite o elemento se encuentra en proceso",
  "activo": 1
}
```

#### 2. Actualizar Estado (`PUT /Actualizar`)
```json
{
  "id": 1,
  "nombre": "Completado",
  "descripcion": "El trámite o elemento ha finalizado exitosamente",
  "activo": 1
}
```

#### 3. Eliminar Estado (`DELETE /Eliminar/1`)
**Respuesta:** `String` con confirmación de eliminación.

---

## 🚀 Ejecución del Proyecto

### Requisitos previos:
- JDK 17 instalado y configurado en el `PATH`.
- Microsoft SQL Server en ejecución.

### Iniciar el servidor:
```bash
# Con Maven Wrapper (Windows)
.\mvnw.cmd spring-boot:run

# O con Maven instalado
mvn spring-boot:run
```

El servidor estará escuchando en `http://localhost:8080`.

---

## 🅰️ Consumo desde Angular (Ejemplo de Servicio)

```typescript
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Estado {
  id?: number;
  nombre: string;
  descripcion: string;
  activo: number;
}

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080';

  listarTodos(): Observable<Estado[]> {
    return this.http.get<Estado[]>(`${this.apiUrl}/Lista`);
  }

  listarActivos(): Observable<Estado[]> {
    return this.http.get<Estado[]>(`${this.apiUrl}/ListarActivo`);
  }

  crear(estado: Estado): Observable<Estado> {
    return this.http.post<Estado>(`${this.apiUrl}/Crear`, estado);
  }

  actualizar(estado: Estado): Observable<Estado> {
    return this.http.put<Estado>(`${this.apiUrl}/Actualizar`, estado);
  }

  eliminar(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/Eliminar/${id}`, { responseType: 'text' as 'json' });
  }
}
```

---

## 💡 Recomendaciones de Mejora para Prácticas

1. **Respuestas HTTP Estándar (`ResponseEntity`)**:
   - Devolver códigos de estado semánticos (`201 Created`, `200 OK`, `404 Not Found`, `400 Bad Request`) en lugar de devolver directamente la entidad o strings.
2. **Uso de DTOs y Validaciones**:
   - Integrar `spring-boot-starter-validation` para validar `@NotBlank`, `@Size` en `nombre` y `descripcion` antes de guardar en base de datos.
3. **Manejo Global de Excepciones**:
   - Implementar `@RestControllerAdvice` con `@ExceptionHandler` para retornar mensajes de error uniformes en JSON en caso de fallos o IDs inexistentes.
4. **Borrado Lógico (*Soft Delete*)**:
   - En lugar de eliminar el registro físico (`DELETE`), actualizar el campo `activo = 0`.
5. **Documentación Interactiva con Swagger / OpenAPI**:
   - Añadir la dependencia `springdoc-openapi-starter-webmvc-ui` para probar los endpoints interactivamente desde `http://localhost:8080/swagger-ui.html`.
6. **Estandarización de Rutas REST**:
   - Agrupar los endpoints bajo un prefijo estándar como `/api/v1/estados` (`GET /`, `POST /`, `PUT /{id}`, `DELETE /{id}`).