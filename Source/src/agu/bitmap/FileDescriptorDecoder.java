package agu.bitmap;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.os.Build;

class FileDescriptorDecoder extends ExternalBitmapDecoder {
	private FileDescriptor fd;
	
	public FileDescriptorDecoder(FileDescriptor fd) {
		this.fd = fd;
	}
	
	protected FileDescriptorDecoder(FileDescriptorDecoder other) {
		super(other);
		fd = other.fd;
	}

	@Override
	protected Bitmap decode(Options opts) {
		return BitmapFactory.decodeFileDescriptor(fd, null, opts);
	}

	@Override
	protected InputStream getInputStream() {
		return new FileInputStream(fd);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@Override
	protected BitmapRegionDecoder createBitmapRegionDecoder() {
		try {
			return BitmapRegionDecoder.newInstance(fd, false);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public ExternalBitmapDecoder clone() {
		return new FileDescriptorDecoder(this);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() ^ fd.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof FileDescriptorDecoder) || !super.equals(o)) return false;
		
		final FileDescriptorDecoder fdd = (FileDescriptorDecoder) o;
		return fd.equals(fdd.fd);
	}

	@Override
	protected boolean isMemCacheSupported() {
		return false;
	}
}
