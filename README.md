# Test technique Unibet

Ce test est petite application web de gestion des événements sportifs en direct.
## 1.Pour commencer


### 1.1. Installer les dependances
```
$ mvn clean install
```
### 1.2. Lancer l'application

```
$ mvn spring-boot:run
```

### 3. API

Methode | Path                   | Description                                   |
-------|------------------------|-----------------------------------------------|
GET    | /events                | Récupère tous les événements de la base       |
GET    | events/{id}/selections | Récupère toutes les sélections d'un événément |
POST   | /bets/add              | Enregistre un pari                            |
GET    | /customers/current     | Récupère l'utlisateur actuel                  |

Il est possible d'utiliser les l'API ils sont accessible à l'adresse : http://localhost:8887/api/v1

