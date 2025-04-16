# ✈️ LOT Flight Reservation System

---

## 🇵🇱 Wersja Polska

### 🧠 Opis

System rezerwacji lotów stworzony w ramach zadania rekrutacyjnego do działu developmentu PLL LOT.

Aplikacja umożliwia kompleksowe zarządzanie rezerwacjami lotniczymi — tworzenie, modyfikację, przeglądanie oraz usuwanie:

- lotów
- pasażerów
- rezerwacji

---

### 🛠 Technologie

- **Backend:** Java 21, Spring Boot, Gradle
- **Frontend:** React (Vite + TypeScript)
- **Konteneryzacja:** Docker + Docker Compose
- **Build system:** Gradle + npm

---

### ✅ Funkcjonalności

- Dodawanie, edycja i usuwanie lotów
- Dodawanie, edycja i usuwanie pasażerów
- Tworzenie rezerwacji z walidacją dostępności miejsc
- Odczyt danych (CRUD) dla wszystkich encji
- Wysyłanie potwierdzenia rezerwacji mailem (przygotowane)
- Kompletne aplikacje backendowe i frontendowe

---

## 🚀 Jak uruchomić aplikację lokalnie (Docker)

### 📦 Wymagania:
- Docker
- Docker Compose

🧰 **Krok po kroku:**

1. Przejdź do katalogu głównego projektu
2. Uruchom aplikację:

```bash
docker-compose up --build
```

---

#### 🌐 Adresy lokalne:

- **Frontend (UI):** http://localhost:3000  
- **Backend (API):** http://localhost:8080

---

#### 🧪 Testy jednostkowe

W aplikacji backendowej znajdują się testy jednostkowe, które można uruchomić komendą:

```bash
cd backend/backend
./gradlew test
```

Wynik testów zostanie wyświetlony w konsoli.

---

#### 📤 Przykładowe dane do testowania (POST JSON)

**Flight:**

```json
{
  "flightNumber": "LO124",
  "placeOfDeparture": "Warsaw",
  "placeOfArrival": "Moscow",
  "flightDuration": "PT2H30M",
  "oneWayFlight": true,
  "availableSeats": ["1A", "1B", "2A"]
}
```

**Passenger:**

```json
{
  "firstName": "Kuba",
  "lastName": "Sajnóg",
  "email": "jakubsajnog@gmail.com",
  "phoneNumber": "603337347",
  "passengerId": 1
}
```

**Reservation:**

```json
{
  "reservationNumber": 1001,
  "seatNumber": "1A",
  "alreadyDeparted": false,
  "passengerId": 1,
  "flightNumber": "LO124"
}
```

---

#### 🗨️ Kontakt

W razie pytań zapraszam do kontaktu.  
Z przyjemnością udzielę dodatkowych informacji.

---

---

## 🇬🇧 English Version

### 🧠 Description

This is a flight reservation system created as part of a recruitment task for the development department of LOT Polish Airlines.

The application allows full management of flight reservations — creation, modification, viewing and deletion of:

- flights  
- passengers  
- reservations  

---

### 🛠 Technologies

- **Backend:** Java 21, Spring Boot, Gradle  
- **Frontend:** React (Vite + TypeScript)  
- **Containerization:** Docker + Docker Compose  
- **Build system:** Gradle + npm  

---

### ✅ Features

- Add, edit and delete flights  
- Add, edit and delete passengers  
- Create reservations with seat availability validation  
- Full CRUD functionality  
- Email confirmation after reservation (logic included)  
- Complete backend and frontend apps

---

## 🚀 How to Run the Application Locally (Docker)

### 📦 Requirements:
- Docker
- Docker Compose

🧰 **Step-by-step:**

1. Navigate to the root directory of the project
2. Start the application:

```bash
docker-compose up --build

```

---

#### 🌐 Local URLs:

- **Frontend (UI):** http://localhost:3000  
- **Backend (API):** http://localhost:8080

---

#### 🧪 Unit Tests

Backend unit tests can be run with the following command:

```bash
cd backend/backend
./gradlew test
```

The results will be displayed in the console.

---

#### 📤 Example test data (POST JSON)

**Flight:**

```json
{
  "flightNumber": "LO124",
  "placeOfDeparture": "Warsaw",
  "placeOfArrival": "Moscow",
  "flightDuration": "PT2H30M",
  "oneWayFlight": true,
  "availableSeats": ["1A", "1B", "2A"]
}
```

**Passenger:**

```json
{
  "firstName": "Kuba",
  "lastName": "Sajnóg",
  "email": "jakubsajnog@gmail.com",
  "phoneNumber": "603337347",
  "passengerId": 1
}
```

**Reservation:**

```json
{
  "reservationNumber": 1001,
  "seatNumber": "1A",
  "alreadyDeparted": false,
  "passengerId": 1,
  "flightNumber": "LO124"
}
```

---

#### 🗨️ Contact

If you have any questions, feel free to reach out.  
I will be happy to provide more information.
