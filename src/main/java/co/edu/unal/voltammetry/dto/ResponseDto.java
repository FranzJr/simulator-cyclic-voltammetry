package co.edu.unal.voltammetry.dto;

public class ResponseDto<T> {

	public static final int SUCCESS_CODE = 0;
	public static final int NO_SESSION_ERROR_CODE = -1;
	public static final int ERROR_CODE = -2;

	private int code;
	private String description;
	private T object;

	public ResponseDto(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public ResponseDto(int code, String description, T object) {
		this.code = code;
		this.description = description;
		this.object = object;
	}

	public static <T> ResponseDto<T> success() {
		return new ResponseDto<>(SUCCESS_CODE, "Success");
	}

	public static <T> ResponseDto<T> success(String description) {
		return new ResponseDto<>(SUCCESS_CODE, description);
	}

	public static <T> ResponseDto<T> success(T object) {
		return new ResponseDto<>(SUCCESS_CODE, "Success", object);
	}

	public static <T> ResponseDto<T> noSessionError(String description) {
		return new ResponseDto<>(NO_SESSION_ERROR_CODE, description);
	}

	public static <T> ResponseDto<T> error(String description) {
		return new ResponseDto<>(ERROR_CODE, description);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
}
