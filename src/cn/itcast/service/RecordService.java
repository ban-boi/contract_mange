package cn.itcast.service;

import cn.itcast.domain.Document;
import cn.itcast.domain.Record;
import javax.servlet.http.Part;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/*
    合同记录管理的业务接口
 */
public interface RecordService {

    /**
     * 查询
     * @param condition
     * @return
     */
    //根据条件搜索条目（页面初始化也用这个方法，只不过是空条件）
    List<Record> selectRecord(Map<String, String[]> condition);

    List<Record> selectRecord1(String keyword);

//    /**
//     * 查询所有合同记录信息
//     */
//    public List<Record> findAll();

    /**
     * 添加记录
     * @param record
     * @param document_part
     */
    void addRecord(Record record, Part document_part);

    /**
     * 根据auto_id删除记录
     * @param auto_id
     */
    void deleteRecord(String auto_id);

    /**
     * 根据auto_id找到记录
     * @return
     */
    Record findRecordByAuto_id(String auto_id);


    /**
     * 根据auto_id找到原记录，把文件和文件名打成Document包
     * @param auto_id
     * @return
     */
    Document findDocument(String auto_id);

    /**
     * 修改记录
     *
     * @param record
     */


    /**
     * 多选删除
     * @param autoIds
     */
    void delSelectRecord(String[] autoIds);


    void updateRecord(Record record, Part document_part);


    /**
     * 用在更新数据没有上传文件的情况
     *
     * @param auto_id
     * @param input
     */
    void updateDocument(String auto_id, InputStream input);
}


