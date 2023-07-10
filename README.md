# tictactoe

```
        ,----,                                ,----,                                    ,----,                       
      ,/   .`|                              ,/   .`|                                  ,/   .`|                       
    ,`   .'  :                            ,`   .'  :                                ,`   .'  :                       
  ;    ;     /   ,--,                   ;    ;     /                              ;    ;     /                       
.'___,/    ,'  ,--.'|                 .'___,/    ,'                             .'___,/    ,'     ,---.              
|    :     |   |  |,                  |    :     |                              |    :     |     '   ,'\             
;    |.';  ;   `--'_        ,---.     ;    |.';  ;      ,--.--.       ,---.     ;    |.';  ;    /   /   |    ,---.   
`----'  |  |   ,' ,'|      /     \    `----'  |  |     /       \     /     \    `----'  |  |   .   ; ,. :   /     \  
    '   :  ;   '  | |     /    / '        '   :  ;    .--.  .-. |   /    / '        '   :  ;   '   | |: :  /    /  | 
    |   |  '   |  | :    .    ' /         |   |  '     \__\/: . .  .    ' /         |   |  '   '   | .; : .    ' / | 
    '   :  |   '  : |__  '   ; :__        '   :  |     ," .--.; |  '   ; :__        '   :  |   |   :    | '   ;   /| 
    ;   |.'    |  | '.'| '   | '.'|       ;   |.'     /  /  ,.  |  '   | '.'|       ;   |.'     \   \  /  '   |  / | 
    '---'      ;  :    ; |   :    :       '---'      ;  :   .'   \ |   :    :       '---'        `----'   |   :    | 
               |  ,   /   \   \  /                   |  ,     .-./  \   \  /                               \   \  /  
                ---`-'     `----'                     `--`---'       `----'                                 `----'   
                                                                                                                     


```


### Description

A project to simulate a game of tic tac toe. Written in Java. 

- Supports boards of from 3x3 to 5x5
- Supports two players only
- Currently only calculates victory based on 3 vertical crosses in a row
- Takes json input in the following structure to simulate a game (where players moves are represented by x:y coordinates)
```
{
  "boardXAxis": "3",
  "boardYAxis": "3",
  "playerOneName": "Bilal Pandor",
  "playerTwoName": "Deustche Bank",
  "playerOneMoves": [
    "1:1",
    "1:2",
    "1:3"
  ],
  "playerTwoMoves": [
    "2:1",
    "3:3",
    "3:1"
  ]
}
```

