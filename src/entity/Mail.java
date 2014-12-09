/**
 * 
 */
package entity;

/**
 * @author philippe
 * 
 */
public class Mail {

	private String id;
	private String source;
	private String destination;
	private String title;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		String str = "";

		str = this.source + "\t" + this.destination + "\t" + this.title + "\t"
				+ this.content;

		return str;
	}

	/**
	 * Populate a mail with a tab beetwen each data
	 * 
	 * @param text
	 */
	public void populat(String text) {

		String[] var = text.split("\t");

		int i = 0;
		while (i < var.length) {

			if (i == 0) {
				this.source = var[0];
			} else if (i == 1) {
				this.destination = var[1];
			} else if (i == 2) {
				this.title = var[2];
			} else if (i == 3) {
				this.content = var[3];
			}
		}
	}

	/**
	 * Populate a mail with a tab of string
	 * 
	 * @param text
	 */
	public void populat(String[] values) {

		this.source = values[0];
		this.destination = values[1];
		this.title = values[2];
		this.content = "";

		int i = 3;
		while (i < values.length) {
			content += values[i] + " ";
			i++;
		}
	}

}
