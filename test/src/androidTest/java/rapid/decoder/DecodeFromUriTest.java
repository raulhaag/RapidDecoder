package rapid.decoder;

import android.graphics.Bitmap;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rapid.decoder.test.MainActivity;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class DecodeFromUriTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testInvalidUri() {
        Bitmap bitmap = BitmapDecoder.from(mActivityRule.getActivity(), "android.resource://a.b.c/12345").decode();
        assertNull(bitmap);

        bitmap = BitmapDecoder.from(mActivityRule.getActivity(), "://a.b.c/12345").decode();
        assertNull(bitmap);
    }

    @Test
    public void testValidUri() {
        Bitmap bitmap = BitmapDecoder.from(mActivityRule.getActivity(), "android.resource://rapid.decoder" +
                ".test/drawable/android").decode();
        assertNotNull(bitmap);

       /* bitmap = BitmapDecoder.from(mActivityRule.getActivity(), "android.resource://rapid.decoder" +
                ".test.test/2130837504").decode();
        assertNotNull(bitmap);/*/
    }
}
