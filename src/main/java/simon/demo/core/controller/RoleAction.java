package simon.demo.core.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import simon.demo.core.bean.Role;
import simon.demo.core.service.RoleService;

@Controller
@RequestMapping(value="/Role")
public class RoleAction {
    @Autowired
    RoleService roleServiceImpl;

    @RequestMapping(value="/index.do")
    public String index(Long rid) throws Exception {
        return "role";
    }

    @RequestMapping(value="/insert.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insert(Role record) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        roleServiceImpl.insert(record);//mapper.insertSelective(...)
        map.put("success", "提示：添加成功！");
        return map;
    }

    @RequestMapping(value="/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(Long rid) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        roleServiceImpl.deleteByPrimaryKey(rid);
        map.put("success", "提示：删除成功！");
        return map;
    }

    @RequestMapping(value="/deleteBatch.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteBatch(String ids) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        String idsArr[] = ids.split(",");
        for (int i = 0; i < idsArr.length; i++) {
            roleServiceImpl.deleteByPrimaryKey(Long.valueOf(idsArr[i]));
        }
        map.put("success", "提示：批量删除成功！");
        return map;
    }

    @RequestMapping(value="/update.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(Role record) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        roleServiceImpl.updateByPrimaryKeySelective(record);
        map.put("success", "提示：编辑成功！");
        return map;
    }

    @RequestMapping(value="/findByPage.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> findByPage(Role record, int rows, int page) throws Exception {
        int startPage=rows*(page-1);
        return roleServiceImpl.findByPage(record,startPage,rows);
    }
}