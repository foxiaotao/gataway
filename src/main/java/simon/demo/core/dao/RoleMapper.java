package simon.demo.core.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import simon.demo.core.bean.Role;
import simon.demo.core.bean.RoleExample;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int countAll();

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    int batchInsert(List<Role> records);

    int insertSelective(Role record);

    List<Role> selectAll();

    List<Role> selectByExample(RoleExample example);

    List<Role> selectByExampleWithPage(@Param("example") RoleExample example, @Param("startPage") int startPage, @Param("rows") int rows);

    Role selectByPrimaryKey(Long rid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}