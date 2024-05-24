package com.tsaidenis.mainservice.dto;

import com.tsaidenis.mainservice.entity.Category;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BookDto {
    @Size(min = 3,max = 15,message = "Максимальная длина 15")
    @NotBlank
    private String title;
    @Size(min = 3,max = 15,message = "Максимальная длина 15")
    @NotBlank
    private String author_name;
    @Size(min = 3,max = 15,message = "Максимальная длина 15")
    private String category;

}
