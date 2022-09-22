package com.example.lab1;

import com.example.lab1.model.input.ConnectionInputData;
import com.example.lab1.model.input.ElementInputData;
import com.example.lab1.model.input.GeneralInputData;
import com.example.lab1.model.input.SystemInputData;
import com.example.lab1.model.output.ConnectionOutputData;
import com.example.lab1.model.output.ElementOutputData;
import com.example.lab1.model.output.GeneralOutputData;
import com.example.lab1.model.output.SystemOutputData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MathProcessor {

    public GeneralOutputData getResult(GeneralInputData input) {

        GeneralOutputData output = new GeneralOutputData();
        calcWeightingCoefficients(input, output);
        calcSystems(input, output);
        return output;

    }

    private void calcSystems(GeneralInputData input, GeneralOutputData output) {

        List<SystemOutputData> systemOutputData = input.getSystems()
                .stream()
                .map(s -> calcSystem(s, input, output))
                .toList();
        output.setSystems(systemOutputData);

    }


    private void calcWeightingCoefficients(GeneralInputData input, GeneralOutputData output) {

        List<Double> weightingCoefficients = CalculateUtil.calcWeightingCoefficients(input);
        log.info("WeightingCoefficients {}", weightingCoefficients);
        output.setWeightingCoefficients(weightingCoefficients);

    }

    private SystemOutputData calcSystem(SystemInputData systemInputData, GeneralInputData input,
            GeneralOutputData output) {

        SystemOutputData outputData = new SystemOutputData();
        calcNumOfElem(systemInputData, outputData);

        ConnectionOutputData connectionOutputData = calcConnectionOutputData(systemInputData.getConnectionInputData(),
                outputData, output);
        outputData.setConnectionOutputData(connectionOutputData);

        List<ElementOutputData> elementOutputData = systemInputData.getElementData()
                .stream()
                .map(e -> calcElementOutputData(e, output))
                .toList();
        outputData.setElementData(elementOutputData);

        double systemComplexity = CalculateUtil.calcSystemComplexity(
                outputData.getConnectionOutputData().getRelativeNumOfConnections(),
                outputData.getConnectionOutputData().getRelativeComplexityOfConnections(),
                systemInputData.getElementData(), outputData.getElementData().stream().map(
                        ElementOutputData::getAssessmentOfComplexity).toList());
        log.info("SystemComplexity {}", systemComplexity);

        outputData.setSystemComplexity(systemComplexity);

        return outputData;
    }

    private ConnectionOutputData calcConnectionOutputData(ConnectionInputData connectionInputData,
            SystemOutputData systemOutputData, GeneralOutputData generalOutputData) {

        ConnectionOutputData outputData = new ConnectionOutputData();

        double relativeNumOfConnections = CalculateUtil.calcRelativeNumberOfConnections(
                connectionInputData, systemOutputData.getNumOfElem());
        log.info("RelativeNumOfConnections {}", relativeNumOfConnections);

        outputData.setRelativeNumOfConnections(relativeNumOfConnections);

        double relativeComplexityOfConnections = CalculateUtil.calcRelativeComplexityOfConnections(
                generalOutputData.getWeightingCoefficients(), connectionInputData);
        log.info("RelativeComplexityOfConnections {}", relativeComplexityOfConnections);

        outputData.setRelativeComplexityOfConnections(relativeComplexityOfConnections);

        return outputData;
    }

    private ElementOutputData calcElementOutputData(ElementInputData inputData, GeneralOutputData generalOutputData) {
        ElementOutputData outputData = new ElementOutputData();
        double assessmentOfComplexity = CalculateUtil.calcAssessmentOfComplexity(
                generalOutputData.getWeightingCoefficients(), inputData);
        outputData.setAssessmentOfComplexity(assessmentOfComplexity);
        log.info("AssessmentOfComplexity {}", assessmentOfComplexity);
        return outputData;
    }

    private void calcNumOfElem(SystemInputData systemInputData, SystemOutputData outputData) {

        int numOfElem = CalculateUtil.calcNumOfElement(systemInputData.getElementData());
        log.info("NumOfElem {}", numOfElem);
        outputData.setNumOfElem(numOfElem);

    }
}
