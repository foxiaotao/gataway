package simon.demo.core.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import simon.demo.core.bean.MenuPrivalige;
import simon.demo.core.bean.MenuPrivaligeExample;
import simon.demo.core.bean.User;
import simon.demo.core.shiro.ShiroUser;

public interface MenuPrivaligeMapper {
    int countByExample(MenuPrivaligeExample example);

    int countAll();

    int deleteByExample(MenuPrivaligeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MenuPrivalige record);

    int batchInsert(List<MenuPrivalige> records);

    int insertSelective(MenuPrivalige record);

    List<MenuPrivalige> selectAll();

    List<MenuPrivalige> selectByExample(MenuPrivaligeExample example);

    List<MenuPrivalige> selectByExampleWithPage(@Param("example") MenuPrivaligeExample example, @Param("startPage") int startPage, @Param("rows") int rows);

    MenuPrivalige selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MenuPrivalige record, @Param("example") MenuPrivaligeExample example);

    int updateByExample(@Param("record") MenuPrivalige record, @Param("example") MenuPrivaligeExample example);

    int updateByPrimaryKeySelective(MenuPrivalige record);

    int updateByPrimaryKey(MenuPrivalige record);

	List<MenuPrivalige> selectAllMenu(ShiroUser user);
}