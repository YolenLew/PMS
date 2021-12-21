package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.Client;

import java.util.Collection;
import java.util.Map;

/**
 * @author __SAD_DOG__
 * 委托方相关接口
 */
public interface ClientService extends IService<Client> {

    /**
     * 查找服务公司的总数目
     * @return 总数目
     */
    Integer findClientNum();
    /**
     * 向委托方表中插入一条委托方数据
     *
     * @param c 委托方entity对象
     * @return 插入是否成功
     */
    boolean insertOneClient(Client c);

    /**
     * 由id获取委托方
     *
     * @param id 要获取的委托方id
     * @return 返回委托方或null(若未查询到)
     */
    Client getClientById(long id);

    /**
     * 根据委托方id集合查询委托方集合, 使用Map表示, key 是委托方id
     *
     * @param ids 委托方id 集合
     * @return 委托方集合
     */
    public Map<Long, Client> getByClientIds(Collection<Long> ids);
}
