package dev.kinodesu.service;

import dev.kinodesu.model.response.calculator.CalculatorResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public interface GenshinSheetService {
    ByteArrayInputStream generateExcel(CalculatorResponse calculatorResponse) throws IOException;
}
