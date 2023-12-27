package com.excel;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.stream.Collectors;

        import jxl.Sheet;
        import jxl.Workbook;
        import jxl.read.biff.BiffException;
        import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadExcel {
    /**
     * @Description
     * @param dataFile
     */
    public void readWorkBookContent(String dataFile) {

        File file = new File(dataFile);
        List excelList =  readExcel(file);
        log.info("list中的数据打印出来");
        excelList.stream().forEach(exce->{
            List listExce = (List) exce;
            Object exceStr=listExce.stream().collect(Collectors.joining(","));
            log.info(exceStr.toString());
        });
    }

    /**
     * @
     * @param dataFile
     * @param sheetName
     */
    public List readSheetContent(String dataFile, String sheetName) {
        File file = new File(dataFile);
        int sheetIndex = findSheetName(file,sheetName);
        List excelList = readSheet(file,sheetIndex );

        log.info("list中的数据打印出来");
        excelList.stream().forEach(exce->{
            List listExce = (List) exce;
            Object excelStr =listExce.stream().collect(Collectors.joining("\t"));
            log.info(excelStr.toString());
        });
        return excelList;
    }

    /**
     * @Description 返回SHEET页对应的INDEX
     * @param file
     * @param sheetName
     * @return
     */
    public Integer findSheetName(File file, String sheetName){
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);

            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for(int i=0; i < sheet_size; i++){
                Sheet sheet = wb.getSheet(i);
                String shName = sheet.getName();
                if(shName.equals(sheetName)){
                    return  i;
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  说明：去读Excel的方法readExcel，该方法的入口参数为一个File对象
     * @param file
     * @return
     */
    public List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            List<List> outerList=new ArrayList<List>();
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {

                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
//                    System.out.println();
                }

            }
            return outerList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 说明：去读Excel sheet的方法readSheet，该方法的入口参数为 File，sheet index 对象
     * @param file
     * @param index: sheet index(0开始)
     * @return
     */
    public List readSheet(File file, int index) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            List<List> outerList=new ArrayList<List>();
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(index);

            // sheet.getRows()返回该页的总行数
            for (int i = 0; i < sheet.getRows(); i++) {
                List innerList=new ArrayList();
                // sheet.getColumns()返回该页的总列数
                for (int j = 0; j < sheet.getColumns(); j++) {
                    String cellInfo = sheet.getCell(j, i).getContents();
                    if(cellInfo.isEmpty()){
                        continue;
                    }
                    innerList.add(cellInfo);
//                        System.out.print(cellInfo);
                }
                outerList.add(i, innerList);
//                    System.out.println();
            }
            return outerList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 说明：去读Excel sheet的方法readSheet，该方法的入口参数为 File，sheet index， column num 对象
     * @param file
     * @param index
     * @param iColumn
     * @return
     */
    public List readColumn(File file, int index, int iColumn) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            List<List> outerList=new ArrayList<List>();
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(index);
            // sheet.getRows()返回该页的总行数
            for (int i = 0; i < sheet.getRows(); i++) {
                List innerList=new ArrayList();
                // sheet.getColumns()返回该页的总列数

                String cellInfo = sheet.getCell(iColumn, i).getContents();
                if(cellInfo.isEmpty()){
                    continue;
                }
                innerList.add(cellInfo);
                System.out.print(cellInfo);

                outerList.add(i, innerList);
                System.out.println();
            }
            return outerList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List readRow(File file, int index, int iRow) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            List<List> outerList=new ArrayList<List>();
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(index);
            // sheet.getRows()返回该页的总行数

            List innerList=new ArrayList();
            // sheet.getColumns()返回该页的总列数
            for (int j = 0; j < sheet.getColumns(); j++) {
                String cellInfo = sheet.getCell(j, iRow).getContents();
                if(cellInfo.isEmpty()){
                    continue;
                }
                innerList.add(cellInfo);
                System.out.print(cellInfo);
            }
            outerList.add(iRow, innerList);
            System.out.println();

            return outerList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}


