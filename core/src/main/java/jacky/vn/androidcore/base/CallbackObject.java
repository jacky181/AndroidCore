package jacky.vn.androidcore.base;

import android.os.Bundle;

/**
 * Created by Jacky Hua on 26/07/2017.
 */

public class CallbackObject {
    private int value;
    private String raw;
    private Bundle bundle;

    public CallbackObject() {
    }

    public int getValue() {
        return this.value;
    }

    public CallbackObject setValue(int value) {
        this.value = value;
        return this;
    }

    public String getRaw() {
        return this.raw;
    }

    public CallbackObject setRaw(String raw) {
        this.raw = raw;
        return this;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public CallbackObject setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }
}
