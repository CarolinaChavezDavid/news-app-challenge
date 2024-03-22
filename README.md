# news-app-challenge
News application made with MVVM pattern, Clean architecture, Jetpack compose, Coroutines, Room, Hilt and Retrofit.

## 📰 NewsScreens 
 The News Screen contains a list of news obtained from the JSON placeholder API using retrofit and saved in the News room table, using coroutines and implementing the MVVM patter and Clean architecture. This list can be filtered using the searching component, and by clicking each item, the user can access the News Detail Screen, where they can find more information.

 ![NewsScreen](https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/7023323d-c022-4697-bbbd-5a6aaa327051)


 |  **News Screen** |  **New Detail Screen** |
|---|---|
|  <img width="200" alt="image" src="https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/92ecdf1f-1ee5-4ea6-b2a3-60235f5d78fa">  | <img width="200" alt="image" src="https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/7e348628-f4bc-456c-939c-e344920a9167">  |
| Information got from https://jsonplaceholder.org/posts  | Information got from https://jsonplaceholder.org/posts/$newsId  |



## 🙆‍♀️ Users Screen 
When the user accesses a news detail, they can see the users section. By clicking the "Read by" section, they can access the Users List screen, and by clicking a user, they can access the users detail screen with the user's information obtained from the API including the user location display in the google's map according to the longitud and latitud got from the user.

![usersScreens](https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/79158a2a-b5d0-4c38-8d92-2dc23c831028)


   |  **Users list screen** |  **User Detail Screen** |
|---|---|
|  <img width="200" alt="image" src="https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/729b37d9-e802-42e5-b05f-3ac215449091">  | <img width="200" alt="image" src="https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/8b63137b-bc3e-4508-9a14-c587cc6ae1aa">  |
|Information got from https://jsonplaceholder.org/users | Information got from https://jsonplaceholder.org/users/$userId  |


 ## 🧪 UnitTest
 Unit test were  created for the view models, repositories and use case
 
![image](https://github.com/CarolinaChavezDavid/news-app-challenge/assets/77591347/e43964f6-fd89-4c3b-bc45-621f83ea6d23)

