package com.dchz.common.http;

import com.dchz.common.thread.ThreadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/***
 * HttpEngin
 * 添加对应的任务到这个引擎中
 * @date 2015/06/27
 * @author billywen
 */
public class HttpEngine extends Thread {
	private final String TAG = "HttpEngine";
	
	private static HttpEngine instace;
	private Vector<HttpBaseTask<Object>> mVector = new Vector<HttpBaseTask<Object>>();
	private volatile boolean isRunning = true;
//	private MyThread mThread;
	private Map<Integer,String> mMapUrl = new HashMap<Integer,String>();
	private int mDevType = 2;
	
	private HttpEngine() {
		// TODO Auto-generated constructor stub
		super("HttpEnginePool");
//		//0->正式域名
		mMapUrl.put(NetCommon.NET_TYPE_INDEX_OFFICAL,"http://192.168.0.102/CCSFlat/CCSMobile/MobileService.asmx");
		//1->UAT域名
		mMapUrl.put(NetCommon.NET_TYPE_INDEX_DEV,"http://192.168.0.102/CCSFlat/CCSMobile/MobileService.asmx");
		//2->测试域名
		mMapUrl.put(NetCommon.NET_TYPE_INDEX_TEST,"http://192.168.0.102/CCSFlat/CCSMobile/MobileService.asmx");


		setPriority(Thread.MAX_PRIORITY);
	}

	/***
	 * 启动Http引擎
	 */
	public void startEngine(){
		this.start();
	}
	
	@Override
	public void run() {
		super.run();
		while(isRunning){
			synchronized (mVector) {
				while(mVector.size() <= 0){
				 	try {
					 mVector.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 }
				 if(mVector.size() > 0){
					 final HttpBaseTask task = mVector.remove(0);
				     ThreadPool.getInstance().submmitJob(new Runnable() {
							@Override
							public void run() {
								task.doTask();
								task.recyle();
							}
						});
				 }
			}
		}
	}
	
	public static final HttpEngine getInstance(){
		if(instace == null){
			instace = new HttpEngine();
		}
		return instace;
	}

	/***
	 * 添加Http任务
	 * @param task
	 */
	public void addTask(HttpBaseTask<Object> task){
		synchronized (mVector) {
			mVector.add(task);
			mVector.notify();

		}
	}
	
	public void stopAll(){
		isRunning = false;
	}
	
	public String getRefer(){
		return mMapUrl.get(mDevType);
	}
	public String getImgRefer(){
		return null;
	}
//	/***
//	 * 更改开发测试环境
//	 * 0 --> 正式环境
//	 * 1 --> 开发环境
//	 * 2 --> 测试环境
//	 * @param index
//	 */
//	public void changeDev(int index){
//		this.mDevType = index;
//		SharePreDev mSharePre = new SharePreDev();
//		mSharePre.saveDevIndex(index);
//	}
//
//	/***
//	 * 在开始启动时候进行，修改状态
//	 */
//	public void loadDev(){
//		SharePreDev mSharePre = new SharePreDev();
//		int index = mSharePre.getDevIndex();
//		this.mDevType = index;
//	}
}
