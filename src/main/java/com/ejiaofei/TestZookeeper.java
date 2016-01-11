package com.ejiaofei;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * Author : secondriver
 */
public class TestZookeeper {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建zookeeper客户端
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("EventType:" + event.getType().name());
            }
        });
        //获取"/" node下的所有子node
        List<String> znodes = zooKeeper.getChildren("/", true);
        for (String path : znodes) {
            System.out.println(path);
        }
        //创建开放权限的持久化node "/test"
        String rs = zooKeeper.create("/test", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode
                .PERSISTENT);
        System.out.println(rs);
        //同步获取"/test" node的数据
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/test", true, stat);
        System.out.println("value=" + new String(data));
        System.out.println(stat.toString());
        //异步获取"/test" node的数据
        zooKeeper.getData("/test", true, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("----------------------");
                System.out.println(rc);
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(new String(data));
                System.out.println(stat.toString());
            }
        }, "Object ctx ..(提供的外部对象)");
        TimeUnit.SECONDS.sleep(10);
        zooKeeper.close();
    }
}