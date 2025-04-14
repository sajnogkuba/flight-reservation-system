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

### 🚀 Jak uruchomić aplikację lokalnie (Docker)

#### 📦 Wymagania:

- Docker + Docker Compose
- Java 21 (dla kompilacji backendu)
- Node.js + npm (dla kompilacji frontendu)

---

#### 🧰 Krok po kroku:

```bash
# 1. Zbuduj backend (.jar)
cd backend/backend
./gradlew build

# 2. Zbuduj frontend (Vite)
cd ../../frontend/frontend
npm install
npm run build

# 3. Wróć do katalogu głównego projektu
cd ../../

# 4. Uruchom cały system przez Docker Compose
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

### 🚀 How to run the app locally (Docker)

#### 📦 Requirements:

- Docker + Docker Compose  
- Java 21 (for building the backend)  
- Node.js + npm (for building the frontend)

---

#### 🧰 Step-by-step:

```bash
# 1. Build backend (.jar)
cd backend/backend
./gradlew build

# 2. Build frontend (Vite)
cd ../../frontend/frontend
npm install
npm run build

# 3. Go back to project root
cd ../../

# 4. Start the whole system using Docker Compose
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
