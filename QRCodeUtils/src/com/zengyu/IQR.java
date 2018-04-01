package com.zengyu;

/**
 * ����ӿ�
 * 
 * @author Agent ZengYu
 *
 */
public interface IQR {
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
}
