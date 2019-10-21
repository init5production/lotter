package init5.production.lotter.juggler.http.entity;

/**
 * @author Jakub Barski
 */
public class HealthCheckResponse {
    private Status status;
    private String error;
    private String dateOfLastDraw;

    public Status getStatus() {
        return status;
    }

    public HealthCheckResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public HealthCheckResponse setError(String error) {
        this.error = error;
        return this;
    }

    public String getDateOfLastDraw() {
        return dateOfLastDraw;
    }

    public HealthCheckResponse setDateOfLastDraw(String dateOfLastDraw) {
        this.dateOfLastDraw = dateOfLastDraw;
        return this;
    }

    public enum Status {
        OK, ERROR
    }
}
