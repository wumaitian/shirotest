package com.ejiaofei;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by dingxin on 2016/1/1.
 */
public class MyClient implements Watcher {
    ZooKeeper zk = null;
    private String urlNode ="aaaa";

    public String getUrlNode() {
        return urlNode;
    }

    public void setUrlNode(String urlNode) {
       this.urlNode = urlNode;
    }
    public static void main(String[] args) throws Exception {
        MyClient myClient = new MyClient();
        ZooKeeper zk = myClient.getZk();
        myClient.initValue();
        int i=0;
        while(true) {
            System.out.println(myClient.getUrlNode());
            System.out.println("- -- - -- -- -- - - -- -");
            Thread.sleep(10000);
            i++;
            if(i==5) {
                break;
            }
        }
    }


    public ZooKeeper getZk() throws Exception {
        zk = new ZooKeeper("localhost", 3000, this);
        while(zk.getState() != ZooKeeper.States.CONNECTED) {
            Thread.sleep(3000);
        }
        System.out.println("connect sucessfully");
        return zk;
    }

    public void initValue() {
        try {
            this.urlNode = new String(zk.getData("/url", true, null));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getType() == Watcher.Event.EventType.None) {
            System.out.println("连接服务器成功");
        } else if(event.getType() == Event.EventType.NodeCreated) {
            System.out.println("节点创建成功");
        } else if(event.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("子节点创建成功");
            initValue();
        } else if(event.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("节点修改成功");
            initValue();
        } else if(event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("节点删除成功");
        }

    }
}
