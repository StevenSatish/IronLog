package com.example.ironlog
val Bench_Press = OldExercise(
    "Bench Press", "Chest Pec", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val Shoulder_Press = OldExercise(
    "Shoulder Press", "Shoulder Delt", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val Dip = OldExercise(
    "Dip", "Chest Pec Tricep", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val Squat = OldExercise(
    "Squat", "Legs Quads Glutes", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val Deadlift = OldExercise(
    "Deadlift", "Legs Back Hamstrings Glutes", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val Barbell_Row = OldExercise(
    "Barbell Row", "Back Lats", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val Pull_Up = OldExercise(
    "Pull Up", "Back Lats", listOf(
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
        ExerciseSet(100, 10),
    )
)
val workout_Pull = Workout("Pull", listOf(Pull_Up, Barbell_Row))
val workout_Legs = Workout("Legs", listOf(Squat, Deadlift))
val workout_Push = Workout("Push", listOf(Bench_Press, Shoulder_Press, Dip))
val sample = Folder("Sample Templates", listOf(workout_Push, workout_Pull, workout_Legs))
