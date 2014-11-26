/**
 * 
 */
package entity;

/**
 * @author philippe
 * 
 */
public class Mail {

	private String source;
	private String destination;
	private String title;
	private String content;

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

	public String convertInString() {
		String str = "";

		str = this.source + "\n" + this.destination + "\n" + this.title + "\n"
				+ this.content;

		return str;
	}
	
	public boolean populat(String text){
		
		String[] var = text.split("\n");
		
		int i = 0;
		while(i < var.length){
			
			if(i == 0){
				this.source = var[0];
			}else if(i == 1){
				this.destination = var[1];
			}
			
		}
		
	}

}
