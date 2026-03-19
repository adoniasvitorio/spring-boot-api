
### Criar conta

```
POST /accounts
```

```sh
curl -X POST http://localhost:8080/accounts \
-H "Content-Type: application/json" \
-d '{"name": "Adonias Vitorio", "cpf": "12345678901"}'
```

---

### Listar contas

```
GET /accounts
```

```sh
curl http://localhost:8080/accounts
```