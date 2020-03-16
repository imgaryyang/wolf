package study.daydayup.wolf.business.org.api.task.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.business.org.api.task.dto
 *
 * @author Wingle
 * @since 2020/3/16 10:41 上午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder(toBuilder = true)
public class TaskIds extends TaskOwner {
    private Set<Long> taskIdSet = new TreeSet<>();

    public void add(@NonNull Long taskId) {
        taskIdSet.add(taskId);
    }

    public void addAll(@NonNull Collection<Long> taskIds) {
        taskIdSet.addAll(taskIds);
    }
}
