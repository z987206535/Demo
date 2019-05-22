package cn.tedu.oa.domain;

public class Module implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Module(int id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
