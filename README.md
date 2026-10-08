# Placement Preparation Platform

A small full-stack study and job-search tracker for a student preparing for software placements. The project uses a straightforward Spring Boot REST backend, MySQL persistence, and a single-page vanilla JavaScript frontend.

## Features

- Track applications and update their status.
- Organize preparation topics by category and completion state.
- Record study sessions manually or time them with the pause/resume focus timer, then see total study time.
- Record mock tests and calculate percentages.
- View application counts, preparation progress, study hours, and test averages.
- See focus areas when a preparation category has less than 50% of its topics complete or a mock-test subject averages below 60%.
- Start with sample records that can be safely edited or deleted.

## Technology stack

Java 21, Spring Boot 3.5, Spring Web, Spring Data JPA/Hibernate, Bean Validation, MySQL, Maven, HTML, CSS, and browser JavaScript Fetch API. There is no frontend build step or JavaScript framework.

## Project structure

```text
placement-prep-platform/
├── backend/                         Spring Boot app and static frontend
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/placementprep/  controller, service, repository, entity, exception
│       └── resources/               properties, seed data, static HTML/CSS/JS
├── database/schema.sql
├── postman/                         API request collection
├── API_DOCUMENTATION.md
├── INTERVIEW_GUIDE.md
└── 10_DAY_PLAN.md
```

The controller translates HTTP requests, the service contains the small amount of application logic, repositories provide database operations, entities map Java objects to tables, and Hibernate performs the SQL work through JPA. This separation makes each API flow easy to follow without adding extra layers.

The dashboard's focus-area hints use two simple thresholds: topic completion below 50% for a category, and average mock-test score below 60% for a subject. Matching names are combined into one item with the reason shown. This is a transparent heuristic based on recorded data, not an assessment of the student's ability.

In the Study Sessions section, the focus timer records the selected topic and optional notes. It can be paused and resumed; finishing saves the elapsed time rounded to the nearest minute (a minimum of 30 seconds is required). The timer runs in the current browser page and resets if the page is reloaded before saving.

## Database setup

1. Install/start MySQL and create a local database. In MySQL Workbench, run `database/schema.sql` (or let the default URL create the database when the MySQL account has permission).
2. The default connection targets `jdbc:mysql://localhost:3306/placement_prep`. For a local setup, configure your MySQL username and password with environment variables instead of saving credentials in Git. The built-in `root` and empty-password fallback is only intended for a local MySQL installation configured that way; use a dedicated database account for shared or hosted environments:

   ```powershell
   $env:DB_URL='jdbc:mysql://localhost:3306/placement_prep?serverTimezone=UTC'
   $env:DB_USERNAME='root'
   $env:DB_PASSWORD='your-local-password'
   ```

   On macOS/Linux, use `export DB_URL=...`, `export DB_USERNAME=...`, and `export DB_PASSWORD=...` in the terminal used to start the app.

3. Start the backend. Hibernate `ddl-auto=update` creates or updates the entity tables; `data.sql` inserts a small starter set if those example records are missing. To reset, delete the sample rows from Workbench or drop the local database and recreate it.

`spring.datasource.url` selects the MySQL server and database. `spring.datasource.username` and `.password` read environment variables with local defaults. `spring.jpa.hibernate.ddl-auto=update` synchronizes entity tables for this learning project; do not use this automatic schema mode as a production migration strategy. `spring.jpa.show-sql=true` prints SQL for learning/debugging.

## Run the project

Prerequisites: JDK 21, Maven 3.9+, and MySQL 8 (or compatible MySQL server). Install Maven and verify `mvn -version`. From the `backend/` folder:

```powershell
mvn spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080). Spring Boot serves the frontend and API from the same port, so there is no separate frontend server or CORS setup. To build a jar, run `mvn clean package`, then `java -jar target/placement-prep-platform-1.0.0.jar`.

## API and Postman

API base: `http://localhost:8080/api`. See [API_DOCUMENTATION.md](API_DOCUMENTATION.md) for methods, bodies, responses and errors. Import `postman/Placement Prep.postman_collection.json` into Postman. Requests use example payloads and a collection variable to remember a newly created application ID. Change the request sequence if you run create/delete requests repeatedly.

## Git and GitHub

From the project root:

```bash
git init                         # start version control
git status                       # inspect changed files
git add .                        # stage project files
git commit -m "Build placement prep platform"  # save a local snapshot
git branch -M main               # name the main branch
git remote add origin <repo-url> # connect your GitHub repository
git push -u origin main          # publish the branch
```

The `.gitignore` excludes generated build output, IDE settings, local configuration and secrets. Never add a real database password to tracked properties files.

## Screenshots

Add screenshots here after running the app locally.

## Implemented and future work

Implemented features are listed above. Possible future extensions are student accounts and authentication, role-based access, notifications, a React UI, deployment and cloud MySQL, and more detailed analytics. They are intentionally outside the small current project.

