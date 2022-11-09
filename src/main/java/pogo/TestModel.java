package pogo;

import lombok.Data;

import java.util.Objects;

@Data
public class TestModel {

    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestModel testModel = (TestModel) o;
        return duration.equalsIgnoreCase(testModel.duration) && method.equalsIgnoreCase(testModel.method)
                && name.equalsIgnoreCase(testModel.name) && startTime.equals(testModel.startTime) && status.equalsIgnoreCase(testModel.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, method, name, startTime, endTime, status);
    }
}
