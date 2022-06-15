package org.barros.modules.exception;


import java.time.OffsetDateTime;
import java.util.List;

public class CustomError {

    private Integer status;
    private String message;
    private OffsetDateTime timeStamp;
    private List<Field> fields;

    public CustomError(Integer status, String message, OffsetDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public CustomError(Integer status, String message, OffsetDateTime timeStamp, List<Field> fields) {
        this(status, message, timeStamp);
        this.fields = fields;

    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getTimeStamp() {
        return timeStamp;
    }

    public List<Field> getFields() {
        return fields;
    }

    public static class Field {
        public String name;
        public String message;

        public Field(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage(){
            return message;
        }
    }
}
