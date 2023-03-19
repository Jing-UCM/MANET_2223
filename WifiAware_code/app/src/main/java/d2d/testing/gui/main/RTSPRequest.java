package d2d.testing.gui.main;

import d2d.testing.streaming.rtsp.RtspClient;
import d2d.testing.streaming.sessions.Session;

public class RTSPRequest {
    public static final int OPTIONS_REQUEST = 1;
    public static final int DESCRIBE_REQUEST = 2;
    public static final int ANNOUNCE_REQUEST = 3;
    public static final int SETUP_REQUEST = 4;
    public static final int PLAY_REQUEST = 5;
    public static final int RECORD_REQUEST = 6;
    public static final int PAUSE_REQUEST = 7;
    public static final int TEARDOWN_REQUEST = 8;
    public static final int AUTH_ANNOUNCE_REQUEST = 9;

    protected class Parameters{
        public String host;
        public String path;
        public String body;
        public int port;
        public int seq;
        public String authorization;
        public String sessionID;

        public Parameters(){
         host = path = body = "";
         port = seq = -1;
        }
    }

    protected Parameters mParams;

    public RTSPRequest(){
        mParams = new Parameters();
    }

    public RTSPRequest(Builder builder){
        this();
        mParams.host = builder.mHost;
        mParams.port = builder.mPort;
        mParams.path = builder.mPath;
        mParams.seq = builder.mSeq;
        mParams.body = builder.mBody;
        mParams.authorization = builder.mAuthorization;
        mParams.sessionID = builder.mSessionID;
    }

    protected String addHeaders() {
        return "CSeq: " + mParams.seq + "\r\n" +
                "Content-Length: 0\r\n" +
                (mParams.sessionID != null ? "Session: " + mParams.sessionID + "\r\n" : "") +
                // For some reason you may have to remove last "\r\n" in the next line to make the RTSP client work with your wowza server :/
                (mParams.authorization != null ? "Authorization: " + mParams.authorization + "\r\n":"") + "\r\n";
    }

    public String toString(){
        return null;
    }

    public static class Builder {
        public String mHost;
        public int mPort;
        public String mBody;
        public String mPath;
        public int mSeq;
        public String mNonce;
        public String mRealm;
        public String mAuthorization;
        public String mSessionID;
        public String mUsername;
        public String mPassword;
        public int mTransport;
        public int mTrackNo;
        int mType;

        public Builder(int type){
            mType = type;
            if(type<1 || type>9) throw new IllegalArgumentException("incorrect type: " + type + ". See RTSPRequest.java for more info");


        }



        public Builder setBody(String body) {
            this.mBody = body;
            return this;
        }

        public Builder setTrackNo(int trackNo) {
            this.mTrackNo = trackNo;
            return this;
        }

        public Builder setTransport(int transport) {
            this.mTransport = transport;
            return this;
        }

        public Builder setHost(String ip) {
            this.mHost = ip;
            return this;
        }

        public Builder setPort(int port) {
            this.mPort = port;
            return this;
        }

        public Builder setPath(String path) {
            this.mPath = path;
            return this;
        }

        public Builder setSeq(int seq) {
            this.mSeq = seq;
            return this;
        }

        public Builder setNonce(String nonce){
            this.mNonce = nonce;
            return this;
        }

        public Builder setRealm(String realm){
            this.mRealm = realm;
            return this;
        }

        public Builder setAuthorization(String authorization) {
            this.mAuthorization = authorization;
            return this;
        }

        public Builder setSessionID(String sessionID) {
            this.mSessionID = sessionID;
            return this;
        }

        public Builder setUsername(String user) {
            this.mUsername = user;
            return this;
        }

        public Builder setPassword(String pwd) {
            this.mPassword = pwd;
            return this;
        }

        public Builder setStreamingState(RtspClient.StreamingState ss) {
//            public int mCSeq;
//            public String mSessionID;
//            public String mAuthorization;

            this.mSeq = ++ss.mCSeq;
            this.mSessionID = ss.mSessionID;
            this.mAuthorization = ss.mAuthorization;
            return this;

        }

        public Builder setParameters(RtspClient.Parameters par) {
//            public String host;
//            public String username;
//            public String password;
//            public int port;
//            public int transport;

            this.mHost = par.host;
            this.mUsername = par.username;
            this.mPassword = par.password;
            this.mPort = par.port;
            this.mTransport = par.transport;
            return this;
        }


        public RTSPRequest build(){

            RTSPRequest mRequest = null;

            switch (mType){
                case OPTIONS_REQUEST:
                    mRequest = new OptionRequest(this);
                    break;
                case DESCRIBE_REQUEST:
                    break;
                case ANNOUNCE_REQUEST:
                    mRequest = new AnnounceRequest(this);
                    break;
                case SETUP_REQUEST:
                    mRequest = new SetupRequest(this);
                    break;
                case PLAY_REQUEST:
                    break;
                case RECORD_REQUEST:
                    mRequest = new RecordRequest(this);
                    break;
                case PAUSE_REQUEST:
                    break;
                case TEARDOWN_REQUEST:
                    mRequest = new TeardownRequest(this);
                    break;
                case AUTH_ANNOUNCE_REQUEST:
//                    String user, String pwd, String nonce, String realm, String authorization, String sessionID

                    if(mUsername!=null
                            &&mPassword!=null
                            &&mNonce!=null
                            &&mRealm!=null
                            &&mAuthorization!=null
                            &&mSessionID!=null){
                        mRequest = new AuthAnnounceRequest(this);
                    }else
                        throw new IllegalArgumentException("Some args to build AUTH_ANNOUNCE_REQUEST are null or invalid");

                    break;
                default:
                    return null;
            }

            return mRequest;
        }



    }

}
