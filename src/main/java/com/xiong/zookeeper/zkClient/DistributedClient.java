package com.xiong.zookeeper.zkClient;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class DistributedClient {

    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        DistributedClient client = new DistributedClient();
        client.getConnect();
        client.getServerList();
        client.business();
    }

    private void business() {
        System.out.println("业务代码处理...");
    }

    private ArrayList<String> getServerList() {
        ArrayList<String> serverList = new ArrayList<>();
        try {
            List<String> children = zooKeeper.getChildren("/servers", true);
            for (String child : children) {
                byte[] serverName = zooKeeper.getData("/servers/" + child, false, null);
                serverList.add(new String(serverName));
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return serverList;
    }

    private void getConnect() {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }
}
