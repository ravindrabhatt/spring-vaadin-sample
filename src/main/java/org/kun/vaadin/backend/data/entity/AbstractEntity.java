package org.kun.vaadin.backend.data.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Id
	private String id;

	@Version
	private int version;

	public String getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, version);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AbstractEntity that = (AbstractEntity) o;
		return version == that.version &&
				Objects.equals(id, that.id);
	}
}
