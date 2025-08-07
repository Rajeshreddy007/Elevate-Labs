
public class Library {
	private int bookid;
	private String tittle;
	private String author;
	private int uid;
	private String name;

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Issued Books : [bookid=" + bookid + ", tittle=" + tittle + ", author=" + author + ", uid=" + uid + ", name="
				+ name + "]";
	}
	
	
}
