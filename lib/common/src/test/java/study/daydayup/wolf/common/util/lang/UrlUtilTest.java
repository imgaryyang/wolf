package study.daydayup.wolf.common.util.lang;

import org.junit.Test;
import study.daydayup.wolf.common.util.net.UrlUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util.lang
 *
 * @author Wingle
 * @since 2020/5/7 7:53 下午
 **/
public class UrlUtilTest {

    @Test
    public void parseUrl() throws UnsupportedEncodingException {
        String url = "processCurrency=INR&amount=100.00&merTransNo=20200507184602600039899850616235&sign=da1b95bbbbeb5979dde19b9ea602eadd11f62ad1a8018a611ae405172f25650c&updateTime=2020-05-07+18%3A46%3A27&pmId=credit.in&transNo=20200507184603457010478245&payerMobile=123456789&createTime=2020-05-07+18%3A46%3A03&processAmount=100.00&payerName=alakd&currency=INR&payerEmail=sklds%40gmail.com&transStatus=success";
        Map<String, String> data = UrlUtil.parseQuery(url);


        assertNotNull("UrlUtil.parseUrl parse fail", data);
        assertEquals("UrlUtil.parseUrl parse fail", data.size(), 14);
        assertEquals("UrlUtil.parseUrl parse fail", data.get("merTransNo"), "20200507184602600039899850616235");
        assertEquals("UrlUtil.parseUrl parse fail", data.get("payerEmail"), "sklds@gmail.com");

    }
}