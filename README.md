# Fil Rouge:

Ce projet a été concu par : 
- Durand Enzo : 21510242
- Leconte Thomas : 22008087
- Robert Adrien : 21701370
- Lepage Dylan : 21804570

## Notice d'utilisation ?

### Compiler le projet :
    - Linux : javac -cp "src/tests/dataminingtests.jar:src/tests/solvertests.jar:src/tests/planningtests.jar:src/tests/representationtests.jar" -d build/ src/*/*.java
    - Windows : javac -cp "src/tests/dataminingtests.jar;src/tests/planningtests.jar;src/tests/solvertests.jar;src/tests/representationtests.jar" -d build src/*/*.java

### Executer le project :
    - Linux / Windows : java -cp build/ examples.HouseDemo

## Comment les packages `representation`, `solvers`, `planning`, `datamining` fonctionnent :
Ces 4 packages forment une librarie permettant de faire de la programmation par contrainte. La partie representation permet de créer les variables du problème (avec la classe Variable et BooleanVariable), et les contraintes (interface Constraint). L'interface Constraint implémente 3 classes :
- Rule : ce type de contrainte permet dans notre exemple de spécifier directement un lien entre deux BooleanVariable. Par exemple elle nous permet de dire "SI la toiture est terminée ALORS les murs sont terminés".
- DifferenceConstraint : ce type de contrainte nous permet ici de faire en sorte que chaque pièce de la maison ait un élement du domaine différent.
- BinaryExtensionConstraint : nous n'avons pas réussi à implémenter celle-ci dans l'exemple. Le principe de cette contrainte est de créer un lien entre deux objets Variable,
pour ensuite spécifier explicitement les couples autorisés de domaine entre deux variables.

Dans le package solvers nous avons mis à disposition plusieurs types de solveurs dont un algorithme de backtrack, ou MAC. Ce package permet de choisir un élément du domaine pour chaque variable du problème suivant la liste de variables et de contraintes créées.

Le package planning nous permet de récupérer, parmi toutes les actions possibles d'un problème résolu, la meilleure suite d'actions pour arriver à un but. Nous avons plusieurs algorithmes nous permettant de trouver la meilleure suite d'actions dont Dijkstra, A*, DFS, BFS.

Le package datamining permet, grâce à une liste de solutions, d'extraire des motifs qui se répètent, suivant leurs fréquences.

## Comment le package `example` fonctionne :
>Tout commence sur la classe **HouseDemo**. On va d'abord demander à l'utilisateur de renseigner les valeurs qu'il souhaite pour son
futur plan de maison. Ensuite, on va construire toutes les contraintes de la maison grâce à la méthode `houseRepresentation.>makeAllConstraint()`. Le solver choisi va donc commencer son travail de résolution grâce aux variables et aux contraintes créées précédemment. Une fois les résultats affichés, c'est le package Planner qui travaille (un peu trop longtemps des fois) pour trouver le plan correspondant à la solution trouvée par solver, et enfin le package Datamining stocke et affiche les règles trouvées.

## Comment le package `tests` fonctionne :
>Nous avons une classe éxécutable dans le package tests, il fait appel à toutes les méthodes de tests de la librairie. Si toutes les fonctions testées sont correctes alors "All tests passed" s'affiche. Cependant les tests fonctionnent comme ceci : quelques inputs dont on connait les outputs sont envoyés dans la méthode testée. On ne teste que des valeurs "spéciales" comme un tableau vide par exemple. Il est donc possible que les tests passent alors qu'il y a une erreur de fonctionnement d'une méthode pour certains cas.

## Autres détails :
>Le package examples contient la démonstration de tous les packages développés dans la librairie. Ainsi, il y a une classe de démonstration pour chaque package développé (HouseRepresentation->package représentation, etc ..). Notre classe Main est donc
**examples.HouseDemo**.

- Pour exécuter rapidement le programme, sans passer par le renseignement de valeurs initiales, il faut commenter la ligne 24 de la
classe principale **HouseDemo** : `inputParameters();`.
- Malgré tous nos efforts, et après de nombreuses heures de debuggage passées avec Josselin Guérenon (que l'on remercie encore une fois),
nous n'avons pas réussi à satisfaire la contrainte concernant les pièces d'eau. Le détail doit être minime mais nous ne savons pas qu'est
ce qui pose problème... Nous avons essayé plusieurs facons de faire, nous avons décidé de laisser ces fonctions visibles mais commentées.
Ces essais se trouvent dans le fichier **HouseRepresentation** ligne 187.
- Notre package **solvers** contient une classe `BackTrackSolverMultipleSolution`. Elle reprend le même code que `BacktrackSolver` mais
elle stocke les résultats trouvés par le solver pour les utiliser plus tard dans le package **examples**.
- Concernant l'affichage des différentes parties de notre démonstration, nous avons @override plusieurs fonctions `toString()`, notamment dans la classe **Variable** et **Action**.
- Lors de l'execution de l'exemple **HouseDemo**, le Planner met beaucoup de temps à s'éxecuter à partir de 6 pièces.
