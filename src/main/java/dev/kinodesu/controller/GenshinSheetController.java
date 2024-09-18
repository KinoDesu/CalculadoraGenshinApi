package dev.kinodesu.controller;

import dev.kinodesu.model.response.calculator.CalculatorResponse;
import dev.kinodesu.service.GenshinSheetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Tag(name = "Genshin File Downloader")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class GenshinSheetController {
    private final GenshinSheetService genshinSheetService;

    @PostMapping
    public ResponseEntity<byte[]> downloadExcel(@RequestBody CalculatorResponse calculatorResponse) throws IOException {
        ByteArrayInputStream excelFile = genshinSheetService.generateExcel(calculatorResponse);

        // Configurar os headers para download de arquivo
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dados.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelFile.readAllBytes());
    }
}
