# Projekt3_Gruppe_3 – Bilabonnement

Internt webbaseret administrationssystem til Bilabonnement A/S. Systemet understøtter tre brugergrupper: dataregistrering, skade og udbedring samt forretningsudviklere.

Teknologi: Java 21, Spring Boot, Thymeleaf, JDBC og MySQL.

## Forudsætninger

- MySQL Server 8 eller nyere, der kører på `localhost:3306`
- MySQL Workbench (eller en anden SQL-klient, fx DBeaver)
- IntelliJ IDEA med Java 21

## 1. Gendan databasen

Begge scripts ligger i `src/main/resources/` og skal køres **som root**, i denne rækkefølge:

1. **`database.SQL`** (DDL): opretter databasen `projekt3_gruppe_3_db` med tabeller. Scriptet sørger også for, at brugeren `F26A` (password `1234`) har adgang til databasen. En eksisterende F26A-bruger bliver ikke slettet eller ændret.
2. **`dml.sql`** (DML): indsætter testdata (brugere, biler, kunder, lejeaftaler, skadesrapporter og skader).

I MySQL Workbench: log ind som root, åbn scriptet via *File → Open SQL Script*, og kør hele scriptet med lyn-ikonet (*Execute*).

Advarsel: `database.SQL` starter med `DROP DATABASE IF EXISTS projekt3_gruppe_3_db`. Vil du nulstille data, så kør begge scripts igen i samme rækkefølge.

## 2. Start applikationen

1. Klon projektet fra GitHub, og åbn mappen i IntelliJ.
2. Vent, til Maven har hentet afhængighederne.
3. Kør `Projekt3Gruppe3Application` (`src/main/java/dk/kea/projekt3_gruppe_3/`).
4. Åbn http://localhost:8080 i browseren.

Databaseforbindelsen er sat op i `src/main/resources/application.properties` (bruger `F26A`, password `1234`). Der anvendes ikke miljøvariabler.

## 3. Log ind

| Brugernavn | Password | Rolle |
|---|---|---|
| `demo` | `demo` | Forretningsudvikler |
| `datareg` | `1234` | Dataregistrering |
| `skade` | `1234` | Skade og udbedring |
| `forretning` | `1234` | Forretningsudvikler |

## Sider

| Adresse | Indhold |
|---|---|
| `/dashboard` | Forretningsudviklernes forside med bilbestanden fordelt på status |

## Fejlfinding

- **`Unknown database 'projekt3_gruppe_3_db'`**: `database.SQL` er ikke kørt.
- **`SELECT command denied to user 'F26A'@'localhost'`**: F26A mangler adgang til databasen. Kør `database.SQL` igen som root.
- **`Access denied for user 'F26A'`**: F26A findes med et andet password end `1234`.
