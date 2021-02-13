package com.zxj.inaction.common.utils.dbutil.jimdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CleanRunnable implements Runnable {
    private static final Logger logger=LoggerFactory.getLogger(CleanRunnable.class);

    private Thread thread;
    private String threadName;
    private static Map<String, Date> threadRecord=new HashMap<>();

    CleanRunnable(String threadName){
        this.threadName=threadName;
        logger.info(threadName+" -- 线程已创建");
    }

    void start(){
        threadRecord.put(threadName,new Date());
        logger.info(threadName+" -- 线程时间戳已添加");
        if (thread == null){
            thread=new Thread(this,threadName);
            thread.start();
            logger.info(threadName+" -- 线程已就绪");
        }
    }

    static void updateTimeStamp(String key){
        if (threadRecord.containsKey(key)){
            threadRecord.replace(key,new Date());
        }
        else {
            //JimDB.destroyClient(key);
        }
        logger.info(key + " -- 时间戳已更新");
    }

    @Override
    public void run() {
        logger.info(threadName+" -- 线程运行中");
        while (true){
            Long databaseConnectTime=new Date().getTime()-threadRecord.get(threadName).getTime();
            if (threadRecord.get(threadName)!=null && databaseConnectTime>3000L){
                logger.info(threadName+" -- CleanBegin");
                //JimDB.destroyClient(threadName);
                threadRecord.remove(threadName);
                logger.info(threadName+" -- CleanEnd");
                break;
            }else if (threadRecord.get(threadName)!=null){
                try{
                    Thread.sleep(databaseConnectTime);
                }catch (InterruptedException ex){
                    logger.error(threadName+" -- Exception"+ex);
                }
            }else {
                logger.error("Thread Record Exception");
                break;
            }
        }
    }
}
