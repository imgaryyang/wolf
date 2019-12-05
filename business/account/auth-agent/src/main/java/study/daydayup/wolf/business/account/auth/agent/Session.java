package study.daydayup.wolf.business.account.auth.agent;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.account.auth.agent
 * facade
 * @author Wingle
 * @since 2019/12/4 5:50 下午
 **/
public class Session {
    private String sessionID;
    private Map<String, Object> data;

    public void init(String token) {
        if (null != data) {
            return;
        }

        sessionID = token;
        data = new HashMap<String, Object>();
        loadFromRedis();
    }

    public String getSessionID() {
        return sessionID;
    }

    public void set(String key, Object value) {

    }

    public Object get(String key) {
        return data.get(key);
    }

    @PreDestroy
    public void destroy() {

    }

    private void loadFromRedis() {
        //TODO
    }

    private void loadFromRpc() {
        //TODO
    }

}