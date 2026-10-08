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



## Future work

 Possible future extensions are student accounts and authentication, role-based access, notifications, a React UI, deployment and cloud MySQL, and more detailed analytics. They are intentionally outside the small current project.

