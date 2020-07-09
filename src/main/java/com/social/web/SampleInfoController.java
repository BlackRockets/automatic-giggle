package com.social.web;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.xdevapi.JsonArray;
import com.social.common.EasyExcelUtil;
import com.social.common.PageUtil;
import com.social.common.ReadExcel;
import com.social.pojo.*;
import com.social.service.SampleInfoService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SampleInfoController {

    @Autowired
    private SampleInfoService sampleInfoService;

    //图标展示毒素信息
    @ResponseBody
    @RequestMapping(value = "findGroupBuToxinType",produces = {"application/json;charset=utf-8"})
    public String findGroupBuToxinType(){
        List<SampleToxinVO> sampleToxinVOS = sampleInfoService.queryGroupByToxinType();
        return JSON.toJSONString(sampleToxinVOS);
    }



    @ResponseBody
    @RequestMapping(value = "getSampleToxin",produces = {"application/json;charset=utf-8"})
    public String getSampleToxin(){
        List<SampleToxinInfo> sampleToxinInfos = sampleInfoService.findallToxin();
        return JSON.toJSONString(sampleToxinInfos);
    }

    //新增采样信息
    @ResponseBody
    @RequestMapping(value = "insertSampleInfoDTO",produces = {"application/json;charset=utf-8"})
    public String insertSampleInfoDTO(@RequestParam String sampleInfoDTO, @RequestParam("wordFile")MultipartFile[] wordFile,
                                      @RequestParam("txtFile")MultipartFile[] txtFile, @RequestParam("pictureFile")MultipartFile[] pictureFile,HttpServletRequest request){
        JSON parse = (JSON) JSON.parse(sampleInfoDTO);
        SampleInfoDTO sampleInfoDTO1 = JSON.toJavaObject(parse, SampleInfoDTO.class);
        sampleInfoService.insertSampleInfo(sampleInfoDTO1,wordFile,txtFile,pictureFile,request);
        return null;
    }

    //编辑回显
    @ResponseBody
    @RequestMapping(value = "findForEdit",produces = {"application/json;charset=utf-8"})
    public String findForEdit(String id){
        SampleInfo sampleInfo = sampleInfoService.editBeforeFind(id);
        return JSON.toJSONString(sampleInfo);
    }

    //起始加载、模糊、分页
    @ResponseBody
    @RequestMapping(value = "findAllSampleInfoVO",produces = {"application/json;charset=utf-8"})
    public String findAllSampleInfoVO(SampleInfoDTO sampleInfoDTO){
        String year = sampleInfoDTO.getYear();
        String month = sampleInfoDTO.getMonth();
        String day = sampleInfoDTO.getDay();
        String date = "";
        if (day != null && !"".equals(day)){
            date = year + "-_" + month + "-_" + day + "%";
        }else {
            if (month != null && !"".equals(month)){
                date = year + "-_" + month + "%";
            }else {
                if (year != null && !"".equals(year)){
                    date = year + "%";
                }else {
                    date = "";
                }
            }
        }
        sampleInfoDTO.setDate(date);
        PageUtil<SampleInfoVO> allSampleInfoVO = sampleInfoService.findAllSampleInfoVO(sampleInfoDTO,sampleInfoDTO.getPageNum());

        return JSON.toJSONString(allSampleInfoVO);
    }


    @RequestMapping(value = "updateBatch",produces = {"application/json;charset=utf-8"})
    public String updateBatch(SampleInfoDTO sampleInfoDTO) {
        sampleInfoService.updateBatch(sampleInfoDTO);
        return JSON.toJSONString("success");
    }

    //导出
    @RequestMapping("doExport")
    @ResponseBody
    public String doExport(String ids){
        sampleInfoService.findSampleInfoByIds(ids.split(","));
        return  "success";
    }


    //poi导入
    @ResponseBody
    @RequestMapping("doImport")
    public String doImport(@RequestParam("file")MultipartFile file,
                         HttpServletRequest request) throws IOException, ParseException {
        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String path = request.getServletContext().getRealPath("/upload");
        String newName = uuid+date+filename.substring(filename.lastIndexOf(".",filename.length()));
        path += "/"+newName;
        File file1 = new File(path);
        //上传服务器
        file.transferTo(file1);
        //从服务器读取文件
        sampleInfoService.doImport(file1);
        return  "success";
    }



    //easyExcel导入
    @ResponseBody
    @RequestMapping("doImport1")
    public String doImport(@RequestParam("file")MultipartFile file) throws IOException {
            sampleInfoService.doImport1(file);
            return "success";
    }





    //下载
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response)throws  IOException{

        //模拟文件，myfile.txt为需要下载的文件
        String name = "样品信息导入模板.xlsx";
        String path = request.getSession().getServletContext().getRealPath("/static/img/样品信息导入模板.xlsx");
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //转码，免得文件名中文乱码
        name = URLEncoder.encode(name,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + name);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }

    //批量删除
    @ResponseBody
    @RequestMapping(value = "batchDeleteByIds")
    public String  batchDeleteByIds(String[] ids){
        sampleInfoService.batchDeleteById(ids);
        return JSON.toJSONString("success");
    }



}
