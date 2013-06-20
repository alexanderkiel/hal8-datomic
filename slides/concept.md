# Title

Datomic - A Functional Database

Alexander Kiel
alexanderkiel@gmx.net

Work: University Leipzig

# Motivation

* Workshop ueber Haskell und funktionale Programmierung - Warum ein Vortrag
  ueber eine Datenbank?
* Datenbanken wichtiger Aspekt in meisten Real-World Softwareprojekten.
* Funktionale Konzepte wie Pure Functions und Immutable Datastructures haben
  sich auf der algorithmischen Seite von Softwareprojekten bewaehrt. Wie
  sieht es auf der Datenbankeseite aus? Gibt es neue Bewegungen? Wo ist die
  funktionale Datenbank?

# Datomic Referenz

* Datomic ist ein kommerzielles Datenbankprodukt vom Erfinder von Clojure Rich
  Hickey in Zusammenarbeit mit der Firma Relevance Inc.
* Clojure ist eine funktionale Programmiersprache fuer die JVM auf die ich
  nicht im Speziellen eingehen moechte.
* Die Entwicklung startete in 2010 und die erste Free Edition war im Juli 2012
  verfuegbar.
* Entwicklung sehr aktiv mit mit ca. 4 neuen Versionen pro Monat.
* Free Edition fuer kleinere bis mittlere Projekte ausreichend. Keine
  Einschraenkung des Funktionsumfanges nur der Skalierbarkeit.
* Bin selbst aktiv in der Datomic Google Group, habe aber ansonsten keine
  weitere Verbindung mit den Entwicklern.

# Datenbank Landschaft - Einordnung Datomic

* Zwei Extreme in der Datenbanklandschaft.
* Viele Loesungen dazwischen.
* Datomic bringt ACID, Queries mit Joins aus der relationalen Welt mit
  horizontaler Skalierbarkeit fuer lesende Transaktionen zusammen.
* Relationale Datenbanken
  * ACID
  * Schema
  * Queries (Joins)
  * nicht horizontal skalierbar
* Key-Value Stores
  * Nur single Key Transaktionen
  * kein Schema
  * keine Queries
  * horizontal skalierbar

# Probleme Relationaler Datenbanken die Datomic loesen moechte

* Update In-Place
  * In relationalen Datenbanken werden Update Operationen In-Place ausgefuehrt.
  * Aehnlich wie bei mutable Arrays wird eine Zelle in einer Tabelle mit einem
    neuen Wert ueberschrieben.
  * Daraus resultieren mehrere Probleme:
    1. Jegliche Historie geht verlohren.
    2. Schreib- und Leseoperationen muessen koordiniert werden.
  * Motivation fuer in-place updates waren die hohen kosten fuer storage und
    Arbeitsspeicher zu der Zeit als RDMS entwickelt wurden.
  * Genau wie bei Persistent Datastructures muss man hier zwischen Resourcen
    und Funktionalitaet abwegen.
  * Die meisten Informationssysteme der pre-computer Area funktionierten
    nicht nach dem Update-In-Place Prinzip. Papierbasierte Workflows sammeln
    Akten ueber Entitaeten an, zu denen neue Informationen hinzugefuegt aber
    alte Informationen nicht geloescht werden. Selbst wenn etwas auf einem
    Blatt Papier geaendert wird, wird der alte Wert durchgestrichen und der
    neue Wert daneben geschrieben.
  * Erst mit Computern mit Ihren kleinen, teuren Speichern hat das grosse
    Vergessen um sich gegriffen.
  * Oft werden heutzutage Audit-Logs angelegt, die sehr aufwaendig zu
    implementieren und kaum abfragbar sind.
