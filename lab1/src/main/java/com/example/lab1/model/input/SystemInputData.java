package com.example.lab1.model.input;

import com.example.lab1.model.input.ConnectionInputData;
import com.example.lab1.model.input.ElementInputData;
import lombok.Data;

import java.util.List;

@Data
public class SystemInputData {
    List<ElementInputData> elementData;
    ConnectionInputData connectionInputData;
}
