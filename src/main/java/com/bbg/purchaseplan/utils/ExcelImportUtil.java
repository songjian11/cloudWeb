package com.bbg.purchaseplan.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName:ExcelUtil excel导入 poi在超过10万数据时会内存溢出，后续改造
 * @Auther: wangyongchao
 * @Date: 2018/7/26 14:53
 * @Description: TODD
 * @Version:1.0
 */
public class ExcelImportUtil {


    /**
     * 读取excel
     * @param excel_url 文件路径
     * @param args 设置excel列数 格式:0,1,2
     * @return
     */
    public static ArrayList<ArrayList<String>> xlsxReader(String excel_url, int ... args){
        //读取xlsx文件
        XSSFWorkbook xssfWorkbook = null;
        //寻找目录读取文件
        File excelFile = new File(excel_url);
        InputStream is = null;
        try {
            is = new FileInputStream(excelFile);
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        if(xssfWorkbook==null){
            return null;
        }

        ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
        //遍历xlsx中的sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null){
                    continue;
                }
                ArrayList<String> curarr=new ArrayList<String>();
                for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
                    XSSFCell cell = xssfRow.getCell(args[columnNum]);

                    curarr.add( Trim_str(getCellValue(cell).toString()));
                }
                ans.add(curarr);
            }
        }
        return ans;


    }

    /**
     * 去掉非法字符
     * @param str
     * @return
     */
    private static String Trim_str(String str){
        if(str==null){
            return null;
        }
        return str.replaceAll("[\\s\\?]", "").replace("　", "");
    }

    /**
     * 判断数据类型 数字去掉小数点
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String cellvalue = "";
        Object inputValue = "";
        try {
            if (null != cell) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC: // 数字
                        if(DateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            cellvalue = new SimpleDateFormat("yyyyMMdd").format(date);
                        } else {
                            cellvalue = String.valueOf(cell.getNumericCellValue());
                            long longVal = Math.round(cell.getNumericCellValue());
                            if (Double.parseDouble(longVal + ".0") == cell.getNumericCellValue()){
                                inputValue = longVal;
                                cellvalue=inputValue.toString();
                            } else {
                                inputValue = cell.getNumericCellValue();
                                cellvalue=inputValue.toString();
                            }
                        }
                        break;
                    case Cell.CELL_TYPE_STRING: // 字符串
                        cellvalue = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN: // Boolean
                        cellvalue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA: // 公式
                        cellvalue = cell.getCellFormula();
                        break;
                    case Cell.CELL_TYPE_BLANK: // 空值
                        cellvalue = "";
                        break;
                    case Cell.CELL_TYPE_ERROR: // 故障
                        cellvalue = "";
                        break;
                    default:
                        cellvalue = "";
                        break;
                }
            }
        } catch(Exception ex) {
            cellvalue = "";
        }
        return cellvalue.trim();
    }
}
