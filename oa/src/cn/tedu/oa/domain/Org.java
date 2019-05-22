package cn.tedu.oa.domain;

/**
 * t_org表对应
 * 
 * @author pc
 *
 */
public class Org implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String sn;
	private String descr;
	private int pid;
	private String pname;

	public Org() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Org(int id, String name, String sn, String descr, int pid, String pname) {
		super();
		this.id = id;
		this.name = name;
		this.sn = sn;
		this.descr = descr;
		this.pid = pid;
		this.pname = pname;
	}

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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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
		Org other = (Org) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Org [id=" + id + ", name=" + name + ", sn=" + sn + ", descr=" + descr + ", pid=" + pid + ", pname="
				+ pname + "]";
	}

}
