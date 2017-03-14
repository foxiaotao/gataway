package simon.demo.core.cache;

import java.util.Date;

/**
 * 缓存管理接口
 * 
 * @author zht
 * @date 2015-01-05
 */
public interface ICacheManager {
	
	/**
	 * 初始化
	 */
	void init();
	
	/**
	 * 销毁
	 */
	void shutDown();

	/**
	 * 返回Cache名字
	 * 
	 * @return
	 */
	String getCacheName();

	/**
	 * 添加元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @return
	 */
	boolean add(String key, Object value);
	
	/**
	 * 没有就添加元素
	 * 有就更新元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @return
	 */
	boolean set(String key, Object value);
	
	/**
	 * 没有就添加元素
	 * 有就更新元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @param exp 超时日期
	 * @return
	 */
	boolean set(String key, Object value, Date exp);
	
	/**
	 * 放二进制流到缓存
	 * 
	 * @param key key值
	 * @param bytes 二进制数组
	 * @param exp 超时日期
	 * @return 返回key(如果返回值为null,则添加失败,如果返回的key与原始key一致,则没有分包存储,如果不一致,则为分包存储)
	 */
	String setBytes(String key, byte[] bytes, Date exp);
	
	/**
	 * 添加元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @param exp 超时日期
	 * @return
	 */
	boolean add(String key, Object value, Date exp);
	
	/**
	 * 添加二进制流
	 * 
	 * @param key key值
	 * @param bytes 二进制数组
	 * @param exp 超时日期
	 * @return 返回key(如果返回值为null,则添加失败,如果返回的key与原始key一致,则没有分包存储,如果不一致,则为分包存储)
	 */
	String addBytes(String key, byte[] bytes, Date exp);

	/**
	 * 添加元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @param exp 超时时间
	 * @param hashcode HashCode值
	 * @return
	 */
	boolean add(String key, Object value, Date exp, int hashcode);
	
	/**
	 * 添加二进制流
	 * 
	 * @param key key值
	 * @param bytes 二进制数组
	 * @param exp 超时日期
	 * @return 返回key(如果返回值为null,则添加失败,如果返回的key与原始key一致,则没有分包存储,如果不一致,则为分包存储)
	 * @param hashcode HashCode值
	 */
	String addBytes(String key, byte[] bytes, Date exp, int hashcode);

	/**
	 * 更新元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @return
	 */
	boolean replace(String key, Object value);
	
	/**
	 * 更新元素
	 * 
	 * @param key key值
	 * @param value value值
	 * @param exp 超时日期 
	 * @return
	 */
	boolean replace(String key, Object value, Date exp);
	
	/**
	 * 更新二进制流
	 * 
	 * @param key key值
	 * @param bytes 二进制数组
	 * @param exp 超时日期
	 * @return
	 */
	String replaceBytes(String key, byte[] bytes, Date exp);
	
	/**
	 * 获取元素
	 * 
	 * @param key key值
	 * @return
	 */
	Object get(String key);
	
	/**
	 * 获取二进制流
	 * 
	 * @param key key值
	 * @param bytes 二进制数组
	 * @param exp 超时日期
	 * @return
	 */
	byte[] getBytes(String key);

	/**
	 * 删除元素
	 * 
	 * @param key key值
	 * @return
	 */
	boolean delete(String key);
	
	/**
	 * 删除元素
	 * 
	 * @param key key值
	 * @return
	 */
	boolean deleteBytes(String key);
	
}
