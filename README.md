# realestate
Launch locally:
- Open a command prompt and add: 
    - cd C:PATH_TO_PROJECT/backend 
    - mvn spring-boot:run

- Open a second command prompt and add: 
    - cd C:PATH_TO_PROJECT/frontend
    - npm start

Prerequisite:
- postgresql server with a DB called realestate, user realestate (pass admin). Or modify these in prop file.
- maven, java JDK (with env paths)
- nodejs
- plugin Moesif Origin & CORS Changer, turned on, or add cors handling.
- Chrome driver installed at: C:\Users\vancer\Desktop\Libraryes & Sources\chromedriver_win32\chromedriver.exe or change the property to new location. This driver should be compatible with your chrome browser (use the same version).
