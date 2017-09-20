package pro.mickey.logically.deleted.repository.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logically_deleted")
public class MickeyLogicallyDeletedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "logically_deleted_id")
	private Integer logicallyDeletedId;

	@Column(name = "name")
	private String name;

	@Column(name = "is_delete")
	private Boolean isDelete;

	@Column(name = "create_date")
	private Date createDate;

	/**
	 * 
	 * @return logically_deleted_id
	 */
	public Integer getLogicallyDeletedId() {
		return logicallyDeletedId;
	}

	/**
	 * 
	 * @param logicallyDeletedId
	 */
	public void setLogicallyDeletedId(Integer logicallyDeletedId) {
		this.logicallyDeletedId = logicallyDeletedId;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 是否删除：默认False
	 * 
	 * @return is_delete 是否删除：默认False
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * 是否删除：默认False
	 * 
	 * @param isDelete
	 *            是否删除：默认False
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 创建时间
	 * 
	 * @return create_date 创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建时间
	 * 
	 * @param createDate
	 *            创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		MickeyLogicallyDeletedEntity other = (MickeyLogicallyDeletedEntity) that;
		return (this.getLogicallyDeletedId() == null ? other.getLogicallyDeletedId() == null : this.getLogicallyDeletedId().equals(other.getLogicallyDeletedId()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
				&& (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getLogicallyDeletedId() == null) ? 0 : getLogicallyDeletedId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
		result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
		return result;
	}
}