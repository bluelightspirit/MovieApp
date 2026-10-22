# Movie + TMDB Kotlin App

### This shows some data of movies like ratings, votes, release dates, descriptions of the movie, capabilities to take selfies, and has a likes button.

## When did you make this originally?

April to May 2026 as part of an Android mobile development class.

## How do you make this work?

You use Android Studio - tested in Windows 11. Go to [AuthInterceptor.kt](https://github.com/bluelightspirit/MovieApp/blob/master/app/src/main/java/edu/vt/mobiledev/movieapp/AuthInterceptor.kt) and replace "YOUR_TMDB_READ_ACCESS_TOKEN" with a TMDB Read Access API token from https://www.themoviedb.org/settings/api then go to Device Manager, set up a device via clicking 2 special icons at the right side shown below - one looks like a phone with android next ot it while the other one looks like a plus,:

<img width="385" height="289" alt="image" src="https://github.com/user-attachments/assets/3bca8875-6041-4b07-8058-c2f5175a1d9d" />

then from 

<img width="273" height="114" alt="image" src="https://github.com/user-attachments/assets/e5a822fa-338e-48e2-8fd8-cd0c049737d4" />

click "Create Virtual Device" then click "Pixel 4" then "Next"

<img width="1014" height="735" alt="image" src="https://github.com/user-attachments/assets/01d49edb-259e-4d6a-b691-5126453e373b" />

To copy the repo author, select API 34 "UpsideDownCake"; Android 14.0, and Google Play Intel x86_64 Atom System Image or whatever system image you have available based on your hardware architecture (this has only been tested on an Intel i5-12600K computer). 

<img width="968" height="731" alt="image" src="https://github.com/user-attachments/assets/571e62a0-314c-4778-9e1a-1ae6bd7252ae" />

The virtual device should look like this in Android Studio. Then go to "Additional settings" and see if these match if you want to copy the author of the repoistory - Automatic Graphics acceleration, 4 CPU cores, 2 GB RAM, 6 GB internal storage, and 256 MB VM heap size:

<img width="910" height="678" alt="image" src="https://github.com/user-attachments/assets/f813cccc-01d2-4f7a-abb1-9c932c14cdad" />

Then press "Finish" and it should be good to run.

To run it, you can setup the Android app configuration. Click the area next to the play button with the android icon next to it, then click "Edit Configurations..."

<img width="823" height="206" alt="image" src="https://github.com/user-attachments/assets/28d8b73f-299b-4fdc-97ed-d8774860abf3" />

and do whatever you can to make it match this, then press "Apply" then "OK":
<img width="909" height="917" alt="image" src="https://github.com/user-attachments/assets/0c43cde8-1484-4123-a42b-6da6cc7e29af" />

Then press the green run button on the right side to run the device, then press the green run button at the top for this to work:

<img width="881" height="250" alt="image" src="https://github.com/user-attachments/assets/849d3544-57d9-4bcd-9326-7133ac4078ad" />

which then should show this:

<img width="817" height="1038" alt="image" src="https://github.com/user-attachments/assets/678efe7a-ec09-4f45-95b6-92d7a0dd2cd5" />

