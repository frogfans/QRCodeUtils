package com.zengyu;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zengyu.QRException.InvalidColorException;
import com.zengyu.QRException.InvalidLengthException;
import com.zengyu.QRException.InvalidPathException;

public class QRUtils implements IQR {
	/**
	 * ��ά������
	 */
	private String content = "";
	/**
	 * ���·��
	 */
	private String outputPath = "";
	/**
	 * ��־·��
	 */
	private String logoPath = "";
	/**
	 * ��־�߳���Ϊ��ά��߳���1/5
	 */
	private int logoSize = 80;
	/**
	 * ��ά��߳���Ĭ��400
	 */
	private int qrcodeSize = 400;
	/**
	 * ��ά��߾࣬Ϊ��ά��߳���1/100
	 */
	private int qrcodeMargin = 4;
	/**
	 * ��ά����ɫ��Ĭ�Ϻ�ɫ
	 */
	private int qrcodeColor = 0xff000000;

	/**
	 * ���ɹ���
	 */
	private void encode() {
		// ���ñ������
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CharacterSetECI.UTF8);
		hints.put(EncodeHintType.MARGIN, qrcodeMargin);
		try {
			// ���ɶ�ά�����
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrcodeSize, qrcodeSize,
					hints);
			int qrcodeWidth = bitMatrix.getWidth();
			int qrcodeHeight = bitMatrix.getHeight();
			// ���ɶ�ά��ͼƬ
			BufferedImage qrcodeImage = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < qrcodeWidth; x++) {
				for (int y = 0; y < qrcodeHeight; y++) {
					qrcodeImage.setRGB(x, y, (bitMatrix.get(x, y) ? qrcodeColor : 0xffffffff));
				}
			}
			// �ж��Ƿ�����־
			if (logoPath != "") {
				File logoFile = new File(logoPath);
				if (logoFile.exists()) {
					Image logoImgSrc = ImageIO.read(new File(logoPath));
					// ѹ����־
					int width = logoImgSrc.getWidth(null);
					int height = logoImgSrc.getHeight(null);
					if (width > logoSize) {
						width = logoSize;
					}
					if (height > logoSize) {
						height = logoSize;
					}
					Image logoImg = logoImgSrc.getScaledInstance(width, height, Image.SCALE_SMOOTH);
					// ���Ʊ�־
					BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					Graphics graphics = bufferedImage.getGraphics();
					graphics.drawImage(logoImg, 0, 0, null);
					graphics.dispose();
					logoImgSrc = logoImg;
					// �����־
					Graphics2D graph = qrcodeImage.createGraphics();
					int x = (qrcodeSize - width) / 2;
					int y = (qrcodeSize - height) / 2;
					graph.drawImage(logoImgSrc, x, y, width, height, null);
					Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
					graph.setStroke(new BasicStroke(3f));
					graph.draw(shape);
					graph.dispose();
				}
			}
			// �����ά��
			String outputName = "" + new Date().getSeconds() + ".jpg";
			ImageIO.write(qrcodeImage, outputName, new File(outputPath));
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void encode(String content) {
		encode(content, null, null, null, null);
	}

	@Override
	public void encode(String content, String outputPath) {
		encode(content, outputPath, null, null, null);
	}

	@Override
	public void encode(String content, String outputPath, String logoPath) {
		encode(content, outputPath, logoPath, null, null);
	}

	@Override
	public void encode(String content, String outputPath, String logoPath, String qrcodeSize) {
		encode(content, outputPath, logoPath, qrcodeSize, null);
	}

	@Override
	public void encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor) {
		try {
			formatParams(content, outputPath, logoPath, qrcodeSize, qrcodeColor);
			encode();
		} catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (InvalidLengthException e) {
			e.printStackTrace();
		} catch (InvalidColorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʽ������
	 * 
	 * @param content
	 * @param outputPath
	 * @param logoPath
	 * @param qrcodeSize
	 * @param qrcodeColor
	 * @throws InvalidPathException
	 *             ·����Ч�쳣
	 * @throws InvalidLengthException
	 *             ������Ч�쳣
	 * @throws InvalidColorException
	 *             ��ɫ��Ч�쳣
	 */
	private void formatParams(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor)
			throws InvalidPathException, InvalidLengthException, InvalidColorException {
		if (outputPath != null) {
			if (outputPath.trim() == "") {
				throw new InvalidPathException("The output path can't be empty string.");
			} else {
				this.outputPath = outputPath.trim();
			}
		}
		if (logoPath != null) {
			if (logoPath.trim() == "") {
				throw new InvalidPathException("The logo path can't be empty string.");
			} else {
				this.logoPath = logoPath.trim();
			}
		}
		if (qrcodeSize != null) {
			try {
				int size = Integer.valueOf(qrcodeSize);
				if (size == 0) {
					throw new InvalidLengthException("The QR code size can't be zero.");
				} else {
					this.qrcodeSize = size;
					this.logoSize = size / 5;
					this.qrcodeMargin = size / 100;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (qrcodeColor != null) {
			try {
				int color = Integer.parseInt(qrcodeColor, 16);
				if (color == 0xffffffff) {
					throw new InvalidLengthException("The QR code color can't be white.");
				} else {
					this.qrcodeSize = color;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
