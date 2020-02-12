package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.config.OffsetConfig;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.business.union.task.dts.sink.DailyLoanSink;
import study.daydayup.wolf.business.union.task.dts.source.ContractSource;
import study.daydayup.wolf.business.union.task.dts.transformation.DailyLoanTransformation;
import study.daydayup.wolf.business.union.task.service.DailyLoanService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.dts.offset.Offset;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/2/10 9:42 上午
 **/
@Component
public class DailyLoanServiceImpl implements DailyLoanService {
    @Resource
    private ContractSource contractSource;
    @Resource
    private DailyLoanTransformation dailyLoanTransformation;
    @Resource
    private DailyLoanSink dailyLoanSink;

    @Resource
    private Offset offset;
    @Resource
    private OffsetConfig offsetConfig;
    @Resource
    private ShardingConfig shardingConfig;


    @Override
    public void countLoanContract() {
        String task = "countLoanContract";

        Long lastId = offset.get(task, offsetConfig.getTable(), shardingConfig.getShard());
        if (lastId == null) {
            return;
        }

        Table contracts = contractSource.latest(task, lastId);
        if (!CollectionUtil.hasValue(contracts)) {
            return;
        }

        Statistics statistics = dailyLoanTransformation.transform(contracts);
        dailyLoanSink.save(task, statistics);
    }

    @Override
    public void countLoanContractState() {

    }

    @Override
    public void countLoanOrder() {

    }

    @Override
    public void countRepayContract() {

    }

    @Override
    public void countRepayOrder() {

    }
}