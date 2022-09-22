package com.example.lab2;

import com.example.lab2.model.ParallelConnectionNode;
import com.example.lab2.model.SequenceConnectionNode;
import com.example.lab2.model.SimpleNode;
import com.example.lab2.model.SystemNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Lab2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SystemNode node = SequenceConnectionNode.of(
                ParallelConnectionNode.of(
                        SequenceConnectionNode.of(0.79, 0.83),
                        SequenceConnectionNode.of(0.91, 0.56),
                        SimpleNode.of(0.33)),
                ParallelConnectionNode.of(0.85, 0.75, 0.65, 0.55),
                ParallelConnectionNode.of(0.63, 0.83));
        System.out.printf("System in toString %s%n",node);
        System.out.println("System in graphical");
        System.out.print(RenderUtil.render(node," "));
        double reliability =CalculateUtil.calcReliability(node);
        System.out.printf("General reliability for system is {%.2f}%n",reliability);
    }
}
