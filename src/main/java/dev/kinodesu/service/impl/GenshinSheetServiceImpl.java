package dev.kinodesu.service.impl;

import dev.kinodesu.model.response.calculator.CalculatorResponse;
import dev.kinodesu.model.response.calculator.data.CalculatorItem;
import dev.kinodesu.model.response.calculator.data.OverallConsume;
import dev.kinodesu.model.response.calculator.item.ConsumeItem;
import dev.kinodesu.model.response.calculator.material_consume.item.CalculatorCharacter;
import dev.kinodesu.service.GenshinSheetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GenshinSheetServiceImpl implements GenshinSheetService {

    @Override
    public ByteArrayInputStream generateExcel(CalculatorResponse calculatorResponse) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {

            List<CalculatorItem> itemList = calculatorResponse.getData().getItems();

            for (CalculatorItem calculatorItem : itemList) {

                CalculatorCharacter character =
                        calculatorResponse.getData().getOverall_material_consume().getAvatar_consume().getFirst().getAvatars().getFirst();

                Sheet sheet = workbook.createSheet(String.format("Upgrade %s", character.getName()));

                Font font = sheet.getWorkbook().createFont();
                font.setBold(true);
                font.setFontHeightInPoints((short) 15);

                int rowNumber = 1;

                tableHeader(sheet, font);
                writeBaseCharacterInfo(sheet, character, rowNumber++);

                Cell materialNameCell;
                Cell OwnedMaterialNumberCell;
                Cell NeededMaterialNumberCell;
                Cell lackMaterialNumberCell;

                List<ConsumeItem> consumeItemList = calculatorItem.getAvatar_consume();

                Row row;
                int lastCellNum = 1;

                for (ConsumeItem consumeItem : consumeItemList) {

                    CellStyle style = workbook.createCellStyle();
                    style.setAlignment(HorizontalAlignment.CENTER);
                    style.setVerticalAlignment(VerticalAlignment.CENTER);
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style.setBorderTop(BorderStyle.THIN);
                    style.setBorderRight(BorderStyle.THIN);
                    style.setBorderBottom(BorderStyle.THIN);
                    style.setBorderLeft(BorderStyle.THIN);

                    int cellNumber = 1;
                    row = sheet.getRow(rowNumber);
                    if (row == null) {
                        row = sheet.createRow(rowNumber++);
                    } else {
                        rowNumber++;
                    }

                    row.setHeight((short) (50 * 20));

                    OverallConsume overallConsume =
                            getOverallConsumeItem(calculatorResponse.getData().getOverall_consume(),
                                    consumeItem.getId()).orElse(new OverallConsume());

                    int overallNumber = overallConsume.getNum();
                    int overallLackNumber = overallConsume.getLack_num() * -1;

                    int totalInAccount = overallNumber + overallLackNumber;
                    int lackNumber = consumeItem.getNum() - totalInAccount;
                    if (lackNumber <= 0) {
                        lackNumber = 0;
                        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 234, 211), null));
                    } else {
                        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(244, 204, 204), null));
                    }


                    materialNameCell = row.createCell(cellNumber++);
                    materialNameCell.setCellValue(consumeItem.getName());

                    String materialImageUrl = consumeItem.getIcon_url();
                    addImage(materialImageUrl, rowNumber - 1, cellNumber++, sheet);

                    OwnedMaterialNumberCell = row.createCell(cellNumber++);
                    OwnedMaterialNumberCell.setCellValue(totalInAccount);

                    NeededMaterialNumberCell = row.createCell(cellNumber++);
                    NeededMaterialNumberCell.setCellValue(consumeItem.getNum());

                    lackMaterialNumberCell = row.createCell(cellNumber);
                    lackMaterialNumberCell.setCellValue(lackNumber);

                    lastCellNum = cellNumber;

                    row.forEach(cell -> {
                        if (cell.getColumnIndex() > 0) cell.setCellStyle(style);
                    });

                    for (int i = 0; i < cellNumber; i++) {
                        sheet.autoSizeColumn(i);
                        if (i == 0) {
                            sheet.setColumnWidth(0, 32 * 256);
                        }
                    }

                }
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, lastCellNum));
                sheet.addMergedRegion(new CellRangeAddress(2, rowNumber - 1, 0, 0));
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    private void writeBaseCharacterInfo(Sheet sheet, CalculatorCharacter character, int rowNumber) {

        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 242, 204), null));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        Row row = sheet.createRow(rowNumber++);
        Cell characterNameCell = row.createCell(0);
        characterNameCell.setCellValue(String.format("%s (%s-%s)", character.getName(),
                character.getAvatar_current_level(), character.getAvatar_target_level()));
        characterNameCell.setCellStyle(style);
        String caracterImageUrl = character.getIcon();
        addImage(caracterImageUrl, rowNumber, 0, sheet);
    }

    private void addImage(String imageUrl, int rowNumber, int columnNumber, Sheet sheet) {
        Row row = sheet.getRow(rowNumber);
        if (row == null) {
            row = sheet.createRow(rowNumber);
        }

        Cell imageCell = row.createCell(columnNumber);
        byte[] img = getImage(imageUrl);
        int inputImage = sheet.getWorkbook().addPicture(img, Workbook.PICTURE_TYPE_PNG);

        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor imageAnchor = new XSSFClientAnchor();

        imageAnchor.setCol1(imageCell.getColumnIndex());
        imageAnchor.setCol2(imageCell.getColumnIndex() + 1);
        imageAnchor.setRow1(rowNumber);
        imageAnchor.setRow2(rowNumber + 1);
        imageAnchor.setDx1(0);
        imageAnchor.setDx2(0);
        imageAnchor.setDy1(0);
        imageAnchor.setDy2(0);

        imageAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

        drawing.createPicture(imageAnchor, inputImage);

        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 242, 204), null));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        imageCell.setCellStyle(style);
    }

    private static void tableHeader(Sheet sheet, Font font) {

        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(189, 189, 189), null));
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("PERSONAGEM/ITEM");
        headerRow.createCell(1).setCellValue("MATERIAIS");
        headerRow.createCell(2).setCellValue("IMAGEM");
        headerRow.createCell(3).setCellValue("TENHO");
        headerRow.createCell(4).setCellValue("PRECISO");
        headerRow.createCell(5).setCellValue("FALTAM");

        headerRow.forEach(cell -> cell.setCellStyle(headerStyle));
        headerRow.setHeight((short) (50 * 20));
    }

    private Optional<OverallConsume> getOverallConsumeItem(List<OverallConsume> overallConsumeList, long materialId) {


        return overallConsumeList.stream().filter(item -> item.getId() == materialId).findFirst();
    }

    private byte[] getImage(String imageUrl) {
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(imageUrl))
                    .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            // Retorna a imagem como byte array
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
