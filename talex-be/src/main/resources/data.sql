INSERT INTO skill (name, category) VALUES
                                       ('Java', 'Programming'),
                                       ('Spring Boot', 'Programming'),
                                       ('Hibernate', 'Programming'),
                                       ('JPA', 'Programming'),
                                       ('Python', 'Programming'),
                                       ('C++', 'Programming'),
                                       ('C#', 'Programming'),
                                       ('JavaScript', 'Programming'),
                                       ('TypeScript', 'Programming'),
                                       ('Go', 'Programming'),
                                       ('Rust', 'Programming'),
                                       ('PHP', 'Programming'),

                                       ('React', 'Frontend'),
                                       ('Next.js', 'Frontend'),
                                       ('Angular', 'Frontend'),
                                       ('Vue.js', 'Frontend'),
                                       ('HTML', 'Frontend'),
                                       ('CSS', 'Frontend'),
                                       ('Tailwind CSS', 'Frontend'),
                                       ('Bootstrap', 'Frontend'),

                                       ('Node.js', 'Backend'),
                                       ('Express.js', 'Backend'),
                                       ('NestJS', 'Backend'),
                                       ('ASP.NET', 'Backend'),
                                       ('Django', 'Backend'),
                                       ('Flask', 'Backend'),
                                       ('FastAPI', 'Backend'),

                                       ('PostgreSQL', 'Database'),
                                       ('MySQL', 'Database'),
                                       ('MongoDB', 'Database'),
                                       ('Redis', 'Database'),
                                       ('Oracle Database', 'Database'),
                                       ('SQL Server', 'Database'),

                                       ('Docker', 'DevOps'),
                                       ('Kubernetes', 'DevOps'),
                                       ('Jenkins', 'DevOps'),
                                       ('GitHub Actions', 'DevOps'),
                                       ('Terraform', 'DevOps'),
                                       ('Ansible', 'DevOps'),
                                       ('Linux', 'DevOps'),

                                       ('AWS', 'Cloud'),
                                       ('Azure', 'Cloud'),
                                       ('Google Cloud', 'Cloud'),

                                       ('Git', 'Tools'),
                                       ('Postman', 'Tools'),
                                       ('Swagger', 'Tools'),
                                       ('Figma', 'Tools'),

                                       ('Machine Learning', 'AI'),
                                       ('Deep Learning', 'AI'),
                                       ('TensorFlow', 'AI'),
                                       ('PyTorch', 'AI'),
                                       ('OpenAI API', 'AI'),
                                       ('Computer Vision', 'AI'),
                                       ('Natural Language Processing', 'AI'),

                                       ('UI Design', 'Design'),
                                       ('UX Design', 'Design'),
                                       ('Graphic Design', 'Design'),
                                       ('Adobe Photoshop', 'Design'),
                                       ('Adobe Illustrator', 'Design'),

                                       ('Project Management', 'Business'),
                                       ('Agile', 'Business'),
                                       ('Scrum', 'Business'),
                                       ('Business Analysis', 'Business'),

                                       ('Content Writing', 'Marketing'),
                                       ('SEO', 'Marketing'),
                                       ('Digital Marketing', 'Marketing'),
                                       ('Social Media Marketing', 'Marketing'),

                                       ('Photography', 'Creative'),
                                       ('Video Editing', 'Creative'),
                                       ('Adobe Premiere Pro', 'Creative'),
                                       ('After Effects', 'Creative'),

                                       ('Excel', 'Office'),
                                       ('Power BI', 'Office'),
                                       ('Data Analysis', 'Office'),
                                       ('Tableau', 'Office');

INSERT INTO users (
    name,
    username,
    email,
    password,
    bio,
    profile_picture,
    role,
    created_at,
    updated_at
)
VALUES
    ('Alice Johnson', 'alice', 'alice@example.com', '$2a$10$passwordHash', 'Backend Developer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Bob Smith', 'bob', 'bob@example.com', '$2a$10$passwordHash', 'Frontend Developer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Charlie Brown', 'charlie', 'charlie@example.com', '$2a$10$passwordHash', 'DevOps Engineer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('David Wilson', 'david', 'david@example.com', '$2a$10$passwordHash', 'AI Engineer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Emma Davis', 'emma', 'emma@example.com', '$2a$10$passwordHash', 'UI/UX Designer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Frank Miller', 'frank', 'frank@example.com', '$2a$10$passwordHash', 'Full Stack Developer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Grace Taylor', 'grace', 'grace@example.com', '$2a$10$passwordHash', 'Data Analyst', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Henry Moore', 'henry', 'henry@example.com', '$2a$10$passwordHash', 'Cloud Engineer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Isabella Thomas', 'isabella', 'isabella@example.com', '$2a$10$passwordHash', 'Digital Marketer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Jack Anderson', 'jack', 'jack@example.com', '$2a$10$passwordHash', 'Software Engineer', NULL, 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_skill_offered(user_id, skill_id, proficiency_level, years_experience) VALUES

-- Alice
(1,1,5,6),
(1,2,5,5),
(1,3,4,4),

-- Bob
(2,13,5,5),
(2,14,4,3),
(2,17,5,6),

-- Charlie
(3,34,5,5),
(3,35,4,4),
(3,41,4,3),

-- David
(4,49,5,4),
(4,50,5,4),
(4,53,4,3),

-- Emma
(5,56,5,6),
(5,57,5,5),
(5,46,4,2),

-- Frank
(6,1,5,7),
(6,13,5,5),
(6,21,4,4),

-- Grace
(7,68,5,5),
(7,69,4,4),
(7,70,5,5),

-- Henry
(8,41,5,5),
(8,34,4,4),
(8,35,4,3),

-- Isabella
(9,64,5,5),
(9,65,4,4),
(9,66,5,4),

-- Jack
(10,5,5,6),
(10,8,5,5),
(10,29,4,4);

INSERT INTO user_skill_wanted(user_id, skill_id, priority) VALUES

-- Alice wants Frontend
(1,13,5),
(1,14,4),
(1,34,3),

-- Bob wants Backend
(2,1,5),
(2,2,4),
(2,21,3),

-- Charlie wants AI
(3,49,5),
(3,50,4),

-- David wants Cloud
(4,41,5),
(4,42,4),

-- Emma wants Frontend
(5,8,5),
(5,13,4),

-- Frank wants Design
(6,56,5),
(6,57,4),

-- Grace wants AI
(7,49,5),
(7,53,4),

-- Henry wants Java
(8,1,5),
(8,2,4),

-- Isabella wants Design
(9,56,5),
(9,57,4),

-- Jack wants DevOps
(10,34,5),
(10,35,4);