package util;

import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertRecordedRequest {
  private final RecordedRequest request;

  private AssertRecordedRequest(RecordedRequest request) {
    this.request = request;
  }

  public static AssertRecordedRequest assertRecordedRequest(RecordedRequest request) {
    return new AssertRecordedRequest(request);
  }

  public AssertRecordedRequest path(String path) {
    assertThat(request.getPath()).isEqualTo(path);
    return this;
  }

  public AssertRecordedRequest method(String method) {
    assertThat(request.getMethod()).isEqualTo(method);
    return this;
  }
}
