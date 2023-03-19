package d2d.testing.gui.main;

public class RecordRequest extends RTSPRequest{

    public RecordRequest(Builder builder){
        super(builder);
    }

    @Override
    public String toString() {
        String request = "RECORD rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path+" RTSP/1.0\r\n" +
                "Range: npt=0.000-\r\n" +
                addHeaders();
        return request;
    }


}
