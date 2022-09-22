package com.example.lab1;

import com.example.lab1.model.input.ConnectionInputData;
import com.example.lab1.model.input.ElementInputData;
import com.example.lab1.model.input.GeneralInputData;

import java.util.List;
import java.util.stream.IntStream;

public interface CalculateUtil {

    static int calcNumOfElement(List<ElementInputData> elementInputData) {
        return elementInputData.stream().mapToInt(ElementInputData::getNumOfElements).sum();
    }

    static double calcRelativeNumberOfConnections(ConnectionInputData connectionInputData, int numOfElement) {
        return connectionInputData.getNumOfElements() / ((double) numOfElement * (numOfElement - 1));
    }

    static List<Double> calcWeightingCoefficients(GeneralInputData generalInputData) {
        int maxExperience = generalInputData.getExperienceOfExpert()
                .stream()
                .mapToInt(value -> value)
                .max()
                .orElseThrow();
        return generalInputData.getExperienceOfExpert()
                .stream()
                .map(v -> (double) v / maxExperience)
                .toList();
    }

    static Double calcAssessmentOfComplexity(List<Double> weightingCoefficients, ElementInputData elementInputData) {
        List<Integer> expertRating = elementInputData.getExpertRating();
        return IntStream.range(0, weightingCoefficients.size())
                .mapToDouble(i -> expertRating.get(i) * weightingCoefficients.get(i))
                .sum();
    }

    static Double calcRelativeComplexityOfConnections(List<Double> weightingCoefficients,
            ConnectionInputData connectionInputData) {
        List<Double> expertRating = connectionInputData.getExpertRating();
        return IntStream.range(0, weightingCoefficients.size())
                .mapToDouble(i -> expertRating.get(i) * weightingCoefficients.get(i))
                .sum();
    }

    static Double calcSystemComplexity(double relativeNumberOfConnections, double relativeComplexityOfConnections,
            List<ElementInputData> elements,
            List<Double> assessmentsOfComplexity) {
        List<Integer> numOfElements = elements.stream().map(ElementInputData::getNumOfElements).toList();
        return (1 + relativeNumberOfConnections * relativeComplexityOfConnections) * IntStream.range(0, elements.size())
                .mapToDouble(i -> numOfElements.get(i) * assessmentsOfComplexity.get(i))
                .sum();
    }

}
