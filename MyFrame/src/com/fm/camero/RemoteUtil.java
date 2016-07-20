package com.fm.camero;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import android.graphics.Bitmap;

public class RemoteUtil {
	private static Deflater deflater = new Deflater();

	/**
	 * // decodeYUV420Sp2RGB
	 *
	 * @param yuv420sp
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] decodeYUV420SP2RGB(byte[] yuv420sp, int width,
											int height) {
		final int frameSize = width * height;
		byte[] rgbBuf = new byte[frameSize * 3];
		// if (rgbBuf == null) throw new
		// NullPointerException("buffer 'rgbBuf' is null");
		if (rgbBuf.length < frameSize * 3)
			throw new IllegalArgumentException("buffer 'rgbBuf' size "
					+ rgbBuf.length + " < minimum " + frameSize * 3);

		if (yuv420sp == null)
			throw new NullPointerException("buffer 'yuv420sp' is null");

		if (yuv420sp.length < frameSize * 3 / 2)
			throw new IllegalArgumentException("buffer 'yuv420sp' size "
					+ yuv420sp.length + " < minimum " + frameSize * 3 / 2);

		int i = 0, y = 0;
		int uvp = 0, u = 0, v = 0;
		int y1192 = 0, r = 0, g = 0, b = 0;
		for (int j = 0, yp = 0; j < height; j++) {
			uvp = frameSize + (j >> 1) * width;
			u = 0;
			v = 0;
			for (i = 0; i < width; i++, yp++) {
				y = (0xff & ((int) yuv420sp[yp])) - 16;
				if (y < 0)
					y = 0;
				if ((i & 1) == 0) {
					v = (0xff & yuv420sp[uvp++]) - 128;
					u = (0xff & yuv420sp[uvp++]) - 128;
				}

				y1192 = 1192 * y;
				r = (y1192 + 1634 * v);
				g = (y1192 - 833 * v - 400 * u);
				b = (y1192 + 2066 * u);

				if (r < 0)
					r = 0;
				else if (r > 262143)
					r = 262143;
				if (g < 0)
					g = 0;
				else if (g > 262143)
					g = 262143;
				if (b < 0)
					b = 0;
				else if (b > 262143)
					b = 262143;

				rgbBuf[yp * 3] = (byte) (r >> 10);
				rgbBuf[yp * 3 + 1] = (byte) (g >> 10);
				rgbBuf[yp * 3 + 2] = (byte) (b >> 10);
			}
		}
		return rgbBuf;
	}

	/**
	 * //YUV420SP2YUV420
	 *
	 * @param data
	 * @param length
	 * @return
	 */
	public static byte[] decodeYUV420SP2YUV420(byte[] data, int length) {
		int width = 176;
		int height = 144;
		byte[] str = new byte[length];
		System.arraycopy(data, 0, str, 0, width * height);

		int strIndex = width * height;

		for (int i = width * height + 1; i < length; i += 2) {
			str[strIndex++] = data[i];
		}
		for (int i = width * height; i < length; i += 2) {
			str[strIndex++] = data[i];
		}
		return str;
	}

	/**
	 * @param yuv420sp
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] YUV420SP2YUV420(byte[] yuv420sp, int width, int height) {
		if (yuv420sp == null)
			return null;
		byte[] yuv420 = new byte[yuv420sp.length];
		int framesize = width * height;
		int i = 0, j = 0;
		// copy y
		for (i = 0; i < framesize; i++) {
			yuv420[i] = yuv420sp[i];
		}
		i = 0;
		for (j = 0; j < framesize / 2; j += 2) {
			yuv420[i + framesize * 5 / 4] = yuv420sp[j + framesize];
			i++;
		}
		i = 0;
		for (j = 1; j < framesize / 2; j += 2) {
			yuv420[i + framesize] = yuv420sp[j + framesize];
			i++;
		}
		return yuv420;
	}

	public static byte[] encode(final byte[] current, final byte[] previous,
								final int blockWidth, final int blockHeight, final int width,
								final int height) throws Exception {
		// 杩欓噷闄愬埗浜?鏈?ぇ涓婁紶瑙嗛瀹归噺涓?G
		ByteArrayOutputStream baos = new ByteArrayOutputStream(16 * 1024);

		if (previous == null) {
			baos.write(getTag(0x01 /* key-frame */, 0x03 /* ScreenVideo codec */));
		} else {
			baos.write(getTag(0x02 /* inter-frame */, 0x03 /* ScreenVideo codec */));
		}

		// write header
		final int wh = width + ((blockWidth / 16 - 1) << 12);
		final int hh = height + ((blockHeight / 16 - 1) << 12);

		writeShort(baos, wh);
		writeShort(baos, hh);

		// write content
		int y0 = height;
		int x0 = 0;
		int bwidth = blockWidth;
		int bheight = blockHeight;

		while (y0 > 0) {
			bheight = Math.min(y0, blockHeight);
			y0 -= bheight;

			bwidth = blockWidth;
			x0 = 0;

			while (x0 < width) {
				bwidth = (x0 + blockWidth > width) ? width - x0 : blockWidth;

				final boolean changed = isChanged(current, previous, x0, y0,
						bwidth, bheight, width, height);

				if (changed) {
					ByteArrayOutputStream blaos = new ByteArrayOutputStream(
							4 * 1024);

					DeflaterOutputStream dos = new DeflaterOutputStream(blaos,
							deflater);

					for (int y = 0; y < bheight; y++) {
						// Log.i("DEBUG",
						// "current鐨勯暱搴?"+current.length+" 璧峰鐐癸細"+3 * ((y0 +
						// bheight - y - 1) * width + x0)+" 缁堢偣锛?+3 * bwidth);
						dos.write(current,
								3 * ((y0 + bheight - y - 1) * width + x0),
								3 * bwidth);
					}
					// dos.write(current, 0, current.length);
					dos.finish();
					deflater.reset();

					final byte[] bbuf = blaos.toByteArray();
					final int written = bbuf.length;

					// write DataSize
					writeShort(baos, written);
					// write Data
					baos.write(bbuf, 0, written);
				} else {
					// write DataSize
					writeShort(baos, 0);
				}
				x0 += bwidth;
			}
		}
		return baos.toByteArray();
	}

	/**
	 * Writes short value to the {@link OutputStream <tt>os</tt>}.
	 *
	 * @param os
	 * @param n
	 * @throws Exception
	 *             if an exception occurred
	 */
	private static void writeShort(OutputStream os, final int n)
			throws Exception {
		os.write((n >> 8) & 0xFF);
		os.write((n >> 0) & 0xFF);
	}

	/**
	 * Checks if image block is changed.
	 *
	 * @param current
	 * @param previous
	 * @param x0
	 * @param y0
	 * @param blockWidth
	 * @param blockHeight
	 * @param width
	 * @param height
	 * @return <code>true</code> if changed, otherwise <code>false</code>
	 */
	public static boolean isChanged(final byte[] current,
									final byte[] previous, final int x0, final int y0,
									final int blockWidth, final int blockHeight, final int width,
									final int height) {
		if (previous == null)
			return true;

		for (int y = y0, ny = y0 + blockHeight; y < ny; y++) {
			final int foff = 3 * (x0 + width * y);
			final int poff = 3 * (x0 + width * y);

			for (int i = 0, ni = 3 * blockWidth; i < ni; i++) {
				if (current[foff + i] != previous[poff + i])
					return true;
			}
		}
		return false;
	}

	/**
	 * @param frame
	 * @param codec
	 * @return tag
	 */
	public static int getTag(final int frame, final int codec) {
		return ((frame & 0x0F) << 4) + ((codec & 0x0F) << 0);
	}

	//yuv420sp转bitmap
	public static Bitmap rawByteArray2RGBABitmap2(byte[] data, int width, int height) {
		int frameSize = width * height;
		int[] rgba = new int[frameSize];

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				int y = (0xff & ((int) data[i * width + j]));
				int u = (0xff & ((int) data[frameSize + (i >> 1) * width + (j & ~1) + 0]));
				int v = (0xff & ((int) data[frameSize + (i >> 1) * width + (j & ~1) + 1]));
				y = y < 16 ? 16 : y;

				int r = Math.round(1.164f * (y - 16) + 1.596f * (v - 128));
				int g = Math.round(1.164f * (y - 16) - 0.813f * (v - 128) - 0.391f * (u - 128));
				int b = Math.round(1.164f * (y - 16) + 2.018f * (u - 128));

				r = r < 0 ? 0 : (r > 255 ? 255 : r);
				g = g < 0 ? 0 : (g > 255 ? 255 : g);
				b = b < 0 ? 0 : (b > 255 ? 255 : b);

				rgba[i * width + j] = 0xff000000 + (b << 16) + (g << 8) + r;
			}

		Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bmp.setPixels(rgba, 0 , width, 0, 0, width, height);
		return bmp;
	}

}
