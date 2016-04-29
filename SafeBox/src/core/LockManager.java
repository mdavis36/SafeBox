package core;

public class LockManager {
	/**
	 * @return true if lock was acquired, otherwise false
	 */
	public static boolean requestLock() {
		if (StorageManager.fileExists(Consts.LOCK_FILE_NAME)) {
			return false;
		}

		if (StorageManager.saveToFile(new byte[1], Consts.LOCK_FILE_NAME)) {
			return true;
		}

		return false;
	}

	/**
	 * @return true if the lock was released, otherwise false
	 */
	public static boolean releaseLock() {
		if (!StorageManager.fileExists(Consts.LOCK_FILE_NAME)) {
			return false;
		}

		return StorageManager.deleteFile(Consts.LOCK_FILE_NAME);
	}

}
