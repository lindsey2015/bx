package cn.edu.xmut.core.utils;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by lelaw on 6/13/2015.
 */
public class ExcelUtilsTest {
    @Test
    public void testExcel2InsuredUserList() {
        try {
            FileInputStream f = new FileInputStream("D:\\source\\bx\\test-files\\20150612024139391634.xls");
            ExcelUtils.excel2InsuredUserList(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
