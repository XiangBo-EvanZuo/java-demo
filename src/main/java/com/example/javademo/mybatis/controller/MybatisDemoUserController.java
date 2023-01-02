/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.javademo.mybatis.controller;

import java.util.List;

import com.example.javademo.mybatis.entity.*;
import com.example.javademo.mybatis.mapper.SkinDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javademo.mybatis.mapper.MybatisDemoUserMapper;

@RestController
@RequestMapping("/usermybatis")
public class MybatisDemoUserController {

    @Autowired
    private MybatisDemoUserMapper mybatisDemoUserMapper;

    @Autowired
    private SkinDetailMapper sKinDetailMapper;


    // http://127.0.0.1:8080/usermybatis/findAll
    @RequestMapping("/findAll")
    public List<MybatisDemoUser> findAll(@RequestBody GetListDao dao) {
        return mybatisDemoUserMapper.findAll(dao);
    }

    @RequestMapping("/findListItem")
    public MybatisDemoUser findListItem(@RequestBody GetListItemDao dao) {
        return mybatisDemoUserMapper.findListItem(dao);
    }

    @RequestMapping("/findDetail")
    public SkinDetailDao findDetail(@RequestBody GetDetailDao dao) {
        return sKinDetailMapper.findSkinDetailDaoById(dao.getId());
    }

    @RequestMapping("/findSkinDetail")
    public DetailItem findSkinDetail(@RequestBody GetListItemDao dao) {
        MybatisDemoUser findListItemRes = findListItem(dao);
        Long resId = findListItemRes.getId();
        GetDetailDao vo = new GetDetailDao();
        vo.setId(resId);
        SkinDetailDao detailItemRes = findDetail(vo);
        DetailItem detailItemVo = new DetailItem();

        detailItemVo.setDesc(detailItemRes.getDesc());
        detailItemVo.setId(findListItemRes.getId());
        detailItemVo.setPrice(findListItemRes.getPrice());
        detailItemVo.setImg(findListItemRes.getImg());
        detailItemVo.setName(findListItemRes.getName());
        return detailItemVo;
    }
}