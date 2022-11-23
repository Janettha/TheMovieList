# TheMovieList

Firebase Storage

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 15 00" src="https://user-images.githubusercontent.com/17371212/203390576-d686dde7-414c-489c-a226-b0aa3c0f83f6.png">


Cloud Firestore

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 14 47" src="https://user-images.githubusercontent.com/17371212/203390611-15b92d87-601b-4de9-ab05-4babcba46f70.png">

App :: Profile Section

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 14 47" src="https://user-images.githubusercontent.com/17371212/203436411-71b25acd-1817-4ff3-b375-2f1843225587.jpg">

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 14 47" src="https://user-images.githubusercontent.com/17371212/203436404-9bc28e3f-1b38-4793-9656-d71b7c607cce.jpg">


App :: Movies Section

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 14 47" src="https://user-images.githubusercontent.com/17371212/203436378-39ee657b-d9da-44b4-b9c4-8ce88713678b.jpg">


App :: Maps Section

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 14 47" src="https://user-images.githubusercontent.com/17371212/203436876-c437ec8e-5767-4063-b157-cab506e8803d.jpg">


App :: Images Section

<img width="500" alt="Captura de pantalla 2022-11-22 a la(s) 12 14 47" src="https://user-images.githubusercontent.com/17371212/203436392-01a7f0fe-d08b-49d0-bc0b-caf78d2a672e.jpg">


Show movies and profile information according to themoviewdb.org API.

This app shows some profile information, according to se session loged. Also, it shows the rated movies by the user.
In the movie section is loaded 3 list of the data available with the api: popular, upcoming and top rated movies.
Maily, each recyclerview shown a list loaded with the grupie library and for the image glide is the tool loaded.
For the background, a services it's implemented to share de user location. Each location is asked each 5 minutes, and saved it in firebase; with NotificationCompat is advised to the user.
In the image section, the user can upload images and save it in the Firebase console.


Missing Steps (work in progress):
- It's necessary to implement the DB to have data persistent.
- Read the firebase db to show the user locations saved it's not included.
- Load images from camera.

Include to improve:
* Login
* Paged in the recyclerview
* A new design
* Jetpackcompe implementation
* New Dialog o Fragment Details for each movie

