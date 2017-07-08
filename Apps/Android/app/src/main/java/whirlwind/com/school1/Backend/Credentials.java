package whirlwind.com.school1.Backend;

import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


// Any signin/signup Activity may override this one to use its methods
public class Credentials extends AppCompatActivity {

    private static final int REQUEST_RESOLUTION = 0,
            REQUEST_SAVE = 1;

    private static boolean isSignedIn = false;
    private static GoogleApiClient googleApiClient;

    public static boolean isSignedIn() {
        return isSignedIn;
    }

    public static GoogleApiClient getGoogleApiClient(AppCompatActivity activity) {
        if (googleApiClient == null)
            googleApiClient = new GoogleApiClient.Builder(activity)
                    .enableAutoManage(activity, getOnConnectionFailedListener())
                    .addApi(Auth.CREDENTIALS_API)
                    .build();

        return googleApiClient;
    }

    public static void autoSignin(final AppCompatActivity activity) {
        boolean hasCredentials = false;
        if (hasCredentials)
            login(null, true);
        else {
            GoogleApiClient client = getGoogleApiClient(activity);

            CredentialRequest request = new CredentialRequest.Builder()
                    .setPasswordLoginSupported(true)
                    .build();

            Auth.CredentialsApi.request(client, request).setResultCallback(new ResultCallback<CredentialRequestResult>() {
                @Override
                public void onResult(@NonNull CredentialRequestResult credentialRequestResult) {
                    Status status = credentialRequestResult.getStatus();
                    if (status.isSuccess())
                        login(credentialRequestResult.getCredential(), false);
                    else if (status.getStatusCode() == CommonStatusCodes.RESOLUTION_REQUIRED) {
                        if (status.hasResolution()) {
                            try {
                                status.startResolutionForResult(activity, REQUEST_RESOLUTION);
                            } catch (IntentSender.SendIntentException ignored) {
                            }
                        } else {
                            // TODO: Error
                        }
                    } else if (status.getStatusCode() == CommonStatusCodes.SIGN_IN_REQUIRED) {
                        // TODO: Register
                    }
                }
            });
        }
    }

    public static void saveCredential(AppCompatActivity activity, String name, String password) {
        GoogleApiClient client = getGoogleApiClient(activity);

        Credential credential = new Credential.Builder(name)
                .setPassword(password)
                .build();

        Auth.CredentialsApi.save(client, credential).setResultCallback(
                new ResolvingResultCallbacks<Status>(activity, REQUEST_SAVE) {
                    @Override
                    public void onSuccess(@NonNull Status status) {
                    }

                    @Override
                    public void onUnresolvableFailure(@NonNull Status status) {
                    }
                });
    }

    private static void login(Credential credential, boolean hasCredentials) {
        // TODO: Backend Login
    }

    public static GoogleApiClient.OnConnectionFailedListener getOnConnectionFailedListener() {
        return new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            }
        };
    }

    public void signup(String name, String password) {
        boolean success = true;

        if (success)
            saveCredential(this, name, password);
    }

    public void signin(String name, String password) {
        boolean success = true;

        if (success)
            saveCredential(this, name, password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_RESOLUTION)
            login((Credential) data.getParcelableExtra(Credential.EXTRA_KEY), false);
        else if (requestCode == REQUEST_SAVE) {

        }
    }
}