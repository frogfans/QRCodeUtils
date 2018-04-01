package com.zengyu;

import com.zengyu.QRException.InvalidColorException;
import com.zengyu.QRException.InvalidLengthException;
import com.zengyu.QRException.InvalidPathException;

public interface IQR {
	void encode();

	void encode(String content);

	void encode(String content, String outputPath);

	void encode(String content, String outputPath, String logoPath);

	void encode(String content, String outputPath, String logoPath, String qrcodeSize);

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
	void encode(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor);

	/**
	 * ��ʽ������
	 * 
	 * @param content
	 * @param outputPath
	 * @param logoPath
	 * @param qrcodeSize
	 * @param qrcodeColor
	 * @throws InvalidPathException
	 *             ��Ч·���쳣
	 * @throws InvalidLengthException
	 *             ��Ч�����쳣
	 * @throws InvalidColorException
	 *             ��Ч��ɫ�쳣
	 */
	void formatParams(String content, String outputPath, String logoPath, String qrcodeSize, String qrcodeColor)
			throws InvalidPathException, InvalidLengthException, InvalidColorException;
}
