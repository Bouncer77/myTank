public interface TCPConnectionListener {

    void onConnectionReady(TCPConnection tcpConnection);
    void onReceiveString(TCPConnection tcpConnection, String value);
    void onDisconect(TCPConnection tcpConnection);
    void onExceptioon(TCPConnection tcpConnection, Exception e);
}
