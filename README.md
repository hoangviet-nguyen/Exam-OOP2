# Midterm-Prüfung 1, OOPI2 FS 2023

## 1. Sortieren und Vergleichen (15 Punkte)

Im Package `modules` finden Sie eine fertige Klasse `Module` und eine Klasse `ModuleSystem` mit einer Methode `sort`, welche noch keine Implementation hat. Implementieren Sie die Methode so, dass die Module im System nach folgenden Kriterien sortiert werden:
* Alphabetisch nach Modulgruppe (`moduleGroup`)
* Falls die Modulgruppe zweier Module gleich sind, absteigend nach Anzahl Kreditpunkte (`ects`)
* Falls Modulgruppe und Kreditpunkte gleich sind, alphabetisch nach Name (`name`)

**Zusatzanforderung:** Für diese Aufgabe müssen Sie einen Lambda-Ausdruck verwenden, um die volle Punktzahl zu erreichen. Lösungen ohne Lambda-Ausdruck erhalten höchstens 10 Punkte.


## 2. Modulanlässe einlesen (25 Punkte)

In `modules` finden Sie zusätzlich eine fertige Klasse `ModuleEvent` und eine Klasse `ModuleEventReader` mit einer Methode `read`. Diese soll Modulanlässe in einem TSV-Format einlesen. Eine Datei in diesem Format könnte beispielsweise so aussehen:

```
Name →   ECTS → Module group →   Module class → Semester
oopI2 →  3 →    Programmierung → Ibb1 →         2
pro2I →  6 →    Projekte →       Ia →           2
apm →    3 →    Vertiefungen →   Iw →           6
```

Beachten Sie die Header-Zeile, welche immer am Anfang einer Datei steht. Die Pfeile (und die Leerzeichen unmittelbar links und rechts davon) stehen jeweils für ein Tab-Zeichen (`\t` in Java-Code).

Implementieren Sie die `read`-Methode so, dass sie aus dem gegebenen `InputStream` die Modulanlassdaten einliest und als Liste von `ModuleEvent`-Objekten zurückgibt. Stellen Sie sicher, dass Sie als Zeichencodierung UTF-8 verwenden und den `InputStream` am Ende garantiert schliessen. Zudem sollen alle Probleme, die beim Einlesen auftreten können, als `IOException` an den Client-Code gemeldet werden; es dürfen keine Exceptions von anderen Typen geworfen werden.


## 3. `MinMaxReporter` (20 Punkte)

Ein `MinMaxReporter` ist ein Objekt, welches von einer Reihe von Elementen das «kleinste» und das «grösste» speichert. Mittels einer `add`-Methode kann man weitere Elemente hinzufügen; der Reporter speichert aber immer nur das kleinste und das grösste bisher gesehene Element. Das Spezielle an `MinMaxReporter` ist, dass man Event-Handlers registrieren kann, welche bei jeder Änderung der kleinsten bzw. grössten Elements aufgerufen werden.

Ein `MinMaxReporter` könnte beispielsweise so verwendet werden:

```java
Comparator<Integer> comparator = Integer::compare;
var reporter = new MinMaxReporter<Integer>(comparator);
reporter.setOnMinChanged((oldMin, newMin) -> {
    System.out.println("Min changed: " + oldMin + " -> " + newMin);
});
reporter.setOnMaxChanged((oldMax, newMax) -> {
    System.out.println("Max changed: " + oldMax + " -> " + newMax);
});

reporter.add(4);
reporter.add(10);
reporter.add(2);
reporter.add(6);
```

Dieser Code sollte folgende Ausgabe produzieren:

```
Min changed: null -> 4
Max changed: null -> 4
Max changed: 4 -> 10
Min changed: 4 -> 2
```

Implementieren Sie die Klasse `MinMaxReporter` im Package `minmax`. Es ist bereits eine minimale Hülle vorgegeben. Beachten Sie, dass man dem Konstruktor einen `Comparator` übergeben muss, der für die Vergleiche der Elemente verwendet wird; der `MinMaxReporter` soll also für beliebige Typen von Elementen (nicht nur Zahlen) funktionieren.

**Zusatzanforderung:** Um die volle Punktzahl zu erhalten, verwenden Sie für die Event-Handlers vordefinierte [Funktionsinterfaces aus der Java-Standardbibliothek](functional-interfaces.md). Lösungen mit eigenen Interfaces erhalten höchstens 15 Punkte.
