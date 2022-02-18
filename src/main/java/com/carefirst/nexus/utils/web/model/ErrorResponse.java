package com.carefirst.nexus.utils.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author sdeshpande
 *
 */
public class ErrorResponse {
	
	private String code;
	private String message;
	private String moreInfo;
	private List<ErrorDetails> details = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public ErrorResponse addDetailsItem(ErrorDetails detailsItem) {
		if (this.details == null) {
			this.details = new ArrayList<>();
		}
		this.details.add(detailsItem);
		return this;
	}

	public List<ErrorDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ErrorDetails> details) {
		this.details = details;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorResponse error = (ErrorResponse) o;
		return Objects.equals(this.code, error.code)
				&& Objects.equals(this.message, error.message) && Objects.equals(this.moreInfo, error.moreInfo)
				&& Objects.equals(this.details, error.details);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message, moreInfo, details);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    moreInfo: ").append(toIndentedString(moreInfo)).append("\n");
		sb.append("    details: ").append(toIndentedString(details)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
