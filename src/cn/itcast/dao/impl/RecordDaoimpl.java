package cn.itcast.dao.impl;

import cn.itcast.dao.RecordDao;
import cn.itcast.domain.Document;
import cn.itcast.domain.Record;
import cn.itcast.util.JDBCUtils;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@MultipartConfig
public class RecordDaoimpl implements RecordDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    //根据条件搜索条目（页面初始化也用这个方法，只不过是空条件）
    @Override
    public List<Record> selectRecord(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql="select * from contract where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key:keySet){
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//?条件的值
            }
        }

        sql=sb.toString();

        System.out.println(sql);
        System.out.println(params);

        //执行sql语句，将返回值打包
        List<Record> records=template.query(sql,params.toArray(), new BeanPropertyRowMapper<Record>(Record.class));

        //如果日期差小于三十就把这条信息中的if_recent_expire改为true
        for (int i = 0; i < records.size(); i++) {
            //System.out.println(dateminus(records.get(i).getExpiry_date()));
            if(dateminus(records.get(i).getExpiry_date())<=30&&dateminus(records.get(i).getExpiry_date())>=0){
                records.set(i, records.get(i)).setIf_recent_expire(true);
            }
        }

        return records;
    }

    @Override
    public List<Record> selectRecord1(String keyword) {
        //1.定义模板初始化sql
        String sql="select * from contract where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
//        //2.遍历map
//        Set<String> keySet = condition.keySet();/        //定义参数的集合
        List<Object> params = new ArrayList<Object>();


            //判断value是否有值
            if(keyword!=null && !"".equals(keyword)){
                //有值
                sb.append(" and (id like  ?  or part_a like ? or part_b like ? or start_date like ? or expiry_date like ? or doc_name like ? or remarks like ? )");
                params.add("%"+keyword+"%");
                params.add("%"+keyword+"%");
                params.add("%"+keyword+"%");
                params.add("%"+keyword+"%");
                params.add("%"+keyword+"%");
                params.add("%"+keyword+"%");
                params.add("%"+keyword+"%");
                //?条件的值
            }


        sql=sb.toString();

        System.out.println(sql);
        System.out.println(params);

        //执行sql语句，将返回值打包
        List<Record> records=template.query(sql,params.toArray(), new BeanPropertyRowMapper<Record>(Record.class));

        //如果日期差小于三十就把这条信息中的if_recent_expire改为true
        for (int i = 0; i < records.size(); i++) {
            //System.out.println(dateminus(records.get(i).getExpiry_date()));
            if(dateminus(records.get(i).getExpiry_date())<=30&&dateminus(records.get(i).getExpiry_date())>=0){
                records.set(i, records.get(i)).setIf_recent_expire(true);
            }
        }

        return records;
    }




    /**
     * 日期减法方法
     * @param expiry_date
     * @return
     */
    public long dateminus(Date expiry_date){
        // 定义项目进行天数
        long Days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date currentTime = new Date();
            long time = sdf.parse(sdf.format(currentTime)).getTime();
            long time1 = sdf.parse(expiry_date.toString())
                    .getTime();
            Days = (int) ((time1 -time) /(24* 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Days;
    }

    @Override
    public void add(Record record,Part document_part) {
        String sql="insert into contract values(null,?,?,?,?,?,?,?,0,?)";

        InputStream FileBytes=null; // to get the body of the request as binary data
        try {
            FileBytes = document_part.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        template.update(sql,record.getId(),record.getPart_a(),record.getPart_b(),record.getStart_date(),record.getExpiry_date(),FileBytes,document_part.getSubmittedFileName(),record.getRemarks());
    }

    @Override
    public void delete(int auto_id) {
        //1.定义sql
        String sql="delete from contract where auto_id=?";
        //2.执行sql
        template.update(sql,auto_id);
    }

    //用来在修改页面回显数据
    @Override
    public Record findByAuto_id(int auto_id) {
        String sql ="select  * from contract where auto_id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Record>(Record.class),auto_id);
    }

    //为了在修改页面回显文件
    @Override
    public Document findDocument(int auto_id){
        String sql="select document,doc_name from contract where auto_id=?";
        //String doc_name=document.getSubmittedFileName();
        return template.queryForObject(sql,new BeanPropertyRowMapper<Document>(Document.class),auto_id);

    }


    @Override
    public void update(Record record,Part document_part) {
        String sql ="update contract set id=?,part_a=?,part_b=?,start_date=?,expiry_date=?,document=?,doc_name=?,remarks=? where auto_id=?";
        InputStream FileBytes=null; // to get the body of the request as binary data
        try {
            FileBytes = document_part.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(record.getDoc_name()==null){
            template.update(sql,record.getId(),record.getPart_a(),record.getPart_b(),record.getStart_date(),record.getExpiry_date(),FileBytes,document_part.getSubmittedFileName(),record.getRemarks(),record.getAuto_id());
        }
        else {
            template.update(sql,record.getId(),record.getPart_a(),record.getPart_b(),record.getStart_date(),record.getExpiry_date(),FileBytes,record.getDoc_name(),record.getRemarks(),record.getAuto_id());

        }
    }

    @Override
    public void updateDocument(int auto_id, InputStream input) {
        String sql = "update contract set document= ? where auto_id = ?";
        template.update(sql,input,auto_id);
    }


}
