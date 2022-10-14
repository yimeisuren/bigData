package com.xiong.zookeeper.zkClient;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributedServer {
    //注意逗号","后面不能有空格
    private String connectString = "master:2181,slave001:2181,slave002:2181";
    private int sessiontTimeout;
    private byte[] sessionPassword;
    private long sessionId;
    private ZooKeeper zooKeeper;
    private String hostname;

    public static void main(String[] args) {
        DistributedServer distributedServer = new DistributedServer();
        distributedServer.getConnect();
        distributedServer.register();
        distributedServer.business();
    }

    private void business() {
        System.out.println("处理业务代码...");
    }

    private void register() {
        try {
            zooKeeper.create("/servers/" + hostname, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getConnect() {
        try {
            zooKeeper = new ZooKeeper(connectString, sessiontTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
