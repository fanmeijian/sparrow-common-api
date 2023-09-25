package cn.sparrowmini.common.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;

import cn.sparrowmini.common.CommonProp;

@NoRepositoryBean
public interface CommonPropRepository<T extends CommonProp, ID> extends JpaRepository<T, ID> {
	
	@Modifying
	default void updateStatus(String status, Boolean enabled, ID[] ids) {
		List<T> ts = new ArrayList<>();
		for (ID id : ids) {
			T t = this.getById(id);
			if (status != null) {
				t.setStat(status);
			}
			if (enabled != null) {
				t.setEnabled(enabled);
			}

		}
		this.saveAll(ts);
	}
}
