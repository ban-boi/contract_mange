package cn.itcast.service.impl;

import cn.itcast.dao.RecordDao;
import cn.itcast.dao.impl.RecordDaoimpl;
import cn.itcast.domain.Document;
import cn.itcast.domain.Record;
import cn.itcast.service.RecordService;
import javax.servlet.http.Part;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RecordServiceImpl implements RecordService {
    private RecordDao dao=new RecordDaoimpl();

    //根据条件搜索条目（页面初始化也用这个方法，只不过是空条件）
    @Override
    public List<Record> selectRecord(Map<String, String[]> condition) {
        return dao.selectRecord(condition);
    }

    @Override
    public List<Record> selectRecord1(String keyword) {
        return dao.selectRecord1(keyword);
    }

//    @Override
//    public List<Record> findAll() {
//        //调用dao来查询
//        return dao.findAll();
//    }

    @Override
    public void addRecord(Record record, Part document_part) {
        dao.add(record,document_part);
    }

    @Override
    public void deleteRecord(String auto_id) {
        dao.delete(Integer.parseInt(auto_id));
    }

    @Override
    public void delSelectRecord(String[] autoIds) {
        if(autoIds!=null&&autoIds.length>0){
            //1.遍历数组
            for(String auto_id:autoIds){
                //2.调用dao删除
                dao.delete(Integer.parseInt(auto_id));
            }
        }
    }

    @Override
    public Record findRecordByAuto_id(String auto_id) {
        return dao.findByAuto_id(Integer.parseInt(auto_id));
    }
    @Override
    public Document findDocument(String auto_id) {

        return dao.findDocument(Integer.parseInt(auto_id));
    }

    @Override
    public void updateRecord(Record record,Part document_part) {
        dao.update(record,document_part);
    }



    @Override
    public void updateDocument(String auto_id, InputStream input) {
        dao.updateDocument(Integer.parseInt(auto_id),input);
    }


}
