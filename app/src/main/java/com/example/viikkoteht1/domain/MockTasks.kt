package com.example.viikkoteht1.domain

import java.time.LocalDate

val mockTasks = listOf(
    Task(1, "Fysiikan perusteet tietotekniikassa", "SI-järjestelmä", 5, LocalDate.of(2026, 1, 9), false),
    Task(2, "Mobiiliohjelmointi natiiviteknologioilla", "Kotlin-perusteet", 5, LocalDate.of(2026, 1, 15), false),
    Task(3, "Web- ja hybriditeknologiat mobiiliohjelmoinnissa", "Tavoitesyke", 5, LocalDate.of(2026, 1, 14), true),
    Task(4, "Fysiikan perusteet tietotekniikassa", "Kinematiikka", 5, LocalDate.of(2026, 1, 14), false),
    Task(5, "Mobiiliohjelmointi natiiviteknologioilla", "Käyttöliittymä", 5, LocalDate.of(2026, 1, 21), false),
    Task(6, "Web- ja hybriditeknologiat mobiiliohjelmoinnissa", "Modal", 5, LocalDate.of(2026, 1, 21), false)
)