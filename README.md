Family Feud
===========

Family Feud version 1.0	by Jon Paul, 2014

Disclaimer: 
This software is provided as-is, and does not come with any warranty.  Its author is not responsible for any data loss or damages incurred through the use of this software.  This program is freeware, and may not be distributed for any commercial purpose.  

The sound libraries were written by Barbara Ericson (ericson@cc.gatech.edu).  GUI development was templated using WindowBuilder.  Family Feud is written in Java 6, using Eclipse, and has been tested for use in OS X and Windows.  Special thanks to Dr. Leo Porter for his advisory during the development and deployment of this project.

Startup Guide:
Launch the game using the included JAR file in 'Family Feud.zip'.  Family Feud operates using "Question Packs" or "QPacks" to extract question, answer, and points data.  The file 'questions.txt' is loaded at run-time, and is automatically generated if not already present.  Question Packs are written and read as plain text, and can be modified either manually, or using the included interface by selected "New". 

Gameplay supports two teams.  When playing, questions may be selected one by one, with points tallied between selections.  In the event that either: Fast Money mode is selected; a team has earned 300 or more points; or only five questions remain, Fast Money mode is activated.  If Fast Money mode is activated before any questions were played, Team 1 will be selected to play.

Once the first question is in play, two windows will be generated: the Play Window - to be shown to the players - and the Administrator Window - which should be viewable to the proctor only.  The Admininstrator Window contains a button for each possible answer, and six additional buttons: Strike, Reveal All, Next Question, Team 1 toggle, Team 2 toggle, and End Game.  Toggling between teams does not affect the strike count, and should only be used when the incorrect team has been selected; the opposing team is automatically toggled in the event that three strikes are incurred.  When the next question is selected, the points in the jackpot are awarded to the team that was last toggled.

During the Fast Money phase, questions are automatically loaded and unloaded after any answer - or a strike - is input.  Answers are revealed after each player has selected five answers.  If the combined total meets or exceeds 200 points, the team wins.

Contact:
	Email: jsimonel@skidmore.edu
