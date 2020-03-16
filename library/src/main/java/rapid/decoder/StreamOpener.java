package rapid.decoder;

import androidx.annotation.Nullable;

import java.io.InputStream;

interface StreamOpener {
    @Nullable
	InputStream openInputStream();
}
