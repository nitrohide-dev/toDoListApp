package commons;

/**
 * Class to validate and create a board.
 */
public class CreateBoardModel {

	public String key;
	public String title;

	public CreateBoardModel(){} // for object mappers, please don't use.

	public CreateBoardModel(String key, String title) {
		this.key = key;
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Checks if the given model is valid
	 * @return true if all requirements are met, false otherwise
	 */
	public boolean isValid() {
		if (key == null)
			return false;
		if (title == null)
			return false;
		return true;
	}
}
