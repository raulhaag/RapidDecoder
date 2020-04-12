package rapid.decoder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rapid.decoder.test.MainActivity;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BasicUnitTest {

	private Resources res;
	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
	private MainActivity ma;

	@SuppressWarnings("unused")
	private static void assertREquals(Rect rect, int left, int top, int right, int bottom) {
		assertEquals(left, rect.left);
		assertEquals(top, rect.top);
		assertEquals(right, rect.right);
		assertEquals(bottom, rect.bottom);
	}

	@Before
	public void setUp() throws Exception {
		Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
		res = context.getResources();
		ma = mActivityRule.getActivity();
	}

	@Test
	public void testDecoding() {
		decodingTest(ma.a);
		decodingTest(ma.p);
	}
	
	private void decodingTest(int id) {
		Bitmap bitmap = BitmapFactory.decodeResource(res, id);
		Bitmap bitmap2;
		
		BitmapDecoder d;
		int w, h;
		
		d = BitmapDecoder.from(res, id);
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(bitmap.getWidth(), bitmap2.getWidth());
		assertEquals(bitmap.getHeight(), bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();
		
		d = BitmapDecoder.from(res, id).useBuiltInDecoder();
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(bitmap.getWidth(), bitmap2.getWidth());
		assertEquals(bitmap.getHeight(), bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();
		
		d = BitmapDecoder.from(res, id).region(10, 10, 100, 90);
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(90, bitmap2.getWidth());
		assertEquals(80, bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();

		d = BitmapDecoder.from(res, id).region(10, 10, 100, 90).scaleBy(0.7f, 0.8f);
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(63, bitmap2.getWidth());
		assertEquals(64, bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();

		d = BitmapDecoder.from(res, id).region(10, 10, 100, 90).useBuiltInDecoder();
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(90, bitmap2.getWidth());
		assertEquals(80, bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();

		d = BitmapDecoder.from(res, id).region(10, 10, 100, 90).scaleBy(0.7f, 0.8f).useBuiltInDecoder();
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(63, bitmap2.getWidth());
		assertEquals(64, bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();
		
		d = BitmapDecoder.from(res, id).scale(210, 220);
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(210, bitmap2.getWidth());
		assertEquals(220, bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();
		
		final float SCALE_FACTOR = 0.5f;

		d = BitmapDecoder.from(res, id).scaleBy(SCALE_FACTOR);
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals((int) Math.ceil(bitmap.getWidth() * SCALE_FACTOR), bitmap2.getWidth());
		assertEquals((int) Math.ceil(bitmap.getHeight() * SCALE_FACTOR), bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();
		
		d = BitmapDecoder.from(res, id).scaleBy(SCALE_FACTOR).useBuiltInDecoder();
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals((int) Math.ceil(bitmap.getWidth() * SCALE_FACTOR), bitmap2.getWidth());
		assertEquals((int) Math.ceil(bitmap.getHeight() * SCALE_FACTOR), bitmap2.getHeight());
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);
		bitmap2.recycle();
		
		// region after scale
		
		final float SCALE_FACTOR_AFTER_REGION = 0.7f;
		
		d = BitmapDecoder.from(res, id).scaleBy(SCALE_FACTOR_AFTER_REGION).region(10, 10, 30, 30).mutable();
		w = d.width();
		h = d.height();
		bitmap2 = d.decode();
        assertNotNull(bitmap2);
		assertEquals(bitmap2.getWidth(), w);
		assertEquals(bitmap2.getHeight(), h);

		bitmap2.recycle();
	}
}
