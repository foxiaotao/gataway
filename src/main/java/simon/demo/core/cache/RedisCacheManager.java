package simon.demo.core.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 
 * <p>
 * Redis客户端访问
 * </p>
 * 
 * @author 卓轩
 * @创建时间：2014年7月11日
 * @version： V1.0
 */
public class RedisCacheManager implements ICacheManager{

	public JedisPool jedisPool; // 池化管理jedis链接池

	private int maxActive;
	private int maxIdle;
	private int maxWait;
	private int port;
	private String ip;
	
	/*{
		int maxActive = ;
		int maxIdle = 2;
		int maxWait = 10000000;
		String ip = "115.29.98.231";
//		String ip = "192.168.4.3";
//		String ip = "localhost";
		int port = 6379;

		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(maxActive);
		// 设置最大空闲数
		config.setMaxIdle(maxIdle);
		// 设置超时时间
		config.setMaxWaitMillis(maxWait);

		// 初始化连接池
//		jedisPool = new JedisPool(ip);
		jedisPool = new JedisPool(config, ip, port);
	}
*/	@Override
	public void init() {
		// TODO Auto-generated method stub
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(maxActive);
		// 设置最大空闲数
		config.setMaxIdle(maxIdle);
		// 设置超时时间
		config.setMaxWaitMillis(maxWait);
		// 初始化连接池
		jedisPool = new JedisPool(config, ip, port);
		
	}
	/**
	 * 向缓存中设置字符串内容
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 * @throws Exception
	 */
	public boolean set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}
	/**
	 * 向缓存中设置字符串内容
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 * @throws Exception
	 */
	public boolean set(byte[] b, byte[] value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(b, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向缓存中设置对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		Jedis jedis = null;
		try {
			String objectJson = JSON.toJSONString(value);
			jedis = jedisPool.getResource();
			jedis.set(key, objectJson);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}


	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return
	 */
	public boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Object value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}
	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) throws JedisConnectionException{
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
		return key;
	}
	
	public boolean delete(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long del = jedis.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
		return false;
	}

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return JSON.parseObject(value, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}
	/**
	 * 根据key 获取 list
	 * 
	 * @param key
	 * @return
	 */
	public List getList(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			List list = jedis.hmget(key);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 向缓存中设置list,map
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setMap(String key, Map map) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hmset(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public void main(String[] args) throws Exception {
		byte[] datas =  null;
		InputStream in = null;
		in  = new FileInputStream(new File("D:\\target.mp3"));
		datas = IOUtils.toByteArray(in);
		set("file1".getBytes(), datas);
		System.out.println("is set ok");
		in.close();
	}
	@Test
	public void getFile() throws Exception{
		byte[] by =  get("file1".getBytes());
		File file = new File("D:\\b.mp3");
		FileOutputStream fileout = new FileOutputStream(file);
		fileout.write(by, 0, by.length);
		System.out.println("is get ok");
		fileout.flush();
		fileout.close();
//		datas
	}
	@Test
	public void delFile() throws Exception{
		Jedis jedis = null;
		jedis = jedisPool.getResource();
		jedis.del("file1".getBytes());
//		jedis.del(by);
		
//		datas
	}
	@Test
	public void setString() throws Exception{
		
//		Jedis jedis = new Jedis("localhost",6379);       // 连接到Redis服务器
        // jedis.auth("123456");                    // 输入口令进行验证
//		jedis.set("greeting", "Hello, world!");     // 将字符串缓存到Redis服务器
		set("greeting", "Hello, world!");     // 将字符串缓存到Redis服务器
        System.out.println(get("greeting"));
		System.out.println("is get ok");
	}

	@Override
	@Deprecated
	public boolean add(String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Deprecated
	public boolean set(String key, Object value, Date exp) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Deprecated
	public String setBytes(String key, byte[] bytes, Date exp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Deprecated
	public boolean add(String key, Object value, Date exp) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Deprecated
	public String addBytes(String key, byte[] bytes, Date exp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Deprecated
	public boolean add(String key, Object value, Date exp, int hashcode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Deprecated
	public String addBytes(String key, byte[] bytes, Date exp, int hashcode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Deprecated
	public boolean replace(String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Deprecated
	public boolean replace(String key, Object value, Date exp) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Deprecated
	public String replaceBytes(String key, byte[] bytes, Date exp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Deprecated
	public byte[] getBytes(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Deprecated
	public boolean delete(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	@Deprecated
	public boolean deleteBytes(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		jedisPool.destroy();
	}
	@Override
	public String getCacheName() {
		// TODO Auto-generated method stub
		return null;
	}
}