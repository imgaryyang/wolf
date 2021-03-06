package study.daydayup.wolf.business.trade.api.domain.state;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.exception.InvalidTradeStateException;

import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.domain.state
 *
 * @author Wingle
 * @since 2019/10/10 11:26 上午
 **/
@Data
public abstract class AbstractTradeState implements TradeState {
    protected int code;
    protected String name;
    protected LocalDateTime updatedAt;

    @Override
    public int getCode() {
        if (code < 0) {
            throw new InvalidTradeStateException(code);
        }

        return code;
    }

    @Override
    public String getName() {
        return name;
    }

}
