package simon.demo.core.service;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import simon.demo.core.bean.Role;
import simon.demo.core.bean.RoleExample;

public interface RoleService {
    int countByExample(RoleExample example) throws Exception;

    int deleteByExample(RoleExample example) throws Exception;

    int deleteByPrimaryKey(Long rid) throws Exception;

    int insert(Role record) throws Exception;

    Map<String,Object> findByPage(Role record, int startPage, int endPage) throws Exception;

    int batchInsert(List records) throws Exception;

    int insertSelective(Role record) throws Exception;

    List<Role> selectByExample(RoleExample example) throws Exception;

    Role selectByPrimaryKey(Long rid) throws Exception;

    int updateByExampleSelective(Role record, RoleExample example) throws Exception;

    int updateByPrimaryKeySelective(Role record) throws Exception;
}