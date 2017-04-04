package simon.demo.core.test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Test
    public void matcher(){

        Pattern pattern = Pattern.compile("(another|test)");
        StringBuffer sb = new StringBuffer();

        String candidateString = "This is another test. another test";

        String replacement = "$1 AAA $2";
        Matcher m = pattern.matcher(candidateString);
//        m.find();

        if(m.find()){
        	System.out.println(m.group());
        	System.out.println(m.groupCount());
        	System.out.println(m.group(candidateString));
        }
        
//        m.appendReplacement(sb, replacement);
//        String msg = sb.toString();
//        System.out.println(msg);
      
    }
    @Test
    public void mapset(){
    	LinkedHashMap<String, String> pm = new LinkedHashMap<String, String>();
    	pm.put("ad_contract_id", "订单ID");
		pm.put("contract_name", "订单名称");
		pm.put("ad_cast_id", "投放ID"); 
		pm.put("cast_name", "订单名称"); 
		pm.put("ad_creative_id", "素材ID"); 
		pm.put("creative_name", "素材名称"); 
		pm.put("province_name", "省份"); 
		pm.put("city_name", "城市"); 
		pm.put("show1", "AD曝光1+UV"); 
		pm.put("show2", "AD曝光2+UV"); 
		pm.put("show3", "AD曝光3+UV"); 
		pm.put("show4", "AD曝光4+UV"); 
		pm.put("show5", "AD曝光5+UV"); 
		pm.put("show6", "AD曝光6+以上UV"); 
		pm.put("click_yt", "优土点击(次)"); 
		pm.put("click_admaster", "AD点击(次)"); 
		pm.put("click1", "AD点击1+UV"); 
		pm.put("click2", "AD点击2+UV"); 
		pm.put("click3", "AD点击3+UV"); 
		pm.put("click4", "AD点击4+UV"); 
		pm.put("click5", "AD点击5+UV"); 
		pm.put("click6", "AD点击6+以上UV"); 
		
		
		Set<String> keySet =pm.keySet();
		System.out.println(keySet.toArray());
		while(keySet.iterator()!=null){
		}
		Collection<String> values = pm.values();
		System.out.println(keySet + "" + values);
    }
}
