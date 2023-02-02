package report.exception;

public record ApiError(int errorCode, String errorMessage) {

    public static final class Builder {
        int errorCode;
        String errorMessage;

        public Builder(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public ApiError build() {
            return new ApiError(errorCode, errorMessage);
        }
    }

}
