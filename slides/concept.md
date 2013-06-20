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

# Welche Probleme moechte Datomic loesen?

* Fehlendes Zeitkonzept
* Update-In-Place
* Monolitische Architektur

# Fehlendes Zeitkonzept

* Typischerweise haben Datenbanken kein eingebautes Konzept der Zeit.
* Es gibt immer nur den Jetztzustand.
* Daten werden einfach geaendert ohne dass man nachvollziehen kann, was vom wem
  wann geaendert wurde und wie der Zustand vor einer Aenderung war.
* Oft werden heutzutage Audit-Logs angelegt, die sehr aufwaendig zu
  implementieren und kaum abfragbar sind.
* Eine Zeitbasis (Snapshots) fehlt. Lang laufende Queries wie Reports sind schwer
  umzusetzen. Wiederholbare Queries sind nicht moeglich.

# Update In-Place

* In relationalen Datenbanken werden Update Operationen In-Place ausgefuehrt.
* Aehnlich wie bei mutable Arrays wird eine Zelle in einer Tabelle mit einem
  neuen Wert ueberschrieben.
* Dadurch muessen Schreib- und Leseoperationen koordiniert werden.
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

# Monolitische Architektur

* Typische relationale Datenbankserver Architekturen bestehen aus einem
  zentralen Server, der die drei Funktionen Transaktionsmanager, Storage und
  Queryengine umfasst.
* Datomic trennt diese drei funktionalen Bereiche auf und verteilt diese in
  unterschiedliche Processe.
* Die Queryengine wandert in den Client. Die Storage wird an einen Storage-
  service ausgelagert. Der Transaktionsmanager bleibt als einzige zentrale
  Komponente erhalten.
* Diese Trennung ist nur moeglich, weil Update-In-Place nicht mehr verwendet
  wird.

#
