package com.muf.jobmaster.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@ToString
@JsonInclude(NON_NULL)
public class ResponseEntity<T> {
    private Status status;
    private T result;
    private Paging paging;

    public ResponseEntity(Status status) {
        this.status = status;
    }

    public ResponseEntity(Status status, T result) {
        this.status = status;
        this.result = result;
    }

    public ResponseEntity(Status status, T result, Paging paging) {
        this.status = status;
        this.result = result;
        this.paging = paging;
    }

    public static <T> ResponseEntity<T> badRequest() {
        return badRequest("Bad Request");
    }

    public static <T> ResponseEntity<T> badRequest(String message) {
        return new ResponseEntity<>(new Status(400, message));
    }

    public static <T> ResponseEntity<T> badRequest(T result) {
        return badRequest("Bad Request", result);
    }

    public static <T> ResponseEntity<T> badRequest(String message, T result) {
        return new ResponseEntity<>(new Status(400, message), result);
    }

    public static <T> ResponseEntity<T> notFound() {
        return notFound("Not Found");
    }

    public static <T> ResponseEntity<T> notFound(String message) {
        return new ResponseEntity<>(new Status(404, message));
    }

    public static <T> ResponseEntity<T> ok() {
        return new ResponseEntity<>(Status.success());
    }

    public static <T> ResponseEntity<T> ok(@Nullable T result) {
        return new ResponseEntity<>(Status.success(), result);
    }

    public static <T> ResponseEntity<List<T>> ok(Page<T> page) {
        return new ResponseEntity<>(Status.success(), page.getContent(), new Paging(page));
    }
}
