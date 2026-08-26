# API de precios

Prueba técnica para consultar el precio aplicable a un producto de una cadena
en una fecha y hora determinadas.

## Requisitos

- Java 17 o superior
- Maven Wrapper incluido

## Ejecutar

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
./mvnw.cmd spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

## Endpoint

```http
GET /api/v1/prices?queryDate=2020-06-14T16:00:00%2B02:00&productId=35455&brandId=1
```

Ejemplo de respuesta:

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00+02:00",
  "endDate": "2020-06-14T18:30:00+02:00",
  "price": 25.45,
  "currency": "EUR"
}
```

Cuando coinciden varias tarifas, se devuelve la de mayor `priority`.

Si falta un parámetro o su formato no es válido, la API devuelve `400`:

```json
{
  "status": 400,
  "message": "Parámetros de entrada no válidos."
}
```

Si no existe una tarifa aplicable, devuelve `404`.

## Tests

```bash
./mvnw test
```

Los tests de integración cubren las cinco peticiones solicitadas en el
enunciado: 
-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

## Base de datos

Se utiliza H2 en memoria. Flyway crea la tabla `prices` y carga los datos de
ejemplo al iniciar la aplicación:

- `src/main/resources/db/migration/V1__initial_schema.sql`
- `src/main/resources/db/migration/V2__initial_data.sql`

La consola de H2 está disponible en `http://localhost:8080/h2-console`

## Documentación OpenAPI

La interfaz Swagger UI está disponible en `http://localhost:8080/swagger-ui.html`.
El contrato se encuentra en `openapi/prices-api.yaml`.

## Estructura

El código sigue una estructura hexagonal sencilla:

- `domain`: modelo de negocio.
- `application`: caso de uso y puertos.
- `infrastructure/input/rest`: controlador y contrato HTTP.
- `infrastructure/output/database`: persistencia JPA y adaptador.
