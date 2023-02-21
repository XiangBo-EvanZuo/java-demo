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
import com.example.javademo.mybatis.common.Exceptions.PassWordError;
import com.example.javademo.mybatis.common.Result.ResultData;
import com.example.javademo.mybatis.Vo.GetListItemVo;
import com.example.javademo.mybatis.Vo.QuerySkinVo;
import com.example.javademo.mybatis.entity.Skin;
import com.example.javademo.mybatis.service.impl.SkinServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('user:list') AND hasRole('admin')")
@RequestMapping("/skin")
public class SkinController {

    @Autowired
    private SkinServiceImpl skinService;

    @RequestMapping("/findAll")
    public ResultData<List<Skin>> findAll(@RequestBody @Validated QuerySkinVo dao) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(dao.getName()),"name", dao.getName());
        if (dao.getMaxPrice() != null) {
            queryWrapper.le("price", dao.getMaxPrice());
            queryWrapper.ge("price", dao.getMinPrice());
        }
        return ResultData.success(skinService.list(queryWrapper));
    }

    @RequestMapping("/findOne")
    public Skin findListItem(@RequestBody GetListItemVo dao) {
        return skinService.findOneItem(skinService, dao);
    }
    @RequestMapping("/error")
    public Skin error() throws PassWordError {
       throw new PassWordError();
    }
}