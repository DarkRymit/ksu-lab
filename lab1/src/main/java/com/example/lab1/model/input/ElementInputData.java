package com.example.lab1.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ElementInputData {
    Integer numOfElements;
    List<Integer> expertRating;
}
