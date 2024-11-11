package com.example.join_quiz.dto.submit;

import com.example.join_quiz.dto.question.QuestionContainCorrectOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SubmitOutputDto {
    private Double score;
    private List<QuestionContainCorrectOutputDto> questions;
}
