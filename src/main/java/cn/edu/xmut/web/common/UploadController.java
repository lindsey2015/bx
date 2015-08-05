package cn.edu.xmut.web.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.edu.xmut.core.utils.ExcelUtils;
import cn.edu.xmut.modules.insured.bean.InsuredUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.filter.Filter;
import cn.edu.xmut.core.filter.PermissionTypeEnum;
import cn.edu.xmut.utils.FileUpload;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;


@Filter(permission = PermissionTypeEnum.LOGIN)
@Controller("commonUploadController")
@RequestMapping("upload")
public class UploadController {

    /**
     * @描述 上传excel
     * @url upload/excel.jhtml
     * @请求参数 MultipartFile File 上传文件名
     * return 例如    /aishou/upload/excels/20150205071859935316.xls
     * @作者 bob(傅文城)
     * @创建时间 2015年4月23日 下午7:19:21
     */
    @RequestMapping("/excel")
    public
    @ResponseBody
    JSONObject uploadExcel(MultipartFile file, HttpServletResponse response) throws IOException {
        String fileName = file.getOriginalFilename();
        int num = fileName.lastIndexOf(".");
        if (fileName.substring(num + 1).equals("xls")) {
            String realPath = UtilCtrl.getSession().getServletContext().getRealPath(FileUpload.DIR_EXCEL_UPLOAD);
            String finalName = FileUpload.Upload(file, realPath);
            String path = UtilCtrl.getSession().getServletContext().getContextPath();
            JSONObject json = new JSONObject();
            json.put("fileName", fileName);
            json.put("url", path + FileUpload.DIR_EXCEL_UPLOAD + finalName);
            response.setContentType("text/html; charset=utf-8");

            List<InsuredUser> insuredUserList = ExcelUtils.excel2InsuredUserList(file.getInputStream());
            json.put("insuredUserList", insuredUserList);
            return JsonTool.genSuccessMsg(json);
        } else {
            response.setContentType("text/html; charset=utf-8");
            return JsonTool.genErrorMsg("上传格式错误");
        }
    }

    /**
     * @描述 上传pdf
     * @url upload/pdf.jhtml
     * @请求参数 MultipartFile File 上传文件名
     * return 例如    /aishou/upload/pdfs/20150205071859935316.pdf
     * @作者 bob(傅文城)
     * @创建时间 2015年4月23日 下午7:19:21
     */
    @RequestMapping("/pdf")
    public
    @ResponseBody
    JSONObject uploadPdf(MultipartFile file, HttpServletResponse response) throws IOException {
        String fileName = file.getOriginalFilename();
        int num = fileName.lastIndexOf(".");
        if (fileName.substring(num + 1).equals("pdf") || fileName.substring(num + 1).equals("jpg")) {
            String realPath = UtilCtrl.getSession().getServletContext().getRealPath(FileUpload.DIR_PDF_UPLOAD);
            String finalName = FileUpload.Upload(file, realPath);
            String path = UtilCtrl.getSession().getServletContext().getContextPath();
            JSONObject json = new JSONObject();
            json.put("fileName", fileName);
            json.put("url", path + FileUpload.DIR_PDF_UPLOAD + finalName);
            response.setContentType("text/html; charset=utf-8");
            return JsonTool.genSuccessMsg(json);
        } else {
            response.setContentType("text/html; charset=utf-8");
            return JsonTool.genErrorMsg("上传格式错误");
        }
    }

    /**
     * @描述 上传doc
     * @url upload/doc.jhtml
     * @请求参数 MultipartFile File 上传文件名
     * return 例如    /aishou/upload/docs/20150205071859935316.doc
     * @作者 bob(傅文城)
     * @创建时间 2015年4月23日 下午7:19:21
     */
    @RequestMapping("/doc")
    public
    @ResponseBody
    JSONObject uploadDoc(MultipartFile file, HttpServletResponse response) throws IOException {
        String fileName = file.getOriginalFilename();
        int num = fileName.lastIndexOf(".");
        if (fileName.substring(num + 1).equals("doc")) {
            String realPath = UtilCtrl.getSession().getServletContext().getRealPath(FileUpload.DIR_DOC_UPLOAD);
            String finalName = FileUpload.Upload(file, realPath);
            String path = UtilCtrl.getSession().getServletContext().getContextPath();
            JSONObject json = new JSONObject();
            json.put("fileName", fileName);
            json.put("url", path + FileUpload.DIR_DOC_UPLOAD + finalName);
            response.setContentType("text/html; charset=utf-8");
            return JsonTool.genSuccessMsg(json);
        } else {
            response.setContentType("text/html; charset=utf-8");
            return JsonTool.genErrorMsg("上传格式错误");
        }
    }

}
