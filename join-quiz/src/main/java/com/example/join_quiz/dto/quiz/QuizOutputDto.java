package com.example.join_quiz.dto.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QuizOutputDto {
    private Long id;
    private String name;
    private String description;
    private Long configId;
    private String authorName;
}
