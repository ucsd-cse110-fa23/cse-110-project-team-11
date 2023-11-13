# cse-110-project-team-11
cse-110-project-team-11 created by GitHub Classroom

## Setup

Please clone the `cse-110-project-team-11 main` repository with `git clone`, using the HTTPS link

<img width="600" alt="image" src="https://github.com/ucsd-cse110-fa23/cse-110-project-team-11/assets/97645890/9deed947-f008-43d4-9cf9-d7b9e38f1d28">

When you import this repository, make sure to make the repository **private**.

After navigating to the project's `java` folder (should be found in `cse-110-project-team-11/src/main/java`), you should see the `UI.java` file. This is the main file that we are using for the PantryPal app.

## Running the App
### Visual Studio Code 
Try to run the app, by pressing the UI.java launch button.\
<img width="166" alt="image" src="https://github.com/ucsd-cse110-fa23/cse-110-project-team-11/assets/97645890/f6a7f837-fe2a-4911-9616-0c3b4f06315c">
## Expected Output
<img width="300" alt="Screenshot 2023-11-11 at 11 19 30 PM" src="https://github.com/ucsd-cse110-fa23/cse-110-project-team-11/assets/97645890/fc81f816-9cdc-4279-83ce-b68fc1aac760">

## Common issues
### Make sure that the launch.json has the correct directory
Our current working app UI.java uses MacOS `aarch64` SDK version. Make sure that the launch.json file in the workspace has the proper build. You can check here:

<img width="608" alt="image" src="https://github.com/ucsd-cse110-fa23/cse-110-project-team-11/assets/97645890/352d7527-cee2-4c8b-aa85-f1ab380431fe">

Note: `lib` refers to the _relative_ path, where we access the library folder in the workspace (`cse-110-project-team-11/lib`).

If the relative path does not work, we can try to the the absolute path. You can download the JavaFX package [here](https://gluonhq.com/products/javafx/)

From here, we will change the configuration in the `launch.json`. 
1. Get the working directory of the JavaFX folder.
2. Copy a `lib` file and replace the lib file from the project.
3. Then copy path of the lib file to enter it into the launch.json directory.
<img width="600" alt="image" src="https://github.com/ucsd-cse110-fa23/cse-110-project-team-11/assets/97645890/701e9bea-3cce-4f71-a43d-95e85f66b8be"> <br />
4. Once you save launch.json after changing directory then your launch should work.

