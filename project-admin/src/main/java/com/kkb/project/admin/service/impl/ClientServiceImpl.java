package com.kkb.project.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.ClientDao;
import com.kkb.project.admin.domain.Client;
import com.kkb.project.admin.service.ClientService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 张观林
 * @Date 2021/4/29 13:31
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements ClientService {
    /**
     * 查找服务公司的总数目
     * @return 总数目
     */
    @Override
    public Integer findClientNum() {
        int count = this.count();
        if (count == 0){
            Asserts.fail("目前还没有甲方公司");
        }
        return count;
    }


    @Override
    public boolean insertOneClient(Client c) {
        String name = c.getName();
        if (StrUtil.isEmpty(name)) {
            Asserts.fail("必须输入委托方姓名");
        }
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", c.getName());
        // 判断是否重名
        if (this.getOne(queryWrapper) != null) {
            Asserts.fail("已经存在名为: " + name + " 的委托方");
        }
        return 1 == baseMapper.insert(c);
    }

    @Override
    public Client getClientById(long id) {
        Client client = baseMapper.selectById(id);
        if (client == null) {
            Asserts.fail("查询委托方失败");
        }
        return client;
    }

    /**
     * 根据委托方id集合查询委托方集合, 使用Map表示, key 是委托方id
     *
     * @param ids 委托方id 集合
     * @return 委托方集合
     */
    @Override
    public Map<Long, Client> getByClientIds(Collection<Long> ids) {
        if (!(ids instanceof Set)) {
            ids = CollectionUtil.newHashSet(ids);
        }
        List<Client> clients = this.listByIds(ids);
        if (ids.size() > clients.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" (");
            List<Long> selectedIds = clients.stream().map(Client::getId).collect(Collectors.toList());
            ids.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            failIds.append(") ");
            Asserts.fail("未查询到id 为: " + failIds.toString() + "的委托方");
        }
        Map<Long, Client> res = new HashMap<>(ids.size());
        clients.forEach(it -> res.put(it.getId(), it));
        return res;
    }
}
