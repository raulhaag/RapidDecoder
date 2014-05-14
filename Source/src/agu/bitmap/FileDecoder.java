package agu.bitmap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.os.Build;

class FileDecoder extends ExternalBitmapDecoder {
	private String pathName;
	
	public FileDecoder(String pathName) {
		if (pathName == null) {
			throw new NullPointerException();
		}
		this.pathName = pathName;
	}
	
	protected FileDecoder(FileDecoder other) {
		super(other);
		pathName = other.pathName;
	}
	
	@Override
	protected Bitmap decode(Options opts) {
		return BitmapFactory.decodeFile(pathName, opts);
	}

	@Override
	protected InputStream getInputStream() {
		try {
			return new FileInputStream(pathName);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@Override
	protected BitmapRegionDecoder createBitmapRegionDecoder() {
		try {
			return BitmapRegionDecoder.newInstance(pathName, false);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public ExternalBitmapDecoder clone() {
		return new FileDecoder(this);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() ^ pathName.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof FileDecoder) || !super.equals(o)) return false;
		
		final FileDecoder d = (FileDecoder) o;
		return pathName.equals(d.pathName);
	}

	@Override
	protected boolean isMemCacheSupported() {
		return true;
	}
}
