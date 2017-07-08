package whirlwind.com.school1.backend;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;

import tech.gusavila92.websocketclient.WebSocketClient;

public final class Backend {

    private static Backend backend = new Backend();

    private WebSocketClient client;

    private Backend() {
        try {
            WebSocketClient client = new WebSocketClient(new URI("wss://whirlwind-studios.org")) {
                @Override
                public void onOpen() {

                }

                @Override
                public void onCloseReceived() {

                }

                @Override
                public void onException(Exception e) {

                }

                @Override
                public void onBinaryReceived(byte[] data) {

                }

                @Override
                public void onTextReceived(String message) {

                }

                @Override
                public void onPingReceived(byte[] data) {

                }

                @Override
                public void onPongReceived(byte[] data) {

                }
            };

            client.connect();
        } catch (URISyntaxException ignored) {
            Log.d("URISyntaxException", ignored.toString());
        }
    }

    public static Backend getInstance() {
        return backend;
    }

    protected WebSocketClient getClient() {
        return client;
    }
}