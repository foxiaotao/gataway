package simon.demo.core.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import simon.demo.core.bean.Product;
import simon.demo.core.bean.ProductExample;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int countAll();

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int batchInsert(List<Product> records);

    int insertSelective(Product record);

    List<Product> selectAll();

    List<Product> selectByExample(ProductExample example);

    List<Product> selectByExampleWithPage(@Param("example") ProductExample example, @Param("startPage") int startPage, @Param("rows") int rows);

    Product selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}