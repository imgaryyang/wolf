package study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india;

import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.Aadhaar;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.AadhaarService;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.AadhaarDAO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india
 *
 * @author Wingle
 * @since 2020/3/22 9:11 下午
 **/
@RpcService(protocol = "dubbo")
public class AadhaarServiceImpl implements AadhaarService {
    @Resource
    private AadhaarDAO dao;
    @Override
    public Result<Aadhaar> find(Long accountId, Long orgId) {

        return null;
    }

    @Override
    public Result<Integer> add(Aadhaar aadhaar) {
        return null;
    }

    @Override
    public Result<Integer> modify(Aadhaar aadhaar) {
        return null;
    }
}
