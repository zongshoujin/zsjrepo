package com.tc.tt.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * @author admin
 * @version ����ʱ�䣺2014��3��10�� ����1:45:02
 */

public class MemcachedCientTest {

	private static MemCachedClient mcc = null;
	
	// ����һ�� memcached �ͻ��˶���  
	public static MemCachedClient getInstance(){
		if(mcc == null){
			mcc = new MemCachedClient();
		}
		
		return mcc;
	}

	// ���� memcached���ӳ�  
	static{
		
		// ָ��memcached�����ַ 
		String[] servers = {"127.0.0.1:11011"};
		
		SockIOPool pool = SockIOPool.getInstance();
		//���û��������
		pool.setServers(servers);
		
		//���ó�ʼ������������С������������������Լ������ʱ�� 
		pool.setInitConn(10);
		pool.setMinConn(10);
		pool.setMaxConn(50);
		pool.setMaxIdle(1000 * 60 * 60);
		//�������߳�˯��ʱ�䣬ÿ3������һ�Σ�ά�����ӳش�С  
		//maintSleep ǧ��Ҫ���ó�30��������һ��ͳ����⣬��λ�Ǻ��룬�Ƽ�30000���롣
		pool.setMaintSleep(3000);
		//�ر��׽��ֻ���
		pool.setNagle(false);
		//���ӽ�����ĳ�ʱʱ�� 
		pool.setSocketTO(3000);
		//���ӽ���ʱ�ĳ�ʱʱ�� 
		pool.setSocketConnectTO(0);
		//��ʼ�����ӳ�
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
