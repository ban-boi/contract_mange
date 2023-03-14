package cn.itcast.dao;

import cn.itcast.domain.Document;
import cn.itcast.domain.Record;
import javax.servlet.http.Part;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * 用户操作的dao
 */
public interface RecordDao {

    /**
     * 查询
     * @param condition
     * @return
     */
    //根据条件搜索条目（页面初始化也用这个方法，只不过是空条件）
    List<Record> selectRecord(Map<String, String[]> condition);

    List<Record> selectRecord1(String keyword);




    /**
     * 添加记录
     * @param record
     * @param documentPart
     */
    void add(Record record, Part documentPart);

    /**
     * 根据auto_id删除
     * @param auto_id
     */
    void delete(int auto_id);

    /**
     * 根据auto_id找到记录
     * @return
     */
    Record findByAuto_id(int parseInt);



    /**
     * 根据auto_id找到原记录，把文件和文件名打成Document包
     * @param auto
     * @return
     */
    Document findDocument(int auto);


    void update(Record record,Part document_part);


    /**
     * 用于更新数据没有上传文件的情况
     * @param i
     * @param input
     */
    void updateDocument(int i, InputStream input);
}
