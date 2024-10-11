# Tennis Scoreboard

![Overview](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/1087e983-d3b1-4aff-ba5f-3dd1834fc9d3)

## Overview

A web application that implements a scoreboard for a tennis match. The idea is taken from [here](https://zhukovsd.github.io/java-backend-learning-course/Projects/TennisScoreboard/)

## Technologies / tools used:
![java-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/bc1ab298-7a78-42ec-8813-05b38668310e)
![hibernate-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/071df0a5-79ef-4435-9c98-5a9b2383d420)
![jakarta-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/0d339161-2abb-419e-9918-e6347b7c686e)
![h2-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/3e65f8a8-a9a7-44bc-85c8-42d173338c74)
![maven-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/159c5f30-83db-49a2-906a-fc92a071eeff)
![html-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/cf73900e-a565-405d-b7dd-cc05f9429c2f)
![css-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/d7d9ecf6-1cfb-4fe1-ba32-dd43d59921a8)
![jsp-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/dc929923-33e6-4d73-a4c0-6eb3638b6ab5)
![jstl-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/782f3a65-deb3-4fd2-9021-e8dfbb387cad)
![junit-logo](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/a1a05826-fecb-4b7a-827c-946ffc72da32)


## Application interface

### Home page

![Home-page](https://github.com/user-attachments/assets/f60fae71-35aa-4459-92b7-ac42f7e9e526)

 URL - '/home'
 - Links leading to the new match and the list of completed matches pages

### New match page

![New-match-page](https://github.com/user-attachments/assets/b32e8180-c795-4bac-b179-8e51a3f1f7f7)


URL - `/new-match`

Interface:

 - HTML form with fields “Player name 1”, “Player name 2” and a “start” button. For simplicity, let's assume that player names are unique. A player cannot play with himself.
 - Clicking the “start” button will result in a POST request to `/new-match`

POST request handler:

 - Checks for the existence of players in the Players table. If a player with this name does not exist, we create one.
 - Create an instance of the Match class (containing the players' ids and current score) and put it into the collection of current matches (existing only in the application memory or in key-value storage). The key of the collection is the UUID, the value is the Match class instance
 - Redirect to the page `/match-score?uuid=$match_id`


### Match score page

![Match-score-page](https://github.com/user-attachments/assets/6f91fa34-ec9b-496c-886c-fff079ca4ce4)


URL - `/match-score?uuid=$match_id`. GET parameter uuid contains the UUID of the match.

Interface:

 - Table with player names, current score
 - Forms and buttons for actions - “player 1 won the current point”, “player 2 won the current point”
 - Clicking the buttons leads to a POST request to `/match-score?uuid=$match_id`, the fields of the submitted form contain the id of the player who won the point.

POST request handler:

 - Retrieves an instance of the Match class from the collection
 - According to which player won the point, updates the match score
 - If the match is not over - renders the match score table with the buttons described above
 - If the match is over:
   - Delete the match from the collection of current matches
   - Write the finished match to SQL database
   - Rendering the final score

### Match result page

![Match-resulte-page](https://github.com/user-attachments/assets/605b1824-32f4-47d1-89e3-05aef7af1936)


 - The match result page is returned when the match ends.
 - The address is the same as the match itself.
 - Shows winner name 


### Played matches page 

![Played-matches-page](https://github.com/user-attachments/assets/2d9d0aa2-dcf4-4c0d-ad9b-83af05a3605e)


URL - `/matches?page=$page_number&filter_by_player_name=$player_name`. GET parameters:
 - `page` - page number. If the parameter is not specified, the first page is implied
 - `filter_by_player_name` - name of the player whose matches we are looking for. If the parameter is not set, all matches are displayed

Interface:

 - Form with filter by player name. An input field for the name and a “search” button. A GET request of the form /matches?filter_by_player_name=${NAME} is generated when clicked.
 - List of matches found
 - Page switch, if more matches are found than can fit on one page.

## Database diagram

![database](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/4285a97a-c018-402e-993d-4f419f64d235)

## Requirements
  + Java 17+
  + Apache Maven
  + Tomcat 10
  + Intellij IDEA

## Project launch

1. Clone the repository:
   ```
   git clone https://github.com/ssss1131/tennis-Scoreboard.git
   ```

2. Open Intellij IDEA and in Main Menu -> Open select the folder you have decloned.
   
3. In Intellij IDEA, select Run -> Edit Configuration.
  
4. In the pop-up window, click "+" and add Tomcat :
   
    ![Add tomcat](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/66f677af-ce05-4676-8dc7-09bc8cbf5db5)

5. Then click "Fix" : 

    ![Fix botton](https://github.com/VladislavLevchikIsAProger/currency_exchange/assets/153897612/516b7afb-42ef-4374-b96e-2a49d3f866c9)

6. In the window that pops up, select :

   ![War exploded](https://github.com/VladislavLevchikIsAProger/tennis_scoreboard/assets/153897612/f9c8ffed-8f6f-41a7-8fe7-3e0cab9708d4)


7. In the Application context leave the following :
   
   ![Application context](https://github.com/VladislavLevchikIsAProger/currency_exchange/assets/153897612/895091c7-dd29-49b9-8edc-c9b5f29cf018)

8. Click Apply and start Tomcat
