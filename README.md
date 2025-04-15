# CaHut

Proiectul implementeaza un quiz in consola unde poti sa te loghezi, sa rezolvi teste, profesorii putand vedea rezultatele.

## Definirea sistemului

### OBIECTE

1. User (clasă de bază) — are id, nume, email.
1. Teacher (moștenire din User) — poate crea cursuri și quizuri.
1. Student (moștenire din User) — poate participa la quizuri.
1. Course — are id, nume, teacher, listaDeQuizuri.
1. Quiz — are id, titlu, dificultate, listaDeIntrebari.
1. Question — id, are intrebarea, timpul alocat, raspunsuri gresite, raspunsuri corecte.
1. Answer — id intrebare + raspuns student.
1. Score — toate raspunsurile, timpul la fiecare, scorul final.
1. Leaderboard — pentru un quiz, scoruri sortate.

### ACTIUNI




