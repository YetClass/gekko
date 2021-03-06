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
 *
 * Created by CuttleFish on 2020/7/2.
 */
package com.github.jobop.gekko.connector.processors;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.github.jobop.gekko.client.SmartGekkoClient;
import com.github.jobop.gekko.protocols.message.api.RefreshPeersReq;
import lombok.extern.slf4j.Slf4j;

/**
 * process the refresh peers request from leader
 */
@Slf4j
public class ClientRefreshPeersProcessor extends DefaultProcessor<RefreshPeersReq> {
    SmartGekkoClient smartGekkoClient;

    public ClientRefreshPeersProcessor(SmartGekkoClient client) {
        super(null);
        this.smartGekkoClient = client;

    }

    /**
     * @param bizCtx
     * @param asyncCtx
     * @param request
     */
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, RefreshPeersReq request) {
        if (null != request.getPeersMap()) {
            log.info("peers changed,the newest peers size=" + request.getPeersMap().size());
            smartGekkoClient.setPeersMap(request.getPeersMap());
            smartGekkoClient.setLeaderPeerId(request.getLeaderId());
        }
    }

    public String interest() {
        return RefreshPeersReq.class.getName();
    }
}
