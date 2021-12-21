package com.kkb.project.portal.domain.constraint;

/**
 * @author __SAD_DOG__
 * @date 2021-04-26
 */
public interface HasProjectId {
    /**
     * 给entity对象实现, 用于表示该表有字段存储project_id
     * @return 返回project_id
     */
    Long getProjectId();
}
