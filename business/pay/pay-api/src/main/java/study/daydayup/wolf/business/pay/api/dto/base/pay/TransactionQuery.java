package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
public class TransactionQuery implements Request {
    @NotNull @Positive
    protected Long payeeId;
    protected Integer transactionType;
    protected Integer paymentChannel;
    protected Integer settlementState;

    protected String payerId;
    protected String payerName;
    protected String payerPhone;
    protected String payerEmail;

    protected String paymentNo;
    protected String settlementNo;

    protected String createdBefore;
    protected String createdAfter;
    protected LocalDateTime createdStart;
    protected LocalDateTime createdEnd;

}
