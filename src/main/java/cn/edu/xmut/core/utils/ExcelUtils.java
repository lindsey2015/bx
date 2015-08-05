package cn.edu.xmut.core.utils;

import cn.edu.xmut.modules.bdinfo.bean.BdInfo;
import cn.edu.xmut.modules.insured.bean.InsuredUser;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lelaw on 6/13/2015.
 */
public class ExcelUtils {
    private static final Logger LOG = LoggerFactory
            .getLogger(ExcelUtils.class);

    public static List<InsuredUser> excel2InsuredUserList(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(inputStream);
        } catch (IOException e) {
            LOG.info("Failed to get uploaded excel.", e);
        } catch (BiffException e) {
            LOG.info("Exception when reading uploaded excel.", e);
        }

        Sheet sheet = workbook.getSheet(0); // 获取第一个sheet
        int rows = sheet.getRows(); //获取总行号
        List<InsuredUser> insuredUserList = new ArrayList<InsuredUser>();

        for (int i = 1; i < rows; i++) {
            String name = sheet.getCell(0, i).getContents(); // 获得第i行第1列信息

            String identityType = sheet.getCell(1, i).getContents();
            String identity = sheet.getCell(2, i).getContents().trim();
            String occupationType = sheet.getCell(3, i).getContents();
            String birthday = sheet.getCell(4, i).getContents();

            if (StringUtils.isEmpty(birthday)) {
                birthday = setBirthday(identityType, identity);
            }

            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(identityType)
                    && StringUtils.isNotBlank(identity)) {
                InsuredUser insuredUser = new InsuredUser();
                insuredUser.setName(name);
                insuredUser.setIdentityType(Integer.parseInt(identityType));
                insuredUser.setIdentity(identity);
                if (StringUtils.isNotBlank(occupationType.trim())) {
                    insuredUser.setOccupationType(Integer.parseInt(occupationType));
                } else {
                    insuredUser.setOccupationType(1);
                }
                insuredUser.setBirthday(birthday);
                insuredUserList.add(insuredUser);
            }

        }
        return insuredUserList;
    }

    private static String setBirthday(String identityType, String identity) {
        if ("1".equals(identityType)
                && StringUtils.isNotBlank(identity)) {
            if (identity.matches("\\d{17}[a-zA-Z0-9]")) {
                return identity.substring(6, 14);
            } else if (identity.matches("\\d{15}")) {
                return "19" + identity.substring(6, 12);
            }
        }
        return "";
    }

    public static void exportToExcel(String file, List<BdInfo> list) throws IOException {
        WritableWorkbook wwb = Workbook.createWorkbook(new File(file));
        if (wwb != null) {
            //创建一个可写入的工作表
            //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
            WritableSheet ws = wwb.createSheet("sheet1", 0);
            int colIndex = 0;
            try {
                ws.addCell(new Label(colIndex++, 0, "产品名称"));
                ws.addCell(new Label(colIndex++, 0, "年龄段"));
                ws.addCell(new Label(colIndex++, 0, "参保人数"));
                ws.addCell(new Label(colIndex++, 0, "起保日期"));
                ws.addCell(new Label(colIndex++, 0, "保障期限"));
                ws.addCell(new Label(colIndex++, 0, "保险单号"));
                ws.addCell(new Label(colIndex++, 0, "保单号"));
                ws.addCell(new Label(colIndex++, 0, "总价格"));
            } catch (RowsExceededException e) {
                LOG.info("Failed to write header to excel.", e);
            } catch (WriteException e) {
                LOG.info("Failed to write header to excel.", e);
            }

            for (int i = 1; i < (list.size() + 1); i++) {
                colIndex = 0;
                BdInfo bdInfo = list.get(i - 1);
                try {
                    ws.addCell(new Label(colIndex++, i, bdInfo.getProduct().getName()));
                    ws.addCell(new Label(colIndex++, i, bdInfo.getAgeGroup()));
                    ws.addCell(new Number(colIndex++, i, bdInfo.getNums()));
                    ws.addCell(new Label(colIndex++, i, bdInfo.getStartDay()));
                    ws.addCell(new Number(colIndex++, i, bdInfo.getDays()));
                    ws.addCell(new Label(colIndex++, i, bdInfo.getTbNo()));
                    ws.addCell(new Label(colIndex++, i, bdInfo.getBdNo()));
                    ws.addCell(new Number(colIndex++, i, bdInfo.getTotal()));
                } catch (RowsExceededException e) {
                    LOG.info("Failed to write row to excel.", e);
                } catch (WriteException e) {
                    LOG.info("Failed to write to excel.", e);
                }
            }

            try {
                wwb.write();
                wwb.close();
            } catch (IOException e) {
                LOG.info("Failed to close excel.", e);
            } catch (WriteException e) {
                LOG.info("Failed to write to excel.", e);
            }
        }
    }
}
