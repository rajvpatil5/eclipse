package Pojo;

import java.util.List;

public class Courses {
	private List<Webautomation> webAutomation;
	private List<Mobile> mobile;
	private List<Api> api;

	public List<Webautomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<Webautomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<Mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

	public List<Api> getApi() {
		return api;
	}

	public void setApi(List<Api> api) {
		this.api = api;
	}

}
