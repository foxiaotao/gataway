package simon.demo.core.bean;

import java.util.List;

public class RestResponse2<T> {
	private Object msg;
	private Object result;
	private Object total;
	private Object success;
	
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	public Object getTotal() {
		return total;
	}
	public void setTotal(Object total) {
		this.total = total;
	}
	public Object getSuccess() {
		return success;
	}
	public void setSuccess(Object success) {
		this.success = success;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
}