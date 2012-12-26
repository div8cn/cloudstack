// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.async;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.cloud.utils.db.GenericDao;
import org.apache.cloudstack.api.InternalIdentity;

@Entity
@Table(name="async_job")
public class AsyncJobVO implements AsyncJob, InternalIdentity {
	public static final int CALLBACK_POLLING = 0;
	public static final int CALLBACK_EMAIL = 1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id = null;

    @Column(name="user_id")
    private long userId;
    
    @Column(name="account_id")
    private long accountId;
    
    @Column(name="session_key")
    private String sessionKey;
    
	@Column(name="job_cmd")
    private String cmd;
	
	@Column(name="job_cmd_originator")
	private String cmdOriginator;
    
	@Column(name="job_cmd_ver")
    private int cmdVersion;
    
    @Column(name="job_cmd_info", length=65535)
    private String cmdInfo;
    
    @Column(name="callback_type")
    private int callbackType;
    
    @Column(name="callback_address")
    private String callbackAddress;
    
    @Column(name="job_status")
    private int status;
    
    @Column(name="job_process_status")
    private int processStatus;
    
    @Column(name="job_result_code")
    private int resultCode;
    
    @Column(name="job_result", length=65535)
    private String result;
    
    @Enumerated(value=EnumType.STRING)
    @Column(name="instance_type", length=64)
    private Type instanceType;
    
	@Column(name="instance_id", length=64)
    private Long instanceId;
    
    @Column(name="job_init_msid")
    private Long initMsid;

    @Column(name="job_complete_msid")
    private Long completeMsid;
    
    @Column(name=GenericDao.CREATED_COLUMN)
    private Date created;
    
    @Column(name="last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    
    @Column(name="last_polled")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPolled;
    
    @Column(name=GenericDao.REMOVED_COLUMN)
    private Date removed;
    
    @Column(name="uuid")
    private String uuid;

    @Transient
    private SyncQueueItemVO syncSource = null;

    @Transient
    private boolean fromPreviousSession = false;

    
    public AsyncJobVO() {
        this.uuid = UUID.randomUUID().toString();
    }
    
    public AsyncJobVO(long userId, long accountId, String cmd, String cmdInfo, Long instanceId, Type instanceType) {
    	this.userId = userId;
    	this.accountId = accountId;
    	this.cmd = cmd;
    	this.cmdInfo = cmdInfo;
    	this.callbackType = CALLBACK_POLLING;
    	this.uuid = UUID.randomUUID().toString();
        this.instanceId = instanceId;
    }
    
    public AsyncJobVO(long userId, long accountId, String cmd, String cmdInfo,
    	int callbackType, String callbackAddress, Long instanceId, Type instanceType) {
    	
    	this(userId, accountId, cmd, cmdInfo, instanceId, instanceType);
    	this.callbackType = callbackType;
    	this.callbackAddress = callbackAddress;
    	this.uuid = UUID.randomUUID().toString();
    }


    @Override
    public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
    public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
    public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Override
    public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	@Override
    public int getCmdVersion() {
		return cmdVersion;
	}
	
	public void setCmdVersion(int version) {
		cmdVersion = version;
	}

	@Override
    public String getCmdInfo() {
		return cmdInfo;
	}

	public void setCmdInfo(String cmdInfo) {
		this.cmdInfo = cmdInfo;
	}

	@Override
    public int getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(int callbackType) {
		this.callbackType = callbackType;
	}

	@Override
    public String getCallbackAddress() {
		return callbackAddress;
	}

	public void setCallbackAddress(String callbackAddress) {
		this.callbackAddress = callbackAddress;
	}

	@Override
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
    public int getProcessStatus() {
		return processStatus;
	}
	
	public void setProcessStatus(int status) {
		processStatus = status;
	}
	
	@Override
    public int getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	@Override
    public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
    public Long getInitMsid() {
		return initMsid;
	}

	public void setInitMsid(Long initMsid) {
		this.initMsid = initMsid;
	}

	@Override
    public Long getCompleteMsid() {
		return completeMsid;
	}

	public void setCompleteMsid(Long completeMsid) {
		this.completeMsid = completeMsid;
	}

	@Override
    public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
    public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
    public Date getLastPolled() {
		return lastPolled;
	}

	public void setLastPolled(Date lastPolled) {
		this.lastPolled = lastPolled;
	}

	@Override
    public Date getRemoved() {
		return removed;
	}

	public void setRemoved(Date removed) {
		this.removed = removed;
	}
	
    @Override
    public Type getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(Type instanceType) {
		this.instanceType = instanceType;
	}

	@Override
    public Long getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(Long instanceId) {
		this.instanceId = instanceId;
	}
	
    @Override
    public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
    @Override
    public String getCmdOriginator() {
		return cmdOriginator;
	}

	public void setCmdOriginator(String cmdOriginator) {
		this.cmdOriginator = cmdOriginator;
	}
	
	@Override
    public SyncQueueItemVO getSyncSource() {
        return syncSource;
    }
    
    public void setSyncSource(SyncQueueItemVO syncSource) {
        this.syncSource = syncSource;
    }
    
    @Override
    public boolean isFromPreviousSession() {
        return fromPreviousSession;
    }
    
    public void setFromPreviousSession(boolean fromPreviousSession) {
        this.fromPreviousSession = fromPreviousSession;
    }
    
    @Override
    public String getUuid() {
    	return this.uuid;
    }
    
    public void setUuid(String uuid) {
    	this.uuid = uuid;
    }
    
	@Override
    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("AsyncJobVO {id:").append(getId());
		sb.append(", userId: ").append(getUserId());
		sb.append(", accountId: ").append(getAccountId());
		sb.append(", sessionKey: ").append(getSessionKey());
		sb.append(", instanceType: ").append(getInstanceType());
		sb.append(", instanceId: ").append(getInstanceId());
		sb.append(", cmd: ").append(getCmd());
		sb.append(", cmdOriginator: ").append(getCmdOriginator());
		sb.append(", cmdInfo: ").append(getCmdInfo());
		sb.append(", cmdVersion: ").append(getCmdVersion());
		sb.append(", callbackType: ").append(getCallbackType());
		sb.append(", callbackAddress: ").append(getCallbackAddress());
		sb.append(", status: ").append(getStatus());
		sb.append(", processStatus: ").append(getProcessStatus());
		sb.append(", resultCode: ").append(getResultCode());
		sb.append(", result: ").append(getResult());
		sb.append(", initMsid: ").append(getInitMsid());
		sb.append(", completeMsid: ").append(getCompleteMsid());
		sb.append(", lastUpdated: ").append(getLastUpdated());
		sb.append(", lastPolled: ").append(getLastPolled());
		sb.append(", created: ").append(getCreated());
		sb.append("}");
		return sb.toString();
	}
}
