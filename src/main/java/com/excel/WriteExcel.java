package com.excel;


        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Map;
        import java.util.concurrent.atomic.AtomicInteger;

        import com.alibaba.fastjson.JSONObject;
        import com.commonFunction;
        import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;
        import org.apache.poi.ss.usermodel.Workbook;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    private static final String strLabel1= "stepName";
    private static final String strLabel2= "stepDescription";
    private static final String strLabel3= "severity";
    private static final String strLabel4= "testRlt";
    private static final String strLabel5= "testData";
    private static final String strLabel6= "expRlt";
    private static final String strLabel7= "actRlt";
    private static final String strLabel8= "exeStartTime";



    public static void writeExcel(String reportName,List<Map> dataList, int columnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = columnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            // sheet sheet = workBook.getSheetAt(1);
            commonFunction obj = new commonFunction();
            Sheet sheet= workBook.createSheet(reportName + obj.getCurrentTime("yyyy-MM-dd HHmmss"));
            sheet.setColumnWidth(0, 40 * 256);
            sheet.setColumnWidth(1, 60 * 256);
            sheet.setColumnWidth(2, 15 * 256);
            sheet.setColumnWidth(3, 15 * 256);
            for(int k = 4; k<columnCount; k++){
                sheet.setColumnWidth(k, 30 * 256);
            }

            Row row0 = sheet.createRow(0 );
            buildColumnLabel( row0);

            /**
             * 删除原有数据，除了属性列
             */
//            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
//            System.out.println("原始数据总行数，除属性列：" + rowNumber);
//            for (int i = 1; i <= rowNumber; i++) {
//                Row row = sheet.getRow(i);
//                sheet.removeRow(row);
//            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
//            out =  new FileOutputStream(finalXlsxPath);
//            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            AtomicInteger j=new AtomicInteger(0);

//
//            dataList.stream().forEach(data->{
//                // 创建一行：从第二行开始，跳过属性列
//                Row row = sheet.createRow(j.addAndGet(1) );
//                // 得到要插入的每一条记录
//                buildColumn(columnNumCount, data, row);
//            });

            for (int k = 0; k < dataList.size(); k++) {
                JSONObject data = (JSONObject) dataList.get(k);
                Row row = sheet.createRow(j.addAndGet(1) );
                buildColumn(columnNumCount, data, row);
            }
//            for (int j = 0; j < dataList.size(); j++) {
//                // 创建一行：从第二行开始，跳过属性列
//                Row row = sheet.createRow(j + 1);
//                // 得到要插入的每一条记录
//                Map dataMap = dataList.get(j);
//                String strVal1 = dataMap.get(strLabel1).toString();
//                String strVal2 = dataMap.get(strLabel2).toString();
//                String strVal3 = dataMap.get(strLabel3).toString();
//                for (int k = 0; k <= columnNumCount; k++) {
//                    // 在一行内循环
//                    Cell first = row.createCell(0);
//                    first.setCellValue(strVal1);
//
//                    Cell second = row.createCell(1);
//                    second.setCellValue(strVal2);
//
//                    Cell third = row.createCell(2);
//                    third.setCellValue(strVal3);
//                }
//            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    private static void buildColumn(int columnNumCount, Map data, Row row) {
        String strVal1 = null;
        String strVal2 = null;
        String strVal3 = null;
        String strVal4 = null;
        String strVal5 = null;
        String strVal6 = null;
        String strVal7 = null;
        String strVal8 = null;


        if(data.get(strLabel1) != null){
            strVal1 = data.get(strLabel1).toString();
        }
        if(data.get(strLabel2) != null){
            strVal2 = data.get(strLabel2).toString();
        }
        if(data.get(strLabel3) != null){
            strVal3 = data.get(strLabel3).toString();
        }
        if(data.get(strLabel4) != null){
            strVal4 = data.get(strLabel4).toString();
        }
        if(data.get(strLabel5) != null){
            strVal5 = data.get(strLabel5).toString();
        }
        if(data.get(strLabel6) != null){
            strVal6 = data.get(strLabel6).toString();
        }
        if(data.get(strLabel7) != null){
            strVal7 = data.get(strLabel7).toString();
        }
        if(data.get(strLabel8) != null){
            strVal8 = data.get(strLabel8).toString();
        }


//        String strVal2 = data.get(strLabel2).toString();
//        String strVal3 = data.get(strLabel3).toString();
//        String strVal4 = data.get(strLabel4).toString();
//        String strVal5 = data.get(strLabel5).toString();
//        String strVal6 = data.get(strLabel6).toString();
//        String strVal7 = data.get(strLabel7).toString();
//        String strVal8 = data.get(strLabel8).toString();
        for (int k = 0; k < columnNumCount; k++) {
            // 在一行内循环 ( ????  )
            Cell first = row.createCell(0);
            first.setCellValue(strVal1);

            Cell second = row.createCell(1);
            second.setCellValue(strVal2);

            Cell third = row.createCell(2);
            third.setCellValue(strVal3);

            Cell fourth = row.createCell(3);
            fourth.setCellValue(strVal4);

            Cell fifth = row.createCell(4);
            fifth.setCellValue(strVal5);

            Cell sixth = row.createCell(5);
            sixth.setCellValue(strVal6);

            Cell seven = row.createCell(6);
            seven.setCellValue(strVal7);

            Cell eight = row.createCell(7);
            eight.setCellValue(strVal8);
        }
    }

    private static void buildColumnLabel( Row row) {

            // 在一行内循环 ( ????  )
            Cell first = row.createCell(0);
            first.setCellValue(strLabel1);

            Cell second = row.createCell(1);
            second.setCellValue(strLabel2);

            Cell third = row.createCell(2);
            third.setCellValue(strLabel3);

            Cell fourth = row.createCell(3);
            fourth.setCellValue(strLabel4);

            Cell fifth = row.createCell(4);
            fifth.setCellValue(strLabel5);

            Cell sixth = row.createCell(5);
            sixth.setCellValue(strLabel6);

            Cell seven = row.createCell(6);
            seven.setCellValue(strLabel7);

            Cell eight = row.createCell(7);
            eight.setCellValue(strLabel8);

    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}