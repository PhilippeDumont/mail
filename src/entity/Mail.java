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

	public Mail() {
		id = null;
		source = null;
		destination = null;
		title = null;
		content = null;
	}

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

		str = this.id + "\t" + this.source + "\t" + this.destination + "\t"
				+ this.title + "\t" + this.content;

		return str;
	}

	/**
	 * Populate a mail with a tab between each data
	 * 
	 * @param text
	 */
	public void populat(String text) {
		String[] var = text.split("\t");
		this.populat(var);
	}

	/**
	 * Populate a mail with a tab of string
	 * 
	 * @param text
	 */
	public void populat(String[] values) {

		this.id = values[0];
		this.source = values[1];
		this.destination = values[2];
		this.title = values[3];
		this.content = "";

		int i = 4;
		while (i < values.length) {
			content += values[i] + " ";
			i++;
		}
	}

}
