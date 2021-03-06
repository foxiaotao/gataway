package simon.demo.core.service;

import java.util.Map;
import simon.demo.core.bean.Product;

public interface ProductService {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Product record) throws Exception;

    Map<String,Object> findByPage(Product record, int startPage, int endPage) throws Exception;

    int updateByPrimaryKeySelective(Product record) throws Exception;
}