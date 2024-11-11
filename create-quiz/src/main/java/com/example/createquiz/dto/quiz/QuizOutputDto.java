package com.example.createquiz.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QuizOutputDto {
    protected Long id;
    protected String name;
    protected String description;
    private String authorName;
}
