package com.example.lab1;

import com.example.lab1.model.input.ConnectionInputData;
import com.example.lab1.model.input.ElementInputData;
import com.example.lab1.model.input.GeneralInputData;
import com.example.lab1.model.input.SystemInputData;
import com.example.lab1.model.output.ElementOutputData;
import com.example.lab1.model.output.GeneralOutputData;
import com.example.lab1.model.output.SystemOutputData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Lab1Application implements CommandLineRunner {

    private final MathProcessor mathProcessor;

    public Lab1Application(MathProcessor mathProcessor) {
        this.mathProcessor = mathProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    private GeneralInputData getData() {
        GeneralInputData inputData = new GeneralInputData();
        inputData.setExperienceOfExpert(List.of(17, 13, 9, 8, 3, 5, 9));

        SystemInputData inputData1 = new SystemInputData();
        SystemInputData inputData2 = new SystemInputData();
        SystemInputData inputData3 = new SystemInputData();

        inputData.setSystems(List.of(inputData1,inputData2,inputData3));

        setUp1(inputData1);
        setUp2(inputData2);
        setUp3(inputData3);

        return inputData;
    }

    private void setUp1(SystemInputData inputData) {
        ConnectionInputData connectionInputData = new ConnectionInputData();
        connectionInputData.setNumOfElements(1255);
        connectionInputData.setExpertRating(List.of(0.8,0.6,0.5,0.3,0.7,0.6,0.9));
        inputData.setConnectionInputData(connectionInputData);
        List<ElementInputData> elementInputData = List.of(
                new ElementInputData(23, List.of(7,5,3,7,8,4,4)),
                new ElementInputData(48, List.of(3,8,5,4,9,5,8)),
                new ElementInputData(129, List.of(1,3,5,3,8,6,2)),
                new ElementInputData(213, List.of(8,2,6,9,8,1,7)),
                new ElementInputData(174, List.of(4,4,8,8,2,3,3)),
                new ElementInputData(17, List.of(6,1,2,4,9,7,5))
        );
        inputData.setElementData(elementInputData);
    }
    private void setUp2(SystemInputData inputData) {
        ConnectionInputData connectionInputData = new ConnectionInputData();
        connectionInputData.setNumOfElements(2001);
        connectionInputData.setExpertRating(List.of(1.1, 1.2, 0.9, 1.0, 1.0, 0.9, 1.0));
        inputData.setConnectionInputData(connectionInputData);
        List<ElementInputData> elementInputData = List.of(
                new ElementInputData(45, List.of(5,1,3,3,1,2,1)),
                new ElementInputData(95, List.of(6,3,7,4,3,7,4)),
                new ElementInputData(118, List.of(7,4,6,7,6,3,5)),
                new ElementInputData(139, List.of(4,7,7,8,9,8,9)),
                new ElementInputData(202, List.of(9,9,8,9,9,9,8)),
                new ElementInputData(121, List.of(2,4,2,5,4,1,3))
        );
        inputData.setElementData(elementInputData);
    }
    private void setUp3(SystemInputData inputData) {
        ConnectionInputData connectionInputData = new ConnectionInputData();
        connectionInputData.setNumOfElements(1927);
        connectionInputData.setExpertRating(List.of(0.9, 0.4, 0.5, 0.6, 0.7, 0.8, 0.6));
        inputData.setConnectionInputData(connectionInputData);
        List<ElementInputData> elementInputData = List.of(
                new ElementInputData(381, List.of(4, 3, 1, 8, 2, 6, 4)),
                new ElementInputData(277, List.of(5, 6, 8, 1, 4, 6, 6)),
                new ElementInputData(189, List.of(4, 8, 9, 9, 4, 9, 7))
        );
        inputData.setElementData(elementInputData);
    }

    @Override
    public void run(String... args) throws Exception {
        GeneralInputData generalInputData = getData();
        GeneralOutputData outputData = mathProcessor.getResult(generalInputData);
        log.info("Output {}",outputData);
        System.out.printf("Вагові коефіцієнти у системі %s%n",outputData.getWeightingCoefficients());
        List<SystemOutputData> systemOutputData = outputData.getSystems();
        for (int i=0 ; i < systemOutputData.size();i++){
            SystemOutputData system = systemOutputData.get(i);
            System.out.printf("Складність у системі %s : %s%n",i+1,system.getSystemComplexity());
            System.out.printf("Номер елементів %s%n",system.getNumOfElem());
            System.out.printf("Відносна складність зв'язків %s%n",system.getConnectionOutputData().getRelativeComplexityOfConnections());
            System.out.printf("Відносний номер зв'язків %s%n",system.getConnectionOutputData().getRelativeNumOfConnections());
            System.out.printf("Оцінка складності %s%n",system.getElementData().stream().map(
                    ElementOutputData::getAssessmentOfComplexity).toList());
        }

    }
}
