package simon.demo.core.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author zhdev060
 *
 */
public class JackJsonUtil {
	private static ObjectMapper jackjsonMapper = new ObjectMapper();
	
	
	 /**
	 * @功能: 对象转json
	 * @作者: 大贲·孙涛
	 * @时间: date_2016-9-29
	 * @版本: v1.0
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String objectTOJackjson(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
		jackjsonMapper.enableDefaultTyping();
		return jackjsonMapper.writeValueAsString(obj);
	}
	
	 /**
	 * @功能: json转对象
	 * @作者: 大贲·孙涛
	 * @时间: date_2016-9-29
	 * @版本: v1.0
	 * @param jsonStr
	 * @param collectionClazz
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String jsonStr, Class<?> beanClazz) throws JsonParseException, JsonMappingException, IOException{
		return (T)jackjsonMapper.readValue(jsonStr, beanClazz);
	}
	/**
	 * @功能: json转集合
	 * @作者: 大贲·孙涛
	 * @时间: date_2016-9-29
	 * @版本: v1.0
	 * @param jsonStr
	 * @param collectionClazz
	 * @param valueType
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToCollection(String jsonStr, Class<?> collectionClazz,Class clazz) throws JsonParseException, JsonMappingException, IOException{
		jackjsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);//忽略json多余的属性
		JavaType javaType = jackjsonMapper.getTypeFactory().constructParametricType(collectionClazz, clazz);
		return (T)jackjsonMapper.readValue(jsonStr, javaType);
	}
}
