Angoso Stefan
Guidis Antoine

## FEATURES

- En Console & Mode RAW :white_check_mark:
- Menu  :white_check_mark:
- Solo mode  :white_check_mark:
- Special character  :white_check_mark:
- Time  :white_check_mark:
- Rotation  :white_check_mark:
- Client - Server communication  :white_check_mark:
- Implementation into Multi mode :red_circle:

## CLIENT DEV MODE

* SET SHELL SCREEN RESOLUTION 

Right click > Profile > Profile Preference
To have a better experience, set the Font to : FreeMono 12
The 'screen' MUST be composed of a 25 rows by 80 columns matrix of character locations.
Manually check it into your shell settings.
Without this configuration the gotoxy() goes wrong.

Possibility to modify this configuration by hand

* DO NOT COMPILE INTO EMBEDDED IDE CONSOLE

```
git clone git@github.com:AngosoStefan/MultiTetris.git
cd MultiTetris
mvn clean install
cd src/main
./Tetris
```

* Gameplay

Play with  z q d key and press p for exit current game to return to menu 

## SERVER DEV MODE

Not working. But you should be in MultiTetris/src/main
```
./GameServer
./Tetris
./Tetris
```
and start MultiPlayer Game on both "./Tetris"
Correct implementation of ArrayBlockingQueue<NetworkMessage>

## Architecture

MVC
Controlleur :
Tetris, menu controlleur global, est responsable du déclenchement du jeu

## Design Pattern / SOLID

Chacune des classes ont leurs propres responsabilités.
Utilisation d'abstraction, héritage :
Game class mère de SoloGame et MultiGame

Le jeu en lui même doit implémenter certains attributs et certaines méthodes.
Dès lors que des ajouts sont nécessaires c'est aux classes filles de les implémenter.

Pattern Servant sur la Vue Console permettant de gérer en interne le visuel (ajout/supression/clean/afficher) utilisé dans les controlleurs.
Pattern Façade sur le Controlleur Tetris, s'occupe du Menu et du lancement des Threads responsables du Multi/Solo mode
On aurait pu utiliser un design Pattern MalusFactory renvoyant directement des instances de NetworkMessage avec les bonnes caractéristiques.

Area -> Gestion de la matrice invisible permettant de faire fonctionner les règles du jeu
Piece -> Gestion des pièces


## Network - Non fonctionnel

Voici le principe du fonctionnement du réseau :
https://drive.google.com/open?id=0B4XkmD7mPURnbkYxYlI2RFlRTzQ

On trouve les packages network.gameclient et network.gameserver pour la gestions des multiples clients et de l'unique server.
Chaque client a un thread d'écoute et un thread d'écriture, de même pour le serveur.

Le package communication contient les interfaces pour les threads que nous venons de présenter, ainsi que des méthodes de fermeture des flux.

Le package messages contient les types de messages qui sont échangés : NetworkMessage.
D'après notre protocole, on enverra (nom du joueur, code général, sous code).
Par exemple, (J1,malus,slow) ou (J2,game,win).
