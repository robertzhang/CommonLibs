package com.robertzhang.commonlibs.net_monitor;

/**
 * Created by robertzhang on 16/1/20.
 * email: robertzhangsh@gmail.com
 */
public abstract class NetChangeObserver {

    /**
     * when network connected callback
     */
    public abstract void onNetConnected(com.robertzhang.commonlibs.net_monitor.NetUtils.NetType type) ;

    /**
     *  when network disconnected callback
     */
    public abstract void onNetDisConnect() ;

}
