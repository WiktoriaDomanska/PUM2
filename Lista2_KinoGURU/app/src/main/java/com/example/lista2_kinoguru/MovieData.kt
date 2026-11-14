package com.example.lista2_kinoguru

val movies = listOf(
    Movie(1, "Barbie", "Greta Gerwig", 2023, "Komedia"),
    Movie(2, "Pamiętnik", "Nick Cassavetes", 2004, "Romans"),
    Movie(3, "Wredne dziewczyny", "Mark Waters", 2004, "Komedia"),
    Movie(4, "Jak stracić chłopaka w 10 dni", "Donald Petrie", 2003, "Komedia romantcyzna"),
    Movie(5, "Legalna blondynka", "Robert Luketic", 2001, "Komedia"),
    Movie(6, "Piraci z karaibów: Klątwa Czarnej Perły", "Gore Verbinski", 2003, "Przygodowy"),
    Movie(7, "Odyseja", "Christopher Nolan", 2026, "Przygodowy"),
    Movie(8, "Diabeł ubiera się u Prady", "David Frankel", 2006, "Komedia"),
    Movie(9, "Pamiętnik księżniczki", "Garry Marshall", 2001, "Komedia"),
    Movie(10, "Kac Vegas", "Todd Phillips", 2009, "Komedia")
)

val descriptions = mapOf(
    1 to "Barbie opuszcza Barbieland i trafia do prawdziwego świata.",
    2 to "Historia miłości Noah i Allie, których dzielą różnice społeczne.",
    3 to "Cady trafia do nowej szkoły i poznaje popularną grupę dziewczyn.",
    4 to "Dziennikarka Andie próbuje zniechęcić do siebie chłopaka w 10 dni.",
    5 to "Elle Woods rozpoczyna studia prawnicze na Harvardzie.",
    6 to "Jack Sparrow próbuje odzyskać swój statek, Czarną Perłę.",
    7 to "Odyseusz po wojnie trojańskiej próbuje wrócić do domu.",
    8 to "Andy rozpoczyna pracę w prestiżowym magazynie modowym.",
    9 to "Mia dowiaduje się, że jest następczynią tronu.",
    10 to "Grupa przyjaciół budzi się po wieczorze kawalerskim i próbuje odnaleźć pana młodego."
)

val reviews = mapOf(
    1 to listOf(
        Review("Anna", "Bardzo zabawny i kolorowy film."),
        Review("Kasia", "Świetna obsada i ciekawa historia.")
    ),
    2 to listOf(
        Review("Ola", "Piękna historia miłosna."),
        Review("Julia", "Bardzo wzruszający film.")
    ),
    3 to listOf(
        Review("Natalia", "Zabawna komedia szkolna."),
        Review("Maja", "Uwielbiam ten film.")
    ),
    4 to listOf(
        Review("Weronika", "Świetna komedia romantyczna."),
        Review("Karolina", "Bardzo zabawne sytuacje.")
    ),
    5 to listOf(
        Review("Zuzia", "Elle Woods jest świetną bohaterką."),
        Review("Magda", "Lekki i motywujący film.")
    ),
    6 to listOf(
        Review("Adam", "Świetna przygoda i dużo humoru."),
        Review("Piotr", "Jack Sparrow jest niezastąpiony.")
    ),
    7 to listOf(
        Review("Michał", "Ciekawa adaptacja mitologicznej historii."),
        Review("Kamil", "Historia Odyseusza zapowiada się interesująco.")
    ),
    8 to listOf(
        Review("Klaudia", "Świetny film o świecie mody."),
        Review("Patrycja", "Bardzo dobra rola Meryl Streep.")
    ),
    9 to listOf(
        Review("Wiktoria", "Uroczy film z dzieciństwa."),
        Review("Emilia", "Bardzo przyjemna historia.")
    ),
    10 to listOf(
        Review("Bartek", "Jedna z moich ulubionych komedii."),
        Review("Tomek", "Mnóstwo zabawnych scen.")
    )
)

val similarMovies = mapOf(
    1 to listOf(3, 5, 9),
    2 to listOf(4, 8, 9),
    3 to listOf(1, 5, 9),
    4 to listOf(2, 5, 9),
    5 to listOf(1, 3, 8),
    6 to listOf(7, 1, 10),
    7 to listOf(6, 2, 10),
    8 to listOf(1, 3, 5),
    9 to listOf(1, 2, 5),
    10 to listOf(3, 4, 6)
)