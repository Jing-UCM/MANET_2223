package d2d.testing.streaming;

import java.util.UUID;

import d2d.testing.streaming.sessions.SessionBuilder;

public interface StreamingRecordObserver {
    void localStreamingAvailable(UUID id, SessionBuilder sessionBuilder);
    void localStreamingUnavailable();
    void streamingAvailable(Streaming streaming, boolean bAllowDispatch);
    void streamingUnavailable(Streaming streaming);
}
