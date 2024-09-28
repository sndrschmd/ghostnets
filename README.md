# Ghostnets - Web-Anwendung zur Meldung und Bergung von Geisternetzen

**Ghostnets** ist eine Java-basierte Web-Anwendung, die entwickelt wurde, um die Meldung und Bergung von Geisternetzen zu unterstützen. Geisternetze sind herrenlose Fischernetze, die im Ozean treiben und eine Bedrohung für die Meeresumwelt darstellen. Diese Anwendung ermöglicht es Nutzern, Geisternetze zu melden, sich zur Bergung anzumelden und den Status der Geisternetze zu aktualisieren.

## Features
- **Geisternetz melden**: Benutzer können Geisternetze erfassen, indem sie Durchmesser, GPS-Koordinaten und optionale Kontaktdaten eingeben.
- **Bergungsstatus**: Zeigt eine Übersicht der gemeldeten Geisternetze an, mit der Möglichkeit, diese als „geborgen“ oder „verschollen“ zu melden.
- **Zusammenarbeit**: Ermöglicht es Nutzern, sich für die Bergung eines Geisternetzes einzutragen und mit anderen Helfern zu kommunizieren.
- **Benutzeroberfläche**: Erstellt mit PrimeFaces, um eine interaktive und responsive Oberfläche zu bieten.

## Technischer Stack
- **Backend**: Java (Jakarta EE)
- **Frontend**: JSF mit PrimeFaces-Komponenten
- **Datenbank**: MySQL via JPA/Hibernate
- **Build-Management**: Maven

## Setup-Anleitung

### Voraussetzungen
- **Java 11**
- **Maven**
- **XAMPP mit MySQL**

### Schritte zur Ausführung der Anwendung

1. **MySQL-Datenbank einrichten**:
   - Starte XAMPP und aktiviere MySQL.
   - Importiere die SQL-Datei (`shea.sepherd.sql`), um die Datenbank zu erstellen:
     ```bash
     mysql -u root -p shea_sepherd < path/to/shea.sepherd.sql
     ```

2. **Projekt in der IDE einrichten**:
   - Öffne das Projekt in deiner bevorzugten IDE (z. B. Eclipse).
   - Stelle sicher, dass Maven alle Abhängigkeiten aus der `pom.xml` installiert.

3. **Server konfigurieren**:
   - Stelle sicher, dass ein Java EE-kompatibler Server wie Tomcat EE o. Ä. installiert und in der IDE konfiguriert ist.

4. **Anwendung ausführen**:
   - Starte den Server und deploye die Anwendung als WAR-Datei oder über deine IDE.
   - Öffne den Browser und gehe zu: `http://localhost:8080/Ghostnets`

## Wichtige Dateien

- **`pom.xml`**: Enthält alle notwendigen Abhängigkeiten (z. B. Jakarta EE, PrimeFaces, Hibernate).
- **`persistence.xml`**: Konfiguriert die JPA-Einstellungen und die Verbindung zur MySQL-Datenbank.
- **Frontend-Dateien**: Die wichtigsten XHTML-Dateien für das UI:
  - `index.xhtml` (Home)
  - `netzmeldung.xhtml` (Geisternetz melden)
  - `datenbank.xhtml` (Bergungsstatus)
  - `unterstuetzer.xhtml` (Gemeinsam helfen)

## Weiterentwicklungsmöglichkeiten
- Integration einer Weltkarte, um die Standorte der gemeldeten Geisternetze anzuzeigen.
- Erweiterung zu einer mobilen App für einfacheres Melden von Geisternetzen unterwegs.
- Ein Login-System für erweiterte Sicherheit und Datenschutz bei personenbezogenen Daten.
