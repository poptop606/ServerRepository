package com.github.lariscy.serverrepo.client.event;

import com.github.lariscy.serverrepo.client.MainApp;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class AppCloseEvent implements EventHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppCloseEvent.class);

    @Override
    public void handle(Event event) {
        LOG.debug("close request received");
        
        event.consume();
        
        MainApp.getClient().shutdownClient();
        
        Platform.exit();
        
        LOG.debug("shutdown complete");
    }
    
}
