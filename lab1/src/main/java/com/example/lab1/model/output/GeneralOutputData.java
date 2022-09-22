package com.example.lab1.model.output;

import lombok.Data;

import java.util.List;

@Data
public class GeneralOutputData {
   List<SystemOutputData> systems;
   List<Double> weightingCoefficients;
}
