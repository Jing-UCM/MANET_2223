package d2d.testing.gui.main;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthAnnounceRequest extends RTSPRequest{

    private String mNonce;
    private String mRealm;
    private String mUsername;
    private String mPassword;

//    private AuthParameters authParams;

    public AuthAnnounceRequest(Builder builder){
        super(builder);
//        String user, String pwd, String nonce, String realm, String authorization, String sessionID
        mNonce = builder.mNonce;
        mRealm = builder.mRealm;
        mUsername = builder.mUsername;
        mPassword = builder.mPassword;
    }

    @Override
    public String toString(){
        if (mParams.host == null || mParams.port == 0 || mParams.path == null || mParams.seq == 0 || mParams.body == null) {
            throw new IllegalStateException("Auth Announce Request: Some params are null or not set");
        }

        String uri = "rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path;
        String hash1 = computeMd5Hash(mUsername+":"+mRealm+":"+mPassword);
        String hash2 = computeMd5Hash("ANNOUNCE"+":"+uri);
        String hash3 = computeMd5Hash(hash1+":"+mNonce+":"+hash2);

        mParams.authorization = "Digest username=\""+mUsername+"\",realm=\""+mRealm+"\",nonce=\""+mNonce+"\",uri=\""+uri+"\",response=\""+hash3+"\"";

        String request = "ANNOUNCE rtsp://"+mParams.host+":"+mParams.port+"/"+mParams.path+" RTSP/1.0\r\n" +
                "CSeq: " + mParams.seq + "\r\n" +
                "Content-Length: " + mParams.body.length() + "\r\n" +
                "Authorization: " + mParams.authorization + "\r\n" +
                "Session: " + mParams.sessionID + "\r\n" +
                "Content-Type: application/sdp\r\n\r\n" +
                mParams.body;

        return request;
    }

    private String computeMd5Hash(String buffer) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            return bytesToHex(md.digest(buffer.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException ignore) {
        } catch (UnsupportedEncodingException e) {}
        return "";
    }

    final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
