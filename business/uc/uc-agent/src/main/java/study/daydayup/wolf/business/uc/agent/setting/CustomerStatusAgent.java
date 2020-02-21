package study.daydayup.wolf.business.uc.agent.setting;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.uc.api.setting.entity.CustomerStatus;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;
import study.daydayup.wolf.business.uc.api.setting.enums.customer.CustomerStatusGroupEnum;
import study.daydayup.wolf.business.uc.api.setting.enums.customer.CustomerStatusProgressEnum;
import study.daydayup.wolf.business.uc.api.setting.exception.StatusNotFoundException;
import study.daydayup.wolf.business.uc.api.setting.service.CustomerStatusService;
import study.daydayup.wolf.common.model.type.number.Step;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.*;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
public class CustomerStatusAgent {
    private final int STATUS_LENGTH = 20;
    private boolean isInit = false;

    private long accountId;
    private long orgId;

    private BitSet statusSet;
    private Map<StatusEnum, StatusEnum> progressMap;
    private Map<StatusEnum, List<StatusEnum>> progressStep;

    @Reference
    private CustomerStatusService service;

    public void init(long accountId, long orgId) {
        if (accountId <= 0 || orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0");
        }

        if (isInit) {
            return;
        }

        CustomerStatus status = service.find(accountId, orgId);
        if (status == null) {
            throw new StatusNotFoundException();
        }

        this.accountId = accountId;
        this.orgId = orgId;
        initStatus(status);
        initStatusMap();

        this.isInit = true;
    }

    public boolean get(StatusEnum status) {
        int code = status.getCode();

        return statusSet.get(code);
    }

    public Map<String, Boolean> getGroup(CustomerStatusGroupEnum... groups) {
        Map<String, Boolean> map = new HashMap<>();

        for (CustomerStatusGroupEnum group: groups) {
            map.putAll(getGroup(group));
        }

        return map;
    }

    public Map<String, Boolean> getGroup(CustomerStatusGroupEnum group) {
        if (group == null) {
            return null;
        }

        Class<? extends StatusEnum> clazz = group.getGroup();
        Map<String, Boolean> map = new HashMap<>();

        for(StatusEnum status: clazz.getEnumConstants()) {
            String desc = status.getDesc();
            map.put(StringUtil.camel(desc, "."), get(status));
        }

        return map;
    }

    public CustomerStatusAgent set(StatusEnum status) {
        return set(status, true);
    }

    public CustomerStatusAgent set(StatusEnum status, boolean state) {
        int code = status.getCode();
        statusSet.set(code, state);

        updateProgress(status, state);
        return this;
    }

    public Step getProgress(@NonNull StatusEnum status) {
        if (null == progressStep.get(status)) {
            return null;
        }


        int total = 0, current = 0;
        for (StatusEnum step : progressStep.get(status)) {
            total++;
            if (get(step)) {
                current++;
            }
        }

        return Step.of(current, total);
    }

    private void updateProgress(StatusEnum status, boolean state) {
        StatusEnum progress = progressMap.get(status);
        if (null == progress) {
            return;
        }

        for (StatusEnum step : progressStep.get(progress)) {
            if (get(step) != state) {
                return;
            }
        }

        set(progress, state);
    }

    public void save() {
        long[] sArray = statusSet.toLongArray();
        CustomerStatus status = arrayToModel(sArray);

        service.save(status);
    }

    private void initStatusMap() {
        if (progressMap != null) {
            return;
        }

        progressMap = new HashMap<>(16);
        progressStep = new HashMap<>(8);
        List<StatusEnum> progress;
        for (CustomerStatusProgressEnum s : CustomerStatusProgressEnum.class.getEnumConstants()) {
            progressMap.put(s.getStatus(), s.getProgress());

            progress = progressStep.computeIfAbsent(s.getProgress(), k -> new LinkedList<>());
            progress.add(s.getStatus());
        }
    }

    private void initStatus(CustomerStatus status) {
        long[] sArray = {
                status.getS1(), status.getS2(), status.getS3(), status.getS4(), status.getS5(),
                status.getS6(), status.getS7(), status.getS8(), status.getS9(), status.getS10(),

                status.getS11(), status.getS12(), status.getS13(), status.getS14(), status.getS15(),
                status.getS16(), status.getS17(), status.getS18(), status.getS19(), status.getS20(),
                1
        };

        statusSet = BitSet.valueOf(sArray);
    }

    private CustomerStatus arrayToModel(long[] s) {
        if (s.length != STATUS_LENGTH + 1) {
            throw new IllegalArgumentException("invalid status array format");
        }

        CustomerStatus status = new CustomerStatus();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        long[] sArray = formatBitArray(s);

        status.setS1(sArray[1]); status.setS2(sArray[2]); status.setS3(sArray[3]); status.setS4(sArray[4]); status.setS5(sArray[5]);
        status.setS6(sArray[6]); status.setS7(sArray[7]); status.setS8(sArray[8]); status.setS9(sArray[9]); status.setS10(sArray[10]);

        status.setS11(sArray[11]); status.setS12(sArray[12]); status.setS13(sArray[13]); status.setS14(sArray[14]); status.setS15(sArray[15]);
        status.setS16(sArray[16]); status.setS17(sArray[17]); status.setS18(sArray[18]); status.setS19(sArray[19]); status.setS20(sArray[20]);

        return status;
    }

    private long[] formatBitArray(long[] s) {
        long[] sArray = new long[STATUS_LENGTH + 1];
        sArray[0] = 0;
        System.arraycopy(s, 0, sArray, 1, STATUS_LENGTH);

        return sArray;
    }

}
