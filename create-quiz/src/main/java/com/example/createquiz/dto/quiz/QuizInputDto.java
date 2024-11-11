package com.example.createquiz.dto.quiz;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QuizInputDto {
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    private Long configId;
    private String authorName;
}
