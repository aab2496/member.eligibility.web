/**
 * 
 */
package com.carefirst.nexus.utils.web.model;

import java.util.Calendar;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sdeshpande
 *
 */
@JsonInclude(Include.NON_NULL)
public class ResponseContext {

	private static final String RESPONSE_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	
	private boolean success;
	private String status;
	private String guid;
	private String responseTime;
	private String currentTimeStamp;
	private ErrorResponse error;
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the responseTime
	 */
	public String getResponseTime() {
		return responseTime;
	}
	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(Long responseTime) {
		if ( responseTime != null ) {
			this.responseTime = responseTime.longValue() + " ms";
		} else {
			this.responseTime = StringUtils.EMPTY;
		}
	}
	/**
	 * @return the currentTimeStamp
	 */
	public String getCurrentTimeStamp() {
		return currentTimeStamp;
	}
	/**
	 * @param currentTimeStamp the currentTimeStamp to set
	 */
	public void setCurrentTimeStamp(String currentTimeStamp) {
		this.currentTimeStamp = currentTimeStamp;
	}
	/**
	 * @param currentTimeStamp the currentTimeStamp to set
	 */
	public void setCurrentTimeStamp(Calendar currentTimeStamp) {
		this.currentTimeStamp = DateUtils.printDate(currentTimeStamp, RESPONSE_TIMESTAMP_FORMAT);
	}
	/**
	 * @return the error
	 */
	public ErrorResponse getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorResponse error) {
		this.error = error;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResponseContext responseContext = (ResponseContext) o;
		return Objects.equals(this.success, responseContext.success) && Objects.equals(this.status, responseContext.status) && Objects.equals(this.guid, responseContext.guid)
				&& Objects.equals(this.responseTime, responseContext.responseTime) && Objects.equals(this.currentTimeStamp, responseContext.currentTimeStamp)
				&& Objects.equals(this.error, responseContext.error);
	}

	@Override
	public int hashCode() {
		return Objects.hash(success, status, guid, responseTime, currentTimeStamp, error);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseContext {\n");

		sb.append("    success: ").append(toIndentedString(success)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
		sb.append("    responseTime: ").append(toIndentedString(responseTime)).append("\n");
		sb.append("    currentTimeStamp: ").append(toIndentedString(currentTimeStamp)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
