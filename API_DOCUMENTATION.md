# API documentation

Base URL: `http://localhost:8080/api`. Send JSON with `Content-Type: application/json`. Lists are returned as JSON arrays. Errors use a JSON object with `timestamp`, `status`, `error`, and `message`.

| Method | Endpoint | Body / behavior | Success | Errors |
|---|---|---|---|---|
| GET | `/applications` | — | 200, application array | 500 |
| GET | `/applications/{id}` | — | 200, application | 404 |
| POST | `/applications` | `companyName`, `jobRole` required; `location`, `applicationDate` (`YYYY-MM-DD`), `status`, `jobType`, `notes` optional | 201, created application | 400 |
| PUT | `/applications/{id}` | Full replacement of editable fields; same JSON as POST | 200, updated application | 400, 404 |
| DELETE | `/applications/{id}` | — | 204, empty body | 404 |
| GET | `/topics?category=Java` | Optional case-insensitive category filter | 200, topic array | 500 |
| GET | `/topics/{id}` | — | 200, topic | 404 |
| POST | `/topics` | `name`, `category` required; `difficulty`, `completed`, `notes` optional | 201, created topic | 400 |
| PUT | `/topics/{id}` | Full replacement: `name`, `category`, `difficulty`, `completed`, `notes` | 200, updated topic | 400, 404 |
| DELETE | `/topics/{id}` | — | 204 | 404 |
| GET | `/study-sessions` | — | 200, session array | 500 |
| POST | `/study-sessions` | `topic` required; `date`, `durationMinutes` (minimum 1), `notes` optional | 201, created session | 400 |
| DELETE | `/study-sessions/{id}` | — | 204 | 404 |
| GET | `/mock-tests` | — | 200, test array including calculated `percentage` | 500 |
| GET | `/mock-tests/{id}` | — | 200, test | 404 |
| POST | `/mock-tests` | `testName`, `subject` required; score >= 0; totalMarks > 0; score <= totalMarks | 201, created test including percentage | 400 |
| PUT | `/mock-tests/{id}` | Full replacement; same fields as POST | 200, updated test | 400, 404 |
| DELETE | `/mock-tests/{id}` | — | 204 | 404 |
| GET | `/dashboard` | — | 200, dashboard summary object | 500 |

Enum values: application status `APPLIED`, `ONLINE_ASSESSMENT`, `INTERVIEW`, `SELECTED`, `REJECTED`; job type `FULL_TIME`, `INTERNSHIP`. Dates use ISO `YYYY-MM-DD` format. Example application body:

```json
{"companyName":"Example Ltd","jobRole":"Graduate Engineer","location":"Pune","applicationDate":"2026-09-28","status":"APPLIED","jobType":"FULL_TIME","notes":"Campus application"}
```

Example dashboard fields: `totalApplications`, per-status application counts, `totalTopics`, `completedTopics`, `preparationPercentage`, `totalStudyHours`, `totalMockTests`, `averageMockScore`, `bestMockScore`, and `weakAreas`. Percentages use the 0–100 scale. Study hours are rounded to two decimals. `weakAreas` is an array of `{ "area": "Java", "reasons": ["..."] }` entries. A preparation category is flagged when less than 50% of its topics are complete; a mock-test subject is flagged when its average score is below 60%. Matching category and subject names are combined. These are simple study prompts based only on entered records, not a prediction of ability.

