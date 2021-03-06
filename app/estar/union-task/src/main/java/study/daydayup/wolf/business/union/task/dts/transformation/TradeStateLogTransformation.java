package study.daydayup.wolf.business.union.task.dts.transformation;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.transformation.DbTransformation;
import study.daydayup.wolf.dts.transformation.Operator;
import study.daydayup.wolf.dts.transformation.Statistics;
import study.daydayup.wolf.dts.transformation.Transformation;

/**
 * study.daydayup.wolf.business.union.task.dts.transformation
 *
 * @author Wingle
 * @since 2020/2/8 8:37 下午
 **/
@Component
public class TradeStateLogTransformation implements Transformation {

    public Statistics latest(Table stream, MysqlSink mysqlSink) {
        if (CollectionUtil.isEmpty(stream)) {
            return null;
        }

        DbTransformation transformation = DbTransformation.newTask(mysqlSink);
        Operator operator;

        operator = transformation.addJob();
        operator.map()
                .rename("seller_id", "org_id")
                .rename("target_state", "state")
                .toLocalDate("created_at", "date")
                .toTag();
        operator.aggregate()
                .count("trade_count")
                .sum("amount", "trade_amount");

        return transformation.transform(stream, true);
    }

}
