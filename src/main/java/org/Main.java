package org;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.newClient("zookeeper:2181", new ExponentialBackoffRetry(1000, 3));

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("We are started!");
                while(true) {
                    Thread.sleep(1000);
                }
            }
        };
        client.start();
        LeaderSelector selector = new LeaderSelector(client, "/app", listener);
        selector.autoRequeue();
        selector.start();

        while (true) {
            Thread.sleep(1000);
        }
    }
}
