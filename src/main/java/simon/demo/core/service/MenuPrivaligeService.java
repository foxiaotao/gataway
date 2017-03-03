package simon.demo.core.service;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import simon.demo.core.bean.MenuPrivalige;
import simon.demo.core.bean.MenuPrivaligeExample;
import simon.demo.core.bean.User;
import simon.demo.core.shiro.ShiroUser;

public interface MenuPrivaligeService {
    int countByExample(MenuPrivaligeExample example) throws Exception;

    int deleteByExample(MenuPrivaligeExample example) throws Exception;

    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(MenuPrivalige record) throws Exception;

    Map<String,Object> findByPage(MenuPrivalige record, int startPage, int endPage) throws Exception;

    int batchInsert(List records) throws Exception;

    int insertSelective(MenuPrivalige record) throws Exception;

    List<MenuPrivalige> selectByExample(MenuPrivaligeExample example) throws Exception;
    
    List<MenuPrivalige> selectAll() throws Exception;

    MenuPrivalige selectByPrimaryKey(Long id) throws Exception;

    int updateByExampleSelective(MenuPrivalige record, MenuPrivaligeExample example) throws Exception;

    int updateByPrimaryKeySelective(MenuPrivalige record) throws Exception;

	List<MenuPrivalige> selectAllMenu(ShiroUser user);
}