package d2d.testing.gui.main;

public class OptionRequest extends RTSPRequest{

    public OptionRequest(Builder builder){
        super(builder);
    }

    @Override
    public String toString() {
        String request = "OPTIONS rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path+" RTSP/1.0\r\n"
                + addHeaders();
        return request;
    }

}
