package logic;

public interface Managable {
	void read();

	Boolean validation();

	void save();

	default void saveAndValidate() {
		if(validation()) {
			save();
		}
	}

}