# RateMyCampus
## Contributors:
* [Leandro Hamaguchi](https://github.com/LeandroHamaguchi)
* [Tianyu Luo](https://github.com/stevenluo624)
* [Karan Singh](https://github.com/codymavok)
* [Minghao Sun](https://github.com/TNDLight)
* [David Wang](https://github.com/David1425)

## Project Summary
This project is a Java Swing-based application allowing users to creat acouunts, post reviews, and rate various University or Toronto places such as library, food truck, building. Users can also view the places on an interactive map, they can view, reply or like other people's review on each place, plus user can build personal profiles with unque bios. 

The application aims to provide a collaborative and engaging platform for students, staff, and vistors to share and explore perspectives about campus spaces.

The application addresses the lack of an integrated platform for University of Toronto students, staffm and vistors to share their experiences and opinions about campus buildings. It helps:
1. **New Students**: Explore different places on campus based on peer reviews.
2. **Campus Visitors**: Find well-rated places for visit, dining or recreation.
3. **University Staff**: Gain insights into user experiences and feedback to imporve facitlities. 


## Table of Contents
- [Project Overview](#ratemycampus)
- [Project Summary](#project-summary)
- [Features](#features)
- [Installation Instructions](#installation-instructions)
- [Usage](#usage)
- [License](#license)
- [Feedback and Contributions](#feedback-and-contributions)


## Features
* Profile creation with unique bios.
* Post, view and rate reviews of University of Toronto places.
![images of create reviews](src/main/resources/images/rate.jpg)
* Interactive map integration to explore various places location.
![images of viewing map](src/main/resources/images/map.jpg)
* Ability to like and reply to reviews.

## Installation Instructions
The Project is compatible through all OS. 
1. Clone the repository (or make sure the .idea, pom.xml and src folder are downloaded).
   ```
   git clone https://github.com/stevenluo624/RateMyCampus.git
   cd RateMyCampus
   ```
2. Check the java version (if your are building from the terminal).
   ```
   java -version
   ```
   Or check java version on your IDE (i.e. intellij), the project is built with corretto-17.
   <img width="827" alt="Screenshot 2024-12-02 at 11 28 23 PM" src="https://github.com/user-attachments/assets/88ae9c9a-5d48-479f-a55d-5b0daf80ca47">
3. Make sure to reload the maven project (i.e. pom.xml) before building the RateMyCampus application.
   <img width="1461" alt="Screenshot 2024-12-02 at 11 31 05 PM" src="https://github.com/user-attachments/assets/83277cf9-4e9d-4201-ac63-1fe93b820bdd">
4. Run the main application MainRateMyCampusApp.java

## Usage
1. Signup for an account (if you have an existing account jump to login)
![image of signup](src/main/resources/images/signup.jpg)
2. Login with your account
![impage of login](src/main/resources/images/login.jpg)
3. Check the reviews posted
4. Make your own review by clicking add your review button
![images of create reviews](src/main/resources/images/rate.jpg)
5. Click on the stars to add your review (from a scale of 1-5) and add your comment. Once you finish your review click submit
6. Or you can check our the location of the place being reviewed by clicking view map button, you zoom in to see the exact location of the place.
![images of viewing map](src/main/resources/images/map.jpg)


## License
This project is licensed under the MIT License. See the LICENSE.md file for details.

## Feedback and Contributions
### Feedbacks
We welcome feedback and suggestions! Please open an issue on GitHub or contact us via [Google Form](https://docs.google.com/forms/d/e/1FAIpQLSfLvapOTcbBCixbNcKGnz9T7zNVomn7YI8HXXTcTsjRQ9pMzg/viewform?usp=sf_link).
People who wants to provide feedback must provide their name and email address in case we need to contact you. Valid feedback must contain a clear description of either a clear description of the issue and what is expected to be fixed or a detailed elaboration of the feature we can add for improvement and a short description of your propose solution. 

### Contributions
1. Fork the repository.
2. Create a new branch.
   ```
   git checkout -b feature-branch
   ```
3. Make your changes.
4. Commit your changes
    ```
   git commit -m 'Add some feature'
    ```
5. Push to the branch
   ```
   git push origin feature-branch
   ```
6. Open a pull request.

