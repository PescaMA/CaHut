# CaHut

Proiectul implementeaza un quiz in consola unde poti sa te loghezi, sa rezolvi teste, profesorii putand vedea rezultatele.

## Definirea sistemului

### OBIECTE

1. User (clasă de bază) — are id, nume, email.

1. Teacher (moștenire din User) — poate crea cursuri și quizuri.

1. Student (moștenire din User) — poate participa la quizuri și să se înscrie la clase.

1. Course — are id, nume, teacher, listaDeQuizuri.

1. Quiz — are id, titlu, dificultate, listaDeIntrebari.

1. Question — id, are intrebarea, timpul alocat, raspunsuri gresite, raspunsuri corecte.

1. Answer — id intrebare + raspuns student.

1. Score — toate raspunsurile, timpul la fiecare, scorul final.

1. Leaderboard — pentru un quiz, scoruri sortate.

### ACTIUNI

1. Creare nou utilizator (profesor sau cursant).

1. Creare nou curs.

1. Înscriere cursant la un curs.

1. Adaugare întrebări și răspunsuri multiple într-un quiz.

1. Adaugare quiz la un curs.

1. Începere un quiz pentru un cursant.

1. Evaluare răspunsuri cursant la un quiz.

1. Afișare scoruri obținute de un cursant la quizurile susținute.

1. Afișare toți cursanții înscriși la un curs.

1. Afișare leaderboard pentru un quiz.



