package pro.mickey.logically.deleted.mapper;

import java.util.List;

import pro.mickey.logically.deleted.mapper.model.MickeyLogicallyDeleted;

public interface MickeyLogicallyDeletedMapper {

	List<MickeyLogicallyDeleted> findAll();

	int deleteByPrimaryKey(Integer logicallyDeletedId);

	int insert(MickeyLogicallyDeleted record);

	int insertSelective(MickeyLogicallyDeleted record);

	MickeyLogicallyDeleted selectByPrimaryKey(Integer logicallyDeletedId);

	int updateByPrimaryKeySelective(MickeyLogicallyDeleted record);

	int updateByPrimaryKey(MickeyLogicallyDeleted record);
}