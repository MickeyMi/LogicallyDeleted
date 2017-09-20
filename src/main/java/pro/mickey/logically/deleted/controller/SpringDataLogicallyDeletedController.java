package pro.mickey.logically.deleted.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pro.mickey.logically.deleted.repository.MickeyLogicallyDeletedRepository;
import pro.mickey.logically.deleted.repository.model.MickeyLogicallyDeletedEntity;

@RestController
@RequestMapping("/rest/SpringData/logicallyDeleted")
public class SpringDataLogicallyDeletedController {

	@Autowired
	private MickeyLogicallyDeletedRepository mickeyLogicallyDeletedRepository;

	@GetMapping("EntryData")
	private String EntryData() {
		for (int i = 0; i < 10; i++) {
			MickeyLogicallyDeletedEntity deleted = new MickeyLogicallyDeletedEntity();
			deleted.setName("测试" + i);
			mickeyLogicallyDeletedRepository.save(deleted);
		}
		return "录入成功";
	}

	@GetMapping("delete/{id}")
	private String delete(@PathVariable Integer id) {
		mickeyLogicallyDeletedRepository.delete(id);
		return "录入成功";
	}

	@GetMapping("findAll")
	@ResponseBody
	private List<MickeyLogicallyDeletedEntity> findAll() {
		return mickeyLogicallyDeletedRepository.findAll();
	}
}
