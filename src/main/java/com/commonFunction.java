package com;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xProject.testResult.actionResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.excel.WriteExcel.getWorkbok;


public class commonFunction {
    private static String testReportPath = "src\\main\\resources\\GlobalConfigInfo\\TestReport.xls";
    final String EXCEL_XLS = "xls";
    final String EXCEL_XLSX = "xlsx";
    static final String strLabel1 = "Case Name";
    static final String strLabel2 = "Function Points";
    static final String strLabel3 = "Status";
    static final String strLabel4 = "API Name";
    static final String strLabel5 = "Test Scenario Type";
    static final String strLabel6 = "Pass";
    static final String strLabel7 = "Fail";
    static final String strLabel8 = "TC No.";
    static final String strLabel9 = "Pass Rate";


    public static String toFormatDate(String strDate){
        try{
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(strDate);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }



    public static int getRandomIntInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    public static String getRandomStringInRange(int min, int max) {
        Random r = new Random();
        return String.valueOf(r.ints(min, (max + 1)).limit(1).findFirst().getAsInt());
    }

    //当前时间 unix时间戳
    public static Long getCurrentTimeUnix() {
        Long time = System.currentTimeMillis() ;
        return time;
    }

    //获取指定格式的时间: "yyyy-MM-dd HH:mm:ss"
    public static String getCurrentTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String currenttime = sdf.format(new Date());
        return currenttime;
    }

    public static String getCurrentTimePlus5(String pattern,int i) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date afterDate = new Date(now.getTime() + i*60*1000);
        String getCurrentTimePlus5 = sdf.format(afterDate);
        return getCurrentTimePlus5;
    }
    public static String getCurrentTimePlusDay(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String currenttime = sdf.format(new Date());
        return currenttime;
    }

    public static int getCurrentDate() {
       Calendar cal = Calendar.getInstance();
       int y = cal.get(Calendar.YEAR);
       return y;
    }

    /**
     * Read json file, return with json string
     *
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get process by name, and stop the process
     * Emily 2021-10-12
     */
    public static void confirmSingleProcess(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("The prefix is null, please check it!");
        }
        //生命文件读取流
        BufferedReader out = null;
        BufferedReader br = null;

        try {
            ProcessBuilder pb = new ProcessBuilder("tasklist");
            Process p = pb.start();

            out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
            br = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));

            //创建集合，存放进程pid
            String ostr;
            List<String> list = new ArrayList<>();

            while ((ostr = out.readLine()) != null) {
                //将读取的进程信息存入集合
                list.add(ostr);
            }

            // 遍历所有进程
            for (int i = 3; i < list.size(); i++) {
                //必须写死， 截取长度，因为是固定的
                String process = list.get(i).substring(0, 25).trim(); //进程名
                String pid = list.get(i).substring(25, 35).trim(); // 进程号
                //匹配指定的进程名，若匹配到，则立即杀死
                if (process.startsWith(prefix)) {
                    Runtime.getRuntime().exec("taskkill /F /PID " + pid);
                }
            }

            //若有错误信息，即打印日志
            String estr = br.readLine();
            if (estr != null) {
                //logger.error (estr);
                System.out.println(estr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关流
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 提取字符串中的数字
     */
    public static String getIntegers(String str) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    /**
     * 休眠指定时间，单位为秒
     */
    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //根据key value 查找json Array 中符合条件的json object。
    public static JSONObject filterJsonArray(JSONArray ja, String key, String value) {
        JSONObject returnJO = null;
        for (Object obj : ja) {
            JSONObject jsonObject = JSONObject.parseObject(obj.toString());
            if (jsonObject.getString(key).equals(value)) {
                returnJO = jsonObject;
                break;
            }
        }
        return returnJO;
    }

    /**
     * Emily 2021-7-14 通过timezone id 获取指定时区的时间，
     *
     * @param timeZoneID Asia/Shanghai , UTC
     * @param dateFormat yyyy-MM-dd HH:mm:ss
     * @return https://blog.csdn.net/caicai1377/article/details/115873822 可用的timezone id 可以参考此链接
     */
    public static String getCurrentTime(String timeZoneID, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneID));    //指定时区
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 将string 类型的价格数据转换为四舍五入之后的价格double类型
     *
     * @param stringDecimal
     * @return
     */
    public static double convertDouble(String stringDecimal) {
        double f = Double.valueOf(stringDecimal);
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return f1;
    }

    /**
     * 比较AB 两个json 对象，b对象中的key 以及value 在a 对象中存在，且值相等
     */
    private boolean compareJsonObject(JSONObject ja1, JSONObject ja2) {
        boolean isSuccess = false;
        String msg = "";
        Set<String> keySets = ja2.keySet();
        for (String key : keySets)
            if (ja2.getString(key).equals(ja1.getString(key))) isSuccess = true;
            else {
                msg = msg + "Value of key: " + key + " is not as expected. ";
            }
        return isSuccess;
    }


    public int getCaseSize(String configPath, String path) {
        return (int) JSONPath.read(readJsonFile(configPath), path);
    }


    public JSONArray getCaseByPath(String configPath, String path) {
        return (JSONArray) JSONPath.read(readJsonFile(configPath), path);
    }


    public static boolean saveJsonFile(String orderID, String jsonFilePath) {
        boolean result = false;
        BufferedWriter bufferWriter = null;
        try {
            File file = new File(jsonFilePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            bufferWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
            bufferWriter.write(orderID);

            bufferWriter.newLine();
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferWriter) {
                try {
                    bufferWriter.close();
                } catch (IOException e) {
                    System.out.println("数据保存到JSON文件后，关闭流异常" + e);
                }
            }
        }
        return result;
    }

    public static boolean cleanJsonFile(String jsonFilePath) {
        boolean result = false;
        BufferedWriter bufferWriter = null;
        try {
            File file = new File(jsonFilePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            bufferWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));
            bufferWriter.write("");
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferWriter) {
                try {
                    bufferWriter.close();
                } catch (IOException e) {
                    System.out.println("数据保存到JSON文件后，关闭流异常" + e);
                }
            }
        }
        return result;
    }


    public static String[] readJsonFileArray(String fileName) {
        String jsonString = "";
        File jsonFile = new File(fileName);
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "UTF-8");
            int ch = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                stringBuffer.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonString = stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] words = jsonString.split("\r\n");
        return words;
    }

    public static List<Map<String, Object>> TestResultDataList(List testData, String strCaseName, String strFunctionPoint, actionResult strStatus) {
//        List<Map<String,Object>> testData=new ArrayList<Map<String,Object>>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("Case Name", strCaseName);
        data.put("Function Points", strFunctionPoint);
        data.put("Status", strStatus);
        testData.add(data);
        return testData;
    }


    public static void writeTestReport(List<Map<String, Object>> dataList, String sheetName, String testReportPath) {
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = dataList.size();
            // 读取Excel文档
            File finalXlsxFile = new File(testReportPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheet(sheetName);
//
//            // 插入空行
//            for (int y = 0; y < 2; y++) {
//                int x= sheet.getLastRowNum();
//                Row row = sheet.createRow(x + 1);
//            }

            /**
             * 往Excel中写新数据
             */
//            AtomicInteger j=new AtomicInteger(0);

            dataList.stream().forEach(data -> {
                int j = sheet.getLastRowNum();
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
//                buildColumn( data, row);
                String strVal1 = data.get(strLabel1).toString();
                String strVal2 = data.get(strLabel2).toString();
                String strVal3 = data.get(strLabel3).toString();

                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(strVal1);

                Cell second = row.createCell(1);
                second.setCellValue(strVal2);

                Cell third = row.createCell(2);
                third.setCellValue(strVal3);
            });
            out = new FileOutputStream(testReportPath);
            workBook.write(out);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    public static void writeSummaryReport(List<Map<String, Object>> dataList, String sheetName, String testReportPath) {
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = dataList.size();
            // 读取Excel文档
            File finalXlsxFile = new File(testReportPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheet(sheetName);

            dataList.stream().forEach(data -> {
                int j = sheet.getLastRowNum();
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
//                buildColumn( data, row);
                String strVal4 = data.get(strLabel4).toString();
                String strVal5 = data.get(strLabel5).toString();
                String strVal6 = data.get(strLabel6).toString();
                String strVal7 = data.get(strLabel7).toString();
                String strVal8 = data.get(strLabel8).toString();
                String strVal9 = data.get(strLabel9).toString();

                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(strVal4);

                Cell second = row.createCell(1);
                second.setCellValue(strVal5);

                Cell third = row.createCell(2);
                third.setCellValue(strVal6);

                Cell fourth = row.createCell(3);
                fourth.setCellValue(strVal7);

                Cell fifth = row.createCell(4);
                fifth.setCellValue(strVal8);

                Cell sixth = row.createCell(5);
                sixth.setCellValue(strVal9);

            });
            out = new FileOutputStream(testReportPath);
            workBook.write(out);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    public static void writeSummaryDetailReport(List<Map<String, Object>> dataList, String sheetName, String testReportPath) {
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = dataList.size();
            // 读取Excel文档
            File finalXlsxFile = new File(testReportPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheet(sheetName);
            int j = sheet.getLastRowNum();
            sheet.createRow(j + 1);
            sheet.createRow(j + 2);

            dataList.stream().forEach(data -> {
                int y = sheet.getLastRowNum();
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(y + 1);
                // 得到要插入的每一条记录
//                buildColumn( data, row);
                String strVal1 = data.get(strLabel1).toString();
                String strVal2 = data.get(strLabel2).toString();
                String strVal3 = data.get(strLabel3).toString();

                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(strVal1);

                Cell second = row.createCell(1);
                second.setCellValue(strVal2);

                Cell third = row.createCell(2);
                third.setCellValue(strVal3);
            });
            out = new FileOutputStream(testReportPath);
            workBook.write(out);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    public void setCellFontColor(String testReportPath, String sheetName, int rowIndex, int cellIndex, short fontColor) throws IOException {
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(testReportPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheet(sheetName);
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                cell = row.createCell(cellIndex);
            }
            CellStyle ztStyle = workBook.createCellStyle();
            // 创建字体对象
            Font ztFont = workBook.createFont();
            ztFont.setBold(true); // 设置字体为斜体字
            ztFont.setColor(fontColor); // 将字体设置为“红色”
//            ztFont.setFontHeightInPoints((short)10); // 将字体大小设置为18px
//            ztFont.setFontName("Arial"); // 将“华文行楷”字体应用到当前单元格上
//            ztFont.setUnderline(Font.U_DOUBLE); // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
//          ztFont.setStrikeout(true); // 是否添加删除线
            ztStyle.setBorderBottom(BorderStyle.THIN);
            ztStyle.setBottomBorderColor(fontColor);
            ztStyle.setBorderLeft(BorderStyle.THIN);
            ztStyle.setLeftBorderColor(fontColor);
            ztStyle.setBorderRight(BorderStyle.THIN);
            ztStyle.setRightBorderColor(fontColor);
            ztStyle.setBorderTop(BorderStyle.THIN);
            ztStyle.setTopBorderColor(fontColor);

            ztStyle.setFont(ztFont); // 将字体应用到样式上面
            cell.setCellStyle(ztStyle); // 样式应用到该单元格上

            out = new FileOutputStream(testReportPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCellBorder(String testReportPath, String sheetName, int rowIndex, int cellIndex, short fontColor) throws IOException {
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(testReportPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheet(sheetName);
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                cell = row.createCell(cellIndex);
            }
            CellStyle ztStyle = workBook.createCellStyle();

            ztStyle.setBorderBottom(BorderStyle.THIN);
            ztStyle.setBottomBorderColor(fontColor);
            ztStyle.setBorderLeft(BorderStyle.THIN);
            ztStyle.setLeftBorderColor(fontColor);
            ztStyle.setBorderRight(BorderStyle.THIN);
            ztStyle.setRightBorderColor(fontColor);
            ztStyle.setBorderTop(BorderStyle.THIN);
            ztStyle.setTopBorderColor(fontColor);

            cell.setCellStyle(ztStyle); // 样式应用到该单元格上
            sheet.setDisplayGridlines(false);//隐藏Excel网格线,默认值为true
            out = new FileOutputStream(testReportPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSubTitle(String testReportPath, String sheetName, int rowIndex, int cellIndex, short fontColor, String auto_execution_summary, boolean iMergeCellTrue) throws IOException {
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(testReportPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheet(sheetName);
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            row.setHeightInPoints(16);//设置行的高度是50个点

            Cell cell = row.getCell(cellIndex);
            if (cell == null) {
                cell = row.createCell(cellIndex);
            }
            cell.setCellValue(auto_execution_summary);
            if (iMergeCellTrue) {
                CellRangeAddress region = new CellRangeAddress(rowIndex, rowIndex, 0, 5);
                sheet.addMergedRegion(region);
            }


            CellStyle ztStyle = workBook.createCellStyle();
            Font ztFont = workBook.createFont();
            ztFont.setBold(true); // 设置字体为斜体字
            ztFont.setColor(fontColor); // 将字体设置为“红色”
            ztFont.setFontHeightInPoints((short) 11); // 将字体大小设置为18px

//            ztFont.setFontName("Arial"); // 将“华文行楷”字体应用到当前单元格上
//            ztFont.setUnderline(Font.U_DOUBLE); // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
//          ztFont.setStrikeout(true); // 是否添加删除线
//            ztStyle.setAlignment(HorizontalAlignment.CENTER);
            ztStyle.setFillForegroundColor((short) 23);//设置图案颜色
//            ztStyle.setFillBackgroundColor((short)23);//设置图案背景色
            ztStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置图案样式
            ztStyle.setFont(ztFont); // 将字体应用到样式上面

            cell.setCellStyle(ztStyle); // 样式应用到该单元格上

            out = new FileOutputStream(testReportPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
