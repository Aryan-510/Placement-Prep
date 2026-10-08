INSERT INTO applications (company_name, job_role, location, application_date, status, job_type, notes)
SELECT 'Sample Company Alpha','Associate Software Engineer','Bengaluru','2026-09-10','APPLIED','FULL_TIME','Example campus placement application'
WHERE NOT EXISTS (SELECT 1 FROM applications WHERE company_name='Sample Company Alpha' AND job_role='Associate Software Engineer');
INSERT INTO applications (company_name, job_role, location, application_date, status, job_type, notes)
SELECT 'Sample Company Beta','Assistant System Engineer','Pune','2026-09-12','ONLINE_ASSESSMENT','FULL_TIME','Example aptitude and coding assessment'
WHERE NOT EXISTS (SELECT 1 FROM applications WHERE company_name='Sample Company Beta' AND job_role='Assistant System Engineer');
INSERT INTO applications (company_name, job_role, location, application_date, status, job_type, notes)
SELECT 'Sample Company Gamma','Systems Engineer','Mysuru','2026-09-15','INTERVIEW','FULL_TIME','Example interview preparation notes'
WHERE NOT EXISTS (SELECT 1 FROM applications WHERE company_name='Sample Company Gamma' AND job_role='Systems Engineer');
INSERT INTO preparation_topics (name, category, difficulty, completed, notes)
SELECT 'Arrays','DSA','MEDIUM',true,'Practice traversal and two-pointer patterns' WHERE NOT EXISTS (SELECT 1 FROM preparation_topics WHERE name='Arrays' AND category='DSA');
INSERT INTO preparation_topics (name, category, difficulty, completed, notes)
SELECT 'HashMap','Java','MEDIUM',false,'Review hashing and common interview problems' WHERE NOT EXISTS (SELECT 1 FROM preparation_topics WHERE name='HashMap' AND category='Java');
INSERT INTO preparation_topics (name, category, difficulty, completed, notes)
SELECT 'Object-oriented programming','OOP','EASY',true,'Encapsulation, inheritance, polymorphism, abstraction' WHERE NOT EXISTS (SELECT 1 FROM preparation_topics WHERE name='Object-oriented programming' AND category='OOP');
INSERT INTO preparation_topics (name, category, difficulty, completed, notes)
SELECT 'SQL joins','SQL','MEDIUM',false,'Practice inner and outer joins' WHERE NOT EXISTS (SELECT 1 FROM preparation_topics WHERE name='SQL joins' AND category='SQL');
INSERT INTO preparation_topics (name, category, difficulty, completed, notes)
SELECT 'Transactions and normalization','DBMS','MEDIUM',false,'Review ACID and normal forms' WHERE NOT EXISTS (SELECT 1 FROM preparation_topics WHERE name='Transactions and normalization' AND category='DBMS');
INSERT INTO preparation_topics (name, category, difficulty, completed, notes)
SELECT 'Process scheduling','Operating Systems','MEDIUM',false,'Compare common scheduling algorithms' WHERE NOT EXISTS (SELECT 1 FROM preparation_topics WHERE name='Process scheduling' AND category='Operating Systems');
INSERT INTO study_sessions (date, topic, duration_minutes, notes)
SELECT '2026-09-20','Arrays',60,'Solved array practice questions' WHERE NOT EXISTS (SELECT 1 FROM study_sessions WHERE topic='Arrays' AND date='2026-09-20');
INSERT INTO study_sessions (date, topic, duration_minutes, notes)
SELECT '2026-09-21','Java collections',45,'Reviewed List, Set and Map' WHERE NOT EXISTS (SELECT 1 FROM study_sessions WHERE topic='Java collections' AND date='2026-09-21');
INSERT INTO study_sessions (date, topic, duration_minutes, notes)
SELECT '2026-09-22','SQL',50,'Practiced joins and grouping' WHERE NOT EXISTS (SELECT 1 FROM study_sessions WHERE topic='SQL' AND date='2026-09-22');
INSERT INTO mock_tests (test_name, subject, date, score, total_marks)
SELECT 'Aptitude practice 1','Aptitude','2026-09-18',34,50 WHERE NOT EXISTS (SELECT 1 FROM mock_tests WHERE test_name='Aptitude practice 1');
INSERT INTO mock_tests (test_name, subject, date, score, total_marks)
SELECT 'Java fundamentals quiz','Java','2026-09-19',38,50 WHERE NOT EXISTS (SELECT 1 FROM mock_tests WHERE test_name='Java fundamentals quiz');
INSERT INTO mock_tests (test_name, subject, date, score, total_marks)
SELECT 'SQL practice test','SQL','2026-09-21',42,50 WHERE NOT EXISTS (SELECT 1 FROM mock_tests WHERE test_name='SQL practice test');

