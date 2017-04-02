package simon.demo.core.test;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestTest {//extends JunitSpringTest{

	
	@Test
	public void indextest(){
	}
	
    public static void main(String[] args) {
        System.out.println(getString());
 
    }
    @SuppressWarnings("finally")
	static String getString(){
        try{
            return "SUCCESS";
        }catch(Exception e){
             
        }finally{
            System.out.println("Finally is executing");
//            return "finally";//如果这句放在finally之外呢？
        }
        return "last";
    }
}
