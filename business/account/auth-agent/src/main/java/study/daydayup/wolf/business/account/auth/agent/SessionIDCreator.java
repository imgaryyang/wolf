package study.daydayup.wolf.business.account.auth.agent;

import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;
import study.daydayup.wolf.business.account.auth.agent.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * study.daydayup.wolf.business.account.auth.agent
 *
 * @author Wingle
 * @since 2019/12/5 12:10 下午
 **/
public class SessionIDCreator {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String sessionKey;

    @Resource
    private AuthConfig config;

    SessionIDCreator(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.sessionKey = config.getSessionKey();
    }

    public String getExistedID() {
        String sId;

        sId = CookieUtil.get(request, sessionKey);
        if (null != sId) {
            return sId;
        }

        sId = request.getHeader(sessionKey);
        if (null != sId) {
            return sId;
        }

        return sId;
    }

    public String create() {
        String sId = UUID.randomUUID().toString().replaceAll("-", "");
        CookieUtil.set(response, sessionKey, sId, true);

        return sId;
    }

}