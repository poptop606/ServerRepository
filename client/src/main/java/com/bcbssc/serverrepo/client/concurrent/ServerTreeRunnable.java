package com.bcbssc.serverrepo.client.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerTreeRunnable implements Runnable {
    
    private static final Logger log = LoggerFactory.getLogger(ServerTreeRunnable.class);
    
    private ServerTreeManager serverTreeManager;

    public ServerTreeRunnable(ServerTreeManager serverTreeManager) {
        this.serverTreeManager = serverTreeManager;
    }

    @Override
    public void run() {
        log.debug("ServerTreeRunnable.run()");
        
        serverTreeManager.generateTestTreeData();
        
        try { Thread.sleep(5000); } catch (InterruptedException ex) {  }
        
        serverTreeManager.addApplication();
        
        try { Thread.sleep(5000); } catch (InterruptedException ex) {  }
        
        serverTreeManager.addServersToNewApplication();
    }
    
}
