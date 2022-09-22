package com.example.lab1.model.input;

import lombok.Data;

import java.util.List;

@Data
public class GeneralInputData {
   List<SystemInputData> systems;
   List<Integer> experienceOfExpert;
}
