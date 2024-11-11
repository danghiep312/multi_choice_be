CREATE TABLE questions (
                           id SERIAL PRIMARY KEY,
                           question_text VARCHAR(255) NOT NULL,
                           is_multiple_choice BOOLEAN NOT NULL,
                           position INT NOT NULL
);

CREATE TABLE question_choices (
                                  question_id INT,
                                  choice VARCHAR(255),
                                  FOREIGN KEY (question_id) REFERENCES questions(id)
);

CREATE TABLE correct_answers (
                                 question_id INT,
                                 correct_answer INT,
                                 FOREIGN KEY (question_id) REFERENCES questions(id)
);