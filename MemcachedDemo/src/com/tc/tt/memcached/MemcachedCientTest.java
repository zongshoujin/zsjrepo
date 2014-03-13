package com.tc.tt.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * @author admin
 * @version 创建时间：2014年3月10日 下午1:45:02
 */

public class MemcachedCientTest {

	private static MemCachedClient mcc = null;
	
	// 创建一个 memcached 客户端对象  
	public static MemCachedClient getInstance(){
		if(mcc == null){
			mcc = new MemCachedClient();
		}
		
		return mcc;
	}

	// 创建 memcached连接池  
	static{
		
		// 指定memcached服务地址 
		String[] servers = {"127.0.0.1:11011"};
		
		SockIOPool pool = SockIOPool.getInstance();
		//设置缓存服务器
		pool.setServers(servers);
		
		//设置初始化连接数，最小连接数，最大连接数以及最大处理时间 
		pool.setInitConn(10);
		pool.setMinConn(10);
		pool.setMaxConn(50);
		pool.setMaxIdle(1000 * 60 * 60);
		//设置主线程睡眠时间，每3秒苏醒一次，维持连接池大小  
		//maintSleep 千万不要设置成30，访问量一大就出问题，单位是毫秒，推荐30000毫秒。
		pool.setMaintSleep(3000);
		//关闭套接字缓存
		pool.setNagle(false);
		//连接建立后的超时时间 
		pool.setSocketTO(3000);
		//连接建立时的超时时间 
		pool.setSocketConnectTO(0);
		//初始化连接池
		pool.initialize();
	}

	public MemcachedCientTest() {
	}
	
	
	public static void main(String[] args) {
		MemCachedClient mcc = MemcachedCientTest.getInstance();
		mcc.add("cache", "admin");
		System.out.println("cache value==="+mcc.get("cache"));
	}
}
