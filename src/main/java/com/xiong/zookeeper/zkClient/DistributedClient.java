package com.xiong.zookeeper.zkClient;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
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

    //todo: 需要重写
    private void getConnect() {
        int sessionTimeout = 100;
        String connectString = "master";
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, event -> {

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
