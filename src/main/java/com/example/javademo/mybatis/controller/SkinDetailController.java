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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javademo.mybatis.Vo.DetailItemVo;
import com.example.javademo.mybatis.Vo.GetListItemVo;
import com.example.javademo.mybatis.Vo.QueryDetailVo;
import com.example.javademo.mybatis.entity.*;
import com.example.javademo.mybatis.service.impl.SkinDetailServiceImpl;
import com.example.javademo.mybatis.service.impl.SkinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skin-detail")
public class SkinDetailController {
    @Autowired
    private SkinServiceImpl skinService;

    @Autowired
    private SkinDetailServiceImpl skinDetailService;

    @RequestMapping("/findDetail")
    public SkinDetail findDetail(@RequestBody QueryDetailVo dao) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", dao.getId());
        return skinDetailService.getOne(queryWrapper);
    }

    @RequestMapping("/findSkinDetail")
    public DetailItemVo findSkinDetail(@RequestBody GetListItemVo dao) {
        Skin findListItemRes = skinService.findOneItem(skinService, dao);
        Long resId = findListItemRes.getId();
        QueryDetailVo vo = new QueryDetailVo();
        vo.setId(resId);
        SkinDetail detailItemRes = findDetail(vo);
        DetailItemVo detailItemVo = new DetailItemVo();

        detailItemVo.setDesc(detailItemRes.getDescription());
        detailItemVo.setId(findListItemRes.getId());
        detailItemVo.setPrice(findListItemRes.getPrice());
        detailItemVo.setImg(findListItemRes.getImg());
        detailItemVo.setName(findListItemRes.getName());
        return detailItemVo;
    }
}