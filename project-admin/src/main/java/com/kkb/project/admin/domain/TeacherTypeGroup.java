package com.kkb.project.admin.domain;

import javax.validation.groups.Default;

/**
 * 校验分组标记：用于标记被校验的属性，仅在 类型为导师 的时候执行校验
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/20
 */
public interface TeacherTypeGroup extends Default {
}
