package pro.mickey.logically.deleted.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pro.mickey.logically.deleted.mapper.MickeyLogicallyDeletedMapper;
import pro.mickey.logically.deleted.mapper.model.MickeyLogicallyDeleted;

@RestController
@RequestMapping("/rest/Mybatis/logicallyDeleted")
public class MybatisLogicallyDeletedController {

	@Autowired
	private MickeyLogicallyDeletedMapper mickeyLogicallyDeletedMapper;

	@GetMapping("EntryData")
	private String EntryData() {
		for (int i = 0; i < 10; i++) {
			MickeyLogicallyDeleted deleted = new MickeyLogicallyDeleted();
			deleted.setName("测试" + i);
			mickeyLogicallyDeletedMapper.insertSelective(deleted);
		}
		return "录入成功";
	}

	@GetMapping("delete/{id}")
	private String delete(@PathVariable Integer id) {
		mickeyLogicallyDeletedMapper.deleteByPrimaryKey(id);
		return "录入成功";
	}

	@GetMapping("findAll")
	@ResponseBody
	private List<MickeyLogicallyDeleted> findAll() {
		return mickeyLogicallyDeletedMapper.findAll();
	}
}
