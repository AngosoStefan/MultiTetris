Angoso Stefan
Guidis Antoine

## FEATURES

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
The have a better experience, set the Font to : FreeMono 12
The 'screen' MUST be composed of a 25 row by 80 column matrix of character locations.
Manually check it into your shell settings.
Without this configuration the gotoxy() goes wrong.

Possibility modify this configuration by hand currently

* DO NOT COMPILE INTO EMBEDDED IDE CONSOLE

```
git clone git@github.com:AngosoStefan/MultiTetris.git
cd MultiTetris
mvn clean install
cd src/main
./Tetris
```

* Play

Play with  z q d key and press p for exit current game to return to menu 

## SERVER DEV MODE

Not working.
But you should be in MultiTetris/src/main
./GameServer
./Tetris
./Tetris
And start MultiPlayer Game on both "./Tetris"
Correct implementation of ArrayBlockingQueue<NetworkMessage>

## Architecture

MVC

## Design Pattern / SOLID

Chacunes classes ont leurs propres responsabilitées.
