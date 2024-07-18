# Connect PDS SDK Integration Guide

## 1. Add Internet Permission

Add the internet permission to your `AndroidManifest.xml` file if it's not already present:

```
<uses-permission android:name="android.permission.INTERNET" />
```


## 2. Installing the Connect PDS SDK

### For Gradle Version Below 7

Add the JitPack repository to your project-level `build.gradle` file:

```
allprojects {
    repositories {
        ......
        maven { url 'https://jitpack.io' } // add this line 
    }
}
```
### For Gradle Version 7 and Above

Add the JitPack repository to your `settings.gradle` file:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ......
        maven { url 'https://jitpack.io' } // add this line
    }
}
```

### Add the Connect PDS Dependency

Add the Connect PDS dependency in your app-level `build.gradle` file like below:

```
dependencies {
    .........
    implementation 'com.github.adandaboina:pds-research:1.0.0'
}
```


## 3. Initialize and Use the SDK

You can now initialize and use the Connect PDS SDK in your application. Here's an example of how to do it:

### Java

```
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.mastercard.openbanking.connect.connectPDS.ConnectAtomic;
import com.mastercard.openbanking.connect.connectPDS.ConnectAtomicEventListener;
import org.json.JSONObject;
 
public class ConnectPDSActivity extends AppCompatActivity implements ConnectAtomicEventListener {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // Initialize ConnectAtomic with your public token and event listener
        ConnectAtomic connectAtomic = new ConnectAtomic(this, "{{pass your public token here taken from the UI input edittext}}", this);
 
        // Start the Atomic Transact SDK flow
        connectAtomic.startAtomicSDK();
    }
 
    // Implement methods from ConnectAtomicEventListener
    @Override
    public void onLoad() {
        // Called when the SDK finishes loading
    }
 
    @Override
    public void onClose(JSONObject closeEvent) {
        // Called when the SDK closes, providing close event data
    }
 
    @Override
    public void onFinish(JSONObject finishEvent) {
        // Called when the SDK finishes, providing finish event data
    }
 
    @Override
    public void onInteraction(JSONObject data) {
        // Called when the SDK triggers an interaction, providing interaction data
    }
}
```

### Kotlin

```
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mastercard.openbanking.connect.connectPDS.ConnectAtomic
import com.mastercard.openbanking.connect.connectPDS.ConnectAtomicEventListener
import org.json.JSONObject
 
class ConnectPDSActivity : AppCompatActivity(), ConnectAtomicEventListener {
 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 
        // Initialize ConnectAtomic with your public token and event listener
        val connectAtomic = ConnectAtomic(this, "{{pass your public token here taken from the UI input edittext}}", this)
 
        // Start the Atomic Transact SDK flow
        connectAtomic.startAtomicSDK()
    }
 
    // Implement methods from ConnectAtomicEventListener
    override fun onLoad() {
        // Called when the SDK finishes loading
    }
 
    override fun onClose(closeEvent: JSONObject?) {
        // Called when the SDK closes, providing close event data
    }
 
    override fun onFinish(finishEvent: JSONObject?) {
        // Called when the SDK finishes, providing finish event data
    }
 
    override fun onInteraction(data: JSONObject?) {
        // Called when the SDK triggers an interaction, providing interaction data
    }
}
```
### Example Demo App

Add one `EditText` in your UI which accepts the public token input. Below is an example screenshot of the UI component:

![Example UI](https://github.com/adandaboina/pds-research/blob/main/pds-demo-app-screenshot.png)

