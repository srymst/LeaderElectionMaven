package org;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
                System.out.println("We are started!");
                while(true) {
                    //System.exit(0);
                    Thread.sleep(1000);
                }
            }
        };
        client.start();
        LeaderSelector selector = new LeaderSelector(client, "/app", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();

        while (true) {
            //System.out.print(".");
            Thread.sleep(1000);
        }
    }
}
