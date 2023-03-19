package d2d.testing.gui.main;

public class TeardownRequest extends RTSPRequest{

    public TeardownRequest(Builder builder){
        super(builder);
    }

    @Override
    public String toString() {
        String request = "TEARDOWN rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path+" RTSP/1.0\r\n"
                + addHeaders();
        return request;
    }
}
