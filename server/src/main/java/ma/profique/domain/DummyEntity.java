package ma.profique.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Unindex
public class DummyEntity {

    @Id
    private Long id;

	@NotNull
    private String name;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}