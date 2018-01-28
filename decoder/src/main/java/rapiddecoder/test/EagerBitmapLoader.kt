package rapiddecoder.test

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Bitmap
import rapiddecoder.source.AndroidAssetBitmapSource
import rapiddecoder.source.AndroidResourceBitmapSource

abstract class EagerBitmapLoader {
    abstract fun scaleTo(width: Int, height: Int): EagerBitmapLoader
    abstract fun scaleBy(x: Float, y: Float): EagerBitmapLoader
    abstract fun region(left: Int, top: Int, right: Int, bottom: Int): EagerBitmapLoader
    abstract fun loadBitmap(): Bitmap

    companion object {
        @JvmStatic
        fun fromResources(res: Resources, id: Int): EagerBitmapLoader =
                BitmapSourceEagerLoader(AndroidResourceBitmapSource(res, id))

        @JvmStatic
        fun fromAsset(context: Context, path: String): EagerBitmapLoader =
                fromAsset(context.assets, path)

        @JvmStatic
        fun fromAsset(assets: AssetManager, path: String): EagerBitmapLoader =
                BitmapSourceEagerLoader(AndroidAssetBitmapSource(assets, path))
    }
}