package study.daydayup.wolf.middleware.notice.agent.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.middleware.notice.api.service.SMSService;
import study.daydayup.wolf.framework.layer.api.Agent;

/**
 * study.daydayup.wolf.middleware.notice.agent.notice
 *
 * @author Wingle
 * @since 2020/3/19 4:36 下午
 **/
@Component
public class SMSAgent implements Agent {
    @Reference
    private SMSService smsService;

    public int toIndia(String mobile, String msg) {
        if (null == mobile || null == msg) {
            return 0;
        }

        return smsService.toIndia(mobile, msg);
    }

    public int toChina(String mobile, String msg) {
        if (null == mobile || null == msg) {
            return 0;
        }

        return smsService.toChina(mobile, msg);
    }
}
