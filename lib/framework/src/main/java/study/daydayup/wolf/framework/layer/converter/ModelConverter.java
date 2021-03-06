package study.daydayup.wolf.framework.layer.converter;

import study.daydayup.wolf.framework.layer.api.Model;
import study.daydayup.wolf.framework.layer.dal.DataObject;

/**
 * study.daydayup.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:01 下午
 **/
public interface ModelConverter extends Converter {
    DataObject toDo(Model model);
}
