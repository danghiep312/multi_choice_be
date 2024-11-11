package com.example.join_quiz.dto.submit;

import com.example.join_quiz.dto.user_choice.UserChoiceInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SubmitInputDto {
    private List<UserChoiceInputDto> choices;
}
