# Tourist Record Update — Spring Boot

A small Spring Boot REST API matching the lab prompt: validate a custom `Token` header on PUT, update an existing tourist record, and test valid/invalid tokens.

## Requirements
- Java 21
- Spring Tool Suite (STS) or Maven

## Import into STS
1. Extract the ZIP.
2. Open STS → **File → Import → Maven → Existing Maven Projects**.
3. Select the extracted `tourist-update-springboot` folder and finish.
4. Right-click `TouristUpdateApplication.java` → **Run As → Spring Boot App**.

The app runs at `http://localhost:8080`. H2 is in-memory, so data resets when the app restarts.

## Test with Postman

### 1. Create a sample tourist
`POST http://localhost:8080/api/tourists`
Body → raw → JSON:
```json
{
  "name": "Asha",
  "visitedDestination": "Tirupati",
  "contactDetails": "asha@example.com"
}
```
This returns an ID (usually 1 for the first record).

### 2. Update a tourist with a valid token
`PUT http://localhost:8080/api/tourists/1`
Headers:
- `Content-Type: application/json`
- `Token: demo-valid-token`

Body:
```json
{
  "name": "Asha Rao",
  "visitedDestination": "Araku Valley",
  "contactDetails": "asha.rao@example.com"
}
```
Expected: `200 OK` and `"message": "Tourist Updated Successfully"`.

### 3. Test invalid token
Send the same PUT request with `Token: wrong-token` or omit the header.
Expected: `401 Unauthorized` and `{"error":"Invalid or missing token"}`.

### Other behavior
- Unknown tourist ID with a valid token → `404 Not Found`.
- Missing/blank required fields → `400 Bad Request`.
- `GET http://localhost:8080/api/tourists/1` retrieves a record.
- H2 console: `http://localhost:8080/h2-console`, JDBC URL `jdbc:h2:mem:touristdb`, username `sa`, blank password.

## Important security note
For this classroom exercise, the token is a fixed demo token (`demo-valid-token`) so the valid/invalid token paths are easy to test. This is not production authentication. For a real application, verify signed JWTs or use Spring Security.
