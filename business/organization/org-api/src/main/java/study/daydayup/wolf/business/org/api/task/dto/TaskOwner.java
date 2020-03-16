package study.daydayup.wolf.business.org.api.task.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.org.api.task.domain.exception.InvalidTaskRequestException;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.business.org.api.task.dto
 *
 * @author Wingle
 * @since 2020/3/16 10:33 上午
 **/
@Data
@SuperBuilder
public class TaskOwner implements DTO {
    private Long orgId;
    private Long staffId;
    private TaskOption option = TaskOption.DEFAULT;

    public void valid() {
       if (null == orgId && null == staffId) {
           throw new InvalidTaskRequestException("OrgId and staffId can't all null for TaskOwner");
       }
    }

}
