package com.bcbssc.serverrepo.client.service;

import com.bcbssc.serverrepo.client.eventbus.LoginEvent;
import com.bcbssc.serverrepo.client.eventbus.LogoutEvent;
import com.bcbssc.serverrepo.client.model.User;
import com.bcbssc.serverrepo.client.model.UserRole;
import com.google.inject.Singleton;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
@Singleton
public class LdapUserService implements UserService {
    
    private static final Logger LOG = LoggerFactory.getLogger(LdapUserService.class);

    private User user;
    
    @Inject
    private MBassador eventBus;
    
    @Override
    public boolean login(String userName, String password) {
        LOG.debug("attempting to login as user [{}]", userName);
        //@TODO add real login logic
        
        if (userName.equals("admin") && password.equals("admin")){
            user = new User();
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.ADMIN);
            eventBus.publishAsync(new LoginEvent(true, user, "login success"));
            return true;
        } else if (userName.equals("user") && password.equals("user")){
            user = new User();
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.USER);
            eventBus.publishAsync(new LoginEvent(true, user, "login success"));
            return true;
        } else if (userName.equals("readonly") && password.equals("readonly")){
            user = new User();
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.READONLY);
            eventBus.publishAsync(new LoginEvent(true, user, "login success"));
            return true;
        }
        
        eventBus.publishAsync(new LoginEvent(false, null, "Error: Login failed!"));
        return false;
    }

    @Override
    public boolean loginGuest() {
        LOG.debug("logging in as guest");
        user = new User();
        user.setUserName("anonymous");
        user.setIsLoggedIn(true);
        user.setUserRole(UserRole.READONLY);
        eventBus.publishAsync(new LoginEvent(true, user, "login success"));
        return true;
    }

    @Override
    public boolean logout() {
        if (user!=null){
            user = null;
            eventBus.publishAsync(new LogoutEvent(true, "logout success"));
        }
        return true;
    }

    @Override
    public User getUser() {
        return user;
    }
    
}
