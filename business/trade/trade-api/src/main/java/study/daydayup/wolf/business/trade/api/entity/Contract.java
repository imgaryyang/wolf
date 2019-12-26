package study.daydayup.wolf.business.trade.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.vo.contract.*;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/10/4 12:04 AM
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Contract extends Trade {

    private ObjectsTerm objectsTerm;
    private PaymentTerm paymentTerm;

    private ConsignTerm consignTerm;
    private PostageTerm postageTerm;

    private LoanTerm loanTerm;
    private RepaymentTerm repaymentTerm;
    private List<InstallmentTerm> installmentTermList;
    private TaxTerm taxTerm;
}
