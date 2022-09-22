package com.example.lab1.model.output;

import com.example.lab1.model.output.ConnectionOutputData;
import com.example.lab1.model.output.ElementOutputData;
import lombok.Data;

import java.util.List;

@Data
public class SystemOutputData {
    List<ElementOutputData> elementData;
    ConnectionOutputData connectionOutputData;
    int numOfElem;
    double systemComplexity;
}
