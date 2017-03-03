package simon.demo.core.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import simon.demo.core.bean.User;
import simon.demo.core.service.UserService;

@Controller
@RequestMapping(value="/User")
public class UserAction {
	
	Logger logger = Logger.getLogger(UserAction.class);
	
    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value="/index.do")
    public String tst(String id) throws Exception {
        return "user";
    }
    
    @RequestMapping(value="/delete.do")
    public void delete(String id) throws Exception {
        userServiceImpl.deleteByPrimaryKey(Long.valueOf(id));
    }

    @RequestMapping(value="/insert.do")
    public void insert(User record) throws Exception {
        userServiceImpl.insert(record);
    }

    @RequestMapping(value="/findByPage.do")
    @ResponseBody
    public Map<String,Object> findByPage(User record, int rows, int page) throws Exception {
    	logger.error("【用户列表查询】");
        int startPage=rows*(page-1)+1;
        int endPage=rows*page;
        return userServiceImpl.findByPage(record,startPage,endPage);
    }

    @RequestMapping(value="/update.do")
    public void update(User record) throws Exception {
        userServiceImpl.updateByPrimaryKeySelective(record);
    }
}