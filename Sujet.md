# Projet Pokemon TCG API

Le but de projet est de développer une API permettant de collection des cartes pokemon, de les échanger, de faire des combats entre dresseurs.

## Règles métiers

Chaque jour, un utilisateur peut tirer un lot de 5 cartes générées aléatoirement.

Une carte représente un pokémon, avec 2 attaques et dispose d'une rareté allant de 1 à 5 étoiles.
La rareté de la carte est générée aléatoirement, et il est plus rare de tirer une carte 5 étoiles qu'une "normale". Une carte pikachu 1 étoile est différente d'une carte pikachu 5 étoiles.

Deux dresseurs ne peuvent échanger de cartes entre eux qu'une seule fois par jour.

Un dresseur a un paquet de 5 cartes principales, qu'il utilise pour les combats. Les autres cartes sont dans un paquet secondaire. Il peut échanger des cartes entre ses deux paquets à tout moment.
Un dresseur peut en défier un autre, le gagnant du combat gagne la meilleure carte du perdant (du paquet de combat).

Il faut être authentifié pour accéder aux routes de l'api.

On peut accéder à l'historique de tous les échanges, mais aussi à l'historique des échanges de chaque dresseur. L'historique doit être filtrable sur le mois, le jour ou l'année.

## Notation

| Attente                                                                                                                                    | Points |
|--------------------------------------------------------------------------------------------------------------------------------------------|--------|
| L'API fonctionne                                                                                                                           | 4      |
| Les routes sont bien nommées                                                                                                               | 2      | 
| Les méthodes HTTP et status HTTP sont correctement utilisés                                                                                | 4      | 
| Toutes les entités sont accessibles, créables (à l'éxception d'une), modifiable (à l'exception d'une) et supprimable (à l'exception d'une) | 4      | 
| Les entités ne sont pas manipulées dans les requêtes                                                                                       | 2      | 
| Des règles de validation sont appliquées                                                                                                   | 2      |
| Apport personnel au projet                                                                                                                 | 2      | 

Projet à rendre via dépot git par groupe de 2 (un seul mail par dépôt).

Le dépôt doit être envoyé par mail à l'adresse suivante : kelig.martin@intervenants.efrei.net
Titre du mail : [Nom - Prénom] des deux élèves du groupe.

La note de zéro sera attribuée en cas de triche, de non rendu, ou de rendu en retard du projet.
