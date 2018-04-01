package com.zengyu;

public class QRException {
	/**
	 * �������쳣
	 * 
	 * @author Agent ZengYu
	 *
	 */
	static class NullContentException extends RuntimeException {
		private static final long serialVersionUID = 4527325632148649544L;

		public NullContentException(String s) {
			super(s);
		}
	}

	/**
	 * ·����Ч�쳣
	 * 
	 * @author Agent ZengYu
	 *
	 */
	static class InvalidPathException extends RuntimeException {
		private static final long serialVersionUID = 5198180599267759598L;

		public InvalidPathException(String s) {
			super(s);
		}
	}

	/**
	 * ������Ч�쳣
	 * 
	 * @author Agent ZengYu
	 *
	 */
	static class InvalidLengthException extends RuntimeException {
		private static final long serialVersionUID = -6618211142772526858L;

		public InvalidLengthException(String s) {
			super(s);
		}
	}

	/**
	 * ��ɫ��Ч�쳣
	 * 
	 * @author Agent ZengYu
	 *
	 */
	static class InvalidColorException extends RuntimeException {
		private static final long serialVersionUID = -8701028977791094270L;

		public InvalidColorException(String s) {
			super(s);
		}
	}
}
