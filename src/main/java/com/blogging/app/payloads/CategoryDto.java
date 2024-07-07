package com.blogging.app.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private int categoryId;
@NotBlank
@Size(min=5, max = 80)
    private String categoryTitle;
    @NotBlank
    @Size(min=5, max = 1000)
    private String categoryDescription;
}
