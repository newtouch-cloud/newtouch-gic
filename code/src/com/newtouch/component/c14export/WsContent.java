package com.newtouch.component.c14export;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 这个是完成信息传递的基础实体
 * @author liu_z
 *
 */
public class WsContent implements Serializable {
	private static final long serialVersionUID = -3711744589812838560L;
	public Map<String, Object> map=new HashMap<String, Object>();
	//请求的ID
	private String tranID;
	//会话的ID
	private String sessionID;
	private String synType;
	
	public String getSynType() {
		return synType;
	}
	public void setSynType(String synType) {
		this.synType = synType;
	}
	public String getTranID() {
		return tranID;
	}
	public void setTranID(String tranID) {
		this.tranID = tranID;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public void set(String key,Object value){
		map.put(key, value);
	}
	public Object get(String key){
		return map.get(key);
	}
}
