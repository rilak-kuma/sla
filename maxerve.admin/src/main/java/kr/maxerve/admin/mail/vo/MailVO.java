package kr.maxerve.admin.mail.vo;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MailVO {
	private String from;
	private String to;
	private String subject;
	private String template;
	private String msg;
	private Map model = new HashMap();
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Map getModel() {
		return model;
	}
	public void setModel(Map model) {
		this.model = model;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
