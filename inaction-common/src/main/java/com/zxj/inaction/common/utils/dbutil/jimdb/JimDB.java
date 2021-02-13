package com.zxj.inaction.common.utils.dbutil.jimdb;

import com.alibaba.dcm.DnsCacheManipulator;
import com.jd.jim.cli.Cluster;
import com.jd.jim.cli.ReloadableJimClientFactory;
import com.jd.jim.cli.config.ConfigLongPollingClientFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class JimDB {
    private static Map<String, Cluster> map=new HashMap<>();
    //解决销毁链接时失败问题
    private static Map<String,String> hostMap=new HashMap<>();
    private static Map<String, ReloadableJimClientFactory> clientFactoryMap=new HashMap<>();

    public static synchronized Cluster getInstance(String host,String jimUrl){
        if (map.get(jimUrl)==null){
            hostMap.put(jimUrl,host);
            if (StringUtils.isNotBlank(host)){
                DnsCacheManipulator.setDnsCache("cfs.jim.jd.local",host);
            }
            ConfigLongPollingClientFactory configLongPollingClientFactory=new ConfigLongPollingClientFactory();
            configLongPollingClientFactory.setServiceEndpoint("http://cfs.jim.jd.local");
            configLongPollingClientFactory.setServiceTimeout(50000);
            map.put(jimUrl,getClient(jimUrl,configLongPollingClientFactory));
            CleanRunnable cleanRunnable=new CleanRunnable(jimUrl);
            cleanRunnable.start();
            DnsCacheManipulator.removeDnsCache("cfs.jim.jd.local");
        }else {
            CleanRunnable.updateTimeStamp(jimUrl);
        }
        return map.get(jimUrl);
    }

    public static Cluster getClient(String jimUrl, ConfigLongPollingClientFactory configLongPollingClientFactory){
        ReloadableJimClientFactory jimClientFactory=new ReloadableJimClientFactory();
        jimClientFactory.setConfigClient(configLongPollingClientFactory.create());
        jimClientFactory.setJimUrl(jimUrl);
        clientFactoryMap.put(jimUrl,jimClientFactory);
        return jimClientFactory.getClient();
    }

    static synchronized void destroyClient(String jimUrl){
        if (StringUtils.isNotBlank(hostMap.get(jimUrl))){
            DnsCacheManipulator.setDnsCache("cfs.jim.jd.local",hostMap.get(jimUrl));
            hostMap.remove(jimUrl);
        }
        if (clientFactoryMap.get(jimUrl)!=null){
            clientFactoryMap.get(jimUrl).clear();
            clientFactoryMap.remove(jimUrl);
        }
        if (map.get(jimUrl)!=null){
            map.get(jimUrl).destroy();
            map.remove(jimUrl);
        }
        DnsCacheManipulator.removeDnsCache("cfs.jim.jd.local");
    }
}
