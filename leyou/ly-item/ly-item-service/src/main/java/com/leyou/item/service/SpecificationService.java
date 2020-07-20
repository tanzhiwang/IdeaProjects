package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupByCid(Long cid) {
        //查询条件，提供通用mapper方法所需的参数
        SpecGroup group=new SpecGroup();
        group.setCid(cid);
        //执行查询操作
        List<SpecGroup> list = groupMapper.select(group);//根据对象中的非空字段查询
        if(CollectionUtils.isEmpty(list)){
            //没查到
             throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FUND);
        }
        return list;
    }

    public List<SpecParam> queryParamByGid(Long gid) {
        SpecParam param=new SpecParam();
        param.setGroupId(gid);
        List<SpecParam> list = paramMapper.select(param);
        if(CollectionUtils.isEmpty(list)){
            //没查到
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FUND);
        }
        return list;
    }

    public List<SpecParam> queryParamList(Long gid, Long cid, Boolean searching) {
        SpecParam param=new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(searching);

        List<SpecParam> list = paramMapper.select(param);//select根据对象中非空属性来查询
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FUND);
        }
        return list;
    }
}
