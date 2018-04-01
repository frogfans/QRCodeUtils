package com.zengyu;

import java.awt.image.BufferedImage;

/**
 * ����ӿ�
 * 
 * @author Agent ZengYu
 *
 */
public interface IQR {
	BufferedImage getQrcodeImage();
	
	QRUtils encode(String content);

	QRUtils encode(String content, String outputPath);

	QRUtils encode(String content, String outputPath, String logoPath);

	QRUtils encode(String content, String outputPath, String logoPath, String qrcodeSize);

	/**
	 * ���ɶ�ά��
	 * 
	 * @param content
	 *            ��ά������
	 * @param outputPath
	 *            ���·��
	 * @param logoPath
	 *            ��־·��
	 * @param qrcodeSize
	 *            ��ά��߳�
	 * @param qrcodeColor
	 *            ��ά����ɫ
	 */
	QRUtils encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor);
}
