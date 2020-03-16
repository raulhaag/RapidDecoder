package rapid.decoder.binder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface DrawableCreator {
    @Nullable
    Drawable createDrawable(@NonNull Context context, @NonNull Bitmap d);
}
