# CaHut

Proiectul implementeaza un quiz in consola unde poti sa te loghezi, sa rezolvi teste, profesorii putand vedea rezultatele.

## Definirea sistemului

### OBIECTE

1. User (clasă de bază) — se poate loga, are username, parola.

1. Student (moștenire din User) — poate participa la quizuri.

1. Teacher (moștenire din User) — poate crea cursuri și quizuri. poate adauga studenti.

1. Course — cursurile la care sunt inscrisi studentii.

1. Question — id, are intrebarea, timpul alocat, raspunsuri gresite, raspunsuri corecte.

1. Answer — id intrebare + raspuns student.

1. Quiz — are id, titlu, lista de intrebari.

1. TimerQuiz — face un nou thread pentru a forta un anumit timp total.

<!-- 1. Score — toate raspunsurile, timpul la fiecare, scorul final.

1. Leaderboard — pentru un quiz, scoruri sortate. -->

### ACTIUNI

1. Creare nou utilizator (profesor sau student).

1. Creare nou curs.

1. Crearea unui quiz.

1. Logare pe baza de parola.

1. Înscriere student la un curs.

1. Adaugare întrebări și răspunsuri multiple într-un quiz.

1. Adaugare quiz la un curs.

1. Începere un quiz pentru un cursant.

1. Afișare toți cursanții înscriși la un curs.

1. Afisare toate cursurile.

<!-- 1. Afișare scoruri obținute de un cursant la quizurile susținute.

1. Afișare leaderboard pentru un quiz. -->



