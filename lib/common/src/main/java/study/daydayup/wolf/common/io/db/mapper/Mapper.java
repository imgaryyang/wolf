package study.daydayup.wolf.common.io.db.mapper;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.common.io.db.mapper
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface Mapper {
    void init(String column);
    void init(String column, String newColumn);
    void map(Row row);
}
