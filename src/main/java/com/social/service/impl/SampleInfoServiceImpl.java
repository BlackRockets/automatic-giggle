package com.social.service.impl;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.social.common.EasyExcelUtil;
import com.social.common.PageUtil;
import com.social.common.ReadExcel;
import com.social.dao.*;
import com.social.pojo.*;
import com.social.service.SampleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SampleInfoServiceImpl implements SampleInfoService {

    @Autowired
    private SampleToxinInfoMapper sampleToxinInfoMapper;
    @Autowired
    private SampleInfoVoMapper sampleInfoVoMapper;
    @Autowired
    private CropSpeciesMapper cropSpeciesMapper;
    @Autowired
    private SampleInfoMapper sampleInfoMapper;
    @Autowired
    private SampleToxinMapper sampleToxinMapper;
    @Autowired
    private BacterialStrainInfoMapper bacterialStrainInfoMapper;
    @Autowired
    private CropCategoryMapper cropCategoryMapper;


    public List<SampleToxinInfo> findallToxin() {
        return sampleToxinInfoMapper.selectToxinAll();
    }


    //获取表格数据(起始加载、模糊查询、分页查询)
    public PageUtil<SampleInfoVO> findAllSampleInfoVO(SampleInfoDTO sampleInfoDTO,Integer page) {
        //分割污染率
        if(sampleInfoDTO != null){
            String pollutionRate = sampleInfoDTO.getPollutionRate();
            if(pollutionRate != null){
                String[] split = pollutionRate.split("-");
                float pollutionRateleft = Float.parseFloat(split[0]);
                float pollutionRateright = Float.parseFloat(split[1]);
                sampleInfoDTO.setPollutionRateleft(pollutionRateleft);
                sampleInfoDTO.setPollutionRateright(pollutionRateright);
            }
        }


        PageUtil<SampleInfoVO> pageUtil = new PageUtil<SampleInfoVO>();
        //第几页
        pageUtil.setPageNum(page);
        //每页总条数
        int pageSize = pageUtil.getPageSize();
        //起始行
        Integer startRow = (page-1) * pageSize;
        sampleInfoDTO.setStartRow(startRow);
        sampleInfoDTO.setPageSize(pageSize);
        List<SampleInfoVO> list = sampleInfoVoMapper.selectSampleToxinByLike(sampleInfoDTO);
        for (SampleInfoVO sampleInfoVO :list){

           List<SampleToxinInfo> sampleToxinInfos = sampleToxinInfoMapper.getsampleToxinInfobySampleInfoId(sampleInfoVO.getId(),sampleInfoDTO.getToxin());
          String str = "";
           if (sampleToxinInfos != null){
               for (SampleToxinInfo sampleToxinInfo :sampleToxinInfos){

                   str += sampleToxinInfo.getToxinType() + ",";
               }
           }

           if (str.endsWith(",")){
               str = str.substring(0,str.length()-1);
           }
           sampleInfoVO.setToxinType(str);

        }

        //sampleInfoDTO.getToxin()有值表示是根据污染模糊查询  并且集合中菌种类别没数据就不用返回数据了

        if(sampleInfoDTO.getToxin() != null && sampleInfoDTO.getToxin() != ""){
            ArrayList<String> arrayList = new ArrayList();
            for (SampleInfoVO sampleInfoVO : list) {
                if(sampleInfoVO.getToxinType() != null && sampleInfoVO.getToxinType() != ""){
                    arrayList.add(sampleInfoVO.getToxinType());
                }
            }
            if(arrayList.size() == 0){
                return null;
            }
        }



        Integer total = sampleInfoVoMapper.selectCount();
        pageUtil.setTotal(total);
        pageUtil.setResult(list);
        return pageUtil;
    }

    //导出
    public List<SampleInfoVO> findSampleInfoByIds(String[] ids){
        List<SampleInfoVO> sampleInfoVOS = sampleInfoVoMapper.selectSampleInfoByIds(ids);
       /*
        //poi
        String[] headers = {"id","样品编号","地点","农产品加工类型","取样时间","录入时间","真菌污染率","主要毒素"};
        try {
            OutputStream outputStream = new FileOutputStream("D://a.xls");
            ExportExcel<SampleInfoVO> exportExcel = new ExportExcel<SampleInfoVO>();
            exportExcel.export(outputStream,sampleInfoVOS,headers);
        }catch (Exception e){

        }
        */

        //easyExcel
        try {
            Sheet sheet = new Sheet(1,1,SampleInfoVO.class);
            sheet.setSheetName("单元格名称");
            ExcelWriter excelWriter = new ExcelWriter(new FileOutputStream("D://b.xls"), ExcelTypeEnum.XLS);
            excelWriter.write(sampleInfoVOS,sheet);
            excelWriter.finish();
        }catch (Exception e){

        }
        return null;
    }

    //easyExcel读取导入
    public void doImport1(MultipartFile file) throws IOException {
        List<Object> list = EasyExcelUtil.readExcel(file, new SampleInfo(),1,1);
        if(list != null && list.size() > 0){
            for(Object object : list){
                SampleInfo sampleInfo = (SampleInfo) object;
                System.out.println(sampleInfo.getSampleId()+sampleInfo.getProvince());
            }
        }
    }

    //poi读取导入
    public void doImport(File file) throws IOException, ParseException {
        List<List<Object>> lists = ReadExcel.readExcel(file);


        List<Object> head = lists.get(1);//
        for (int i = 2; i <lists.size() ; i++) {
            List<Object> data = lists.get(i);

            SampleInfo sampleInfo = new SampleInfo();
            List<SampleToxin> sampleInfoList = new ArrayList<SampleToxin>();
            SampleToxin sampleToxin = new SampleToxin();
            for (int j = 0; j < data.size() ; j++) {

                String dataSon = data.get(j).toString();
                if ("样品编号".equals(head.get(j))){
                    sampleInfo.setSampleId(dataSon);
                }else if("农产品加工原料类别".equals(head.get(j).toString().trim())){
                    if(dataSon != null && dataSon != ""){
                        int cropCategoryId = cropSpeciesMapper.selectCropCategoryIdByCropSpecies(dataSon);
                        sampleInfo.setCropCategoryId(cropCategoryId);
                    }
                }else if("是否取样".equals(head.get(j).toString())){
                    if("是".equals(dataSon)){
                        sampleInfo.setFlag(1);
                    }else {
                        sampleInfo.setFlag(0);
                    }
                }else if("取样省".equals(head.get(j).toString())){
                    if(dataSon !=null){
                        sampleInfo.setProvince(dataSon);
                    }

                }else if("取样市".equals(head.get(j).toString())){
                    if(dataSon !=null) {
                        sampleInfo.setCity(dataSon);
                    }
                }else if("取样县".equals(head.get(j).toString())){
                    sampleInfo.setCounty(dataSon);
                }else if("取样乡".equals(head.get(j).toString())){
                    sampleInfo.setTownship(dataSon);
                }else if("取样村".equals(head.get(j).toString())){
                    sampleInfo.setVillage(dataSon);
                }else if("取样户".equals(head.get(j).toString())){
                    sampleInfo.setHousehold(dataSon);
                }else if("收获时间".equals(head.get(j).toString())){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                    Date harvestTime = format.parse(dataSon);
                    sampleInfo.setHarvestTime(harvestTime);
                }else if("取样时间".equals(head.get(j).toString())){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                    Date samplingTime = format.parse(dataSon);
                    sampleInfo.setSamplingTime(samplingTime);
                }else if("取样人".equals(head.get(j).toString().trim())){
                    sampleInfo.setSamplingPeople(dataSon);
                }else if("采集时季节及气候特征".equals(head.get(j).toString())){
                    sampleInfo.setSeasonal(dataSon);
                }else if("采集地、环境信息描述".equals(head.get(j).toString())){
                    sampleInfo.setDescription(dataSon);
                }else if("真菌污染率".equals(head.get(j).toString())){
                    sampleInfo.setPollutionRate(Float.parseFloat(dataSon));
                }else if("DON".equals(head.get(j).toString())){
                    setSampleToxin(head, dataSon, sampleInfoList, sampleToxin, j);
                }else if("伏马菌素".equals(head.get(j).toString())){
                    setSampleToxin(head, dataSon, sampleInfoList, sampleToxin, j);
                }else if("玉米赤霉烯酮".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素总量".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素B1".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素B2".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素B2".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素G1".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素G2".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素M1".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("黄曲霉毒素M2".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("展青霉毒素".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }else if("赭曲霉毒素A".equals(head.get(j).toString())){
                    setSampleToxin(head,dataSon,sampleInfoList,sampleToxin,j);
                }

            }
            sampleInfo.setSampleToxins(sampleInfoList);
            sampleInfoMapper.insertSelective(sampleInfo);
            Integer id = sampleInfo.getId();
            for (SampleToxin toxin : sampleInfoList) {
                toxin.setSampleInfoId(id);
            }
            sampleToxinMapper.insertAllSampleToxin(sampleInfoList);




        }

        file.delete();
    }

    private void setSampleToxin(List<Object> head, String dataSon, List<SampleToxin> sampleInfoList, SampleToxin sampleToxin, int j) {
        if(dataSon != null && dataSon != ""){
            float toxinCount = Float.parseFloat(dataSon);
            int toxinId = sampleToxinInfoMapper.getToxinId(head.get(j).toString());
            sampleToxin.setToxinId(toxinId);
            sampleToxin.setToxinCount(toxinCount);
            sampleInfoList.add(sampleToxin);
        }
    }

    //新增采样信息
    public void insertSampleInfo(SampleInfoDTO sampleInfoDTO, MultipartFile[] wordFile, MultipartFile[] txtFile,
                                 MultipartFile[] pictureFile, HttpServletRequest request){

        //毒素名称iD
        List<Integer> toxinIdList = sampleInfoDTO.getToxinIdList();
        //毒素含量
        List<Float> toxinCountList = sampleInfoDTO.getToxinCountList();
        //添加主表信息获取自增主键
        sampleInfoMapper.insertSampleInfoDTO(sampleInfoDTO);
        Integer sampleInfoId = sampleInfoDTO.getId();

        //添加附表毒素信息
        ArrayList<SampleToxin> sampleToxinList = new ArrayList();
        SampleToxin sampleToxin = new SampleToxin();
        for (int i = 0; i < toxinIdList.size(); i++) {
            sampleToxin.setSampleInfoId(sampleInfoId);
            sampleToxin.setToxinId(toxinIdList.get(i));
            sampleToxin.setToxinCount(toxinCountList.get(i));
            sampleToxinList.add(sampleToxin);
        }
        sampleToxinMapper.insertAllSampleToxin(sampleToxinList);

        //添加产毒菌株信息
        List<BacterialStrainInfo> bacterialStrainInfoList = new ArrayList();
        BacterialStrainInfo bacterialStrainInfo = new BacterialStrainInfo();
        //先获取前端封装的菌株原始编号
        List<String> originalNumList = sampleInfoDTO.getOriginalNumList();
        String sampleId = sampleInfoDTO.getSampleId();
        for (int i = 0; i < originalNumList.size(); i++) {
            bacterialStrainInfo.setSampleInfoId(sampleInfoId);
            bacterialStrainInfo.setSampleNum(sampleId);
            bacterialStrainInfo.setOriginalNum(originalNumList.get(i));
            //添加文件地址
            if(!"nothing".equals(wordFile[i])){
                String wordFilePath = upload(wordFile[i], request);
                bacterialStrainInfo.setWordAddr(wordFilePath);
            }
            if(!"nothing".equals(txtFile[i])){
                String txtFilePath = upload(txtFile[i], request);
                bacterialStrainInfo.setTxtAddr(txtFilePath);
            }
            if(!"nothing".equals(pictureFile[i])){
                String pictureFilePath = upload(pictureFile[i], request);
                bacterialStrainInfo.setPictureAddr(pictureFilePath);
            }
            bacterialStrainInfoList.add(bacterialStrainInfo);
        }
        bacterialStrainInfoMapper.insertBacterialStrainInfo(bacterialStrainInfoList);
    }

    //更新
    public void updateBatch(SampleInfoDTO sampleInfoDTO) {
        //更新主表信息
        sampleInfoMapper.updateByPrimaryKey(sampleInfoDTO);


        //毒素名称iD
        List<Integer> toxinIdList = sampleInfoDTO.getToxinIdList();
        //毒素含量
        List<Float> toxinCountList = sampleInfoDTO.getToxinCountList();
        List<Integer> ids = sampleInfoDTO.getIdList();
        //更新毒素信息
        SampleToxin sampleToxin = new SampleToxin();
        for (int i = 0; i < toxinIdList.size(); i++) {
            sampleToxin.setId(ids.get(i));
            sampleToxin.setToxinId(toxinIdList.get(i));
            sampleToxin.setToxinCount(toxinCountList.get(i));
            sampleToxinMapper.updatebatch(sampleToxin);
        }

        //更新产毒菌株信息
        BacterialStrainInfo bacterialStrainInfo = new BacterialStrainInfo();
        //先获取前端封装的菌株原始编号
        List<String> originalNumList = sampleInfoDTO.getOriginalNumList();
        List<Integer> bacterialStrainidList = sampleInfoDTO.getBacterialStrainidList();
        String sampleId = sampleInfoDTO.getSampleId();
        for (int i = 0; i < originalNumList.size(); i++) {
            bacterialStrainInfo.setId(bacterialStrainidList.get(i));
            bacterialStrainInfo.setSampleInfoId(sampleInfoDTO.getId());
            bacterialStrainInfo.setSampleNum(sampleId);
            bacterialStrainInfo.setOriginalNum(originalNumList.get(i));
            bacterialStrainInfoMapper.updateBatch(bacterialStrainInfo);
        }

    }

    public List<SampleToxinVO> queryGroupByToxinType() {
        List<SampleToxinVO> sampleToxinVOS = sampleToxinMapper.selectGroupByToxinType();
        return sampleToxinVOS;
    }


    //批量删除
    public void batchDeleteById(String[] ids) {
        int i = sampleInfoMapper.batchDelete(ids);
    }

    //编辑前查询
    public SampleInfo editBeforeFind(String id) {
        //采样信息
        SampleInfo sampleInfo = sampleInfoMapper.selectByPrimaryKey(Integer.valueOf(id));

        //毒素信息
        List<SampleToxin> sampleToxins = sampleToxinMapper.selectBySampleId(sampleInfo.getId());
        ArrayList<SampleToxinVO> list = new ArrayList();
        for (SampleToxin sampleToxin : sampleToxins) {
            SampleToxinVO sampleToxinVO = new SampleToxinVO();
            sampleToxinVO.setToxinCount(sampleToxin.getToxinCount());
            sampleToxinVO.setId(sampleToxin.getId());
            SampleToxinInfo sampleToxinInfo = sampleToxinInfoMapper.selectByPrimaryKey(sampleToxin.getToxinId());
            sampleToxinVO.setToxinType(sampleToxinInfo.getToxinType());
            list.add(sampleToxinVO);
        }
        sampleInfo.setSampleToxinVOList(list);
        //产毒菌株信息
        List<BacterialStrainInfo> bacterialStrainInfos = bacterialStrainInfoMapper.selectBacterialStrainInfoBySampleIds(sampleInfo.getId());
        sampleInfo.setBacterialStrainInfoList(bacterialStrainInfos);

        String s = cropCategoryMapper.selectCropCategory(sampleInfo.getCropCategoryId());
        sampleInfo.setCropCategory(s);
        String cropSpecies = cropSpeciesMapper.selectByCropCategoryId(sampleInfo.getBreed());
        sampleInfo.setCropSpecies(cropSpecies);
        return sampleInfo;
    }



    //上传单个文件
    public String upload(MultipartFile file,HttpServletRequest request){
        String filename = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String path = request.getServletContext().getRealPath("/upload");
        String newName =uuid+date+filename.substring(filename.lastIndexOf('.',filename.length()));
        path += "/"+newName;
        File file1 = new File(path);
        try {
            //上传服务器指定地址
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }




}
