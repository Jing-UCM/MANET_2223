package d2d.testing.gui.main;

import static d2d.testing.streaming.rtsp.RtspClient.TRANSPORT_TCP;
public class SetupRequest extends RTSPRequest{
    private int mTransport;
    private int mTrackNo;

    public SetupRequest(Builder builder){
        super(builder);
        mTransport = builder.mTransport;
        mTrackNo = builder.mTrackNo;
    }

    @Override
    public String toString(){

        String params = mTransport==TRANSPORT_TCP ?
                ("TCP;interleaved="+2*mTrackNo+"-"+(2*mTrackNo+1)) : ("UDP;unicast;client_port="+(5000+2*mTrackNo)+"-"+(5000+2*mTrackNo+1)+";mode=receive");
        String request = "SETUP rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path+"/trackID="+mTrackNo+" RTSP/1.0\r\n" +
                "Transport: RTP/AVP/"+params+"\r\n" +
                addHeaders();

        return request;
    }


}
