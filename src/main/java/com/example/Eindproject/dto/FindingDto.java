package com.example.Eindproject.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindingDto {

    Long id;

    @NonNull
    @NotEmpty
    String title;

    @NonNull
    @NotEmpty
    String description;

    Long inspection;
}
