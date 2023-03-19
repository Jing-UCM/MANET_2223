package d2d.testing.gui.main;

public class AnnounceRequest extends RTSPRequest{

    public AnnounceRequest(Builder builder){
        super(builder);
    }

    @Override
    public String toString(){
        if (mParams.host == null || mParams.port == 0 || mParams.path == null || mParams.seq == 0 || mParams.body == null) {
            throw new IllegalStateException("Announce Request Builder: Some params are null or not set");
        }
        String request = "ANNOUNCE rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path+" RTSP/1.0\r\n" +
                "CSeq: " + mParams.seq + "\r\n" +
                "Content-Length: " + mParams.body.length() + "\r\n" +
                "Content-Type: application/sdp\r\n\r\n" +
                mParams.body;
        return request;
    }
}

